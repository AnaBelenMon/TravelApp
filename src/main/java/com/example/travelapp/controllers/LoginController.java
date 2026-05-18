package com.example.travelapp.controllers;

import com.example.travelapp.HelloApplication;
import com.example.travelapp.dao.UsuarioDAO;
import com.example.travelapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField email;
    @FXML private PasswordField contrasena;
    @FXML private Button inicioSesion;
    @FXML private Button llevarARegistro;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        inicioSesion.setDisable(true);

        email.textProperty().addListener((obs, oldVal, newVal) -> validarCampos());
        contrasena.textProperty().addListener((obs, oldVal, newVal) -> validarCampos());
    }

    private void validarCampos() {
        boolean emailOk = !email.getText().trim().isEmpty();
        boolean passOk = !contrasena.getText().trim().isEmpty();
        inicioSesion.setDisable(!(emailOk && passOk));
    }

    @FXML
    public void iniciarSesion(ActionEvent event) {
        String emailIntroducido = email.getText();
        String password = contrasena.getText();

        // Validación de campos vacíos
        if (emailIntroducido.isBlank() || password.isBlank()) {
            mostrarAlerta("Campos incompletos", "Por favor, rellena todos los campos.");
            return;
        }

        Usuario usuario = usuarioDAO.login(emailIntroducido, password);

        // Usuario NO registrado
        if (usuario == null) {
            mostrarAlerta("Usuario no registrado",
                    "Error, este email no está registrado.\nSi deseas registrarte, pulsa 'Crear Cuenta'.");
            return;
        }

        // Contraseña incorrecta
        if (usuario.getIdUsuario() == -1) {
            mostrarAlerta("Contraseña incorrecta",
                    "Error, la contraseña no es correcta.");
            return;
        }

        // Login correcto
        mostrarInfo("Iniciando sesión", "Bienvenido, " + usuario.getNombre() + ".");
        cargarPantallaPrincipal(usuario);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cargarPantallaPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Principal.fxml"));
            Parent root = loader.load();

            // Pasar el usuario al controlador principal
            PrincipalController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage stage = (Stage) email.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onclickButonCrearCuenta() {
        HelloApplication.setRoot("registro");
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


/**
 * Primero se rellena el TextField email, despues el PasswordField contraseña.
 * Cuando se realicen todas esas acciones se debe activar el Button inicioSesion y cuando se le pulse al Button inicioSesion se debera comprobar:
 * -si el email existe.
 * -si la contraseña existe.
 * -si el email y la contraseña existen y son correctos mandar un mensaje"Iniciando Sesión" y además me deberan llevar a la principal_view.
 * Si no existe el email se debera mandar un mensaje de error "Error, este email no esta registrado, si desea registrarse pulse el boton de Crear Cuenta".
 * Si no existe o no esta registrada con ese email del usuario la contraseña se debera mandar por pantalla un mensaje de error "Error, la contraseña no es correcta"
 * Si no existe o no es correcta ni la contraseña ni el email mandar un mensaje de error "Error, email y contraseña incorrectos".
 */

