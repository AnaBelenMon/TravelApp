package com.example.travelapp.model;

/**
 * Enum que representa los diferentes tipos de viaje disponibles en la aplicación.
 * Se utiliza para clasificar los viajes según su propósito o experiencia.
 */
public enum TipoViaje {

    AVENTURA,
    RELAX,
    CULTURAL,
    NEGOCIOS,
    ROMANTICO,
    FAMILIAR;

    /**
     * Devuelve una versión legible del tipo de viaje para la interfaz de usuario.
     *
     * @return nombre formateado del tipo de viaje
     */
    public String getNombre() {
        return switch (this) {
            case AVENTURA -> "Aventura";
            case RELAX -> "Relax";
            case CULTURAL -> "Cultural";
            case NEGOCIOS -> "Negocios";
            case ROMANTICO -> "Romántico";
            case FAMILIAR -> "Familiar";
        };
    }
}