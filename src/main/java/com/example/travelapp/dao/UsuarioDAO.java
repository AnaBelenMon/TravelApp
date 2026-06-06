package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO<Usuario> {
    private final static String SQL_ALL = "SELECT * FROM Usuario";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM Usuario WHERE idUsuario = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM Usuario WHERE nombre = ?";
    private final static String SQL_FIND_BY_EMAIL = "SELECT * FROM Usuario WHERE email = ?";
    private final static String SQL_LOGIN = "SELECT * FROM Usuario WHERE email = ? AND password = ?";

    private final static String SQL_INSERT = "INSERT INTO Usuario (nombre, email, password) VALUES (?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE Usuario SET nombre = ?, email = ?, password = ? WHERE idUsuario = ?";
    private final static String SQL_DELETE = "DELETE FROM Usuario WHERE idUsuario = ?";

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