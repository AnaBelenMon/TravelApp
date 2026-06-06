package com.example.travelapp.model;

import com.example.travelapp.model.enums.EstadoTransporte;
import com.example.travelapp.model.enums.TipoTransporte;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Transporte {
    private int idTransporte;
    private TipoTransporte tipo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private double precio;
    private EstadoTransporte estado;

    public Transporte(TipoTransporte tipo, String origen, String destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, double precio, EstadoTransporte estado) {
        setTipo(tipo);
        setOrigen(origen);
        setDestino(destino);
        setFechaSalida(fechaSalida);
        setFechaLlegada(fechaLlegada);
        setPrecio(precio);
        setEstado(estado);
    }

    public Transporte(int idTransporte, TipoTransporte tipo, String origen, String destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, double precio, EstadoTransporte estado) {
        setIdTransporte(idTransporte);
        setTipo(tipo);
        setOrigen(origen);
        setDestino(destino);
        setFechaSalida(fechaSalida);
        setFechaLlegada(fechaLlegada);
        setPrecio(precio);
        setEstado(estado);
    }

    public Transporte() {}

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        if (idTransporte < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idTransporte = idTransporte;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporte tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        if (origen == null || origen.isBlank())
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.isBlank())
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        this.destino = destino;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        if (fechaSalida == null)
            throw new IllegalArgumentException("La fecha de salida no puede ser nula.");
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        if (fechaLlegada == null)
            throw new IllegalArgumentException("La fecha de llegada no puede ser nula.");
        if (fechaSalida != null && fechaLlegada.isBefore(fechaSalida))
            throw new IllegalArgumentException("La llegada no puede ser anterior a la salida.");
        this.fechaLlegada = fechaLlegada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public EstadoTransporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransporte estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    public long getDuracionHoras() {
        return ChronoUnit.HOURS.between(fechaSalida, fechaLlegada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transporte)) return false;
        Transporte t = (Transporte) o;
        return idTransporte == t.idTransporte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransporte);
    }

    @Override
    public String toString() {
        return tipo.getNombre() + " " + origen + " → " + destino + " (" + precio + "€)";
    }
}