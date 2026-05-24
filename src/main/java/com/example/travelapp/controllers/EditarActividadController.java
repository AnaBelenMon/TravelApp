package com.example.travelapp.controllers;

import com.example.travelapp.dao.ActividadDAO;
import com.example.travelapp.model.Actividad;
import com.example.travelapp.model.CategoriaActividad;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditarActividadController {

    @FXML private Label labelTitulo;

    @FXML private TextField txtNombre;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<CategoriaActividad> cmbCategoria;
    @FXML private TextField txtCoste;
    @FXML private TextArea txtDescripcion;

    @FXML private TextField txtDuracion;
    @FXML private TextField txtUbicacion;
    @FXML private ComboBox<Integer> cmbValoracion;
    @FXML private CheckBox chkReservada;

    @FXML private Button botonGuardar;
    @FXML private Button botonCancelar;

    private Actividad actividad;
    private int idViaje;

    private final ActividadDAO actividadDAO = new ActividadDAO();

    @FXML
    public void initialize() {
        cmbCategoria.getItems().setAll(CategoriaActividad.values());
        cmbValoracion.getItems().addAll(1, 2, 3, 4, 5);
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public void cargarActividad(Actividad a) {
        this.actividad = a;
        labelTitulo.setText("Editar Actividad");

        txtNombre.setText(a.getNombre());
        dpFecha.setValue(a.getFecha());
        cmbCategoria.setValue(a.getCategoria());
        txtCoste.setText(String.valueOf(a.getPrecio()));
        txtDescripcion.setText(a.getNotas());
        txtDuracion.setText(String.valueOf(a.getDuracionMinutos()));
        txtUbicacion.setText(a.getLugar());
        cmbValoracion.setValue(a.getValoracion());
        chkReservada.setSelected(a.isReservada());
    }

    @FXML
    private void guardar() {
        if (txtNombre.getText().isEmpty()) {
            mostrarError("El nombre no puede estar vacío.");
            return;
        }

        if (dpFecha.getValue() == null) {
            mostrarError("Debe seleccionar una fecha.");
            return;
        }

        if (cmbCategoria.getValue() == null) {
            mostrarError("Debe seleccionar una categoría.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(txtCoste.getText());
            if (precio < 0) {
                mostrarError("El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un número válido.");
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(txtDuracion.getText());
            if (duracion <= 0) {
                mostrarError("La duración debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("La duración debe ser un número entero.");
            return;
        }

        if (txtUbicacion.getText().isEmpty()) {
            mostrarError("Debe introducir una ubicación.");
            return;
        }

        if (cmbValoracion.getValue() == null) {
            mostrarError("Debe seleccionar una valoración.");
            return;
        }

        try {
            if (actividad == null) {
                actividad = new Actividad(
                        0,
                        idViaje,
                        txtNombre.getText(),
                        cmbCategoria.getValue(),
                        dpFecha.getValue(),
                        precio,
                        txtDescripcion.getText(),
                        cmbValoracion.getValue(),
                        duracion,
                        chkReservada.isSelected(),
                        txtUbicacion.getText()
                );

                actividadDAO.addActividad(actividad);
                mostrarInfo("Actividad creada correctamente.");

            } else {
                actividad.setNombre(txtNombre.getText());
                actividad.setFecha(dpFecha.getValue());
                actividad.setCategoria(cmbCategoria.getValue());
                actividad.setPrecio(precio);
                actividad.setNotas(txtDescripcion.getText());
                actividad.setDuracionMinutos(duracion);
                actividad.setLugar(txtUbicacion.getText());
                actividad.setValoracion(cmbValoracion.getValue());
                actividad.setReservada(chkReservada.isSelected());

                actividadDAO.updateActividad(actividad);
                mostrarInfo("Actividad actualizada correctamente.");
                cerrarVentana();
            }

        } catch (Exception e) {
            mostrarError("Error al guardar la actividad: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
        mostrarInfo("Operación cancelada.");
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) labelTitulo.getScene().getWindow();
        stage.close();
    }

}
