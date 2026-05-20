package com.example.travelapp.model;

import java.util.Objects;

public class Obra extends ElementoCultural {
    private String autor;
    private String estilo;

    public Obra(String nombre, String descripcion, String autor, String estilo) {
        super(nombre, descripcion);
        this.autor = autor;
        this.estilo = estilo;
    }

    @Override
    public String getTipo() {
        return "Obra";
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean tieneAutor() {
        return autor != null && !autor.isBlank();
    }

    public boolean esDeEstilo(String estilo) {
        return this.estilo.equalsIgnoreCase(estilo);
    }

    public boolean esAnonima() {
        return autor == null || autor.isBlank();
    }

    public boolean esDeAutor(String autor) {
        return this.autor != null && this.autor.equalsIgnoreCase(autor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obra)) return false;
        Obra obra = (Obra) o;
        return id == obra.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getTipo() + ": " + nombre + " (" + autor + ")";
    }
}
