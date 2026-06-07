package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador encargado de gestionar el registro de nuevos usuarios
 * dentro de la aplicación TravelApp.
 *
 * Funcionalidades:
 * <ul>
 *     <li>Validar email y contraseña con reglas estrictas.</li>
 *     <li>Comprobar si el email ya está registrado.</li>
 *     <li>Crear un nuevo usuario en la base de datos.</li>
 *     <li>Navegar de vuelta a la pantalla de login.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link UsuarioDAO} para insertar
 * nuevos usuarios y utiliza {@link Utils} para mostrar alertas.
 */
public class RegistroController {

    @FXML private TextField txtemail;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Valida los campos del formulario y registra un nuevo usuario.
     * Aplica validaciones estrictas:
     * <ul>
     *     <li>Email no vacío.</li>
     *     <li>Email con formato válido.</li>
     *     <li>Contraseña no vacía.</li>
     *     <li>Contraseña con mínimo 4 caracteres.</li>
     *     <li>Email no registrado previamente.</li>
     * </ul>
     */
    @FXML
    public void registrar() {
        String email = txtemail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        if (email.isBlank()) {
            Utils.mostrarError("El email no puede estar vacío.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Utils.mostrarError("El formato del email no es válido.");
            return;
        }

        if (password.isBlank()) {
            Utils.mostrarError("La contraseña no puede estar vacía.");
            return;
        }

        if (password.length() < 4) {
            Utils.mostrarError("La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        if (usuarioDAO.findByEmail(email) != null) {
            Utils.mostrarError("Ya existe una cuenta con este email.");
            return;
        }

        try {
            Usuario usuario = new Usuario("Usuario", email, password);
            usuarioDAO.add(usuario);

            Utils.mostrarInfo("Cuenta creada correctamente.");
            txtemail.clear();
            txtPassword.clear();

        } catch (Exception e) {
            Utils.mostrarError("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Navega de vuelta a la pantalla de inicio de sesión.
     */
    @FXML
    public void volverLogin() {
        TravelApplication.setRoot("Login");
    }
}