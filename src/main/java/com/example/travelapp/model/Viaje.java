package com.example.travelapp.model;

import com.example.travelapp.model.enums.TipoViaje;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un viaje dentro de la aplicación TravelApp.
 * Un viaje contiene información esencial para su planificación,
 * como fechas, destino, presupuesto, tipo de viaje y usuario propietario.
 * Esta clase es el núcleo del modelo, ya que centraliza la relación
 * con otros elementos como alojamiento, actividades, gastos y transporte.
 */
public class Viaje {

    private int idViaje;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private String nombre;
    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double presupuesto;
    private String notas;
    private TipoViaje tipo;
    private String imagen;

    /**
     * Constructor vacío necesario para serialización, frameworks
     * o creación dinámica de objetos.
     */
    public Viaje() {}

    /**
     * Constructor utilizado para crear un viaje nuevo sin ID asignado,
     * normalmente antes de insertarlo en la base de datos.
     *
     * @param usuario      usuario propietario del viaje
     * @param nombre       nombre del viaje
     * @param destino      destino principal del viaje
     * @param fechaInicio  fecha de inicio del viaje
     * @param fechaFin     fecha de fin del viaje
     * @param tipo         tipo de viaje (negocios, ocio, cultural…)
     * @param presupuesto  presupuesto estimado
     * @param imagen       imagen asociada al viaje (ruta o URL)
     * @param notas        notas adicionales opcionales
     */
    public Viaje(Usuario usuario, String nombre, String destino,
                 LocalDate fechaInicio, LocalDate fechaFin,
                 TipoViaje tipo, double presupuesto,
                 String imagen, String notas) {

        setUsuario(usuario);
        setNombre(nombre);
        setDestino(destino);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setTipo(tipo);
        setPresupuesto(presupuesto);
        setImagen(imagen);
        setNotas(notas);
    }

    /**
     * Constructor completo utilizado cuando el viaje ya existe en la base de datos
     * y dispone de un ID previamente asignado.
     *
     * @param idViaje      identificador único del viaje
     * @param usuario      usuario propietario
     * @param alojamiento  alojamiento asociado (opcional)
     * @param nombre       nombre del viaje
     * @param destino      destino del viaje
     * @param fechaInicio  fecha de inicio
     * @param fechaFin     fecha de fin
     * @param presupuesto  presupuesto estimado
     * @param notas        notas adicionales
     * @param tipo         tipo de viaje
     * @param imagen       imagen asociada
     */
    public Viaje(int idViaje, Usuario usuario, Alojamiento alojamiento,
                 String nombre, String destino,
                 LocalDate fechaInicio, LocalDate fechaFin,
                 double presupuesto, String notas,
                 TipoViaje tipo, String imagen) {

        setIdViaje(idViaje);
        setUsuario(usuario);
        setAlojamiento(alojamiento);
        setNombre(nombre);
        setDestino(destino);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setPresupuesto(presupuesto);
        setTipo(tipo);
        setImagen(imagen);
        setNotas(notas);
    }

    /**
     * Obtiene el identificador único del viaje.
     *
     * @return ID del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Establece el identificador del viaje.
     *
     * @param idViaje ID del viaje
     * @throws IllegalArgumentException si el ID es negativo
     */
    public void setIdViaje(int idViaje) {
        if (idViaje < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idViaje = idViaje;
    }

    /**
     * Obtiene el usuario propietario del viaje.
     *
     * @return usuario del viaje
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario propietario del viaje.
     *
     * @param usuario usuario del viaje
     * @throws IllegalArgumentException si el usuario es nulo
     */
    public void setUsuario(Usuario usuario) {
        if (usuario == null)
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        this.usuario = usuario;
    }

    /**
     * Obtiene el alojamiento asociado al viaje.
     *
     * @return alojamiento del viaje (puede ser null)
     */
    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    /**
     * Establece el alojamiento del viaje.
     * Este campo es opcional.
     *
     * @param alojamiento alojamiento del viaje
     */
    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    /**
     * Obtiene el nombre del viaje.
     *
     * @return nombre del viaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del viaje.
     *
     * @param nombre nombre del viaje
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    /**
     * Obtiene el destino del viaje.
     *
     * @return destino del viaje
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del viaje.
     *
     * @param destino destino del viaje
     * @throws IllegalArgumentException si el destino es nulo o vacío
     */
    public void setDestino(String destino) {
        if (destino == null || destino.isBlank())
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        this.destino = destino;
    }

    /**
     * Obtiene la fecha de inicio del viaje.
     *
     * @return fecha de inicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del viaje.
     *
     * @param fechaInicio fecha de inicio
     * @throws IllegalArgumentException si la fecha es nula
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin del viaje.
     *
     * @return fecha de fin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin del viaje.
     * Válida que no sea anterior a la fecha de inicio.
     *
     * @param fechaFin fecha de fin
     * @throws IllegalArgumentException si la fecha es nula o anterior al inicio
     */
    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null)
            throw new IllegalArgumentException("La fecha de fin no puede ser nula.");
        if (fechaInicio != null && fechaFin.isBefore(fechaInicio))
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene el tipo de viaje.
     *
     * @return tipo de viaje
     */
    public TipoViaje getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de viaje.
     *
     * @param tipo tipo de viaje
     * @throws IllegalArgumentException si el tipo es nulo
     */
    public void setTipo(TipoViaje tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    /**
     * Obtiene el presupuesto estimado del viaje.
     *
     * @return presupuesto del viaje
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Establece el presupuesto estimado del viaje.
     *
     * @param presupuesto presupuesto del viaje
     * @throws IllegalArgumentException si el presupuesto es negativo
     */
    public void setPresupuesto(double presupuesto) {
        if (presupuesto < 0)
            throw new IllegalArgumentException("El presupuesto no puede ser negativo.");
        this.presupuesto = presupuesto;
    }

    /**
     * Obtiene la imagen asociada al viaje.
     *
     * @return ruta o URL de la imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen asociada al viaje.
     *
     * @param imagen ruta o URL de la imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene las notas adicionales del viaje.
     *
     * @return notas del viaje
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece notas adicionales para el viaje.
     * Este campo es opcional.
     *
     * @param notas notas del viaje
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Compara dos viajes por su ID.
     *
     * @param o objeto a comparar
     * @return true si ambos viajes tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje v)) return false;
        return idViaje == v.idViaje;
    }

    /**
     * Genera un hash basado en el ID del viaje.
     *
     * @return hash del viaje
     */
    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
    }

    /**
     * Devuelve una representación legible del viaje,
     * útil para mostrarlo en listas o interfaces gráficas.
     *
     * @return cadena con el nombre y destino del viaje
     */
    @Override
    public String toString() {
        return nombre + " (" + destino + ")";
    }
}
