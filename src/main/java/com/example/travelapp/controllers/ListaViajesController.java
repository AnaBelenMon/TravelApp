package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.TipoViaje;

import java.time.LocalDate;

public class ListaViajesController {

    @FXML private TableView<Viaje> tablaViajes;

    @FXML private TableColumn<Viaje, Integer> colId;
    @FXML private TableColumn<Viaje, String> colNombre;
    @FXML private TableColumn<Viaje, LocalDate> colFechaInicio;
    @FXML private TableColumn<Viaje, LocalDate> colFechaFin;
    @FXML private TableColumn<Viaje, TipoViaje> colTipo;
    @FXML private TableColumn<Viaje, String> colCiudad;
    @FXML private TableColumn<Viaje, String> colPais;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Viaje> listaViajes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdViaje()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNombre()));
        colFechaInicio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaInicio()));
        colFechaFin.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaFin()));
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipoViaje()));
        colCiudad.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDestinoCiudad()));
        colPais.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDestinoPais()));

        // Datos de ejemplo (puedes quitarlos cuando conectes la BD)
        listaViajes.addAll(
                new Viaje("París", LocalDate.of(2026, 4, 12), LocalDate.of(2026, 4, 20),
                        TipoViaje.CULTURAL, null, "", 1200, "Francia", "París"),

                new Viaje("Roma", LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 7),
                        TipoViaje.ROMANTICO, null, "", 900, "Italia", "Roma")
        );

        tablaViajes.setItems(listaViajes);
    }

    @FXML
    private void nuevoViaje() {
        System.out.println("Crear nuevo viaje");
    }

    @FXML
    private void editarViaje() {
        System.out.println("Editar viaje seleccionado");
    }

    @FXML
    private void eliminarViaje() {
        System.out.println("Eliminar viaje seleccionado");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla principal");
    }
}
