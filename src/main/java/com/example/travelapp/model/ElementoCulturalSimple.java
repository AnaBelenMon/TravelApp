package com.example.travelapp.model;

public class ElementoCulturalSimple extends ElementoCultural {

    public ElementoCulturalSimple(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    @Override
    public String getTipo() {
        return "Elemento cultural";
    }
}
