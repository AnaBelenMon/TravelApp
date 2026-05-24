package com.example.travelapp.model;

/**
 * Enum que representa las emociones asociadas a experiencias o recuerdos
 * dentro de la aplicación.
 *
 * Cada emoción incluye un icono representativo y un nombre legible,
 * facilitando su visualización en la interfaz de usuario.
 *
 * Se utiliza principalmente para enriquecer la experiencia del usuario
 * al registrar estados emocionales en actividades, viajes o recuerdos.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
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

    /**
     * Constructor del enum con icono y nombre.
     *
     * @param icono representación visual de la emoción
     * @param nombre nombre legible de la emoción
     */
    Emocion(String icono, String nombre) {
        this.icono = icono;
        this.nombre = nombre;
    }

    /**
     * Obtiene el icono de la emoción.
     *
     * @return icono emoji
     */
    public String getIcono() {
        return icono;
    }

    /**
     * Obtiene el nombre de la emoción.
     *
     * @return nombre de la emoción
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Representación en texto de la emoción.
     *
     * @return icono + nombre
     */
    @Override
    public String toString() {
        return icono + " - " + nombre;
    }
}