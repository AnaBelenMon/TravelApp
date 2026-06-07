package com.example.travelapp.model;

import com.example.travelapp.model.enums.CategoriaGasto;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.MetodoPago;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un gasto asociado a un viaje dentro de la aplicación TravelApp.
 * Un gasto recoge información económica y contextual como el concepto,
 * categoría, importe, fecha, método de pago y estado del gasto.
 * Esta clase forma parte del modelo financiero del viaje y permite
 * gestionar y clasificar los gastos realizados durante la planificación
 * o ejecución del mismo.
 */
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

    /**
     * Constructor utilizado para crear un gasto nuevo sin ID asignado,
     * normalmente antes de insertarlo en la base de datos.
     *
     * @param viaje      viaje al que pertenece el gasto
     * @param concepto   descripción breve del gasto
     * @param categoria  categoría del gasto (comida, transporte, ocio…)
     * @param importe    importe económico del gasto
     * @param fecha      fecha en la que se realizó el gasto
     * @param lugar      lugar donde se produjo el gasto
     * @param metodoPago método de pago utilizado
     * @param estado     estado del gasto (pendiente, pagado…)
     * @param notas      notas adicionales opcionales
     */
    public Gasto(Viaje viaje, String concepto, CategoriaGasto categoria, double importe,
                 LocalDate fecha, String lugar, MetodoPago metodoPago, EstadoGasto estado, String notas) {

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

    /**
     * Constructor completo utilizado cuando el gasto ya existe en la base de datos
     * y dispone de un ID previamente asignado.
     *
     * @param idGasto    identificador único del gasto
     * @param viaje      viaje al que pertenece el gasto
     * @param concepto   descripción del gasto
     * @param categoria  categoría del gasto
     * @param importe    importe económico
     * @param fecha      fecha del gasto
     * @param lugar      lugar donde se realizó
     * @param metodoPago método de pago utilizado
     * @param estado     estado del gasto
     * @param notas      notas adicionales
     */
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

    /**
     * Constructor vacío necesario para operaciones de serialización,
     * frameworks o carga dinámica.
     */
    public Gasto() {}

    /**
     * Obtiene el identificador único del gasto.
     *
     * @return ID del gasto
     */
    public int getIdGasto() {
        return idGasto;
    }

    /**
     * Establece el identificador del gasto.
     *
     * @param idGasto ID del gasto
     * @throws IllegalArgumentException si el ID es negativo
     */
    public void setIdGasto(int idGasto) {
        if (idGasto < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idGasto = idGasto;
    }

    /**
     * Obtiene el viaje al que pertenece el gasto.
     *
     * @return viaje asociado
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * Establece el viaje al que pertenece el gasto.
     *
     * @param viaje viaje asociado
     * @throws IllegalArgumentException si el viaje es nulo
     */
    public void setViaje(Viaje viaje) {
        if (viaje == null)
            throw new IllegalArgumentException("El viaje no puede ser nulo.");
        this.viaje = viaje;
    }

    /**
     * Obtiene el concepto del gasto.
     *
     * @return concepto del gasto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Establece el concepto del gasto.
     *
     * @param concepto descripción del gasto
     * @throws IllegalArgumentException si el concepto es nulo o vacío
     */
    public void setConcepto(String concepto) {
        if (concepto == null || concepto.isBlank())
            throw new IllegalArgumentException("El concepto no puede estar vacío.");
        this.concepto = concepto;
    }

    /**
     * Obtiene la categoría del gasto.
     *
     * @return categoría del gasto
     */
    public CategoriaGasto getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del gasto.
     *
     * @param categoria categoría del gasto
     * @throws IllegalArgumentException si la categoría es nula
     */
    public void setCategoria(CategoriaGasto categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        this.categoria = categoria;
    }

    /**
     * Obtiene el importe económico del gasto.
     *
     * @return importe del gasto
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece el importe del gasto.
     *
     * @param importe importe económico
     * @throws IllegalArgumentException si el importe es negativo
     */
    public void setImporte(double importe) {
        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        this.importe = importe;
    }

    /**
     * Obtiene la fecha en la que se realizó el gasto.
     *
     * @return fecha del gasto
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del gasto.
     *
     * @param fecha fecha del gasto
     * @throws IllegalArgumentException si la fecha es nula
     */
    public void setFecha(LocalDate fecha) {
        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        this.fecha = fecha;
    }

    /**
     * Obtiene el lugar donde se realizó el gasto.
     *
     * @return lugar del gasto
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Establece el lugar donde se realizó el gasto.
     * Este campo es opcional.
     *
     * @param lugar lugar del gasto
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene el método de pago utilizado.
     *
     * @return método de pago
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago utilizado.
     *
     * @param metodoPago método de pago
     * @throws IllegalArgumentException si el método de pago es nulo
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        if (metodoPago == null)
            throw new IllegalArgumentException("El método de pago no puede ser nulo.");
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el estado del gasto.
     *
     * @return estado del gasto
     */
    public EstadoGasto getEstado() {
        return estado;
    }

    /**
     * Establece el estado del gasto.
     *
     * @param estado estado del gasto
     * @throws IllegalArgumentException si el estado es nulo
     */
    public void setEstado(EstadoGasto estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    /**
     * Obtiene las notas adicionales del gasto.
     *
     * @return notas del gasto
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece notas adicionales para el gasto.
     * Este campo es opcional.
     *
     * @param notas notas del gasto
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Devuelve una representación legible del gasto,
     * útil para mostrarlo en listas o interfaces gráficas.
     *
     * @return cadena con la categoría y el importe
     */
    @Override
    public String toString() {
        return categoria + " - " + importe + " € ";
    }

    /**
     * Compara dos gastos por su ID.
     *
     * @param o objeto a comparar
     * @return true si ambos gastos tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto gasto)) return false;
        return idGasto == gasto.idGasto;
    }

    /**
     * Genera un hash basado en el ID del gasto.
     *
     * @return hash del gasto
     */
    @Override
    public int hashCode() {
        return Objects.hash(idGasto);
    }
}
