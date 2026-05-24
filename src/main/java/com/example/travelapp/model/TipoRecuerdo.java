package com.example.travelapp.model;

/**
 * Enum que representa los tipos de recuerdos que un usuario
 * puede guardar en un viaje.
 *
 * Los recuerdos pueden ser fotos, vídeos, audios o documentos,
 * permitiendo almacenar diferentes tipos de contenido multimedia
 * asociado a la experiencia del viaje.
 *
 * Este enum ayuda a clasificar y gestionar los recuerdos dentro
 * de la aplicación de forma organizada.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoRecuerdo {

    FOTO("Foto"),
    VIDEO("Vídeo"),
    AUDIO("Audio"),
    DOCUMENTO("Documento");

    private final String nombre;

    /**
     * Constructor del tipo de recuerdo con nombre legible.
     *
     * @param nombre nombre visible del tipo de recuerdo
     */
    TipoRecuerdo(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del tipo de recuerdo.
     *
     * @return nombre del tipo de recuerdo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si el recuerdo es de tipo visual.
     * (fotos o vídeos)
     *
     * @return true si es visual
     */
    public boolean esVisual() {
        return this == FOTO || this == VIDEO;
    }

    /**
     * Indica si el recuerdo es multimedia.
     * (todo excepto documentos)
     *
     * @return true si es multimedia
     */
    public boolean esMultimedia() {
        return this != DOCUMENTO;
    }

    /**
     * Representación en texto del tipo de recuerdo.
     *
     * @return nombre del tipo
     */
    @Override
    public String toString() {
        return nombre;
    }
}