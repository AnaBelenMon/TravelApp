package com.example.travelapp.controllers.componentes;

import com.example.travelapp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TarjetaRecuerdoController {
    public Button verRecuerdo;
    public Button eliminar;

    public void onclickButtonVerRecuerdo(ActionEvent actionEvent) {
        HelloApplication.setRoot("recuerdo");
    }

    public void onclickButtonEliminar(ActionEvent actionEvent) {
        HelloApplication.setRoot("dialogoConfirmacion");
    }
}
