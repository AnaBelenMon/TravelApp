package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.CategoriaGasto;

import java.time.LocalDate;

public class EditarGastoController {

    @FXML private Label lblTitulo;

    @FXML private TextField txtConcepto;
    @FXML private ComboBox<CategoriaGasto> cmbCategoria;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtImporte;
    @FXML private TextArea txtDescripcion;

    private Gasto gasto; // null = crear, no null = editar

    @FXML
    public void initialize() {
        cmbCategoria.getItems().setAll(CategoriaGasto.values());
    }

    public void cargarGasto(Gasto g) {
        this.gasto = g;
        lblTitulo.setText("Editar Gasto");

        //txtConcepto.setText(g.getConcepto());
        cmbCategoria.setValue(g.getCategoria());
        dpFecha.setValue(g.getFecha());
        txtImporte.setText(String.valueOf(g.getImporte()));
        //txtDescripcion.setText(g.getDescripcion());
    }

    @FXML
    private void guardar() {
        System.out.println("Guardando gasto…");

        // Aquí luego conectas tu DAO
    }

    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}
