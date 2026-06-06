package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.utils.SessionManager;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField txtemail;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // ---------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------
    @FXML
    private void login() {

        String email = txtemail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Utils.mostrarWarning("Debes introducir email y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.login(email, password);

        if (usuario == null) {
            Utils.mostrarWarning("Email o contraseña incorrectos.");
            return;
        }
        irPantallaPrincipal(usuario);
    }

    // ---------------------------------------------------------
    // IR A REGISTRO
    // ---------------------------------------------------------
    @FXML
    private void irRegistro() {
        TravelApplication.setRoot("registro");
    }
    // ---------------------------------------------------------
    // ABRIR PANTALLA PRINCIPAL
    // ---------------------------------------------------------
    private void irPantallaPrincipal(Usuario usuario) {
        SessionManager.setUsuarioActual(usuario);  // ← AGREGAR ESTO
        ListaViajesController controller = TravelApplication.setRoot("ListaViajes");
        controller.setUsuario(usuario);
    }
}

/**
 * Primero se rellena el TextField email, después el PasswordField contraseña.
 * Cuando se realicen todas esas acciones se debe activar el Button inicioSesion y cuando se le pulse al Button inicioSesion se debera comprobar:
 * -si el email existe.
 * -si la contraseña existe.
 * -si el email y la contraseña existen y son correctos mandar un mensaje"Iniciando Sesión" y además me deberan llevar a la principal_view.
 * Si no existe el email se debera mandar un mensaje de error "Error, este email no esta registrado, si desea registrarse pulse el boton de Crear Cuenta".
 * Si no existe o no esta registrada con ese email del usuario la contraseña se debera mandar por pantalla un mensaje de error "Error, la contraseña no es correcta"
 * Si no existe o no es correcta ni la contraseña ni el email mandar un mensaje de error "Error, email y contraseña incorrectos".
 */

/**
 * Quiero que si solo has introducido la contraseña o email mande una alerta que por ejemplo si el campo de rellenar el email esta vacio mande una alerta diciendo que debe introducir el email
 * El login debe:
 * Buscar el usuario por email
 * Recuperar su contraseña almacenada (idealmente hasheada)
 * Comparar la contraseña introducida con la almacenada
 *
 *Email y contraseña correctos
 * → Login exitoso
 * → Mensaje:
 * “Iniciando sesión…”
 * → Navegar a la pantalla principal
 *
 */