package com.example.travelapp.model;

/**
 * Enum que representa las posibles categorías de una actividad dentro de un viaje.
 * Estas categorías permiten clasificar las actividades para facilitar
 * la organización, filtrado y búsqueda dentro de la aplicación.
 * Cada categoría tiene un nombre legible asociado para mostrar al usuario.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public enum CategoriaActividad {
    CULTURAL("Cultural"),
    GASTRONOMICA("Gastronómica"),
    AVENTURA("Aventura"),
    RELAX("Relax"),
    OCIO("Ocio"),
    NATURALEZA("Naturaleza");

    private final String nombre;

    /**
     * Constructor del enum con el nombre legible de la categoría.
     * @param nombre nombre que se mostrará al usuario
     */
    CategoriaActividad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible de la categoría.
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Representación en texto de la categoría.
     * @return nombre legible de la categoría
     */
    @Override
    public String toString() {
        return nombre;
    }
}