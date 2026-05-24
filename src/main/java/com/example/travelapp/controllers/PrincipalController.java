package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.TipoViaje;
import java.time.LocalDate;

public class PrincipalController {

    @FXML private TableView<Viaje> tablaViajes;
    @FXML private TableColumn<Viaje, Integer> colId;
    @FXML private TableColumn<Viaje, String> colNombre;
    @FXML private TableColumn<Viaje, LocalDate> colFechaInicio;
    @FXML private TableColumn<Viaje, LocalDate> colFechaFin;
    @FXML private TableColumn<Viaje, TipoViaje> colTipo;

    @FXML private Button botonNuevoViaje;
    @FXML private Button botonVerDetalles;
    @FXML private Button botonCerrarSesion;

    private ObservableList<Viaje> listaViajes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdViaje()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNombre()));
        colFechaInicio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaInicio()));
        colFechaFin.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaFin()));
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipoViaje()));

        listaViajes.addAll(
                new Viaje("París", LocalDate.of(2026, 4, 12), LocalDate.of(2026, 4, 20), TipoViaje.CULTURAL, "paris.jpg", "Notas", 1200, "Francia", "París"),
                new Viaje("Roma", LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 7), TipoViaje.ROMANTICO, "roma.jpg", "Notas", 900, "Italia", "Roma")
        );

        tablaViajes.setItems(listaViajes);
    }

    @FXML
    private void nuevoViaje() {
        System.out.println("Crear nuevo viaje");
    }

    @FXML
    private void verDetalles() {
        System.out.println("Ver detalles del viaje seleccionado");
    }

    @FXML
    private void cerrarSesion() {
        System.out.println("Cerrar sesión");
    }

    @FXML private void abrirActividades() { System.out.println("Abrir actividades"); }
    @FXML private void abrirAlojamientos() { System.out.println("Abrir alojamientos"); }
    @FXML private void abrirGastos() { System.out.println("Abrir gastos"); }
    @FXML private void abrirMuseos() { System.out.println("Abrir museos"); }
    @FXML private void abrirObras() { System.out.println("Abrir obras"); }
    @FXML private void abrirRecuerdos() { System.out.println("Abrir recuerdos"); }
    @FXML private void abrirEmociones() { System.out.println("Abrir emociones"); }
}