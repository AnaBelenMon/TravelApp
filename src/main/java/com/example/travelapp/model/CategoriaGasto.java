package com.example.travelapp.model;

public enum CategoriaGasto {
    COMIDA("Comida"),
    TRANSPORTE("Transporte"),
    ALOJAMIENTO("Alojamiento"),
    OCIO("Ocio"),
    COMPRAS("Compras"),
    OTROS("Otros");

    private final String nombre;

    CategoriaGasto(String nombre) {
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