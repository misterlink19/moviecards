package com.lauracercas.moviecards.util;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
public class Messages {

    // Constructor privado para evitar la instanciación
    private Messages() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no se puede instanciar");
    }
    public static final String CARD_REGISTRATION_SUCCESS = "Se ha registrado el actor en la película. Ficha creada correctamente";
    public static final String CARD_ALREADY_EXISTS = "Ya se ha inscrito este actor en esta película";
    public static final String ERROR_MESSAGE = "Ha ocurrido un error";
    public static final String UPDATED_ACTOR_SUCCESS = "Actor actualizado correctamente";
    public static final String UPDATED_MOVIE_SUCCESS = "Película actualizada correctamente";
    public static final String SAVED_ACTOR_SUCCESS = "Actor guardado correctamente";
    public static final String SAVED_MOVIE_SUCCESS = "Película guardada correctamente";
    public static final String EDIT_ACTOR_TITLE = "Editar Actor";
    public static final String NEW_ACTOR_TITLE = "Nuevo Actor";
    public static final String EDIT_MOVIE_TITLE = "Editar Película";
    public static final String NEW_MOVIE_TITLE = "Nueva Película";

}
