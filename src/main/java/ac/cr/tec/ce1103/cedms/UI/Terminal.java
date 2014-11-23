package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.data.Commons;
import ac.cr.tec.ce1103.cedms.data.Connection;
import ac.cr.tec.ce1103.cedms.data.Mensaje;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import java.net.ConnectException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pablo on 19/11/14.
 * Clase abstracta
 */
public abstract class Terminal implements Commons {
    public static final String DESCONECTO = "Se desconecto: ";
    public static final String CONECTO = "Se conecto: ";
    public static final String SLASH = "-------------------------";
    public static final String SLASH_FINAL = "___________________________";
    public static final String ERROR_DE_CONEXION = "Error de conexion";
    public static final String BIENVENIDO = "Bienvenido a CEDMS";
    public static final String ASK_PORT = "Por favor ingrese el puerto de conexion: ";
    public static final String ASK_IP = "Por favor ingrese el ip de conexion: ";
    public static final String ASK_ID = "Por favor ingrese el id destino: ";
    public static final String INPUT_ERROR = "Respuesta invalida...";
    public static final String INVALID_ID = "Formato Id Invalido!";
    public static final String INVALID_IP = "Formato Ip Invalido!";
    public static final String INVALID_PORT = "Formato Puerto Invalido!";
    public static final String TRUE = "1";
    public static final long ID_SMALL_MASK = 1234567890123000l;
    public static final String SERVER_IP = "107.170.181.249";
    public static final String SERVER = "server";
    public static final String PRECIO = "Precio: ";
    public static final String CONEXION_HACIA = "Conexion hacia id:";
    public static final String DE_TIPO = "Tipo: ";
    static protected final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))|localhost|server";
    public static final String PARA_DESCONECTAR = "Para desconectar: ";
    public static final String PESO_INVALIDO = "Peso invalido.";
    public static final String ASK_PESO = "Por favor ingrese el peso deseado: ";
    public static final String MENSAJE_DE = "Mensaje de: ";
    public static final String ASUNTO = "Asunto: ";
    public static final String MENSAJE = "Mensaje: ";
    static protected Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    protected static String OPCIONES;
    static protected Pattern ID_PATTERN = Pattern.compile("\\d{16}");
    static protected Pattern ID_PATTERN_SMALL = Pattern.compile("\\d{3}");
    static protected Pattern PORT_PATTERN = Pattern.compile("\\d{1,5}");
    static protected Pattern BOOLEAN_PATTERN = Pattern.compile("[01]");
    static protected Pattern OPCIONES_PATTERN;
    protected Scanner terminal;
    protected boolean on = true;
    protected Core core;


    public Terminal(Core pCore) {
        this.core = pCore;
        terminal = new Scanner(System.in);
    }

    protected void init() {
        terminal.reset();
        printBlankSpace();
        System.out.println(BIENVENIDO + " " + VERSION);
        askAndConnect();
        while (on) {
            menuOpciones();
        }
    }

    protected void printBlankSpace() {
        for (int i = 0; i < 3; i++)
            System.out.println("");
    }

    protected void verNodosAdyacentes() {
        List<Connection> connections = core.getConnections();
        for (int i = 0; i < connections.getLength(); i++) {
            Connection connection = connections.get(i);
            System.out.println(CONEXION_HACIA);
            System.out.println(connection.getTarget());
            System.out.println(PRECIO);
            System.out.println(connection.getPrecio());
            System.out.println(DE_TIPO);
            System.out.println(connection.getType());
            System.out.println(SLASH);
        }
        System.out.println(SLASH_FINAL);
    }


    protected void cambiarPesoNodo() {
        System.out.println(PARA_DESCONECTAR);
        core.cambiarPesoNodo(askID(), askPeso());
    }


    protected void desconectar() {
        core.desconectar(askID());
    }

    protected abstract void menuOpciones();


    public void askAndConnect() {
        String ip = askIP();
        int port = askPort();
        try {
            core.createConnectionPhase1(ip, port);// llamamos a core
        } catch (ConnectException e) {
            System.out.println(ERROR_DE_CONEXION);
            askAndConnect();
        }
    }


    public String askIP() {
        System.out.println(ASK_IP);
        if (terminal.hasNext(IPV4_PATTERN)) {// verificamos que sea un puerto valido
            String ip = terminal.nextLine();
            if (ip.equals(SERVER))
                return SERVER_IP;
            return ip;
        } else {
            System.out.println(INVALID_IP);
            terminal.nextLine();
        }
        return askIP();
    }

    /**
     * Metodo para preguntar ids
     * @return el id destino
     */
    public long askID() {
        System.out.println(ASK_ID);
        if (terminal.hasNext(ID_PATTERN)) {// verificamos que sea un id valido
            return Long.parseLong(terminal.nextLine());
        } else if (terminal.hasNext(ID_PATTERN_SMALL))// para ahorrar tiempo se usan ids de tres digitos
        {
            return ID_SMALL_MASK + Long.parseLong(terminal.next());
        } else {
            System.out.println(INVALID_ID);
            terminal.nextLine();
        }
        return askID();
    }

    /**
     * M
     * @return
     */
    public int askPeso() {
        System.out.println(ASK_PESO);
        if (terminal.hasNextInt()) {// verificamos que sea un numero valido
            return Integer.parseInt(terminal.nextLine());
        }
         else {
            System.out.println(PESO_INVALIDO);
            terminal.nextLine();
        }
        return askPeso();
    }
    public boolean yesNoQuestion(String text) {
        System.out.println(text);
        if (terminal.hasNext(BOOLEAN_PATTERN)) {
            return (terminal.nextLine()).equals(TRUE);
        } else {
            System.out.println(INPUT_ERROR);
            terminal.nextLine();
            return yesNoQuestion(text);
        }
    }

    public int askPort() {
        System.out.println(ASK_PORT);
        if (terminal.hasNext(PORT_PATTERN)) { //verifica que sea en formato puerto
            return Integer.parseInt(terminal.nextLine());
        } else {
            System.out.println(INVALID_PORT);
            terminal.nextLine();
        }
        return askPort();
    }


    /**
     * Se muestran todos los mensajes nuevos
     */
    protected void verMensajesProcesados() {
        List<Mensaje> mensajes = core.getMensajes();
        for (int i = 0; i < mensajes.getLength(); i++) {
            Mensaje mensaje = mensajes.get(i);
            System.out.print(MENSAJE_DE);
            System.out.println(mensaje.getSource());
            System.out.println(ASUNTO + mensaje.getTitulo());
            System.out.println(MENSAJE + mensaje.getMsg());
            System.out.println(SLASH);
        }
        System.out.println("No hay mas mensajes...");
    }

    public void nuevaConexionPrint(long id) {
        System.out.println(CONECTO + id);
    }

    public void desconexionPrint(long id) {
        System.out.println(DESCONECTO + id);
    }
}
