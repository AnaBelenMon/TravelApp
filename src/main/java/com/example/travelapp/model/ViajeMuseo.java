package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class ViajeMuseo {
    private int idViaje;
    private int idMuseo;
    private LocalDate fechaVisita;
    private Emocion emocion;

    public ViajeMuseo(LocalDate fechaVisita, Emocion emocion) {
        this.fechaVisita = fechaVisita;
        this.emocion = emocion;
    }

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

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(int idMuseo) {
        this.idMuseo = idMuseo;
    }

    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

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

    @Override
    public String toString() {
        return fechaVisita + " – " + (emocion != null ? emocion : "Sin emoción");
    }

    public boolean esReciente() {
        return fechaVisita.isAfter(LocalDate.now().minusDays(30));
    }

    public boolean esFutura() {
        return fechaVisita.isAfter(LocalDate.now());
    }
}