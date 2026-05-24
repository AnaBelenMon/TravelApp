package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Museo;

public class EditarMuseoController {

    @FXML private Label lblTitulo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtUbicacion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtHorario;
    @FXML private TextArea txtDescripcion;

    private Museo museo; // null = crear, no null = editar

    public void cargarMuseo(Museo m) {
        this.museo = m;
        lblTitulo.setText("Editar Museo");

        txtNombre.setText(m.getNombre());
        txtUbicacion.setText(m.getUbicacion());
        txtPrecio.setText(String.valueOf(m.getPrecioEntrada()));
        txtHorario.setText(m.getHorario());
        txtDescripcion.setText(m.getDescripcion());
    }

    @FXML
    private void guardar() {
        System.out.println("Guardando museo…");

        // Aquí luego conectas tu DAO
    }

    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}
