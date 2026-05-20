package com.example.travelapp.controllers.autenticacion;

import com.example.travelapp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {
    public TextField nombreUsuario;
    public TextField emailUsuario;
    public TextField repetirContraseña;
    public Button registrarse;
    public PasswordField contraseñaUsuario;
    public Button inicioSesion;

    @FXML
    public void initialize(){
        registrarse.setDisable(true);

        nombreUsuario.textProperty().addListener((observable, oldValue, newValue) -> {});
        emailUsuario.textProperty().addListener((observable, oldValue, newValue) -> {});
        contraseñaUsuario.textProperty().addListener((observable, oldValue, newValue) -> {});
        inicioSesion.setDisable(true);
    }

    private void validarCampos(){
        boolean nombreExiste = false;
        boolean emailExiste = false;

    }

    public void validarNombreUsuario(ActionEvent actionEvent) {

    }

    public void validarEmailUsuario(ActionEvent actionEvent) {

    }

    public void comprobarMismaContraseña(ActionEvent actionEvent) {

    }

    public void registrarse(ActionEvent actionEvent) {

    }

    public void validarContraseña(ActionEvent actionEvent) {

    }

    public void llevarAInicioSesion() {
        HelloApplication.setRoot("login");
    }
}
/**
 * Para crear una cuenta es necesario que el usuario introduzca:
 * - el nombre de usuario
 * - el email del usuario
 * - la contraseña del usuario
 * - la contraseña repetida
 * Si el nombre del usuario está ya registrado debera mandar un mensaje de error "Error, nombre de usuario no disponible"
 * Si el email del usuario está ya registrado debera mandar un mensaje de error
 * Si las contraseñas no son iguales debera mandar un mensaje de error.
 */