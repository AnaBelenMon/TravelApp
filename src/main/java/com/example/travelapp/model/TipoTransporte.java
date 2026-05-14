package com.example.travelapp.model;

public enum TipoTransporte {

    AVION("Avión", "✈️"),
    TREN("Tren", "🚆"),
    AUTOBUS("Autobús", "🚌"),
    COCHE("Coche", "🚗"),
    BARCO("Barco", "🚢");

    private final String nombre;
    private final String icono;

    TipoTransporte(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIcono() {
        return icono;
    }

    public boolean esAereo() {
        return this == AVION;
    }

    public boolean esMaritimo() {
        return this == BARCO;
    }

    public boolean esTerrestre() {
        return this == TREN || this == AUTOBUS || this == COCHE;
    }

    public boolean requiereBillete() {
        return this == AVION || this == TREN;
    }

    public boolean esPublico() {
        return this == TREN || this == AUTOBUS;
    }

    public boolean esPrivado() {
        return this == COCHE;
    }

    public boolean puedeSerInternacional() {
        return this == AVION || this == BARCO;
    }

    @Override
    public String toString() {
        return icono + " " + nombre;
    }

}
