package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
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
    public static final String TIPO = "tipo";
    public static final String ERROR_EN_FORMATO_DEL_XML = "Error en formato del XML";
    public static final String ERROR_EN_EL_XML = "Error en el XML";
    public static final String CONEXIONES = "conexiones";
    public static final String CONEXION = "conexion";
    public static final String DISPOSITIVO = "dispositivo";
    public static final String PUERTO = "puerto";
    public static final String DISPOSITIVOS = "dispositivos";

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
            System.out.println(ERROR_EN_EL_XML);
            return;
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
                e.printStackTrace();
                System.out.println(ERROR_EN_FORMATO_DEL_XML);
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
        String updateId = xml.getElementsByTagName(UPDATEID).item(HEAD).getTextContent();
        switch (type) {

            case CONNECTION:
                readInConnection(output, xml, updateId);
                break;
            // gets information relevant to message
            case GRAFO:
                //output.recibirGrafo();
                break;
            case MENSAJE:
                readInMessage(output, xml, updateId);
                break;
            case DESCONECTAR:
                //output.desconectar();
                break;
        }
    }

    private static void readInMessage(Core output, Document xml, String updateId) {
        long source = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        long target = Long.parseLong(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        String titulo = xml.getElementsByTagName(TITULO).item(HEAD).getTextContent();
        String msg = xml.getElementsByTagName(MSG).item(HEAD).getTextContent();
        int numero = Integer.parseInt(xml.getElementsByTagName(NUMERO).item(HEAD).getTextContent());
        output.recibirMensaje(source, target, updateId, titulo, msg, numero);
    }

    private static void readInConnection(Core output, Document xml, String updateId) {
        if (xml.getElementsByTagName(SOURCE).getLength() != 0) {
            long target = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
            output.recibirConnectionPhase1(target, updateId);
        } else {
            int precio = Integer.parseInt(xml.getElementsByTagName(PRECIO).item(HEAD).getTextContent());

            if (xml.getElementsByTagName(ID).getLength() != 0) {
                int id = Integer.parseInt(xml.getElementsByTagName(ID).item(HEAD).getTextContent());
                int adyacente = Integer.parseInt(xml.getElementsByTagName(ADYACENTE).item(HEAD).getTextContent());
                output.recibirConnection(updateId, precio, id, adyacente);
            } else {
                String type = xml.getElementsByTagName(TIPO).item(HEAD).getTextContent();
                output.recibirConnectionPhase2(updateId, precio, type);
            }

        }
    }

    //////////////////////////////
    public static String newConnectionPhase1(long source, String updateId) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.CONNECTION.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);

        appendChild(root, sourceNode, updateIdNode);

        return XmlToolkit.xmlToString(xml);
    }

    public static String newConnectionPhase2(int precio, String updateId, String type) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.CONNECTION.toString());
        xml.appendChild(root);

        Element precioNode = crearElementoConTexto(xml, PRECIO, "" + precio);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);
        Element typeNode = crearElementoConTexto(xml, TIPO, "" + type);


        appendChild(root, precioNode, updateIdNode, typeNode);

        return XmlToolkit.xmlToString(xml);
    }

    public static String newConnection(int id, int adyacente, int precio, String updateId) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.CONNECTION.toString());
        xml.appendChild(root);

        Element idNode = crearElementoConTexto(xml, ID, "" + id);
        Element adyNode = crearElementoConTexto(xml, ADYACENTE, "" + adyacente);
        Element precioNode = crearElementoConTexto(xml, PRECIO, "" + precio);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);

        appendChild(root, idNode, adyNode, precioNode, updateIdNode);

        return XmlToolkit.xmlToString(xml);
    }




    public static String createMessage(long source, long target, String updateId, String titulo, String msg, int numero) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.MENSAJE.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element targetNode = crearElementoConTexto(xml, TARGET, "" + target);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);
        Element tituloNode = crearElementoConTexto(xml, TITULO, titulo);
        Element msgNode = crearElementoConTexto(xml, MSG, msg);
        Element numeroNode = crearElementoConTexto(xml, NUMERO, "" + numero);

        appendChild(root, sourceNode, targetNode, updateIdNode, tituloNode, msgNode, numeroNode);

        return XmlToolkit.xmlToString(xml);
    }

    /**
     * Crea un nodo con nombre definido y con texto adentro.
     *
     * @param nombreNodo
     * @param texto
     * @return
     */
    private static Element crearElementoConTexto(Document document, String nombreNodo, String texto) {
        Element elemento = document.createElement(nombreNodo);
        elemento.appendChild((document.createTextNode(texto)));
        return elemento;
    }

    /**
     * le agrega todos los nodos a un root
     *
     * @param root la raiz
     * @param args elementos por agregar
     * @return
     */
    private static Element appendChild(Element root, Element... args) {
        for (Element arg : args) {
            root.appendChild(arg);
        }
        return root;
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

    /**
     * Crea  un grafo listo para ser enviado
     *
     * @param source
     * @param target
     * @param updateId
     * @param connections
     * @param dispositivos
     * @return
     */
    public String crearGrafo(long source, long target, String updateId, List<Connection> connections, List<Dispositivo> dispositivos) {
        Document xml = newDocument();
        Element root = xml.createElement(Message.GRAFO.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element targetNode = crearElementoConTexto(xml, TARGET, "" + target);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);
        Element conexionesNode = crearConexionesGrafo(xml, connections);
        Element dispositivosNode = crearDispositivosGrafo(xml, dispositivos);

        appendChild(root, sourceNode, targetNode, updateIdNode, conexionesNode, dispositivosNode);
        return xmlToString(xml);
    }

    /**
     * Creamos un elemento conexiones con hijos de conexion
     */
    public Element crearConexionesGrafo(Document document, List<Connection> connections) {
        Element element = document.createElement(CONEXIONES);
        for (int i = 0; i < connections.getLength(); i++) {
            Connection connection = connections.get(i);
            element.appendChild(crearConexion(document, connection.getSource(), connection.getTarget(), connection.getPrecio()));
        }
        return element;
    }

    /**
     * Creamos un elemento conexiones con hijos de conexion
     */
    public Element crearDispositivosGrafo(Document document, List<Dispositivo> dispositivos) {
        Element element = document.createElement(DISPOSITIVOS);
        for (int i = 0; i < dispositivos.getLength(); i++) {
            Dispositivo dispositivo = dispositivos.get(i);
            element.appendChild(crearDispositivo(document, dispositivo.getId(), dispositivo.getTipo(), dispositivo.getPuerto()));
        }
        return element;
    }

    /**
     * Crea conexiones para el grafo individuales
     * @param document
     * @param source
     * @param target
     * @param precio
     * @return
     */
    private Element crearConexion(Document document, long source, long target, int precio) {
        Element element = document.createElement(CONEXION);
        element.setAttribute(SOURCE, source + "");
        element.setAttribute(TARGET, target + "");
        element.setAttribute(PRECIO, precio + "");
        return element;
    }

    /**
     * Crea dispositivos para el grafo individuales
     * @return elemento creado
     */
    private Element crearDispositivo(Document document, long id, CoreType type, int puerto) {
        Element element = document.createElement(DISPOSITIVO);
        element.setAttribute(SOURCE, id + "");
        element.setAttribute(TIPO, type + "");
        element.setAttribute(PUERTO, puerto + "");
        return element;
    }

}
