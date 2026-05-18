package com.example.travelapp.model;

public enum CategoriaActividad {
    CULTURAL("Cultural"),
    GASTRONOMICA("Gastronómica"),
    AVENTURA("Aventura"),
    RELAX("Relax"),
    OCIO("Ocio"),
    NATURALEZA("Naturaleza");

    private final String nombre;

    /**
     *
     * @param nombre
     */
    CategoriaActividad(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "CategoriaActividad{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
