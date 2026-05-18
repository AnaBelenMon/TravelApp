package com.example.travelapp.model;

public enum TipoTransporte {

    AVION("Avión", "✈️"),
    TREN("Tren", "🚆"),
    AUTOBUS("Autobús", "🚌"),
    COCHE("Coche", "🚗"),
    BARCO("Barco", "🚢");

    private final String nombre;
    private final String icono;

    /**
     *
     * @param nombre
     * @param icono
     */
    TipoTransporte(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public String getIcono() {
        return icono;
    }

    /**
     *
     * @return
     */
    public boolean esAereo() {
        return this == AVION;
    }

    /**
     *
     * @return
     */
    public boolean esMaritimo() {
        return this == BARCO;
    }

    /**
     *
     * @return
     */
    public boolean esTerrestre() {
        return this == TREN || this == AUTOBUS || this == COCHE;
    }

    /**
     *
     * @return
     */
    public boolean requiereBillete() {
        return this == AVION || this == TREN;
    }

    /**
     *
     * @return
     */
    public boolean esPublico() {
        return this == TREN || this == AUTOBUS;
    }

    /**
     *
     * @return
     */
    public boolean esPrivado() {
        return this == COCHE;
    }

    /**
     *
     * @return
     */
    public boolean puedeSerInternacional() {
        return this == AVION || this == BARCO;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return icono + " " + nombre;
    }

}
