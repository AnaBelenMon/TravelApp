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
    private String server;
    private String port;
    private String dataBase;
    private String user;
    private String password;

    /**
     * Constructor vacío requerido por JAXB para la deserialización del XML.
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

    /**
     * Métodos getters para acceder a las propiedades de conexión.
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * Método para obtener la contraseña de la base de datos.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método para construir la URL de conexión a la base de datos utilizando el formato JDBC.
     * @return
     */
    public String getURL(){
        return "jdbc:mysql://"+server+":"+port+"/"+dataBase;
    }
}