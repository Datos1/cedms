package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.UI.ClientTerminal;
import ac.cr.tec.ce1103.cedms.data.Commons;
import ac.cr.tec.ce1103.cedms.data.CoreType;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core implements Commons {
    public static final int INITAL_MESSAGE_ID = 1;
    private Queue<Mensaje> nuevosMensajes = new Queue<Mensaje>();


    public Client(long pId, int pPort) {
        super(pId, pPort);
        type = CoreType.CLIENTE;
        this.serverSocket = new Server_Socket(this, pPort);
        ui = new ClientTerminal(this);
    }


    /**
     * Este metodo recibe el mensaje descifrado y lo agrega a la cola de mensajes.
     */
    @Override
    public void recibirMensaje(Mensaje mensaje) {
        if (mensaje.getTarget() == this.id) {
            nuevosMensajes.add(mensaje);
        } else
            difusion(XmlToolkit.createMessage(mensaje));
    }

    /**
     * Cuando nos llega un mensaje nuevo lo creamos y lo difundimos
     *
     * @param target destino
     * @param titulo
     * @param msg
     */
    public void sendNewMessage(long target, String titulo, String msg) {
        difusion(XmlToolkit.createMessage(new Mensaje(this.id, target, nextUpdateId(), titulo, msg, INITAL_MESSAGE_ID)));
    }

    public Mensaje getNuevoMensaje() {
        Mensaje mensaje = nuevosMensajes.deQueue();
        mensajes.append(mensaje);
        return mensaje;
    }

    /**
     * Este metodo responde el mensaje con otro texto y otro numero.
     *
     * @param mensaje
     * @param text
     */
    public void responderMensaje(Mensaje mensaje, String text) {
        difusion(XmlToolkit.createMessage(new Mensaje(mensaje.getTarget(), mensaje.getSource(), mensaje.getUpdateId(),
                mensaje.getTitulo(), text, mensaje.getNumero() + 1)));

    }

}
