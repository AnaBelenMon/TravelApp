package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.ViajeTransporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeTransporteDAO {
    private final static String SQL_ALL = "SELECT * FROM ViajeTransporte";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM ViajeTransporte WHERE idViajeTransporte = ?";
    private final static String SQL_FIND_BY_VIAJE =  "SELECT * FROM ViajeTransporte WHERE idViaje = ?";

    private final static String SQL_INSERT = "INSERT INTO ViajeTransporte (idViaje, idTransporte, notas) VALUES (?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE ViajeTransporte SET idViaje=?, idTransporte=?, notas=? WHERE idViajeTransporte=?";
    private final static String SQL_DELETE = "DELETE FROM ViajeTransporte WHERE idViaje = ? AND idTransporte = ?";

    private ViajeDAO viajeDAO = new ViajeDAO();
    private TransporteDAO transporteDAO = new TransporteDAO();

    public List<ViajeTransporte> findAll() {
        List<ViajeTransporte> viajeTransportes = new ArrayList<>();
        ViajeTransporte viajeTransporte = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idViajeTransporte = rs.getInt("idViajeTransporte");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                int idTransporte = rs.getInt("idTransporte");
                Transporte transporte = transporteDAO.findById(idTransporte);
                String notas = rs.getString("notas");
                viajeTransporte = new ViajeTransporte(idViajeTransporte, viaje, transporte, notas);
                viajeTransportes.add(viajeTransporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viajeTransportes;
    }

    public ViajeTransporte findById(int id) {
        ViajeTransporte viajeTransporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idViajeTransporte = rs.getInt("idViajeTransporte");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                int idTransporte = rs.getInt("idTransporte");
                Transporte transporte = transporteDAO.findById(idTransporte);
                String notas = rs.getString("notas");
                viajeTransporte = new ViajeTransporte(idViajeTransporte, viaje, transporte, notas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viajeTransporte;
    }

    public List<ViajeTransporte> findByViaje(Viaje viajeActual) {
        List<ViajeTransporte> viajeTransportes = new ArrayList<>();
        ViajeTransporte viajeTransporte = null;
        try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_VIAJE)){
            ps.setInt(1, viajeActual.getIdViaje());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViajeTransporte = rs.getInt("idViajeTransporte");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                int idTransporte = rs.getInt("idTransporte");
                Transporte transporte = transporteDAO.findById(idTransporte);
                String notas = rs.getString("notas");
                viajeTransporte = new ViajeTransporte(idViajeTransporte, viaje, transporte, notas);
                viajeTransportes.add(viajeTransporte);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return viajeTransportes;
    }

    public ViajeTransporte add(ViajeTransporte vt) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, vt.getViaje().getIdViaje());
            ps.setInt(2, vt.getTransporte().getIdTransporte());
            ps.setString(3, vt.getNotas());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                vt.setIdViajeTransporte(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vt;
    }

    public boolean update(ViajeTransporte vt) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, vt.getViaje().getIdViaje());
            ps.setInt(2, vt.getTransporte().getIdTransporte());
            ps.setString(3, vt.getNotas());
            ps.setInt(4, vt.getIdViajeTransporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Viaje viaje, Transporte transporte) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, viaje.getIdViaje());
            ps.setInt(2, transporte.getIdTransporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Viaje viajeActual, Transporte transporteActual) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setInt(1, viajeActual.getIdViaje());
            ps.setInt(2, transporteActual.getIdTransporte());
            ps.setString(3, null); // notas opcional
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
