package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Obra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObraDAO {

    private final static String SQL_ALL =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra " +
                    "WHERE ec.idElemento = ?";

    private final static String SQL_FIND_BY_NOMBRE =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra " +
                    "WHERE ec.nombre = ?";

    private final static String SQL_INSERT_ELEMENTO =
            "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";

    private final static String SQL_INSERT_OBRA =
            "INSERT INTO obra (idObra, autor, estilo) VALUES (?, ?, ?)";

    private final static String SQL_UPDATE_ELEMENTO =
            "UPDATE elemento_cultural SET nombre=?, descripcion=? WHERE idElemento=?";

    private final static String SQL_UPDATE_OBRA =
            "UPDATE obra SET autor=?, estilo=? WHERE idObra=?";

    private final static String SQL_DELETE_OBRA =
            "DELETE FROM obra WHERE idObra=?";

    private final static String SQL_DELETE_ELEMENTO =
            "DELETE FROM elemento_cultural WHERE idElemento=?";


    // ---------------------------------------------------------
    // SELECT ALL
    // ---------------------------------------------------------
    public static List<Obra> findAll() throws SQLException {
        List<Obra> obras = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                obras.add(map(rs));
            }
        }
        return obras;
    }


    // ---------------------------------------------------------
    // SELECT BY ID
    // ---------------------------------------------------------
    public static Obra findById(int id) throws SQLException {
        Obra obra = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                obra = map(rs);
            }
        }
        return obra;
    }


    // ---------------------------------------------------------
    // SELECT BY NOMBRE
    // ---------------------------------------------------------
    public static List<Obra> findByNombre(String nombre) throws SQLException {
        List<Obra> obras = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obras.add(map(rs));
            }
        }
        return obras;
    }


    // ---------------------------------------------------------
    // INSERT (SIN getGeneratedKeys)
    // ---------------------------------------------------------
    public static boolean insert(Obra obra) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // 1. Insertar en elemento_cultural
            PreparedStatement psPadre = conn.prepareStatement(SQL_INSERT_ELEMENTO);
            psPadre.setString(1, obra.getNombre());
            psPadre.setString(2, obra.getDescripcion());
            psPadre.executeUpdate();

            // 2. Recuperar ID generado SIN getGeneratedKeys()
            PreparedStatement psSelect = conn.prepareStatement(
                    "SELECT idElemento FROM elemento_cultural " +
                            "WHERE nombre = ? AND descripcion = ? " +
                            "ORDER BY idElemento DESC LIMIT 1"
            );
            psSelect.setString(1, obra.getNombre());
            psSelect.setString(2, obra.getDescripcion());
            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) throw new SQLException("No se pudo recuperar el ID generado");
            int idGenerado = rs.getInt(1);
            obra.setId(idGenerado);

            // 3. Insertar en obra
            PreparedStatement psHijo = conn.prepareStatement(SQL_INSERT_OBRA);
            psHijo.setInt(1, idGenerado);
            psHijo.setString(2, obra.getAutor());
            psHijo.setString(3, obra.getEstilo());
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
    public static boolean update(Obra obra) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Actualizar elemento_cultural
            PreparedStatement psPadre = conn.prepareStatement(SQL_UPDATE_ELEMENTO);
            psPadre.setString(1, obra.getNombre());
            psPadre.setString(2, obra.getDescripcion());
            psPadre.setInt(3, obra.getId());
            psPadre.executeUpdate();

            // Actualizar obra
            PreparedStatement psHijo = conn.prepareStatement(SQL_UPDATE_OBRA);
            psHijo.setString(1, obra.getAutor());
            psHijo.setString(2, obra.getEstilo());
            psHijo.setInt(3, obra.getId());
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
            // Borrar en obra
            PreparedStatement psHijo = conn.prepareStatement(SQL_DELETE_OBRA);
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
    private static Obra map(ResultSet rs) throws SQLException {
        return new Obra(
                rs.getInt("idElemento"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("autor"),
                rs.getString("estilo")
        );
    }
}