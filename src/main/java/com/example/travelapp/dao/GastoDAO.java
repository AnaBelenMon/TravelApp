package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.enums.CategoriaGasto;
import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.MetodoPago;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO implements GenericDAO<Gasto> {
    private final static String SQL_ALL = "SELECT * FROM gasto";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gasto WHERE idGasto = ?";
    private final static String SQL_FIND_BY_VIAJE = "SELECT * FROM gasto WHERE idViaje = ?";
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM gasto WHERE categoria = ?";
    private final static String SQL_INSERT = "INSERT INTO gasto (idViaje, concepto, categoria, importe, fecha, lugar, metodoPago, estado, notas)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE gasto SET idViaje=?, concepto=?, categoria=?, importe=?, fecha=?, lugar=?, metodoPago=?, estado=?, notas=? " + "WHERE idGasto=?";
    private final static String SQL_DELETE = "DELETE FROM gasto WHERE idGasto=?";

    private ViajeDAO viajeDAO = new ViajeDAO();

    public List<Gasto> findAll() {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
                gastos.add(gasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    public Gasto findById(int id) {
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasto;
    }

    public List<Gasto> findByViaje(Viaje viaje) {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_VIAJE)) {
            ps.setInt(1, viaje.getIdViaje());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje2 = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje2,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
                gastos.add(gasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    public Gasto add(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, gasto.getViaje().getIdViaje());
            ps.setString(2, gasto.getConcepto());
            ps.setString(3, gasto.getCategoria().name());
            ps.setDouble(4, gasto.getImporte());
            ps.setDate(5, Date.valueOf(gasto.getFecha()));
            ps.setString(6, gasto.getLugar());
            ps.setString(7, gasto.getMetodoPago().name());
            ps.setString(8, gasto.getEstado().name());
            ps.setString(9, gasto.getNotas());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                gasto.setIdGasto(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasto;
    }

    public boolean update(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, gasto.getViaje().getIdViaje());
            ps.setString(2, gasto.getConcepto());
            ps.setString(3, gasto.getCategoria().name());
            ps.setDouble(4, gasto.getImporte());
            ps.setDate(5, Date.valueOf(gasto.getFecha()));
            ps.setString(6, gasto.getLugar());
            ps.setString(7, gasto.getMetodoPago().name());
            ps.setString(8, gasto.getEstado().name());
            ps.setString(9, gasto.getNotas());
            ps.setInt(10, gasto.getIdGasto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, gasto.getIdGasto());
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}