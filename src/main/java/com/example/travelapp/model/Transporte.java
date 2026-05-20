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

    /**
     *
     * @param tipo
     * @param fecha
     * @param precio
     * @param tipoDocumento
     * @param rutaDocumento
     */
    public Transporte(TipoTransporte tipo, LocalDate fecha, double precio, TipoDocumento tipoDocumento, String rutaDocumento) {
        if (idTransporte <= 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de transporte no puede ser nulo");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo");
        }
        if (rutaDocumento == null || rutaDocumento.isBlank()) {
            rutaDocumento = "";
        }
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDocumento = tipoDocumento;
        this.rutaDocumento = rutaDocumento;
    }

    /**
     *
     * @return
     */
    public int getIdTransporte() {
        return idTransporte;
    }

    /**
     *
     * @param idTransporte
     */
    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    /**
     *
     * @return
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     *
     * @param idViaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     *
     * @return
     */
    public TipoTransporte getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     *
     * @param tipoDocumento
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     *
     * @return
     */
    public String getRutaDocumento() {
        return rutaDocumento;
    }

    /**
     *
     * @param rutaDocumento
     */
    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    /**
     *
     * @return
     */
    public boolean esCaro() {
        return precio > 100;
    }

    /**
     *
     * @return
     */
    public boolean tieneDocumento() {
        return rutaDocumento != null && !rutaDocumento.isBlank();
    }

    /**
     *
     * @return
     */
    public boolean esAereo() {
        return tipo.esAereo();
    }

    /**
     *
     * @return
     */
    public boolean esTerrestre() {
        return tipo.esTerrestre();
    }

    /**
     *
     * @return
     */
    public boolean esMaritimo() {
        return tipo.esMaritimo();
    }

    /**
     *
     * @return
     */
    public boolean esGratis() {
        return precio == 0;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transporte)) return false;
        Transporte t = (Transporte) o;
        return idTransporte == t.idTransporte;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTransporte);
    }

    /**
     *
     * @return
     */
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