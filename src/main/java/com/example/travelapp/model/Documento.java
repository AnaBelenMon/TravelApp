package com.example.travelapp.model;

import java.util.Objects;

public class Documento {
    private int idDocumento;
    private int idViaje;
    private String nombre;
    private TipoDocumento tipo;
    private String rutaArchivo;

    public Documento(String nombre, TipoDocumento tipo, String rutaArchivo) {
        setNombre(nombre);
        setTipo(tipo);
        setRutaArchivo(rutaArchivo);
    }

    public Documento(int idDocumento, int idViaje, String nombre, TipoDocumento tipo, String rutaArchivo) {
        this.idDocumento = idDocumento;
        this.idViaje = idViaje;
        setNombre(nombre);
        setTipo(tipo);
        setRutaArchivo(rutaArchivo);
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo no puede ser nulo");
        }
        this.tipo = tipo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía");
        }
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return idDocumento == that.idDocumento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocumento);
    }

    @Override
    public String toString() {
        return nombre; // Mostrar solo el nombre en ComboBox y tablas
    }

    public boolean esPDF() {
        return tipo == TipoDocumento.PDF;
    }

    public boolean esImagen() {
        return tipo == TipoDocumento.IMAGEN;
    }

    public boolean tieneArchivo() {
        return rutaArchivo != null && !rutaArchivo.isBlank();
    }
}