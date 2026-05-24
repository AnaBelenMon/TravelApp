package com.example.travelapp.model;

import java.util.Objects;

/**
 * Representa un documento asociado a un viaje.
 *
 * Puede ser un archivo como PDF, imagen u otro tipo definido por
 * {@link TipoDocumento}. Cada documento tiene un nombre, tipo y una ruta
 * de archivo en el sistema.
 *
 * Esta clase incluye validaciones básicas para garantizar la integridad
 * de los datos.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Documento {

    private int idDocumento;
    private int idViaje;
    private String nombre;
    private TipoDocumento tipo;
    private String rutaArchivo;

    /**
     * Constructor para crear un documento sin identificadores.
     */
    public Documento(String nombre, TipoDocumento tipo, String rutaArchivo) {
        setNombre(nombre);
        setTipo(tipo);
        setRutaArchivo(rutaArchivo);
    }

    /**
     * Constructor completo con identificadores.
     */
    public Documento(int idDocumento, int idViaje, String nombre,
                     TipoDocumento tipo, String rutaArchivo) {

        this.idDocumento = idDocumento;
        this.idViaje = idViaje;
        setNombre(nombre);
        setTipo(tipo);
        setRutaArchivo(rutaArchivo);
    }

    /**
     * Obtiene el identificador del documento.
     *
     * @return id del documento
     */
    public int getIdDocumento() {
        return idDocumento;
    }

    /**
     * Establece el identificador del documento.
     *
     * @param idDocumento identificador único
     */
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * Obtiene el identificador del viaje asociado.
     *
     * @return id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Establece el identificador del viaje.
     *
     * @param idViaje identificador del viaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * Obtiene el nombre del documento.
     *
     * @return nombre del documento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del documento.
     *
     * @param nombre nombre del documento
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo del documento.
     *
     * @return tipo de documento
     */
    public TipoDocumento getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del documento.
     *
     * @param tipo tipo de documento
     * @throws IllegalArgumentException si el tipo es nulo
     */
    public void setTipo(TipoDocumento tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo no puede ser nulo");
        }
        this.tipo = tipo;
    }

    /**
     * Obtiene la ruta del archivo.
     *
     * @return ruta del archivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Establece la ruta del archivo.
     *
     * @param rutaArchivo ruta en el sistema de archivos
     * @throws IllegalArgumentException si la ruta es nula o vacía
     */
    public void setRutaArchivo(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía");
        }
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Compara documentos por su identificador único.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return idDocumento == that.idDocumento;
    }

    /**
     * Genera hash basado en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idDocumento);
    }

    /**
     * Representación en texto del documento.
     *
     * @return nombre del documento
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Indica si el documento es un PDF.
     *
     * @return true si es PDF
     */
    public boolean esPDF() {
        return tipo == TipoDocumento.PDF;
    }

    /**
     * Indica si el documento es una imagen.
     *
     * @return true si es imagen
     */
    public boolean esImagen() {
        return tipo == TipoDocumento.IMAGEN;
    }

    /**
     * Comprueba si el documento tiene una ruta válida.
     *
     * @return true si tiene archivo asociado
     */
    public boolean tieneArchivo() {
        return rutaArchivo != null && !rutaArchivo.isBlank();
    }
}