package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Clase que representa un viaje dentro de la aplicación.
 * Contiene información sobre fechas, destino, tipo de viaje y presupuesto.
 */
public class Viaje {

    /** Identificador único del viaje */
    private int idViaje;

    /** Identificador del usuario propietario del viaje */
    private int idUsuario;

    /** Nombre del viaje */
    private String nombre;

    /** Fecha de inicio del viaje */
    private LocalDate fechaInicio;

    /** Fecha de fin del viaje */
    private LocalDate fechaFin;

    /** Tipo de viaje (aventura, relax, etc.) */
    private TipoViaje tipoViaje;

    /** Ruta o URL de la imagen de portada del viaje */
    private String imagenPortada;

    /** Notas generales sobre el viaje */
    private String notasGenerales;

    /** Presupuesto estimado del viaje */
    private double presupuestoEstimado;

    /** País de destino */
    private String destinoPais;

    /** Ciudad de destino */
    private String destinoCiudad;

    /** Constructor vacío */
    public Viaje() {}

    /**
     * Constructor sin ID (uso previo a persistencia).
     */
    public Viaje(String nombre, LocalDate fechaInicio, LocalDate fechaFin,
                 TipoViaje tipoViaje, String imagenPortada, String notasGenerales,
                 double presupuestoEstimado, String destinoPais, String destinoCiudad) {

        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoViaje = tipoViaje;
        this.imagenPortada = imagenPortada;
        this.notasGenerales = notasGenerales;
        this.presupuestoEstimado = presupuestoEstimado;
        this.destinoPais = destinoPais;
        this.destinoCiudad = destinoCiudad;
    }

    /**
     * Constructor completo con validaciones de negocio.
     *
     * @throws IllegalArgumentException si los datos no son válidos
     */
    public Viaje(int idViaje, int idUsuario, String nombre,
                 LocalDate fechaInicio, LocalDate fechaFin,
                 TipoViaje tipoViaje, String imagenPortada,
                 String notasGenerales, double presupuestoEstimado,
                 String destinoPais, String destinoCiudad) {

        if (idViaje <= 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (idUsuario <= 0) {
            throw new IllegalArgumentException("El id de usuario no es válido");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (!fechaInicio.isBefore(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
        if (tipoViaje == null) {
            throw new IllegalArgumentException("El tipo de viaje no puede ser nulo");
        }
        if (presupuestoEstimado < 0) {
            throw new IllegalArgumentException("El presupuesto no puede ser negativo");
        }
        if (destinoPais == null || destinoPais.isBlank()) {
            throw new IllegalArgumentException("El país de destino no puede estar vacío");
        }
        if (destinoCiudad == null || destinoCiudad.isBlank()) {
            throw new IllegalArgumentException("La ciudad de destino no puede estar vacía");
        }

        this.idViaje = idViaje;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoViaje = tipoViaje;
        this.imagenPortada = imagenPortada;
        this.notasGenerales = notasGenerales;
        this.presupuestoEstimado = presupuestoEstimado;
        this.destinoPais = destinoPais;
        this.destinoCiudad = destinoCiudad;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public int getIdViaje() { return idViaje; }
    public void setIdViaje(int idViaje) { this.idViaje = idViaje; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public TipoViaje getTipoViaje() { return tipoViaje; }
    public void setTipoViaje(TipoViaje tipoViaje) { this.tipoViaje = tipoViaje; }

    public String getImagenPortada() { return imagenPortada; }
    public void setImagenPortada(String imagenPortada) { this.imagenPortada = imagenPortada; }

    public String getNotasGenerales() { return notasGenerales; }
    public void setNotasGenerales(String notasGenerales) { this.notasGenerales = notasGenerales; }

    public double getPresupuestoEstimado() { return presupuestoEstimado; }
    public void setPresupuestoEstimado(double presupuestoEstimado) { this.presupuestoEstimado = presupuestoEstimado; }

    public String getDestinoPais() { return destinoPais; }
    public void setDestinoPais(String destinoPais) { this.destinoPais = destinoPais; }

    public String getDestinoCiudad() { return destinoCiudad; }
    public void setDestinoCiudad(String destinoCiudad) { this.destinoCiudad = destinoCiudad; }

    // =========================
    // OBJECT METHODS
    // =========================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje)) return false;
        Viaje v = (Viaje) o;
        return idViaje == v.idViaje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
    }

    @Override
    public String toString() {
        return nombre + " (" + destinoCiudad + ", " + destinoPais + ")";
    }

    // =========================
    // LÓGICA DE NEGOCIO
    // =========================

    /**
     * Calcula la duración del viaje en días.
     */
    public long getDuracionDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    /**
     * Indica si el viaje está actualmente en curso.
     */
    public boolean estaEnCurso() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }

    /**
     * Indica si el viaje es futuro.
     */
    public boolean esFuturo() {
        return LocalDate.now().isBefore(fechaInicio);
    }

    /**
     * Indica si el viaje ya ha finalizado.
     */
    public boolean esPasado() {
        return LocalDate.now().isAfter(fechaFin);
    }

    /**
     * Valida si las fechas del viaje son correctas.
     */
    public boolean fechaValida() {
        return fechaInicio != null && fechaFin != null && fechaInicio.isBefore(fechaFin);
    }

    /**
     * Devuelve el destino completo en formato "Ciudad, País".
     */
    public String getDestinoCompleto() {
        return destinoCiudad + ", " + destinoPais;
    }
}