package com.example.travelapp.model;

import java.util.Objects;

/**
 * Representa un usuario dentro de la aplicación TravelApp.
 * Un usuario contiene información básica necesaria para autenticación
 * y personalización de la experiencia dentro del sistema.
 * Esta clase forma parte del modelo principal y se utiliza en procesos
 * como registro, inicio de sesión y gestión de datos personales.
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;

    /**
     * Constructor utilizado para crear un usuario nuevo sin ID asignado,
     * normalmente antes de insertarlo en la base de datos.
     *
     * @param nombre   nombre del usuario
     * @param email    email del usuario
     * @param password contraseña del usuario
     */
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor completo con validaciones, utilizado cuando el usuario
     * ya existe en la base de datos y dispone de un ID asignado.
     *
     * @param idUsuario identificador único del usuario
     * @param nombre    nombre del usuario
     * @param email     email del usuario
     * @param password  contraseña del usuario
     * @throws IllegalArgumentException si algún dato no es válido
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

    /**
     * Constructor vacío necesario para operaciones de serialización,
     * frameworks o carga dinámica.
     */
    public Usuario() {}

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return ID del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param idUsuario ID del usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Compara dos usuarios por su ID.
     *
     * @param o objeto a comparar
     * @return true si ambos usuarios tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario u)) return false;
        return idUsuario == u.idUsuario;
    }

    /**
     * Genera un hash basado en el ID del usuario.
     *
     * @return hash del usuario
     */
    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    /**
     * Devuelve una representación legible del usuario,
     * útil para mostrarlo en listas o interfaces gráficas.
     *
     * @return cadena con el nombre y el email del usuario
     */
    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}
