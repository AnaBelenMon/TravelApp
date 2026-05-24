package com.example.travelapp.controllers;

import com.example.travelapp.dao.AlojamientoDAO;
import com.example.travelapp.model.Alojamiento;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ListaAlojamientosController {

    @FXML private TableView<Alojamiento> tablaAlojamientos;

    @FXML private TableColumn<Alojamiento, String> colNombre;
    @FXML private TableColumn<Alojamiento, String> colDireccion;
    @FXML private TableColumn<Alojamiento, LocalDate> colEntrada;
    @FXML private TableColumn<Alojamiento, LocalDate> colSalida;
    @FXML private TableColumn<Alojamiento, Double> colPrecio;
    @FXML private TableColumn<Alojamiento, Integer> colValoracion;

    private ObservableList<Alojamiento> listaAlojamientos = FXCollections.observableArrayList();
    private int idViajeActual;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNombre()));
        colDireccion.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDireccion()));
        colEntrada.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaCheckin()));
        colSalida.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaCheckout()));
        colPrecio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecioTotal()));
        colValoracion.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValoracion()));
    }

    /** Recibe el id del viaje desde la pantalla anterior */
    public void cargarAlojamientosDeViaje(int idViaje) {
        this.idViajeActual = idViaje;
        cargarDesdeBD();
    }

    /** Carga los alojamientos reales desde la BD */
    private void cargarDesdeBD() {
        try {
            listaAlojamientos.setAll(AlojamientoDAO.findByIdViaje(idViajeActual));
            tablaAlojamientos.setItems(listaAlojamientos);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar alojamientos", "No se pudieron cargar los alojamientos.");
        }
    }

    @FXML
    private void nuevoAlojamiento() {
        abrirVentanaEditar(null); // null = modo crear
    }

    @FXML
    private void editarAlojamiento() {
        Alojamiento seleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Selecciona un alojamiento", "Debes seleccionar un alojamiento para editarlo.");
            return;
        }

        abrirVentanaEditar(seleccionado);
    }

    @FXML
    private void eliminarAlojamiento() {
        Alojamiento seleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Selecciona un alojamiento", "Debes seleccionar un alojamiento para eliminarlo.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText("¿Eliminar alojamiento?");
        confirm.setContentText("Se eliminará: " + seleccionado.getNombre());

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                AlojamientoDAO.deleteAlojamientoById(seleccionado.getIdAlojamiento());
                cargarDesdeBD();
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el alojamiento.");
            }
        }
    }

    /** Abre la ventana de crear/editar alojamiento */
    private void abrirVentanaEditar(Alojamiento alojamiento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/travelapp/views/EditarAlojamiento.fxml"));
            Parent root = loader.load();

            EditarAlojamientoController controller = loader.getController();

            // Si estamos editando → cargar datos
            if (alojamiento != null) {
                controller.cargarAlojamiento(alojamiento);
            }

            // Pasar idViaje al formulario
            controller.setIdViaje(idViajeActual);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // ventana modal
            stage.showAndWait();

            // Refrescar tabla al cerrar
            cargarDesdeBD();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
