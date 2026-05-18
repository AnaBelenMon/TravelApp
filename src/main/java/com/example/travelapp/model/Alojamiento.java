package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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
     *
     * @param idAlojamiento
     * @param idViaje
     * @param nombre
     * @param direccion
     * @param precioTotal
     * @param fechaCheckin
     * @param fechaCheckout
     * @param valoracion
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

    /**
     *
     * @return
     */
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    /**
     *
     * @param idAlojamiento
     */
    public void setIdAlojamiento(int idAlojamiento) {
        if (idAlojamiento < 0){
            throw new IllegalArgumentException("La fechas no puede ser negativo");
        }
        this.idAlojamiento = idAlojamiento;
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
        if (idViaje < 0){
            throw new IllegalArgumentException("La fechas no puede ser negativo");
        }
        this.idViaje = idViaje;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        this.direccion = direccion;
    }

    /**
     *
     * @return
     */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     *
     * @param precioTotal
     */
    public void setPrecioTotal(double precioTotal) {
        if (precioTotal < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioTotal = precioTotal;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    /**
     *
     * @param fechaCheckin
     */
    public void setFechaCheckin(LocalDate fechaCheckin) {
        if (fechaCheckin == null){
            throw new IllegalArgumentException("La fecha de checkin no puede ser nula");
        }
        this.fechaCheckin = fechaCheckin;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    /**
     *
     * @param fechaCheckout
     */
    public void setFechaCheckout(LocalDate fechaCheckout) {
        if (fechaCheckout == null)
            throw new IllegalArgumentException("La fecha de check-out no puede ser nula");
        if (!fechaCheckout.isAfter(fechaCheckin))
            throw new IllegalArgumentException("El checkout debe ser posterior al checkin");
        this.fechaCheckout = fechaCheckout;
    }

    /**
     *
     * @param valoracion
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     *
     * @return
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alojamiento that = (Alojamiento) o;
        return idAlojamiento == that.idAlojamiento && idViaje == that.idViaje && Double.compare(precioTotal, that.precioTotal) == 0 && valoracion == that.valoracion && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(fechaCheckin, that.fechaCheckin) && Objects.equals(fechaCheckout, that.fechaCheckout);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idAlojamiento);
    }

    /**
     *
     * @return
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

    /**
     *
     * @return
     */
    public int getNumeroNoches() {
        return (int) ChronoUnit.DAYS.between(fechaCheckin, fechaCheckout);
    }

    /**
     *
     * @return
     */
    public double getPrecioPorNoche() {
        return precioTotal / getNumeroNoches();
    }

    /**
     *
     * @return
     */
    public boolean esGratuito() {
        return precioTotal == 0;
    }

    /**
     *
     * @return
     */
    public boolean tieneDireccion() {
        return direccion != null && !direccion.isBlank();
    }

    /**
     *
     * @param puntuacion valor entre 1 y 5
     */
    @Override
    public void valorar(int puntuacion) {
        setValoracion(puntuacion);
    }

    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }
}