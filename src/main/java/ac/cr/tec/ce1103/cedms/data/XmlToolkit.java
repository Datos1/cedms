package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
    public static final String NODO = "nodo";
    public static final String RUTA = "ruta";
    public static final String PESO = "peso";

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
        XmlMessage type = null;
        for (XmlMessage m : XmlMessage.values()) {
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
    private static void readMessageAux(Core output, Document xml, XmlMessage type) {
        if (type == XmlMessage.DESCONECTAR) readInDesconectar(output, xml);
        else if (type == XmlMessage.PESO) readInPeso(output, xml);
        else {
            String updateId = xml.getElementsByTagName(UPDATEID).item(HEAD).getTextContent();
            switch (type) {
                case CONNECTION:
                    readInConnection(output, xml, updateId);
                    break;
                case GRAFO:
                    readInGrafo(output, xml, updateId);
                    break;
                case MENSAJE:
                    readInMessage(output, xml, updateId);
                    break;
            }
        }
    }

    /**
     * lee el XML de desconectar y lo interpreta
     * @param output
     * @param xml
     */
    private static void readInDesconectar(Core output, Document xml) {
        long source = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        long target = Long.parseLong(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        output.recibirDesconectar(source, target);
    }

    /**
     * lee el XML del peso y lo interpreta
     * @param output
     * @param xml
     */
    private static void readInPeso(Core output, Document xml) {
        long source = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        long target = Long.parseLong(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        int peso = Integer.parseInt(xml.getElementsByTagName(PESO).item(HEAD + 1).getTextContent());
        output.recibirCambiarPeso(source, target, peso);
    }

    /**
     * lee el XML del grafo y lo interpreta
     * @param output
     * @param xml
     * @param updateId
     */
    private static void readInGrafo(Core output, Document xml, String updateId) {
        long source = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        long target = Long.parseLong(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        NodeList dispositivos = xml.getElementsByTagName(DISPOSITIVO);
        List<Dispositivo> dispositivosList = new List<Dispositivo>();
        for (int i = 0; i < dispositivos.getLength(); i++) {
            Element nodo = (Element) dispositivos.item(i);
            Dispositivo ele = new Dispositivo(nodo.getAttribute(ID), nodo.getAttribute(TIPO), nodo.getAttribute(PUERTO));
            dispositivosList.append(ele);
        }
        NodeList conexiones = xml.getElementsByTagName(CONEXION);
        List<Connection> conexionesList = new List<Connection>();
        for (int i = 0; i < conexiones.getLength(); i++) {
            Element nodo = (Element) conexiones.item(i);
            Connection ele = new Connection(Long.parseLong(nodo.getAttribute(SOURCE)), Long.parseLong(nodo.getAttribute(TARGET)),
                    Integer.parseInt(nodo.getAttribute(PRECIO)));
            conexionesList.append(ele);
        }

    }

    /**
     * lee el XML de un mensaje y lo interpreta.
     * @param output
     * @param xml
     * @param updateId
     */
    private static void readInMessage(Core output, Document xml, String updateId) {
        long source = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
        long target = Long.parseLong(xml.getElementsByTagName(TARGET).item(HEAD).getTextContent());
        String titulo = xml.getElementsByTagName(TITULO).item(HEAD).getTextContent();
        String msg = xml.getElementsByTagName(MSG).item(HEAD).getTextContent();
        int numero = Integer.parseInt(xml.getElementsByTagName(NUMERO).item(HEAD).getTextContent());
        if (xml.getElementsByTagName(RUTA).getLength() != 0) {
            NodeList ruta = xml.getElementsByTagName(NODO);
            List<Long> nodos = new List<Long>();
            for (int i = 0; i < ruta.getLength(); i++) {
                nodos.append(Long.parseLong(ruta.item(i).getNodeValue()));
            }
            output.recibirMensaje(new Mensaje(source, target, updateId, titulo, msg, numero, nodos));
        } else {
            output.recibirMensaje(new Mensaje(source, target, updateId, titulo, msg, numero));
        }
    }

    /**
     * lee el XML de conexion y lo interpreta.
     * @param output
     * @param xml
     * @param updateId
     */
    private static void readInConnection(Core output, Document xml, String updateId) {
        if (xml.getElementsByTagName(SOURCE).getLength() != 0) {
            long target = Long.parseLong(xml.getElementsByTagName(SOURCE).item(HEAD).getTextContent());
            output.recibirConnectionPhase1(target, updateId);
        } else {
            int precio = Integer.parseInt(xml.getElementsByTagName(PRECIO).item(HEAD).getTextContent());

            if (xml.getElementsByTagName(ID).getLength() != 0) {
                long id = Long.parseLong(xml.getElementsByTagName(ID).item(HEAD).getTextContent());
                long adyacente = Long.parseLong(xml.getElementsByTagName(ADYACENTE).item(HEAD).getTextContent());
                output.recibirConnection(updateId, precio, id, adyacente);
            } else {
                String type = xml.getElementsByTagName(TIPO).item(HEAD).getTextContent();
                output.recibirConnectionPhase2(updateId, precio, type);
            }

        }
    }

    /**
     * crea un xml con la conexion en la fase 1
     * @param source
     * @param updateId
     * @return
     */
    public static String newConnectionPhase1(long source, String updateId) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.CONNECTION.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);

        appendChild(root, sourceNode, updateIdNode);

        return XmlToolkit.xmlToString(xml);
    }

    /**
     * crea un XM de la conexion en la fase 2
     * @param precio
     * @param updateId
     * @param type
     * @return
     */
    public static String newConnectionPhase2(int precio, String updateId, String type) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.CONNECTION.toString());
        xml.appendChild(root);

        Element precioNode = crearElementoConTexto(xml, PRECIO, "" + precio);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);
        Element typeNode = crearElementoConTexto(xml, TIPO, "" + type);


        appendChild(root, precioNode, updateIdNode, typeNode);

        return XmlToolkit.xmlToString(xml);
    }

    /**
     * crea un XML de nueva conexion
     * @param id
     * @param adyacente
     * @param precio
     * @param updateId
     * @return
     */
    public static String newConnection(long id, long adyacente, int precio, String updateId) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.CONNECTION.toString());
        xml.appendChild(root);

        Element idNode = crearElementoConTexto(xml, ID, "" + id);
        Element adyNode = crearElementoConTexto(xml, ADYACENTE, "" + adyacente);
        Element precioNode = crearElementoConTexto(xml, PRECIO, "" + precio);
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, updateId);

        appendChild(root, idNode, adyNode, precioNode, updateIdNode);

        return XmlToolkit.xmlToString(xml);
    }

    /**
     * crea el mensaje dependiendo si tiene ruta o no, que se van a mandar por el socket
     * @param mensaje
     * @return
     */
    public static String createMessage(Mensaje mensaje) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.MENSAJE.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + mensaje.getSource());
        Element targetNode = crearElementoConTexto(xml, TARGET, "" + mensaje.getTarget());
        Element updateIdNode = crearElementoConTexto(xml, UPDATEID, mensaje.getUpdateId());
        Element tituloNode = crearElementoConTexto(xml, TITULO, mensaje.getTitulo());
        Element msgNode = crearElementoConTexto(xml, MSG, mensaje.getMsg());
        Element numeroNode = crearElementoConTexto(xml, NUMERO, "" + mensaje.getNumero());
        if (mensaje.getRuta() == null)// nos fijamos si tiene ruta pegada
            appendChild(root, sourceNode, targetNode, updateIdNode, tituloNode, msgNode, numeroNode);
        else
            appendChild(root, sourceNode, targetNode, updateIdNode, tituloNode, msgNode, numeroNode, crearRuta(xml, mensaje.getRuta()));
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
     * Crea una ruta llena de nodos
     * que contienen un long
     */
    public static Element crearRuta(Document document, List<Long> nodos) {
        Element elemento = document.createElement(RUTA);
        for (int i = 0; i < nodos.getLength(); i++) {
            Element nodo = document.createElement(NODO);
            nodo.appendChild(document.createTextNode(nodos.get(i) + ""));
            elemento.appendChild(nodo);
        }
        return elemento;
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
     * Metodo se encarga de crear el xml de desconexion
     * @return String xml
     */
    public static String crearDesconexion(long source, long target) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.DESCONECTAR.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element targetNode = crearElementoConTexto(xml, TARGET, "" + target);
        appendChild(root, sourceNode, targetNode);

        return xmlToString(xml);
    }
    /**
     * Metodo se encarga de crear el xml de cambio de Peso
     * @return String xml
     */
    public static String crearCambioPeso(long source, long target, int peso)
    {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.PESO.toString());
        xml.appendChild(root);

        Element sourceNode = crearElementoConTexto(xml, SOURCE, "" + source);
        Element targetNode = crearElementoConTexto(xml, TARGET, "" + target);
        Element pesoNode = crearElementoConTexto(xml, PESO, "" + peso);

        appendChild(root, sourceNode, targetNode);

        return xmlToString(xml);
    }
    /**
     * Crea  un grafo listo para ser enviado
     * @param connections lista conexiones
     * @param dispositivos lista dispositivos
     * @return
     */
    public String crearGrafo(long source, long target, String updateId, List<Connection> connections, List<Dispositivo> dispositivos) {
        Document xml = newDocument();
        Element root = xml.createElement(XmlMessage.GRAFO.toString());
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
     *
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
