package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.ElementoCultural;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElementoCulturalDAO {
    private final static String SQL_ALL = "SELECT * FROM elemento_cultural";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM elemento_cultural WHERE id = ?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM elemento_cultural WHERE nombre = ?";

    private final static String SQL_INSERT = "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";
    private final static String SQL_UPDATE = "UPDATE elemento_cultural SET nombre = ?, descripcion = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM elemento_cultural WHERE id = ?";

    public static List<ElementoCultural> findAll() throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();
        ElementoCultural elementoCultural = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                elementoCultural = new ElementoCultural(id, nombre, descripcion) {
                    @Override
                    public String getTipo() {
                        return "";
                    }
                };
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
                int id2 =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                elementoCultural = new ElementoCultural(id2, nombre, descripcion) {
                    @Override
                    public String getTipo() {
                        return "";
                    }
                };
            }
        }
        return elementoCultural;
    }

    public static List<ElementoCultural> findByName(String nombre) throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();
        ElementoCultural elementoCultural = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id2 =  rs.getInt("id");
                String nombre2 = rs.getString("nombre");
                String descripcion2 = rs.getString("descripcion");
                elementoCultural = new ElementoCultural(id2, nombre2, descripcion2) {
                    @Override
                    public String getTipo() {
                        return "";
                    }
                };
                elementosCulturales.add(elementoCultural);
            }
        }
        return elementosCulturales;
    }

    public static ElementoCultural addElementoCultural(ElementoCultural elementoCultural) throws SQLException {
        if (elementoCultural == null && findByName(elementoCultural.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, elementoCultural.getNombre());
                ps.setString(2, elementoCultural.getDescripcion());
                ps.setInt(3, elementoCultural.getId());
                ps.executeUpdate();
            }
        }else {
            elementoCultural =  null;
        }
        return elementoCultural;
    }

    public static boolean updateElementoCultural(ElementoCultural elementoCulturalNuevo, ElementoCultural elementoCulturalActual) throws SQLException {
        boolean updated = false;
        if (elementoCulturalActual != null && elementoCulturalNuevo != null && findByName(elementoCulturalActual.getNombre()) == null && findByName(elementoCulturalNuevo.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, elementoCulturalActual.getNombre());
                ps.setString(2, elementoCulturalActual.getDescripcion());
                ps.setInt(3, elementoCulturalActual.getId());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteElementoCultural(int id) throws SQLException {
        boolean deleted = false;
        if (findById(id) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}