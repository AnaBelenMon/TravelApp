package com.example.travelapp.model;

import java.util.Objects;

public class Documento {
    private int idDocumento;
    private int idViaje;
    private String nombre;
    private TipoDocumento tipo;
    private String rutaArchivo;

    /**
     *
     * @param nombre
     * @param tipo
     * @param rutaArchivo
     */
    public Documento(String nombre, TipoDocumento tipo, String rutaArchivo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo");
        }
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía");
        }
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaArchivo = rutaArchivo;
    }

    /**
     *
     * @return
     */
    public int getIdDocumento() {
        return idDocumento;
    }

    /**
     *
     * @param idDocumento
     */
    public void setIdDocumento(int idDocumento) {
        if (idDocumento > 0){
            this.idDocumento = idDocumento;
        }
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
        if (idViaje > 0) {
            this.idViaje = idViaje;
        }
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public TipoDocumento getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoDocumento tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo no puede ser nulo");
        }
        this.tipo = tipo;
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
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía");
        }
        this.rutaArchivo = rutaArchivo;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return idDocumento == that.idDocumento;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idDocumento);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento=" + idDocumento +
                ", idViaje=" + idViaje +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", archivo='" + rutaArchivo + '\'' +
                '}';
    }

    /**
     *
     * @return
     */
    public boolean esPDF() {
        return tipo == TipoDocumento.PDF;
    }

    /**
     *
     * @return
     */
    public boolean esImagen() {
        return tipo == TipoDocumento.IMAGEN;
    }

    /**
     *
     * @return
     */
    public boolean tieneArchivo() {
        return rutaArchivo != null && !rutaArchivo.isBlank();
    }
}