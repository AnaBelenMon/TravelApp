package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Emocion;
import com.example.travelapp.model.Recuerdo;
import com.example.travelapp.model.TipoRecuerdo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Recuerdo.
 *
 * Gestiona el acceso a la tabla "recuerdo" de la base de datos,
 * permitiendo realizar operaciones CRUD (crear, leer, actualizar y eliminar)
 * así como consultas filtradas por distintos campos.
 *
 * Cada recuerdo representa un elemento multimedia asociado a un viaje,
 * incluyendo información como ubicación, emoción, tipo, fecha y rutas de archivo.
 */
public class RecuerdoDAO {

    // =========================================================
    // CONSULTAS SQL
    // =========================================================

    private final static String SQL_FIND_ALL =
            "SELECT * FROM recuerdo";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM recuerdo WHERE idRecuerdo = ?";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM recuerdo WHERE idViaje = ?";

    private final static String SQL_FIND_BY_RUTAARCHIVO =
            "SELECT * FROM recuerdo WHERE rutaArchivo = ?";

    private final static String SQL_FIND_BY_UBICACION =
            "SELECT * FROM recuerdo WHERE ubicacion = ?";

    private final static String SQL_FIND_BY_FECHA =
            "SELECT * FROM recuerdo WHERE fecha = ?";

    private final static String SQL_FIND_BY_EMOCION =
            "SELECT * FROM recuerdo WHERE emocion = ?";

    private final static String SQL_FIND_BY_TIPO =
            "SELECT * FROM recuerdo WHERE tipo = ?";

    private final static String SQL_FIND_BY_FAVORITO =
            "SELECT * FROM recuerdo WHERE favorito = ?";

    private final static String SQL_FIND_BY_RUTAMINIATURA =
            "SELECT * FROM recuerdo WHERE rutaMiniatura = ?";

    private final static String SQL_INSERT =
            "INSERT INTO recuerdo (idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipo, favorito, rutaMiniatura) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE recuerdo SET idViaje=?, rutaArchivo=?, descripcion=?, ubicacion=?, fecha=?, emocion=?, tipo=?, favorito=?, rutaMiniatura=? " +
                    "WHERE idRecuerdo=?";

    private final static String SQL_DELETE =
            "DELETE FROM recuerdo WHERE idRecuerdo = ?";

    // =========================================================
    // MAPEADOR RESULTSET -> OBJETO
    // =========================================================

    /**
     * Convierte una fila del ResultSet en un objeto Recuerdo.
     */
    private static Recuerdo map(ResultSet rs) throws SQLException {
        return new Recuerdo(
                rs.getInt("idRecuerdo"),
                rs.getInt("idViaje"),
                rs.getString("rutaArchivo"),
                rs.getString("descripcion"),
                rs.getString("ubicacion"),
                rs.getDate("fecha").toLocalDate(),
                Emocion.valueOf(rs.getString("emocion")),
                TipoRecuerdo.valueOf(rs.getString("tipo")),
                rs.getBoolean("favorito"),
                rs.getString("rutaMiniatura")
        );
    }

    // =========================================================
    // CONSULTAS SELECT
    // =========================================================

    /**
     * Obtiene todos los recuerdos almacenados.
     */
    public static List<Recuerdo> findAll() throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_FIND_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }

        return lista;
    }

    /**
     * Busca un recuerdo por su ID.
     */
    public static Recuerdo findById(int id) throws SQLException {
        Recuerdo r = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = map(rs);
                }
            }
        }

        return r;
    }

    /**
     * Obtiene todos los recuerdos de un viaje concreto.
     */
    public static List<Recuerdo> findByIdViaje(int idViaje) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Busca un recuerdo por la ruta del archivo original.
     */
    public static Recuerdo findByRutaArchivo(String ruta) throws SQLException {
        Recuerdo r = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_RUTAARCHIVO)) {
            ps.setString(1, ruta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = map(rs);
                }
            }
        }

        return r;
    }

    /**
     * Obtiene recuerdos por ubicación.
     */
    public static List<Recuerdo> findByUbicacion(String ubicacion) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_UBICACION)) {
            ps.setString(1, ubicacion);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Obtiene recuerdos por fecha.
     */
    public static List<Recuerdo> findByFecha(LocalDate fecha) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {
            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Obtiene recuerdos filtrando por emoción.
     */
    public static List<Recuerdo> findByEmocion(Emocion emocion) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_EMOCION)) {
            ps.setString(1, emocion.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Obtiene recuerdos filtrando por tipo.
     */
    public static List<Recuerdo> findByTipo(TipoRecuerdo tipo) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)) {
            ps.setString(1, tipo.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Filtra recuerdos por si están marcados como favoritos o no.
     */
    public static List<Recuerdo> findByFavorito(boolean favorito) throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FAVORITO)) {
            ps.setBoolean(1, favorito);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    /**
     * Busca un recuerdo por su ruta de miniatura.
     */
    public static Recuerdo findByRutaMiniatura(String rutaMiniatura) throws SQLException {
        Recuerdo r = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_RUTAMINIATURA)) {
            ps.setString(1, rutaMiniatura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = map(rs);
                }
            }
        }

        return r;
    }

    // =========================================================
    // INSERT / UPDATE / DELETE
    // =========================================================

    /**
     * Inserta un nuevo recuerdo en la base de datos.
     */
    public static boolean insert(Recuerdo r) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, r.getIdViaje());
            ps.setString(2, r.getRutaArchivo());
            ps.setString(3, r.getDescripcion());
            ps.setString(4, r.getUbicacion());
            ps.setDate(5, Date.valueOf(r.getFecha()));
            ps.setString(6, r.getEmocion().toString());
            ps.setString(7, r.getTipo().toString());
            ps.setBoolean(8, r.isFavorito());
            ps.setString(9, r.getRutaMiniatura());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un recuerdo existente.
     */
    public static boolean update(Recuerdo r) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, r.getIdViaje());
            ps.setString(2, r.getRutaArchivo());
            ps.setString(3, r.getDescripcion());
            ps.setString(4, r.getUbicacion());
            ps.setDate(5, Date.valueOf(r.getFecha()));
            ps.setString(6, r.getEmocion().toString());
            ps.setString(7, r.getTipo().toString());
            ps.setBoolean(8, r.isFavorito());
            ps.setString(9, r.getRutaMiniatura());
            ps.setInt(10, r.getIdRecuerdo());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un recuerdo por su ID.
     */
    public static boolean delete(int idRecuerdo) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idRecuerdo);
            return ps.executeUpdate() > 0;
        }
    }
}