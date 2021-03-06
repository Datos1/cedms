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
    public static final String NUEVA_CONEXION = "Nueva conexion entrante de:";
    private final Core core;
    private Thread t;
    private java.net.ServerSocket serv_socket;
    private Socket socket;
    private DataOutputStream output_msg;


    public Server_Socket(Core core, int port)
    {
        this.core = core;
            try {
                serv_socket = new java.net.ServerSocket(port);
                if (t == null) {
                    t = new Thread(this);
                    t.start();
                }
                //int newPort = 50000 - new java.util.Random().nextInt(5000);
                // Process p = Runtime.getRuntime().exec("ssh prbls@107.170.181.249 " + port + "localhost:" + newPort);
                //System.out.println(newPort);

            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
                System.exit(-1);
            }

    }


    public void initConnection(){

        try {//se pregunta al usuario si quiere una conexion, si la quiere hace lo siguiente:
            BufferedReader input;
            socket=serv_socket.accept();//waits until there is a connection.
            System.out.println(NUEVA_CONEXION);
            System.out.println(socket.getRemoteSocketAddress());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));//receives the flow of data saved in a buffered reader.
            new newServerConnection(input);

        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        }


    }




    @Override
    public void run() {
        while (core.isOn()) {
            initConnection();
        }
    }

    private class newServerConnection implements Runnable
    {
        Thread thread;
        BufferedReader input;
        public newServerConnection(BufferedReader input){
            this.input = input;
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        }

        /**
         Listen to connection
         */
        @Override
        public void run() {
            listen();

        }

        protected void listen() {
            String messageReceived;
            while (core.isOn()) {
                try {
                    if ((messageReceived = input.readLine()) != null)//reads message
                        System.out.println(messageReceived);
                        core.recibirSocket(messageReceived);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
