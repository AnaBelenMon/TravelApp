package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Gasto {
    private int idGasto;
    private int idViaje;
    private CategoriaGasto categoriaGasto;
    private LocalDate fecha;
    private double importe;
    private String notas;

    public Gasto(CategoriaGasto categoriaGasto, LocalDate fecha, double importe, String notas) {
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
        this.importe = importe;
        this.notas = notas;
    }

    public Gasto(int idGasto, int idViaje, CategoriaGasto categoriaGasto, LocalDate fecha, double importe, String notas) {
        this.idGasto = idGasto;
        this.idViaje = idViaje;
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
        this.importe = importe;
        this.notas = notas;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public CategoriaGasto getCategoriaGasto() {
        return categoriaGasto;
    }

    public void setCategoriaGasto(CategoriaGasto categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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

    @Override
    public String toString() {
        return categoriaGasto + " - " + importe + "€";
    }

    public String getImporteFormateado() {
        return String.format("%.2f €", importe);
    }
}