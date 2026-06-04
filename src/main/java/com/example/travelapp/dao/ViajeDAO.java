package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.enums.TipoViaje;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.model.Viaje;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Viaje.
 *
 * Gestiona el acceso a datos de los viajes almacenados en la base de datos.
 * Permite operaciones CRUD completas y múltiples consultas filtradas.
 *
 * Esta entidad es la principal del sistema, ya que agrupa usuarios,
 * gastos, recuerdos, documentos y transporte.
 */
public class ViajeDAO implements GenericDAO<Viaje> {
    private final static String SQL_ALL = "SELECT * FROM viaje";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM viaje WHERE idViaje = ?";
    private final static String SQL_FIND_BY_USUARIO = "SELECT * FROM viaje WHERE idUsuario = ?";
    private final static String SQL_FIND_BY_ALOJAMIENTO = "SELECT * FROM viaje WHERE idAlojamiento = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM viaje WHERE nombre = ?";
    private final static String SQL_FIND_BY_DESTINO = "SELECT * FROM viaje WHERE destino = ?";
    private final static String SQL_FIND_BY_FECHAINICIO = "SELECT * FROM viaje WHERE fechaInicio = ?";
    private final static String SQL_FIND_BY_FECHAFIN = "SELECT * FROM viaje WHERE fechaFin = ?";
    private final static String SQL_FIND_BY_PRESUPUESTO = "SELECT * FROM viaje WHERE presupuesto = ?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM viaje WHERE tipo = ?";
    private final static String SQL_INSERT = "INSERT INTO viaje (idUsuario,idAlojamiento, nombre,destino, fechaInicio, fechaFin, presupuesto , notas, tipo,  imagen) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE viaje SET idUsuario=?, idAlojamiento=?, nombre=?, destino=?, fechaInicio=?, fechaFin=?, presupuesto=?, notas=?, tipo=?, imagen=? " + "WHERE idViaje=?";
    private final static String SQL_DELETE = "DELETE FROM viaje WHERE idViaje = ?";
    /**
     * Obtiene todos los viajes.
     */
    public List<Viaje> findAll() {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return viajes;
    }

    /**
     * Busca un viaje por su ID.
     */
    public Viaje findById(int id) {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return viaje;
    }

    /**
     * Obtiene los viajes de un usuario.
     */
    public static List<Viaje> findByUsuario(int idUsuario) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_USUARIO)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Busca viajes por nombre.
     */
    public static List<Viaje> findByNombre(String nombre) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje  viaje = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre2 = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre2, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Busca viajes por fecha de inicio.
     */
    public static List<Viaje> findByFechaInicio(LocalDate fechaInicio) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje  viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAINICIO)) {
            ps.setDate(1, Date.valueOf(fechaInicio));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio2 = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio2, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Busca viajes por fecha de fin.
     */
    public static List<Viaje> findByFechaFin(LocalDate fechaFin) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHAFIN)) {
            ps.setDate(1, Date.valueOf(fechaFin));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin2 = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin2, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Busca viajes por tipo.
     */
    public static List<Viaje> findByTipoViaje(TipoViaje tipoViaje) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)) {
            ps.setString(1, tipoViaje.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Busca viajes por presupuesto.
     */
    public static List<Viaje> findByPresupuesto(double presupuesto) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Viaje  viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRESUPUESTO)) {
            ps.setDouble(1, presupuesto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                Usuario usuario = rs.getObject("usuario", Usuario.class);
                Alojamiento alojamiento = rs.getObject("alojamiento", Alojamiento.class);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getObject("fechaInicio", LocalDate.class);
                LocalDate fechaFin = rs.getObject("fechaFin", LocalDate.class);
                double presupuesto2 = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = rs.getObject("tipo", TipoViaje.class);
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto2, notas, tipo, imagen);
                viajes.add(viaje);
            }
        }
        return viajes;
    }

    /**
     * Inserta un nuevo viaje en la base de datos.
     */
    public Viaje add(Viaje v) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setInt(1, v.getIdViaje());
            ps.setString(2, v.getUsuario().getNombre());
            ps.setString(3, v.getAlojamiento().getNombre());
            ps.setString(4, v.getNombre());
            ps.setString(5, v.getDestino());
            ps.setDate(6, Date.valueOf(v.getFechaInicio()));
            ps.setDate(7, Date.valueOf(v.getFechaFin()));
            ps.setDouble(8, v.getPresupuesto());
            ps.setString(9, v.getNotas());
            ps.setString(10, v.getTipo().toString());
            ps.setString(11, v.getImagen());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Actualiza un viaje existente.
     */
    public boolean update(Viaje v) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, v.getIdViaje());
            ps.setString(2, v.getUsuario().getNombre());
            ps.setString(3, v.getAlojamiento().getNombre());
            ps.setString(4, v.getNombre());
            ps.setString(5, v.getDestino());
            ps.setDate(6, Date.valueOf(v.getFechaInicio()));
            ps.setDate(7, Date.valueOf(v.getFechaFin()));
            ps.setDouble(8, v.getPresupuesto());
            ps.setString(9, v.getNotas());
            ps.setString(10, v.getTipo().toString());
            ps.setString(11, v.getImagen());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un viaje por ID.
     */
    public boolean delete(int idViaje) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idViaje);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}