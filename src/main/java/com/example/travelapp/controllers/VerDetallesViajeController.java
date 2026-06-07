package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.*;
import com.example.travelapp.model.*;
import com.example.travelapp.utils.Utils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de mostrar todos los detalles de un viaje:
 * <ul>
 *     <li>Información general del viaje</li>
 *     <li>Alojamiento asociado (1:1)</li>
 *     <li>Transportes asociados (N:M)</li>
 *     <li>Gastos asociados (1:N)</li>
 * </ul>
 *
 * Desde esta vista el usuario puede:
 * <ul>
 *     <li>Editar el viaje</li>
 *     <li>Añadir, editar o eliminar alojamiento</li>
 *     <li>Añadir, editar o eliminar transportes</li>
 *     <li>Añadir, editar o eliminar gastos</li>
 *     <li>Volver a la lista de viajes</li>
 * </ul>
 *
 * Este controlador actúa como un panel central de gestión del viaje.
 */
public class VerDetallesViajeController {
    @FXML private Label lblNombre;
    @FXML private Label lblDestino;
    @FXML private Label lblFechaInicio;
    @FXML private Label lblFechaFin;
    @FXML private Label lblPresupuesto;
    @FXML private Label lblTipo;
    @FXML private Label lblNotas;

    @FXML private Label lblNombreAlojamiento;
    @FXML private Label lblTipoAlojamiento;
    @FXML private Label lblDireccionAlojamiento;
    @FXML private Label lblCiudadAlojamiento;
    @FXML private Label lblPaisAlojamiento;
    @FXML private Label lblPuntuacionAlojamiento;

    @FXML private TableView<Transporte> tablaTransportes;
    @FXML private TableColumn<Transporte, String> colTipoTransporte;
    @FXML private TableColumn<Transporte, String> colOrigenTransporte;
    @FXML private TableColumn<Transporte, String> colDestinoTransporte;
    @FXML private TableColumn<Transporte, String> colSalidaTransporte;
    @FXML private TableColumn<Transporte, String> colLlegadaTransporte;
    @FXML private TableColumn<Transporte, Double> colPrecioTransporte;
    @FXML private TableColumn<Transporte, String> colEstadoTransporte;

    @FXML private TableView<Gasto> tablaGastos;
    @FXML private TableColumn<Gasto, String> colConceptoGasto;
    @FXML private TableColumn<Gasto, String> colCategoriaGasto;
    @FXML private TableColumn<Gasto, Double> colImporteGasto;
    @FXML private TableColumn<Gasto, String> colFechaGasto;
    @FXML private TableColumn<Gasto, String> colLugarGasto;
    @FXML private TableColumn<Gasto, String> colMetodoPagoGasto;
    @FXML private TableColumn<Gasto, String> colEstadoGasto;
    @FXML private TableColumn<Gasto, String> colNotasGasto;

    private final ViajeDAO viajeDAO = new ViajeDAO();
    private final GastoDAO gastoDAO = new GastoDAO();
    private final ViajeTransporteDAO viajeTransporteDAO = new ViajeTransporteDAO();

    private Viaje viajeActual;

    /**
     * Configura las columnas de las tablas de transportes y gastos.
     * Se ejecuta automáticamente al cargar la vista.
     */
    @FXML
    public void initialize() {
        colTipoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipo().toString()));
        colOrigenTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getOrigen()));
        colDestinoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDestino()));
        colSalidaTransporte.setCellValueFactory(data ->
                new SimpleObjectProperty<>(Utils.formatearFechaHora(data.getValue().getFechaSalida())));
        colLlegadaTransporte.setCellValueFactory(data ->
                new SimpleObjectProperty<>(Utils.formatearFechaHora(data.getValue().getFechaLlegada())));
        colPrecioTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecio()));
        colEstadoTransporte.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEstado().toString()));

        colConceptoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getConcepto()));
        colCategoriaGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getCategoria().toString()));
        colImporteGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getImporte()));
        colFechaGasto.setCellValueFactory(data ->
                new SimpleObjectProperty<>(Utils.formatearFecha(data.getValue().getFecha())));
        colLugarGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getLugar()));
        colMetodoPagoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getMetodoPago().toString()));
        colEstadoGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEstado().toString()));
        colNotasGasto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNotas()));
    }

    /**
     * Recibe el viaje seleccionado desde la vista anterior y carga
     * toda su información en pantalla.
     *
     * @param viaje viaje seleccionado
     */
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
        cargarDatosViaje();
        cargarAlojamiento();
        cargarTransportes();
        cargarGastos();
    }

    /**
     * Muestra en pantalla los datos generales del viaje.
     */
    private void cargarDatosViaje() {
        lblNombre.setText(viajeActual.getNombre());
        lblDestino.setText(viajeActual.getDestino());
        lblFechaInicio.setText(Utils.formatearFecha(viajeActual.getFechaInicio()));
        lblFechaFin.setText(Utils.formatearFecha(viajeActual.getFechaFin()));
        lblPresupuesto.setText(String.valueOf(viajeActual.getPresupuesto()));
        lblTipo.setText(viajeActual.getTipo().toString());
        lblNotas.setText(viajeActual.getNotas());
    }

    /**
     * Muestra el alojamiento del viaje o guiones si no existe.
     */
    private void cargarAlojamiento() {
        Alojamiento a = viajeActual.getAlojamiento();

        if (a == null) {
            lblNombreAlojamiento.setText("—");
            lblTipoAlojamiento.setText("—");
            lblDireccionAlojamiento.setText("—");
            lblCiudadAlojamiento.setText("—");
            lblPaisAlojamiento.setText("—");
            lblPuntuacionAlojamiento.setText("—");
            return;
        }

        lblNombreAlojamiento.setText(a.getNombre());
        lblTipoAlojamiento.setText(a.getTipo().toString());
        lblDireccionAlojamiento.setText(a.getDireccion());
        lblCiudadAlojamiento.setText(a.getCiudad());
        lblPaisAlojamiento.setText(a.getPais());
        lblPuntuacionAlojamiento.setText(String.valueOf(a.getValoracion()));
    }

    /**
     * Carga en la tabla todos los transportes asociados al viaje.
     */
    private void cargarTransportes() {
        tablaTransportes.getItems().clear();

        List<ViajeTransporte> lista = viajeTransporteDAO.findByViaje(viajeActual);
        List<Transporte> transportes = new ArrayList<>();

        for (ViajeTransporte vt : lista) {
            transportes.add(vt.getTransporte());
        }

        tablaTransportes.getItems().addAll(transportes);
    }

    /**
     * Carga en la tabla todos los gastos asociados al viaje.
     */
    private void cargarGastos() {
        tablaGastos.getItems().clear();
        tablaGastos.getItems().addAll(gastoDAO.findByViaje(viajeActual));
    }

    /**
     * Abre la vista de edición del viaje.
     */
    @FXML
    public void editarViaje() {
        EditarViajeController controller = TravelApplication.setRoot("EditarViaje");
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }

    /**
     * Abre la vista para crear un nuevo alojamiento.
     */
    @FXML
    public void crearAlojamiento() {
        EditarAlojamientoController controller = TravelApplication.setRoot("EditarAlojamiento");
        if (controller != null) {
            controller.setAlojamiento(null);
            controller.setViaje(viajeActual);
        }

    }

    /**
     * Abre la vista para editar el alojamiento del viaje.
     */
    @FXML
    public void editarAlojamiento() {
        if (viajeActual.getAlojamiento() == null) {
            Utils.mostrarWarning("Este viaje no tiene alojamiento.");
            return;
        }

        EditarAlojamientoController controller = TravelApplication.setRoot("EditarAlojamiento");
        if (controller != null) {
            controller.setAlojamiento(viajeActual.getAlojamiento());
        }
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }

    /**
     * Elimina el alojamiento del viaje.
     */
    @FXML
    public void eliminarAlojamiento() {
        if (viajeActual.getAlojamiento() == null) {
            Utils.mostrarWarning("No hay alojamiento que eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar alojamiento?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        var resultado = confirm.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            viajeActual.setAlojamiento(null);
            viajeDAO.update(viajeActual);
            cargarAlojamiento();
        }
    }

    /**
     * Abre la vista para crear un nuevo transporte.
     */
    @FXML
    public void crearTransporte() {
        EditarTransporteController controller = TravelApplication.setRoot("EditarTransporte");
        if (controller != null) {
            controller.setTransporte(null);
        }
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
        if (controller != null) {
            controller.setViajeTransporteDAO(viajeTransporteDAO);
        }
    }

    /**
     * Abre la vista para editar un transporte seleccionado.
     */
    @FXML
    public void editarTransporte() {
        Transporte seleccionado = tablaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un transporte.");
            return;
        }

        EditarTransporteController controller = TravelApplication.setRoot("EditarTransporte");
        if (controller != null) {
            controller.setTransporte(seleccionado);
        }
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
        if (controller != null) {
            controller.setViajeTransporteDAO(viajeTransporteDAO);
        }
    }

    /**
     * Elimina un transporte del viaje.
     */
    @FXML
    public void eliminarTransporte() {
        Transporte seleccionado = tablaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un transporte.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar transporte?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        var resultado = confirm.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            viajeTransporteDAO.delete(viajeActual, seleccionado);
            cargarTransportes();
        }
    }

    /**
     * Abre la vista para crear un nuevo gasto.
     */
    @FXML
    public void crearGasto() {
        EditarGastoController controller = TravelApplication.setRoot("EditarGasto");
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }

    /**
     * Abre la vista para editar un gasto seleccionado.
     */
    @FXML
    public void editarGasto() {
        Gasto seleccionado = tablaGastos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un gasto.");
            return;
        }

        EditarGastoController controller = TravelApplication.setRoot("EditarGasto");
        if (controller != null) {
            controller.setGasto(seleccionado);
        }
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }

    /**
     * Elimina un gasto del viaje.
     */
    @FXML
    public void eliminarGasto() {
        Gasto seleccionado = tablaGastos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Utils.mostrarWarning("Selecciona un gasto.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar gasto?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        var resultado = confirm.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            gastoDAO.delete(seleccionado);
            cargarGastos();
        }
    }

    /**
     * Vuelve a la lista de viajes.
     */
    @FXML
    public void volver() {
        TravelApplication.setRoot("ListaViajes");
    }
}