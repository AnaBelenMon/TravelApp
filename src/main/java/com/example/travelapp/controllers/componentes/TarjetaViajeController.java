package com.example.travelapp.controllers.componentes;

import com.example.travelapp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TarjetaViajeController {
    public Button verViaje;
    public Button eliminar;

    public void onclickButtonVerViaje(ActionEvent actionEvent) {
        HelloApplication.setRoot("detalle_viaje");
    }

    public void onclickButtonEliminar(ActionEvent actionEvent) {
        HelloApplication.setRoot("dialogoConfirmacion");
    }
}
