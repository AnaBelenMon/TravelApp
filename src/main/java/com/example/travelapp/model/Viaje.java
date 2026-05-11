package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

public class Viaje {
    private int idViaje;
    private int idUsuario;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private TipoViaje tipoViaje;
    private String imagenPortada;
    private String notasGenerales;
    private double presupuestoEstimado;
    private String destinoPais;
    private String destinoCiudad;

    public Viaje(int idViaje, int idUsuario, String nombre, LocalDate fechaInicio, LocalDate fechaFin, TipoViaje tipoViaje, String imagenPortada, String notasGenerales, double presupuestoEstimado, String destinoPais, String destinoCiudad) {
        this.idViaje = idViaje;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoViaje = tipoViaje;
        this.imagenPortada = imagenPortada;
        this.notasGenerales = notasGenerales;
        this.presupuestoEstimado = presupuestoEstimado;
        this.destinoPais = destinoPais;
        this.destinoCiudad = destinoCiudad;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public String getNotasGenerales() {
        return notasGenerales;
    }

    public void setNotasGenerales(String notasGenerales) {
        this.notasGenerales = notasGenerales;
    }

    public double getPresupuestoEstimado() {
        return presupuestoEstimado;
    }

    public void setPresupuestoEstimado(double presupuestoEstimado) {
        this.presupuestoEstimado = presupuestoEstimado;
    }

    public String getDestinoPais() {
        return destinoPais;
    }

    public void setDestinoPais(String destinoPais) {
        this.destinoPais = destinoPais;
    }

    public String getDestinoCiudad() {
        return destinoCiudad;
    }

    public void setDestinoCiudad(String destinoCiudad) {
        this.destinoCiudad = destinoCiudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje)) return false;
        Viaje v = (Viaje) o;
        return idViaje == v.idViaje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "idViaje=" + idViaje +
                ", idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", tipoViaje='" + tipoViaje + '\'' +
                ", imagenPortada='" + imagenPortada + '\'' +
                ", notasGenerales='" + notasGenerales + '\'' +
                ", presupuestoEstimado=" + presupuestoEstimado +
                ", destinoPais='" + destinoPais + '\'' +
                ", destinoCiudad='" + destinoCiudad + '\'' +
                '}';
    }

    public long getDuracionDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public boolean estaEnCurso() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }

    public boolean esFuturo() {
        return LocalDate.now().isBefore(fechaInicio);
    }
}