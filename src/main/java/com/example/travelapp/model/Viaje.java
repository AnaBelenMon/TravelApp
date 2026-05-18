package com.example.travelapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Viaje {
    private int idViaje;
    private int idUsuario;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private TipoViaje tipoViaje;
    private String imagenPortada;
    private String notasGenerales;
    private double presupuestoEstimado;
    private String destinoPais;
    private String destinoCiudad;

    /**
     *
     * @param idViaje
     * @param idUsuario
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @param tipoViaje
     * @param imagenPortada
     * @param notasGenerales
     * @param presupuestoEstimado
     * @param destinoPais
     * @param destinoCiudad
     */
    public Viaje(int idViaje,int idUsuario, String nombre, LocalDate fechaInicio, LocalDate fechaFin, TipoViaje tipoViaje, String imagenPortada, String notasGenerales, double presupuestoEstimado, String destinoPais, String destinoCiudad) {
        if (idViaje <= 0){
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (idUsuario <= 0){
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
        this.destinoPais = destinoPais.trim();
        this.destinoCiudad = destinoCiudad.trim();
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
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     *
     * @param fechaInicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     *
     * @param fechaFin
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     *
     * @return
     */
    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    /**
     *
     * @param tipoViaje
     */
    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    /**
     *
     * @return
     */
    public String getImagenPortada() {
        return imagenPortada;
    }

    /**
     *
     * @param imagenPortada
     */
    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    /**
     *
     * @return
     */
    public String getNotasGenerales() {
        return notasGenerales;
    }

    /**
     *
     * @param notasGenerales
     */
    public void setNotasGenerales(String notasGenerales) {
        this.notasGenerales = notasGenerales;
    }

    /**
     *
     * @return
     */
    public double getPresupuestoEstimado() {
        return presupuestoEstimado;
    }

    /**
     *
     * @param presupuestoEstimado
     */
    public void setPresupuestoEstimado(double presupuestoEstimado) {
        this.presupuestoEstimado = presupuestoEstimado;
    }

    /**
     *
     * @return
     */
    public String getDestinoPais() {
        return destinoPais;
    }

    /**
     *
     * @param destinoPais
     */
    public void setDestinoPais(String destinoPais) {
        this.destinoPais = destinoPais;
    }

    /**
     *
     * @return
     */
    public String getDestinoCiudad() {
        return destinoCiudad;
    }

    /**
     *
     * @param destinoCiudad
     */
    public void setDestinoCiudad(String destinoCiudad) {
        this.destinoCiudad = destinoCiudad;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje)) return false;
        Viaje v = (Viaje) o;
        return idViaje == v.idViaje;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Viaje{" +
                "idViaje=" + idViaje +
                ", idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", tipoViaje='" + tipoViaje + '\'' +
                ", imagenPortada='" + imagenPortada + '\'' +
                ", notasGenerales='" + notasGenerales + '\'' +
                ", presupuestoEstimado=" + presupuestoEstimado +
                ", destinoPais='" + destinoPais + '\'' +
                ", destinoCiudad='" + destinoCiudad + '\'' +
                '}';
    }

    /**
     *
     * @return
     */
    public long getDuracionDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    /**
     *
     * @return
     */
    public boolean estaEnCurso() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }

    /**
     *
     * @return
     */
    public boolean esFuturo() {
        return LocalDate.now().isBefore(fechaInicio);
    }

    /**
     *
     * @return
     */
    public boolean fechaValida() {
        return fechaInicio.isBefore(fechaFin) &&  fechaFin.isAfter(LocalDate.now());
    }

    /**
     *
     * @return
     */
    public String getDestinoCompleto() {
        return destinoCiudad + ", " + destinoPais;
    }

    /**
     *
     * @return
     */
    public boolean esPasado() {
        return LocalDate.now().isAfter(fechaFin);
    }
}