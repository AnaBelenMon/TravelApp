package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa la relación entre un viaje y un museo visitado.
 * Incluye información sobre la fecha de visita y la emoción asociada.
 */
public class ViajeMuseo {

    /** Identificador del viaje */
    private int idViaje;

    /** Identificador del museo */
    private int idMuseo;

    /** Fecha en la que se realizó la visita al museo */
    private LocalDate fechaVisita;

    /** Emoción asociada a la visita */
    private Emocion emocion;

    /**
     * Constructor básico sin IDs.
     *
     * @param fechaVisita fecha de la visita
     * @param emocion emoción asociada a la visita
     */
    public ViajeMuseo(LocalDate fechaVisita, Emocion emocion) {
        this.fechaVisita = fechaVisita;
        this.emocion = emocion;
    }

    /**
     * Constructor completo con validaciones.
     *
     * @param idViaje identificador del viaje
     * @param idMuseo identificador del museo
     * @param fechaVisita fecha de la visita
     * @param emocion emoción asociada
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public ViajeMuseo(int idViaje, int idMuseo, LocalDate fechaVisita, Emocion emocion) {

        if (idViaje <= 0) {
            throw new IllegalArgumentException("El id del viaje no es válido");
        }
        if (idMuseo <= 0) {
            throw new IllegalArgumentException("El id del museo no es válido");
        }
        if (fechaVisita == null) {
            throw new IllegalArgumentException("La fecha de visita no puede ser nula");
        }
        if (emocion == null) {
            throw new IllegalArgumentException("La emoción no puede ser nula");
        }

        this.idViaje = idViaje;
        this.idMuseo = idMuseo;
        this.fechaVisita = fechaVisita;
        this.emocion = emocion;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public int getIdViaje() { return idViaje; }
    public void setIdViaje(int idViaje) { this.idViaje = idViaje; }

    public int getIdMuseo() { return idMuseo; }
    public void setIdMuseo(int idMuseo) { this.idMuseo = idMuseo; }

    public LocalDate getFechaVisita() { return fechaVisita; }
    public void setFechaVisita(LocalDate fechaVisita) { this.fechaVisita = fechaVisita; }

    public Emocion getEmocion() { return emocion; }
    public void setEmocion(Emocion emocion) { this.emocion = emocion; }

    // =========================
    // OBJECT METHODS
    // =========================

    /**
     * Dos registros ViajeMuseo se consideran iguales si coinciden
     * el id del viaje y el id del museo.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViajeMuseo)) return false;
        ViajeMuseo vm = (ViajeMuseo) o;
        return idViaje == vm.idViaje && idMuseo == vm.idMuseo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViaje, idMuseo);
    }

    /**
     * Representación en texto del registro de visita.
     */
    @Override
    public String toString() {
        return fechaVisita + " – " + (emocion != null ? emocion : "Sin emoción");
    }

    // =========================
    // LÓGICA DE NEGOCIO
    // =========================

    /**
     * Indica si la visita es reciente (últimos 30 días).
     */
    public boolean esReciente() {
        return fechaVisita.isAfter(LocalDate.now().minusDays(30));
    }

    /**
     * Indica si la visita está programada para el futuro.
     */
    public boolean esFutura() {
        return fechaVisita.isAfter(LocalDate.now());
    }
}