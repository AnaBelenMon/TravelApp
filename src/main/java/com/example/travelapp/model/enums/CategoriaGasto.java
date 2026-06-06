package com.example.travelapp.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum que representa las categorías de gasto dentro de un viaje.
 * Se utiliza para clasificar los gastos del usuario y facilitar
 * su organización, filtrado y análisis dentro de la aplicación.
 * Cada categoría tiene un nombre legible para su visualización
 * en la interfaz de usuario.
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public enum CategoriaGasto {
    COMIDA("Comida"),
    TRANSPORTE("Transporte"),
    ALOJAMIENTO("Alojamiento"),
    OCIO("Ocio"),
    COMPRAS("Compras"),
    OTROS("Otros");

    private final String nombre;

    /**
     * Constructor del enum con el nombre legible de la categoría.
     * @param nombre nombre visible para el usuario
     */
    CategoriaGasto(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible de la categoría de gasto.
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Representación en texto de la categoría.
     * @return nombre legible del gasto
     */
    @Override
    public String toString() {
        return nombre;
    }

    public static CategoriaGasto fromNombre(String nombre) {
        for (CategoriaGasto c : values()) {
            if (c.nombre.equalsIgnoreCase(nombre)) return c;
        }
        throw new IllegalArgumentException("Categoría no válida: " + nombre);
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(CategoriaGasto::getNombre)
                .toList();
    }

}