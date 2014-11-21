package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.data.Commons;

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
    public static final String SLASH = "----------------------";
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
    static protected final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))|localhost";
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
        System.out.println(BIENVENIDO + " " + VERSION);
        askAndConnect();
        while (on) {
            menuOpciones();
        }
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
            return terminal.next();
        } else {
            System.out.println(INVALID_IP);
            terminal.next();
        }
        return askIP();
    }

    public long askID() {
        System.out.println(ASK_ID);
        if (terminal.hasNext(ID_PATTERN)) {// verificamos que sea un puerto valido
            return Long.parseLong(terminal.next());
        } else if (terminal.hasNext(ID_PATTERN_SMALL))// para ahorrar tiempo se usan ids de tres digitos
        {
            return ID_SMALL_MASK + Long.parseLong(terminal.next());
        } else {
            System.out.println(INVALID_ID);
            terminal.next();
        }
        return askID();
    }

    public boolean yesNoQuestion(String text) {
        System.out.println(text);
        if (terminal.hasNext(BOOLEAN_PATTERN)) {
            return (terminal.next()).equals(TRUE);
        } else {
            System.out.println(INPUT_ERROR);
            terminal.next();
            return yesNoQuestion(text);
        }
    }

    public int askPort() {
        System.out.println(ASK_PORT);
        if (terminal.hasNext(PORT_PATTERN)) { //verifica que sea en formato puerto
            return Integer.parseInt(terminal.next());
        } else {
            System.out.println(INVALID_PORT);
            terminal.next();
        }
        return askPort();
    }

    public void nuevaConexionPrint(long id) {
        System.out.println(CONECTO + id);
    }

    public void desconexionPrint(long id) {
        System.out.println(DESCONECTO + id);
    }
}
