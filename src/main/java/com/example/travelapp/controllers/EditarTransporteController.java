package com.example.travelapp.controllers;

import com.example.travelapp.dao.TransporteDAO;
import com.example.travelapp.model.TipoDocumento;
import com.example.travelapp.model.TipoTransporte;
import com.example.travelapp.model.Transporte;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditarTransporteController {

    @FXML private Label lblTitulo;

    @FXML private ComboBox<TipoTransporte> cmbTipoTransporte;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtPrecio;

    @FXML private Label lblDocumento;

    private String rutaDocumento;
    private TipoDocumento tipoDocumento;

    private Transporte transporte;   // null = crear, no null = editar
    private int idViaje;             // necesario para asignarlo al transporte

    // ---------------------------------------------------------
    // INICIALIZACIÓN
    // ---------------------------------------------------------
    @FXML
    public void initialize() {
        cmbTipoTransporte.getItems().setAll(TipoTransporte.values());
    }

    // ---------------------------------------------------------
    // CARGAR TRANSPORTE (EDITAR)
    // ---------------------------------------------------------
    public void cargarTransporte(Transporte t) {
        this.transporte = t;
        lblTitulo.setText("Editar Transporte");

        cmbTipoTransporte.setValue(t.getTipo());
        dpFecha.setValue(t.getFecha());
        txtPrecio.setText(String.valueOf(t.getPrecio()));

        rutaDocumento = t.getRutaDocumento();
        tipoDocumento = t.getTipoDocumento();

        lblDocumento.setText(rutaDocumento == null || rutaDocumento.isBlank()
                ? "Ninguno"
                : new File(rutaDocumento).getName());
    }

    // ---------------------------------------------------------
    // RECIBIR ID DEL VIAJE (AL CREAR)
    // ---------------------------------------------------------
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    // ---------------------------------------------------------
    // SELECCIONAR DOCUMENTO
    // ---------------------------------------------------------
    @FXML
    private void seleccionarDocumento() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar documento");

        File archivo = fc.showOpenDialog(null);

        if (archivo != null) {
            rutaDocumento = archivo.getAbsolutePath();
            tipoDocumento = archivo.getName().toLowerCase().endsWith(".pdf")
                    ? TipoDocumento.PDF
                    : TipoDocumento.IMAGEN;

            lblDocumento.setText(archivo.getName());
        }
    }

    // ---------------------------------------------------------
    // GUARDAR
    // ---------------------------------------------------------
    @FXML
    private void guardar() {
        try {
            TipoTransporte tipo = cmbTipoTransporte.getValue();
            LocalDate fecha = dpFecha.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());

            if (tipo == null || fecha == null) {
                mostrarError("Debes completar todos los campos obligatorios.");
                return;
            }

            if (transporte == null) {
                // CREAR
                transporte = new Transporte(
                        tipo,
                        fecha,
                        precio,
                        tipoDocumento,
                        rutaDocumento
                );
                transporte.setIdViaje(idViaje);

                TransporteDAO.insert(transporte);

            } else {
                // EDITAR
                transporte.setTipo(tipo);
                transporte.setFecha(fecha);
                transporte.setPrecio(precio);
                transporte.setTipoDocumento(tipoDocumento);
                transporte.setRutaDocumento(rutaDocumento);

                TransporteDAO.update(transporte);
            }

            cerrarVentana();

        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un número válido.");
        } catch (SQLException e) {
            mostrarError("Error al guardar el transporte.");
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // CANCELAR
    // ---------------------------------------------------------
    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    // ---------------------------------------------------------
    // UTILIDADES
    // ---------------------------------------------------------
    private void cerrarVentana() {
        Stage stage = (Stage) lblTitulo.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
