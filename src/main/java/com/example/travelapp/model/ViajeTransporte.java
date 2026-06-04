package com.example.travelapp.model;

public class ViajeTransporte {
    private int idViajeTransporte;
    private Viaje viaje;
    private Transporte transporte;
    private String notas;

    public ViajeTransporte() {}

    public ViajeTransporte(Viaje viaje, Transporte transporte, String notas) {
        setViaje(viaje);
        setTransporte(transporte);
        setNotas(notas);
    }

    public ViajeTransporte(int idViajeTransporte, Viaje viaje, Transporte transporte, String notas) {
        setIdViajeTransporte(idViajeTransporte);
        setViaje(viaje);
        setTransporte(transporte);
        setNotas(notas);
    }

    public int getIdViajeTransporte() {
        return idViajeTransporte;
    }

    public void setIdViajeTransporte(int idViajeTransporte) {
        if (idViajeTransporte < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idViajeTransporte = idViajeTransporte;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        if (viaje == null)
            throw new IllegalArgumentException("El viaje no puede ser nulo.");
        this.viaje = viaje;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        if (transporte == null)
            throw new IllegalArgumentException("El transporte no puede ser nulo.");
        this.transporte = transporte;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return viaje.getNombre() + " ↔ " + transporte.getTipo();
    }
}
