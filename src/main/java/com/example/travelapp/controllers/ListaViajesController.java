package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.utils.SessionManager;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ListaViajesController {


    // TABLA
    @FXML private TableView<Viaje> tablaViajes;

    @FXML private TableColumn<Viaje, String> colNombre;
    @FXML private TableColumn<Viaje, String> colDestino;
    @FXML private TableColumn<Viaje, String> colFechaInicio;
    @FXML private TableColumn<Viaje, String> colFechaFin;
    @FXML private TableColumn<Viaje, Double> colPresupuesto;
    @FXML private TableColumn<Viaje, String> colNotas;
    @FXML private TableColumn<Viaje, String> colTipo;
    public TableColumn colAlojamiento;

    // BOTONES
    @FXML private Button botonNuevoViaje;
    @FXML private Button botonEditarViaje;
    @FXML private Button botonEliminar;
    @FXML private Button btnDetalles;
    @FXML private Button botonVolver;

    private final ViajeDAO viajeDAO = new ViajeDAO();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarViajes();
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colPresupuesto.setCellValueFactory(new PropertyValueFactory<>("presupuesto"));
        colNotas.setCellValueFactory(new PropertyValueFactory<>("notas"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAlojamiento.setCellValueFactory(new PropertyValueFactory<>("alojamiento"));
    }

    private void cargarViajes() {
        tablaViajes.getItems().clear();

        // Obtener solo los viajes del usuario actual
        int idUsuario = SessionManager.getIdUsuarioActual();
        List<Viaje> lista = viajeDAO.findByIdUsuario(idUsuario);

        tablaViajes.getItems().addAll(lista);
    }

    // -----------------------------
    // BOTONES CRUD
    // -----------------------------

    @FXML
    private void nuevoViaje() {
        TravelApplication.setRoot("EditarViaje");
    }

    @FXML
    private void editarViaje() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para poder editarlo");
            return;
        }

        EditarViajeController controller = TravelApplication.setRoot("EditarViaje");
        controller.setViaje(seleccionado);
    }

    @FXML
    private void eliminarViaje() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para eliminar un viaje");
            return;
        }

        viajeDAO.delete(seleccionado);
        cargarViajes();
    }

    @FXML
    private void verDetalles() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para ver sus detalles");
            return;
        }

        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        controller.setViaje(seleccionado);
    }
    @FXML
    private void cerrarSesion() {
        SessionManager.limpiarSesion();  // ← AGREGAR ESTO
        TravelApplication.setRoot("Login");
    }

    private Usuario usuarioActual;
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void volver() {
        TravelApplication.setRoot("Principal");
    }
}