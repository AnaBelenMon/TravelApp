package com.example.travelapp.model.enums;

/**
 * Enum que representa el estado de un gasto dentro de un viaje.
 * Permite gestionar y clasificar los gastos según su situación actual,
 * facilitando su organización, filtrado y análisis dentro de la aplicación.
 * Cada estado incluye un nombre legible que se muestra directamente
 * en la interfaz de usuario para mejorar la claridad visual.
 * Este enum se utiliza en la clase {@link com.example.travelapp.model.Gasto}.
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

    /**
     * Constructor del enum que asigna un nombre legible al estado.
     *
     * @param nombre nombre visible para el usuario
     */
    EstadoGasto(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del estado del gasto.
     *
     * @return nombre del estado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la representación en texto del estado,
     * utilizada en interfaces gráficas y listados.
     *
     * @return nombre legible del estado
     */
    @Override
    public String toString() {
        return nombre;
    }
}
