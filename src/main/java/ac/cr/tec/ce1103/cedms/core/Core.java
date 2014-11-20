package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.Terminal;
import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;
import ac.cr.tec.ce1103.cedms.serverClient.Client_Socket;

import java.net.ConnectException;
import java.util.Random;

/**
 * Created by pablo on 10/11/14.
 */
public abstract class Core {
    public static final int INITAL_UPDATE_ID = 0;
    protected int updateIds = INITAL_UPDATE_ID;
    protected long id;
    protected int port;
    protected int updateCounter = 0;
    protected List<String> updateIdsList = new List<String>(); // importante!! el update id de mensajes posee un append de un guion y el numero
    protected List<Connection> connections = new List<Connection>();
    protected Terminal ui;
    protected Queue queueIn = new Queue();
    protected Queue queueOut = new Queue();
    protected Server_Socket serverSocket;
    protected boolean on = true;
    protected CoreType type;


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


    /**
     * This sends the connection on phase 1
     *
     * @param pIp
     * @param pPort
     */
    public void createConnectionPhase1(String pIp, int pPort) throws ConnectException {
        String updateId = nextUpdateId();
        Client_Socket clientSocketTemp = new Client_Socket(this, pIp, pPort);
        clientSocketTemp.send(XmlToolkit.newConnectionPhase1(id, updateId));
        connections.append(new Connection(this.id, updateId, clientSocketTemp));

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
        difusion(XmlToolkit.newConnectionPhase2(precio, updateId, type.toString()));
    }

    protected void createConnection(long id, long adyacente, int precio, String updateId) {
        difusion(XmlToolkit.newConnectionPhase2(precio, updateId, type.toString()));
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
