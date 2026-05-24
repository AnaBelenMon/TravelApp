package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.TipoDocumento;
import com.example.travelapp.model.TipoTransporte;
import com.example.travelapp.model.Transporte;

import java.sql.*;
import java.time.LocalDate;
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
public class TransporteDAO {

    // =========================================================
    // CONSULTAS SQL
    // =========================================================

    private final static String SQL_ALL =
            "SELECT * FROM transporte";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM transporte WHERE idTransporte = ?";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM transporte WHERE idViaje = ?";

    private final static String SQL_FIND_BY_TIPO =
            "SELECT * FROM transporte WHERE tipo = ?";

    private final static String SQL_FIND_BY_FECHA =
            "SELECT * FROM transporte WHERE fecha = ?";

    private final static String SQL_FIND_BY_PRECIO =
            "SELECT * FROM transporte WHERE precio = ?";

    private final static String SQL_INSERT =
            "INSERT INTO transporte (idViaje, tipo, fecha, precio, tipoDocumento, rutaDocumento) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE transporte SET idViaje=?, tipo=?, fecha=?, precio=?, tipoDocumento=?, rutaDocumento=? " +
                    "WHERE idTransporte=?";

    private final static String SQL_DELETE =
            "DELETE FROM transporte WHERE idTransporte = ?";

    // =========================================================
    // MAPEADOR RESULTSET -> OBJETO
    // =========================================================

    /**
     * Convierte una fila del ResultSet en un objeto Transporte.
     */
    private static Transporte map(ResultSet rs) throws SQLException {
        return new Transporte(
                rs.getInt("idTransporte"),
                rs.getInt("idViaje"),
                TipoTransporte.valueOf(rs.getString("tipo")),
                rs.getDate("fecha").toLocalDate(),
                rs.getDouble("precio"),
                TipoDocumento.valueOf(rs.getString("tipoDocumento")),
                rs.getString("rutaDocumento")
        );
    }

    // =========================================================
    // CONSULTAS SELECT
    // =========================================================

    /**
     * Obtiene todos los transportes registrados.
     */
    public static List<Transporte> findAll() throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }

        return lista;
    }

    /**
     * Busca un transporte por su ID.
     */
    public static Transporte findById(int id) throws SQLException {
        Transporte t = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    t = map(rs);
                }
            }
        }

        return t;
    }

    /**
     * Obtiene todos los transportes de un viaje concreto.
     */
    public static List<Transporte> findByIdViaje(int idViaje) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

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
     * Filtra transportes por tipo (bus, tren, avión, etc.).
     */
    public static List<Transporte> findByTipo(TipoTransporte tipo) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

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
     * Filtra transportes por fecha.
     */
    public static List<Transporte> findByFecha(LocalDate fecha) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

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
     * Filtra transportes por precio.
     */
    public static List<Transporte> findByPrecio(double precio) throws SQLException {
        List<Transporte> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRECIO)) {
            ps.setDouble(1, precio);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }

        return lista;
    }

    // =========================================================
    // INSERT / UPDATE / DELETE
    // =========================================================

    /**
     * Inserta un nuevo transporte en la base de datos.
     */
    public static boolean insert(Transporte t) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, t.getIdViaje());
            ps.setString(2, t.getTipo().toString());
            ps.setDate(3, Date.valueOf(t.getFecha()));
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getTipoDocumento().toString());
            ps.setString(6, t.getRutaDocumento());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un transporte existente.
     */
    public static boolean update(Transporte t) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, t.getIdViaje());
            ps.setString(2, t.getTipo().toString());
            ps.setDate(3, Date.valueOf(t.getFecha()));
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getTipoDocumento().toString());
            ps.setString(6, t.getRutaDocumento());
            ps.setInt(7, t.getIdTransporte());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un transporte por su ID.
     */
    public static boolean delete(int idTransporte) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idTransporte);
            return ps.executeUpdate() > 0;
        }
    }
}