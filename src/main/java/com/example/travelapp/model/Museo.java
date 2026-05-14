package com.example.travelapp.model;

import java.util.Objects;

public class Museo extends ElementoCultural {
    private String ciudad;
    private String pais;
    private double precioEntrada;
    private String horario;
    private String webOficial;

    public Museo(String nombre, String descripcion, String ciudad, String pais, double precioEntrada, String horario, String webOficial) {
        if (ciudad == null || ciudad.isBlank())
            throw new IllegalArgumentException("La ciudad no puede estar vacía");

        if (pais == null || pais.isBlank())
            throw new IllegalArgumentException("El país no puede estar vacío");

        if (precioEntrada < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        if (horario == null || horario.isBlank())
            throw new IllegalArgumentException("El horario no puede estar vacío");

        if (webOficial == null || webOficial.isBlank())
            throw new IllegalArgumentException("La web oficial no puede estar vacía");
        super(0, nombre, descripcion);
        this.ciudad = ciudad;
        this.pais = pais;
        this.precioEntrada = precioEntrada;
        this.horario = horario;
        this.webOficial = webOficial;
    }

    @Override
    public String getTipo() {
        return "Museo";
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        if (ciudad == null || ciudad.isBlank())
            throw new IllegalArgumentException("La ciudad no puede estar vacía");
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        if (pais == null || pais.isBlank())
            throw new IllegalArgumentException("El país no puede estar vacío");
        this.pais = pais;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        if (precioEntrada < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precioEntrada = precioEntrada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        if (horario == null || horario.isBlank())
            throw new IllegalArgumentException("El horario no puede estar vacío");
        this.horario = horario;
    }

    public String getWebOficial() {
        return webOficial;
    }

    public void setWebOficial(String webOficial) {
        if (webOficial == null || webOficial.isBlank())
            throw new IllegalArgumentException("La web oficial no puede estar vacía");
        this.webOficial = webOficial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Museo)) return false;
        Museo museo = (Museo) o;
        return id == museo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getTipo() + ": " + nombre;
    }

    public boolean esGratuito() {
        return precioEntrada == 0;
    }

    public boolean esDePais(String pais) {
        return this.pais.equalsIgnoreCase(pais);
    }

    public String getUbicacion() {
        return ciudad + ", " + pais;
    }
}