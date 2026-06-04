package com.example.travelapp.model;

import com.example.travelapp.model.enums.TipoViaje;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Viaje {
    private int idViaje;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private String nombre;
    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double presupuesto;
    private String notas;
    private TipoViaje tipo;
    private String imagen;

    public Viaje() {}

    public Viaje(Usuario usuario, String nombre, String destino, LocalDate fechaInicio, LocalDate fechaFin, TipoViaje tipo, double presupuesto, String imagen, String notas) {
        setUsuario(usuario);
        setNombre(nombre);
        setDestino(destino);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setTipo(tipo);
        setPresupuesto(presupuesto);
        setImagen(imagen);
        setNotas(notas);
    }

    public Viaje(int idViaje, Usuario usuario, Alojamiento alojamiento, String nombre, String destino, LocalDate fechaInicio, LocalDate fechaFin, double presupuesto, String notas, TipoViaje tipo, String imagen) {
        setIdViaje(idViaje);
        setUsuario(usuario);
        setAlojamiento(alojamiento);
        setNombre(nombre);
        setDestino(destino);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setPresupuesto(presupuesto);
        setTipo(tipo);
        setImagen(imagen);
        setNotas(notas);
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        if (idViaje < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idViaje = idViaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario == null)
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        this.usuario = usuario;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.isBlank())
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        this.destino = destino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null)
            throw new IllegalArgumentException("La fecha de fin no puede ser nula.");
        if (fechaInicio != null && fechaFin.isBefore(fechaInicio))
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        this.fechaFin = fechaFin;
    }

    public TipoViaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoViaje tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        if (presupuesto < 0)
            throw new IllegalArgumentException("El presupuesto no puede ser negativo.");
        this.presupuesto = presupuesto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
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

    public boolean esPasado() {
        return LocalDate.now().isAfter(fechaFin);
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
        return nombre + " (" + destino + ")";
    }
}
