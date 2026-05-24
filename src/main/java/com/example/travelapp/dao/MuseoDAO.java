package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Museo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MuseoDAO {

    private final static String SQL_ALL =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo " +
                    "WHERE ec.idElemento = ?";

    private final static String SQL_FIND_BY_NOMBRE =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN museo m ON ec.idElemento = m.idMuseo " +
                    "WHERE ec.nombre = ?";

    private final static String SQL_INSERT_ELEMENTO =
            "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";

    private final static String SQL_INSERT_MUSEO =
            "INSERT INTO museo (idMuseo, ciudad, pais, precioEntrada, horario, webOficial) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE_ELEMENTO =
            "UPDATE elemento_cultural SET nombre=?, descripcion=? WHERE idElemento=?";

    private final static String SQL_UPDATE_MUSEO =
            "UPDATE museo SET ciudad=?, pais=?, precioEntrada=?, horario=?, webOficial=? " +
                    "WHERE idMuseo=?";

    private final static String SQL_DELETE_MUSEO =
            "DELETE FROM museo WHERE idMuseo=?";

    private final static String SQL_DELETE_ELEMENTO =
            "DELETE FROM elemento_cultural WHERE idElemento=?";


    // ---------------------------------------------------------
    // SELECT ALL
    // ---------------------------------------------------------
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


    // ---------------------------------------------------------
    // SELECT BY ID
    // ---------------------------------------------------------
    public static Museo findById(int id) throws SQLException {
        Museo museo = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                museo = map(rs);
            }
        }
        return museo;
    }


    // ---------------------------------------------------------
    // SELECT BY NOMBRE
    // ---------------------------------------------------------
    public static List<Museo> findByNombre(String nombre) throws SQLException {
        List<Museo> museos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                museos.add(map(rs));
            }
        }
        return museos;
    }


    // ---------------------------------------------------------
    // INSERT (SIN getGeneratedKeys)
    // ---------------------------------------------------------
    public static boolean insert(Museo museo) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // 1. Insertar en elemento_cultural
            PreparedStatement psPadre = conn.prepareStatement(SQL_INSERT_ELEMENTO);
            psPadre.setString(1, museo.getNombre());
            psPadre.setString(2, museo.getDescripcion());
            psPadre.executeUpdate();

            // 2. Recuperar ID generado SIN getGeneratedKeys()
            PreparedStatement psSelect = conn.prepareStatement(
                    "SELECT idElemento FROM elemento_cultural " +
                            "WHERE nombre = ? AND descripcion = ? " +
                            "ORDER BY idElemento DESC LIMIT 1"
            );
            psSelect.setString(1, museo.getNombre());
            psSelect.setString(2, museo.getDescripcion());
            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) throw new SQLException("No se pudo recuperar el ID generado");
            int idGenerado = rs.getInt(1);
            museo.setId(idGenerado);

            // 3. Insertar en museo
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


    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    public static boolean update(Museo museo) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Actualizar elemento_cultural
            PreparedStatement psPadre = conn.prepareStatement(SQL_UPDATE_ELEMENTO);
            psPadre.setString(1, museo.getNombre());
            psPadre.setString(2, museo.getDescripcion());
            psPadre.setInt(3, museo.getId());
            psPadre.executeUpdate();

            // Actualizar museo
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


    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    public static boolean delete(int id) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Borrar en museo
            PreparedStatement psHijo = conn.prepareStatement(SQL_DELETE_MUSEO);
            psHijo.setInt(1, id);
            psHijo.executeUpdate();

            // Borrar en elemento_cultural
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


    // ---------------------------------------------------------
    // MAPEO
    // ---------------------------------------------------------
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