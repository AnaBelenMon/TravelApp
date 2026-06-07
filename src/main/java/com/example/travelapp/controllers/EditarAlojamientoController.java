package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.AlojamientoDAO;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.TipoAlojamiento;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controlador encargado de gestionar la creación, edición y eliminación
 * de alojamientos asociados a un viaje dentro de la aplicación.
 * Permite:
 * <ul>
 *     <li>Crear un nuevo alojamiento.</li>
 *     <li>Editar un alojamiento existente.</li>
 *     <li>Eliminar un alojamiento y desvincularlo del viaje.</li>
 *     <li>Asignar la valoración del alojamiento.</li>
 * </ul>
 *
 * Este controlador se comunica con {@link AlojamientoDAO} y {@link ViajeDAO}
 * para persistir los cambios en la base de datos.
 */
public class EditarAlojamientoController {
    @FXML private Label labelTitulo;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<TipoAlojamiento> cmbtipo;
    @FXML private TextField txtdireccion;
    @FXML private TextField txtciudad;
    @FXML private TextField txtpais;
    @FXML private ComboBox<Integer> cmbValoracion;

    @FXML private Button botonEliminar;

    private final AlojamientoDAO alojamientoDAO = new AlojamientoDAO();
    private final ViajeDAO viajeDAO = new ViajeDAO();

    private Viaje viajeActual;
    private Alojamiento alojamientoActual;

    /**
     * Inicializa los componentes de la vista.
     * Carga los tipos de alojamiento y las valoraciones posibles.
     * Oculta el botón de eliminar hasta que haya un alojamiento cargado.
     */
    @FXML
    public void initialize() {
        cmbtipo.getItems().setAll(TipoAlojamiento.values());
        cmbValoracion.getItems().addAll(1, 2, 3, 4, 5);
        botonEliminar.setVisible(false);
    }

    /**
     * Establece el viaje al que pertenece el alojamiento.
     *
     * @param viaje viaje actual
     */
    public void setViaje(Viaje viaje) {
        this.viajeActual = viaje;
    }

    /**
     * Establece el alojamiento a editar.
     * Si es null, el formulario se prepara para crear uno nuevo.
     *
     * @param alojamiento alojamiento existente o null
     */
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

    /**
     * Carga los datos del alojamiento en los campos del formulario.
     */
    private void cargarDatos() {
        txtNombre.setText(alojamientoActual.getNombre());
        cmbtipo.setValue(alojamientoActual.getTipo());
        txtdireccion.setText(alojamientoActual.getDireccion());
        txtciudad.setText(alojamientoActual.getCiudad());
        txtpais.setText(alojamientoActual.getPais());

        if (alojamientoActual.getValoracion() > 0) {
            cmbValoracion.setValue(alojamientoActual.getValoracion());
        } else {
            cmbValoracion.setValue(null);
        }

    }

    /**
     * Válida los campos, guarda o actualiza el alojamiento,
     * lo asigna al viaje y persiste los cambios en la base de datos.
     */
    @FXML
    private void guardar() {

        if (txtNombre.getText().isBlank() ||
                cmbtipo.getValue() == null ||
                txtdireccion.getText().isBlank() ||
                txtciudad.getText().isBlank() ||
                txtpais.getText().isBlank()) {

            Utils.mostrarWarning("Faltan campos obligatorios.");
            return;
        }

        if (alojamientoActual == null) {
            alojamientoActual = new Alojamiento();
        }

        alojamientoActual.setNombre(txtNombre.getText().trim());
        alojamientoActual.setTipo(cmbtipo.getValue());
        alojamientoActual.setDireccion(txtdireccion.getText().trim());
        alojamientoActual.setCiudad(txtciudad.getText().trim());
        alojamientoActual.setPais(txtpais.getText().trim());

        Integer puntuacion = cmbValoracion.getValue();
        if (puntuacion != null) {
            alojamientoActual.valorar(puntuacion);
        }

        if (alojamientoActual.getIdAlojamiento() == 0) {
            alojamientoDAO.add(alojamientoActual);
        } else {
            alojamientoDAO.update(alojamientoActual);
        }

        viajeActual.setAlojamiento(alojamientoActual);
        viajeDAO.update(viajeActual);

        volverADetalles();
    }

    /**
     * Elimina el alojamiento actual tras confirmación del usuario.
     * También lo desvincula del viaje correspondiente.
     */
    @FXML
    private void eliminar() {

        if (alojamientoActual == null) {
            Utils.mostrarWarning("No se puede eliminar un alojamiento inexistente.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("¿Eliminar alojamiento?");
        confirm.setContentText("Esta acción no se puede deshacer.");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        var resultado = confirm.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            alojamientoDAO.delete(alojamientoActual);

            viajeActual.setAlojamiento(null);
            viajeDAO.update(viajeActual);

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
        VerDetallesViajeController controller =
                TravelApplication.setRoot("VerDetallesViaje");
        if (controller != null) {
            controller.setViaje(viajeActual);
        }
    }
}
