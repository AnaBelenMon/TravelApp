package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Usuario.
 *
 * Gestiona el acceso a datos de usuarios en la base de datos.
 * Permite realizar operaciones CRUD básicas y autenticación (login).
 *
 * IMPORTANTE:
 * - Utiliza ConnectionBD para obtener la conexión.
 * - No aplica transacciones complejas porque la entidad es simple.
 */
public class UsuarioDAO {

    private final static String SQL_ALL = "SELECT * FROM Usuario";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM Usuario WHERE id = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM Usuario WHERE nombre = ?";
    private final static String SQL_FIND_BY_EMAIL = "SELECT * FROM Usuario WHERE email = ?";
    private final static String SQL_LOGIN = "SELECT * FROM Usuario WHERE email = ? AND password = ?";

    private final static String SQL_INSERT = "INSERT INTO Usuario (nombre, email, password) VALUES (?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE Usuario SET nombre = ?, email = ?, password = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM Usuario WHERE id = ?";

    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (ResultSet rs = ConnectionBD.getConnection()
                .createStatement()
                .executeQuery(SQL_ALL)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        }
        return usuarios;
    }

    public Usuario findById(int id) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public Usuario findByNombre(String nombre) throws SQLException {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public Usuario findByEmail(String email) {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_EMAIL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUsuario(Usuario usuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_INSERT)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUsuario(Usuario usuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_UPDATE)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUsuario(int idUsuario) {
        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario findByEmailAndPassword(String email, String password) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_LOGIN)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}