package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class ViajeMuseo {
    private int idViaje;
    private int idMuseo;
    private LocalDate fechaVisita;
    private Emocion emocion;

    /**
     *
     * @param fechaVisita
     * @param emocion
     */
    public ViajeMuseo(LocalDate fechaVisita, Emocion emocion) {
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
        this.fechaVisita = fechaVisita;
        this.emocion = emocion;
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
        this.idViaje = idViaje;
    }

    /**
     *
     * @return
     */
    public int getIdMuseo() {
        return idMuseo;
    }

    /**
     *
     * @param idMuseo
     */
    public void setIdMuseo(int idMuseo) {
        this.idMuseo = idMuseo;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    /**
     *
     * @param fechaVisita
     */
    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    /**
     *
     * @return
     */
    public Emocion getEmocion() {
        return emocion;
    }

    /**
     *
     * @param emocion
     */
    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViajeMuseo)) return false;
        ViajeMuseo vm = (ViajeMuseo) o;
        return idViaje == vm.idViaje && idMuseo == vm.idMuseo;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idViaje, idMuseo);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ViajeMuseo{" +
                "idViaje=" + idViaje +
                ", idMuseo=" + idMuseo +
                ", fechaVisita='" + fechaVisita + '\'' +
                ", emocion='" + emocion + '\'' +
                '}';
    }

    /**
     *
     * @return
     */
    public boolean esReciente() {
        return fechaVisita.isAfter(LocalDate.now().minusDays(30));
    }

    /**
     *
     * @return
     */
    public boolean esFutura() {
        return fechaVisita.isAfter(LocalDate.now());
    }

}