package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.HubTerminal;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

/**
 * Created by pablo on 10/11/14.
 */
public class Hub extends Core {
    public Hub(long pId, int pPort) {
        super(pId, pPort);
        type = CoreType.HUB;
        this.serverSocket = new Server_Socket(this, pPort);
        ui = new HubTerminal(this);
    }


    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    @Override
    public void difusion(String msg) {

    }

    /**
     * Recibe la conexion completa
     *
     * @param updateId
     * @param precio
     * @param id
     * @param adyacente
     */
    @Override
    public void recibirConnection(String updateId, int precio, int id, int adyacente) {

    }

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
    @Override
    public void recibirMensaje(long source, long target, String updateId, String titulo, String msg, int numero) {

    }

}
