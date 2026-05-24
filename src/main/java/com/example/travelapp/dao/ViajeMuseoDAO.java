package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Emocion;
import com.example.travelapp.model.ViajeMuseo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViajeMuseoDAO {

    private final static String SQL_ALL =
            "SELECT * FROM viajeMuseo";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM viajeMuseo WHERE idViaje = ?";

    private final static String SQL_FIND_BY_IDMUSEO =
            "SELECT * FROM viajeMuseo WHERE idMuseo = ?";

    private final static String SQL_FIND_BY_FECHAVISITA =
            "SELECT * FROM viajeMuseo WHERE fechaVisita = ?";

    private final static String SQL_INSERT =
            "INSERT INTO viajeMuseo (idViaje, idMuseo, fechaVisita, emocion) VALUES (?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE viajeMuseo SET fechaVisita=?, emocion=? WHERE idViaje=? AND idMuseo=?";

    private final static String SQL_DELETE =
            "DELETE FROM viajeMuseo WHERE idViaje=? AND idMuseo=?";


    // ---------------------------------------------------------
    // MAPEO
    // ---------------------------------------------------------
    private static ViajeMuseo map(ResultSet rs) throws SQLException {
        return new ViajeMuseo(
                rs.getInt("idViaje"),
                rs.getInt("idMuseo"),
                rs.getDate("fechaVisita").toLocalDate(),
                Emocion.valueOf(rs.getString("emocion"))
        );
    }


    // ---------------------------------------------------------
    // FIND ALL
    // ---------------------------------------------------------
    public static List<ViajeMuseo> findAll() throws SQLException {
        List<ViajeMuseo> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY ID VIAJE
    // ---------------------------------------------------------
    public static List<ViajeMuseo> findByIdViaje(int idViaje) throws SQLException {
        List<ViajeMuseo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY ID MUSEO
    // ---------------------------------------------------------
    public static List<ViajeMuseo> findByIdMuseo(int idMuseo) throws SQLException {
        List<ViajeMuseo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDMUSEO)) {
            ps.setInt(1, idMuseo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY FECHA VISITA
    // ---------------------------------------------------------
    public static List<ViajeMuseo> findByFechaVisita(LocalDate fecha) throws SQLException {
        List<ViajeMuseo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAVISITA)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // INSERT
    // ---------------------------------------------------------
    public static boolean insert(ViajeMuseo vm) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, vm.getIdViaje());
            ps.setInt(2, vm.getIdMuseo());
            ps.setDate(3, Date.valueOf(vm.getFechaVisita()));
            ps.setString(4, vm.getEmocion().toString());

            return ps.executeUpdate() > 0;
        }
    }


    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    public static boolean update(ViajeMuseo vm) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setDate(1, Date.valueOf(vm.getFechaVisita()));
            ps.setString(2, vm.getEmocion().toString());
            ps.setInt(3, vm.getIdViaje());
            ps.setInt(4, vm.getIdMuseo());

            return ps.executeUpdate() > 0;
        }
    }


    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    public static boolean delete(int idViaje, int idMuseo) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idViaje);
            ps.setInt(2, idMuseo);
            return ps.executeUpdate() > 0;
        }
    }
}
