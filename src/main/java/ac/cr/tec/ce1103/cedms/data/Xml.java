package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.core.Core;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by pablo on 8/11/14.
 */
public class Xml {

    public static final String HEADER
            = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    public static final String SOURCE = "source";
    public static final int HEAD = 0;
    public static final String TARGET = "target";
    public static final String UPDATEID = "updateid";
    public static final String ID = "id";
    public static final String ADYACENTE = "adyacente";
    public static final String PRECIO = "precio";

    /**
     * Decodes a message and gives it to the other method
     *
     * @param input
     * @param output
     */
    public static void readMessage(String input, Core output) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document xmlFile = null;
        try {
            builder = factory.newDocumentBuilder();
            xmlFile = builder.parse(new InputSource(new StringReader(input)));
        } catch (Exception e) {
            System.out.println("Error en el xml");
        }
        String rootName = xmlFile.getDocumentElement().getNodeName();
        Message type = null;
        for (Message m : Message.values()) {
            if (m.equals(rootName)) {   //Identifies type of message
                type = m;
                break;
            }
        }
        if (type == null) return;
        else readMessageAux(output, xmlFile, type);
    }

    /**
     * It receives the type of a message and executes
     *
     * @param output
     * @param xml
     * @param type
     */
    private static void readMessageAux(Core output, Document xml, Message type) {
        int updateId = Integer.parseInt(xml.getElementsByTagName(UPDATEID).item(HEAD).getTextContent());
        int source = Integer.parseInt(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        int target = Integer.parseInt(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        switch (type) {

            case CONNECTION:
                int id = Integer.parseInt(xml.getElementsByTagName(ID).item(HEAD).getTextContent());
                int adyacente = Integer.parseInt(xml.getElementsByTagName(ADYACENTE).item(HEAD).getTextContent());
                int precio = Integer.parseInt(xml.getElementsByTagName(PRECIO).item(HEAD).getTextContent());
                output.recibirConnection(source, target, id, adyacente, precio, updateId);
                break;
            // gets information relevant to message
            case GRAFO:
                //output.recibirGrafo();
                break;
            case MENSAJE:
                //output.recibirMensaje();
                break;
            case DESCONECTAR:
                //output.desconectar();
                break;
        }
    }

    public static String newConnection(String id, String adyacente, String precio) {
        return "";
    }
}
