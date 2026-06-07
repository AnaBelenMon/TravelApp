package com.example.travelapp.dao;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones CRUD básicas para cualquier entidad
 * del modelo. Permite estandarizar el acceso a datos y reutilizar la lógica
 * en todos los DAO de la aplicación.
 *
 * <p>Las clases DAO que implementen esta interfaz deben encargarse de:</p>
 * <ul>
 *     <li>Mapear los registros de la base de datos a objetos del modelo.</li>
 *     <li>Ejecutar consultas SQL seguras mediante JDBC.</li>
 *     <li>Gestionar correctamente excepciones y recursos.</li>
 * </ul>
 *
 * @param <T> tipo de la entidad gestionada por el DAO
 */
public interface GenericDAO<T> {
    /**
     * Obtiene todos los registros de la entidad.
     *
     * @return lista completa de entidades
     */
    List<T> findAll();

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id identificador de la entidad
     * @return entidad encontrada o null si no existe
     */
    T findById(int id);

    /**
     * Inserta una nueva entidad en la base de datos.
     *
     * @param entity entidad a insertar
     * @return entidad insertada (posiblemente con ID generado)
     */
    T add(T entity);

    /**
     * Actualiza una entidad existente.
     *
     * @param entity entidad con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    boolean update(T entity);

    /**
     * Elimina una entidad de la base de datos.
     *
     * @param entity entidad a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    boolean delete(T entity);
}
