package com.example.travelapp.model;

import com.example.travelapp.model.enums.TipoAlojamiento;
import java.util.Objects;

public class Alojamiento {
    private int idAlojamiento;
    private String nombre;
    private TipoAlojamiento tipo;
    private String direccion;
    private String ciudad;
    private String pais;

    public Alojamiento(String nombre, TipoAlojamiento tipo, String direccion, String ciudad, String pais) {
        setNombre(nombre);
        setTipo(tipo);
        setDireccion(direccion);
        setCiudad(ciudad);
        setPais(pais);
    }

    public Alojamiento(int idAlojamiento, String nombre, TipoAlojamiento tipo, String direccion, String ciudad, String pais) {
        setIdAlojamiento(idAlojamiento);
        setNombre(nombre);
        setTipo(tipo);
        setDireccion(direccion);
        setCiudad(ciudad);
        setPais(pais);
    }

    public Alojamiento() {}

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        if (idAlojamiento < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idAlojamiento = idAlojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public TipoAlojamiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlojamiento tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        if (ciudad == null || ciudad.isBlank())
            throw new IllegalArgumentException("La ciudad no puede estar vacía.");
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        if (pais == null || pais.isBlank())
            throw new IllegalArgumentException("El país no puede estar vacío.");
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alojamiento)) return false;
        Alojamiento that = (Alojamiento) o;
        return idAlojamiento == that.idAlojamiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlojamiento);
    }

    @Override
    public String toString() {
        return nombre + " - " + ciudad + ", " + pais;
    }
}