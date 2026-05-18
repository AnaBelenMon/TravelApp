package com.example.travelapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static Stage stagePrincipal;

    @Override
    public void start(Stage stage) {
        stagePrincipal = stage;
        setRoot("login"); // Carga login.fxml desde /vista/login.fxml
    }

    public static void setRoot(String nombreVista) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/vista/" + nombreVista + ".fxml")
            );

            Scene scene = new Scene(loader.load());
            stagePrincipal.setScene(scene);
            stagePrincipal.sizeToScene();
            stagePrincipal.setResizable(false);
            stagePrincipal.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo cargar la vista " + nombreVista);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
