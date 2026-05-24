package com.example.travelapp.model;

/**
 * Clase abstracta que representa un elemento cultural dentro de la aplicación.
 *
 * Sirve como clase base para diferentes tipos de elementos culturales
 * (por ejemplo museos, monumentos, etc.), proporcionando atributos comunes
 * como identificador, nombre y descripción.
 *
 * Las subclases deben implementar el método {@link #getTipo()} para
 * indicar el tipo específico del elemento cultural.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public abstract class ElementoCultural {
    protected int id;
    protected String nombre;
    protected String descripcion;

    /**
     * Constructor sin ID (asignado posteriormente por el sistema).
     */
    public ElementoCultural(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor completo con identificador.
     */
    public ElementoCultural(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador del elemento cultural.
     *
     * @return id del elemento
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del elemento.
     *
     * @param id identificador único
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del elemento cultural.
     *
     * @return nombre del elemento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del elemento cultural.
     *
     * @param nombre nombre del elemento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del elemento cultural.
     *
     * @return descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del elemento cultural.
     *
     * @param descripcion descripción del elemento
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el tipo específico del elemento cultural.
     * Debe ser implementado por las subclases.
     *
     * @return tipo del elemento cultural
     */
    public abstract String getTipo();

    /**
     * Representación en texto del elemento cultural.
     *
     * @return nombre del elemento
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Devuelve un resumen del elemento cultural.
     *
     * @return nombre + tipo del elemento
     */
    public String resumen() {
        return nombre + " - " + getTipo();
    }
}