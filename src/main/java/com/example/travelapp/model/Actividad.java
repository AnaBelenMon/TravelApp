package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Actividad implements Valorable {
    private int idActividad;
    private int idViaje;
    private String nombre;
    private CategoriaActividad categoria;
    private LocalDate fecha;
    private double precio;
    private String notas;
    private int valoracion;
    private int duracionMinutos;
    private boolean reservada;
    private String lugar;

    public Actividad(int idActividad, int idViaje, String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int valoracion, int duracionMinutos, boolean reservada, String lugar) {
        this.idActividad = idActividad;
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.precio = precio;
        this.notas = notas;
        this.valoracion = valoracion;
        this.duracionMinutos = duracionMinutos;
        this.reservada = reservada;
        this.lugar = lugar;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaActividad getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaActividad categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        if (valoracion < 1 || valoracion > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5");
        }
        this.valoracion = valoracion;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("La duración no puede ser negativa");
        }
        this.duracionMinutos = duracionMinutos;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actividad)) return false;
        Actividad a = (Actividad) o;
        return idActividad == a.idActividad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividad);
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "idActividad=" + idActividad +
                ", idViaje=" + idViaje +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", fecha='" + fecha + '\'' +
                ", precio=" + precio +
                ", notas='" + notas + '\'' +
                ", valoracion=" + valoracion +
                ", duracionMinutos=" + duracionMinutos +
                ", reservada=" + reservada +
                ", lugar='" + lugar + '\'' +
                '}';
    }

    @Override
    public void valorar(int puntuacion) {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5");
        }
        this.valoracion = puntuacion;
    }

    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }

    public boolean esGratuita() {
        return precio == 0;
    }
}