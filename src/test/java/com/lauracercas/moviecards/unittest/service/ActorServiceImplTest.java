package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.actor.ActorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
class ActorServiceImplTest {

    @Mock
    private RestTemplate template;
    @InjectMocks
    private ActorService sut = new ActorServiceImpl();
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllActors() {
        Actor actors[] = new Actor[2];
        actors[0] = new Actor();
        Date birthDateExample1 = new Date();
        Date deadDateExample1 = new Date();
        actors[0].setBirthDate(birthDateExample1);
        actors[0].setDeadDate(deadDateExample1);

        actors[1] = new Actor();
        Date birthDateExample2 = new Date();
        Date deadDateExample2 = new Date();
        actors[1].setBirthDate(birthDateExample2);
        actors[1].setDeadDate(deadDateExample2);

        when(template.getForObject(anyString(), any())).thenReturn(actors);
        List<Actor> result = sut.getAllActors();

        assertEquals(2, result.size());
        assertEquals(birthDateExample1, result.get(0).getBirthDate());
        assertEquals(deadDateExample1, result.get(0).getDeadDate());
        assertEquals(birthDateExample2, result.get(1).getBirthDate());
        assertEquals(deadDateExample2, result.get(1).getDeadDate());
    }

    @Test
    public void shouldGetActorById() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("Sample Actor");
        Date birthDateExample = new Date();
        Date deadDateExample = new Date();
        actor.setBirthDate(birthDateExample);
        actor.setDeadDate(deadDateExample);
        when(template.getForObject(anyString(), any())).thenReturn(actor);

        Actor result = sut.getActorById(1);

        assertEquals(1, result.getId());
        assertEquals("Sample Actor", result.getName());
        assertEquals(birthDateExample, result.getBirthDate());
        assertEquals(deadDateExample, result.getDeadDate());
    }

}