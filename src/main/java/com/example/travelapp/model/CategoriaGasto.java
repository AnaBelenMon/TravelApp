package com.example.travelapp.model;

public enum CategoriaGasto {
    COMIDA("Comida️"),
    TRANSPORTE("Transporte"),
    ALOJAMIENTO("Alojamiento"),
    OCIO("Ocio"),
    COMPRAS("Compras️"),
    OTROS("Otros");

    private final String nombre;

    /**
     *
     * @param nombre
     */
    CategoriaGasto(String nombre) {
        this.nombre = nombre;
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
    @Override
    public String toString() {
        return "CategoriaGasto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}