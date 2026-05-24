package com.example.travelapp.model;

import java.time.LocalDate;

/**
 * Representa una actividad dentro de un viaje.
 * Una actividad contiene información como su nombre, categoría, fecha,
 * precio, duración, notas del usuario, valoración, estado de reserva y lugar.
 * Implementa la interfaz {@link Valorable} para permitir asignar y gestionar
 * valoraciones por parte del usuario.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Actividad implements Valorable {
    private int idActividad;
    private int idViaje;
    private String nombre;
    private CategoriaActividad categoria;
    private LocalDate fecha;
    private double precio;
    private String notas;
    private int valoracion;
    private int duracionMinutos;
    private boolean reservada;
    private String lugar;

    /**
     * Constructor vacío.
     */
    public Actividad() {}

    /**
     * Constructor para crear una actividad sin ID.
     */
    public Actividad(int idViaje, String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int duracionMinutos, boolean reservada, String lugar) {
        setIdViaje(idViaje);
        setNombre(nombre);
        setCategoria(categoria);
        setFecha(fecha);
        setPrecio(precio);
        setNotas(notas);
        setDuracionMinutos(duracionMinutos);
        setReservada(reservada);
        setLugar(lugar);
        this.valoracion = 0;
    }

    /**
     * Constructor completo.
     */
    public Actividad(int idActividad, int idViaje, String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int valoracion, int duracionMinutos, boolean reservada, String lugar) {
        setIdActividad(idActividad);
        setIdViaje(idViaje);
        setNombre(nombre);
        setCategoria(categoria);
        setFecha(fecha);
        setPrecio(precio);
        setNotas(notas);
        setValoracion(valoracion);
        setDuracionMinutos(duracionMinutos);
        setReservada(reservada);
        setLugar(lugar);
    }

    /**
     * Obtiene el identificador único de la actividad.
     * @return id de la actividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * Establece el identificador de la actividad
     * @param idActividad identificador de la actividad
     */
    public void setIdActividad(int idActividad) {
        if (idActividad < 0){
            throw new IllegalArgumentException("El ID de actividad no puede ser negativo");
        }
        this.idActividad = idActividad;
    }

    /**
     * Obtiene el identificador del viaje al que pertenece la actividad.
     * @return id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Establece el identificador del viaje.
     * @param idViaje identificador del viaje
     */
    public void setIdViaje(int idViaje) {
        if (idViaje <= 0){
            throw new IllegalArgumentException("El ID de viaje no puede ser negativo");
        }
        this.idViaje = idViaje;
    }

    /**
     * Obtiene el nombre de la actividad.
     * @return nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     * @param nombre nombre de la actividad
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de la actividad no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoría de la actividad.
     * @return categoría
     */
    public CategoriaActividad getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría de la actividad.
     * @param categoria categoría de la actividad
     */
    public void setCategoria(CategoriaActividad categoria) {
        if (categoria == null){
            throw new IllegalArgumentException("El categoria de la actividad no puede ser nula");
        }
        this.categoria = categoria;
    }

    /**
     * Obtiene la fecha de la actividad.
     * @return fecha de la actividad
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la actividad.
     * @param fecha fecha de la actividad
     */
    public void setFecha(LocalDate fecha) {
        if (fecha == null){
            throw new IllegalArgumentException("La fecha de la actividad no puede ser nula");
        }
        this.fecha = fecha;
    }

    /**
     * Obtiene el precio de la actividad.
     * @return precio en euros
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la actividad.
     * @param precio precio en euros
     */
    public void setPrecio(double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }


    /**
     * Obtiene las notas del usuario sobre la actividad.
     * @return notas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas del usuario.
     * @param notas notas sobre la actividad
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene la valoración de la actividad.
     * @return valoración (0-5)
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración de la actividad.
     * @param valoracion valor entre 1 y 5
     */
    public void setValoracion(int valoracion) {
        if (valoracion < 0 || valoracion > 5){
            throw new IllegalArgumentException("La valoración debe estar entre 0 y 5");
        }
        this.valoracion = valoracion;
    }

    /**
     * Obtiene la duración de la actividad en minutos.
     * @return duración en minutos
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Establece la duración de la actividad.
     * @param duracionMinutos duración en minutos
     */
    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("La duración no puede ser negativa");
        }
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Indica si la actividad está reservada.
     * @return true si está reservada
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     * Establece si la actividad está reservada.
     * @param reservada estado de reserva
     */
    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * Obtiene el lugar de la actividad.
     * @return lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Establece el lugar de la actividad.
     * @param lugar ubicación de la actividad
     */
    public void setLugar(String lugar) {
        if (lugar == null || lugar.isBlank()){
            throw new IllegalArgumentException("El lugar no puede estar vacío");
        }
        this.lugar = lugar;
    }

    /**
     * Representación en texto la actividad.
     * @return nombre y fecha de la actividad
     */
    @Override
    public String toString() {
        return nombre + " (" + fecha + ")";
    }

    /**
     * Asigna una valoración a la actividad.
     * @param puntuacion valor entre 1 y 5
     * @throws IllegalArgumentException si no está en rango válido
     */
    @Override
    public void valorar(int puntuacion) {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5");
        }
        this.valoracion = puntuacion;
    }

    /**
     * Limpia la valoración de la actividad.
     */
    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }
}