package com.example.travelapp.model;

/**
 * Enum que representa los diferentes tipos de documentos
 * que pueden estar asociados a un viaje.
 *
 * Incluye documentos como billetes, reservas, imágenes, PDFs
 * y otros archivos relacionados con la planificación del viaje.
 *
 * Permite clasificar y gestionar los documentos de forma organizada
 * dentro de la aplicación.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoDocumento {

    PDF("PDF"),
    IMAGEN("Imagen"),
    BILLETE("Billete"),
    RESERVA("Reserva"),
    OTRO("Otro");

    private final String nombre;

    /**
     * Constructor del tipo de documento con nombre legible.
     *
     * @param nombre nombre visible del tipo de documento
     */
    TipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del tipo de documento.
     *
     * @return nombre del tipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si el documento es de tipo visual o visualizable.
     * (por ejemplo PDF o imagen)
     *
     * @return true si es visual
     */
    public boolean esVisual() {
        return this == IMAGEN || this == PDF;
    }

    /**
     * Representación en texto del tipo de documento.
     *
     * @return nombre del tipo
     */
    @Override
    public String toString() {
        return nombre;
    }
}