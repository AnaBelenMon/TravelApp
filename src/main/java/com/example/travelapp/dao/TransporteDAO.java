package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.enums.EstadoTransporte;
import com.example.travelapp.model.enums.TipoDocumento;
import com.example.travelapp.model.enums.TipoTransporte;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.Viaje;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Transporte.
 *
 * Gestiona el acceso a la tabla "transporte" de la base de datos,
 * permitiendo realizar operaciones CRUD (crear, leer, actualizar y eliminar)
 * y consultas filtradas por diferentes campos como tipo, fecha o precio.
 *
 * Cada transporte está asociado a un viaje mediante el campo idViaje
 * y puede incluir información adicional como documentos asociados.
 */
public class TransporteDAO implements GenericDAO<Transporte>{
    private final static String SQL_ALL = "SELECT * FROM transporte";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM transporte WHERE idTransporte = ?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM transporte WHERE tipo = ?";
    private final static String SQL_FIND_BY_ORIGEN = "SELECT * FROM transporte WHERE origen = ?";
    private final static String SQL_FIND_BY_DESTINO = "SELECT * FROM transporte WHERE destino = ?";
    private final static String SQL_FIND_BY_FECHASALIDA = "SELECT * FROM transporte WHERE fechaSalida = ?";
    private final static String SQL_FIND_BY_FECHALLEGADA = "SELECT * FROM transporte WHERE fechaLlegada = ?";
    private final static String SQL_FIND_BY_PRECIO = "SELECT * FROM transporte WHERE precio = ?";
    private final static String SQL_FIND_BY_ESTADO = "SELECT * FROM transporte WHERE estado = ?";
    private final static String SQL_INSERT = "INSERT INTO transporte (idViaje, tipo, fecha, precio, tipoDocumento, rutaDocumento) " + "VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE transporte SET idViaje=?, tipo=?, fecha=?, precio=?, tipoDocumento=?, rutaDocumento=? " + "WHERE idTransporte=?";
    private final static String SQL_DELETE = "DELETE FROM transporte WHERE idTransporte = ?";

    /**
     * Obtiene todos los transportes registrados.
     */
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

    /**
     * Busca un transporte por su ID.
     */
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transporte;
    }

    /**
     * Filtra transportes por tipo (bus, tren, avión, etc.).
     */
    public static List<Transporte> findByTipo(TipoTransporte tipo) throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)) {
            ps.setString(1, tipo.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idTransporte = rs.getInt("idTransporte");
                TipoTransporte tipoTransporte  = TipoTransporte.valueOf(rs.getString("tipo"));
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                LocalDateTime fechaSalida = rs.getTimestamp("fechaSalida").toLocalDateTime();
                LocalDateTime fechaLlegada = rs.getTimestamp("fechaLlegada").toLocalDateTime();
                double precio = rs.getDouble("precio");
                EstadoTransporte  estado = EstadoTransporte.valueOf(rs.getString("estado"));
                transporte = new Transporte(idTransporte, tipoTransporte, origen, destino, fechaSalida, fechaLlegada, precio, estado);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    /**
     * Filtra transportes por precio.
     */
    public static List<Transporte> findByPrecio(double precio) throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRECIO)) {
            ps.setDouble(1, precio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idTransporte = rs.getInt("idTransporte");
                TipoTransporte tipoTransporte  = TipoTransporte.valueOf(rs.getString("tipo"));
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                LocalDateTime fechaSalida = rs.getTimestamp("fechaSalida").toLocalDateTime();
                LocalDateTime fechaLlegada = rs.getTimestamp("fechaLlegada").toLocalDateTime();
                double precio2 = rs.getDouble("precio");
                EstadoTransporte estado = EstadoTransporte.valueOf(rs.getString("estado"));
                transporte = new Transporte(idTransporte, tipoTransporte, origen, destino, fechaSalida, fechaLlegada, precio2, estado);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    /**
     * Inserta un nuevo transporte en la base de datos.
     */
    public  Transporte add(Transporte t) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setInt(1, t.getIdTransporte());
            ps.setString(2, t.getTipo().toString());
            ps.setString(3, t.getOrigen());
            ps.setString(4, t.getDestino());
            ps.setTimestamp(5, Timestamp.valueOf(t.getFechaSalida()));
            ps.setTimestamp(6, Timestamp.valueOf(t.getFechaLlegada()));
            ps.setDouble(7, t.getPrecio());
            ps.setString(8, t.getEstado().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Actualiza un transporte existente.
     */
    public boolean update(Transporte t) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, t.getIdTransporte());
            ps.setString(2, t.getTipo().toString());
            ps.setString(3, t.getOrigen());
            ps.setString(4, t.getDestino());
            ps.setTimestamp(5, Timestamp.valueOf(t.getFechaSalida()));
            ps.setTimestamp(6, Timestamp.valueOf(t.getFechaLlegada()));
            ps.setDouble(7, t.getPrecio());
            ps.setString(8, t.getEstado().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un transporte por su ID.
     */
    public boolean delete(int idTransporte) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idTransporte);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}