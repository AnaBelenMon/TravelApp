package com.example.travelapp.model;

public enum Emocion {
    FELICIDAD("😊","Felicidad"),
    TRISTEZA("😢","Tristeza"),
    SORPRESA("😲","Sorpresa"),
    MIEDO("😨","Miedo"),
    NOSTALGIA("🥹","Nostalgia"),
    EUFORIA("🤩","Euforia"),
    TRANQUILIDAD("😌","Tranquilidad");

    private final String icono;
    private final String nombre;

    Emocion(String icono, String nombre) {
        this.icono = icono;
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return icono + " - " + nombre;
    }
}
