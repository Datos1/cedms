package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.BaseStationTerminal;
import ac.cr.tec.ce1103.cedms.data.Connection;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.data.Dispositivo;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.graphClass.Graph;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

/**
 * Created by pablo on 10/11/14.
 */
public class BaseStation extends Core {
    private List<Mensaje> mensajesPendientes = new List<Mensaje>();
    private Graph<Dispositivo> grafoConexiones;
    public BaseStation(long pId, int pPort) {
        super(pId, pPort);
        type = CoreType.BASESTATION;
        this.serverSocket = new Server_Socket(this, pPort);
        ui = new BaseStationTerminal(this);
    }

    /**
     * Recibe la conexion completa
     * y la agrega al grafo
     */
    @Override
    public void recibirConnection(String updateId, int precio, long id, long adyacente) {
       // grafoConexiones.addNode();
    }

    /**
     * Este metodo recibe el mensaje descifrado y ...
     *
     * @param mensaje
     */
    @Override
    public void recibirMensaje(Mensaje mensaje) {

    }

    /**
     * Es metodo se encarga de manejar el cambio de pesos
     *
     * @param peso   nuevo peso
     */
    @Override
    public void recibirCambiarPeso(long source, long target, int peso) {

    }

    /**
     * Es metodo se encarga de manejar las desconexiones
     *
     * @param source
     * @param target
     */
    @Override
    public void recibirDesconectar(long source, long target) {

    }


}
