package com.example.travelapp.utils;

import com.example.travelapp.model.Usuario;

/**
 * Gestiona la sesión del usuario autenticado en la aplicación
 */
public class SessionManager {
    private static Usuario usuarioActual = null;

    public static void setUsuarioActual(Usuario usuario) {
        SessionManager.usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static int getIdUsuarioActual() {
        return usuarioActual != null ? usuarioActual.getIdUsuario() : -1;
    }

    public static void limpiarSesion() {
        usuarioActual = null;
    }

    public static boolean estaLogueado() {
        return usuarioActual != null;
    }
}