package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.TipoDocumento;
import com.example.travelapp.model.TipoTransporte;
import com.example.travelapp.model.Transporte;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransporteDAO {

    private final static String SQL_ALL =
            "SELECT * FROM transporte";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM transporte WHERE idTransporte = ?";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM transporte WHERE idViaje = ?";

    private final static String SQL_FIND_BY_TIPO =
            "SELECT * FROM transporte WHERE tipo = ?";

    private final static String SQL_FIND_BY_FECHA =
            "SELECT * FROM transporte WHERE fecha = ?";

    private final static String SQL_FIND_BY_PRECIO =
            "SELECT * FROM transporte WHERE precio = ?";

    private final static String SQL_INSERT =
            "INSERT INTO transporte (idViaje, tipo, fecha, precio, tipoDocumento, rutaDocumento) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE transporte SET idViaje=?, tipo=?, fecha=?, precio=?, tipoDocumento=?, rutaDocumento=? " +
                    "WHERE idTransporte=?";

    private final static String SQL_DELETE =
            "DELETE FROM transporte WHERE idTransporte = ?";


    // ---------------------------------------------------------
    // MAPEO
    // ---------------------------------------------------------
    private static Transporte map(ResultSet rs) throws SQLException {
        return new Transporte(
                rs.getInt("idTransporte"),
                rs.getInt("idViaje"),
                TipoTransporte.valueOf(rs.getString("tipo")),
                rs.getDate("fecha").toLocalDate(),
                rs.getDouble("precio"),
                TipoDocumento.valueOf(rs.getString("tipoDocumento")),
                rs.getString("rutaDocumento")
        );
    }


    // ---------------------------------------------------------
    // FIND ALL
    // ---------------------------------------------------------
    public static List<Transporte> findAll() throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY ID
    // ---------------------------------------------------------
    public static Transporte findById(int id) throws SQLException {
        Transporte t = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = map(rs);
            }
        }
        return t;
    }


    // ---------------------------------------------------------
    // FIND BY ID VIAJE
    // ---------------------------------------------------------
    public static List<Transporte> findByIdViaje(int idViaje) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

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
    // FIND BY TIPO
    // ---------------------------------------------------------
    public static List<Transporte> findByTipo(TipoTransporte tipo) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)) {
            ps.setString(1, tipo.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY FECHA
    // ---------------------------------------------------------
    public static List<Transporte> findByFecha(LocalDate fecha) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // ---------------------------------------------------------
    // FIND BY PRECIO
    // ---------------------------------------------------------
    public static List<Transporte> findByPrecio(double precio) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRECIO)) {
            ps.setDouble(1, precio);
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
    public static boolean insert(Transporte t) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, t.getIdViaje());
            ps.setString(2, t.getTipo().toString());
            ps.setDate(3, Date.valueOf(t.getFecha()));
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getTipoDocumento().toString());
            ps.setString(6, t.getRutaDocumento());

            return ps.executeUpdate() > 0;
        }
    }


    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    public static boolean update(Transporte t) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, t.getIdViaje());
            ps.setString(2, t.getTipo().toString());
            ps.setDate(3, Date.valueOf(t.getFecha()));
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getTipoDocumento().toString());
            ps.setString(6, t.getRutaDocumento());
            ps.setInt(7, t.getIdTransporte());

            return ps.executeUpdate() > 0;
        }
    }


    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    public static boolean delete(int idTransporte) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idTransporte);
            return ps.executeUpdate() > 0;
        }
    }
}