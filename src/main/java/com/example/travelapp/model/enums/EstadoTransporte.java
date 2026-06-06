package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa el estado de un transporte dentro de un viaje.
 * Permite gestionar y clasificar transportes según su situación actual.
 *
 * Cada estado incluye un nombre legible para mostrar en la interfaz.
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum EstadoTransporte {
    PENDIENTE("Pendiente"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado");

    private final String nombre;

    EstadoTransporte(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static EstadoTransporte fromNombre(String nombre) {
        for (EstadoTransporte e : values()) {
            if (e.nombre.equalsIgnoreCase(nombre)) return e;
        }
        throw new IllegalArgumentException("Estado de transporte no válido: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(EstadoTransporte::getNombre)
                .toList();
    }
}
