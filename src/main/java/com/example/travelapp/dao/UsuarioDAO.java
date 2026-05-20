package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final static String SQL_ALL = "SELECT * FROM Usurio";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM Usuario WHERE id = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM Usuario WHERE nombre = ?";
    private final static String SQL_FIND_BY_EMAIL = "SELECT * FROM Usuario WHERE email = ?";
    private final static String SQL_LOGIN = "SELECT * FROM Usuario WHERE email = ? AND password = ?";

    private final static String SQL_INSERT = "INSERT INTO Usuario values()";
    private final static String SQL_UPDATE = "UPDATE Usuario SET ";
    private final static String SQL_DELETE = "DELETE FROM Usuario WHERE id = ?";

    public static List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario =  null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)){
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(nombre, email, password);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public static Usuario findById(int id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(nombre, email, password);
            }
        }
        return usuario;
    }

    public static Usuario findByNombre(String nombre) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NOMBRE)){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombre2 = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(nombre2, email, password);
            }
        }
        return usuario;
    }

    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_EMAIL)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String email2 = rs.getString("email");
                String password = rs.getString("password");
                usuario = new Usuario(nombre, email2, password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario addUsuario(Usuario usuario) throws SQLException {
        if (usuario != null && findByNombre(usuario.getNombre()) == null) {
            try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getEmail());
                ps.executeUpdate();
            }
        }else {
            usuario = null;
        }
        return usuario;
    }

    public static boolean updateUsuario(Usuario usuarioNuevo, Usuario usuarioActual) throws SQLException {
        boolean updated = false;
        if (usuarioNuevo != null && findByNombre(usuarioNuevo.getNombre()) == null && usuarioActual != null && findByNombre(usuarioActual.getNombre()) != null) {
            try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)){
                ps.setString(1, usuarioNuevo.getNombre());
                ps.setString(2, usuarioActual.getNombre());
                ps.setString(3, usuarioActual.getEmail());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteUsuario(int idUsuario) throws SQLException {
        boolean deleted = false;
        if (findById(idUsuario) != null) {
            try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)){
                ps.setInt(1, idUsuario);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }

    public Usuario login(String email, String password) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_LOGIN)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            // Caso 1: el usuario NO existe
            if (!rs.next()) {
                return null; // usuario no registrado
            }

            // Caso 2: el usuario existe → comprobar contraseña
            String passwordBD = rs.getString("password");

            if (!passwordBD.equals(password)) {
                // Usuario existe pero contraseña incorrecta
                return new Usuario("INVALIDO", email, "");
                // Marcamos un usuario especial
            }

            String nombre = rs.getString("nombre");
            String email2 = rs.getString("email");
            String password2 = rs.getString("password");
            return new Usuario(nombre, email2, password2);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
