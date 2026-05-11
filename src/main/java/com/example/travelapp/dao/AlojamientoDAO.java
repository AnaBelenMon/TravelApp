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
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM alojamiento WHERE nombre=? ";
    private final static String SQL_FIND_BY_DIRECCION  = "SELECT * FROM alojamiento WHERE direccion=? ";
    private final static String SQL_FIND_BY_PRECIOTOTAL = "SELECT * FROM alojamiento WHERE precioTotal=? ";
    private final static String SQL_FIND_BY_FECHACHECKIN = "SELECT * FROM alojamiento WHERE fechacheckin=? ";
    private final static String SQL_FIND_BY_FECHACHECKOUT = "SELECT * FROM alojamiento WHERE fechacheckout=? ";

    //private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM alojamiento WHERE categoria=? ";
    //private final static String SQL_FIND_BY_STATUS = "SELECT * FROM alojamiento WHERE estado=? ";
    //private final static String SQL_FIND_BY_DATE =  "SELECT * FROM alojamiento WHERE fecha=? ";
    //private final static String SQL_FIND_BY_PRICE = "SELECT * FROM alojamiento WHERE precio=? ";

    private final static String SQL_INSERT = "INSERT INTO alojamiento(nombre) values (?)";
    private final static String SQL_UPDATE = "UPDATE alojamiento SET nombre=? WHERE id=?";
    private final static String SQL_DELETE = "DELETE FROM alojamiento WHERE id=?";

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
                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre,direccion,precioTotal, fechaCheckin,fechacheckout);
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
            while (rs.next()) {
                int idAlojamiento2 = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre,direccion,precioTotal, fechacheckin,fechacheckout);
            }
        }
        return alojamiento;
    }

    public static Alojamiento findByIdViaje(int idViaje) throws SQLException {
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje2 = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje2,nombre,direccion,precioTotal, fechacheckin,fechacheckout);
            }
        }
        return alojamiento;
    }

    public static List<Alojamiento> findByNombre(String nombre) throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre2 = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre2,direccion,precioTotal, fechacheckin,fechacheckout);
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
                double precioTotal = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre,direccion2,precioTotal, fechacheckin,fechacheckout);
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
                double precioTotal2 = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout= LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre,direccion,precioTotal2, fechacheckin,fechacheckout);
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
                double precioTotal = rs.getDouble("precio");
                LocalDate fechaCheckin2 = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout = LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento, idViaje, nombre, direccion, precioTotal, fechaCheckin2, fechacheckout);
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
                double precioTotal = rs.getDouble("precio");
                LocalDate fechacheckin = LocalDate.parse(rs.getString("fechacheckin"));
                LocalDate fechacheckout = LocalDate.parse(rs.getString("fechacheckout"));
                alojamiento = new Alojamiento(idAlojamiento,idViaje,nombre,direccion,precioTotal, fechacheckin,fechacheckout);
                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    public static Alojamiento addAlojamiento(Alojamiento alojamiento) throws SQLException {
        if (alojamiento != null && findByNombre(alojamiento.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, alojamiento.getNombre());
                ps.setString(2, alojamiento.getDireccion());
                ps.setDouble(3, alojamiento.getPrecioTotal());
                ps.executeUpdate();
            }
        }else {
            alojamiento = null;
        }
        return alojamiento;
    }

    public static boolean updateAlojamiento(Alojamiento alojamientoNuevo, Alojamiento alojamientoActual) throws SQLException {
        boolean updated = false;
        if((alojamientoActual!=null)&&(alojamientoNuevo!=null)&&findByNombre(alojamientoActual.getNombre())!=null && findByNombre(alojamientoNuevo.getNombre())==null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, alojamientoNuevo.getNombre());
                ps.setInt(2, alojamientoNuevo.getIdAlojamiento());
                ps.executeUpdate();
                updated = true;

            }
        }
        return updated;
    }


    public static boolean deleteAlojamientoById(int idAlojamiento) throws SQLException {
        boolean deleted = false;
        if(findByIdAlojamiento(idAlojamiento)!=null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idAlojamiento);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}