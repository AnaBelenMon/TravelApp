package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.enums.EstadoTransporte;
import com.example.travelapp.model.enums.TipoTransporte;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransporteDAO implements GenericDAO<Transporte> {
    private final static String SQL_ALL = "SELECT * FROM transporte";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM transporte WHERE idTransporte = ?";

    private final static String SQL_INSERT = "INSERT INTO transporte (tipo, origen, destino, fechaSalida, fechaLlegada, precio, estado) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE transporte SET tipo=?, origen=?, destino=?, fechaSalida=?, fechaLlegada=?, precio=?, estado=? " + "WHERE idTransporte=?";
    private final static String SQL_DELETE = "DELETE FROM transporte WHERE idTransporte = ?";

    @Override
    public List<Transporte> findAll() {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idTransporte = rs.getInt("idTransporte");
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                LocalDateTime fechaSalida = rs.getTimestamp("fechaSalida").toLocalDateTime();
                LocalDateTime fechaLlegada = rs.getTimestamp("fechaLlegada").toLocalDateTime();
                double precio = rs.getDouble("precio");
                EstadoTransporte estado = EstadoTransporte.valueOf(rs.getString("estado"));
                transporte = new Transporte(idTransporte, tipo, origen, destino, fechaSalida, fechaLlegada, precio, estado);
                transportes.add(transporte);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transportes;
    }

    @Override
    public Transporte findById(int id) {
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idTransporte = rs.getInt("idTransporte");
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                LocalDateTime fechaSalida = rs.getTimestamp("fechaSalida").toLocalDateTime();
                LocalDateTime fechaLLegada = rs.getTimestamp("fechaLlegada").toLocalDateTime();
                double precio = rs.getDouble("precio");
                EstadoTransporte estado = EstadoTransporte.valueOf(rs.getString("estado"));
                transporte = new Transporte(idTransporte, tipo, origen, destino, fechaSalida, fechaLLegada, precio, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transporte;
    }

    @Override
    public Transporte add(Transporte t) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getTipo().name());
            ps.setString(2, t.getOrigen());
            ps.setString(3, t.getDestino());
            ps.setTimestamp(4, Timestamp.valueOf(t.getFechaSalida()));
            ps.setTimestamp(5, Timestamp.valueOf(t.getFechaLlegada()));
            ps.setDouble(6, t.getPrecio());
            ps.setString(7, t.getEstado().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                t.setIdTransporte(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


    @Override
    public boolean update(Transporte t) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setString(1, t.getTipo().name());
            ps.setString(2, t.getOrigen());
            ps.setString(3, t.getDestino());
            ps.setTimestamp(4, Timestamp.valueOf(t.getFechaSalida()));
            ps.setTimestamp(5, Timestamp.valueOf(t.getFechaLlegada()));
            ps.setDouble(6, t.getPrecio());
            ps.setString(7, t.getEstado().name());
            ps.setInt(8, t.getIdTransporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Transporte transporte) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, transporte.getIdTransporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}