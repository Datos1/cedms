package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;

import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pablo on 10/11/14.
 */
public abstract class Core {
    protected int id;
    protected List<UpdateId> updateIds = new List<UpdateId>();
    protected List<Connection> connections = new List<Connection>();
    protected Scanner terminal;
    private Socket socket = new Socket();
    private Queue queueIn = new Queue();
    private Queue queueOut = new Queue();

    protected Core() {
    }

    /**
     * It receives a message and
     *
     * @param msg
     */
    //public abstract void recibir(String msg);

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
   /* public abstract void difusion(String msg);

    protected abstract void readScanner();

    protected abstract void createConnection();*/
}
