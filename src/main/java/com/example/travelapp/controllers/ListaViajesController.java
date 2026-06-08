package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.utils.SessionUtils;
import com.example.travelapp.utils.Utils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador encargado de gestionar la vista de lista de viajes del usuario.
 * Permite:
 * <ul>
 *     <li>Visualizar todos los viajes del usuario en sesión.</li>
 *     <li>Crear un nuevo viaje.</li>
 *     <li>Editar un viaje existente.</li>
 *     <li>Eliminar un viaje.</li>
 *     <li>Acceder a la vista de detalles de un viaje.</li>
 *     <li>Cerrar sesión y volver al menú principal.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link ViajeDAO} para obtener y manipular
 * los datos almacenados en la base de datos.
 */
public class ListaViajesController {
    @FXML private TableView<Viaje> tablaViajes;

    @FXML private TableColumn<Viaje, String> colNombre;
    @FXML private TableColumn<Viaje, String> colDestino;
    @FXML private TableColumn<Viaje, String> colFechaInicio;
    @FXML private TableColumn<Viaje, String> colFechaFin;
    @FXML private TableColumn<Viaje, Double> colPresupuesto;
    @FXML private TableColumn<Viaje, String> colNotas;
    @FXML private TableColumn<Viaje, String> colTipo;
    @FXML public TableColumn<Viaje, String> colAlojamiento;

    private final ViajeDAO viajeDAO = new ViajeDAO();

    private Usuario usuarioActual;

    /**
     * Inicializa la vista configurando la tabla y cargando los viajes del usuario.
     */
    @FXML
    public void initialize() {
        configurarTabla();
        cargarViajes();
    }

    /**
     * Configura las columnas de la tabla para que muestren las propiedades del modelo {@link Viaje}.
     */
    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colFechaInicio.setCellValueFactory(cell -> new ReadOnlyStringWrapper(Utils.formatearFecha(cell.getValue().getFechaInicio())));
        colFechaFin.setCellValueFactory(cell -> new ReadOnlyStringWrapper(Utils.formatearFecha(cell.getValue().getFechaFin())));
        colPresupuesto.setCellValueFactory(new PropertyValueFactory<>("presupuesto"));
        colNotas.setCellValueFactory(new PropertyValueFactory<>("notas"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAlojamiento.setCellValueFactory(cell -> {
            var alo = cell.getValue().getAlojamiento();
            return new ReadOnlyStringWrapper(alo == null ? "—" : alo.getNombre());
        });

    }

    /**
     * Carga en la tabla únicamente los viajes pertenecientes al usuario en sesión.
     */
    private void cargarViajes() {
        tablaViajes.getItems().clear();

        int idUsuario = SessionUtils.getIdUsuarioActual();
        List<Viaje> lista = viajeDAO.findByIdUsuario(idUsuario);

        tablaViajes.getItems().addAll(lista);
    }

    /**
     * Abre la vista para crear un nuevo viaje.
     */
    @FXML
    private void nuevoViaje() {
        TravelApplication.setRoot("EditarViaje");
    }

    /**
     * Abre la vista de edición para el viaje seleccionado.
     * Muestra una advertencia si no hay selección.
     */
    @FXML
    private void editarViaje() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para poder editarlo");
            return;
        }

        EditarViajeController controller = TravelApplication.setRoot("EditarViaje");
        if (controller != null) {
            controller.setViaje(seleccionado);
        }
    }

    /**
     * Elimina el viaje seleccionado tras confirmación.
     * Muestra una advertencia si no hay selección.
     */
    @FXML
    private void eliminarViaje() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para eliminarlo.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar viaje?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        var resultado = confirm.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            viajeDAO.delete(seleccionado);
            cargarViajes();
        }
    }


    /**
     * Abre la vista de detalles del viaje seleccionado.
     * Muestra una advertencia si no hay selección.
     */
    @FXML
    private void verDetalles() {
        Viaje seleccionado = tablaViajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            Utils.mostrarWarning("Debes seleccionar un viaje para ver sus detalles");
            return;
        }

        VerDetallesViajeController controller = TravelApplication.setRoot("VerDetallesViaje");
        if (controller != null) {
            controller.setViaje(seleccionado);
        }
    }

    /**
     * Cierra la sesión del usuario y vuelve a la pantalla de login.
     */
    @FXML
    private void cerrarSesion() {
        SessionUtils.limpiarSesion();
        TravelApplication.setRoot("Login");
    }

    /**
     * Establece el usuario actual
     *
     * @param usuario usuario logueado
     */
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}