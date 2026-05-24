package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.CategoriaGasto;
import com.example.travelapp.model.Gasto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar el acceso a datos de la entidad Gasto.
 *
 * Permite realizar operaciones CRUD y consultas específicas sobre la tabla
 * gasto de la base de datos.
 */
public class GastoDAO {

    // =========================
    // CONSULTAS SQL
    // =========================

    /** Obtiene todos los gastos */
    private final static String SQL_ALL = "SELECT * FROM gasto";

    /** Busca un gasto por su ID */
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gasto WHERE idGasto = ?";

    /** Busca gastos por viaje */
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM gasto WHERE idViaje = ?";

    /** Busca gastos por categoría */
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM gasto WHERE categoria = ?";

    /** Busca gastos por fecha */
    private final static String SQL_FIND_BY_FECHA = "SELECT * FROM gasto WHERE fecha = ?";

    /** Inserta un nuevo gasto */
    private final static String SQL_INSERT =
            "INSERT INTO gasto (idViaje, categoria, fecha, importe, notas) VALUES (?, ?, ?, ?, ?)";

    /** Actualiza un gasto existente */
    private final static String SQL_UPDATE =
            "UPDATE gasto SET idViaje=?, categoria=?, fecha=?, importe=?, notas=? WHERE idGasto=?";

    /** Elimina un gasto por ID */
    private final static String SQL_DELETE = "DELETE FROM gasto WHERE idGasto=?";


    // =========================
    // CONSULTAS (SELECT)
    // =========================

    /**
     * Obtiene todos los gastos almacenados en la base de datos.
     *
     * @return lista de gastos
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findAll() throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    /**
     * Busca un gasto por su identificador.
     *
     * @param idGasto identificador del gasto
     * @return gasto encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta
     */
    public static Gasto findById(int idGasto) throws SQLException {
        Gasto gasto = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idGasto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gasto = map(rs);
                }
            }
        }
        return gasto;
    }

    /**
     * Busca todos los gastos asociados a un viaje.
     *
     * @param idViaje identificador del viaje
     * @return lista de gastos del viaje
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByIdViaje(int idViaje) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

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
     * Busca gastos por categoría.
     *
     * @param categoria categoría del gasto
     * @return lista de gastos filtrados
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByCategoria(CategoriaGasto categoria) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CATEGORIA)) {

            ps.setString(1, categoria.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }
        return lista;
    }

    /**
     * Busca gastos por fecha.
     *
     * @param fecha fecha del gasto
     * @return lista de gastos en esa fecha
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByFecha(LocalDate fecha) throws SQLException {
        List<Gasto> lista = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {

            ps.setString(1, fecha.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        }
        return lista;
    }


    // =========================
    // CRUD (INSERT / UPDATE / DELETE)
    // =========================

    /**
     * Inserta un nuevo gasto en la base de datos.
     *
     * @param gasto objeto a insertar
     * @return true si se insertó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean insert(Gasto gasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setInt(1, gasto.getIdViaje());
            ps.setString(2, gasto.getCategoriaGasto().name());
            ps.setString(3, gasto.getFecha().toString());
            ps.setDouble(4, gasto.getImporte());
            ps.setString(5, gasto.getNotas());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un gasto existente en la base de datos.
     *
     * @param gasto objeto con los nuevos datos
     * @return true si se actualizó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean update(Gasto gasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, gasto.getIdViaje());
            ps.setString(2, gasto.getCategoriaGasto().name());
            ps.setString(3, gasto.getFecha().toString());
            ps.setDouble(4, gasto.getImporte());
            ps.setString(5, gasto.getNotas());
            ps.setInt(6, gasto.getIdGasto());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un gasto por su identificador.
     *
     * @param idGasto identificador del gasto
     * @return true si se eliminó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean delete(int idGasto) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idGasto);

            return ps.executeUpdate() > 0;
        }
    }


    // =========================
    // MAPEADOR (RESULTSET → OBJETO)
    // =========================

    /**
     * Convierte un ResultSet en un objeto Gasto.
     *
     * @param rs resultado de la consulta
     * @return objeto Gasto construido
     * @throws SQLException si ocurre un error al leer datos
     */
    private static Gasto map(ResultSet rs) throws SQLException {
        return new Gasto(
                rs.getInt("idGasto"),
                rs.getInt("idViaje"),
                CategoriaGasto.valueOf(rs.getString("categoria")),
                LocalDate.parse(rs.getString("fecha")),
                rs.getDouble("importe"),
                rs.getString("notas")
        );
    }
}