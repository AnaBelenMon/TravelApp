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

    /**
     *
     * @param idRecuerdo
     * @param idViaje
     * @param rutaArchivo
     * @param descripcion
     * @param ubicacion
     * @param fecha
     * @param emocion
     * @param tipo
     * @param favorito
     * @param rutaMiniatura
     */
    public Recuerdo(int idRecuerdo, int idViaje, String rutaArchivo, String descripcion, String ubicacion, LocalDate fecha, Emocion emocion, TipoRecuerdo tipo, boolean favorito, String rutaMiniatura) {
        this.idRecuerdo = idRecuerdo;
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

    /**
     *
     * @return
     */
    public int getIdRecuerdo() {
        return idRecuerdo;
    }

    /**
     *
     * @param idRecuerdo
     */
    public void setIdRecuerdo(int idRecuerdo) {
        this.idRecuerdo = idRecuerdo;
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
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     *
     * @param rutaArchivo
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     *
     * @param ubicacion
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     *
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
    private void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    /**
     *
     * @return
     */
    public TipoRecuerdo getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoRecuerdo tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public boolean isFavorito() {
        return favorito;
    }

    /**
     *
     * @param favorito
     */
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    /**
     *
     * @return
     */
    public String getRutaMiniatura() {
        return rutaMiniatura;
    }

    /**
     *
     * @param rutaMiniatura
     */
    public void setRutaMiniatura(String rutaMiniatura) {
        this.rutaMiniatura = rutaMiniatura;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recuerdo)) return false;
        Recuerdo recuerdo = (Recuerdo) o;
        return idRecuerdo == recuerdo.idRecuerdo;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idRecuerdo);
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param emocion
     */
    @Override
    public void asignarEmocion(Emocion emocion) {
        setEmocion(emocion);
    }

    @Override
    public void limpiarEmocion() {
        this.emocion = null;
    }

    /**
     *
     * @return
     */
    public boolean esFoto() {
        return tipo == TipoRecuerdo.FOTO;
    }

    /**
     *
     * @return
     */
    public boolean esFavorito() {
        return favorito;
    }

    /**
     *
     * @return
     */
    public boolean tieneMiniatura() {
        return rutaMiniatura != null && !rutaMiniatura.isBlank();
    }

    /**
     *
     * @return
     */
    public boolean tieneUbicacion() {
        return ubicacion != null && !ubicacion.isBlank();
    }
}
