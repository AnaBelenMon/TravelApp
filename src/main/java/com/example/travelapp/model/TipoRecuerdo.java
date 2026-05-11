package com.example.travelapp.model;

public enum TipoRecuerdo {

    FOTO("Foto"),
    VIDEO("Vídeo"),
    AUDIO("Audio"),
    DOCUMENTO("Documento");

    private final String nombre;

    TipoRecuerdo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esVisual() {
        return this == FOTO || this == VIDEO;
    }

    public boolean esMultimedia() {
        return this != DOCUMENTO;
    }
}
