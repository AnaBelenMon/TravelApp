package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa los diferentes tipos de viaje disponibles en la aplicación.
 * Cada tipo incluye un nombre legible para mostrar en la interfaz.
 *
 * Se utiliza para clasificar los viajes según su propósito o experiencia.
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoViaje {
    AVENTURA("Aventura"),
    RELAX("Relax"),
    CULTURAL("Cultural"),
    NEGOCIOS("Negocios"),
    ROMANTICO("Romántico"),
    FAMILIAR("Familiar"),
    OTROS("Otros");

    private final String nombre;

    TipoViaje(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static TipoViaje fromNombre(String nombre) {
        for (TipoViaje t : values()) {
            if (t.nombre.equalsIgnoreCase(nombre)) return t;
        }
        throw new IllegalArgumentException("Tipo de viaje no válido: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(TipoViaje::getNombre)
                .toList();
    }
}