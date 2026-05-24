package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Obra;

public class ListaObrasController {

    @FXML private TableView<Obra> tablaObras;

    @FXML private TableColumn<Obra, Integer> colId;
    @FXML private TableColumn<Obra, String> colNombre;
    @FXML private TableColumn<Obra, String> colAutor;
    @FXML private TableColumn<Obra, Integer> colAnio;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Obra> listaObras = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNombre()));
        colAutor.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAutor()));
        //colAnio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAnio()));

        // Datos de ejemplo
        listaObras.addAll(
                new Obra("La Gioconda","" ,"Leonardo da Vinci", "Una de las pinturas más famosas del mundo"),

                new Obra("La Escuela de Atenas","", "Rafael", "Fresco renacentista ubicado en el Vaticano")
        );

        tablaObras.setItems(listaObras);
    }

    @FXML
    private void nuevaObra() {
        System.out.println("Crear nueva obra");
    }

    @FXML
    private void editarObra() {
        System.out.println("Editar obra seleccionada");
    }

    @FXML
    private void eliminarObra() {
        System.out.println("Eliminar obra seleccionada");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla anterior");
    }
}
