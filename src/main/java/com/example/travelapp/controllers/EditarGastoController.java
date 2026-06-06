package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.GastoDAO;
import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.CategoriaGasto;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.MetodoPago;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditarGastoController {

    @FXML private Label labelTitulo;

    @FXML private TextField txtConcepto;
    @FXML private ComboBox<CategoriaGasto> cmbCategoria;
    @FXML private TextField txtImporte;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtLugar;
    @FXML private ComboBox<MetodoPago> cmbMetodoPago;
    @FXML private ComboBox<EstadoGasto> cmbEstado;
    @FXML private TextField txtNotas;

    @FXML private Button botonGuardar;
    @FXML private Button botonCancelar;
    @FXML private Button botonEliminar;

    private final GastoDAO gastoDAO = new GastoDAO();

    private Viaje viajeActual;
    private Gasto gastoActual;

    @FXML
    public void initialize() {
        cmbCategoria.getItems().setAll(CategoriaGasto.values());
        cmbMetodoPago.getItems().setAll(MetodoPago.values());
        cmbEstado.getItems().setAll(EstadoGasto.values());

        botonEliminar.setVisible(false);
    }

    // ============================================================
    // RECIBIR VIAJE Y GASTO
    // ============================================================
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    public void setGasto(Gasto gasto) {
        this.gastoActual = gasto;

        if (gasto == null) {
            labelTitulo.setText("Crear Gasto");
            botonEliminar.setVisible(false);
        } else {
            labelTitulo.setText("Editar Gasto");
            cargarDatos();
            botonEliminar.setVisible(true);
        }
    }

    private void cargarDatos() {
        txtConcepto.setText(gastoActual.getConcepto());
        cmbCategoria.setValue(gastoActual.getCategoria());
        txtImporte.setText(String.valueOf(gastoActual.getImporte()));
        dpFecha.setValue(gastoActual.getFecha());
        txtLugar.setText(gastoActual.getLugar());
        cmbMetodoPago.setValue(gastoActual.getMetodoPago());
        cmbEstado.setValue(gastoActual.getEstado());
        txtNotas.setText(gastoActual.getNotas());
    }

    // ============================================================
    // GUARDAR
    // ============================================================
    @FXML
    private void guardar() {

        if (txtConcepto.getText().isEmpty() ||
                cmbCategoria.getValue() == null ||
                txtImporte.getText().isEmpty() ||
                dpFecha.getValue() == null ||
                txtLugar.getText().isEmpty() ||
                cmbMetodoPago.getValue() == null ||
                cmbEstado.getValue() == null) {

            Utils.mostrarWarning("Rellena todos los campos obligatorios.");
            return;
        }

        if (gastoActual == null) {
            gastoActual = new Gasto();
            gastoActual.setViaje(viajeActual);
        }

        gastoActual.setConcepto(txtConcepto.getText());
        gastoActual.setCategoria(cmbCategoria.getValue());
        gastoActual.setImporte(Double.parseDouble(txtImporte.getText()));
        gastoActual.setFecha(dpFecha.getValue());
        gastoActual.setLugar(txtLugar.getText());
        gastoActual.setMetodoPago(cmbMetodoPago.getValue());
        gastoActual.setEstado(cmbEstado.getValue());
        gastoActual.setNotas(txtNotas.getText());

        if (gastoActual.getIdGasto() == 0) {
            gastoDAO.add(gastoActual);
        } else {
            gastoDAO.update(gastoActual);
        }

        volverADetalles();
    }

    // ============================================================
    // ELIMINAR
    // ============================================================
    @FXML
    private void eliminar() {

        if (gastoActual == null) {
            Utils.mostrarWarning("No se puede eliminar un gasto inexistente.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar gasto?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        if (confirm.showAndWait().get() == ButtonType.YES) {
            gastoDAO.delete(gastoActual);
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
        VerDetallesViajeController controller =
                TravelApplication.setRoot("VerDetallesViaje");
        controller.setViaje(viajeActual);
    }
}
