package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Actividad;
import com.example.travelapp.model.CategoriaActividad;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Actividad.
 * Se encarga de gestionar todas las operaciones de acceso a la base de datos
 * relacionadas con la tabla "actividad".
 *
 * Incluye operaciones CRUD y consultas filtradas.
 */
public class ActividadDAO {

    /* =========================
       CONSULTAS SQL
       ========================= */

    /** Obtiene todas las actividades */
    private final static String SQL_ALL = "SELECT * FROM actividad";

    /** Busca actividad por su ID */
    private final static String SQL_FIND_BY_IDACTIVIDAD = "SELECT * FROM actividad WHERE idActividad =? ";

    /** Busca actividades por ID de viaje */
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM actividad WHERE idViaje=? ";

    /** Busca actividades por nombre (búsqueda parcial) */
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM actividad WHERE nombre LIKE ? ";

    /** Busca actividades por categoría */
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM actividad WHERE categoria=? ";

    /** Busca actividades por fecha */
    private final static String SQL_FIND_BY_FECHA = "SELECT * FROM actividad WHERE fecha=? ";

    /** Busca actividades por precio */
    private final static String SQL_FIND_BY_PRECIO = "SELECT * FROM actividad WHERE precio=? ";

    /** Busca actividades por valoración */
    private final static String SQL_FIND_BY_VALORACION = "SELECT * FROM actividad WHERE valoracion=? ";

    /** Busca actividades por estado de reserva */
    private final static String SQL_FIND_BY_RESERVADA = "SELECT * FROM actividad WHERE reservada=? ";

    /** Busca actividades por lugar (búsqueda parcial) */
    private final static String SQL_FIND_BY_LUGAR = "SELECT * FROM actividad WHERE lugar LIKE ? ";

    /** Inserta una nueva actividad */
    private final static String SQL_INSERT =
            "INSERT INTO actividad (idViaje, nombre, categoria, fecha, precio, notas, valoracion, duracionMinutos, reservada, lugar) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /** Actualiza una actividad existente */
    private final static String SQL_UPDATE =
            "UPDATE actividad SET nombre=?, categoria=?, fecha=?, precio=?, notas=?, valoracion=?, duracionMinutos=?, reservada=?, lugar=? " +
                    "WHERE idActividad=?";

    /** Elimina una actividad por ID */
    private final static String SQL_DELETE = "DELETE FROM actividad WHERE idActividad=?";


    /* =========================
       CONSULTAS (READ)
       ========================= */

    /**
     * Obtiene todas las actividades de la base de datos.
     */
    public List<Actividad> findAll() {
        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (ResultSet rs = ConnectionBD.getConnection()
                .createStatement()
                .executeQuery(SQL_ALL)) {

            while (rs.next()) {

                int idActividad = rs.getInt("idActividad");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                double precio = rs.getDouble("precio");
                String notas = rs.getString("notas");
                int valoracion = rs.getInt("valoracion");
                int duracionMinutos = rs.getInt("duracionMinutos");
                boolean reservada = rs.getBoolean("reservada");
                String lugar = rs.getString("lugar");

                actividad = new Actividad(
                        idActividad, idViaje, nombre, categoria, fecha,
                        precio, notas, valoracion, duracionMinutos,
                        reservada, lugar
                );

                actividades.add(actividad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return actividades;
    }

    /**
     * Busca una actividad por su ID.
     */
    public Actividad findByIdActividad(int idActividad) throws SQLException {

        Actividad actividad = null;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDACTIVIDAD)) {

            ps.setInt(1, idActividad);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int idActividad2 = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad2, idViaje, nombre, categoria, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );
                }
            }
        }

        return actividad;
    }

    /**
     * Busca todas las actividades de un viaje concreto.
     */
    public List<Actividad> findByIdViaje(int idViaje) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDVIAJE)) {

            ps.setInt(1, idViaje);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje2 = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje2, nombre, categoria, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por nombre (coincidencia parcial).
     */
    public List<Actividad> findByNombre(String nombre) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre2 = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre2, categoria, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por categoría.
     */
    public List<Actividad> findByCategoria(CategoriaActividad categoria) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_CATEGORIA)) {

            ps.setString(1, String.valueOf(categoria));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria2 = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria2, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por fecha.
     */
    public List<Actividad> findByFecha(LocalDate fecha) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_FECHA)) {

            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha2 = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria, fecha2,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por precio exacto.
     */
    public List<Actividad> findByPrice(double precio) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_PRECIO)) {

            ps.setDouble(1, precio);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio2 = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria, fecha,
                            precio2, notas, valoracion, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por valoración.
     */
    public List<Actividad> findByValoracion(int valoracion) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_VALORACION)) {

            ps.setInt(1, valoracion);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion2 = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria, fecha,
                            precio, notas, valoracion2, duracionMinutos,
                            reservada, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por estado de reserva.
     */
    public List<Actividad> findByReservada(boolean reservada) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_RESERVADA)) {

            ps.setBoolean(1, reservada);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada2 = rs.getBoolean("reservada");
                    String lugar = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada2, lugar
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /**
     * Busca actividades por lugar (búsqueda parcial).
     */
    public List<Actividad> findByLugar(String lugar) throws SQLException {

        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_LUGAR)) {

            ps.setString(1, "%" + lugar + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int idActividad = rs.getInt("idActividad");
                    int idViaje = rs.getInt("idViaje");
                    String nombre = rs.getString("nombre");
                    CategoriaActividad categoria = CategoriaActividad.valueOf(rs.getString("categoria"));
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    double precio = rs.getDouble("precio");
                    String notas = rs.getString("notas");
                    int valoracion = rs.getInt("valoracion");
                    int duracionMinutos = rs.getInt("duracionMinutos");
                    boolean reservada = rs.getBoolean("reservada");
                    String lugar2 = rs.getString("lugar");

                    actividad = new Actividad(
                            idActividad, idViaje, nombre, categoria, fecha,
                            precio, notas, valoracion, duracionMinutos,
                            reservada, lugar2
                    );

                    actividades.add(actividad);
                }
            }
        }

        return actividades;
    }

    /* =========================
       OPERACIONES (CREATE / UPDATE / DELETE)
       ========================= */

    /**
     * Inserta una nueva actividad en la base de datos.
     * Solo la inserta si no existe otra con el mismo nombre.
     */
    public Actividad addActividad(Actividad actividad) throws SQLException {

        if (actividad != null && findByNombre(actividad.getNombre()).isEmpty()) {

            try (PreparedStatement ps = ConnectionBD.getConnection()
                    .prepareStatement(SQL_INSERT)) {

                ps.setInt(1, actividad.getIdViaje());
                ps.setString(2, actividad.getNombre());
                ps.setString(3, actividad.getCategoria().name());
                ps.setDate(4, Date.valueOf(actividad.getFecha()));
                ps.setDouble(5, actividad.getPrecio());
                ps.setString(6, actividad.getNotas());
                ps.setInt(7, actividad.getValoracion());
                ps.setInt(8, actividad.getDuracionMinutos());
                ps.setBoolean(9, actividad.isReservada());
                ps.setString(10, actividad.getLugar());

                ps.executeUpdate();
            }
        }

        return actividad;
    }

    /**
     * Actualiza una actividad existente en la base de datos.
     */
    public boolean updateActividad(Actividad actividad) throws SQLException {

        boolean updated = false;

        if (actividad != null && findByIdActividad(actividad.getIdActividad()) != null) {

            try (PreparedStatement ps = ConnectionBD.getConnection()
                    .prepareStatement(SQL_UPDATE)) {

                ps.setString(1, actividad.getNombre());
                ps.setString(2, actividad.getCategoria().name());
                ps.setDate(3, Date.valueOf(actividad.getFecha()));
                ps.setDouble(4, actividad.getPrecio());
                ps.setString(5, actividad.getNotas());
                ps.setInt(6, actividad.getValoracion());
                ps.setInt(7, actividad.getDuracionMinutos());
                ps.setBoolean(8, actividad.isReservada());
                ps.setString(9, actividad.getLugar());
                ps.setInt(10, actividad.getIdActividad());

                ps.executeUpdate();
                updated = true;
            }
        }

        return updated;
    }

    /**
     * Elimina una actividad por su ID.
     */
    public boolean deleteActividadById(int idActividad) throws SQLException {

        boolean deleted = false;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idActividad);

            deleted = ps.executeUpdate() > 0;
        }

        return deleted;
    }
}