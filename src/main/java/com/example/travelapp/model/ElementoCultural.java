package com.example.travelapp.model;

public abstract class ElementoCultural {
    protected int id;
    protected String nombre;
    protected String descripcion;

    public ElementoCultural(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public ElementoCultural(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre;
    }

    public String resumen() {
        return nombre + " - " + getTipo();
    }
}