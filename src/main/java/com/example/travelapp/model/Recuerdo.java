package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un recuerdo asociado a un viaje.
 *
 * Un recuerdo puede ser una foto, vídeo u otro tipo de contenido multimedia
 * que el usuario guarda durante su experiencia de viaje.
 *
 * Incluye información adicional como ubicación, fecha, emoción asociada,
 * tipo de recuerdo y si está marcado como favorito.
 *
 * Implementa {@link Emocionable} para permitir la gestión de emociones
 * asociadas al recuerdo.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
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
     * Constructor sin identificadores.
     */
    public Recuerdo(String rutaArchivo, String descripcion, String ubicacion,
                    LocalDate fecha, Emocion emocion, TipoRecuerdo tipo,
                    boolean favorito, String rutaMiniatura) {

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
     * Constructor completo con identificadores.
     */
    public Recuerdo(int idRecuerdo, int idViaje, String rutaArchivo,
                    String descripcion, String ubicacion, LocalDate fecha,
                    Emocion emocion, TipoRecuerdo tipo, boolean favorito,
                    String rutaMiniatura) {

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
     * Obtiene el identificador del recuerdo.
     *
     * @return id del recuerdo
     */
    public int getIdRecuerdo() {
        return idRecuerdo;
    }

    public void setIdRecuerdo(int idRecuerdo) {
        this.idRecuerdo = idRecuerdo;
    }

    /**
     * Obtiene el identificador del viaje asociado.
     *
     * @return id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * Obtiene la ruta del archivo multimedia.
     *
     * @return ruta del archivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Obtiene la descripción del recuerdo.
     *
     * @return descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la ubicación del recuerdo.
     *
     * @return ubicación
     */
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene la fecha del recuerdo.
     *
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la emoción asociada al recuerdo.
     *
     * @return emoción
     */
    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    /**
     * Obtiene el tipo de recuerdo.
     *
     * @return tipo de recuerdo
     */
    public TipoRecuerdo getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecuerdo tipo) {
        this.tipo = tipo;
    }

    /**
     * Indica si el recuerdo es favorito.
     *
     * @return true si es favorito
     */
    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    /**
     * Obtiene la ruta de la miniatura.
     *
     * @return ruta miniatura
     */
    public String getRutaMiniatura() {
        return rutaMiniatura;
    }

    public void setRutaMiniatura(String rutaMiniatura) {
        this.rutaMiniatura = rutaMiniatura;
    }

    /**
     * Asigna una emoción al recuerdo.
     */
    @Override
    public void asignarEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    /**
     * Elimina la emoción asociada al recuerdo.
     */
    @Override
    public void limpiarEmocion() {
        this.emocion = null;
    }

    /**
     * Indica si el recuerdo es una foto.
     *
     * @return true si es foto
     */
    public boolean esFoto() {
        return tipo == TipoRecuerdo.FOTO;
    }

    /**
     * Indica si el recuerdo es favorito.
     *
     * @return true si es favorito
     */
    public boolean esFavorito() {
        return favorito;
    }

    /**
     * Indica si tiene miniatura asociada.
     *
     * @return true si existe miniatura
     */
    public boolean tieneMiniatura() {
        return rutaMiniatura != null && !rutaMiniatura.isBlank();
    }

    /**
     * Indica si tiene ubicación válida.
     *
     * @return true si tiene ubicación
     */
    public boolean tieneUbicacion() {
        return ubicacion != null && !ubicacion.isBlank();
    }

    /**
     * Compara recuerdos por su identificador.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recuerdo)) return false;
        Recuerdo recuerdo = (Recuerdo) o;
        return idRecuerdo == recuerdo.idRecuerdo;
    }

    /**
     * Genera hash basado en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idRecuerdo);
    }

    /**
     * Representación en texto del recuerdo.
     *
     * @return tipo y fecha
     */
    @Override
    public String toString() {
        return tipo + " - " + fecha;
    }
}