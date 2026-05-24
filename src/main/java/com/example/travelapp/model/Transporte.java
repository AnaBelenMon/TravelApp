package com.example.travelapp.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un transporte asociado a un viaje.
 * Incluye información como tipo de transporte, fecha, precio y documento asociado.
 */
public class Transporte {

    /** Identificador único del transporte */
    private int idTransporte;

    /** Identificador del viaje al que pertenece */
    private int idViaje;

    /** Tipo de transporte (avión, tren, coche, etc.) */
    private TipoTransporte tipo;

    /** Fecha en la que se realiza el transporte */
    private LocalDate fecha;

    /** Precio del transporte */
    private double precio;

    /** Tipo de documento asociado (billete, reserva, etc.) */
    private TipoDocumento tipoDocumento;

    /** Ruta del documento asociado al transporte */
    private String rutaDocumento;

    /**
     * Constructor básico sin ID (uso para creación antes de persistencia).
     *
     * @param tipo tipo de transporte
     * @param fecha fecha del transporte
     * @param precio precio del transporte
     * @param tipoDocumento tipo de documento asociado
     * @param rutaDocumento ruta del documento asociado
     */
    public Transporte(TipoTransporte tipo, LocalDate fecha, double precio,
                      TipoDocumento tipoDocumento, String rutaDocumento) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDocumento = tipoDocumento;
        this.rutaDocumento = rutaDocumento;
    }

    /**
     * Constructor completo con validación de datos.
     *
     * @param idTransporte identificador del transporte
     * @param idViaje identificador del viaje
     * @param tipo tipo de transporte
     * @param fecha fecha del transporte
     * @param precio precio del transporte
     * @param tipoDocumento tipo de documento asociado
     * @param rutaDocumento ruta del documento
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Transporte(int idTransporte, int idViaje, TipoTransporte tipo,
                      LocalDate fecha, double precio,
                      TipoDocumento tipoDocumento, String rutaDocumento) {

        if (idTransporte <= 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de transporte no puede ser nulo");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo");
        }
        if (rutaDocumento == null || rutaDocumento.isBlank()) {
            rutaDocumento = "";
        }

        this.idTransporte = idTransporte;
        this.idViaje = idViaje;
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDocumento = tipoDocumento;
        this.rutaDocumento = rutaDocumento;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    /** @return id del transporte */
    public int getIdTransporte() {
        return idTransporte;
    }

    /** @param idTransporte establece el id del transporte */
    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    /** @return id del viaje asociado */
    public int getIdViaje() {
        return idViaje;
    }

    /** @param idViaje establece el id del viaje */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /** @return tipo de transporte */
    public TipoTransporte getTipo() {
        return tipo;
    }

    /** @param tipo establece el tipo de transporte */
    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    /** @return fecha del transporte */
    public LocalDate getFecha() {
        return fecha;
    }

    /** @param fecha establece la fecha del transporte */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /** @return precio del transporte */
    public double getPrecio() {
        return precio;
    }

    /** @param precio establece el precio del transporte */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /** @return tipo de documento asociado */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /** @param tipoDocumento establece el tipo de documento */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /** @return ruta del documento asociado */
    public String getRutaDocumento() {
        return rutaDocumento;
    }

    /** @param rutaDocumento establece la ruta del documento */
    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    // =========================
    // MÉTODOS DE LÓGICA
    // =========================

    /**
     * Indica si el transporte es caro (más de 100€).
     */
    public boolean esCaro() {
        return precio > 100;
    }

    /**
     * Indica si tiene un documento asociado.
     */
    public boolean tieneDocumento() {
        return rutaDocumento != null && !rutaDocumento.isBlank();
    }

    /**
     * Indica si el transporte es aéreo.
     */
    public boolean esAereo() {
        return tipo.esAereo();
    }

    /**
     * Indica si el transporte es terrestre.
     */
    public boolean esTerrestre() {
        return tipo.esTerrestre();
    }

    /**
     * Indica si el transporte es marítimo.
     */
    public boolean esMaritimo() {
        return tipo.esMaritimo();
    }

    /**
     * Indica si el transporte es gratuito.
     */
    public boolean esGratis() {
        return precio == 0;
    }

    // =========================
    // OBJECT METHODS
    // =========================

    /**
     * Dos transportes se consideran iguales si tienen el mismo id.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transporte)) return false;
        Transporte t = (Transporte) o;
        return idTransporte == t.idTransporte;
    }

    /**
     * Genera el hash basado en el id del transporte.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTransporte);
    }

    /**
     * Representación en texto del transporte.
     */
    @Override
    public String toString() {
        return tipo.getIcono() + " " + tipo.getNombre() + " - " + precio + "€";
    }
}