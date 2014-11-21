package ac.cr.tec.ce1103.cedms.server;

import ac.cr.tec.ce1103.cedms.core.Core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Stiven on 11/9/2014.
 */
public class Server_Socket implements Runnable {
    private final Core core;
    private Thread t;
    private java.net.ServerSocket serv_socket;
    private Socket socket;
    private DataOutputStream output_msg;
    private BufferedReader input;

    public Server_Socket(Core core, int port)
    {
        this.core = core;
        try {
            serv_socket = new java.net.ServerSocket(port);
            if (t == null) {
                t = new Thread(this);
                t.start();
            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
            System.exit(-1);
        }
    }


    public void initConnection(){

        try {//se pregunta al usuario si quiere una conexion, si la quiere hace lo siguiente:
            socket=serv_socket.accept();//waits until there is a connection.
            System.out.println(socket.getRemoteSocketAddress());
            System.out.println(socket.getPort());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));//receives the flow of data saved in a buffered reader.


        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        }


    }

    protected void listen() {
        String messageReceived;
        while (core.isOn()) {
            try {
                if ((messageReceived = input.readLine()) != null)//reads message
                    core.recibirSocket(messageReceived);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void run() {
        initConnection();
        listen();
    }
}
