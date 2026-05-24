package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.TipoViaje;
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
public class ViajeDAO {

    private final static String SQL_ALL = "SELECT * FROM viaje";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM viaje WHERE idViaje = ?";

    private final static String SQL_FIND_BY_USUARIO =
            "SELECT * FROM viaje WHERE idUsuario = ?";

    private final static String SQL_FIND_BY_NOMBRE =
            "SELECT * FROM viaje WHERE nombre = ?";

    private final static String SQL_FIND_BY_FECHAINICIO =
            "SELECT * FROM viaje WHERE fechaInicio = ?";

    private final static String SQL_FIND_BY_FECHAFIN =
            "SELECT * FROM viaje WHERE fechaFin = ?";

    private final static String SQL_FIND_BY_TIPO =
            "SELECT * FROM viaje WHERE tipoViaje = ?";

    private final static String SQL_FIND_BY_PRESUPUESTO =
            "SELECT * FROM viaje WHERE presupuestoEstimado = ?";

    private final static String SQL_FIND_BY_DESTINOPAIS =
            "SELECT * FROM viaje WHERE destinoPais = ?";

    private final static String SQL_FIND_BY_DESTINOCIUDAD =
            "SELECT * FROM viaje WHERE destinoCiudad = ?";

    private final static String SQL_INSERT =
            "INSERT INTO viaje (idUsuario, nombre, fechaInicio, fechaFin, tipoViaje, imagenPortada, notasGenerales, presupuestoEstimado, destinoPais, destinoCiudad) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE viaje SET idUsuario=?, nombre=?, fechaInicio=?, fechaFin=?, tipoViaje=?, imagenPortada=?, notasGenerales=?, presupuestoEstimado=?, destinoPais=?, destinoCiudad=? " +
                    "WHERE idViaje=?";

    private final static String SQL_DELETE =
            "DELETE FROM viaje WHERE idViaje = ?";

    /**
     * Mapea un ResultSet a un objeto Viaje.
     */
    private static Viaje map(ResultSet rs) throws SQLException {
        return new Viaje(
                rs.getInt("idViaje"),
                rs.getInt("idUsuario"),
                rs.getString("nombre"),
                rs.getDate("fechaInicio").toLocalDate(),
                rs.getDate("fechaFin").toLocalDate(),
                TipoViaje.valueOf(rs.getString("tipoViaje")),
                rs.getString("imagenPortada"),
                rs.getString("notasGenerales"),
                rs.getDouble("presupuestoEstimado"),
                rs.getString("destinoPais"),
                rs.getString("destinoCiudad")
        );
    }

    /**
     * Obtiene todos los viajes.
     */
    public static List<Viaje> findAll() throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca un viaje por su ID.
     */
    public static Viaje findById(int idViaje) throws SQLException {
        Viaje v = null;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = map(rs);
            }
        }
        return v;
    }

    /**
     * Obtiene los viajes de un usuario.
     */
    public static List<Viaje> findByUsuario(int idUsuario) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_USUARIO)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por nombre.
     */
    public static List<Viaje> findByNombre(String nombre) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por fecha de inicio.
     */
    public static List<Viaje> findByFechaInicio(LocalDate fechaInicio) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_FECHAINICIO)) {

            ps.setDate(1, Date.valueOf(fechaInicio));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por fecha de fin.
     */
    public static List<Viaje> findByFechaFin(LocalDate fechaFin) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_FECHAFIN)) {

            ps.setDate(1, Date.valueOf(fechaFin));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por tipo.
     */
    public static List<Viaje> findByTipoViaje(TipoViaje tipoViaje) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_TIPO)) {

            ps.setString(1, tipoViaje.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por presupuesto.
     */
    public static List<Viaje> findByPresupuesto(double presupuesto) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_PRESUPUESTO)) {

            ps.setDouble(1, presupuesto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por país de destino.
     */
    public static List<Viaje> findByDestinoPais(String pais) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_DESTINOPAIS)) {

            ps.setString(1, pais);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca viajes por ciudad de destino.
     */
    public static List<Viaje> findByDestinoCiudad(String ciudad) throws SQLException {
        List<Viaje> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_DESTINOCIUDAD)) {

            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Inserta un nuevo viaje en la base de datos.
     */
    public static boolean insert(Viaje v) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_INSERT)) {

            ps.setInt(1, v.getIdUsuario());
            ps.setString(2, v.getNombre());
            ps.setDate(3, Date.valueOf(v.getFechaInicio()));
            ps.setDate(4, Date.valueOf(v.getFechaFin()));
            ps.setString(5, v.getTipoViaje().toString());
            ps.setString(6, v.getImagenPortada());
            ps.setString(7, v.getNotasGenerales());
            ps.setDouble(8, v.getPresupuestoEstimado());
            ps.setString(9, v.getDestinoPais());
            ps.setString(10, v.getDestinoCiudad());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un viaje existente.
     */
    public static boolean update(Viaje v) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, v.getIdUsuario());
            ps.setString(2, v.getNombre());
            ps.setDate(3, Date.valueOf(v.getFechaInicio()));
            ps.setDate(4, Date.valueOf(v.getFechaFin()));
            ps.setString(5, v.getTipoViaje().toString());
            ps.setString(6, v.getImagenPortada());
            ps.setString(7, v.getNotasGenerales());
            ps.setDouble(8, v.getPresupuestoEstimado());
            ps.setString(9, v.getDestinoPais());
            ps.setString(10, v.getDestinoCiudad());
            ps.setInt(11, v.getIdViaje());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un viaje por ID.
     */
    public static boolean delete(int idViaje) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idViaje);
            return ps.executeUpdate() > 0;
        }
    }
}