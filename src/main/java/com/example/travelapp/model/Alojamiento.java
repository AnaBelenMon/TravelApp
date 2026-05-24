package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Representa un alojamiento asociado a un viaje dentro de la aplicación.
 * Contiene información básica como nombre, dirección, precio total,
 * fechas de check-in y check-out y una valoración opcional.
 *
 * Esta clase actúa como un POJO sin validaciones internas. Las comprobaciones
 * de datos se realizan en los controladores siguiendo el patrón MVC.
 */
public class Alojamiento implements Valorable{
    private int idAlojamiento;
    private int idViaje;
    private String nombre;
    private String direccion;
    private double precioTotal;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;
    private int valoracion;

    /**
     * Constructor para crear un alojamiento sin IDs asignados.
     * Se utiliza al crear un nuevo alojamiento desde la interfaz.
     * @param nombre nombre del alojamiento
     * @param direccion dirección del alojamiento
     * @param precioTotal precio total de la estancia
     * @param fechaCheckin fecha de entrada
     * @param fechaCheckout fecha de salida
     * @param valoracion valoración inicial
     */
    public Alojamiento(String nombre, String direccion, double precioTotal, LocalDate fechaCheckin, LocalDate fechaCheckout, int valoracion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.precioTotal = precioTotal;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
        this.valoracion = valoracion;
    }

    /**
     * Constructor completo con IDs. Se utiliza al cargar datos desde la base de datos.
     *
     * @param idAlojamiento identificador del alojamiento
     * @param idViaje identificador del viaje asociado
     * @param nombre nombre del alojamiento
     * @param direccion dirección del alojamiento
     * @param precioTotal precio total de la estancia
     * @param fechaCheckin fecha de entrada
     * @param fechaCheckout fecha de salida
     * @param valoracion valoración del alojamiento
     */
    public Alojamiento(int idAlojamiento, int idViaje, String nombre, String direccion, double precioTotal, LocalDate fechaCheckin, LocalDate fechaCheckout, int valoracion) {
        this.idAlojamiento = idAlojamiento;
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precioTotal = precioTotal;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
        this.valoracion = valoracion;
    }

    /** @return id del alojamiento */
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    /** @param idAlojamiento nuevo id */
    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    /** @return id del viaje asociado */
    public int getIdViaje() {
        return idViaje;
    }

    /** @param idViaje nuevo id de viaje */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /** @return nombre del alojamiento */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre nuevo nombre */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return dirección del alojamiento */
    public String getDireccion() {
        return direccion;
    }

    /** @param direccion nueva dirección */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /** @return precio total de la estancia */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /** @param precioTotal nuevo precio total */
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /** @return fecha de check-in */
    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    /** @param fechaCheckin nueva fecha de entrada */
    public void setFechaCheckin(LocalDate fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    /** @return fecha de check-out */
    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    /** @param fechaCheckout nueva fecha de salida */
    public void setFechaCheckout(LocalDate fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    /** @return valoración del alojamiento */
    public int getValoracion() {
        return valoracion;
    }

    /** @param valoracion nueva valoración */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Calcula el número total de noches entre check-in y check-out.
     *
     * @return número de noches
     */
    public int getNumeroNoches() {
        return (int) ChronoUnit.DAYS.between(fechaCheckin, fechaCheckout);
    }

    /**
     * Calcula el precio por noche.
     *
     * @return precio dividido entre número de noches
     */
    public double getPrecioPorNoche() {
        return precioTotal / getNumeroNoches();
    }

    /**
     * Indica si el alojamiento es gratuito.
     *
     * @return true si el precio total es 0
     */
    public boolean esGratuito() {
        return precioTotal == 0;
    }

    /**
     * Indica si el alojamiento tiene una dirección válida.
     *
     * @return true si la dirección no está vacía
     */
    public boolean tieneDireccion() {
        return direccion != null && !direccion.isBlank();
    }

    /**
     * Asigna una valoración al alojamiento.
     *
     * @param puntuacion valor entre 1 y 5
     */
    @Override
    public void valorar(int puntuacion) {
        setValoracion(puntuacion);
    }

    /** Elimina la valoración del alojamiento. */
    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }

    /**
     * Representación en texto del alojamiento.
     * @return cadena descriptiva
     */
    @Override
    public String toString() {
        return "Alojamiento{" +
                "idAlojamiento=" + idAlojamiento +
                ", idViaje=" + idViaje +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", precioTotal=" + precioTotal +
                ", fechaCheckin=" + fechaCheckin +
                ", fechaCheckout=" + fechaCheckout +
                ", valoracion=" + valoracion +
                '}';
    }
}