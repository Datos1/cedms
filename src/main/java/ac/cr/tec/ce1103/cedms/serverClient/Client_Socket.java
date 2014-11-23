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
public class Client_Socket {
    public static final String UNKNOWN_HOST = "Unknown Host";
    private PrintWriter out;
    Socket echoSocket = null;

    public Client_Socket(String hostName, int portNumber) throws ConnectException {

        try {
            try {
                echoSocket = new Socket(hostName, portNumber);
            } catch (ConnectException e) {
                throw e;
            } catch (UnknownHostException e) {
                System.out.println(UNKNOWN_HOST);
            }

            out = new PrintWriter(echoSocket.getOutputStream(), true);

        } catch (ConnectException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        out.println(text);
    }


    public void close() {
        try {
            echoSocket.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
