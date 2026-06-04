package com.example.travelapp.model;

import java.util.Objects;

/**
 * Representa un usuario dentro de la aplicación TravelApp.
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;

    /**
     * Constructor básico para creación de usuario sin ID.
     * @param nombre nombre del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     */
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor completo con validaciones.
     *
     * @param idUsuario identificador del usuario
     * @param nombre nombre del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     * @throws IllegalArgumentException si los datos no son válidos
     */
    public Usuario(int idUsuario, String nombre, String email, String password) {
        if (idUsuario < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /** Constructor vacío */
    public Usuario() {}

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /** @return id del usuario */
    public int getIdUsuario() {
        return idUsuario;
    }

    /** @param idUsuario establece el id del usuario */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** @return nombre del usuario */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre establece el nombre del usuario */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return email del usuario */
    public String getEmail() {
        return email;
    }

    /** @param email establece el email del usuario */
    public void setEmail(String email) {
        if (!email.contains("@") || !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("El email no es válido.");
        this.email = email;
    }

    /** @return contraseña del usuario */
    public String getPassword() {
        return password;
    }

    /** @param password establece la contraseña del usuario */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Comprueba si el nombre es válido.
     */
    public boolean nombreValido() {
        return nombre != null && !nombre.isBlank();
    }

    /**
     * Comprueba si el email es válido.
     */
    public boolean emailValido() {
        return email != null && email.contains("@");
    }

    /**
     * Comprueba si la contraseña es válida (mínimo 6 caracteres).
     */
    public boolean passwordValido() {
        return password != null && password.length() > 5;
    }

    /**
     * Dos usuarios son iguales si tienen el mismo id.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return idUsuario == u.idUsuario;
    }

    /**
     * Genera el hash basado en el id del usuario.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    /**
     * Representación en texto del usuario.
     */
    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}