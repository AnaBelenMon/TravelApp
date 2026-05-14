package com.example.travelapp.model;

public enum TipoViaje {
    AVENTURA,
    RELAX,
    CULTURAL,
    NEGOCIOS,
    ROMANTICO,
    FAMILIAR;

    public String getNombreBonito() {
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

