package com.example.travelapp.model;

public abstract class ElementoCultural {
    protected int id;
    protected String nombre;
    protected String descripcion;

    public ElementoCultural(int id, String nombre, String descripcion) {
        if (id <= 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");

        if (descripcion == null || descripcion.isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        this.descripcion = descripcion;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "ElementoCultural{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
