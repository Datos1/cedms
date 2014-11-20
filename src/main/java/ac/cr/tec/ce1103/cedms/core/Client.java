package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.ClientTerminal;
import ac.cr.tec.ce1103.cedms.data.Commons;
import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core implements Commons {
    public static final int INITAL_MESSAGE_ID = 1;
    private List<Mensaje> mensajes = new List<Mensaje>();
    private Queue<Mensaje> nuevosMensajes = new Queue<Mensaje>();


    public Client(long pId, int pPort) {
        super(pId, pPort);
        this.serverSocket = new Server_Socket(this, pPort);
        ui = new ClientTerminal(this);
    }


    /**
     * Recibe la conexion en segunda fase
     *
     * @param updateId
     * @param precio
     */
    @Override
    public void recibirConnectionPhase2(String updateId, int precio) {

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
     * Este metodo recibe el mensaje descifrado y lo agrega a la cola de mensajes.
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
        if (target == this.id) {
            Mensaje nuevo = new Mensaje(source, target, updateId, titulo, msg, numero);
            mensajes.append(nuevo);
            nuevosMensajes.add(nuevo);
        } else
            difusion(XmlToolkit.createMessage(source, target, updateId, titulo, msg, numero));
    }

    public void sendMessage(long target, String titulo, String msg) {
        difusion(XmlToolkit.createMessage(this.id, target, nextUpdateId(), titulo, msg, INITAL_MESSAGE_ID));
    }

    public Queue<Mensaje> getNuevosMensajes() {
        return nuevosMensajes;
    }

    public void difusion(Mensaje mensaje, String text) {
        difusion(XmlToolkit.createMessage(mensaje.getSource(), mensaje.getTarget(), mensaje.getUpdateId(),
                mensaje.getTitulo(), text, mensaje.getNumero() + 1));

    }
}
