package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.BaseStationTerminal;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

/**
 * Created by pablo on 10/11/14.
 */
public class BaseStation extends Core {
    private List<Mensaje> mensajesPendientes = new List<Mensaje>();

    public BaseStation(long pId, int pPort) {
        super(pId, pPort);
        type = CoreType.BASESTATION;
        this.serverSocket = new Server_Socket(this, pPort);
        ui = new BaseStationTerminal(this);
    }


    /**
     * Este metodo recibe el mensaje descifrado y ...
     *
     * @param mensaje
     */
    @Override
    public void recibirMensaje(Mensaje mensaje) {

    }


}
