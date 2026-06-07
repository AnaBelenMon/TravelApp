package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.ViajeTransporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar la tabla intermedia {@link ViajeTransporte},
 * que representa la relación N:M entre {@link Viaje} y {@link Transporte}.
 *
 * Este DAO permite:
 * <ul>
 *     <li>Obtener todas las relaciones viaje–transporte.</li>
 *     <li>Buscar relaciones por ID o por viaje.</li>
 *     <li>Insertar, actualizar y eliminar asociaciones.</li>
 * </ul>
 *
 * Cada método utiliza consultas preparadas para garantizar seguridad,
 * evitar inyecciones SQL y asegurar un acceso eficiente a la base de datos.
 *
 * Este DAO colabora con {@link ViajeDAO} y {@link TransporteDAO} para
 * reconstruir completamente los objetos relacionados.
 */
public class ViajeTransporteDAO {
    private final static String SQL_ALL = "SELECT * FROM ViajeTransporte";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM ViajeTransporte WHERE idViajeTransporte = ?";
    private final static String SQL_FIND_BY_VIAJE =  "SELECT * FROM ViajeTransporte WHERE idViaje = ?";

    private final static String SQL_INSERT = "INSERT INTO ViajeTransporte (idViaje, idTransporte, notas) VALUES (?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE ViajeTransporte SET idViaje=?, idTransporte=?, notas=? WHERE idViajeTransporte=?";
    private final static String SQL_DELETE = "DELETE FROM ViajeTransporte WHERE idViaje = ? AND idTransporte = ?";

    private ViajeDAO viajeDAO = new ViajeDAO();
    private TransporteDAO transporteDAO = new TransporteDAO();

    /**
     * Obtiene todas las relaciones viaje–transporte registradas.
     *
     * @return lista de relaciones {@link ViajeTransporte}
     */
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

    /**
     * Busca una relación viaje–transporte por su identificador único.
     *
     * @param id identificador de la relación
     * @return objeto {@link ViajeTransporte} o null si no existe
     */
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

    /**
     * Obtiene todas las relaciones asociadas a un viaje concreto.
     *
     * @param viajeActual viaje del que se desean obtener los transportes asociados
     * @return lista de relaciones {@link ViajeTransporte}
     */
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

    /**
     * Inserta una nueva relación viaje–transporte en la base de datos.
     * Tras la inserción, se recupera el ID generado automáticamente.
     *
     * @param vt relación a insertar
     * @return relación con su ID actualizado
     */
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

    /**
     * Actualiza una relación viaje–transporte existente.
     *
     * @param vt relación con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
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

    /**
     * Elimina una relación concreta entre un viaje y un transporte.
     *
     * @param viaje viaje asociado
     * @param transporte transporte asociado
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
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

    /**
     * Inserta una relación viaje–transporte sin notas.
     * Método auxiliar utilizado en controladores.
     *
     * @param viajeActual viaje asociado
     * @param transporteActual transporte asociado
     */
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