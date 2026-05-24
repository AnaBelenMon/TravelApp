package com.example.travelapp.model;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

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

    public Usuario() {}

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean nombreValido() {
        return nombre != null && !nombre.isBlank();
    }

    public boolean emailValido() {
        return email != null && email.contains("@");
    }

    public boolean passwordValido() {
        return password != null && password.length() > 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return idUsuario == u.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}