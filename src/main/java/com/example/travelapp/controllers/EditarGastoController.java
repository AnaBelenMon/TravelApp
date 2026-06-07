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

/**
 * Controlador encargado de gestionar la creación, edición y eliminación
 * de gastos asociados a un viaje dentro de la aplicación TravelApp.
 * Permite:
 * <ul>
 *     <li>Crear un nuevo gasto.</li>
 *     <li>Editar un gasto existente.</li>
 *     <li>Eliminar un gasto.</li>
 *     <li>Validar datos numéricos y fechas.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link GastoDAO} para persistir los cambios
 * en la base de datos y con {@link VerDetallesViajeController} para actualizar
 * la vista de detalles del viaje.
 */
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

    @FXML private Button botonEliminar;

    private final GastoDAO gastoDAO = new GastoDAO();

    private Viaje viajeActual;
    private Gasto gastoActual;

    /**
     * Inicializa los componentes de la vista.
     * Carga las categorías, métodos de pago y estados disponibles.
     * Oculta el botón de eliminar hasta que haya un gasto cargado.
     */
    @FXML
    public void initialize() {
        cmbCategoria.getItems().setAll(CategoriaGasto.values());
        cmbMetodoPago.getItems().setAll(MetodoPago.values());
        cmbEstado.getItems().setAll(EstadoGasto.values());

        botonEliminar.setVisible(false);
    }

    /**
     * Establece el viaje al que pertenece el gasto.
     *
     * @param viaje viaje actual
     */
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    /**
     * Establece el gasto a editar.
     * Si es null, el formulario se prepara para crear uno nuevo.
     *
     * @param gasto gasto existente o null
     */
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

    /**
     * Carga los datos del gasto en los campos del formulario.
     */
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

    /**
     * Válida los campos, guarda o actualiza el gasto,
     * y persiste los cambios en la base de datos.
     */
    @FXML
    private void guardar() {

        if (txtConcepto.getText().isBlank() ||
                cmbCategoria.getValue() == null ||
                txtImporte.getText().isBlank() ||
                dpFecha.getValue() == null ||
                txtLugar.getText().isBlank() ||
                cmbMetodoPago.getValue() == null ||
                cmbEstado.getValue() == null) {

            Utils.mostrarWarning("Rellena todos los campos obligatorios.");
            return;
        }

        Double importe = Utils.toDouble(txtImporte.getText());
        if (importe == null || importe < 0) {
            Utils.mostrarWarning("Importe inválido. Introduce un número positivo.");
            return;
        }

        if (gastoActual == null) {
            gastoActual = new Gasto();
            gastoActual.setViaje(viajeActual);
        }

        gastoActual.setConcepto(txtConcepto.getText().trim());
        gastoActual.setCategoria(cmbCategoria.getValue());
        gastoActual.setImporte(importe);
        gastoActual.setFecha(dpFecha.getValue());
        gastoActual.setLugar(txtLugar.getText().trim());
        gastoActual.setMetodoPago(cmbMetodoPago.getValue());
        gastoActual.setEstado(cmbEstado.getValue());
        gastoActual.setNotas(txtNotas.getText().trim());
        try {
            if (gastoActual.getIdGasto() == 0) {
                gastoDAO.add(gastoActual);
            } else {
                gastoDAO.update(gastoActual);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.mostrarError("Error al guardar el gasto: " + e.getMessage());
            return;
        }

        volverADetalles();
    }

    /**
     * Elimina el gasto actual tras confirmación del usuario.
     */
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

        var resultado = confirm.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            gastoDAO.delete(gastoActual);
            volverADetalles();
        }
    }


    /**
     * Cancela la operación y vuelve a la pantalla de detalles del viaje.
     */
    @FXML
    private void cancelar() {
        volverADetalles();
    }

    /**
     * Regresa a la vista de detalles del viaje,
     * recargando el viaje actualizado.
     */
    private void volverADetalles() {
        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }
}
