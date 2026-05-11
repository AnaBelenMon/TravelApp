package com.example.travelapp.model;

public enum CategoriaGasto {
    COMIDA("Comida️"),
    TRANSPORTE("Transporte"),
    ALOJAMIENTO("Alojamiento"),
    OCIO("Ocio"),
    COMPRAS("Compras️"),
    OTROS("Otros");

    private final String nombreBonito;

    CategoriaGasto(String nombreBonito) {
        this.nombreBonito = nombreBonito;
    }

    public String getNombreBonito() {
        return nombreBonito;
    }
}

