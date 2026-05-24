package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.ElementoCultural;
import com.example.travelapp.model.ElementoCulturalSimple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElementoCulturalDAO {
    private final static String SQL_ALL = "SELECT * FROM elemento_cultural";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM elemento_cultural WHERE id = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM elemento_cultural WHERE nombre = ?";

    private final static String SQL_INSERT = "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";
    private final static String SQL_UPDATE = "UPDATE elemento_cultural SET nombre = ?, descripcion = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM elemento_cultural WHERE id = ?";

    public static List<ElementoCultural> findAll() throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();
        ElementoCultural elementoCultural = null;
        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                elementoCultural = new ElementoCulturalSimple(id, nombre, descripcion);
                elementosCulturales.add(elementoCultural);
            }
        }
        return elementosCulturales;
    }

    public static ElementoCultural findById(int id) throws SQLException {
        ElementoCultural elementoCultural = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id2 = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                elementoCultural = new ElementoCulturalSimple(id2, nombre, descripcion);
            }
            return elementoCultural;
        }
    }

    public static List<ElementoCultural> findByName(String nombre) throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();
        ElementoCultural elementoCultural = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre2 = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                elementoCultural = new ElementoCulturalSimple(id, nombre2, descripcion);
                elementosCulturales.add(elementoCultural);
            }
        }
        return elementosCulturales;
    }

    public static boolean insert(ElementoCultural elemento) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setString(1, elemento.getNombre());
            ps.setString(2, elemento.getDescripcion());
            return ps.executeUpdate() > 0;
        }
    }

    public static boolean update(ElementoCultural elemento) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setString(1, elemento.getNombre());
            ps.setString(2, elemento.getDescripcion());
            ps.setInt(3, elemento.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public static boolean delete(int id) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}