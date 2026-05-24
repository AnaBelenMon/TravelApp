package com.example.travelapp.model;

import java.util.Objects;

public class Museo extends ElementoCultural {
    private String ciudad;
    private String pais;
    private double precioEntrada;
    private String horario;
    private String webOficial;

    public Museo(String nombre, String descripcion, String ciudad, String pais, double precioEntrada, String horario, String webOficial) {
        super(nombre, descripcion);
        this.ciudad = ciudad;
        this.pais = pais;
        this.precioEntrada = precioEntrada;
        this.horario = horario;
        this.webOficial = webOficial;
    }

    public Museo(int id, String nombre, String descripcion, String ciudad, String pais, double precioEntrada, String horario, String webOficial) {
        super(id, nombre, descripcion);
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
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getWebOficial() {
        return webOficial;
    }

    public void setWebOficial(String webOficial) {
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
        return nombre;
    }

    public boolean esGratuito() {
        return precioEntrada == 0;
    }

    public boolean esDePais(String pais) {
        return this.pais != null && this.pais.equalsIgnoreCase(pais);
    }

    public String getUbicacion() {
        return ciudad + ", " + pais;
    }
}