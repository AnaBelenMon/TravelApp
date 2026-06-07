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

/**
 * Controlador encargado de gestionar la creación, edición y eliminación
 * de transportes asociados a un viaje dentro de la aplicación TravelApp.
 * Permite:
 * <ul>
 *     <li>Añadir un nuevo transporte al viaje.</li>
 *     <li>Editar un transporte existente.</li>
 *     <li>Eliminar un transporte y su relación con el viaje.</li>
 *     <li>Validar fechas, precio y datos obligatorios.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link TransporteDAO} para persistir
 * los datos del transporte y con {@link ViajeTransporteDAO} para gestionar
 * la relación N:M entre viaje y transporte.
 */
public class EditarTransporteController {
    @FXML private Label lblTitulo;

    @FXML private ComboBox<TipoTransporte> cmbTipo;
    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private DatePicker dpSalida;
    @FXML private DatePicker dpLlegada;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<EstadoTransporte> cmbEstado;

    @FXML private Button botonEliminar;

    private final TransporteDAO transporteDAO = new TransporteDAO();
    private ViajeTransporteDAO viajeTransporteDAO;

    private Viaje viajeActual;
    private Transporte transporteActual;

    /**
     * Inicializa los componentes de la vista.
     * Carga los tipos de transporte y estados disponibles.
     * Oculta el botón de eliminar hasta que haya un transporte cargado.
     */
    @FXML
    public void initialize() {
        cmbTipo.getItems().setAll(TipoTransporte.values());
        cmbEstado.getItems().setAll(EstadoTransporte.values());
        botonEliminar.setVisible(false);
    }

    /**
     * Establece el viaje al que pertenece el transporte.
     *
     * @param viaje viaje actual
     */
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    /**
     * Establece el transporte a editar.
     * Si es null, el formulario se prepara para crear uno nuevo.
     *
     * @param transporte transporte existente o null
     */
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

    /**
     * Establece el DAO encargado de gestionar la relación viaje–transporte.
     *
     * @param dao instancia de ViajeTransporteDAO
     */
    public void setViajeTransporteDAO(ViajeTransporteDAO dao) {
        this.viajeTransporteDAO = dao;
    }

    /**
     * Carga los datos del transporte en los campos del formulario.
     */
    private void cargarDatos() {
        cmbTipo.setValue(transporteActual.getTipo());
        txtOrigen.setText(transporteActual.getOrigen());
        txtDestino.setText(transporteActual.getDestino());
        dpSalida.setValue(LocalDate.from(transporteActual.getFechaSalida()));
        dpLlegada.setValue(LocalDate.from(transporteActual.getFechaLlegada()));
        txtPrecio.setText(String.valueOf(transporteActual.getPrecio()));
        cmbEstado.setValue(transporteActual.getEstado());
    }

    /**
     * Válida los campos, guarda o actualiza el transporte,
     * y persiste los cambios en la base de datos.
     */
    @FXML
    private void guardar() {
        if (cmbTipo.getValue() == null ||
                txtOrigen.getText().isBlank() ||
                txtDestino.getText().isBlank() ||
                dpSalida.getValue() == null ||
                dpLlegada.getValue() == null ||
                txtPrecio.getText().isBlank() ||
                cmbEstado.getValue() == null) {

            Utils.mostrarWarning("Rellena todos los campos.");
            return;
        }

        Double precio = Utils.toDouble(txtPrecio.getText());
        if (precio == null || precio < 0) {
            Utils.mostrarWarning("Precio inválido. Introduce un número positivo.");
            return;
        }
        if (dpSalida.getValue().isAfter(dpLlegada.getValue())) {
            Utils.mostrarWarning("La fecha de salida debe ser anterior a la de llegada.");
            return;
        }

        if (transporteActual == null) {
            transporteActual = new Transporte();
        }

        transporteActual.setTipo(cmbTipo.getValue());
        transporteActual.setOrigen(txtOrigen.getText().trim());
        transporteActual.setDestino(txtDestino.getText().trim());
        transporteActual.setFechaSalida(dpSalida.getValue().atStartOfDay());
        transporteActual.setFechaLlegada(dpLlegada.getValue().atStartOfDay());
        transporteActual.setPrecio(precio);
        transporteActual.setEstado(cmbEstado.getValue());

        if (transporteActual.getIdTransporte() == 0) {
            transporteDAO.add(transporteActual);
            viajeTransporteDAO.insert(viajeActual, transporteActual);
        } else {
            transporteDAO.update(transporteActual);
        }

        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }

    /**
     * Elimina el transporte actual tras confirmación del usuario.
     * También elimina la relación viaje–transporte.
     */
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

        var resultado = confirm.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {

            viajeTransporteDAO.delete(viajeActual, transporteActual);
            transporteDAO.delete(transporteActual);

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
