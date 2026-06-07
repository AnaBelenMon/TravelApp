package com.example.travelapp.dataAccess;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Clase utilitaria encargada de la lectura y escritura de archivos XML
 * utilizando la librería JAXB.
 *
 * Permite serializar objetos Java a XML y deserializar archivos XML
 * para reconstruir objetos, facilitando la persistencia de configuraciones
 * o datos estructurados fuera de la base de datos.
 *
 * Esta clase se utiliza, por ejemplo, para cargar los datos de conexión
 * en {@link ConnectionBD} mediante la clase {@link ConnectionProperties}.
 */
public class XMLManager {

    /**
     * Serializa un objeto a formato XML y lo guarda en un archivo.
     *
     * <p>El proceso consiste en:</p>
     * <ol>
     *     <li>Crear un contexto JAXB para la clase del objeto.</li>
     *     <li>Configurar un {@link Marshaller} con formato legible.</li>
     *     <li>Generar el archivo XML en la ruta indicada.</li>
     * </ol>
     *
     * @param c        objeto a serializar
     * @param filename nombre del archivo destino
     * @param <T>      tipo genérico del objeto
     * @return true si la operación fue correcta, false si ocurrió un error
     */
    public static <T> boolean writeXML(T c, String filename) {
        boolean result = false;

        try {
            JAXBContext context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            m.marshal(c, new File(filename));
            result = true;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Lee un archivo XML y lo deserializa para reconstruir un objeto Java.
     *
     * <p>El proceso consiste en:</p>
     * <ol>
     *     <li>Crear un contexto JAXB basado en la clase del objeto de referencia.</li>
     *     <li>Configurar un {@link Unmarshaller}.</li>
     *     <li>Leer el archivo XML y convertirlo en un objeto Java.</li>
     * </ol>
     *
     * @param c        objeto de referencia para obtener el tipo
     * @param filename nombre del archivo XML a leer
     * @param <T>      tipo genérico del objeto
     * @return objeto reconstruido desde el XML; si ocurre un error, devuelve el objeto de referencia
     */
    public static <T> T readXML(T c, String filename) {
        T result = c;

        try {
            JAXBContext context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(new File(filename));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }
}
