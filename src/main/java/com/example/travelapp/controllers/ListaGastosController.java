package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.CategoriaGasto;

import java.time.LocalDate;

public class ListaGastosController {

    @FXML private TableView<Gasto> tablaGastos;

    @FXML private TableColumn<Gasto, Integer> colId;
    @FXML private TableColumn<Gasto, String> colConcepto;
    @FXML private TableColumn<Gasto, CategoriaGasto> colCategoria;
    @FXML private TableColumn<Gasto, LocalDate> colFecha;
    @FXML private TableColumn<Gasto, Double> colImporte;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private ObservableList<Gasto> listaGastos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdGasto()));
        //colConcepto.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getConcepto()));
        colCategoria.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getCategoria()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));
        colImporte.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getImporte()));

        // Datos de ejemplo
        listaGastos.addAll(
                new Gasto(CategoriaGasto.COMIDA, LocalDate.of(2026, 4, 13), 25.50, "Almuerzo en restaurante"),

                new Gasto(CategoriaGasto.TRANSPORTE, LocalDate.of(2026, 4, 14), 12.00, "Metro y bus")
        );

        tablaGastos.setItems(listaGastos);
    }

    @FXML
    private void nuevoGasto() {
        System.out.println("Crear nuevo gasto");
    }

    @FXML
    private void editarGasto() {
        System.out.println("Editar gasto seleccionado");
    }

    @FXML
    private void eliminarGasto() {
        System.out.println("Eliminar gasto seleccionado");
    }

    @FXML
    private void volver() {
        System.out.println("Volver a pantalla anterior");
    }
}
