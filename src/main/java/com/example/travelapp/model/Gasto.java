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

    public Gasto(int idViaje, CategoriaGasto categoriaGasto, LocalDate fecha, double importe, String notas) {
        if (idGasto < 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");

        if (categoriaGasto == null)
            throw new IllegalArgumentException("La categoría no puede ser nula");

        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula");

        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo");

        if (notas == null)
            notas = "";
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
        if (idGasto < 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");
        this.idGasto = idGasto;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public CategoriaGasto getCategoria() {
        return categoriaGasto;
    }

    public void setCategoria(CategoriaGasto categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("La categoría no puede ser nula");
        this.categoriaGasto = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula");
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo");
        this.importe = importe;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        if (notas == null)
            notas = "";
        this.notas = notas;
    }

    public boolean esGratuito() {
        return importe == 0;
    }

    public boolean esDeCategoria(CategoriaGasto c) {
        return categoriaGasto == c;
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
        return "Gasto{" +
                "idGasto=" + idGasto +
                ", idViaje=" + idViaje +
                ", categoria='" + categoriaGasto + '\'' +
                ", fecha='" + fecha + '\'' +
                ", importe=" + importe +
                ", notas='" + notas + '\'' +
                '}';
    }

    public String getImporteFormateado() {
        return String.format("%.2f €", importe);
    }
}