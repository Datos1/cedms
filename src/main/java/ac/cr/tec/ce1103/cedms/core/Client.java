package ac.cr.tec.ce1103.cedms.core;

import java.util.Scanner;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core implements Commons {
    public static final int INITAL_UPDATE_ID = 0;
    private int updateIds = INITAL_UPDATE_ID;
    public static final String BIENVENIDO = "Bienvenido a CEDMS.";
    public static final int INITAL_MESSAGE_ID = 1;


    public Client(int pId, int pPort) {
        super(pId, pPort);
        initScanner();
    }

    public void initScanner() {
        terminal = new Scanner(System.in);
        System.out.println(BIENVENIDO);

        String ip = askIP();
        int port = askPort();

        while (on) {
            System.out.println("Seleccione una de las siguientes opciones: 1:");
            if (terminal.hasNext()) {
                System.out.println(terminal.next());
            }
        }
    }


    @Override
    public void recibirConnection(int source, int target, int id, int adyacente, int precio, int updateId) {

    }

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    @Override
    public void difusion(String msg) {

    }


    public void sendMessage(int target, String titulo, String msg) {

    }
}
