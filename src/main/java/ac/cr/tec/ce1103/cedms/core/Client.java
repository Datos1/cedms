package ac.cr.tec.ce1103.cedms.core;

import ac.cr.tec.ce1103.cedms.server.Server_Socket;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core implements Commons {
    public static final int INITAL_UPDATE_ID = 0;
    private int updateIds = INITAL_UPDATE_ID;
    public static final String ERROR_DE_CONEXION = "Error de conexion";
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion, 2:Enviar Mensajes 3: Ver Mensajes Nuevos 4: Desconectar ";
    public static final String BIENVENIDO = "Bienvenido a CEDMS.";
    public static final int INITAL_MESSAGE_ID = 1;
    static protected Pattern OPCIONES_PATTERN = Pattern.compile("[1-5]");


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
                        break;


                }
                System.out.println("------");
            } else terminal.next();
        }
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

    public void sendMessage(int target, String titulo, String msg) {

    }
}
