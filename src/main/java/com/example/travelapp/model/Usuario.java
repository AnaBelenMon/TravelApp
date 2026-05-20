package com.example.travelapp.model;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;

    /**
     *
     * @param idUsuario
     * @param nombre
     * @param email
     * @param password
     */
    public Usuario(String nombre, String email, String password) {
        if (idUsuario < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña no es válida");
        }
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario() {}

    /**
     *
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean nombreValido() {
        return nombre != null && !nombre.isBlank();
    }

    /**
     *
     * @return
     */
    public boolean emailValido() {
        return email != null && email.contains("@");
    }

    /**
     *
     * @return
     */
    public boolean passwordValido() {
        return password != null && password.length() > 5;
    }

    /**
     *
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return idUsuario == u.idUsuario;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
