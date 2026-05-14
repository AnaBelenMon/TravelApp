package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Transporte {
    private int idTransporte;
    private int idViaje;
    private TipoTransporte tipo;
    private LocalDate fecha;
    private double precio;
    private TipoDocumento tipoDocumento;
    private String rutaDocumento;

    public Transporte(int idViaje, TipoTransporte tipo, LocalDate fecha,
                      double precio, TipoDocumento tipoDocumento, String rutaDocumento) {

        if (idTransporte < 0)
            throw new IllegalArgumentException("El id no puede ser negativo");

        if (tipo == null)
            throw new IllegalArgumentException("El tipo de transporte no puede ser nulo");

        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula");

        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        if (tipoDocumento == null)
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo");

        if (rutaDocumento == null || rutaDocumento.isBlank())
            rutaDocumento = "";

        this.idTransporte = idTransporte;
        this.idViaje = idViaje;
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDocumento = tipoDocumento;
        this.rutaDocumento = rutaDocumento;
    }

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
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
        this.precio = precio;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public boolean esCaro() {
        return precio > 100;
    }

    public boolean tieneDocumento() {
        return rutaDocumento != null && !rutaDocumento.isBlank();
    }

    public boolean esAereo() {
        return tipo.esAereo();
    }

    public boolean esTerrestre() {
        return tipo.esTerrestre();
    }

    public boolean esMaritimo() {
        return tipo.esMaritimo();
    }

    public boolean esGratis() {
        return precio == 0;
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
        return "Transporte{" +
                "idTransporte=" + idTransporte +
                ", idViaje=" + idViaje +
                ", tipo='" + tipo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precio=" + precio +
                ", documento='" + tipoDocumento + '\'' +
                '}';
    }
}