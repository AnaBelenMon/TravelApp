package com.example.travelapp.model;

/**
 * Implementación simple de {@link ElementoCultural}.
 *
 * Representa un elemento cultural genérico sin especialización adicional.
 * Se utiliza cuando no se requiere diferenciar entre tipos más específicos
 * como museos, monumentos, etc.
 *
 * Esta clase concreta hereda los atributos básicos de un elemento cultural
 * y define un tipo genérico.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public class ElementoCulturalSimple extends ElementoCultural {

    /**
     * Constructor del elemento cultural simple.
     *
     * @param id identificador del elemento
     * @param nombre nombre del elemento cultural
     * @param descripcion descripción del elemento cultural
     */
    public ElementoCulturalSimple(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    /**
     * Devuelve el tipo de elemento cultural.
     *
     * @return tipo genérico del elemento
     */
    @Override
    public String getTipo() {
        return "Elemento cultural";
    }
}