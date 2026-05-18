package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Obra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObraDAO {
    private final static String SQL_ALL = "SELECT * FROM obra";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM obra WHERE id=?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM obra WHERE nombre=?";
    private final static String SQL_FIND_AUTOR = "SELECT * FROM obra WHERE autor=?";
    private final static String SQL_FIND_BY_ESTILO = "SELECT * FROM obra WHERE estilo=?";

    private final static String SQL_INSERT = "INSERT INTO obra VALUES (?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE obra SET nombre=? WHERE id=?";
    private final static String SQL_DELETE = "DELETE FROM obra WHERE id=?";

    public static List<Obra> findAll() throws  SQLException {
        List<Obra> obras = new ArrayList<>();
        Obra obra = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)){
            while (rs.next()) {
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor = rs.getString("autor");
                String estilo = rs.getString("estilo");
                obra = new Obra(id, nombre, descripcion, autor, estilo);
                obras.add(obra);
            }
        }
        return obras;
    }

    public static Obra findById(int id) throws SQLException {
        Obra obra = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id2 =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor = rs.getString("autor");
                String estilo = rs.getString("estilo");
                obra = new Obra(id2, nombre, descripcion, autor, estilo);
            }
        }
        return obra;
    }

    public static List<Obra> findByNombre(String nombre) throws SQLException {
        Obra obra = null;
        List<Obra> obras = new ArrayList<>();
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("id");
                String nombre2 = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor = rs.getString("autor");
                String estilo = rs.getString("estilo");
                obra = new Obra(id, nombre2, descripcion, autor, estilo);
                obras.add(obra);
            }
        }
        return obras;
    }

    public static List<Obra> findByAutor(String autor) throws SQLException {
        Obra obra = null;
        List<Obra> obras = new ArrayList<>();
        try (PreparedStatement ps =  ConnectionBD.getConnection().prepareStatement(SQL_FIND_AUTOR)){
            ps.setString(1, autor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor2 = rs.getString("autor");
                String estilo = rs.getString("estilo");
                obra = new Obra(id, nombre, descripcion, autor, estilo);
                obras.add(obra);
            }
        }
        return obras;
    }

    public static List<Obra> findByEstilo(String estilo) throws SQLException{
        Obra obra = null;
        List<Obra> obras = new ArrayList<>();
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ESTILO)){
            ps.setString(1, estilo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor = rs.getString("autor");
                String estilo2 = rs.getString("estilo");
                obra = new Obra(id, nombre, descripcion, autor, estilo);
                obras.add(obra);
            }
        }
        return obras;
    }

    public static Obra addObra(Obra obra) throws SQLException {
        if (obra != null && findByNombre(obra.getNombre()) == null){
            try (PreparedStatement ps= ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, obra.getNombre());
                ps.setString(2, obra.getDescripcion());
                ps.setString(3, obra.getAutor());
                ps.setString(4, obra.getEstilo());
                ps.executeUpdate();
            }
        }
        return obra;
    }

    public static boolean updateObra(Obra obraNueva, Obra obraActual) throws SQLException {
        boolean update = false;
        if((obraActual!=null)&&(obraNueva!=null)&&findByNombre(obraActual.getNombre())!=null && findByNombre(obraNueva.getNombre())==null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, obraNueva.getNombre());
                ps.setString(2, obraNueva.getDescripcion());
                ps.setString(3, obraNueva.getAutor());
                ps.setString(4, obraNueva.getEstilo());
                ps.setInt(5, obraActual.getId());
                ps.executeUpdate();
                update = true;
            }
        }
        return update;
    }

    public static boolean deleteObra(int idObra) throws SQLException {
        boolean deleted = false;
        if (findById(idObra) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idObra);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}
