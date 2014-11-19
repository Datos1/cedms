package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.serverClient.Client_Socket;

/**
 * Created by pablo on 11/11/14.
 */
public class Connection {
    private int source;
    private int target;
    private int precio;
    private Client_Socket socket;

    public Connection(int pSource, int pTarget, int pPrecio, Client_Socket pSocket) {
        source = pSource;
        target = pTarget;
        precio = pPrecio;
        socket = pSocket;
    }

    public Client_Socket getSocket() {
        return socket;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public int getPrecio() {
        return precio;
    }
}
