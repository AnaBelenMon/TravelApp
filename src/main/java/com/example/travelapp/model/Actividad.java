package com.example.travelapp.model;

import java.time.LocalDate;

/**
 * Clase que representa una actividad que se realiza en un viaje.
 * Contiene información sobre el nombre, categoría, fecha, precio, notas,
 * valoración, duración, si está reservada y el lugar de la actividad.
 *
 * Implementa la interfaz Valorable para permitir puntuar la actividad.
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class Actividad implements Valorable {
    private int idActividad;
    private int idViaje;
    private String nombre;
    private CategoriaActividad categoria;
    private LocalDate fecha;
    private double precio;
    private String notas;
    private int valoracion;
    private int duracionMinutos;
    private boolean reservada;
    private String lugar;

    /**
     * Constructor para crear una actividad vacía.
     */
    public Actividad() {}

    /**
     * Constructor para crear una actividad con los valores siguientes:
     * @param nombre es el nombre de la actividad.
     * @param categoria es la categoria de la actividad.
     * @param fecha es la fecha de la actividad.
     * @param precio es el precio de la actividad.
     * @param notas es las anotaciones que hace el usuario sobre la actividad.
     * @param duracionMinutos es la duración de la actividad en minutos.
     * @param reservada si una actividad esta reservada o no.
     * @param lugar es el lugar donde se realiza la actividad.
     */
    public Actividad(int idViaje, String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int duracionMinutos, boolean reservada, String lugar) {
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.precio = precio;
        this.notas = notas;
        this.duracionMinutos = duracionMinutos;
        this.reservada = reservada;
        this.lugar = lugar;
        this.valoracion = 0;
    }

    /**
     * Constructor para crear una actividad con los valores siguientes:
     * @param idActividad es la id de la actividad
     * @param idViaje es la id del viaje en el que se realiza la actividad.
     * @param nombre es el nombre de la actividad.
     * @param categoria es la categoria de la actividad.
     * @param fecha es la fecha de la actividad.
     * @param precio es el precio de la actividad.
     * @param notas es las anotaciones que hace el usuario sobre la actividad.
     * @param duracionMinutos es la duración de la actividad en minutos.
     * @param reservada si una actividad esta reservada o no.
     * @param lugar es el lugar donde se realiza la actividad.
     */
    public Actividad(int idActividad, int idViaje, String nombre, CategoriaActividad categoria, LocalDate fecha, double precio, String notas, int valoracion, int duracionMinutos, boolean reservada, String lugar) {
        this.idActividad = idActividad;
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.precio = precio;
        this.notas = notas;
        this.valoracion = valoracion;
        this.duracionMinutos = duracionMinutos;
        this.reservada = reservada;
        this.lugar = lugar;
    }

    /**
     * Método getter para obtener la id de la actividad. La id es un número entero positivo que identifica de forma única a cada actividad dentro de un viaje.
     * La id de la actividad es asignada por el sistema al crear una nueva actividad y no puede ser modificada posteriormente. Si se intenta establecer una id inválida (menor o igual a 0), se lanzará una excepción IllegalArgumentException.
     * La id de la actividad es utilizada internamente para gestionar las actividades dentro de un viaje y no debe ser expuesta directamente a los usuarios. En su lugar, se recomienda utilizar métodos específicos para acceder a la información de la actividad sin revelar su id.
     * @throws IllegalArgumentException si se intenta establecer una id de actividad inválida (menor o igual a 0).
     * @see #setIdActividad(int)
     * @return la id de la actividad.
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * Método para establecer la id de la actividad. La id es un número entero positivo que identifica de forma única a cada actividad dentro de un viaje.
     * La id de la actividad es asignada por el sistema al crear una nueva actividad y no puede ser modificada posteriormente. Si se intenta establecer
     * una id inválida (menor o igual a 0), se lanzará una excepción IllegalArgumentException.
     * La id de la actividad es utilizada internamente para gestionar las actividades dentro de un viaje y no debe ser expuesta directamente a los usuarios. En su lugar, se recomienda utilizar métodos específicos para acceder a la información de la actividad sin revelar su id.
     * @throws IllegalArgumentException si se intenta establecer una id de actividad inválida (menor o igual a 0).
     * @see #getIdActividad()
     * @param idActividad devuelve la id de la actividad
     */
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * Método getter para obtener la id del viaje. La id es un número entero positivo que identifica de forma única a cada viaje.
     * La id del viaje es asignada por el sistema al crear una nueva actividad y no puede ser modificada posteriormente. Si se intenta establecer una id inválida (menor o igual a 0), se lanzará una excepción IllegalArgumentException.
     * La id del viaje es utilizada internamente para gestionar las actividades dentro de un viaje y no debe ser expuesta directamente a los usuarios. En su lugar, se recomienda utilizar métodos específicos para acceder a la información de la actividad sin revelar su id de viaje.
     * @throws IllegalArgumentException si se intenta establecer una id de viaje inválida (menor o igual a 0).
     * @see #setIdViaje(int)
     * @return la id del viaje.
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * Método para establecer la id del viaje. La id es un número entero positivo que identifica de forma única a cada viaje.
     * La id del viaje es asignada por el sistema al crear una nueva actividad y no puede ser modificada posteriormente. Si se intenta establecer una id inválida (menor o igual a 0), se lanzará una excepción IllegalArgumentException.
     * La id del viaje es utilizada internamente para gestionar las actividades dentro de un viaje y no debe ser expuesta directamente a los usuarios. En su lugar, se recomienda utilizar métodos específicos para acceder a la información de la actividad sin revelar su id de viaje.
     * @throws IllegalArgumentException si se intenta establecer una id de viaje inválida (menor o igual a 0).
     * @see #setIdViaje(int)
     * @param idViaje devuelve la id del viaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * Método para obtener el nombre de la actividad. El nombre es una cadena de texto que describe la actividad y debe ser único dentro de un viaje. Si se intenta establecer un nombre vacío o nulo, se lanzará una excepción IllegalArgumentException.
     * El nombre de la actividad es utilizado para identificar y diferenciar las actividades dentro de un viaje, por lo que es importante que sea descriptivo y representativo de la actividad que se va a realizar.
     * @throws IllegalArgumentException si se intenta establecer un nombre vacío o nulo.
     * @see #setNombre(String)
     * @return devuelve el nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre de la actividad. El nombre es una cadena de texto que describe la actividad y debe ser único dentro de un viaje. Si se intenta establecer un nombre vacío o nulo, se lanzará una excepción IllegalArgumentException.
     * El nombre de la actividad es utilizado para identificar y diferenciar las actividades dentro de un viaje, por lo que es importante que sea descriptivo y representativo de la actividad que se va a realizar.
     * @throws IllegalArgumentException si se intenta establecer un nombre vacío o nulo.
     * @see #getNombre()
     * @param nombre devuelve el nombre de la actividad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener la categoria de la actividad. La categoría es un valor de que se obtiene del enum CategoriaActividad que clasifica la actividad en una de las siguientes categorías: CULTURAL, GASTRONOMICA, AVENTURA, RELAX, OCIO y NATURALEZA. Si se intenta establecer una categoría nula, se lanzará una excepción IllegalArgumentException.
     * La categoría de la actividad es utilizada para organizar y filtrar las actividades dentro de un viaje, permitiendo a los usuarios encontrar fácilmente las actividades que se ajusten a sus intereses y preferencias.
     * @throws IllegalArgumentException si se intenta establecer una categoría nula.
     * @see #setCategoria(CategoriaActividad)
     * @see CategoriaActividad
     * @return devuelve la categoria de la actividad.
     */
    public CategoriaActividad getCategoria() {
        return categoria;
    }

    /**
     * Método para establecer la categoria de la actividad. La categoría es un valor de que se obtiene del enum CategoriaActividad que clasifica la actividad en una de las siguientes categorías: CULTURAL, GASTRONOMICA, AVENTURA, RELAX, OCIO y NATURALEZA. Si se intenta establecer una categoría nula, se lanzará una excepción IllegalArgumentException.
     * La categoría de la actividad es utilizada para organizar y filtrar las actividades dentro de un viaje, permitiendo a los usuarios encontrar fácilmente las actividades que se ajusten a sus intereses y preferencias.
     * @throws IllegalArgumentException si se intenta establecer una categoría nula.
     * @see #getCategoria()
     * @see CategoriaActividad
     * @param categoria devuelve la categoria de la actividad.
     */
    public void setCategoria(CategoriaActividad categoria) {
        this.categoria = categoria;
    }

    /**
     * Método getter para obtener la fecha de la actividad. La fecha es un valor de tipo LocalDate que representa el día en que se realizará la actividad. Si se intenta establecer una fecha nula, se lanzará una excepción IllegalArgumentException.
     * La fecha de la actividad es utilizada para organizar y planificar las actividades dentro de un viaje, permitiendo a los usuarios visualizar y gestionar su itinerario de manera efectiva.
     * @throws IllegalArgumentException si se intenta establecer una fecha nula.
     * @see #setFecha(LocalDate)
     * @see LocalDate
     * @return devuelve la fecha en la que se ha realiza la actividad.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Método para establecer la fecha de la actividad. La fecha es un valor de tipo LocalDate que representa el día en que se realizará la actividad. Si se intenta establecer una fecha nula, se lanzará una excepción IllegalArgumentException.
     * La fecha de la actividad es utilizada para organizar y planificar las actividades dentro de un viaje, permitiendo a los usuarios visualizar y gestionar su itinerario de manera efectiva.
     * @throws IllegalArgumentException si se intenta establecer una fecha nula.
     * @see #getFecha()
     * @see LocalDate
     * @param fecha devuelve la fecha en la que se realiza la actividad.
     */
    public void setFecha(LocalDate fecha) {

        this.fecha = fecha;
    }

    /**
     * Método para obtener el precio de la actividad. El precio es un valor de tipo double que representa el costo de la actividad en euros. Si se intenta establecer un precio negativo, se lanzará una excepción IllegalArgumentException.
     * El precio de la actividad es utilizado para calcular el costo total del viaje y para ayudar a los usuarios a tomar decisiones informadas sobre qué actividades incluir en su itinerario en función de su presupuesto y preferencias.
     * @throws IllegalArgumentException si se intenta establecer un precio negativo.
     * @see #setPrecio(double)
     * @see #esGratuita()
     * @see #getPrecio()
     * @return devuelve el precio de la actividad.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método para establecer el precio de la actividad. El precio es un valor de tipo double que representa el costo de la actividad en euros. Si se intenta establecer un precio negativo, se lanzará una excepción IllegalArgumentException.
     * El precio de la actividad es utilizado para calcular el costo total del viaje y para ayudar a los usuarios a tomar decisiones informadas sobre qué actividades incluir en su itinerario en función de su presupuesto y preferencias.
     * @throws IllegalArgumentException si se intenta establecer un precio negativo.
     * @see #getPrecio()
     * @see #esGratuita()
     * @param precio devuelve el precio de la actividad.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método para obtener las anotaciones que el usuario hace sobre la actividad. Las notas son una cadena de texto que permite al usuario registrar información adicional sobre la actividad, como detalles logísticos, recomendaciones, experiencias personales o cualquier otro dato relevante que desee recordar sobre la actividad.
     * @return devuelve las notas sobre la actividad.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Método para establecer las anotaciones que el usuario hace sobre la actividad. Las notas son una cadena de texto que permite al usuario registrar información adicional sobre la actividad, como detalles logísticos, recomendaciones, experiencias personales o cualquier otro dato relevante que desee recordar sobre la actividad.
     * @param notas devuelve las notas sobre la actividad.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Método para obtener la valoración de la actividad. La valoración es un valor entero que representa la puntuación que el usuario asigna a la actividad después de realizarla, con un rango de 1 a 5, donde 1 es la peor valoración y 5 es la mejor valoración. Si la actividad no ha sido valorada, el valor de la valoración será 0.
     * @return devuelve la valoración de la actividad.
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Método para establecer la puntuacion que el usuario hace sobre la actividad. La valoración es un valor entero que representa la puntuación que el usuario asigna a la actividad después de realizarla, con un rango de 1 a 5, donde 1 es la peor valoración y 5 es la mejor valoración. Si se intenta establecer una valoración fuera del rango permitido (menor a 1 o mayor a 5), se lanzará una excepción IllegalArgumentException.
     * La valoración de la actividad es utilizada para proporcionar retroalimentación sobre la experiencia del usuario con la actividad, ayudando a otros usuarios a tomar decisiones informadas sobre qué actividades incluir en su itinerario en función de las valoraciones y opiniones de otros viajeros.
     * @throws IllegalArgumentException si se intenta establecer una valoración fuera del rango permitido (menor a 1 o mayor a 5).
     * @see #getValoracion()
     * @param valoracion devuelve la valoración de la actividad.
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Método para obtener la duración de la actividad en minutos. ¡
     * @return devuelve la duración de la actividad en minutos.
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Método para establecer la duración de la actividad en minutos. La duración es un valor entero que representa el tiempo estimado que se tarda en realizar la actividad, medido en minutos. Si se intenta establecer una duración negativa, se lanzará una excepción IllegalArgumentException.
     * La duración de la actividad es utilizada para ayudar a los usuarios a planificar su itinerario de manera efectiva, permitiéndoles asignar el tiempo adecuado para cada actividad y evitar solapamientos o tiempos insuficientes para disfrutar de las actividades que han seleccionado para su viaje.
     * @throws IllegalArgumentException si se intenta establecer una duración negativa.
     * @see #getDuracionMinutos()
     * @param duracionMinutos devuelve la duración de la actividad en minutos.
     */
    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Método para obtener si una actividad esta reservada o no. El valor de reservada es un booleano que indica si la actividad ha sido reservada o no por el usuario. Si el valor es true, significa que la actividad ha sido reservada, mientras que si el valor es false, significa que la actividad no ha sido reservada.
     * La información sobre si una actividad está reservada o no es importante para que los usuarios puedan gestionar su itinerario de manera efectiva, asegurándose de reservar las actividades que desean realizar durante su viaje y evitando conflictos o solapamientos en su planificación.
     * @return devuelve true si la actividad está reservada, o false si no lo está.
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     * Método para establecer si una actividad esta reservada o no. El valor de reservada es un booleano que indica si la actividad ha sido reservada o no por el usuario. Si el valor es true, significa que la actividad ha sido reservada, mientras que si el valor es false, significa que la actividad no ha sido reservada.
     * La información sobre si una actividad está reservada o no es importante para que los usuarios puedan gestionar su itinerario de manera efectiva, asegurándose de reservar las actividades que desean realizar durante su viaje y evitando conflictos o solapamientos en su planificación.
     * @see #isReservada()
     * @param reservada devuelve true si la actividad está reservada, o false si no lo está.
     */
    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * Método para obtener el lugar en el que se realiza la actividad. El lugar es una cadena de texto que describe el sitio o ubicación donde se llevará a cabo la actividad. Si se intenta establecer un lugar vacío o nulo, se lanzará una excepción IllegalArgumentException.
     * El lugar de la actividad es utilizado para proporcionar información adicional sobre la ubicación de la actividad, ayudando a los usuarios a planificar su itinerario de manera efectiva y a tener una mejor comprensión de dónde se llevará a cabo cada actividad durante su viaje.
     * @throws IllegalArgumentException si se intenta establecer un lugar vacío o nulo.
     * @see #setLugar(String)
     * @return devuelve el lugar de la actividad.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Método para establecer el lugar en el que se realiza la actividad. El lugar es una cadena de texto que describe el sitio o ubicación donde se llevará a cabo la actividad. Si se intenta establecer un lugar vacío o nulo, se lanzará una excepción IllegalArgumentException.
     * El lugar de la actividad es utilizado para proporcionar información adicional sobre la ubicación de la actividad, ayudando a los usuarios a planificar su itinerario de manera efectiva y a tener una mejor comprensión de dónde se llevará a cabo cada actividad durante su viaje.
     * @throws IllegalArgumentException si se intenta establecer un lugar vacío o nulo.
     * @see #getLugar()
     * @param lugar el lugar de la actividad. No puede estar vacío o ser nulo.
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Método para obtener la información de la actividad en formato de texto. El formato de la cadena devuelta es "nombre (fecha)", donde "nombre" es el nombre de la actividad y "fecha" es la fecha en la que se realizará la actividad, formateada como "dd/MM/yyyy". Esta representación proporciona una forma concisa y clara de identificar la actividad y su fecha asociada, facilitando a los usuarios la visualización y gestión de sus actividades dentro de un viaje.
     * @return devuelve la información de la actividad en formato de texto.
     */
    @Override
    public String toString() {
        return nombre + " (" + fecha + ")";
    }

    /**
     * Método implementado de la interfaz Valorable que sirve para establecer la puntuacion que el usuario hace sobre la actividad. La valoración es un valor entero que representa la puntuación que el usuario asigna a la actividad después de realizarla, con un rango de 1 a 5, donde 1 es la peor valoración y 5 es la mejor valoración. Si se intenta establecer una valoración fuera del rango permitido (menor a 1 o mayor a 5), se lanzará una excepción IllegalArgumentException.
     * @param puntuacion valor entre 1 y 5
     */
    @Override
    public void valorar(int puntuacion) {
        if (puntuacion < 1 || puntuacion > 5){
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5");
        }
        this.valoracion = puntuacion;
    }

    /**
     * Método implementado de la interfaz Valorable que sirve para limpiar la valoración de la actividad, estableciendo su valor a 0. Esto indica que la actividad no ha sido valorada o que se ha restablecido su valoración a un estado inicial sin puntuación.
     */
    @Override
    public void limpiarValoracion() {
        this.valoracion = 0;
    }
    /**
     * Método para indicar si la actividad es gratuita o no. Una actividad se considera gratuita si su precio es igual a 0 euros. Este método devuelve true si el precio de la actividad es 0, lo que significa que no tiene costo asociado, y devuelve false si el precio es mayor que 0, indicando que la actividad tiene un costo asociado.
     * La información sobre si una actividad es gratuita o no es útil para que los usuarios puedan tomar decisiones informadas sobre qué actividades incluir en su itinerario en función de su presupuesto y preferencias, permitiéndoles identificar fácilmente las actividades que pueden disfrutar sin incurrir en gastos adicionales durante su viaje.
     * @see #getPrecio()
     * @see #setPrecio(double)
     * @throws IllegalArgumentException si se intenta establecer un precio negativo.
     * @see #setPrecio(double)
     * @return devuelve true si la actividad es gratuita (precio igual a 0), o false si la actividad tiene un costo asociado (precio mayor que 0).
     */
    public boolean esGratuita() {
        return precio == 0;
    }
}