package com.example.travelapp.model;

import java.util.Objects;

public enum Emocion {
    FELICIDAD("😊"),
    TRISTEZA("😢"),
    SORPRESA("😲"),
    MIEDO("😨"),
    NOSTALGIA("🥹"),
    EUFORIA("🤩"),
    TRANQUILIDAD("😌");

    private final String icono;

    Emocion(String icono) {
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }
}
