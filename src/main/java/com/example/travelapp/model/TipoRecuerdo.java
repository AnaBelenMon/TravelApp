package com.example.travelapp.model;

public enum TipoRecuerdo {

    FOTO("Foto"),
    VIDEO("Vídeo"),
    AUDIO("Audio"),
    DOCUMENTO("Documento");

    private final String nombre;

    /**
     *
     * @param nombre
     */
    TipoRecuerdo(String nombre) {
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
        return this == FOTO || this == VIDEO;
    }

    /**
     *
     * @return
     */
    public boolean esMultimedia() {
        return this != DOCUMENTO;
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
