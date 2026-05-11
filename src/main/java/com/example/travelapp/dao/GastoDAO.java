package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.CategoriaGasto;
import com.example.travelapp.model.Gasto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {
    private final static String SQL_ALL = "SELECT * FROM GASTO";
    private final static String SQL_FIND_BY_IDGASTO = "SELECT * FROM GASTO WHERE idGasto = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM GASTO WHERE idViaje = ?";
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM GASTO WHERE categoria = ?";
    private final static String SQL_FIND_BY_DATE = "SELECT * FROM GASTO WHERE fecha = ?";

    private final static String SQL_INSERT = "INSERT INTO GASTO VALUES (?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE GASTO SET GASTO = ? WHERE ID = ?";
    private final static String SQL_DELETE = "DELETE FROM GASTO WHERE ID = ?";


    public static List<Gasto> findAll() throws SQLException {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;
        try(ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)){
            while(rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double importe = rs.getDouble("importe");
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto,idViaje,categoria,fecha,importe,notas);
                gastos.add(gasto);
            }
        }
        return gastos;
    }

    public static Gasto findByIdGasto(int idGasto) throws SQLException {
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDGASTO)){
            ps.setInt(1, idGasto);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idGasto2 = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double importe = rs.getDouble("importe");
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto2,idViaje,categoria,fecha,importe,notas);
            }
        }
        return gasto;
    }

    public static Gasto findByIdViaje(int idViaje) throws SQLException {
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)){
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje2 = rs.getInt("idViaje");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double importe = rs.getDouble("importe");
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto,idViaje2,categoria,fecha,importe,notas);
            }
        }
        return gasto;
    }

    public static List<Gasto> findByCategoria(String categoria) throws SQLException {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;
        try (PreparedStatement ps =  ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CATEGORIA)){
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                CategoriaGasto categoria1 = CategoriaGasto.valueOf(rs.getString("categoria"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double importe = rs.getDouble("importe");
                String notas = rs.getString("notas");
                gasto= new Gasto(idGasto,idViaje,categoria1,fecha,importe,notas);
            }
        }
        return gastos;
    }

    public static List<Gasto> findByDate(String fecha) throws SQLException {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DATE)){
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                LocalDate fecha1 = LocalDate.parse(rs.getString("fecha"));
                double importe = rs.getDouble("importe");
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto,idViaje,categoria,fecha1,importe,notas);
                gastos.add(gasto);
            }
        }
        return gastos;
    }

    public static Gasto addGasto(Gasto gasto) throws SQLException {
        if (gasto != null && findByIdGasto(gasto.getIdGasto()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setInt(1, gasto.getIdGasto());
                ps.setInt(2, gasto.getIdViaje());
                ps.setString(3, gasto.getCategoria().toString());
                ps.setString(4, gasto.getFecha().toString());
                ps.setDouble(5, gasto.getImporte());
                ps.setString(6, gasto.getNotas());
                ps.executeUpdate();
            }
        }else {
            gasto = null;
        }
        return gasto;
    }

    public static boolean updateGasto(Gasto gastoNuevo, Gasto gastoActual) throws SQLException {
        boolean updated = false;
        if((gastoActual!=null)&&(gastoNuevo!=null)&&findByIdGasto(gastoActual.getIdGasto())!=null && findByIdGasto(gastoNuevo.getIdGasto())==null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, gastoNuevo.getIdGasto());
                ps.setInt(2, gastoNuevo.getIdViaje());
                ps.setString(3, gastoNuevo.getCategoria().toString());
                ps.setString(4, gastoNuevo.getFecha().toString());
                ps.setDouble(5, gastoNuevo.getImporte());
                ps.setString(6, gastoNuevo.getNotas());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteGasto(int idGasto) throws SQLException {
        boolean deleted = false;
        if(findByIdGasto(idGasto)!=null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idGasto);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}
