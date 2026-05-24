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

    exports com.example.travelapp;
    exports com.example.travelapp.controllers;
    exports com.example.travelapp.model;
    exports com.example.travelapp.dao;
}
