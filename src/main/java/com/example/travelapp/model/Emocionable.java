package com.example.travelapp.model;

/**
 * Interfaz que define la capacidad de asignar y gestionar emociones
 * en distintos elementos de la aplicación.
 *
 * Las clases que implementen esta interfaz podrán asociar una emoción
 * (definida en {@link Emocion}) y también eliminarla cuando sea necesario.
 *
 * Se utiliza para enriquecer la experiencia del usuario permitiendo
 * expresar estados emocionales asociados a viajes, actividades o recuerdos.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-04-30
 */
public interface Emocionable {

    /**
     * Asigna una emoción al elemento.
     *
     * @param emocion emoción que se desea asignar
     */
    void asignarEmocion(Emocion emocion);

    /**
     * Elimina la emoción asignada al elemento, si existe.
     */
    void limpiarEmocion();
}