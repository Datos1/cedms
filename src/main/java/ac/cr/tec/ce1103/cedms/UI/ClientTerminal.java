package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Mensaje;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;

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
    public static final String ASK_ID = "Por favor ingrese el id destino: ";
    public static final String NO_NEW_MESSAGES = "No hay mensajes nuevos...";
    public static final String NUEVO_MENSAJE = "Nuevo mensaje de: ";
    public static final String REPLY = "Desea responder o descartar? (1/0): ";
    static protected Pattern OPCIONES_PATTERN = Pattern.compile("[1-6]");
    protected Client client;

    public ClientTerminal(Client pClient) {
        super(pClient);
        this.client = pClient;// asignamos client
    }

    @Override
    protected void menuOpciones() {
        System.out.println(OPCIONES);
        if (terminal.hasNext(OPCIONES_PATTERN)) {
            switch (Integer.parseInt(terminal.next())) {
                case 1:
                    askAndConnect();
                    break;
                case 2:
                    askAndSend();
                    break;
                case 3:
                    verMensajes();
                    break;


            }
            System.out.println(SLASH);
        } else terminal.next();
    }

    private void verMensajes() {
        Queue<Mensaje> nuevosMensajes = client.getNuevosMensajes();// llamamos a client
        for (int i = 0; i < nuevosMensajes.getLength(); i++) {
            Mensaje mensaje = nuevosMensajes.deQueue();
            System.out.println(NUEVO_MENSAJE);
            System.out.print(mensaje.getSource());
            System.out.println(mensaje.getTitulo());
            System.out.println(SLASH);
            System.out.println(mensaje.getMsg());
            if (yesNoQuestion(REPLY)) {
                String text = "";
                System.out.println(ASK_MENSAJE);
                if (terminal.hasNext()) {
                    text = terminal.next();
                    client.difusion(mensaje, text);// llamamos a client
                }
            }
        }
        System.out.println(NO_NEW_MESSAGES);
    }

    private void askAndSend() {

        System.out.println(ASK_ID);
        long id = askID();
        String titulo = askTitulo();
        String msg = askMensaje();
        client.sendMessage(id, titulo, msg);// llamamos a client
    }

    private String askTitulo() {
        System.out.println(ASK_TITULO);
        if (terminal.hasNext()) {
            return terminal.next();
        } else {
            terminal.next();
            return askTitulo();
        }
    }

    private String askMensaje() {
        System.out.println(ASK_MENSAJE);
        if (terminal.hasNext()) {
            return terminal.next();
        } else {
            terminal.next();
            return askTitulo();
        }
    }
}
