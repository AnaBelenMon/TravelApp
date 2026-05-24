package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Museo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar el acceso a datos de la entidad Museo.
 *
 * Esta clase trabaja con una relación entre:
 * - elemento_cultural (tabla padre)
 * - museo (tabla hija)
 *
 * Se encarga de realizar operaciones CRUD y consultas combinadas
 * entre ambas tablas garantizando consistencia de datos.
 */
public class MuseoDAO {

    // =========================
    // CONSULTAS SQL
    // =========================

    /** Obtiene todos los museos con sus datos culturales asociados */
    private final static String SQL_ALL =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo";

    /** Busca un museo por su ID */
    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo " +
                    "WHERE ec.idElemento = ?";

    /** Busca museos por nombre */
    private final static String SQL_FIND_BY_NOMBRE =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo " +
                    "WHERE ec.nombre = ?";

    /** Inserta datos en la tabla padre (elemento cultural) */
    private final static String SQL_INSERT_ELEMENTO =
            "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";

    /** Inserta datos específicos del museo */
    private final static String SQL_INSERT_MUSEO =
            "INSERT INTO museo (idMuseo, ciudad, pais, precioEntrada, horario, webOficial) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    /** Actualiza la tabla padre */
    private final static String SQL_UPDATE_ELEMENTO =
            "UPDATE elemento_cultural SET nombre=?, descripcion=? WHERE idElemento=?";

    /** Actualiza los datos específicos del museo */
    private final static String SQL_UPDATE_MUSEO =
            "UPDATE museo SET ciudad=?, pais=?, precioEntrada=?, horario=?, webOficial=? " +
                    "WHERE idMuseo=?";

    /** Elimina el registro de la tabla museo */
    private final static String SQL_DELETE_MUSEO =
            "DELETE FROM museo WHERE idMuseo=?";

    /** Elimina el registro de la tabla elemento_cultural */
    private final static String SQL_DELETE_ELEMENTO =
            "DELETE FROM elemento_cultural WHERE idElemento=?";


    // =========================
    // CONSULTAS (SELECT)
    // =========================

    /**
     * Obtiene todos los museos registrados en la base de datos.
     *
     * @return lista de museos
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static List<Museo> findAll() throws SQLException {
        List<Museo> museos = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                museos.add(map(rs));
            }
        }
        return museos;
    }


    /**
     * Busca un museo por su identificador.
     *
     * @param id identificador del museo
     * @return museo encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static Museo findById(int id) throws SQLException {
        Museo museo = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    museo = map(rs);
                }
            }
        }
        return museo;
    }


    /**
     * Busca museos por nombre.
     *
     * @param nombre nombre del museo
     * @return lista de museos que coinciden
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static List<Museo> findByNombre(String nombre) throws SQLException {
        List<Museo> museos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    museos.add(map(rs));
                }
            }
        }
        return museos;
    }


    // =========================
    // INSERT (TRANSACCIONAL)
    // =========================

    /**
     * Inserta un museo en ambas tablas relacionadas:
     * - elemento_cultural (padre)
     * - museo (hijo)
     *
     * Se realiza dentro de una transacción para garantizar consistencia.
     *
     * @param museo objeto a insertar
     * @return true si la operación fue exitosa
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean insert(Museo museo) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Inserción en tabla padre
            PreparedStatement psPadre = conn.prepareStatement(SQL_INSERT_ELEMENTO);
            psPadre.setString(1, museo.getNombre());
            psPadre.setString(2, museo.getDescripcion());
            psPadre.executeUpdate();

            // Recuperación manual del ID generado
            PreparedStatement psSelect = conn.prepareStatement(
                    "SELECT idElemento FROM elemento_cultural " +
                            "WHERE nombre = ? AND descripcion = ? " +
                            "ORDER BY idElemento DESC LIMIT 1"
            );

            psSelect.setString(1, museo.getNombre());
            psSelect.setString(2, museo.getDescripcion());

            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) {
                throw new SQLException("No se pudo recuperar el ID generado");
            }

            int idGenerado = rs.getInt(1);
            museo.setId(idGenerado);

            // Inserción en tabla hija
            PreparedStatement psHijo = conn.prepareStatement(SQL_INSERT_MUSEO);
            psHijo.setInt(1, idGenerado);
            psHijo.setString(2, museo.getCiudad());
            psHijo.setString(3, museo.getPais());
            psHijo.setDouble(4, museo.getPrecioEntrada());
            psHijo.setString(5, museo.getHorario());
            psHijo.setString(6, museo.getWebOficial());
            psHijo.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }


    // =========================
    // UPDATE
    // =========================

    /**
     * Actualiza los datos de un museo en ambas tablas relacionadas.
     *
     * @param museo objeto con los datos actualizados
     * @return true si se actualizó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean update(Museo museo) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Actualización tabla padre
            PreparedStatement psPadre = conn.prepareStatement(SQL_UPDATE_ELEMENTO);
            psPadre.setString(1, museo.getNombre());
            psPadre.setString(2, museo.getDescripcion());
            psPadre.setInt(3, museo.getId());
            psPadre.executeUpdate();

            // Actualización tabla hija
            PreparedStatement psHijo = conn.prepareStatement(SQL_UPDATE_MUSEO);
            psHijo.setString(1, museo.getCiudad());
            psHijo.setString(2, museo.getPais());
            psHijo.setDouble(3, museo.getPrecioEntrada());
            psHijo.setString(4, museo.getHorario());
            psHijo.setString(5, museo.getWebOficial());
            psHijo.setInt(6, museo.getId());
            psHijo.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }


    // =========================
    // DELETE
    // =========================

    /**
     * Elimina un museo y su elemento cultural asociado.
     *
     * La eliminación se realiza en transacción para evitar inconsistencias.
     *
     * @param id identificador del museo
     * @return true si se eliminó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean delete(int id) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Eliminación en tabla hija
            PreparedStatement psHijo = conn.prepareStatement(SQL_DELETE_MUSEO);
            psHijo.setInt(1, id);
            psHijo.executeUpdate();

            // Eliminación en tabla padre
            PreparedStatement psPadre = conn.prepareStatement(SQL_DELETE_ELEMENTO);
            psPadre.setInt(1, id);
            psPadre.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }


    // =========================
    // MAPPER
    // =========================

    /**
     * Convierte un ResultSet en un objeto Museo.
     *
     * @param rs resultado de la consulta SQL
     * @return objeto Museo construido
     * @throws SQLException si ocurre un error al leer datos
     */
    private static Museo map(ResultSet rs) throws SQLException {
        return new Museo(
                rs.getInt("idElemento"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("ciudad"),
                rs.getString("pais"),
                rs.getDouble("precioEntrada"),
                rs.getString("horario"),
                rs.getString("webOficial")
        );
    }
}