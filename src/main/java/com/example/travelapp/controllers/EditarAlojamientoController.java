package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.AlojamientoDAO;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.TipoAlojamiento;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditarAlojamientoController {

    @FXML private Label labelTitulo;

    @FXML private TextField txtNombre;
    @FXML private ComboBox<TipoAlojamiento> cmbtipo;
    @FXML private TextField txtdireccion;
    @FXML private TextField txtciudad;
    @FXML private TextField txtpais;

    @FXML private Button botonGuardar;
    @FXML private Button botonCancelar;
    @FXML private Button botonEliminar;

    private final AlojamientoDAO alojamientoDAO = new AlojamientoDAO();
    private final ViajeDAO viajeDAO = new ViajeDAO();

    private Viaje viajeActual;
    private Alojamiento alojamientoActual;

    @FXML
    public void initialize() {
        cmbtipo.getItems().setAll(TipoAlojamiento.values());
        botonEliminar.setVisible(false);
    }

    // ============================================================
    // RECIBIR VIAJE Y ALOJAMIENTO
    // ============================================================
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamientoActual = alojamiento;

        if (alojamiento == null) {
            labelTitulo.setText("Crear Alojamiento");
            botonEliminar.setVisible(false);
        } else {
            labelTitulo.setText("Editar Alojamiento");
            cargarDatos();
            botonEliminar.setVisible(true);
        }
    }

    private void cargarDatos() {
        txtNombre.setText(alojamientoActual.getNombre());
        cmbtipo.setValue(alojamientoActual.getTipo());
        txtdireccion.setText(alojamientoActual.getDireccion());
        txtciudad.setText(alojamientoActual.getCiudad());
        txtpais.setText(alojamientoActual.getPais());
    }

    // ============================================================
    // GUARDAR
    // ============================================================
    @FXML
    private void guardar() {

        if (txtNombre.getText().isEmpty() ||
                cmbtipo.getValue() == null ||
                txtdireccion.getText().isEmpty() ||
                txtciudad.getText().isEmpty() ||
                txtpais.getText().isEmpty()) {

            mostrarAlerta("Faltan campos obligatorios.");
            return;
        }

        if (alojamientoActual == null) {
            alojamientoActual = new Alojamiento();
        }

        alojamientoActual.setNombre(txtNombre.getText());
        alojamientoActual.setTipo(cmbtipo.getValue());
        alojamientoActual.setDireccion(txtdireccion.getText());
        alojamientoActual.setCiudad(txtciudad.getText());
        alojamientoActual.setPais(txtpais.getText());

        // Guardar en BD
        if (alojamientoActual.getIdAlojamiento() == 0) {
            alojamientoDAO.add(alojamientoActual);
        } else {
            alojamientoDAO.update(alojamientoActual);
        }

        // Asignar al viaje
        viajeActual.setAlojamiento(alojamientoActual);
        viajeDAO.update(viajeActual);

        volverADetalles();
    }

    // ============================================================
    // ELIMINAR
    // ============================================================
    @FXML
    private void eliminar() {

        if (alojamientoActual == null) {
            mostrarAlerta("No se puede eliminar un alojamiento inexistente.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar alojamiento?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        if (confirm.showAndWait().get() == ButtonType.YES) {

            alojamientoDAO.delete(alojamientoActual);

            viajeActual.setAlojamiento(null);
            viajeDAO.update(viajeActual);

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

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
