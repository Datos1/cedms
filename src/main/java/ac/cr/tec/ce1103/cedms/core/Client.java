package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.data.XmlToolkit;
import ac.cr.tec.ce1103.cedms.dataStructures.Queue;
import ac.cr.tec.ce1103.cedms.server.Server_Socket;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core implements Commons {
    public static final int INITAL_UPDATE_ID = 0;
    private int updateIds = INITAL_UPDATE_ID;
    public static final String TITULO = "Por favor proporcione el titulo del mensaje: ";
    public static final String ASK_MENSAJE = "Por favor ingrese el mensaje: ";
    public static final String ASK_ID = "Por favor ingrese el id destino: ";
    public static final String INVALID_ID = "Formato Id Invalido!";
    public static final String NO_NEW_MESSAGES = "No hay mensajes nuevos...";
    public static final String NUEVO_MENSAJE = "Nuevo mensaje de: ";
    public static final String SLASH = "----------------------";
    public static final String REPLY = "Desea responder o descartar? (1/0): ";
    public static final String ERROR_DE_CONEXION = "Error de conexion";
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion, 2:Enviar Mensajes 3: Ver Mensajes Nuevos 4: Desconectar ";
    public static final String BIENVENIDO = "Bienvenido a CEDMS.";
    public static final int INITAL_MESSAGE_ID = 1;
    static protected Pattern OPCIONES_PATTERN = Pattern.compile("[1-5]");
    static protected Pattern ID_PATTERN = Pattern.compile("\\d{16}");
    private Queue<Mensaje> nuevosMensajes = new Queue<Mensaje>();


    public Client(long pId, int pPort) {
        super(pId, pPort);
        this.serverSocket = new Server_Socket(this, pPort);
        initScanner();
    }

    public void initScanner() {
        terminal = new Scanner(System.in);
        System.out.println(BIENVENIDO);

        askAndConnect();

        while (on) {
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
    }

    private void verMensajes() {
        for (int i = 0; i < nuevosMensajes.getLength(); i++) {
            Mensaje mensaje = nuevosMensajes.deQueue();
            System.out.println(NUEVO_MENSAJE);
            System.out.print(mensaje.getSource());
            System.out.println(mensaje.getTitulo());
            System.out.println(SLASH);
            System.out.println(mensaje.getMsg());
            if (yesNoQuestion(REPLY)) {
                String msg = "";
                System.out.println(ASK_MENSAJE);
                if (terminal.hasNext()) {
                    msg = terminal.next();

                    difusion(XmlToolkit.createMessage(mensaje.getSource(), mensaje.getTarget(), mensaje.getUpdateId(),
                            mensaje.getTitulo(), msg, mensaje.getNumero() + 1));
                }
            }
        }
        System.out.println(NO_NEW_MESSAGES);
    }

    private void askAndSend() {
        String titulo = "";
        String msg = "";
        long id = 0;
        System.out.println(ASK_ID);
        if (terminal.hasNext(ID_PATTERN)) {
            id = Long.parseLong(terminal.next());
        } else {
            System.out.println(INVALID_ID);
            terminal.next();
            askAndSend();
        }

        System.out.println(TITULO);
        if (terminal.hasNext()) {
            titulo = terminal.next();
        }
        System.out.println(ASK_MENSAJE);
        if (terminal.hasNext()) {
            msg = terminal.next();
        }
        sendMessage(id, titulo, msg);
    }


    protected void askAndConnect() {
        try {
            createConnectionPhase1(askIP(), askPort());
        } catch (Exception e) {
            System.out.println(ERROR_DE_CONEXION);
            askAndConnect();
        }
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
        if (target == this.id)
            nuevosMensajes.add(new Mensaje(source, target, updateId, titulo, msg, numero));
        else
            difusion(XmlToolkit.createMessage(source, target, updateId, titulo, msg, numero));
    }

    public void sendMessage(long target, String titulo, String msg) {
        difusion(XmlToolkit.createMessage(this.id, target, nextUpdateId(), titulo, msg, INITAL_MESSAGE_ID));
    }
}
