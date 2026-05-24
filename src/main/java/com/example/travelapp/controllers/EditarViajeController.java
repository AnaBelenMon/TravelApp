package com.example.travelapp.controllers;

import com.example.travelapp.dao.DocumentoDAO;
import com.example.travelapp.dao.TransporteDAO;
import com.example.travelapp.model.Documento;
import com.example.travelapp.model.TipoDocumento;
import com.example.travelapp.model.TipoViaje;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.Viaje;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.Desktop;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class EditarViajeController {

    @FXML private Label labelTitulo;

    @FXML private TextField textNombre;
    @FXML private DatePicker fechaInicio;
    @FXML private DatePicker fechaFin;
    @FXML private ComboBox<TipoViaje> comboTipoViaje;
    @FXML private TextField textPais;
    @FXML private TextField textCiudad;
    @FXML private TextField textPresupuesto;
    @FXML private TextField textImagen;
    @FXML private TextArea textNotas;

    // DOCUMENTOS
    @FXML private ListView<Documento> listaDocumentos;

    // TRANSPORTES
    @FXML private ListView<Transporte> listaTransportes;

    private Viaje viaje; // null = crear, no null = editar

    // ---------------------------------------------------------
    // INICIALIZACIÓN
    // ---------------------------------------------------------
    @FXML
    public void initialize() {
        comboTipoViaje.getItems().setAll(TipoViaje.values());
    }

    // ---------------------------------------------------------
    // CARGAR VIAJE (EDITAR)
    // ---------------------------------------------------------
    public void cargarViaje(Viaje v) {
        this.viaje = v;
        labelTitulo.setText("Editar Viaje");

        textNombre.setText(v.getNombre());
        fechaInicio.setValue(v.getFechaInicio());
        fechaFin.setValue(v.getFechaFin());
        comboTipoViaje.setValue(v.getTipoViaje());
        textPais.setText(v.getDestinoPais());
        textCiudad.setText(v.getDestinoCiudad());
        textPresupuesto.setText(String.valueOf(v.getPresupuestoEstimado()));
        textImagen.setText(v.getImagenPortada());
        textNotas.setText(v.getNotasGenerales());

        cargarDocumentos();
        cargarTransportes();
    }

    // ---------------------------------------------------------
    // DOCUMENTOS
    // ---------------------------------------------------------
    private void cargarDocumentos() {
        if (viaje == null) return;

        try {
            listaDocumentos.getItems().setAll(
                    DocumentoDAO.findByIdViaje(viaje.getIdViaje())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void añadirDocumento() {
        if (viaje == null) return;

        FileChooser fc = new FileChooser();
        File archivo = fc.showOpenDialog(null);

        if (archivo != null) {
            TipoDocumento tipo = archivo.getName().toLowerCase().endsWith(".pdf")
                    ? TipoDocumento.PDF
                    : TipoDocumento.IMAGEN;

            Documento doc = new Documento(
                    archivo.getName(),
                    tipo,
                    archivo.getAbsolutePath()
            );
            doc.setIdViaje(viaje.getIdViaje());

            try {
                DocumentoDAO.insert(doc);
                cargarDocumentos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void verDocumento() {
        Documento seleccionado = listaDocumentos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        try {
            Desktop.getDesktop().open(new File(seleccionado.getRutaArchivo()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarDocumento() {
        Documento seleccionado = listaDocumentos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        try {
            DocumentoDAO.delete(seleccionado.getIdDocumento());
            cargarDocumentos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // TRANSPORTES
    // ---------------------------------------------------------
    private void cargarTransportes() {
        if (viaje == null) return;

        try {
            listaTransportes.getItems().setAll(
                    TransporteDAO.findByIdViaje(viaje.getIdViaje())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void añadirTransporte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/travelapp/views/EditarTransporte.fxml"));
            Parent root = loader.load();

            EditarTransporteController controller = loader.getController();
            controller.setIdViaje(viaje.getIdViaje());

            Stage stage = new Stage();
            stage.setTitle("Añadir Transporte");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTransportes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editarTransporte() {
        Transporte seleccionado = listaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/travelapp/views/EditarTransporte.fxml"));
            Parent root = loader.load();

            EditarTransporteController controller = loader.getController();
            controller.cargarTransporte(seleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Transporte");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTransportes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarTransporte() {
        Transporte seleccionado = listaTransportes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText(null);
        confirm.setContentText("¿Eliminar este transporte?");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                TransporteDAO.delete(seleccionado.getIdTransporte());
                cargarTransportes();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------------------------------------------
    // GUARDAR VIAJE
    // ---------------------------------------------------------
    @FXML
    private void guardar() {
        System.out.println("Guardando viaje…");
        // Aquí conectas ViajeDAO cuando quieras
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) labelTitulo.getScene().getWindow();
        stage.close();
    }
}
