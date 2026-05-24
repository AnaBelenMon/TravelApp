package com.example.travelapp.model;

/**
 * Enum que representa los diferentes tipos de transporte disponibles en la aplicación.
 * Cada transporte tiene un nombre legible y un icono asociado para su representación visual.
 */
public enum TipoTransporte {

    /** Transporte aéreo */
    AVION("Avión", "✈️"),

    /** Transporte ferroviario */
    TREN("Tren", "🚆"),

    /** Transporte público por carretera */
    AUTOBUS("Autobús", "🚌"),

    /** Vehículo privado */
    COCHE("Coche", "🚗"),

    /** Transporte marítimo */
    BARCO("Barco", "🚢");

    /** Nombre legible del transporte */
    private final String nombre;

    /** Icono representativo del transporte */
    private final String icono;

    /**
     * Constructor del enum.
     *
     * @param nombre nombre legible del transporte
     * @param icono icono representativo del transporte
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
     * Obtiene el icono representativo del transporte.
     *
     * @return icono del transporte
     */
    public String getIcono() {
        return icono;
    }

    /**
     * Indica si el transporte es aéreo.
     *
     * @return true si es avión
     */
    public boolean esAereo() {
        return this == AVION;
    }

    /**
     * Indica si el transporte es marítimo.
     *
     * @return true si es barco
     */
    public boolean esMaritimo() {
        return this == BARCO;
    }

    /**
     * Indica si el transporte es terrestre.
     *
     * @return true si es tren, autobús o coche
     */
    public boolean esTerrestre() {
        return this == TREN || this == AUTOBUS || this == COCHE;
    }

    /**
     * Indica si requiere billete para su uso.
     *
     * @return true si requiere billete
     */
    public boolean requiereBillete() {
        return this == AVION || this == TREN;
    }

    /**
     * Indica si es un transporte público.
     *
     * @return true si es tren o autobús
     */
    public boolean esPublico() {
        return this == TREN || this == AUTOBUS;
    }

    /**
     * Indica si es un transporte privado.
     *
     * @return true si es coche
     */
    public boolean esPrivado() {
        return this == COCHE;
    }

    /**
     * Indica si puede utilizarse en trayectos internacionales.
     *
     * @return true si es avión o barco
     */
    public boolean puedeSerInternacional() {
        return this == AVION || this == BARCO;
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