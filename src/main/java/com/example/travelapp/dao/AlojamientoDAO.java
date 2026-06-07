package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.enums.TipoAlojamiento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar las operaciones CRUD relacionadas con la entidad
 * {@link Alojamiento}. Utiliza JDBC para interactuar con la base de datos y
 * convierte los registros obtenidos en objetos del modelo.
 * Este DAO implementa la interfaz {@link GenericDAO} y proporciona métodos
 * específicos de búsqueda por nombre, tipo, dirección, ciudad y país.
 * Cada método utiliza consultas preparadas para evitar inyecciones SQL y
 * garantizar un acceso seguro y eficiente a la base de datos.
 */
public class AlojamientoDAO implements GenericDAO<Alojamiento> {
    private final static String SQL_ALL = "SELECT * FROM alojamiento";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM alojamiento WHERE idAlojamiento = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM alojamiento WHERE nombre=?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM alojamiento WHERE tipo=?";
    private final static String SQL_FIND_BY_DIRECCION = "SELECT * FROM alojamiento WHERE direccion=?";
    private final static String SQL_FIND_BY_CIUDAD = "SELECT * FROM alojamiento WHERE ciudad = ?";
    private final static String SQL_FIND_BY_PAIS = "SELECT * FROM alojamiento WHERE pais = ?";

    private final static String SQL_INSERT = "INSERT INTO alojamiento(nombre, tipo, direccion, ciudad, pais) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE alojamiento SET nombre=?, tipo=?, direccion=?, ciudad=?, pais=? WHERE idAlojamiento=?";
    private final static String SQL_DELETE = "DELETE FROM alojamiento WHERE idAlojamiento=?";

    /**
     * Obtiene todos los alojamientos registrados en la base de datos.
     *
     * @return lista de alojamientos
     */
    public List<Alojamiento> findAll() {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                String nombre = rs.getString("nombre");
                TipoAlojamiento tipo = TipoAlojamiento.valueOf(rs.getString("tipo"));
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, nombre, tipo, direccion, ciudad, pais, valoracion);
                alojamientos.add(alojamiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alojamientos;
    }

    /**
     * Busca un alojamiento por su identificador único.
     *
     * @param id identificador del alojamiento
     * @return alojamiento encontrado o null si no existe
     */
    public Alojamiento findById(int id) {
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                String nombre = rs.getString("nombre");
                TipoAlojamiento tipo = TipoAlojamiento.valueOf(rs.getString("tipo"));
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, nombre, tipo, direccion, ciudad, pais, valoracion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alojamiento;
    }

    /**
     * Busca alojamientos cuyo nombre coincida con el proporcionado.
     *
     * @param nombre nombre del alojamiento
     * @return lista de alojamientos coincidentes
     */
    public List<Alojamiento> findByNombre(String nombre) {
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlojamiento = rs.getInt("idAlojamiento");
                String nombre2 = rs.getString("nombre");
                TipoAlojamiento tipo = TipoAlojamiento.valueOf(rs.getString("tipo"));
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int valoracion = rs.getInt("valoracion");
                alojamiento = new Alojamiento(idAlojamiento, nombre2, tipo, direccion, ciudad, pais, valoracion);
                alojamientos.add(alojamiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alojamientos;
    }

    /**
     * Inserta un nuevo alojamiento en la base de datos.
     * Tras la inserción, se recupera el ID generado automáticamente.
     *
     * @param alojamiento alojamiento a insertar
     * @return alojamiento con su ID actualizado
     */
    public Alojamiento add(Alojamiento alojamiento) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getTipo().name());
            ps.setString(3, alojamiento.getDireccion());
            ps.setString(4, alojamiento.getCiudad());
            ps.setString(5, alojamiento.getPais());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setIdAlojamiento(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alojamiento;
    }

    /**
     * Actualiza los datos de un alojamiento existente.
     *
     * @param alojamiento alojamiento con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean update(Alojamiento alojamiento) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getTipo().name());
            ps.setString(3, alojamiento.getDireccion());
            ps.setString(4, alojamiento.getCiudad());
            ps.setString(5, alojamiento.getPais());
            ps.setInt(6, alojamiento.getIdAlojamiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un alojamiento de la base de datos.
     *
     * @param alojamiento alojamiento a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean delete(Alojamiento alojamiento) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, alojamiento.getIdAlojamiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}