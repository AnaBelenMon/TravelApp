package com.example.travelapp.model.enums;

/**
 * Enum que representa los métodos de pago disponibles para registrar gastos
 * dentro de un viaje. Cada método incluye un nombre legible para mostrar
 * en la interfaz de usuario.
 * Este enum permite clasificar los gastos según cómo fueron pagados,
 * facilitando su filtrado, análisis y organización dentro de la aplicación.
 * Se utiliza directamente en la clase {@link com.example.travelapp.model.Gasto}.
 * Incluye métodos utilitarios para conversión y obtención de nombres.
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

    /**
     * Constructor del enum que asigna un nombre legible al método de pago.
     *
     * @param nombre nombre visible para el usuario
     */
    MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del método de pago.
     *
     * @return nombre del método
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la representación en texto del método de pago,
     * utilizada en interfaces gráficas y listados.
     *
     * @return nombre legible del método
     */
    @Override
    public String toString() {
        return nombre;
    }
}
