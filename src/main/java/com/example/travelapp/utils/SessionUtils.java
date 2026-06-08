package com.example.travelapp.utils;

import com.example.travelapp.model.Usuario;

/**
 * Clase utilitaria encargada de gestionar la sesión del usuario autenticado
 * dentro de la aplicación.
 *
 * <p>Permite:</p>
 * <ul>
 *     <li>Guardar el usuario que ha iniciado sesión.</li>
 *     <li>Recuperar el usuario actual desde cualquier parte de la aplicación.</li>
 *     <li>Obtener rápidamente el ID del usuario autenticado.</li>
 *     <li>Cerrar sesión limpiando los datos almacenados.</li>
 * </ul>
 *
 * <p>Esta clase actúa como un contenedor estático, accesible globalmente,
 * lo que facilita la comunicación entre controladores sin necesidad de
 * pasar objetos manualmente.</p>
 */
public class SessionUtils {
    private static Usuario usuarioActual = null;

    /**
     * Establece el usuario que ha iniciado sesión.
     *
     * @param usuario usuario autenticado
     */
    public static void setUsuarioActual(Usuario usuario) {
        SessionUtils.usuarioActual = usuario;
    }

    /**
     * Devuelve el usuario actualmente autenticado.
     *
     * @return usuario en sesión o null si no hay sesión activa
     */
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Devuelve el ID del usuario autenticado.
     *
     * @return ID del usuario o -1 si no hay sesión activa
     */
    public static int getIdUsuarioActual() {
        return usuarioActual != null ? usuarioActual.getIdUsuario() : -1;
    }

    /**
     * Limpia la sesión actual, cerrando la sesión del usuario.
     */
    public static void limpiarSesion() {
        usuarioActual = null;
    }
}
