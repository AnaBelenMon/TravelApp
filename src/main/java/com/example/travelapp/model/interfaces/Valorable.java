package com.example.travelapp.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden ser valorados
 * dentro de la aplicación TravelApp.
 *
 * Se utiliza en clases que permiten asignar una puntuación numérica
 * (por ejemplo, actividades, alojamientos o viajes), facilitando así
 * la evaluación y comparación por parte del usuario.
 *
 * La interfaz establece la operación fundamental de asignar una valoración,
 * dejando a cada implementación la responsabilidad de validar y almacenar
 * dicha puntuación.
 */
public interface Valorable {

    /**
     * Asigna una valoración al elemento.
     * La puntuación debe estar dentro de un rango válido (normalmente de 1 a 5),
     * aunque la implementación concreta puede definir sus propias reglas.
     *
     * @param puntuacion valor numérico de la valoración
     * @throws IllegalArgumentException si la puntuación no es válida
     */
    void valorar(int puntuacion);
}
