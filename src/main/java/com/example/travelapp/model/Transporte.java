package com.example.travelapp.model;

import com.example.travelapp.model.enums.EstadoTransporte;
import com.example.travelapp.model.enums.TipoTransporte;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa un medio de transporte asociado a un viaje dentro de la aplicación TravelApp.
 * Un transporte define un desplazamiento concreto entre un origen y un destino, incluyendo
 * fechas, precio y estado de la reserva.
 * Esta clase forma parte del modelo de planificación del viaje y permite gestionar
 * vuelos, trenes, autobuses, barcos o cualquier otro tipo de transporte.
 */
public class Transporte {

    private int idTransporte;
    private TipoTransporte tipo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private double precio;
    private EstadoTransporte estado;

    /**
     * Constructor utilizado para crear un transporte nuevo sin ID asignado,
     * normalmente antes de insertarlo en la base de datos.
     *
     * @param tipo         tipo de transporte (avión, tren, autobús…)
     * @param origen       ciudad o punto de salida
     * @param destino      ciudad o punto de llegada
     * @param fechaSalida  fecha y hora de salida
     * @param fechaLlegada fecha y hora de llegada
     * @param precio       precio del transporte
     * @param estado       estado de la reserva (pendiente, confirmado, cancelado)
     */
    public Transporte(TipoTransporte tipo, String origen, String destino,
                      LocalDateTime fechaSalida, LocalDateTime fechaLlegada,
                      double precio, EstadoTransporte estado) {

        setTipo(tipo);
        setOrigen(origen);
        setDestino(destino);
        setFechaSalida(fechaSalida);
        setFechaLlegada(fechaLlegada);
        setPrecio(precio);
        setEstado(estado);
    }

    /**
     * Constructor completo utilizado cuando el transporte ya existe en la base de datos
     * y dispone de un ID previamente asignado.
     *
     * @param idTransporte identificador único del transporte
     * @param tipo         tipo de transporte
     * @param origen       origen del trayecto
     * @param destino      destino del trayecto
     * @param fechaSalida  fecha y hora de salida
     * @param fechaLlegada fecha y hora de llegada
     * @param precio       precio del transporte
     * @param estado       estado de la reserva
     */
    public Transporte(int idTransporte, TipoTransporte tipo, String origen, String destino,
                      LocalDateTime fechaSalida, LocalDateTime fechaLlegada,
                      double precio, EstadoTransporte estado) {

        setIdTransporte(idTransporte);
        setTipo(tipo);
        setOrigen(origen);
        setDestino(destino);
        setFechaSalida(fechaSalida);
        setFechaLlegada(fechaLlegada);
        setPrecio(precio);
        setEstado(estado);
    }

    /**
     * Constructor vacío necesario para operaciones de serialización,
     * frameworks o carga dinámica.
     */
    public Transporte() {}

    /**
     * Obtiene el identificador único del transporte.
     *
     * @return ID del transporte
     */
    public int getIdTransporte() {
        return idTransporte;
    }

    /**
     * Establece el identificador del transporte.
     *
     * @param idTransporte ID del transporte
     * @throws IllegalArgumentException si el ID es negativo
     */
    public void setIdTransporte(int idTransporte) {
        if (idTransporte < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idTransporte = idTransporte;
    }

    /**
     * Obtiene el tipo de transporte.
     *
     * @return tipo de transporte
     */
    public TipoTransporte getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de transporte.
     *
     * @param tipo tipo de transporte
     * @throws IllegalArgumentException si el tipo es nulo
     */
    public void setTipo(TipoTransporte tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    /**
     * Obtiene el origen del trayecto.
     *
     * @return origen del transporte
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Establece el origen del trayecto.
     *
     * @param origen ciudad o punto de salida
     * @throws IllegalArgumentException si el origen es nulo o vacío
     */
    public void setOrigen(String origen) {
        if (origen == null || origen.isBlank())
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        this.origen = origen;
    }

    /**
     * Obtiene el destino del trayecto.
     *
     * @return destino del transporte
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del trayecto.
     *
     * @param destino ciudad o punto de llegada
     * @throws IllegalArgumentException si el destino es nulo o vacío
     */
    public void setDestino(String destino) {
        if (destino == null || destino.isBlank())
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        this.destino = destino;
    }

    /**
     * Obtiene la fecha y hora de salida.
     *
     * @return fecha de salida
     */
    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Establece la fecha y hora de salida.
     *
     * @param fechaSalida fecha de salida
     * @throws IllegalArgumentException si la fecha es nula
     */
    public void setFechaSalida(LocalDateTime fechaSalida) {
        if (fechaSalida == null)
            throw new IllegalArgumentException("La fecha de salida no puede ser nula.");
        this.fechaSalida = fechaSalida;
    }

    /**
     * Obtiene la fecha y hora de llegada.
     *
     * @return fecha de llegada
     */
    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Establece la fecha y hora de llegada.
     * Válida que no sea anterior a la salida.
     *
     * @param fechaLlegada fecha de llegada
     * @throws IllegalArgumentException si la fecha es nula o anterior a la salida
     */
    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        if (fechaLlegada == null)
            throw new IllegalArgumentException("La fecha de llegada no puede ser nula.");
        if (fechaSalida != null && fechaLlegada.isBefore(fechaSalida))
            throw new IllegalArgumentException("La llegada no puede ser anterior a la salida.");
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * Obtiene el precio del transporte.
     *
     * @return precio del transporte
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del transporte.
     *
     * @param precio precio del transporte
     * @throws IllegalArgumentException si el precio es negativo
     */
    public void setPrecio(double precio) {
        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    /**
     * Obtiene el estado del transporte.
     *
     * @return estado del transporte
     */
    public EstadoTransporte getEstado() {
        return estado;
    }

    /**
     * Establece el estado del transporte.
     *
     * @param estado estado del transporte
     * @throws IllegalArgumentException si el estado es nulo
     */
    public void setEstado(EstadoTransporte estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    /**
     * Compara dos transportes por su ID.
     *
     * @param o objeto a comparar
     * @return true si ambos transportes tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transporte t)) return false;
        return idTransporte == t.idTransporte;
    }

    /**
     * Genera un hash basado en el ID del transporte.
     *
     * @return hash del transporte
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTransporte);
    }

    /**
     * Devuelve una representación legible del transporte,
     * útil para mostrarlo en listas o interfaces gráficas.
     *
     * @return cadena con tipo, ruta y precio
     */
    @Override
    public String toString() {
        return tipo.getNombre() + " " + origen + " → " + destino + " (" + precio + "€)";
    }
}
