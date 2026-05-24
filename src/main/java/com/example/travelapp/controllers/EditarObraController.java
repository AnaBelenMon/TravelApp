package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Obra;

public class EditarObraController {

    @FXML private Label lblTitulo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtAutor;
    @FXML private TextField txtAnio;
    @FXML private TextArea txtDescripcion;

    private Obra obra; // null = crear, no null = editar

    public void cargarObra(Obra o) {
        this.obra = o;
        lblTitulo.setText("Editar Obra");

        txtNombre.setText(o.getNombre());
        txtAutor.setText(o.getAutor());
        //txtAnio.setText(String.valueOf(o.getAnio()));
        txtDescripcion.setText(o.getDescripcion());
    }

    @FXML
    private void guardar() {
        System.out.println("Guardando obra…");

        // Aquí luego conectas tu DAO
    }

    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}
