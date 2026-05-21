package com.example.travelapp.model;

import java.time.LocalDate;

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

    public Actividad() {}

    /**
     * Constructor de la clase Actividad compuesto por:
     * @param nombre representa el nombre de la actividad.
     * @param categoria representa la categoria de la actividad.
     * @param fecha representa la fecha en la que se realiza la actividad.
     * @param precio representa el precio que cuesta la actividad.
     * @param notas representa las notas o descripciones que se toman de la actividad.
     * @param valoracion representa la valoración que el usuario le pone a la actividad.
     * @param duracionMinutos representa la duración en Minutos de la actividad.
     * @param reservada representa si una actividad esta reservada o no.
     * @param lugar representa el lugar en donde se realiza la actividad.
     */
    public Actividad(String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int valoracion, int duracionMinutos, boolean reservada, String lugar) {
        if (idActividad <= 0){
            throw new IllegalArgumentException("idActividad inválida");
        }
        if (idViaje <= 0){
            throw new IllegalArgumentException("idViaje inválido");
        }
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre vacío");
        }
        if (categoria == null){
            throw new IllegalArgumentException("Categoría nula");
        }
        if (fecha == null){
            throw new IllegalArgumentException("Fecha nula");
        }
        if (precio < 0){
            throw new IllegalArgumentException("Precio negativo");
        }
        if (duracionMinutos < 0){
            throw new IllegalArgumentException("Duración negativa");
        }
        if (lugar == null || lugar.isBlank()){
            throw new IllegalArgumentException("Lugar vacío");
        }
        if (valoracion < 1 || valoracion > 5) {
            throw new IllegalArgumentException("Valoración inválida");
        }
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.precio = precio;
        this.notas = notas;
        this.valoracion = valoracion;
        this.duracionMinutos = duracionMinutos;
        this.reservada = reservada;
        this.lugar = lugar;
    }

    /**
     * Método para obtener la id de la actividad
     * @return la id de la actividad obtenida.
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     *
     * @param idActividad la id de la actividad que introduce el usuario
     */
    public void setIdActividad(int idActividad) {
        if (idActividad <= 0) {
            throw new IllegalArgumentException("idActividad inválida");
        }
        this.idActividad = idActividad;
    }

    /**
     * Método que obtiene la id del viaje
     * @return la id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     *
     * @param idViaje la id del viaje del usuario
     */
    public void setIdViaje(int idViaje) {
        if (idViaje <= 0) {
            throw new IllegalArgumentException("idViaje inválido");
        }
        this.idViaje = idViaje;
    }

    /**
     * Método para obtener el nombre de la actividad
     * @return devuelve el nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que obtiene el nombre a traves del usuario.
     * @param nombre el nombre de la actividad obtenido por el usuario
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Método que devuelve la categoria de la actividad
     * @return devuelve la categoria de la actividad
     */
    public CategoriaActividad getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria devuelve la categoria de la actividad
     */
    public void setCategoria(CategoriaActividad categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoría nula");
        }
        this.categoria = categoria;
    }

    /**
     *
     * @return devuelve la fecha de la actividad
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha devuelve la fecha de la actividad
     */
    public void setFecha(LocalDate fecha) {
        if (fecha == null){
            throw new IllegalArgumentException("Fecha nula");
        }
        this.fecha = fecha;
    }

    /**
     *
     * @return devuelve el precio de la actividad
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio devuelve el precio de la actividad.
     */
    public void setPrecio(double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    /**
     *
     * @return devuelve las anotaciones de la actividad del usuario.
     */
    public String getNotas() {
        return notas;
    }

    /**
     *
     * @param notas devuelve las anotaciones de la actividad del usuario.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     *
     * @return devuelve la valoración de la actividad.
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     *
     * @param valoracion devuelve la valoración de la actividad.
     */
    private void setValoracion(int valoracion) {
        if (valoracion < 1 || valoracion > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5");
        }
        this.valoracion = valoracion;
    }

    /**
     *
     * @return devuelve la duración en minutos de la actividad.
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     *
     * @param duracionMinutos devuelve la duración en minutos de la actividad.
     */
    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("La duración no puede ser negativa");
        }
        this.duracionMinutos = duracionMinutos;
    }

    /**
     *
     * @return devuelve si una actividad está reservada o no.
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     *
     * @param reservada devuelve si la actividad esta reservada o no.
     */
    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    /**
     *
     * @return devuelve el lugar de la actividad.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     *
     * @param lugar es el lugar que ha introducido el usuario para la actividad.
     */
    public void setLugar(String lugar) {
        if (lugar == null || lugar.isBlank()) {
            throw new IllegalArgumentException("Lugar no puede ser nulo");
        }
        this.lugar = lugar;
    }

    /**
     * Método toString que imprime la información de la actividad.
     * @return devuelve la información de la actividad.
     */
    @Override
    public String toString() {
        return nombre + ":" + fecha;
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

    /**
     * Método para comprobar si una actividad es gratuita
     * @return devuelve la comparación de que una actividad es gratuita
     * cuando el precio es igual a 0.
     */
    public boolean esGratuita() {
        return precio == 0;
    }
}