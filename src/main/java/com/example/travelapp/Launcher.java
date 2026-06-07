package com.example.travelapp;

/**
 * Clase lanzadora de la aplicación JavaFX.
 *
 * <p>JavaFX requiere, en algunos entornos, una clase separada de la clase
 * principal que extiende {@link javafx.application.Application} para poder
 * iniciar correctamente la aplicación, especialmente cuando se empaqueta en
 * archivos JAR.</p>
 *
 * <p>Esta clase simplemente delega el arranque en {@link TravelApplication},
 * que contiene la lógica principal de inicialización.</p>
 */
public class Launcher {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        javafx.application.Application.launch(TravelApplication.class, args);
    }
}
