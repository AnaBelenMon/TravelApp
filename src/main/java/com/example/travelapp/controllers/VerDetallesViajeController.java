package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.*;
import com.example.travelapp.model.*;
import com.example.travelapp.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class VerDetallesViajeController {
    // ============================
    //   VIAJE
    // ============================
    @FXML private Label lblNombre;
    @FXML private Label lblDestino;
    @FXML private Label lblFechaInicio;
    @FXML private Label lblFechaFin;
    @FXML private Label lblPresupuesto;
    @FXML private Label lblTipo;
    @FXML private Label lblNotas;

    // ============================
    //   ALOJAMIENTO (1 SOLO)
    // ============================
    @FXML private Label lblNombreAlojamiento;
    @FXML private Label lblTipoAlojamiento;
    @FXML private Label lblDireccionAlojamiento;
    @FXML private Label lblCiudadAlojamiento;
    @FXML private Label lblPaisAlojamiento;

    // ============================
    //   TRANSPORTES
    // ============================
    @FXML private TableView<Transporte> tablaTransportes;
    @FXML private TableColumn<Transporte, String> colTipoTransporte;
    @FXML private TableColumn<Transporte, String> colOrigenTransporte;
    @FXML private TableColumn<Transporte, String> colDestinoTransporte;
    @FXML private TableColumn<Transporte, String> colSalidaTransporte;
    @FXML private TableColumn<Transporte, String> colLlegadaTransporte;
    @FXML private TableColumn<Transporte, Double> colPrecioTransporte;
    @FXML private TableColumn<Transporte, String> colEstadoTransporte;

    // ============================
    //   GASTOS
    // ============================
    @FXML private TableView<Gasto> tablaGastos;
    @FXML private TableColumn<Gasto, String> colConceptoGasto;
    @FXML private TableColumn<Gasto, String> colCategoriaGasto;
    @FXML private TableColumn<Gasto, Double> colImporteGasto;
    @FXML private TableColumn<Gasto, String> colFechaGasto;
    @FXML private TableColumn<Gasto, String> colLugarGasto;
    @FXML private TableColumn<Gasto, String> colMetodoPagoGasto;
    @FXML private TableColumn<Gasto, String> colEstadoGasto;
    @FXML private TableColumn<Gasto, String> colNotasGasto;

    // ============================
    //   BOTONES
    // ============================
    @FXML private Button btnEditarViaje;
    @FXML private Button btnAñadirAlojamiento;
    @FXML private Button btnEditarAlojamiento;
    @FXML private Button btnEliminarAlojamiento;
    @FXML private Button btnCrearTransporte;
    @FXML private Button btnEditarTransporte;
    @FXML private Button btnBorrarTransporte;
    @FXML private Button btnCrearGasto;
    @FXML private Button btnEditarGasto;
    @FXML private Button btnEliminarGasto;
    @FXML private Button btnVolver;

    // ============================
    //   DAOs
    // ============================
    private final ViajeDAO viajeDAO = new ViajeDAO();
    private final AlojamientoDAO alojamientoDAO = new AlojamientoDAO();
    private final TransporteDAO transporteDAO = new TransporteDAO();
    private final GastoDAO gastoDAO = new GastoDAO();
    private final ViajeTransporteDAO viajeTransporteDAO = new ViajeTransporteDAO();

    private Viaje viajeActual;

    @FXML
    public void initialize() {
        // Configurar columnas de TRANSPORTES
        colTipoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipo().toString()));
        colOrigenTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getOrigen()));
        colDestinoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDestino()));
        colSalidaTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaSalida().toString()));
        colLlegadaTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaLlegada().toString()));
        colPrecioTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecio()));
        colEstadoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEstado().toString()));

        // Configurar columnas de GASTOS
        colConceptoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getConcepto()));
        colCategoriaGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getCategoria().toString()));
        colImporteGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getImporte()));
        colFechaGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha().toString()));
        colLugarGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getLugar()));
        colMetodoPagoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getMetodoPago().toString()));
        colEstadoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEstado().toString()));
        colNotasGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNotas().toString()));
    }

    // ============================
    //   RECIBIR VIAJE
    // ============================
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
        cargarDatosViaje();
        cargarAlojamiento();
        cargarTransportes();
        cargarGastos();
    }

    // ============================
    //   CARGAR VIAJE
    // ============================
    private void cargarDatosViaje() {
        lblNombre.setText(viajeActual.getNombre());
        lblDestino.setText(viajeActual.getDestino());
        lblFechaInicio.setText(viajeActual.getFechaInicio().toString());
        lblFechaFin.setText(viajeActual.getFechaFin().toString());
        lblPresupuesto.setText(String.valueOf(viajeActual.getPresupuesto()));
        lblTipo.setText(viajeActual.getTipo().toString());
        lblNotas.setText(viajeActual.getNotas());
    }

    // ============================
    //   CARGAR ALOJAMIENTO
    // ============================
    private void cargarAlojamiento() {
        Alojamiento a = viajeActual.getAlojamiento();

        if (a == null) {
            lblNombreAlojamiento.setText("—");
            lblTipoAlojamiento.setText("—");
            lblDireccionAlojamiento.setText("—");
            lblCiudadAlojamiento.setText("—");
            lblPaisAlojamiento.setText("—");
            return;
        }

        lblNombreAlojamiento.setText(a.getNombre());
        lblTipoAlojamiento.setText(String.valueOf(a.getTipo()));
        lblDireccionAlojamiento.setText(a.getDireccion());
        lblCiudadAlojamiento.setText(a.getCiudad());
        lblPaisAlojamiento.setText(a.getPais());
    }

    // ============================
    //   CARGAR TRANSPORTES
    // ============================
    private void cargarTransportes() {
        tablaTransportes.getItems().clear();

        List<ViajeTransporte> lista = viajeTransporteDAO.findByViaje(viajeActual);
        List<Transporte> transportes = new ArrayList<>();

        for (ViajeTransporte vt : lista) {
            transportes.add(vt.getTransporte());
        }

        tablaTransportes.getItems().addAll(transportes);
    }

    // ============================
    //   CARGAR GASTOS
    // ============================
    private void cargarGastos() {
        tablaGastos.getItems().clear();
        tablaGastos.getItems().addAll(gastoDAO.findByViaje(viajeActual));
    }

    // ============================
    //   BOTONES VIAJE
    // ============================
    @FXML
    public void editarViaje(ActionEvent actionEvent) {
        EditarViajeController controller = TravelApplication.setRoot("EditarViaje");
        controller.setViaje(viajeActual);
    }

    // ============================
    //   BOTONES ALOJAMIENTO
    // ============================
    @FXML
    public void crearAlojamiento(ActionEvent actionEvent) {
        EditarAlojamientoController controller = TravelApplication.setRoot("EditarAlojamiento");
        controller.setAlojamiento(null);
        controller.setViaje(viajeActual);
    }

    @FXML
    public void editarAlojamiento(ActionEvent actionEvent) {
        if (viajeActual.getAlojamiento() == null) {
            Utils.mostrarWarning("Este viaje no tiene alojamiento.");
            return;
        }

        EditarAlojamientoController controller = TravelApplication.setRoot("EditarAlojamiento");
        controller.setAlojamiento(viajeActual.getAlojamiento());
        controller.setViaje(viajeActual);
    }

    @FXML
    public void eliminarAlojamiento(ActionEvent actionEvent) {
        if (viajeActual.getAlojamiento() == null) {
            Utils.mostrarWarning("No hay alojamiento que eliminar.");
            return;
        }

        viajeActual.setAlojamiento(null);
        viajeDAO.update(viajeActual);
        cargarAlojamiento();
    }

    // ============================
    //   BOTONES TRANSPORTE
    // ============================
    @FXML
    public void crearTransporte(ActionEvent actionEvent) {
        EditarTransporteController controller = TravelApplication.setRoot("EditarTransporte");
        controller.setTransporte(null);
        controller.setViaje(viajeActual);
        controller.setViajeTransporteDAO(viajeTransporteDAO);
    }

    @FXML
    public void editarTransporte(ActionEvent actionEvent) {
        Transporte seleccionado = tablaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un transporte.");
            return;
        }

        EditarTransporteController controller = TravelApplication.setRoot("EditarTransporte");
        controller.setTransporte(seleccionado);
        controller.setViaje(viajeActual);
        controller.setViajeTransporteDAO(viajeTransporteDAO);
    }

    @FXML
    public void eliminarTransporte(ActionEvent actionEvent) {
        Transporte seleccionado = tablaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un transporte.");
            return;
        }

        viajeTransporteDAO.delete(viajeActual, seleccionado);
        cargarTransportes();
    }

    // ============================
    //   BOTONES GASTOS
    // ============================
    @FXML
    public void crearGasto(ActionEvent actionEvent) {
        EditarGastoController controller = TravelApplication.setRoot("EditarGasto");
        controller.setViaje(viajeActual);
    }

    @FXML
    public void editarGasto(ActionEvent actionEvent) {
        Gasto seleccionado = tablaGastos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un gasto.");
            return;
        }

        EditarGastoController controller = TravelApplication.setRoot("EditarGasto");
        controller.setGasto(seleccionado);
        controller.setViaje(viajeActual);
    }

    @FXML
    public void eliminarGasto(ActionEvent actionEvent) {
        Gasto seleccionado = tablaGastos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un gasto.");
            return;
        }

        gastoDAO.delete(seleccionado);
        cargarGastos();
    }

    // ============================
    //   VOLVER
    // ============================
    @FXML
    public void volver(ActionEvent actionEvent) {
        TravelApplication.setRoot("ListaViajes");
    }
}