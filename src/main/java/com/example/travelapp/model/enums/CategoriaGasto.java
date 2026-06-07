package com.example.travelapp.model.enums;

/**
 * Enum que representa las categorías de gasto dentro de un viaje.
 * Se utiliza para clasificar los gastos del usuario y facilitar su
 * organización, filtrado y análisis dentro de la aplicación.
 * Cada categoría incluye un nombre legible que se muestra en la interfaz
 * de usuario, permitiendo una experiencia más clara e intuitiva.
 * Este enum forma parte del modelo financiero de TravelApp y se utiliza
 * directamente en la clase {@link com.example.travelapp.model.Gasto}.
 *
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
     * Constructor del enum que asigna un nombre legible a la categoría.
     *
     * @param nombre nombre visible para el usuario
     */
    CategoriaGasto(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible de la categoría de gasto.
     *
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la representación en texto de la categoría,
     * utilizada en interfaces gráficas y listados.
     *
     * @return nombre legible de la categoría
     */
    @Override
    public String toString() {
        return nombre;
    }
}
