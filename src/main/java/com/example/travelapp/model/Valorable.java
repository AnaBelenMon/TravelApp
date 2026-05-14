package com.example.travelapp.model;

public interface Valorable {

    /**
     * Asigna una valoración al elemento.
     * @param puntuacion valor entre 1 y 5
     * @throws IllegalArgumentException si la puntuación no es válida
     */
    void valorar(int puntuacion);
    void limpiarValoracion();
}
