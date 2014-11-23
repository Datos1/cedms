package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import java.util.regex.Pattern;

/**
 * Created by pablo on 19/11/14.
 */
public class ClientTerminal extends Terminal {
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion," +
            " 2:Enviar Mensajes 3: Ver Mensajes Nuevos 4: Ver mensajes procesados 5: Ver Nodos Adyacentes" +
            " 6: Cambiar peso Nodo 7: Desconectar ";
    public static final String ASK_TITULO = "Por favor proporcione el titulo del mensaje: ";
    public static final String ASK_MENSAJE = "Por favor ingrese el mensaje: ";
    public static final String NO_NEW_MESSAGES = "No hay mensajes nuevos...";
    public static final String NUEVO_MENSAJE = "Nuevo mensaje de: ";
    public static final String REPLY = "Desea responder o descartar? (1/0): ";



    static protected Pattern OPCIONES_PATTERN = Pattern.compile("[1-6]");
    protected Client client;

    public ClientTerminal(Client pClient) {
        super(pClient);
        this.client = pClient;// asignamos client
        init();
    }

    /**
     * Aqui se muestran las opciones del menu principal
     */
    @Override
    protected void menuOpciones() {
        System.out.println(OPCIONES);
        if (terminal.hasNext(OPCIONES_PATTERN)) {
            switch (Integer.parseInt(terminal.nextLine())) {
                case 1:
                    askAndConnect();
                    break;
                case 2:
                    askAndSend();
                    break;
                case 3:
                    verMensajes();
                    break;
                case 4:
                    verMensajesProcesados();
                    break;
                case 5:
                    verNodosAdyacentes();
                    break;
                case 6:
                    cambiarPesoNodo();
                    break;
                case 7:
                    desconectar();

            }
            System.out.println(SLASH);
        } else terminal.next();
    }



    /**
     * Se muestran todos los mensajes viejos
     */
    private void verMensajes() {
        Mensaje mensaje;
        while ((mensaje = client.getNuevoMensaje()) != null)// llamamos a client
        {
            System.out.print(NUEVO_MENSAJE);
            System.out.println(mensaje.getSource());
            System.out.println(ASUNTO + mensaje.getTitulo());
            System.out.println(MENSAJE + mensaje.getMsg());
            System.out.println(SLASH);
            if (yesNoQuestion(REPLY))
                client.responderMensaje(mensaje, askMensaje());// llamamos a client
        }
        System.out.println(NO_NEW_MESSAGES);
    }

    /**
     * Con el scanner preguntasmos
     * el mensaje y lo enviamos por primera vez
     */
    private void askAndSend() {
        long id = askID();
        String titulo = askTitulo();
        String msg = askMensaje();
        client.sendNewMessage(id, titulo, msg);// llamamos a client
    }

    /**
     * Con el scanner preguntasmos
     * el titulo del mensaje y lo devolvemos
     */
    private String askTitulo() {
        terminal.nextLine();
        System.out.println(ASK_TITULO);
        if (terminal.hasNextLine()) {
            return terminal.nextLine();
        } else {
            terminal.nextLine();
            return askTitulo();
        }
    }

    /**
     * Con el scanner preguntasmos
     * el mensaje y lo devolvemos.
     */
    private String askMensaje() {
        System.out.println(ASK_MENSAJE);
        if (terminal.hasNextLine()) {
            return terminal.nextLine();
        } else {
            terminal.nextLine();
            return askMensaje();
        }
    }
}
