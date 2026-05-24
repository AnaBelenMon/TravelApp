package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un gasto asociado a un viaje.
 *
 * Un gasto almacena información sobre una transacción realizada durante el viaje,
 * incluyendo su categoría, fecha, importe y notas opcionales.
 *
 * Esta clase permite llevar un control económico del viaje y analizar
 * en qué se ha distribuido el presupuesto.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Gasto {

    private int idGasto;
    private int idViaje;
    private CategoriaGasto categoriaGasto;
    private LocalDate fecha;
    private double importe;
    private String notas;

    /**
     * Constructor básico de gasto.
     */
    public Gasto(CategoriaGasto categoriaGasto, LocalDate fecha, double importe, String notas) {
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
        this.importe = importe;
        this.notas = notas;
    }

    /**
     * Constructor completo con identificadores.
     */
    public Gasto(int idGasto, int idViaje, CategoriaGasto categoriaGasto,
                 LocalDate fecha, double importe, String notas) {

        this.idGasto = idGasto;
        this.idViaje = idViaje;
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
        this.importe = importe;
        this.notas = notas;
    }

    /**
     * Obtiene el identificador del gasto.
     *
     * @return id del gasto
     */
    public int getIdGasto() {
        return idGasto;
    }

    /**
     * Establece el identificador del gasto.
     *
     * @param idGasto identificador único
     */
    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    /**
     * Obtiene el identificador del viaje asociado.
     *
     * @return id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Establece el identificador del viaje.
     *
     * @param idViaje identificador del viaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * Obtiene la categoría del gasto.
     *
     * @return categoría del gasto
     */
    public CategoriaGasto getCategoriaGasto() {
        return categoriaGasto;
    }

    /**
     * Establece la categoría del gasto.
     *
     * @param categoriaGasto categoría
     */
    public void setCategoriaGasto(CategoriaGasto categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }

    /**
     * Obtiene la fecha del gasto.
     *
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del gasto.
     *
     * @param fecha fecha del gasto
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el importe del gasto.
     *
     * @return importe en euros
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece el importe del gasto.
     *
     * @param importe cantidad en euros
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Obtiene las notas del gasto.
     *
     * @return notas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas del gasto.
     *
     * @param notas información adicional
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Indica si el gasto es gratuito.
     *
     * @return true si el importe es 0
     */
    public boolean esGratuito() {
        return importe == 0;
    }

    /**
     * Representación en texto del gasto.
     *
     * @return categoría e importe
     */
    @Override
    public String toString() {
        return categoriaGasto + " - " + importe + "€";
    }

    /**
     * Compara gastos por su identificador único.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto)) return false;
        Gasto gasto = (Gasto) o;
        return idGasto == gasto.idGasto;
    }

    /**
     * Genera hash basado en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idGasto);
    }

    /**
     * Devuelve el importe formateado con dos decimales.
     *
     * @return importe en formato "0.00 €"
     */
    public String getImporteFormateado() {
        return String.format("%.2f €", importe);
    }
}