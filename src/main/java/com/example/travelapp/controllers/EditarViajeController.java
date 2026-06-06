package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.enums.TipoViaje;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditarViajeController {

    @FXML private Label labelTitulo;

    @FXML private TextField textNombre;
    @FXML private TextField textDestino;
    @FXML private DatePicker fechaInicio;
    @FXML private DatePicker fechaFin;
    @FXML private ComboBox<TipoViaje> comboTipoViaje;
    @FXML private TextField textPresupuesto;
    @FXML private TextArea textNotas;

    @FXML private Button botonGuardar;
    @FXML private Button botonCancelar;

    private final ViajeDAO viajeDAO = new ViajeDAO();

    private Viaje viajeActual = null; // null = crear, no null = editar

    @FXML
    public void initialize() {
        comboTipoViaje.getItems().setAll(TipoViaje.values());
    }

    // ---------------------------------------------------------
    // RECIBIR VIAJE DESDE ListaViajes o VerDetallesViaje
    // ---------------------------------------------------------
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;

        labelTitulo.setText("Editar Viaje");

        textNombre.setText(viaje.getNombre());
        textDestino.setText(viaje.getDestino());
        fechaInicio.setValue(viaje.getFechaInicio());
        fechaFin.setValue(viaje.getFechaFin());
        comboTipoViaje.setValue(viaje.getTipo());
        textPresupuesto.setText(String.valueOf(viaje.getPresupuesto()));
        textNotas.setText(viaje.getNotas());
    }

    // ---------------------------------------------------------
    // GUARDAR (INSERT o UPDATE)
    // ---------------------------------------------------------
    @FXML
    private void guardar() {

        // Validaciones básicas
        if (textNombre.getText().isEmpty() ||
                textDestino.getText().isEmpty() ||
                fechaInicio.getValue() == null ||
                fechaFin.getValue() == null ||
                comboTipoViaje.getValue() == null) {

            mostrarAlerta("Faltan campos obligatorios.");
            return;
        }

        double presupuesto = 0;
        try {
            presupuesto = Double.parseDouble(textPresupuesto.getText());
        } catch (Exception e) {
            mostrarAlerta("El presupuesto debe ser un número.");
            return;
        }

// Si es un viaje nuevo
        if (viajeActual == null) {
            viajeActual = new Viaje();
            // asignar el usuario logueado
            viajeActual.setUsuario(SessionManager.getUsuarioActual());
        }

        // Rellenar datos
        viajeActual.setNombre(textNombre.getText());
        viajeActual.setDestino(textDestino.getText());
        viajeActual.setFechaInicio(fechaInicio.getValue());
        viajeActual.setFechaFin(fechaFin.getValue());
        viajeActual.setTipo(comboTipoViaje.getValue());
        viajeActual.setPresupuesto(presupuesto);
        viajeActual.setNotas(textNotas.getText());

        // Antes de insertar, por seguridad:
        if (viajeActual.getUsuario() == null) {
            mostrarAlerta("No se ha podido determinar el usuario del viaje. Reinicia sesión e inténtalo de nuevo.");
            return;
        }
        // INSERT o UPDATE
        if (viajeActual.getIdViaje() == 0) {
            viajeDAO.add(viajeActual);
        } else {
            viajeDAO.update(viajeActual);
        }

        TravelApplication.setRoot("ListaViajes");
    }

    // ---------------------------------------------------------
    // CANCELAR
    // ---------------------------------------------------------
    @FXML
    private void cancelar() {
        TravelApplication.setRoot("ListaViajes");
    }

    private void cerrarVentana() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
/**
 * Mejorar las alertas y las comprobaciones
 * Las notas no es obligatorio escribir una nota para crear un viaje
 */