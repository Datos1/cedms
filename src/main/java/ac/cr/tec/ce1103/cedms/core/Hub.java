package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.HubTerminal;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
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
     * @param xml
     */
    @Override
    public void difusion(String xml) {

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
     * Este metodo recibe el mensaje descifrado y lo distribuye
     */
    @Override
    public void recibirMensaje(Mensaje mensaje) {
        if (mensaje.getRuta() == null)
            difusion(XmlToolkit.createMessage(mensaje));
    }

}
