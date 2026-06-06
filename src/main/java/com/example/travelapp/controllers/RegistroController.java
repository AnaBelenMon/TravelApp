package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {
    @FXML private TextField txtemail;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void registrar() {

        String email = txtemail.getText().trim();
        String password = txtPassword.getText().trim();
        if (email.isEmpty()) {
            Utils.mostrarError("El email no puede estar vacío.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Utils.mostrarError("El formato del email no es válido.");
            return;
        }

        if (password.isEmpty()) {
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
            Usuario usuario = new Usuario("Usuario",email, password);
            usuarioDAO.add(usuario);

            Utils.mostrarInfo("Cuenta creada correctamente.");

            // Limpiar campos
            txtemail.clear();
            txtPassword.clear();

        } catch (Exception e) {
            Utils.mostrarError("Error al registrar usuario: " + e.getMessage());
        }
    }

    @FXML
    public void volverLogin() {
        TravelApplication.setRoot("Login");
    }
}
/**
 * Mejorar las validaciones
 * Voy a quitar la pantalla principal y voy ha poner el boton de cerrar sesion en ListaViaje
 */