package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;

import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pablo on 10/11/14.
 */
public abstract class Core {
    public static final String ASK_PORT = "Por favor ingrese el puerto: ";
    public static final String ASK_IP = "Por favor ingrese el ip de conexion: ";
    static protected final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    static protected Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    static protected Pattern PORT_PATTERN = Pattern.compile("\\d{1,5}");
    protected int id;
    protected int port;
    protected List<UpdateId> updateIds = new List<UpdateId>();
    protected List<Connection> connections = new List<Connection>();
    protected Scanner terminal;
    protected Socket socket = new Socket();
    protected Queue queueIn = new Queue();
    protected Queue queueOut = new Queue();


    protected Core(int pId, int pPort) {
        this.id = pId;
        this.port = pPort;
    }

    public abstract void recibirConnection(int source, int target, int id, int adyacente, int precio, int updateId);

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
    public abstract void difusion(String msg);


    protected abstract void initScanner();

    protected abstract void createConnection();

    protected String askIP() {
        System.out.println(ASK_IP);
        if (terminal.hasNext(IPV4_PATTERN)) {
            return terminal.next();
        }
        return askIP();
    }

    protected int askPort() {
        System.out.println(ASK_PORT);
        if (terminal.hasNext(PORT_PATTERN)) {
            return Integer.parseInt(terminal.next());
        }
        return askPort();
    }

}
