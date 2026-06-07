package com.example.travelapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 *
 * <p>Extiende {@link javafx.application.Application} y se encarga de:</p>
 * <ul>
 *     <li>Inicializar la ventana principal (Stage).</li>
 *     <li>Cargar la primera vista al iniciar la aplicación.</li>
 *     <li>Proporcionar un método estático {@code setRoot()} para cambiar de vista
 *         desde cualquier controlador.</li>
 * </ul>
 *
 * <p>Esta clase actúa como un gestor central de navegación entre pantallas,
 * permitiendo cargar archivos FXML y devolver su controlador asociado.</p>
 */
public class TravelApplication extends javafx.application.Application {

    /** Ventana principal de la aplicación. */
    private static Stage stagePrincipal;

    /**
     * Método de inicio de JavaFX.
     * Se ejecuta automáticamente al lanzar la aplicación.
     *
     * @param stage ventana principal proporcionada por JavaFX
     */
    @Override
    public void start(Stage stage) {
        stagePrincipal = stage;
        setRoot("Login"); // Vista inicial
    }

    /**
     * Cambia la vista actual cargando un archivo FXML desde la carpeta /vista/.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Carga el archivo FXML indicado.</li>
     *     <li>Actualiza la escena del Stage principal.</li>
     *     <li>Devuelve el controlador asociado a la vista cargada.</li>
     * </ul>
     *
     * @param nombreVista nombre del archivo FXML sin extensión
     * @param <T> tipo del controlador asociado a la vista
     * @return instancia del controlador o null si ocurre un error
     */
    public static <T> T setRoot(String nombreVista) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    TravelApplication.class.getResource("/vista/" + nombreVista + ".fxml")
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

    /**
     * Método main estándar para lanzar la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}
