package com.lauracercas.moviecards.unittest.controller;

import com.lauracercas.moviecards.controller.ActorController;
import com.lauracercas.moviecards.dto.ActorDTO;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
class ActorControllerTest {

    private ActorController controller;

    @Mock
    private ActorService actorServiceMock;

    private AutoCloseable closeable;
    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        controller = new ActorController(actorServiceMock);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGoListActorAndGetAllActors() {
        List<Actor> actors = new ArrayList<>();

        when(actorServiceMock.getAllActors()).thenReturn(actors);

        String viewName = controller.getActorsList(model);

        assertEquals("actors/list", viewName);
    }

    @Test
    public void shouldInitializeActor() {
        String viewName = controller.newActor(model);

        assertEquals("actors/form", viewName);

        verify(model).addAttribute("actor", new Actor());
        verify(model).addAttribute("title", Messages.NEW_ACTOR_TITLE);
    }

    @Test
    public void shouldSaveActorWithNoErrors() {
        Date birthDateExample = new Date();
        Date deadDateExample = new Date();
        ActorDTO actorDTO = new ActorDTO(null, "Sample name", birthDateExample, deadDateExample, "Sample country",
                new ArrayList<>());

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        Actor actor = new Actor();
        actor.setId(1);
        when(actorServiceMock.save(any(Actor.class))).thenReturn(actor);

        String viewName = controller.saveActor(actorDTO, result, model);

        assertEquals("redirect:/actors", viewName);
        assertEquals(birthDateExample, actorDTO.getBirthDate());
        assertEquals(deadDateExample, actorDTO.getDeadDate());

        verify(model).addAttribute(eq("actor"), any(Actor.class));
        verify(model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
        verify(model).addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
    }

    @Test
    public void shouldUpdateActorWithNoErrors() {
        Date birthDateExample = new Date();
        Date deadDateExample = new Date();
        ActorDTO actorDTO = new ActorDTO(1, "Sample name", birthDateExample, deadDateExample, "Sample country",
                new ArrayList<>());
        
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        
        Actor actor = new Actor();
        actor.setId(1);
        when(actorServiceMock.save(any(Actor.class))).thenReturn(actor);

        String viewName = controller.saveActor(actorDTO, result, model);

        assertEquals("redirect:/actors", viewName);
        assertEquals(birthDateExample, actorDTO.getBirthDate());
        assertEquals(deadDateExample, actorDTO.getDeadDate());

        verify(model).addAttribute(eq("actor"), any(Actor.class));
        verify(model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
        verify(model).addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
    }

    @Test
    public void shouldTrySaveActorWithErrors() {
        ActorDTO actorDTO = new ActorDTO();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = controller.saveActor(actorDTO, result, model);

        assertEquals("actors/form", viewName);

        verifyNoInteractions(model);
    }

    @Test
    public void shouldGoToEditActor() {
        Actor actor = new Actor();
        actor.setId(1);
        List<Movie> movies = List.of(new Movie());
        actor.setMovies(movies);
        when(actorServiceMock.getActorById(actor.getId())).thenReturn(actor);

        String viewName = controller.editActor(actor.getId(), model);

        assertEquals("actors/form", viewName);

        verify(model).addAttribute("actor", actor);
        verify(model).addAttribute("movies", movies);
        verify(model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
    }

}