package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador para la pantalla de registro de usuarios.
 * Permite a los nuevos usuarios crear una cuenta proporcionando su email y contraseña.
 *
 * Funcionalidades:
 * - Validación de datos de entrada (email y contraseña).
 * - Registro de usuario en la base de datos.
 * - Mensajes informativos sobre el proceso de registro.
 * - Navegación a la pantalla de login sin cerrar la aplicación.
 *
 * @author Ana Belén Montilla López
 * @version 1.0
 * @since 2026-05-01
 */
public class RegistroController {
    @FXML private TextField txtemail;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Acción del botón "Registrar".
     * Valida los datos, registra el usuario y muestra mensajes informativos.
     * NO cierra la ventana (opción 2 solicitada).
     */
    @FXML
    public void registrar() {

        String email = txtemail.getText().trim();
        String password = txtPassword.getText().trim();
        if (email.isEmpty()) {
            mostrarError("El email no puede estar vacío.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarError("El formato del email no es válido.");
            return;
        }

        if (password.isEmpty()) {
            mostrarError("La contraseña no puede estar vacía.");
            return;
        }

        if (password.length() < 4) {
            mostrarError("La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        if (usuarioDAO.findByEmail(email) != null) {
            mostrarError("Ya existe una cuenta con este email.");
            return;
        }

        try {
            Usuario usuario = new Usuario("Usuario",email, password);
            usuarioDAO.addUsuario(usuario);

            mostrarInfo("Cuenta creada correctamente.");

            // Limpiar campos
            txtemail.clear();
            txtPassword.clear();

        } catch (Exception e) {
            mostrarError("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Acción del botón "Volver al login".
     * Abre la pantalla de login SIN cerrar la aplicación.
     */
    @FXML
    public void volverLogin() {
        TravelApplication.setRoot("Login");
    }

    /**
     * Muestra un mensaje de error.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje informativo.
     */
    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
