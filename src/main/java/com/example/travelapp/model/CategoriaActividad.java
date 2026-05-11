package com.example.travelapp.model;

public enum CategoriaActividad {
    CULTURAL("Cultural"),
    GASTRONOMICA("Gastronómica"),
    AVENTURA("Aventura"),
    RELAX("Relax"),
    OCIO("Ocio"),
    NATURALEZA("Naturaleza");

    private final String nombreBonito;

    CategoriaActividad(String nombreBonito) {
        this.nombreBonito = nombreBonito;
    }

    public String getNombreBonito() {
        return nombreBonito;
    }
}
