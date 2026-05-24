package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Alojamiento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) encargado de gestionar todas las operaciones
 * de acceso a datos relacionadas con la entidad Alojamiento.
 *
 * Se encarga de realizar consultas SQL sobre la tabla "alojamiento"
 * y mapear los resultados a objetos Alojamiento.
 */
public class AlojamientoDAO {

    // =========================
    // CONSULTAS SQL PREDEFINIDAS
    // =========================

    private final static String SQL_ALL = "SELECT * FROM alojamiento";
    private final static String SQL_FIND_BY_IDALOJAMIENTO = "SELECT * FROM alojamiento WHERE idAlojamiento = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM alojamiento WHERE idViaje = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM alojamiento WHERE nombre=?";
    private final static String SQL_FIND_BY_DIRECCION  = "SELECT * FROM alojamiento WHERE direccion=?";
    private final static String SQL_FIND_BY_PRECIOTOTAL = "SELECT * FROM alojamiento WHERE precioTotal=?";
    private final static String SQL_FIND_BY_FECHACHECKIN = "SELECT * FROM alojamiento WHERE fechaCheckin = ?";
    private final static String SQL_FIND_BY_FECHACHECKOUT = "SELECT * FROM alojamiento WHERE fechaCheckout = ?";
    private final static String SQL_FIND_BY_VALORACION = "SELECT * FROM alojamiento WHERE valoracion = ?";

    private final static String SQL_INSERT =
            "INSERT INTO alojamiento(nombre, direccion, precioTotal, fechaCheckin, fechaCheckout, valoracion, idViaje) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE alojamiento SET nombre=?, direccion=?, precioTotal=?, fechaCheckin=?, fechaCheckout=?, valoracion=?, idViaje=? " +
                    "WHERE idAlojamiento=?";

    private final static String SQL_DELETE =
            "DELETE FROM alojamiento WHERE idAlojamiento=?";

    // =========================
    // CONSULTA: FIND ALL
    // =========================

    /**
     * Obtiene todos los alojamientos almacenados en la base de datos.
     *
     * @return lista de objetos Alojamiento
     */
    public static List<Alojamiento> findAll() throws SQLException {
        List<Alojamiento> alojamientos = new ArrayList<>();

        try (ResultSet rs = ConnectionBD.getConnection()
                .createStatement()
                .executeQuery(SQL_ALL)) {

            while (rs.next()) {

                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");

                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));

                int valoracion = rs.getInt("valoracion");

                Alojamiento alojamiento =
                        new Alojamiento(idAlojamiento, idViaje, nombre, direccion,
                                precioTotal, fechaCheckin, fechaCheckout, valoracion);

                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    // =========================
    // CONSULTA: FIND BY ID
    // =========================

    /**
     * Busca un alojamiento por su ID.
     *
     * @param idAlojamiento identificador del alojamiento
     * @return objeto Alojamiento si existe, null si no
     */
    public static Alojamiento findByIdAlojamiento(int idAlojamiento) throws SQLException {

        Alojamiento alojamiento = null;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDALOJAMIENTO)) {

            ps.setInt(1, idAlojamiento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");

                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));

                int valoracion = rs.getInt("valoracion");

                alojamiento =
                        new Alojamiento(idAlojamiento, idViaje, nombre, direccion,
                                precioTotal, fechaCheckin, fechaCheckout, valoracion);
            }
        }
        return alojamiento;
    }

    // =========================
    // CONSULTA: FIND BY ID VIAJE
    // =========================

    /**
     * Obtiene todos los alojamientos asociados a un viaje.
     *
     * @param idViaje identificador del viaje
     * @return lista de alojamientos
     */
    public static List<Alojamiento> findByIdViaje(int idViaje) throws SQLException {

        List<Alojamiento> alojamientos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDVIAJE)) {

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

                Alojamiento alojamiento =
                        new Alojamiento(idAlojamiento, idViaje2, nombre, direccion,
                                precioTotal, fechaCheckin, fechaCheckout, valoracion);

                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    // =========================
    // CONSULTA: FIND BY NOMBRE
    // =========================

    /**
     * Busca alojamientos por nombre exacto.
     */
    public static List<Alojamiento> findByNombre(String nombre) throws SQLException {

        List<Alojamiento> alojamientos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idAlojamiento = rs.getInt("idAlojamiento");
                int idViaje = rs.getInt("idViaje");
                String nombre2 = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double precioTotal = rs.getDouble("precioTotal");

                LocalDate fechaCheckin = LocalDate.parse(rs.getString("fechaCheckin"));
                LocalDate fechaCheckout = LocalDate.parse(rs.getString("fechaCheckout"));

                int valoracion = rs.getInt("valoracion");

                Alojamiento alojamiento =
                        new Alojamiento(idAlojamiento, idViaje, nombre2, direccion,
                                precioTotal, fechaCheckin, fechaCheckout, valoracion);

                alojamientos.add(alojamiento);
            }
        }
        return alojamientos;
    }

    // =========================
    // CONSULTA: INSERT
    // =========================

    /**
     * Inserta un nuevo alojamiento en la base de datos.
     *
     * @param alojamiento objeto a insertar
     * @return alojamiento insertado
     */
    public static Alojamiento addAlojamiento(Alojamiento alojamiento) throws SQLException {

        if (alojamiento != null) {

            try (PreparedStatement ps = ConnectionBD.getConnection()
                    .prepareStatement(SQL_INSERT)) {

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

    // =========================
    // CONSULTA: UPDATE
    // =========================

    /**
     * Actualiza un alojamiento existente.
     *
     * @param alojamiento objeto con datos actualizados
     * @return true si se actualiza correctamente
     */
    public static boolean updateAlojamiento(Alojamiento alojamiento) throws SQLException {

        if (alojamiento != null) {

            try (PreparedStatement ps = ConnectionBD.getConnection()
                    .prepareStatement(SQL_UPDATE)) {

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

    // =========================
    // CONSULTA: DELETE
    // =========================

    /**
     * Elimina un alojamiento por su ID.
     *
     * @param idAlojamiento identificador
     * @return true si se elimina correctamente
     */
    public static boolean deleteAlojamientoById(int idAlojamiento) throws SQLException {

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idAlojamiento);
            return ps.executeUpdate() > 0;
        }
    }
}