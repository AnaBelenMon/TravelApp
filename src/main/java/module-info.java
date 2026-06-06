module com.example.travelapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.xml.bind;

    opens com.example.travelapp to javafx.fxml;
    opens com.example.travelapp.controllers to javafx.fxml;
    opens com.example.travelapp.model to javafx.fxml;
    opens com.example.travelapp.dao to javafx.fxml;
    opens com.example.travelapp.dataAccess to java.xml.bind;

    exports com.example.travelapp.dataAccess;
    exports com.example.travelapp;
    exports com.example.travelapp.controllers;
    exports com.example.travelapp.model;
    exports com.example.travelapp.dao;
    exports com.example.travelapp.model.enums;
    opens com.example.travelapp.model.enums to javafx.fxml;
    exports com.example.travelapp.model.interfaces;
    opens com.example.travelapp.model.interfaces to javafx.fxml;
    exports com.example.travelapp.utils;
    opens com.example.travelapp.utils to javafx.fxml;
}
