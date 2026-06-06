package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa los tipos de alojamiento disponibles dentro de un viaje.
 * Cada tipo incluye un nombre legible para mostrar en la interfaz.
 *
 * Se utiliza para clasificar alojamientos y facilitar su organización,
 * filtrado y análisis dentro de la aplicación.
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

    TipoAlojamiento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static TipoAlojamiento fromNombre(String nombre) {
        for (TipoAlojamiento t : values()) {
            if (t.nombre.equalsIgnoreCase(nombre)) return t;
        }
        throw new IllegalArgumentException("Tipo de alojamiento no válido: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(TipoAlojamiento::getNombre)
                .toList();
    }
}
