package com.example.travelapp.model;

/**
 * Representa la relación entre un {@link Viaje} y un {@link Transporte}
 * dentro de la aplicación TravelApp.
 * Esta clase actúa como tabla intermedia en una relación N:M,
 * permitiendo asociar múltiples transportes a un viaje y, a su vez,
 * que un transporte pueda estar vinculado a distintos viajes si fuera necesario.
 * Además, permite almacenar notas adicionales específicas de la relación,
 * como detalles del uso del transporte dentro del viaje.
 */
public class ViajeTransporte {

    private int idViajeTransporte;
    private Viaje viaje;
    private Transporte transporte;
    private String notas;

    /**
     * Constructor vacío necesario para serialización,
     * frameworks o creación dinámica de objetos.
     */
    public ViajeTransporte() {}

    /**
     * Constructor completo utilizado cuando la relación ya existe en la base de datos
     * y dispone de un ID previamente asignado.
     *
     * @param idViajeTransporte identificador único de la relación
     * @param viaje             viaje asociado
     * @param transporte        transporte asociado
     * @param notas             notas adicionales
     */
    public ViajeTransporte(int idViajeTransporte, Viaje viaje, Transporte transporte, String notas) {
        setIdViajeTransporte(idViajeTransporte);
        setViaje(viaje);
        setTransporte(transporte);
        setNotas(notas);
    }

    /**
     * Obtiene el identificador único de la relación.
     *
     * @return ID de ViajeTransporte
     */
    public int getIdViajeTransporte() {
        return idViajeTransporte;
    }

    /**
     * Establece el identificador de la relación.
     *
     * @param idViajeTransporte ID de la relación
     * @throws IllegalArgumentException si el ID es negativo
     */
    public void setIdViajeTransporte(int idViajeTransporte) {
        if (idViajeTransporte < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idViajeTransporte = idViajeTransporte;
    }

    /**
     * Obtiene el viaje asociado.
     *
     * @return viaje vinculado
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * Establece el viaje asociado.
     *
     * @param viaje viaje vinculado
     * @throws IllegalArgumentException si el viaje es nulo
     */
    public void setViaje(Viaje viaje) {
        if (viaje == null)
            throw new IllegalArgumentException("El viaje no puede ser nulo.");
        this.viaje = viaje;
    }

    /**
     * Obtiene el transporte asociado.
     *
     * @return transporte vinculado
     */
    public Transporte getTransporte() {
        return transporte;
    }

    /**
     * Establece el transporte asociado.
     *
     * @param transporte transporte vinculado
     * @throws IllegalArgumentException si el transporte es nulo
     */
    public void setTransporte(Transporte transporte) {
        if (transporte == null)
            throw new IllegalArgumentException("El transporte no puede ser nulo.");
        this.transporte = transporte;
    }

    /**
     * Obtiene las notas adicionales de la relación.
     *
     * @return notas asociadas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece notas adicionales para la relación.
     * Este campo es opcional.
     *
     * @param notas notas de la relación
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Devuelve una representación legible de la relación,
     * útil para mostrarla en listas o interfaces gráficas.
     *
     * @return cadena con el nombre del viaje y el tipo de transporte
     */
    @Override
    public String toString() {
        return viaje.getNombre() + " ↔ " + transporte.getTipo();
    }
}
