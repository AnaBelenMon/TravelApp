package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Museo;

public class ListaMuseosController {

    @FXML private TableView<Museo> tablaMuseos;

    @FXML private TableColumn<Museo, Integer> colId;
    @FXML private TableColumn<Museo, String> colNombre;
    @FXML private TableColumn<Museo, String> colUbicacion;
    @FXML private TableColumn<Museo, Double> colPrecio;
    @FXML private TableColumn<Museo, String> colHorario;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Museo> listaMuseos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNombre()));
        colUbicacion.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getUbicacion()));
        colPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecioEntrada()));
        colHorario.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getHorario()));

        // Datos de ejemplo
        listaMuseos.addAll(
                new Museo("Museo del Louvre","", "Rue de Rivoli","París",
                        17.0, "09:00 - 18:00", "Uno de los museos más importantes del mundo"),

                new Museo("Museos Vaticanos", "","Ciudad del Vaticano","Vaticano",
                        21.0, "09:00 - 18:00", "Colección de arte de la Iglesia Católica")
        );

        tablaMuseos.setItems(listaMuseos);
    }

    @FXML
    private void nuevoMuseo() {
        System.out.println("Crear nuevo museo");
    }

    @FXML
    private void editarMuseo() {
        System.out.println("Editar museo seleccionado");
    }

    @FXML
    private void eliminarMuseo() {
        System.out.println("Eliminar museo seleccionado");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla anterior");
    }
}
