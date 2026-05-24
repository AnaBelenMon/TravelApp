package com.example.travelapp.model;

import java.util.Objects;

/**
 * Representa una obra de arte dentro del sistema de elementos culturales.
 *
 * Hereda de {@link ElementoCultural} y añade información específica como
 * el autor y el estilo artístico.
 *
 * Se utiliza para representar obras en museos o catálogos culturales
 * dentro de la aplicación de viajes.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Obra extends ElementoCultural {

    private String autor;
    private String estilo;

    /**
     * Constructor de una obra sin identificador.
     */
    public Obra(int idElemento, String nombre, String descripcion, String autor, String estilo) {
        super(nombre, descripcion);
        this.autor = autor;
        this.estilo = estilo;
    }

    /**
     * Devuelve el tipo de elemento cultural.
     *
     * @return "Obra"
     */
    @Override
    public String getTipo() {
        return "Obra";
    }

    /**
     * Obtiene el autor de la obra.
     *
     * @return autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor de la obra.
     *
     * @param autor nombre del autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el estilo artístico de la obra.
     *
     * @return estilo artístico
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * Establece el estilo artístico de la obra.
     *
     * @param estilo estilo artístico
     */
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    /**
     * Indica si la obra tiene autor definido.
     *
     * @return true si tiene autor
     */
    public boolean tieneAutor() {
        return autor != null && !autor.isBlank();
    }

    /**
     * Comprueba si la obra pertenece a un estilo concreto.
     *
     * @param estilo estilo a comparar
     * @return true si coincide
     */
    public boolean esDeEstilo(String estilo) {
        return this.estilo.equalsIgnoreCase(estilo);
    }

    /**
     * Indica si la obra es anónima.
     *
     * @return true si no tiene autor
     */
    public boolean esAnonima() {
        return autor == null || autor.isBlank();
    }

    /**
     * Comprueba si la obra es de un autor específico.
     *
     * @param autor autor a comparar
     * @return true si coincide
     */
    public boolean esDeAutor(String autor) {
        return this.autor != null && this.autor.equalsIgnoreCase(autor);
    }

    /**
     * Compara obras por su identificador.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obra)) return false;
        Obra obra = (Obra) o;
        return id == obra.id;
    }

    /**
     * Genera hash basado en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Representación en texto de la obra.
     *
     * @return tipo, nombre y autor
     */
    @Override
    public String toString() {
        return getTipo() + ": " + nombre + " (" + autor + ")";
    }
}