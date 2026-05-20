package com.example.travelapp.controllers.componentes;

import com.example.travelapp.HelloApplication;
import javafx.scene.control.Button;

public class BarraNavegacionViajeController {
    public Button resumen;
    public Button alojamiento;
    public Button transporte;
    public Button actividades;
    public Button gastos;
    public Button documentos;
    public Button recuerdos;
    public Button museos;
    public Button obras;
    public Button mapaCultural;

    public void onclickButtonResumen() {
        HelloApplication.setRoot("resumenViaje");
    }

    public void onclickButtonAlojamiento() {
        HelloApplication.setRoot("alojamiento");
    }

    public void onclickButtonTransporte() {
        HelloApplication.setRoot("transporte");
    }

    public void onclickButtonActividades() {
        HelloApplication.setRoot("actividades");
    }

    public void onclickButtonGastos() {
        HelloApplication.setRoot("gastos");
    }

    public void onclickButtonDocumentos() {
        HelloApplication.setRoot("documentos");
    }

    public void onclickButtonRecuerdos() {
        HelloApplication.setRoot("recuerdos");
    }

    public void onclickButtonMuseos() {
        HelloApplication.setRoot("museos_visitados");
    }

    public void onclickButtonObras() {
        HelloApplication.setRoot("obras");
    }

    public void onclickButtonMapaCultural() {
        HelloApplication.setRoot("mapaEmocultural");
    }
}
