package com.example.travelapp.controllers;

import com.example.travelapp.TravelApplication;
import com.example.travelapp.dao.ViajeDAO;
import com.example.travelapp.model.enums.TipoViaje;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.utils.SessionUtils;
import com.example.travelapp.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controlador encargado de gestionar la creación y edición de viajes dentro
 * de la aplicación TravelApp.
 * Permite:
 * <ul>
 *     <li>Crear un nuevo viaje asignado al usuario en sesión.</li>
 *     <li>Editar un viaje existente.</li>
 *     <li>Validar campos obligatorios y coherencia de datos.</li>
 *     <li>Persistir los cambios en la base de datos mediante {@link ViajeDAO}.</li>
 * </ul>
 *
 * Este controlador se utiliza desde las vistas ListaViajes y VerDetallesViaje.
 */
public class EditarViajeController {
    @FXML private Label labelTitulo;

    @FXML private TextField textNombre;
    @FXML private TextField textDestino;
    @FXML private DatePicker fechaInicio;
    @FXML private DatePicker fechaFin;
    @FXML private ComboBox<TipoViaje> comboTipoViaje;
    @FXML private TextField textPresupuesto;
    @FXML private TextArea textNotas;

    private final ViajeDAO viajeDAO = new ViajeDAO();

    private Viaje viajeActual = null;

    /**
     * Inicializa los componentes de la vista.
     * Carga los tipos de viaje disponibles.
     */
    @FXML
    public void initialize() {
        comboTipoViaje.getItems().setAll(TipoViaje.values());
    }

    /**
     * Establece el viaje a editar.
     * Si se llama a este método, significa que el usuario está editando un viaje existente.
     *
     * @param viaje viaje existente
     */
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

    /**
     * Válida los campos, crea o actualiza el viaje y lo guarda en la base de datos.
     * Si el viaje es nuevo, se asigna automáticamente al usuario en sesión.
     */
    @FXML
    private void guardar() {
        if (textNombre.getText().isBlank() ||
                textDestino.getText().isBlank() ||
                fechaInicio.getValue() == null ||
                fechaFin.getValue() == null ||
                comboTipoViaje.getValue() == null) {

            Utils.mostrarWarning("Faltan campos obligatorios.");
            return;
        }

        Double presupuesto = Utils.toDouble(textPresupuesto.getText());
        if (presupuesto == null || presupuesto < 0) {
            Utils.mostrarWarning("El presupuesto debe ser un número positivo.");
            return;
        }

        if (fechaInicio.getValue().isAfter(fechaFin.getValue())) {
            Utils.mostrarWarning("La fecha de inicio debe ser anterior a la fecha de fin.");
            return;
        }

        if (viajeActual == null) {
            viajeActual = new Viaje();
            viajeActual.setUsuario(SessionUtils.getUsuarioActual());
        }
        viajeActual.setNombre(textNombre.getText().trim());
        viajeActual.setDestino(textDestino.getText().trim());
        viajeActual.setFechaInicio(fechaInicio.getValue());
        viajeActual.setFechaFin(fechaFin.getValue());
        viajeActual.setTipo(comboTipoViaje.getValue());
        viajeActual.setPresupuesto(presupuesto);
        viajeActual.setNotas(textNotas.getText().trim());

        if (viajeActual.getUsuario() == null) {
            Utils.mostrarWarning("No se ha podido determinar el usuario del viaje. Reinicia sesión e inténtalo de nuevo.");
            return;
        }
        if (viajeActual.getIdViaje() == 0) {
            viajeDAO.add(viajeActual);
        } else {
            viajeDAO.update(viajeActual);
        }

        TravelApplication.setRoot("ListaViajes");
    }

    /**
     * Cancela la operación y vuelve a la lista de viajes.
     */
    @FXML
    private void cancelar() {
        TravelApplication.setRoot("ListaViajes");
    }
}
