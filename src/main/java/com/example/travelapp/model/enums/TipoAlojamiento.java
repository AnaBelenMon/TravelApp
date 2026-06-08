package com.example.travelapp.model.enums;

/**
 * Enum que representa los tipos de alojamiento disponibles dentro de un viaje.
 * Cada tipo incluye un nombre legible para mostrar en la interfaz de usuario.
 * Este enum permite clasificar los alojamientos y facilita su organización,
 * filtrado y análisis dentro de la aplicación TravelApp.
 * Se utiliza directamente en la clase {@link com.example.travelapp.model.Alojamiento}.
 * Incluye métodos utilitarios para conversión y obtención de nombres.
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoAlojamiento {
    HOTEL("Hotel"),
    HOSTAL("Hostal"),
    APARTAMENTO("Apartamento"),
    CASA_RURAL("Casa rural"),
    CAMPING("Camping"),
    OTRO("Otro");

    private final String nombre;

    /**
     * Constructor del enum que asigna un nombre legible al tipo de alojamiento.
     *
     * @param nombre nombre visible para el usuario
     */
    TipoAlojamiento(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del tipo de alojamiento.
     *
     * @return nombre del tipo de alojamiento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la representación en texto del tipo de alojamiento,
     * utilizada en interfaces gráficas y listados.
     *
     * @return nombre legible del tipo
     */
    @Override
    public String toString() {
        return nombre;
    }
}
