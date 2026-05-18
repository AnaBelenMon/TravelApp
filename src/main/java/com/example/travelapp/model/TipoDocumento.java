package com.example.travelapp.model;

public enum TipoDocumento {
    PDF("PDF"),
    IMAGEN("Imagen"),
    BILLETE("Billete"),
    RESERVA("Reserva"),
    OTRO("Otro");

    private final String nombre;

    /**
     *
     * @param nombre
     */
    TipoDocumento(String nombre) {
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
    public boolean esVisual() {
        return this == IMAGEN || this == PDF;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return nombre;
    }
}
