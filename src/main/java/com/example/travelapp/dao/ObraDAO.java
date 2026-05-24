package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Obra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Obra.
 *
 * Gestiona el acceso a datos de obras artísticas almacenadas en la base de datos,
 * incluyendo operaciones CRUD y consultas específicas.
 *
 * Esta clase trabaja con una relación 1 a 1 entre las tablas elemento_cultural y obra,
 * por lo que las operaciones de inserción, actualización y eliminación deben realizarse
 * de forma transaccional para mantener la integridad de los datos.
 */
public class ObraDAO {

    private final static String SQL_ALL =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra";

    private final static String SQL_FIND_BY_ID =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra " +
                    "WHERE ec.idElemento = ?";

    private final static String SQL_FIND_BY_NOMBRE =
            "SELECT * FROM elemento_cultural ec " +
                    "JOIN obra o ON ec.idElemento = o.idObra " +
                    "WHERE ec.nombre = ?";

    private final static String SQL_INSERT_ELEMENTO =
            "INSERT INTO elemento_cultural (nombre, descripcion) VALUES (?, ?)";

    private final static String SQL_INSERT_OBRA =
            "INSERT INTO obra (idObra, autor, estilo) VALUES (?, ?, ?)";

    private final static String SQL_UPDATE_ELEMENTO =
            "UPDATE elemento_cultural SET nombre=?, descripcion=? WHERE idElemento=?";

    private final static String SQL_UPDATE_OBRA =
            "UPDATE obra SET autor=?, estilo=? WHERE idObra=?";

    private final static String SQL_DELETE_OBRA =
            "DELETE FROM obra WHERE idObra=?";

    private final static String SQL_DELETE_ELEMENTO =
            "DELETE FROM elemento_cultural WHERE idElemento=?";

    /**
     * Obtiene todas las obras registradas en la base de datos.
     *
     * @return lista de objetos Obra
     * @throws SQLException si ocurre un error durante la consulta SQL
     */
    public static List<Obra> findAll() throws SQLException {
        List<Obra> obras = new ArrayList<>();

        try (ResultSet rs = ConnectionBD.getConnection()
                .createStatement()
                .executeQuery(SQL_ALL)) {

            while (rs.next()) {
                int idElemento = rs.getInt("idElemento");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String autor = rs.getString("autor");
                String estilo = rs.getString("estilo");

                Obra obra = new Obra(idElemento, nombre, descripcion, autor, estilo);
                obras.add(obra);
            }
        }

        return obras;
    }

    /**
     * Busca una obra por su identificador.
     *
     * @param id identificador de la obra
     * @return Obra encontrada o null si no existe
     * @throws SQLException si ocurre un error SQL
     */
    public static Obra findById(int id) throws SQLException {
        Obra obra = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idElemento = rs.getInt("idElemento");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String autor = rs.getString("autor");
                    String estilo = rs.getString("estilo");
                    obra = new Obra(idElemento, nombre, descripcion, autor, estilo);
                }
            }
        }

        return obra;
    }

    /**
     * Busca obras por nombre exacto.
     *
     * @param nombre nombre de la obra
     * @return lista de obras que coinciden con el nombre
     * @throws SQLException si ocurre un error SQL
     */
    public static List<Obra> findByNombre(String nombre) throws SQLException {
        List<Obra> obras = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idElemento = rs.getInt("idElemento");
                    String nombre2 = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String autor = rs.getString("autor");
                    String estilo = rs.getString("estilo");

                    Obra obra = new Obra(idElemento, nombre2, descripcion, autor, estilo);
                    obras.add(obra);
                }
            }
        }

        return obras;
    }

    /**
     * Inserta una obra en la base de datos.
     *
     * Primero inserta en elemento_cultural y posteriormente en obra,
     * asegurando la consistencia mediante transacción manual.
     *
     * @param obra objeto a insertar
     * @return true si la inserción fue correcta
     * @throws SQLException si ocurre un error SQL
     */
    public static boolean insert(Obra obra) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // 1. Insert en tabla padre (elemento_cultural)
            PreparedStatement psPadre = conn.prepareStatement(SQL_INSERT_ELEMENTO);
            psPadre.setString(1, obra.getNombre());
            psPadre.setString(2, obra.getDescripcion());
            psPadre.executeUpdate();

            // 2. Recuperar ID generado (sin getGeneratedKeys)
            PreparedStatement psSelect = conn.prepareStatement(
                    "SELECT idElemento FROM elemento_cultural " +
                            "WHERE nombre = ? AND descripcion = ? " +
                            "ORDER BY idElemento DESC LIMIT 1"
            );

            psSelect.setString(1, obra.getNombre());
            psSelect.setString(2, obra.getDescripcion());

            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) {
                throw new SQLException("No se pudo recuperar el ID generado");
            }

            int idGenerado = rs.getInt(1);
            obra.setId(idGenerado);

            // 3. Insert en tabla hija (obra)
            PreparedStatement psHijo = conn.prepareStatement(SQL_INSERT_OBRA);
            psHijo.setInt(1, idGenerado);
            psHijo.setString(2, obra.getAutor());
            psHijo.setString(3, obra.getEstilo());
            psHijo.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * Actualiza una obra existente.
     *
     * Se actualizan tanto los datos de elemento_cultural como los de obra
     * dentro de una transacción para mantener consistencia.
     *
     * @param obra objeto con los datos actualizados
     * @return true si la actualización fue correcta
     * @throws SQLException si ocurre un error SQL
     */
    public static boolean update(Obra obra) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            // Actualización tabla padre
            PreparedStatement psPadre = conn.prepareStatement(SQL_UPDATE_ELEMENTO);
            psPadre.setString(1, obra.getNombre());
            psPadre.setString(2, obra.getDescripcion());
            psPadre.setInt(3, obra.getId());
            psPadre.executeUpdate();

            // Actualización tabla hija
            PreparedStatement psHijo = conn.prepareStatement(SQL_UPDATE_OBRA);
            psHijo.setString(1, obra.getAutor());
            psHijo.setString(2, obra.getEstilo());
            psHijo.setInt(3, obra.getId());
            psHijo.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * Elimina una obra de la base de datos.
     *
     * Primero elimina la tabla hija (obra) y después la tabla padre
     * (elemento_cultural) para mantener integridad referencial.
     *
     * @param id identificador de la obra
     * @return true si la eliminación fue correcta
     * @throws SQLException si ocurre un error SQL
     */
    public static boolean delete(int id) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        conn.setAutoCommit(false);

        try {
            PreparedStatement psHijo = conn.prepareStatement(SQL_DELETE_OBRA);
            psHijo.setInt(1, id);
            psHijo.executeUpdate();

            PreparedStatement psPadre = conn.prepareStatement(SQL_DELETE_ELEMENTO);
            psPadre.setInt(1, id);
            psPadre.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}