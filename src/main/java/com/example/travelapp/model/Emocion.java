package com.example.travelapp.model;

public enum Emocion {
    FELICIDAD("😊","Felicidad"),
    TRISTEZA("😢","Tristeza"),
    SORPRESA("😲","Sorpresa"),
    MIEDO("😨","Miedo"),
    NOSTALGIA("🥹","Nostalgia"),
    EUFORIA("🤩","Euforia"),
    TRANQUILIDAD("😌","Transquilidad"),;

    private final String icono;
    private final String nombre;

    /**
     *
     * @param icono
     * @param nombre
     */
    Emocion(String icono, String nombre) {
        this.icono = icono;
        this.nombre = nombre;
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
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return icono + " - " + nombre;
    }
}
