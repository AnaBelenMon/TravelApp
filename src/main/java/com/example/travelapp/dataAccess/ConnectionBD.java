package com.example.travelapp.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase responsable de gestionar la conexión a la base de datos de forma centralizada.
 *
 * Implementa el patrón Singleton para asegurar que solo exista una única conexión
 * activa durante toda la ejecución de la aplicación, evitando múltiples conexiones
 * innecesarias y mejorando el rendimiento.
 *
 * La configuración de la conexión (URL, usuario y contraseña) se obtiene desde
 * un archivo XML externo mediante la clase {@link XMLManager}.
 *
 * Flujo de funcionamiento:
 * <ol>
 *     <li>Se lee el archivo <b>connection.xml</b> con los datos de conexión.</li>
 *     <li>Se crea la conexión mediante {@link DriverManager}.</li>
 *     <li>La conexión se almacena en una instancia estática y se reutiliza.</li>
 * </ol>
 *
 * Si ocurre un error durante la conexión, se captura la excepción y la conexión
 * queda establecida como <code>null</code>.
 */
public class ConnectionBD {
    private static final String FILE = "connection.xml";
    private static Connection con;
    private static ConnectionBD _instance;

    /**
     * Constructor privado para evitar instanciación externa.
     * Lee los datos del archivo XML y establece la conexión.
     */
    private ConnectionBD() {
        // Leer propiedades desde el archivo XML
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);

        try {
            con = DriverManager.getConnection(
                    properties.getURL(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            con = null;
        }
    }

    /**
     * Devuelve la conexión activa a la base de datos.
     * Si es la primera vez que se solicita, crea la instancia Singleton.
     *
     * @return conexión activa o null si no se pudo establecer
     */
    public static Connection getConnection() {
        if (_instance == null) {
            _instance = new ConnectionBD();
        }
        return con;
    }
}
