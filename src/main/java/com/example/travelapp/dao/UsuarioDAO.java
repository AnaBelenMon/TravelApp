package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar las operaciones CRUD relacionadas con la entidad
 * {@link Usuario}. Utiliza JDBC para interactuar con la base de datos y
 * convierte los registros obtenidos en objetos del modelo.
 *
 * Este DAO implementa la interfaz {@link GenericDAO} y proporciona métodos
 * adicionales de búsqueda por nombre, email y autenticación mediante login.
 *
 * Cada método utiliza consultas preparadas para garantizar seguridad,
 * evitar inyecciones SQL y asegurar un acceso eficiente a la base de datos.
 */
public class UsuarioDAO implements GenericDAO<Usuario> {
    private final static String SQL_ALL = "SELECT * FROM Usuario";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM Usuario WHERE idUsuario = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM Usuario WHERE nombre = ?";
    private final static String SQL_FIND_BY_EMAIL = "SELECT * FROM Usuario WHERE email = ?";
    private final static String SQL_LOGIN = "SELECT * FROM Usuario WHERE email = ? AND password = ?";

    private final static String SQL_INSERT = "INSERT INTO Usuario (nombre, email, password) VALUES (?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE Usuario SET nombre = ?, email = ?, password = ? WHERE idUsuario = ?";
    private final static String SQL_DELETE = "DELETE FROM Usuario WHERE idUsuario = ?";

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     *
     * @return lista de usuarios
     */
    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()){
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre, email, password);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return usuario encontrado o null si no existe
     */
    @Override
    public Usuario findById(int id) {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Busca un usuario por su nombre.
     *
     * @param nombre nombre del usuario
     * @return usuario encontrado o null si no existe
     */
    public Usuario findByNombre(String nombre) {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idUsuario = rs.getInt("idUsuario");
                String nombre2 = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre2, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Busca un usuario por su email.
     *
     * @param email email del usuario
     * @return usuario encontrado o null si no existe
     */
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String email2 = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre, email2, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * Tras la inserción, se recupera el ID generado automáticamente.
     *
     * @param usuario usuario a insertar
     * @return usuario con su ID actualizado
     */
    @Override
    public Usuario add(Usuario usuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                usuario.setIdUsuario(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario usuario con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    @Override
    public boolean update(Usuario usuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param usuario usuario a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    @Override
    public boolean delete(Usuario usuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, usuario.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Válida las credenciales de un usuario mediante email y contraseña.
     *
     * @param email email del usuario
     * @param password contraseña del usuario
     * @return usuario autenticado o null si las credenciales no son válidas
     */
    public Usuario login(String email, String password) {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_LOGIN)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String email2 = rs.getString("email");
                String password2 = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre, email2, password2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}