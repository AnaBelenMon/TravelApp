package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Recuerdo;
import com.example.travelapp.model.TipoRecuerdo;

public class EditarRecuerdoController {

    @FXML private Label lblTitulo;

    @FXML private TextField txtNombre;
    @FXML private ComboBox<TipoRecuerdo> cmbTipo;
    @FXML private DatePicker dpFecha;
    @FXML private TextArea txtDescripcion;

    private Recuerdo recuerdo; // null = crear, no null = editar

    @FXML
    public void initialize() {
        cmbTipo.getItems().setAll(TipoRecuerdo.values());
    }

    public void cargarRecuerdo(Recuerdo r) {
        this.recuerdo = r;
        lblTitulo.setText("Editar Recuerdo");

        //txtNombre.setText(r.getNombre());
        cmbTipo.setValue(r.getTipo());
        dpFecha.setValue(r.getFecha());
        txtDescripcion.setText(r.getDescripcion());
    }

    @FXML
    private void guardar() {
        System.out.println("Guardando recuerdo…");

        // Aquí luego conectas tu DAO
    }

    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}
