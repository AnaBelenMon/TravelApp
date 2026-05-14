package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Recuerdo implements Emocionable {
    private int idRecuerdo;
    private int idViaje;
    private String rutaArchivo;
    private String descripcion;
    private String ubicacion;
    private LocalDate fecha;
    private Emocion emocion;
    private TipoRecuerdo tipo;
    private boolean favorito;
    private String rutaMiniatura;

    public Recuerdo(int idViaje, String rutaArchivo, String descripcion, String ubicacion, LocalDate fecha, Emocion emocion, TipoRecuerdo tipo, boolean favorito, String rutaMiniatura) {
        this.idViaje = idViaje;
        this.rutaArchivo = rutaArchivo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.emocion = emocion;
        this.tipo = tipo;
        this.favorito = favorito;
        this.rutaMiniatura = rutaMiniatura;
    }

    public int getIdRecuerdo() {
        return idRecuerdo;
    }

    public void setIdRecuerdo(int idRecuerdo) {
        this.idRecuerdo = idRecuerdo;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    public TipoRecuerdo getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecuerdo tipo) {
        this.tipo = tipo;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getRutaMiniatura() {
        return rutaMiniatura;
    }

    public void setRutaMiniatura(String rutaMiniatura) {
        this.rutaMiniatura = rutaMiniatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recuerdo)) return false;
        Recuerdo recuerdo = (Recuerdo) o;
        return idRecuerdo == recuerdo.idRecuerdo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecuerdo);
    }

    @Override
    public String toString() {
        return "Recuerdo{" +
                "idRecuerdo=" + idRecuerdo +
                ", idViaje=" + idViaje +
                ", archivo='" + rutaArchivo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", emocion=" + emocion +
                ", tipo=" + tipo +
                ", favorito=" + favorito +
                ", miniatura='" + rutaMiniatura + '\'' +
                '}';
    }

    @Override
    public void asignarEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    @Override
    public void limpiarEmocion() {
        this.emocion = null;
    }

    public boolean esFoto() {
        return tipo == TipoRecuerdo.FOTO;
    }

    public boolean esFavorito() {
        return favorito;
    }

    public boolean tieneMiniatura() {
        return rutaMiniatura != null && !rutaMiniatura.isBlank();
    }

    public boolean tieneUbicacion() {
        return ubicacion != null && !ubicacion.isBlank();
    }
}
