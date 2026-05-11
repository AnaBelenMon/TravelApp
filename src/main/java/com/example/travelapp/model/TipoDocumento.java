package com.example.travelapp.model;

public enum TipoDocumento {
    PDF("PDF"),
    IMAGEN("Imagen"),
    BILLETE("Billete"),
    RESERVA("Reserva"),
    OTRO("Otro");

    private final String nombre;

    TipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esVisual() {
        return this == IMAGEN || this == PDF;
    }
}
