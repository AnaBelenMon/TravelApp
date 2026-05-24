package com.example.travelapp.controllers;

import com.example.travelapp.model.Emocion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Recuerdo;
import com.example.travelapp.model.TipoRecuerdo;

import java.time.LocalDate;

public class ListaRecuerdosController {

    @FXML private TableView<Recuerdo> tablaRecuerdos;

    @FXML private TableColumn<Recuerdo, Integer> colId;
    @FXML private TableColumn<Recuerdo, String> colNombre;
    @FXML private TableColumn<Recuerdo, TipoRecuerdo> colTipo;
    @FXML private TableColumn<Recuerdo, LocalDate> colFecha;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Recuerdo> listaRecuerdos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdRecuerdo()));
        //colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNombre()));
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipo()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));

        // Datos de ejemplo
        listaRecuerdos.addAll(
                new Recuerdo("Entrada Coliseo", "", "Entrada física del Coliseo Romano", LocalDate.of(2026, 5, 2), Emocion.EUFORIA,TipoRecuerdo.AUDIO,false, "")
        );

        tablaRecuerdos.setItems(listaRecuerdos);
    }

    @FXML
    private void nuevoRecuerdo() {
        System.out.println("Crear nuevo recuerdo");
    }

    @FXML
    private void editarRecuerdo() {
        System.out.println("Editar recuerdo seleccionado");
    }

    @FXML
    private void eliminarRecuerdo() {
        System.out.println("Eliminar recuerdo seleccionado");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla anterior");
    }
}
