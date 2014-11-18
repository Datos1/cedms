package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.core.UpdateId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pablo on 8/11/14.
 */
public class XmlToolkit {

    public static final String HEADER
            = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    public static final String SOURCE = "source";
    public static final int HEAD = 0;
    public static final String TARGET = "target";
    public static final String UPDATEID = "updateid";
    public static final String ID = "id";
    public static final String ADYACENTE = "adyacente";
    public static final String PRECIO = "precio";
    public static final String TITULO = "titulo";
    public static final String MSG = "msg";
    public static final String NUMERO = "numero";

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
            System.out.println("Error en el XML");
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
        else
            try {
                readMessageAux(output, xmlFile, type);
            } catch (Exception e) {
                System.out.println("Error en formato del XML");
            }
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
                output.recibirConnection(source, target, updateId, id, adyacente, precio);
                break;
            // gets information relevant to message
            case GRAFO:
                //output.recibirGrafo();
                break;
            case MENSAJE:
                String titulo = xml.getElementsByTagName(TITULO).item(HEAD).getTextContent();
                String msg = xml.getElementsByTagName(MSG).item(HEAD).getTextContent();
                int numero = Integer.parseInt(xml.getElementsByTagName(NUMERO).item(HEAD).getTextContent());
                //output.recibirMensaje(source,target,updateId, titulo, msg, numero);
                break;
            case DESCONECTAR:
                //output.desconectar();
                break;
        }
    }

    public static String newConnection(int id, int adyacente, int precio, UpdateId updateId) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.CONNECTION.toString());
        xml.appendChild(root);

        Element idNode = xml.createElement(ID);
        Element adyNode = xml.createElement(ADYACENTE);
        Element precioNode = xml.createElement(PRECIO);
        Element updateIdNode = xml.createElement(UPDATEID);

        idNode.appendChild(xml.createTextNode("" + id));
        adyNode.appendChild(xml.createTextNode("" + adyacente));
        precioNode.appendChild(xml.createTextNode("" + precio));
        updateIdNode.appendChild(xml.createTextNode(updateId.toString()));

        root.appendChild(idNode);
        root.appendChild(adyNode);
        root.appendChild(precioNode);
        root.appendChild(updateIdNode);
        return XmlToolkit.xmlToString(xml);
    }


    /**
     * Creates new xml Document in blank
     *
     * @return
     */
    public static Document newDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return builder.newDocument();
    }

    /**
     * helpful tool that transforms Documento to string
     *
     * @param doc
     * @return
     */
    public static String xmlToString(Document doc) {
        {
            try {
                StringWriter stw = new StringWriter();
                Transformer serializer = TransformerFactory.newInstance().newTransformer();
                serializer.transform(new DOMSource(doc), new StreamResult(stw));
                return stw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}