package com.example.travelapp.model.enums;

/**
 * Enum que representa los diferentes tipos de transporte disponibles en la aplicación.
 * Cada tipo incluye un nombre legible y un icono asociado para su representación visual
 * dentro de la interfaz de usuario.
 * Este enum se utiliza directamente en la clase
 * {@link com.example.travelapp.model.Transporte} y permite clasificar los transportes
 * según su naturaleza (aéreo, terrestre, marítimo, público, privado…).
 * Incluye métodos utilitarios para conversión desde texto y obtención de nombres.
 * También incorpora métodos semánticos que facilitan la lógica de negocio.
 * Ejemplos de uso:
 * - Mostrar iconos en listas de transporte.
 * - Filtrar transportes por tipo (público, privado, internacional…).
 * - Validar reglas según el tipo (si requiere billete, si es aéreo, etc.).
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoTransporte {

    /** Transporte aéreo mediante avión. */
    AVION("Avión", "✈️"),

    /** Transporte ferroviario mediante tren. */
    TREN("Tren", "🚆"),

    /** Transporte público por carretera mediante autobús. */
    AUTOBUS("Autobús", "🚌"),

    /** Transporte privado mediante coche. */
    COCHE("Coche", "🚗"),

    /**
     * Transporte marítimo mediante barco.
     * */
    BARCO("Barco", "🚢");

    private final String nombre;
    private final String icono;

    /**
     * Constructor del enum que asigna un nombre legible y un icono representativo.
     *
     * @param nombre nombre visible para el usuario
     * @param icono  icono asociado al tipo de transporte
     */
    TipoTransporte(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    /**
     * Obtiene el nombre legible del transporte.
     *
     * @return nombre del transporte
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Devuelve una representación en texto del transporte,
     * combinando icono y nombre.
     *
     * @return representación legible del transporte
     */
    @Override
    public String toString() {
        return icono + " " + nombre;
    }
}