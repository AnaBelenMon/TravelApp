package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Alojamiento {
    private int idAlojamiento;
    private int idViaje;
    private String nombre;
    private String direccion;
    private double precioTotal;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;

    public Alojamiento(int idAlojamiento, int idViaje, String nombre, String direccion, double precioTotal, LocalDate fechaCheckin, LocalDate fechaCheckout) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");

        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía");

        if (precioTotal < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        if (fechaCheckin == null || fechaCheckout == null)
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");

        if (!fechaCheckout.isAfter(fechaCheckin))
            throw new IllegalArgumentException("La fecha de checkout debe ser posterior al checkin");
        this.idAlojamiento = idAlojamiento;
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precioTotal = precioTotal;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        this.direccion = direccion;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        if (precioTotal < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioTotal = precioTotal;
    }


    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDate fechaCheckin) {
        if (fechaCheckin == null)
            throw new IllegalArgumentException("La fecha de check-in no puede ser nula");
        this.fechaCheckin = fechaCheckin;
    }

    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(LocalDate fechaCheckout) {
        if (fechaCheckout == null)
            throw new IllegalArgumentException("La fecha de check-out no puede ser nula");
        if (!fechaCheckout.isAfter(fechaCheckin))
            throw new IllegalArgumentException("El checkout debe ser posterior al checkin");
        this.fechaCheckout = fechaCheckout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alojamiento)) return false;
        Alojamiento that = (Alojamiento) o;
        return idAlojamiento == that.idAlojamiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlojamiento);
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "idAlojamiento=" + idAlojamiento +
                ", idViaje=" + idViaje +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", precioTotal=" + precioTotal +
                ", fechaCheckin='" + fechaCheckin + '\'' +
                ", fechaCheckout='" + fechaCheckout + '\'' +
                '}';
    }

    public int getNumeroNoches() {
        return (int) ChronoUnit.DAYS.between(fechaCheckin, fechaCheckout);
    }

    public double getPrecioPorNoche() {
        return precioTotal / getNumeroNoches();
    }

    public boolean esGratuito() {
        return precioTotal == 0;
    }

    public boolean tieneDireccion() {
        return direccion != null && !direccion.isBlank();
    }
}