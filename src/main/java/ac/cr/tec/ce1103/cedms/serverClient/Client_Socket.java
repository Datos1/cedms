package ac.cr.tec.ce1103.cedms.serverClient;

import ac.cr.tec.ce1103.cedms.core.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Stiven on 11/10/2014.
 */
public class Client_Socket implements Runnable {
    private PrintWriter out;
    private BufferedReader in;

    public Client_Socket(Core core, String hostName, int portNumber) throws ConnectException {
        Socket echoSocket = null;
        try {
            try {
                echoSocket = new Socket(hostName, portNumber);
            } catch (ConnectException e) {
                throw e;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            out = new PrintWriter(echoSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        } catch (ConnectException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        out.print(text);
    }


    @Override
    public void run() {

    }
}
