package ac.cr.tec.ce1103.cedms.core;

/**
 * Created by pablo on 11/11/14.
 */
public class Connection {
    private int source;
    private int target;
    private int precio;
    private int port;
    private int ip;

    public Connection(int pSource, int pTarget, int pPrecio) {
        source = pSource;
        target = pTarget;
        precio = pPrecio;
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
