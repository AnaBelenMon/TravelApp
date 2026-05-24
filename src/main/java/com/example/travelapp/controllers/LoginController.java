package com.example.travelapp.controllers;

import com.example.travelapp.Application;
import com.example.travelapp.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    @FXML
    private void login() {
        if (UsuarioDAO.login(txtUsuario.getText(), txtPassword.getText())) {
            Application.setRoot("Principal");
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    @FXML
    private void irRegistro() {
        Application.setRoot("Registro");
    }
}
