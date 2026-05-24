package com.example.travelapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static Stage stagePrincipal;

    @Override
    public void start(Stage stage) {
        stagePrincipal = stage;
        setRoot("Login");
    }

    public static <T> T setRoot(String nombreVista) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Application.class.getResource("/vista/" + nombreVista + ".fxml")
            );

            Scene scene = new Scene(loader.load());
            stagePrincipal.setScene(scene);
            stagePrincipal.sizeToScene();
            stagePrincipal.setResizable(false);
            stagePrincipal.show();
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo cargar la vista " + nombreVista);
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
