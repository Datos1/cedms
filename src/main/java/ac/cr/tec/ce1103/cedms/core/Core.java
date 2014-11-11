package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;

import java.net.Socket;

/**
 * Created by pablo on 10/11/14.
 */
public abstract class Core {
    protected int id;
    protected List<Integer> updateIds = new List<Integer>();
    private Socket socket = new Socket();
    private Queue queueIn = new Queue();
    private Queue queueOut = new Queue();

    /**
     * It receives a message and
     *
     * @param msg
     */
    public abstract void recibir(String msg);

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    public abstract void difusion(String msg);


}
