package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.serverClient.Client_Socket;

/**
 * Created by pablo on 11/11/14.
 */
public class Connection {
    private long source;
    private long target;
    private CoreType type;
    private int precio;
    private String updateId;
    private Client_Socket socket;

    public Connection(long pSource, String updateId, Client_Socket pSocket) {
        source = pSource;
        socket = pSocket;
    }

    public Client_Socket getSocket() {
        return socket;
    }

    public long getSource() {
        return source;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public CoreType getType() {
        return type;
    }

    public void setType(CoreType type) {
        this.type = type;
    }

    public String getUpdateId() {
        return updateId;
    }
}
