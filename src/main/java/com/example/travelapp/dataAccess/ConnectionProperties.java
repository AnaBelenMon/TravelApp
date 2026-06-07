package com.example.travelapp.dataAccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase que representa la configuración necesaria para establecer la conexión
 * con la base de datos. Sus atributos se cargan desde un archivo XML mediante JAXB,
 * permitiendo separar la configuración del código fuente.
 *
 * Esta clase actúa como contenedor de datos (DTO) para:
 * <ul>
 *     <li>Servidor</li>
 *     <li>Puerto</li>
 *     <li>Nombre de la base de datos</li>
 *     <li>Usuario</li>
 *     <li>Contraseña</li>
 * </ul>
 *
 * Se utiliza directamente en {@link ConnectionBD} para construir la URL JDBC
 * y establecer la conexión con MySQL.
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
     * @param server   servidor de la base de datos
     * @param port     puerto de conexión
     * @param dataBase nombre de la base de datos
     * @param user     usuario de acceso
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
     * Obtiene el usuario configurado para la conexión.
     *
     * @return usuario de la base de datos
     */
    public String getUser() {
        return user;
    }

    /**
     * Obtiene la contraseña configurada para la conexión.
     *
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Construye la URL JDBC necesaria para conectar con MySQL.
     * El formato generado es:
     * <pre>
     * jdbc:mysql://servidor:puerto/baseDeDatos
     * </pre>
     *
     * @return URL de conexión JDBC
     */
    public String getURL() {
        return "jdbc:mysql://" + server + ":" + port + "/" + dataBase;
    }
}
