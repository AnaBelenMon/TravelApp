package com.example.travelapp.model;

import com.example.travelapp.model.enums.TipoAlojamiento;
import com.example.travelapp.model.interfaces.Valorable;

import java.util.Objects;

/**
 * Representa un alojamiento físico dentro de la aplicación TravelApp.
 * Un alojamiento incluye información básica como nombre, tipo, dirección
 * y localización, además de permitir asignar una valoración mediante la
 * interfaz {@link Valorable}.
 *
 * Esta clase forma parte del modelo principal y se utiliza para asociar
 * alojamientos a viajes, así como para mostrar información detallada
 * en la interfaz de usuario.
 */
public class Alojamiento implements Valorable {
    private int idAlojamiento;
    private String nombre;
    private TipoAlojamiento tipo;
    private String direccion;
    private String ciudad;
    private String pais;
    private int valoracion;

    /**
     * Constructor vacío necesario para serialización, frameworks
     * o creación dinámica de objetos.
     */
    public Alojamiento() {}

    /**
     * Constructor utilizado para crear un alojamiento nuevo sin ID asignado.
     *
     * @param nombre      nombre del alojamiento
     * @param tipo        tipo de alojamiento
     * @param direccion   dirección del alojamiento
     * @param ciudad      ciudad donde se ubica
     * @param pais        país donde se ubica
     * @param valoracion  valoración inicial del alojamiento
     */
    public Alojamiento(String nombre, TipoAlojamiento tipo, String direccion,
                       String ciudad, String pais, int valoracion) {

        setNombre(nombre);
        setTipo(tipo);
        setDireccion(direccion);
        setCiudad(ciudad);
        setPais(pais);
        valorar(valoracion);
    }

    /**
     * Constructor completo utilizado cuando el alojamiento ya existe
     * en la base de datos y dispone de un ID asignado.
     *
     * @param idAlojamiento identificador único del alojamiento
     * @param nombre        nombre del alojamiento
     * @param tipo          tipo de alojamiento
     * @param direccion     dirección del alojamiento
     * @param ciudad        ciudad donde se ubica
     * @param pais          país donde se ubica
     * @param valoracion    valoración inicial del alojamiento
     */
    public Alojamiento(int idAlojamiento, String nombre, TipoAlojamiento tipo,
                       String direccion, String ciudad, String pais, int valoracion) {

        setIdAlojamiento(idAlojamiento);
        setNombre(nombre);
        setTipo(tipo);
        setDireccion(direccion);
        setCiudad(ciudad);
        setPais(pais);
        valorar(valoracion);
    }

    /**
     * Obtiene el identificador único del alojamiento.
     *
     * @return ID del alojamiento
     */
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    /**
     * Establece el identificador del alojamiento.
     *
     * @param idAlojamiento ID del alojamiento
     * @throws IllegalArgumentException si el ID es negativo
     */
    public void setIdAlojamiento(int idAlojamiento) {
        if (idAlojamiento < 0)
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.idAlojamiento = idAlojamiento;
    }

    /**
     * Obtiene el nombre del alojamiento.
     *
     * @return nombre del alojamiento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alojamiento.
     *
     * @param nombre nombre del alojamiento
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de alojamiento.
     *
     * @return tipo de alojamiento
     */
    public TipoAlojamiento getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de alojamiento.
     *
     * @param tipo tipo de alojamiento
     * @throws IllegalArgumentException si el tipo es nulo
     */
    public void setTipo(TipoAlojamiento tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("El tipo no puede ser nulo.");
        this.tipo = tipo;
    }

    /**
     * Obtiene la dirección del alojamiento.
     *
     * @return dirección del alojamiento
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del alojamiento.
     *
     * @param direccion dirección del alojamiento
     * @throws IllegalArgumentException si la dirección es nula o vacía
     */
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        this.direccion = direccion;
    }

    /**
     * Obtiene la ciudad donde se encuentra el alojamiento.
     *
     * @return ciudad del alojamiento
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad del alojamiento.
     *
     * @param ciudad ciudad del alojamiento
     * @throws IllegalArgumentException si la ciudad es nula o vacía
     */
    public void setCiudad(String ciudad) {
        if (ciudad == null || ciudad.isBlank())
            throw new IllegalArgumentException("La ciudad no puede estar vacía.");
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el país donde se encuentra el alojamiento.
     *
     * @return país del alojamiento
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del alojamiento.
     *
     * @param pais país del alojamiento
     * @throws IllegalArgumentException si el país es nulo o vacío
     */
    public void setPais(String pais) {
        if (pais == null || pais.isBlank())
            throw new IllegalArgumentException("El país no puede estar vacío.");
        this.pais = pais;
    }

    /**
     * Obtiene la valoración actual del alojamiento.
     *
     * @return valoración numérica entre 1 y 5
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Asigna una valoración al alojamiento.
     * La puntuación debe estar entre 1 y 5.
     *
     * @param puntuacion puntuación asignada
     */
    @Override
    public void valorar(int puntuacion) {
        if (puntuacion < 1 || puntuacion > 5) return;
        this.valoracion = puntuacion;
    }

    /**
     * Compara dos alojamientos por su ID.
     *
     * @param o objeto a comparar
     * @return true si ambos alojamientos tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alojamiento that)) return false;
        return idAlojamiento == that.idAlojamiento;
    }

    /**
     * Genera un hash basado en el ID del alojamiento.
     *
     * @return hash del alojamiento
     */
    @Override
    public int hashCode() {
        return Objects.hash(idAlojamiento);
    }

    /**
     * Devuelve una representación legible del alojamiento,
     * útil para mostrarlo en listas o interfaces gráficas.
     *
     * @return cadena con el nombre y localización del alojamiento
     */
    @Override
    public String toString() {
        return nombre + " - " + ciudad + ", " + pais;
    }
}
