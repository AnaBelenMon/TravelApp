package com.example.travelapp.dataAccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase que representa la configuración de conexión a la base de datos.
 * Se utiliza para leer los datos desde un archivo XML mediante JAXB.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "connection")
public class ConnectionProperties implements Serializable {

    /** Servidor de la base de datos */
    private String server;

    /** Puerto de conexión */
    private String port;

    /** Nombre de la base de datos */
    private String dataBase;

    /** Usuario de la base de datos */
    private String user;

    /** Contraseña del usuario */
    private String password;

    /**
     * Constructor vacío necesario para la deserialización XML (JAXB).
     */
    public ConnectionProperties() {}

    /**
     * Constructor completo para inicializar todos los parámetros de conexión.
     *
     * @param server servidor de la base de datos
     * @param port puerto de conexión
     * @param dataBase nombre de la base de datos
     * @param user usuario de la base de datos
     * @param password contraseña del usuario
     */
    public ConnectionProperties(String server, String port, String dataBase, String user, String password) {
        this.server = server;
        this.port = port;
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }

    /** @return usuario de la base de datos */
    public String getUser() {
        return user;
    }

    /** @return contraseña del usuario */
    public String getPassword() {
        return password;
    }

    /**
     * Construye la URL de conexión JDBC.
     *
     * @return URL de conexión a MySQL
     */
    public String getURL() {
        if (server == null || port == null || dataBase == null) {
            throw new RuntimeException("ConnectionProperties incompleto (null values)");
        }

        return "jdbc:mysql://" + server + ":" + port + "/" + dataBase;
    }
}