package com.example.travelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.travelapp.model.Emocion;
//import com.example.travelapp.model.TipoEmocion;

public class EditarEmocionController {

    @FXML
    private Label lblTitulo;

    //    @FXML private ComboBox<TipoEmocion> cmbTipo;
    @FXML
    private Slider sliderIntensidad;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextArea txtDescripcion;

    private Emocion emocion; // null = crear, no null = editar

    @FXML
/*    public void initialize() {
        cmbTipo.getItems().setAll(TipoEmocion.values());
    }
*/
    public void cargarEmocion(Emocion e) {
        this.emocion = e;
        lblTitulo.setText("Editar Emoción");

/*        cmbTipo.setValue(e.getTipo());
        sliderIntensidad.setValue(e.getIntensidad());
        dpFecha.setValue(e.getFecha());
        txtDescripcion.setText(e.getDescripcion());
    }

    @FXML
    private void guardar() {
        System.out.println("Guardando emoción…");

        // Aquí luego conectas tu DAO
    }

    @FXML
    private void cancelar() {
        System.out.println("Cancelar y volver a lista");
    }
}
*/
    }
}