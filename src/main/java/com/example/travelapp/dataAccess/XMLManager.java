package com.example.travelapp.dataAccess;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Clase utilitaria encargada de la lectura y escritura de archivos XML
 * utilizando JAXB.
 */
public class XMLManager {

    /**
     * Serializa un objeto a XML y lo guarda en un archivo.
     *
     * @param c objeto a serializar
     * @param filename nombre del archivo destino
     * @param <T> tipo genérico del objeto
     * @return true si la operación fue correcta, false en caso contrario
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
     * Lee un archivo XML y lo deserializa a un objeto.
     *
     * @param c objeto de referencia para obtener el tipo
     * @param filename nombre del archivo XML
     * @param <T> tipo genérico del objeto
     * @return objeto reconstruido desde XML
     */
    public static <T> T readXML(T c, String filename) {
        T result = c;
        JAXBContext context;

        try{
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(new File(filename));
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return result;
    }
}