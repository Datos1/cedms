package ac.cr.tec.ce1103.cedms.core;

import java.util.Scanner;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core {
    public static final int INITAL_UPDATE_ID = 0;
    private int updateIds = INITAL_UPDATE_ID;
    public static final int INITAL_MESSAGE_ID = 1;
    private int id;
    private int port;
    private boolean on = true;

    public Client(int pId, int pPort) {
        id = pId;
        port = pPort;
        readScanner();
    }

    public void readScanner() {
        terminal = new Scanner(System.in);
        while (on) {
            if (terminal.hasNext()) {
                //sendMessage();
            }
        }
    }

    @Override
    protected void createConnection() {

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
