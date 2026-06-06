package com.example.travelapp.model;

import com.example.travelapp.model.enums.CategoriaGasto;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.MetodoPago;

import java.time.LocalDate;
import java.util.Objects;

public class Gasto {
    private int idGasto;
    private Viaje viaje;
    private String concepto;
    private CategoriaGasto categoria;
    private double importe;
    private LocalDate fecha;
    private String lugar;
    private MetodoPago metodoPago;
    private EstadoGasto estado;
    private String notas;

    public Gasto(Viaje viaje, String concepto, CategoriaGasto categoria, double importe, LocalDate fecha, String lugar, MetodoPago metodoPago, EstadoGasto estado, String notas) {
        setViaje(viaje);
        setConcepto(concepto);
        setCategoria(categoria);
        setImporte(importe);
        setFecha(fecha);
        setLugar(lugar);
        setMetodoPago(metodoPago);
        setEstado(estado);
        setNotas(notas);
    }

    public Gasto(int idGasto, Viaje viaje, String concepto, CategoriaGasto categoria, double importe,
                 LocalDate fecha, String lugar, MetodoPago metodoPago, EstadoGasto estado, String notas) {

        setIdGasto(idGasto);
        setViaje(viaje);
        setConcepto(concepto);
        setCategoria(categoria);
        setImporte(importe);
        setFecha(fecha);
        setLugar(lugar);
        setMetodoPago(metodoPago);
        setEstado(estado);
        setNotas(notas);
    }

    public Gasto() {}

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        if (idGasto < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idGasto = idGasto;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        if (viaje == null)
            throw new IllegalArgumentException("El viaje no puede ser nulo.");
        this.viaje = viaje;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        if (concepto == null || concepto.isBlank())
            throw new IllegalArgumentException("El concepto no puede estar vacío.");
        this.concepto = concepto;
    }

    public CategoriaGasto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaGasto categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        this.categoria = categoria;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        if (metodoPago == null)
            throw new IllegalArgumentException("El método de pago no puede ser nulo.");
        this.metodoPago = metodoPago;
    }

    public EstadoGasto getEstado() {
        return estado;
    }

    public void setEstado(EstadoGasto estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public boolean esGratuito() {
        return importe == 0;
    }

    @Override
    public String toString() {
        return categoria + " - " + importe + " € ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto)) return false;
        Gasto gasto = (Gasto) o;
        return idGasto == gasto.idGasto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGasto);
    }
}
