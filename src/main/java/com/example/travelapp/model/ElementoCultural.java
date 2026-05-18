package com.example.travelapp.model;

public abstract class ElementoCultural {
    protected int id;
    protected String nombre;
    protected String descripcion;

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     */
    public ElementoCultural(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");
        this.id = id;
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
     * @param nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public abstract String getTipo();

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ElementoCultural{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    /**
     *
     * @return
     */
    public String resumen() {
        return nombre + " - " + getTipo();
    }

}
