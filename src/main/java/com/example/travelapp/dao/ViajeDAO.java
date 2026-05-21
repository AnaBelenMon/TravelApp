package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.TipoViaje;
import com.example.travelapp.model.Viaje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViajeDAO {
    private final static String SQL_FIND_ALL = "SELECT * FROM viaje";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM viaje WHERE idViaje = ?";
    private final static String SQL_FIND_BY_IDUSUARIO = "SELECT * FROM viaje WHERE idUsuario = ?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM viaje WHERE nombre = ?";
    private final static String SQL_FIND_BY_FECHAINICIO = "SELECT * FROM viaje WHERE fechaInicio = ?";
    private final static String SQL_FIND_BY_FECHAFIN =  "SELECT * FROM viaje WHERE fechaFin = ?";
    private final static String SQL_FIND_BY_TIPOV = "SELECT * FROM viaje WHERE tipoViaje = ?";
    private final static String SQL_FIND_BY_PRESUPUESTOESTIMADO = "SELECT * FROM viaje WHERE presupuestoEstimado = ?";
    private final static String SQL_FIND_BY_DESTINOPAIS = "SELECT * FROM viaje WHERE destinoPais = ?";
    private final static String SQL_FIND_BY_DESTINOCIUDAD = "SELECT * FROM viaje WHERE destinoCiudad = ?";

    private final static String SQL_INSERT = "INSERT INTO viaje";
    private final static String SQL_UPDATE =  "UPDATE viaje SET ";
    private final static String SQL_DELETE = "DELETE FROM viaje WHERE idViaje = ?";


    public static List<Viaje>  findAllViaje() throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    public static Viaje findByIdViaje(int idViaje) {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)){
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return viaje;
    }

    public static Viaje findByIdUsuario(int idUsuario) throws SQLException {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDUSUARIO)){
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
            }
        }
        return viaje;
    }

    public static List<Viaje> findByNombre(String nombre) {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre2 = rs.getString("nombre");
                LocalDate fechaInicio = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre2,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
                viajes.add(viaje);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return viajes;
    }

    public static Viaje findByFechaInicio(String fechaInicio) throws SQLException {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAINICIO)){
            ps.setString(1, fechaInicio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio2  = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin  = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presuspuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio2,fechaFin,tipoViaje,imagenPortada,notasGenerales,presuspuestoEstimado,destinoPais,destinoCiudad);
            }
        }
        return viaje;
    }

    public static Viaje findByFechaFin(String fechaFin) throws SQLException {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAFIN)){
            ps.setString(1, fechaFin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio  = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin2 = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin2,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
            }
        }
        return viaje;
    }

    public static List<Viaje> findByTipoViaje(TipoViaje tipoViaje) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPOV)){
            ps.setString(1, tipoViaje.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio  = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin  = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje2 = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje2,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    public static List<Viaje> findByPresupuestoEstimado(double presupuestoEstimado) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRESUPUESTOESTIMADO)){
            ps.setDouble(1, presupuestoEstimado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio  = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin  = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje =  TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado2 =  rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado2,destinoPais,destinoCiudad);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    public static List<Viaje> findByDestinoPais(String destinoPais) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DESTINOPAIS)){
            ps.setString(1, destinoPais);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais2  = rs.getString("destinoPais");
                String destinoCiudad = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais2,destinoCiudad);
            }
        }
        return  viajes;
    }

    public static List<Viaje> findByDestinoCiudad(String destinoCiudad) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DESTINOCIUDAD)){
            ps.setString(1, destinoCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio  = LocalDate.parse(rs.getString("fechaInicio"));
                LocalDate fechaFin  = LocalDate.parse(rs.getString("fechaFin"));
                TipoViaje tipoViaje = TipoViaje.valueOf(rs.getString("tipoViaje"));
                String imagenPortada = rs.getString("imagenPortada");
                String notasGenerales = rs.getString("notasGenerales");
                double presupuestoEstimado = rs.getDouble("presupuestoEstimado");
                String destinoPais = rs.getString("destinoPais");
                String destinoCiudad2 = rs.getString("destinoCiudad");
                viaje = new Viaje(nombre,fechaInicio,fechaFin,tipoViaje,imagenPortada,notasGenerales,presupuestoEstimado,destinoPais,destinoCiudad2);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    public Viaje addViaje(Viaje viaje) {
        if (viaje != null && findByNombre(viaje.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, viaje.getNombre());
                ps.setDouble(2, viaje.getPresupuestoEstimado());
                ps.setString(3, viaje.getDestinoPais());
                ps.setString(4, viaje.getDestinoCiudad());
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            viaje = null;
        }
        return viaje;
    }

    public  boolean updateViaje(Viaje viaje) {
        boolean updated = false;
        if((viaje!=null)&&findByNombre(viaje.getNombre())!=null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, viaje.getNombre());
                ps.setDouble(2, viaje.getPresupuestoEstimado());
                ps.setString(3, viaje.getDestinoPais());
                ps.setString(4, viaje.getDestinoCiudad());
                ps.executeUpdate();
                updated = true;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    public boolean deleteViaje(int idViaje) {
        boolean deleted = false;
        if(findByIdViaje(idViaje)!=null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idViaje);
                ps.executeUpdate();
                deleted = true;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deleted;
    }
}
