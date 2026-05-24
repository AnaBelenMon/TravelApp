package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Emocion;
//import com.example.travelapp.model.TipoEmocion;

import java.time.LocalDate;

public class ListaEmocionesController {

    @FXML private TableView<Emocion> tablaEmociones;

    @FXML private TableColumn<Emocion, Integer> colId;
//    @FXML private TableColumn<Emocion, TipoEmocion> colTipo;
    @FXML private TableColumn<Emocion, Integer> colIntensidad;
    @FXML private TableColumn<Emocion, LocalDate> colFecha;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Emocion> listaEmociones = FXCollections.observableArrayList();
/*
    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdEmocion()));
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipo()));
        colIntensidad.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIntensidad()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));

        // Datos de ejemplo
        listaEmociones.addAll(
                new Emocion(1, 1, TipoEmocion.ALEGRIA, 8,
                        "Momento muy feliz en la Torre Eiffel", LocalDate.of(2026, 4, 13)),

                new Emocion(2, 1, TipoEmocion.SORPRESA, 6,
                        "Impresionada por la Capilla Sixtina", LocalDate.of(2026, 5, 2))
        );

        tablaEmociones.setItems(listaEmociones);
    }*/

    @FXML
    private void nuevaEmocion() {
        System.out.println("Crear nueva emoción");
    }

    @FXML
    private void editarEmocion() {
        System.out.println("Editar emoción seleccionada");
    }

    @FXML
    private void eliminarEmocion() {
        System.out.println("Eliminar emoción seleccionada");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla anterior");
    }
}
