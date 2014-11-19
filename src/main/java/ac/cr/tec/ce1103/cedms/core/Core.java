package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;
import ac.cr.tec.ce1103.cedms.serverClient.Client_Socket;

import java.net.ConnectException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pablo on 10/11/14.
 */
public abstract class Core {
    public static final String ASK_PORT = "Por favor ingrese el puerto de conexion: ";
    public static final String ASK_IP = "Por favor ingrese el ip de conexion: ";
    public static final String INPUT_ERROR = "Respuesta invalida...";
    static protected final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    static protected Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    static protected Pattern PORT_PATTERN = Pattern.compile("\\d{1,5}");
    static protected Pattern BOOLEAN_PATTERN = Pattern.compile("[01]");
    static protected Pattern OPCIONES_PATTERN;
    protected long id;
    protected int port;
    protected int updateCounter = 0;
    protected List<String> updateIds = new List<String>();
    protected List<Connection> connections = new List<Connection>();
    protected Scanner terminal;
    protected Queue queueIn = new Queue();
    protected Queue queueOut = new Queue();
    protected Client_Socket clientSocketTemp;
    protected Server_Socket serverSocket;
    protected boolean on = true;


    protected Core(long pId, int pPort) {
        this.id = pId;
        this.port = pPort;
    }

    public boolean isOn() {
        return on;
    }


    /**
     * It receives a message and
     *
     * @param msg
     */

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    public void difusion(String msg) {
        for (int i = 0; i < connections.getLength(); i++) {
            connections.get(i).getSocket().send(msg);
        }
    }


    protected abstract void initScanner();

    /**
     * This sends the connection on phase 1
     *
     * @param pIp
     * @param pPort
     */
    protected void createConnectionPhase1(String pIp, int pPort) throws ConnectException {

        this.clientSocketTemp = new Client_Socket(this, pIp, pPort);
        this.clientSocketTemp.send(XmlToolkit.newConnectionPhase1(id, nextUpdateId()));
    }

    protected String nextUpdateId() {
        return id + "-" + updateCounter++;
    }

    /**
     * This sends the connection on phase 2
     *
     * @param precio
     * @param updateId
     */
    protected void createConnectionPhase2(int precio, String updateId) {
        difusion(XmlToolkit.newConnectionPhase2(precio, updateId));
    }

    protected void createConnection(long id, long adyacente, int precio, String updateId) {
        difusion(XmlToolkit.newConnectionPhase2(precio, updateId));
    }

    protected String askIP() {
        System.out.println(ASK_IP);
        if (terminal.hasNext(IPV4_PATTERN)) {// verificamos que sea un puerto valido
            return terminal.next();
        }
        return askIP();
    }

    protected boolean yesNoQuestion(String text) {
        System.out.println(text);
        if (terminal.hasNext(BOOLEAN_PATTERN)) {
            return (terminal.next()).equals("1");
        } else {
            System.out.println(INPUT_ERROR);
            terminal.next();
            return yesNoQuestion(text);
        }
    }
    protected int askPort() {
        System.out.println(ASK_PORT);
        if (terminal.hasNext(PORT_PATTERN)) { //verifica que sea en formato puerto
            return Integer.parseInt(terminal.next());
        }
        return askPort();
    }

    public void recibirSocket(String messageReceived) {
        XmlToolkit.readMessage(messageReceived, this);
    }

    /**
     * Recibe la conexion en primera fase
     *
     * @param target
     * @param updateId
     */
    public void recibirConnectionPhase1(long target, String updateId) {
        int precio = new Random().nextInt(100);
        createConnectionPhase2(precio, updateId);
        createConnection(target, this.id, precio, updateId);
    }

    /**
     * Recibe la conexion en segunda fase
     *
     * @param updateId
     * @param precio
     */
    public void recibirConnectionPhase2(String updateId, int precio) {
        //no hace nada
    }


    /**
     * Recibe la conexion completa
     *
     * @param updateId
     * @param precio
     * @param id
     * @param adyacente
     */
    public abstract void recibirConnection(String updateId, int precio, int id, int adyacente);

    /**
     * Este metodo recibe el mensaje descifrado y ...
     *
     * @param source
     * @param target
     * @param updateId
     * @param titulo
     * @param msg
     * @param numero
     */
    public abstract void recibirMensaje(long source, long target, String updateId, String titulo, String msg, int numero);
}
