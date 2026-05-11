package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Museo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuseoDAO {
    private final static String SQL_ALL = "SELECT * FROM museo";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM museo WHERE id_museo = ?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM museo WHERE name_museo = ?";
    private final static String SQL_FIND_BY_CIUDAD = "SELECT * FROM museo WHERE ciudad_museo = ?";
    private final static String SQL_FIND_BY_PAIS = "SELECT * FROM museo WHERE pais_museo = ?";
    private final static String SQL_FIND_BY_PRECIOENTRADA = "SELECT * FROM museo WHERE precioentrada = ?";
    private final static String SQL_FIND_BY_HORARIO = "SELECT * FROM museo WHERE horario = ?";

    private final static String SQL_INSERT = "INSERT INTO Museo values (?,?,?,?,?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE Museo SET idMuseo = ?";
    private final static String SQL_DELETE = "DELETE FROM Museo WHERE idMuseo = ?";

    public static List<Museo> findAll() throws SQLException {
        List<Museo> museos = new ArrayList<>();
        Museo museo = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)){
            while (rs.next()) {
                int idMuseo = rs.getInt("idMuseo");
                String nameMuseo = rs.getString("nameMuseo");
                String descriptionMuseo = rs.getString("descriptionMuseo");
                String ciudadMuseo = rs.getString("ciudadMuseo");
                String paisMuseo = rs.getString("paisMuseo");
                double precioEntrada = rs.getDouble("precioEntrada");
                String horarioMuseo = rs.getString("horarioMuseo");
                String webOficialMuseo = rs.getString("webOficialMuseo");
                museo = new Museo(idMuseo,nameMuseo,descriptionMuseo,ciudadMuseo,paisMuseo,precioEntrada, horarioMuseo,webOficialMuseo);
                museos.add(museo);
            }
        }
        return museos;
    }

    public static Museo  findById(int idMuseo) throws SQLException {
        Museo museo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1, idMuseo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMuseo2 = rs.getInt("idMuseo");
                String nameMuseo = rs.getString("nameMuseo");
                String descriptionMuseo = rs.getString("descriptionMuseo");
                String ciudadMuseo = rs.getString("ciudadMuseo");
                String paisMuseo = rs.getString("paisMuseo");
                double precioEntrada = rs.getDouble("precioEntrada");
                String horarioMuseo = rs.getString("horarioMuseo");
                String webOficialMuseo = rs.getString("webOficialMuseo");
                museo = new Museo(idMuseo2,nameMuseo,descriptionMuseo,ciudadMuseo,paisMuseo,precioEntrada, horarioMuseo,webOficialMuseo);
            }
        }
        return museo;
    }

    public static List<Museo> findByName(String nameMuseo) throws SQLException {
        List<Museo> museos = new ArrayList<>();
        Museo museo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, nameMuseo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMuseo = rs.getInt("idMuseo");
                String nameMuseo2 = rs.getString("nameMuseo");
                String descriptionMuseo = rs.getString("descriptionMuseo");
                String ciudadMuseo = rs.getString("ciudadMuseo");
                String paisMuseo = rs.getString("paisMuseo");
                double precioEntrada = rs.getDouble("precioEntrada");
                String horarioMuseo = rs.getString("horarioMuseo");
                String webOficialMuseo = rs.getString("webOficialMuseo");
                museo = new Museo(idMuseo, nameMuseo2, descriptionMuseo, ciudadMuseo, paisMuseo,precioEntrada, horarioMuseo,webOficialMuseo);
                museos.add(museo);
            }
        }
        return museos;
    }

    public static List<Museo> findByCiudad(String ciudadMuseo) throws SQLException {
        List<Museo> museos = new ArrayList<>();
        Museo museo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CIUDAD)){
            ps.setString(1, ciudadMuseo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMuseo = rs.getInt("idMuseo");
                String nameMuseo = rs.getString("nameMuseo");
                String descriptionMuseo = rs.getString("descriptionMuseo");
                String ciudadMuseo2 = rs.getString("ciudadMuseo");
                String paisMuseo = rs.getString("paisMuseo");
                double precioEntrada = rs.getDouble("precioEntrada");
                String horarioMuseo = rs.getString("horarioMuseo");
                String webOficialMuseo = rs.getString("webOficialMuseo");
                museo = new Museo(idMuseo,nameMuseo,descriptionMuseo,ciudadMuseo2,paisMuseo,precioEntrada, horarioMuseo,webOficialMuseo);
                museos.add(museo);
            }
        }
        return museos;
    }

    public static List<Museo> findByPais(String paisMuseo) throws SQLException {
        List<Museo> museos = new ArrayList<>();
        Museo museo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PAIS)){
            ps.setString(1, paisMuseo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMuseo = rs.getInt("idMuseo");
                String nameMuseo = rs.getString("nameMuseo");
                String descriptionMuseo = rs.getString("descriptionMuseo");
                String ciudadMuseo = rs.getString("ciudadMuseo");
                String paisMuseo2 = rs.getString("paisMuseo");
                double precioEntrada = rs.getDouble("precioEntrada");
                String horarioMuseo = rs.getString("horarioMuseo");
                String webOficialMuseo = rs.getString("webOficialMuseo");
                museo = new Museo(idMuseo,nameMuseo,descriptionMuseo,ciudadMuseo,paisMuseo,precioEntrada, horarioMuseo,webOficialMuseo);
                museos.add(museo);
            }
        }
        return museos;
    }

    public static Museo addMuseo(Museo museo) throws SQLException {
        if (museo != null && findByName(museo.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, museo.getNombre());
                ps.setString(2, museo.getDescripcion());
                ps.setString(3, museo.getCiudad());
                ps.setString(4, museo.getPais());
                ps.executeUpdate();
            }
        }else {
            museo = null;
        }
        return museo;
    }

    public static boolean updateMuseo(Museo museoNuevo, Museo museoActual) throws SQLException {
        boolean updated = false;
        if((museoActual!=null)&&(museoNuevo!=null)&&findByName(museoActual.getNombre())!=null && findByName(museoNuevo.getNombre())==null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, museoActual.getNombre());
                ps.setString(2, museoActual.getDescripcion());
                ps.setString(3, museoActual.getCiudad());
                ps.setString(4, museoActual.getPais());
                ps.executeUpdate();
            }
        }
        return updated;
    }

    public static boolean deleteMuseoById(int idMuseo) throws SQLException {
        boolean deleted = false;
        if(findById(idMuseo)!=null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idMuseo);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}