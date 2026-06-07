package com.example.travelapp.model.enums;

/**
 * Enum que representa los diferentes tipos de viaje disponibles en la aplicación.
 * Cada tipo incluye un nombre legible para mostrar en la interfaz de usuario.
 * Este enum permite clasificar los viajes según su propósito o experiencia,
 * facilitando su organización, filtrado y análisis dentro de la aplicación TravelApp.
 * Se utiliza directamente en la clase {@link com.example.travelapp.model.Viaje}.
 * Incluye métodos utilitarios para conversión desde texto y obtención de nombres.
 * Ejemplos de uso:
 * - Filtrar viajes por estilo (aventura, relax, cultural…)
 * - Mostrar categorías en ComboBox o listas
 * - Guardar y cargar tipos de viaje desde la base de datos
 *
 * @author Ana
 * @version 1.0
 * @since 2026-04-30
 */
public enum TipoViaje {

    /** Viaje orientado a actividades intensas, exploración y experiencias dinámicas. */
    AVENTURA("Aventura"),

    /** Viaje centrado en descanso, tranquilidad y desconexión. */
    RELAX("Relax"),

    /** Viaje enfocado en cultura, historia, arte y aprendizaje. */
    CULTURAL("Cultural"),

    /** Viaje relacionado con trabajo, reuniones o eventos profesionales. */
    NEGOCIOS("Negocios"),

    /** Viaje con enfoque romántico o en pareja. */
    ROMANTICO("Romántico"),

    /** Viaje pensado para realizar en familia. */
    FAMILIAR("Familiar"),

    /** Cualquier otro tipo de viaje no contemplado en las categorías anteriores. */
    OTROS("Otros");

    private final String nombre;

    /**
     * Constructor del enum que asigna un nombre legible al tipo de viaje.
     *
     * @param nombre nombre visible para el usuario
     */
    TipoViaje(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre legible del tipo de viaje.
     *
     * @return nombre del tipo de viaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la representación en texto del tipo de viaje,
     * utilizada en interfaces gráficas y listados.
     *
     * @return nombre legible del tipo
     */
    @Override
    public String toString() {
        return nombre;
    }
}