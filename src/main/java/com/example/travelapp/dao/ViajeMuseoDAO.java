package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Emocion;
import com.example.travelapp.model.ViajeMuseo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViajeMuseoDAO {
    private final static String SQL_FIND_ALL = "SELECT * FROM ViajeMuseo";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM ViajeMuseo WHERE idViaje=?";
    private final static String SQL_FIND_BY_IDMUSEO = "SELECT * FROM Museo WHERE idMuseo=?";
    private final static String SQL_FIND_BY_FECHAVISITA =  "SELECT * FROM Museo WHERE fechaVisita=?";

    private final static String SQL_INSERT ="INSERT INTO viajeMuseo VALUES (?,?)";
    private final static String SQL_UPDATE ="UPDATE viajeMuseo SET nombre=? WHERE id=?";
    private final static String SQL_DELETE ="DELETE FROM viajeMuseo WHERE id=?";

    public static List<ViajeMuseo> findAllViajeMuseo() throws SQLException {
        List<ViajeMuseo> viajeMuseos = new ArrayList<>();
        ViajeMuseo viajeMuseo = null;

        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while (rs.next()) {
                LocalDate fechaVisita = rs.getDate("fechaVisita").toLocalDate();
                Emocion emocion = rs.getObject("emocion", Emocion.class);
                viajeMuseo = new ViajeMuseo(fechaVisita, emocion);
                viajeMuseos.add(viajeMuseo);
            }
        }
        return viajeMuseos;
    }

    public static ViajeMuseo findByIdViaje(int idViaje) throws SQLException {
        ViajeMuseo viajeMuseo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)){
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate fechaVisita = rs.getDate("fechaVisita").toLocalDate();
                Emocion emocion = rs.getObject("emocion", Emocion.class);
                viajeMuseo = new ViajeMuseo( fechaVisita, emocion);
            }
        }
        return viajeMuseo;
    }

    public static ViajeMuseo findByIdMuseo(int idMuseo) throws SQLException {
        ViajeMuseo viajeMuseo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDMUSEO)){
            ps.setInt(1, idMuseo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                LocalDate fechaVisita = rs.getDate("fechaVisita").toLocalDate();
                Emocion emocion = rs.getObject("emocion", Emocion.class);
                viajeMuseo = new ViajeMuseo(fechaVisita, emocion);
            }
        }
        return viajeMuseo;
    }

    public static List<ViajeMuseo> findByFechaVisita(LocalDate fechaVisita) throws SQLException {
        List<ViajeMuseo> viajeMuseos = new ArrayList<>();
        ViajeMuseo viajeMuseo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAVISITA)){
            ps.setString(1, fechaVisita.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                LocalDate fechaVisita2 = rs.getDate("fechaVisita").toLocalDate();
                Emocion emocion = rs.getObject("emocion", Emocion.class);
                viajeMuseo = new ViajeMuseo(fechaVisita2, emocion);
                viajeMuseos.add(viajeMuseo);
            }
        }
        return viajeMuseos;
    }

    public static ViajeMuseo addViajeMuseo(ViajeMuseo viajeMuseo) throws SQLException {
        if (viajeMuseo != null && (findByIdMuseo(viajeMuseo.getIdMuseo()) == null) && (findByIdViaje(viajeMuseo.getIdViaje())) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setInt(1, viajeMuseo.getIdMuseo());
                ps.setInt(2, viajeMuseo.getIdViaje());
                ps.executeUpdate();
            }
        }
        return viajeMuseo;
    }

    public static boolean updateViajeMuseo(ViajeMuseo viajeMuseoNuevo, ViajeMuseo viajeMuseoActual) throws SQLException {
        boolean updated = false;
        if((viajeMuseoActual!=null)&&(viajeMuseoNuevo!=null)&&findByIdMuseo(viajeMuseoActual.getIdMuseo())!=null && findByIdMuseo(viajeMuseoNuevo.getIdMuseo())==null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, viajeMuseoActual.getIdMuseo());
                ps.setInt(2, viajeMuseoActual.getIdViaje());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteViajeMuseo(int idViaje) throws SQLException {
        boolean deleted = false;
        if (findByIdViaje(idViaje) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idViaje);
                ps.executeUpdate();
            }
        }
        return deleted;
    }
}
