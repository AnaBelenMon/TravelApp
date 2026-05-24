package com.example.travelapp.controllers;

import com.example.travelapp.dao.AlojamientoDAO;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.Viaje;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Controlador de la vista de edición de alojamientos.
 *
 * Permite crear y editar alojamientos asociados a un viaje,
 * gestionando validaciones de datos y persistencia en la base de datos.
 */
public class EditarAlojamientoController {

    @FXML private Label labelTitulo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDireccion;
    @FXML private DatePicker dpEntrada;
    @FXML private DatePicker dpSalida;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<Integer> cbValoracion;
    @FXML private ComboBox<Viaje> cbViaje;

    private Alojamiento alojamientoEditar;   // null = crear
    private int idViajeActual;               // recibido desde ListaAlojamientos

    /**
     * Inicializa la vista cargando datos necesarios en los ComboBox.
     *
     * @throws SQLException si ocurre un error al cargar los viajes desde la BD
     */
    @FXML
    public void initialize() throws SQLException {

        // Valoraciones de 0 a 5
        cbValoracion.getItems().addAll(0, 1, 2, 3, 4, 5);

        // Cargar viajes desde la base de datos
        cbViaje.setItems(FXCollections.observableArrayList(ViajeDAO.findAll()));
    }

    /**
     * Establece el identificador del viaje actual.
     *
     * @param idViaje identificador del viaje
     */
    public void setIdViaje(int idViaje) {
        this.idViajeActual = idViaje;

        // Seleccionar automáticamente el viaje correspondiente
        for (Viaje v : cbViaje.getItems()) {
            if (v.getIdViaje() == idViaje) {
                cbViaje.getSelectionModel().select(v);
                break;
            }
        }
    }

    /**
     * Carga los datos de un alojamiento en modo edición.
     *
     * @param alojamiento alojamiento a editar
     */
    public void cargarAlojamiento(Alojamiento alojamiento) {
        this.alojamientoEditar = alojamiento;

        labelTitulo.setText("Editar Alojamiento");

        txtNombre.setText(alojamiento.getNombre());
        txtDireccion.setText(alojamiento.getDireccion());
        dpEntrada.setValue(alojamiento.getFechaCheckin());
        dpSalida.setValue(alojamiento.getFechaCheckout());
        txtPrecio.setText(String.valueOf(alojamiento.getPrecioTotal()));
        cbValoracion.setValue(alojamiento.getValoracion());

        // Seleccionar viaje correspondiente
        for (Viaje v : cbViaje.getItems()) {
            if (v.getIdViaje() == alojamiento.getIdViaje()) {
                cbViaje.getSelectionModel().select(v);
                break;
            }
        }
    }

    /**
     * Guarda el alojamiento en la base de datos.
     *
     * Si el objeto es null, se crea un nuevo alojamiento.
     * Si no, se actualiza el existente.
     */
    @FXML
    private void guardar() {

        // VALIDACIONES
        if (txtNombre.getText().isEmpty()) {
            mostrarError("El nombre es obligatorio.");
            return;
        }

        if (txtDireccion.getText().isEmpty()) {
            mostrarError("La dirección es obligatoria.");
            return;
        }

        LocalDate entrada = dpEntrada.getValue();
        LocalDate salida = dpSalida.getValue();

        if (entrada == null || salida == null) {
            mostrarError("Debes seleccionar las fechas de entrada y salida.");
            return;
        }

        if (!salida.isAfter(entrada)) {
            mostrarError("La fecha de salida debe ser posterior a la de entrada.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText());
            if (precio <= 0) {
                mostrarError("El precio debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un número válido.");
            return;
        }

        if (cbValoracion.getValue() == null) {
            mostrarError("Debes seleccionar una valoración.");
            return;
        }

        if (cbViaje.getValue() == null) {
            mostrarError("Debes seleccionar un viaje.");
            return;
        }

        // GUARDAR EN BD
        try {
            if (alojamientoEditar == null) {

                // CREAR
                Alojamiento nuevo = new Alojamiento(
                        0,
                        cbViaje.getValue().getIdViaje(),
                        txtNombre.getText(),
                        txtDireccion.getText(),
                        precio,
                        entrada,
                        salida,
                        cbValoracion.getValue()
                );

                AlojamientoDAO.addAlojamiento(nuevo);

            } else {

                // EDITAR
                alojamientoEditar.setNombre(txtNombre.getText());
                alojamientoEditar.setDireccion(txtDireccion.getText());
                alojamientoEditar.setFechaCheckin(entrada);
                alojamientoEditar.setFechaCheckout(salida);
                alojamientoEditar.setPrecioTotal(precio);
                alojamientoEditar.setValoracion(cbValoracion.getValue());
                alojamientoEditar.setIdViaje(cbViaje.getValue().getIdViaje());

                AlojamientoDAO.updateAlojamiento(alojamientoEditar);
            }

            cerrarVentana();

        } catch (SQLException e) {
            mostrarError("Error al guardar en la base de datos.");
            e.printStackTrace();
        }
    }

    /**
     * Cancela la operación y cierra la ventana.
     */
    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    /**
     * Cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra un mensaje de error en un diálogo emergente.
     *
     * @param mensaje mensaje de error
     */
    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}