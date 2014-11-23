package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.HubTerminal;
import ac.cr.tec.ce1103.cedms.data.Connection;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
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
     * Este metodo recibe el mensaje descifrado y lo distribuye
     */
    @Override
    public void recibirMensaje(Mensaje mensaje) {
        if (mensaje.getRuta() != null) {
            List<Long> ruta = mensaje.getRuta();
            for (int i = 0; i < ruta.getLength(); i++) {
                if (ruta.get(i) == this.id) {
                    for (int j = 0; j < connections.getLength(); j++) {
                        Connection connection = connections.get(j);
                        if (connection.getTarget() == ruta.get(i + 1)) {
                            connection.getSocket().send(XmlToolkit.createMessage(mensaje));
                            return;
                        }
                    }
                    break;
                }
            }
        }
        difusion(XmlToolkit.createMessage(mensaje));
    }


    public List<String> getUpdates() {
        return updateIdsList;
    }
}
