package com.example.travelapp.dao;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD básicas.
 *
 * @param <T>  tipo de la entidad
 */
public interface GenericDAO <T> {
    List<T> findAll();

    T findById(int id);

    T add(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
