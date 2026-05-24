package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Emocion;

/**
 * Controlador de la vista de edición de emociones.
 *
 * Permite crear o editar una emoción asociada a un recuerdo o actividad.
 * Gestiona la interfaz gráfica y la carga de datos en los componentes.
 */
public class EditarEmocionController {

    @FXML
    private Label lblTitulo;

    @FXML
    private Slider sliderIntensidad;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea txtDescripcion;

    private Emocion emocion; // null = crear, no null = editar

    /**
     * Carga los datos de una emoción en la vista para su edición.
     *
     * @param e emoción a editar
     */
    public void cargarEmocion(Emocion e) {
        this.emocion = e;
        lblTitulo.setText("Editar Emoción");

        // Aquí se rellenarán los campos cuando el modelo esté completo
        // sliderIntensidad.setValue(e.getIntensidad());
        // dpFecha.setValue(e.getFecha());
        // txtDescripcion.setText(e.getDescripcion());
    }

    /**
     * Guarda la emoción actual.
     *
     * Actualmente en desarrollo (conexión con DAO pendiente).
     */
    @FXML
    private void guardar() {
        System.out.println("Guardando emoción…");

        // Aquí luego conectas tu DAO
    }

    /**
     * Cancela la operación y vuelve a la pantalla anterior.
     */
    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}