package server;
import ac.cr.tec.ce1103.cedms.VARIABLES;
import java.net.*;
import java.io.*;
import ac.cr.tec.ce1103.cedms.VARIABLES.*;

/**
 * Created by Stiven on 11/9/2014.
 */
public class ServerSocket {

    private java.net.ServerSocket serv_socket;
    private int PORT;
    private Socket socket;
    private String messageReceived;
    private DataOutputStream output_msg;
    private DataInputStream input;

    public ServerSocket(int port, Socket sock, String message)
    {
        this.messageReceived=message;
        this.PORT=port;
        this.socket=sock;

    }


    public void initConnection(){
        BufferedReader input;
        try{//se pregunta al usuario si quiere una conexion, si la quiere hace lo siguiente:

            serv_socket=new java.net.ServerSocket(PORT);//creates socket with port.
            socket=serv_socket.accept();//initializes socket.

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));//reads the input.
            output_msg = new DataOutputStream(socket.getOutputStream());//write the output.
            messageReceived = input.readLine();
            System.out.println("message :"+messageReceived);
            serv_socket.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
