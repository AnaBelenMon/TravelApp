package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.ElementoCultural;
import com.example.travelapp.model.ElementoCulturalSimple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) encargado de gestionar el acceso a datos
 * de la entidad ElementoCultural.
 *
 * Proporciona operaciones CRUD básicas y consultas específicas
 * sobre la tabla elemento_cultural en la base de datos.
 */
public class ElementoCulturalDAO {

    // =========================
    // CONSULTAS SQL
    // =========================

    /** Consulta para obtener todos los elementos culturales */
    private final static String SQL_ALL = "SELECT * FROM elemento_cultural";

    /** Consulta para buscar un elemento cultural por su ID */
    private final static String SQL_FIND_BY_ID = "SELECT * FROM elemento_cultural WHERE id = ?";

    /** Consulta para buscar elementos culturales por nombre */
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM elemento_cultural WHERE nombre = ?";

    /** Inserta un nuevo elemento cultural */
    private final static String SQL_INSERT = "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";

    /** Actualiza un elemento cultural existente */
    private final static String SQL_UPDATE = "UPDATE elemento_cultural SET nombre = ?, descripcion = ? WHERE id = ?";

    /** Elimina un elemento cultural por ID */
    private final static String SQL_DELETE = "DELETE FROM elemento_cultural WHERE id = ?";

    // =========================
    // MÉTODOS DE CONSULTA
    // =========================

    /**
     * Obtiene todos los elementos culturales de la base de datos.
     *
     * @return lista de elementos culturales
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static List<ElementoCultural> findAll() throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                ElementoCultural elementoCultural =
                        new ElementoCulturalSimple(id, nombre, descripcion);

                elementosCulturales.add(elementoCultural);
            }
        }

        return elementosCulturales;
    }

    /**
     * Busca un elemento cultural por su identificador.
     *
     * @param id identificador del elemento cultural
     * @return elemento cultural encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static ElementoCultural findById(int id) throws SQLException {
        ElementoCultural elementoCultural = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id2 = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");

                    elementoCultural = new ElementoCulturalSimple(id2, nombre, descripcion);
                }
            }
        }

        return elementoCultural;
    }

    /**
     * Busca elementos culturales por nombre exacto.
     *
     * @param nombre nombre del elemento cultural
     * @return lista de elementos culturales que coinciden
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    public static List<ElementoCultural> findByName(String nombre) throws SQLException {
        List<ElementoCultural> elementosCulturales = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre2 = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");

                    ElementoCultural elementoCultural =
                            new ElementoCulturalSimple(id, nombre2, descripcion);

                    elementosCulturales.add(elementoCultural);
                }
            }
        }

        return elementosCulturales;
    }

    // =========================
    // OPERACIONES CRUD
    // =========================

    /**
     * Inserta un nuevo elemento cultural en la base de datos.
     *
     * @param elemento objeto a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean insert(ElementoCultural elemento) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {

            ps.setString(1, elemento.getNombre());
            ps.setString(2, elemento.getDescripcion());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un elemento cultural existente.
     *
     * @param elemento objeto con los nuevos datos
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean update(ElementoCultural elemento) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {

            ps.setString(1, elemento.getNombre());
            ps.setString(2, elemento.getDescripcion());
            ps.setInt(3, elemento.getId());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un elemento cultural por su identificador.
     *
     * @param id identificador del elemento cultural
     * @return true si se eliminó correctamente, false si no existía
     * @throws SQLException si ocurre un error en la base de datos
     */
    public static boolean delete(int id) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}