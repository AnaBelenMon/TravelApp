package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.utils.SessionUtils;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controlador encargado de gestionar el inicio de sesión en la aplicación.
 *
 * Funcionalidades:
 * <ul>
 *     <li>Validar campos de email y contraseña.</li>
 *     <li>Comprobar si el email existe en la base de datos.</li>
 *     <li>Verificar si la contraseña introducida coincide con la almacenada.</li>
 *     <li>Iniciar sesión y redirigir a la lista de viajes.</li>
 *     <li>Navegar a la pantalla de registro.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link UsuarioDAO} para validar credenciales
 * y con {@link SessionUtils} para almacenar el usuario en sesión.
 */
public class LoginController {

    @FXML private TextField txtemail;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Válida los campos, comprueba las credenciales del usuario
     * y, si son correctas, inicia sesión y navega a la pantalla principal.
     */
    @FXML
    private void login() {
        String email = txtemail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        if (email.isEmpty()) {
            Utils.mostrarWarning("Debes introducir el email.");
            return;
        }

        if (password.isEmpty()) {
            Utils.mostrarWarning("Debes introducir la contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.findByEmail(email);

        if (usuario == null) {
            Utils.mostrarWarning("Error: este email no está registrado.\nSi deseas registrarte, pulsa 'Crear Cuenta'.");
            return;
        }

        if (!usuario.getPassword().equals(password)) {
            Utils.mostrarWarning("Error: la contraseña no es correcta.");
            return;
        }

        Utils.mostrarInfo("Iniciando sesión…");
        irPantallaPrincipal(usuario);
    }

    /**
     * Navega a la pantalla de registro.
     */
    @FXML
    private void irRegistro() {
        TravelApplication.setRoot("registro");
    }

    /**
     * Guarda el usuario en sesión y navega a la lista de viajes.
     *
     * @param usuario usuario autenticado
     */
    private void irPantallaPrincipal(Usuario usuario) {
        SessionUtils.setUsuarioActual(usuario);
        ListaViajesController controller = TravelApplication.setRoot("ListaViajes");
        if (controller != null) {
            controller.setUsuario(usuario);
        }
    }
}
