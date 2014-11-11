package ac.cr.tec.ce1103.cedms.core;

import java.util.Scanner;

/**
 * Created by pablo on 10/11/14.
 */
public class Client extends Core {
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
                
            }
        }
    }


    /**
     * It receives a message and
     *
     * @param msg
     */
    @Override
    public void recibir(String msg) {

    }

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    @Override
    public void difusion(String msg) {

    }
}
