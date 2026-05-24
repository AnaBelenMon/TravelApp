package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.CategoriaGasto;
import com.example.travelapp.model.Gasto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    private final static String SQL_ALL =
            "SELECT * FROM gasto";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM gasto WHERE idGasto = ?";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM gasto WHERE idViaje = ?";

    private final static String SQL_FIND_BY_CATEGORIA =
            "SELECT * FROM gasto WHERE categoria = ?";

    private final static String SQL_FIND_BY_FECHA =
            "SELECT * FROM gasto WHERE fecha = ?";

    private final static String SQL_INSERT =
            "INSERT INTO gasto (idViaje, categoria, fecha, importe, notas) VALUES (?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE gasto SET idViaje=?, categoria=?, fecha=?, importe=?, notas=? WHERE idGasto=?";

    private final static String SQL_DELETE =
            "DELETE FROM gasto WHERE idGasto=?";


    // -----------------------------
    // SELECTS
    // -----------------------------

    public static List<Gasto> findAll() throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    public static Gasto findById(int idGasto) throws SQLException {
        Gasto gasto = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, idGasto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                gasto = map(rs);
            }
        }
        return gasto;
    }

    public static List<Gasto> findByIdViaje(int idViaje) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    public static List<Gasto> findByCategoria(CategoriaGasto categoria) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CATEGORIA)) {
            ps.setString(1, categoria.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    public static List<Gasto> findByFecha(LocalDate fecha) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {
            ps.setString(1, fecha.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }


    // -----------------------------
    // INSERT / UPDATE / DELETE
    // -----------------------------

    public static boolean insert(Gasto gasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, gasto.getIdViaje());
            ps.setString(2, gasto.getCategoriaGasto().name());
            ps.setString(3, gasto.getFecha().toString());
            ps.setDouble(4, gasto.getImporte());
            ps.setString(5, gasto.getNotas());

            return ps.executeUpdate() > 0;
        }
    }

    public static boolean update(Gasto gasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, gasto.getIdViaje());
            ps.setString(2, gasto.getCategoriaGasto().name());
            ps.setString(3, gasto.getFecha().toString());
            ps.setDouble(4, gasto.getImporte());
            ps.setString(5, gasto.getNotas());
            ps.setInt(6, gasto.getIdGasto());

            return ps.executeUpdate() > 0;
        }
    }

    public static boolean delete(int idGasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idGasto);
            return ps.executeUpdate() > 0;
        }
    }


    // -----------------------------
    // MAPEO DEL RESULTSET
    // -----------------------------

    private static Gasto map(ResultSet rs) throws SQLException {
        return new Gasto(
                rs.getInt("idGasto"),
                rs.getInt("idViaje"),
                CategoriaGasto.valueOf(rs.getString("categoria")),
                LocalDate.parse(rs.getString("fecha")),
                rs.getDouble("importe"),
                rs.getString("notas")
        );
    }
}
