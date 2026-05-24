package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa un alojamiento asociado a un viaje.
 * Contiene información sobre el hotel o alojamiento contratado,
 * incluyendo fechas de estancia, precio total, dirección y valoración.
 * Implementa la interfaz Valorable para permitir la valoración
 * por parte del usuario.
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Alojamiento implements Valorable {
    private int idAlojamiento;
    private int idViaje;
    private String nombre;
    private String direccion;
    private double precioTotal;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;
    private int valoracion;

    /**
     * Constructor para construir un alojamiento.
     */
    public Alojamiento(int idViaje,String nombre, String direccion, double precioTotal, LocalDate fechaCheckin, LocalDate fechaCheckout, int valoracion) {
        setIdViaje(idViaje);
        setNombre(nombre);
        setDireccion(direccion);
        setPrecioTotal(precioTotal);
        setFechaCheckin(fechaCheckin);
        setFechaCheckout(fechaCheckout);
        setValoracion(valoracion);
    }

    /**
     * Constructor completo.
     */
    public Alojamiento(int idAlojamiento, int idViaje, String nombre, String direccion, double precioTotal, LocalDate fechaCheckin, LocalDate fechaCheckout, int valoracion) {
        setIdAlojamiento(idAlojamiento);
        setIdViaje(idViaje);
        setNombre(nombre);
        setDireccion(direccion);
        setPrecioTotal(precioTotal);
        setFechaCheckin(fechaCheckin);
        setFechaCheckout(fechaCheckout);
        setValoracion(valoracion);
    }

    /**
     * Obtiene el identificador del alojamiento.
     * @return id del alojamiento
     */
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    /**
     * Establece el identificador del alojamiento.
     * @param idAlojamiento identificador único
     */
    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    /**
     * Obtiene el identificador del viaje asociado.
     * @return id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Establece el identificador del viaje asociado.
     * @param idViaje identificador del viaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * Obtiene el nombre del alojamiento.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alojamiento.
     * @param nombre nombre del alojamiento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del alojamiento.
     * @return dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del alojamiento.
     * @param direccion dirección del alojamiento
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el precio total de la estancia.
     * @return precio total
     */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total de la estancia.
     * @param precioTotal precio total en euros
     */
    public void setPrecioTotal(double precioTotal) {
        if (precioTotal < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioTotal = precioTotal;
    }


    /**
     * Obtiene la fecha de entrada.
     * @return check-in
     */
    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    /**
     * Establece la fecha de entrada.
     * @param fechaCheckin fecha de check-in
     */
    public void setFechaCheckin(LocalDate fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    /**
     * Obtiene la fecha de salida.
     * @return check-out
     */
    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    /**
     * Establece la fecha de salida.
     * @param fechaCheckout fecha de check-out
     */
    public void setFechaCheckout(LocalDate fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    /**
     * Obtiene la valoración del alojamiento.
     * @return valoración (0-5)
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración del alojamiento.
     * @param valoracion puntuación del usuario
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Calcula el número de noches de la estancia.
     * @return número de días entre check-in y check-out
     */
    public int getNumeroNoches() {
        return (int) ChronoUnit.DAYS.between(fechaCheckin, fechaCheckout);
    }

    /**
     * Calcula el precio por noche.
     * @return precio medio por noche
     */
    public double getPrecioPorNoche() {
        return precioTotal / getNumeroNoches();
    }

    /**
     * Indica si el alojamiento es gratuito.
     * @return true si el precio es 0
     */
    public boolean esGratuito() {
        return precioTotal == 0;
    }

    /**
     * Indica si el alojamiento tiene dirección válida.
     * @return true si la dirección no es nula ni vacía
     */
    public boolean tieneDireccion() {
        return direccion != null && !direccion.isBlank();
    }

    /**
     * Asigna una valoración al alojamiento.
     * @param puntuacion valor entre 1 y 5
     */
    @Override
    public void valorar(int puntuacion) {
        setValoracion(puntuacion);
    }

    /**
     * Elimina la valoración del alojamiento.
     */
    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }

    /**
     * Representación en texto del alojamiento.
     * @return información completa del alojamiento
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