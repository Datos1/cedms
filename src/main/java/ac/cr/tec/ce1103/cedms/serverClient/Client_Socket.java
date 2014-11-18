package ac.cr.tec.ce1103.cedms.serverClient;

import ac.cr.tec.ce1103.cedms.core.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stiven on 11/10/2014.
 */
public class Client_Socket {
    private PrintWriter out;
    private BufferedReader in;

    public Client_Socket(Core core, String hostName, int portNumber) {
        try {
            Socket echoSocket = new Socket(hostName, portNumber);

            out = new PrintWriter(echoSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        out.print(text);
    }

}
