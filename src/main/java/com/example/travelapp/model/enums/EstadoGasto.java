package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa el estado de un gasto dentro de un viaje.
 * Permite gestionar y clasificar los gastos según su situación actual.
 *
 * Cada estado incluye un nombre legible para mostrar en la interfaz.
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum EstadoGasto {
    PENDIENTE("Pendiente"),
    PAGADO("Pagado"),
    CANCELADO("Cancelado"),
    REEMBOLSADO("Reembolsado");

    private final String nombre;

    EstadoGasto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static EstadoGasto fromNombre(String nombre) {
        for (EstadoGasto e : values()) {
            if (e.nombre.equalsIgnoreCase(nombre)) return e;
        }
        throw new IllegalArgumentException("Estado de gasto no válido: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(EstadoGasto::getNombre)
                .toList();
    }
}
