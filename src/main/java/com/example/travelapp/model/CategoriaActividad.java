package com.example.travelapp.model;

public enum CategoriaActividad {
    CULTURAL("Cultural"),
    GASTRONOMICA("Gastronómica"),
    AVENTURA("Aventura"),
    RELAX("Relax"),
    OCIO("Ocio"),
    NATURALEZA("Naturaleza");

    private final String nombre;

    CategoriaActividad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
