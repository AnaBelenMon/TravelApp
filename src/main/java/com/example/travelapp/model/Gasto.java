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

    /**
     *
     * @param categoriaGasto
     * @param fecha
     * @param importe
     * @param notas
     */
    public Gasto(CategoriaGasto categoriaGasto, LocalDate fecha, double importe, String notas) {
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
        this.importe = importe;
        this.notas = notas;
    }

    /**
     *
     * @return
     */
    public int getIdGasto() {
        return idGasto;
    }

    /**
     *
     * @param idGasto
     */
    public void setIdGasto(int idGasto) {
        if (idGasto < 0)
            throw new IllegalArgumentException("El id debe ser mayor que 0");
        this.idGasto = idGasto;
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
        if (idViaje > 0){
            this.idViaje = idViaje;
        }
    }

    /**
     *
     * @return
     */
    public CategoriaGasto getCategoria() {
        return categoriaGasto;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(CategoriaGasto categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("La categoría no puede ser nula");
        this.categoriaGasto = categoria;
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
        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula");
        this.fecha = fecha;
    }

    /**
     *
     * @return
     */
    public double getImporte() {
        return importe;
    }

    /**
     *
     * @param importe
     */
    public void setImporte(double importe) {
        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo");
        this.importe = importe;
    }

    /**
     *
     * @return
     */
    public String getNotas() {
        return notas;
    }

    /**
     *
     * @param notas
     */
    public void setNotas(String notas) {
        if (notas == null)
            notas = "";
        this.notas = notas;
    }

    /**
     *
     * @return
     */
    public boolean esGratuito() {
        return importe == 0;
    }

    /**
     *
     * @param categoriaGasto
     * @return
     */
    public boolean esDeCategoria(CategoriaGasto categoriaGasto) {
        return categoriaGasto == categoriaGasto;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto)) return false;
        Gasto gasto = (Gasto) o;
        return idGasto == gasto.idGasto;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idGasto);
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String getImporteFormateado() {
        return String.format("%.2f €", importe);
    }
}