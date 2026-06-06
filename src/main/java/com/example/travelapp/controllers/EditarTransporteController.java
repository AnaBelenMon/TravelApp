package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.TransporteDAO;
import com.example.travelapp.dao.ViajeTransporteDAO;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.EstadoTransporte;
import com.example.travelapp.model.enums.TipoTransporte;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EditarTransporteController {

    @FXML private Label lblTitulo;

    @FXML private ComboBox<TipoTransporte> cmbTipo;
    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private DatePicker dpSalida;
    @FXML private DatePicker dpLlegada;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<EstadoTransporte> cmbEstado;

    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button botonEliminar;

    private final TransporteDAO transporteDAO = new TransporteDAO();
    private ViajeTransporteDAO viajeTransporteDAO;

    private Viaje viajeActual;
    private Transporte transporteActual;

    @FXML
    public void initialize() {
        cmbTipo.getItems().setAll(TipoTransporte.values());
        cmbEstado.getItems().setAll(EstadoTransporte.values());

        botonEliminar.setVisible(false);
    }

    // ============================================================
    // RECIBIR VIAJE Y TRANSPORTE
    // ============================================================
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    public void setTransporte(Transporte transporte) {
        this.transporteActual = transporte;

        if (transporte == null) {
            lblTitulo.setText("Añadir Transporte");
            botonEliminar.setVisible(false);
        } else {
            lblTitulo.setText("Editar Transporte");
            cargarDatos();
            botonEliminar.setVisible(true);
        }
    }

    public void setViajeTransporteDAO(ViajeTransporteDAO dao) {
        this.viajeTransporteDAO = dao;
    }

    private void cargarDatos() {
        cmbTipo.setValue(transporteActual.getTipo());
        txtOrigen.setText(transporteActual.getOrigen());
        txtDestino.setText(transporteActual.getDestino());
        dpSalida.setValue(LocalDate.from(transporteActual.getFechaSalida()));
        dpLlegada.setValue(LocalDate.from(transporteActual.getFechaLlegada()));
        txtPrecio.setText(String.valueOf(transporteActual.getPrecio()));
        cmbEstado.setValue(transporteActual.getEstado());
    }

    // ============================================================
    // GUARDAR
    // ============================================================
    @FXML
    private void guardar() {

        if (cmbTipo.getValue() == null ||
                txtOrigen.getText().isEmpty() ||
                txtDestino.getText().isEmpty() ||
                dpSalida.getValue() == null ||
                dpLlegada.getValue() == null ||
                txtPrecio.getText().isEmpty() ||
                cmbEstado.getValue() == null) {

            Utils.mostrarWarning("Rellena todos los campos.");
            return;
        }

        if (transporteActual == null) {
            transporteActual = new Transporte();
        }

        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException ex) {
            Utils.mostrarWarning("Precio inválido. Introduce un número.");
            return;
        }

        try {
            transporteActual.setTipo(cmbTipo.getValue());
            transporteActual.setOrigen(txtOrigen.getText());
            transporteActual.setDestino(txtDestino.getText());
            transporteActual.setFechaSalida(dpSalida.getValue().atStartOfDay());
            transporteActual.setFechaLlegada(dpLlegada.getValue().atStartOfDay());
            transporteActual.setPrecio(precio);
            transporteActual.setEstado(cmbEstado.getValue());
        } catch (IllegalArgumentException ex) {
            Utils.mostrarWarning(ex.getMessage());
            return;
        }

        if (transporteActual.getIdTransporte() == 0) {
            transporteDAO.add(transporteActual);
            viajeTransporteDAO.insert(viajeActual, transporteActual);
        } else {
            transporteDAO.update(transporteActual);
        }

        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        controller.setViaje(viajeActual);
    }

    // ============================================================
    // ELIMINAR
    // ============================================================
    @FXML
    private void eliminar() {

        if (transporteActual == null) {
            Utils.mostrarWarning("No se puede eliminar un transporte inexistente.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar transporte?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        if (confirm.showAndWait().get() == ButtonType.YES) {

            viajeTransporteDAO.delete(viajeActual, transporteActual);
            transporteDAO.delete(transporteActual);

            volverADetalles();
        }
    }

    // ============================================================
    // CANCELAR
    // ============================================================
    @FXML
    private void cancelar() {
        volverADetalles();
    }

    // ============================================================
    // VOLVER A VER DETALLES
    // ============================================================
    private void volverADetalles() {
        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        controller.setViaje(viajeActual);
    }
}
