package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa los métodos de pago disponibles para registrar gastos
 * dentro de un viaje. Cada método incluye un nombre legible para mostrar
 * en la interfaz de usuario.
 *
 * Permite clasificar los gastos según cómo fueron pagados y facilita
 * el filtrado, análisis y organización dentro de la aplicación.
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum MetodoPago {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    BIZUM("Bizum"),
    TRANSFERENCIA("Transferencia");

    private final String nombre;

    MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static MetodoPago fromNombre(String nombre) {
        for (MetodoPago m : values()) {
            if (m.nombre.equalsIgnoreCase(nombre)) return m;
        }
        throw new IllegalArgumentException("Método de pago no válido: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(MetodoPago::getNombre)
                .toList();
    }
}
