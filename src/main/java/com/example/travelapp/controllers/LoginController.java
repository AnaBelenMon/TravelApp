package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void login() {
        System.out.println("Botón pulsado");

        String email = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        try {
            Usuario usuario = usuarioDAO.findByEmailAndPassword(email, password);

            System.out.println("Resultado consulta: " + usuario);

            if (usuario != null) {
                System.out.println("Login correcto");
                TravelApplication.setRoot("Principal");
            } else {
                System.out.println("Credenciales incorrectas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irRegistro() {
        TravelApplication.setRoot("Registro");
    }

    @FXML
    private void cerrarSesion() {
        TravelApplication.setRoot("Login");
    }


}
