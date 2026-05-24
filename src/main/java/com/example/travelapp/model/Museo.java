package com.example.travelapp.model;

import java.util.Objects;

/**
 * Representa un museo dentro del sistema de elementos culturales.
 *
 * Hereda de {@link ElementoCultural} y añade información específica
 * como ubicación, precio de entrada, horario y web oficial.
 *
 * Permite representar museos turísticos dentro de la aplicación de viajes.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Museo extends ElementoCultural {
    private String ciudad;
    private String pais;
    private double precioEntrada;
    private String horario;
    private String webOficial;

    /**
     * Constructor sin identificador.
     */
    public Museo(String nombre, String descripcion, String ciudad, String pais,
                 double precioEntrada, String horario, String webOficial) {

        super(nombre, descripcion);
        this.ciudad = ciudad;
        this.pais = pais;
        this.precioEntrada = precioEntrada;
        this.horario = horario;
        this.webOficial = webOficial;
    }

    /**
     * Constructor completo con identificador.
     */
    public Museo(int id, String nombre, String descripcion, String ciudad,
                 String pais, double precioEntrada, String horario,
                 String webOficial) {

        super(id, nombre, descripcion);
        this.ciudad = ciudad;
        this.pais = pais;
        this.precioEntrada = precioEntrada;
        this.horario = horario;
        this.webOficial = webOficial;
    }

    /**
     * Devuelve el tipo de elemento cultural.
     *
     * @return "Museo"
     */
    @Override
    public String getTipo() {
        return "Museo";
    }

    /**
     * Obtiene la ciudad del museo.
     *
     * @return ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad del museo.
     *
     * @param ciudad ciudad donde se encuentra
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el país del museo.
     *
     * @return país
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del museo.
     *
     * @param pais país donde se encuentra
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene el precio de entrada del museo.
     *
     * @return precio en euros
     */
    public double getPrecioEntrada() {
        return precioEntrada;
    }

    /**
     * Establece el precio de entrada del museo.
     *
     * @param precioEntrada precio de entrada
     */
    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    /**
     * Obtiene el horario del museo.
     *
     * @return horario de apertura
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Establece el horario del museo.
     *
     * @param horario horario de apertura
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Obtiene la web oficial del museo.
     *
     * @return URL oficial
     */
    public String getWebOficial() {
        return webOficial;
    }

    /**
     * Establece la web oficial del museo.
     *
     * @param webOficial URL oficial
     */
    public void setWebOficial(String webOficial) {
        this.webOficial = webOficial;
    }

    /**
     * Compara museos por su identificador.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Museo)) return false;
        Museo museo = (Museo) o;
        return id == museo.id;
    }

    /**
     * Genera hash basado en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Representación en texto del museo.
     *
     * @return nombre del museo
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Indica si el museo es gratuito.
     *
     * @return true si no tiene coste
     */
    public boolean esGratuito() {
        return precioEntrada == 0;
    }

    /**
     * Comprueba si el museo pertenece a un país.
     *
     * @param pais país a comparar
     * @return true si coincide
     */
    public boolean esDePais(String pais) {
        return this.pais != null && this.pais.equalsIgnoreCase(pais);
    }

    /**
     * Devuelve la ubicación completa del museo.
     *
     * @return ciudad y país
     */
    public String getUbicacion() {
        return ciudad + ", " + pais;
    }
}