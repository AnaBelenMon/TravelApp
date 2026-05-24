package com.example.travelapp.controllers;

import com.example.travelapp.dao.ActividadDAO;
import com.example.travelapp.model.Actividad;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ListaActividadesController {

    @FXML private TableView<Actividad> tablaActividades;

    @FXML private TableColumn<Actividad, String> colNombre;
    @FXML private TableColumn<Actividad, String> colFecha;
    @FXML private TableColumn<Actividad, String> colCategoria;
    @FXML private TableColumn<Actividad, String> colCoste;
    @FXML private TableColumn<Actividad, String> colDescripcion;
    @FXML private TableColumn<Actividad, String> colDuracion;
    @FXML private TableColumn<Actividad, String> colUbicacion;
    @FXML private TableColumn<Actividad, String> colValoracion;
    @FXML private TableColumn<Actividad, String> colReservada;

    @FXML private Button btnNueva;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    private final ActividadDAO actividadDAO = new ActividadDAO();
    private int idViaje;

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
        cargarActividades();
    }

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getNombre()));
        colFecha.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getFecha().toString()));
        colCategoria.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getCategoria().name()));
        colCoste.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(String.valueOf(a.getValue().getPrecio())));
        colDescripcion.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getNotas()));
        colDuracion.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(String.valueOf(a.getValue().getDuracionMinutos())));
        colUbicacion.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getLugar()));
        colValoracion.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(String.valueOf(a.getValue().getValoracion())));
        colReservada.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().isReservada() ? "Sí" : "No"));
    }

    private void cargarActividades() {
        try {
            tablaActividades.setItems(FXCollections.observableArrayList(
                    actividadDAO.findByIdViaje(idViaje)
            ));
        } catch (Exception e) {
            mostrarError("Error al cargar actividades: " + e.getMessage());
        }
    }

    @FXML
    private void nuevaActividad() {
        mostrarInfo("Abrir pantalla para crear actividad.");
    }

    @FXML
    private void editarActividad() {
        Actividad seleccionada = tablaActividades.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarError("Debe seleccionar una actividad para editar.");
            return;
        }

        mostrarInfo("Abrir pantalla para editar: " + seleccionada.getNombre());
    }

    @FXML
    private void eliminarActividad() {
        Actividad seleccionada = tablaActividades.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarError("Debe seleccionar una actividad para eliminar.");
            return;
        }

        try {
            actividadDAO.deleteActividadById(seleccionada.getIdActividad());
            mostrarInfo("Actividad eliminada correctamente.");
            cargarActividades();
        } catch (Exception e) {
            mostrarError("Error al eliminar actividad: " + e.getMessage());
        }
    }

    @FXML
    private void volver() {
        mostrarInfo("Volviendo a la pantalla anterior.");
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
