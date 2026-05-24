package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Alojamiento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoDAO {
    private final static String SQL_ALL = "SELECT * FROM alojamiento";
    private final static String SQL_FIND_BY_IDALOJAMIENTO = "SELECT * FROM alojamiento WHERE idAlojamiento = ?";
    private final static String SQL_FIND_BY_IDVIAJE =  "SELECT * FROM alojamiento WHERE idViaje = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM alojamiento WHERE nombre=? ";
    private final static String SQL_FIND_BY_DIRECCION  = "SELECT * FROM alojamiento WHERE direccion=? ";
    private final static String SQL_FIND_BY_PRECIOTOTAL = "SELECT * FROM alojamiento WHERE precioTotal=? ";
    private final static String SQL_FIND_BY_FECHACHECKIN = "SELECT * FROM alojamiento WHERE fechaCheckin = ?";
    private final static String SQL_FIND_BY_FECHACHECKOUT = "SELECT * FROM alojamiento WHERE fechaCheckout = ?";
    private final static String SQL_FIND_BY_VALORACION = "SELECT * FROM alojamiento WHERE valoracion = ?";

    private final static String SQL_INSERT = "INSERT INTO alojamiento(nombre, direccion, precioTotal, fechaCheckin, fechaCheckout, valoracion, idViaje) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE alojamiento SET nombre=?, direccion=?, precioTotal=?, fechaCheckin=?, fechaCheckout=?, valoracion=?, idViaje=? " + "WHERE idAlojamiento=?";
    private final static String SQL_DELETE = "DELETE FROM alojamiento WHERE idAlojamiento=?";

    public static List<Alojamiento> findAll() throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, idViaje, nombre, direccion, precioTotal, fechaCheckin, fechaCheckout, valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static Alojamiento findByIdAlojamiento(int idAlojamiento) throws SQLException {
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDALOJAMIENTO)) {
            ps.setInt(1, idAlojamiento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idAlojamiento2 = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento2, idViaje, nombre, direccion, precioTotal, fechaCheckin, fechaCheckout, valoracion);
            }
        }
        return alojamiento;
    }

    public static List<Alojamiento> findByIdViaje(int idViaje) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje2 = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento =new Alojamiento(idAlojamiento, idViaje2, nombre, direccion, precioTotal, fechaCheckin, fechaCheckout, valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByNombre(String nombre) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre2 = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre2,direccion,precioTotal, fechaCheckin,fechacheckout,valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByDireccion(String direccion) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DIRECCION)) {
            ps.setString(1, direccion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion2 = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, idViaje, nombre,direccion2,precioTotal, fechaCheckin,fechacheckout,valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByPrecioTotal(double precioTotal) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRECIOTOTAL)){
            ps.setDouble(1, precioTotal);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal2 = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, idViaje, nombre,direccion,precioTotal2, fechaCheckin,fechacheckout, valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByDateCheckin(String fechaCheckin) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHACHECKIN)){
            ps.setString(1, fechaCheckin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin2 = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout = LocalDate.parse(rs.getString("fechacheckout"));
                int  valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre, direccion, precioTotal, fechaCheckin2, fechacheckout,  valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByDateCheckout(String fechaCheckout) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHACHECKOUT)){
            ps.setString(1, fechaCheckout);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechacheckout = LocalDate.parse(rs.getString("fechacheckout"));
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre, direccion, precioTotal, fechaCheckin, fechacheckout,  valoracion);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static List<Alojamiento> findByValoracion(int valoracion) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_VALORACION)) {
            ps.setInt(1, valoracion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechacheckout = LocalDate.parse(rs.getString("fechacheckout"));
                int valoracion2 = rs.getInt("valoracion");
                alojamiento =  new Alojamiento(idAlojamiento,idViaje,nombre, direccion, precioTotal, fechaCheckin, fechacheckout,  valoracion2);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static Alojamiento addAlojamiento(Alojamiento alojamiento) throws SQLException {
        if (alojamiento != null) {

            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, alojamiento.getNombre());
                ps.setString(2, alojamiento.getDireccion());
                ps.setDouble(3, alojamiento.getPrecioTotal());
                ps.setString(4, alojamiento.getFechaCheckin().toString());
                ps.setString(5, alojamiento.getFechaCheckout().toString());
                ps.setInt(6, alojamiento.getValoracion());
                ps.setInt(7, alojamiento.getIdViaje());
                ps.executeUpdate();
            }
        }
        return alojamiento;
    }

    public static boolean updateAlojamiento(Alojamiento alojamiento) throws SQLException {
        if (alojamiento != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, alojamiento.getNombre());
                ps.setString(2, alojamiento.getDireccion());
                ps.setDouble(3, alojamiento.getPrecioTotal());
                ps.setString(4, alojamiento.getFechaCheckin().toString());
                ps.setString(5, alojamiento.getFechaCheckout().toString());
                ps.setInt(6, alojamiento.getValoracion());
                ps.setInt(7, alojamiento.getIdViaje());
                ps.setInt(8, alojamiento.getIdAlojamiento());
                return ps.executeUpdate() > 0;
            }
        }
        return false;
    }

    public static boolean deleteAlojamientoById(int idAlojamiento) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idAlojamiento);
            return ps.executeUpdate() > 0;
        }
    }
}