package com.example.travelapp.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestión centralizada de la conexión a la base de datos.
 * Versión estable para proyecto académico.
 */
public class ConnectionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/travelapp?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private ConnectionBD() {}

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("✅ Conexión a la BD establecida correctamente");
            }

        } catch (SQLException e) {

            System.out.println("❌ ERROR: No se pudo conectar a la base de datos");
            e.printStackTrace();

            connection = null;
        }

        return connection;
    }
}