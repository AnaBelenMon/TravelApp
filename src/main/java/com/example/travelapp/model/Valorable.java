package com.example.travelapp.model;

/**
 * Interfaz que define el comportamiento de elementos que pueden ser valorados.
 * Permite asignar y eliminar una valoración.
 */
public interface Valorable {

    /**
     * Asigna una valoración al elemento.
     * La puntuación debe estar en un rango válido (normalmente de 1 a 5).
     *
     * @param puntuacion valor numérico de la valoración
     * @throws IllegalArgumentException si la puntuación no es válida
     */
    void valorar(int puntuacion);

    /**
     * Elimina o reinicia la valoración del elemento.
     */
    void limpiarValoracion();
}