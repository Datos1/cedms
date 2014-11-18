package ac.cr.tec.ce1103.cedms.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Stiven on 11/9/2014.
 */
public class Server_Socket {

    private java.net.ServerSocket serv_socket;
    private int PORT=5012;
    private Socket socket;
    private String messageReceived;
    private DataOutputStream output_msg;
    private DataInputStream input;

    /*public Server_Socket(int port, Socket sock, String message)
    {
        this.messageReceived=message;
        this.PORT=port;
        this.socket=sock;

    }*/


    public void initConnection(){
        BufferedReader input;
        try{//se pregunta al usuario si quiere una conexion, si la quiere hace lo siguiente:

            serv_socket=new java.net.ServerSocket(PORT);//creates socket with port.
            System.out.println("Waiting for connection.....");
            socket=serv_socket.accept();//waits until there is a connection.

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));//receives the flow of data saved in a buffered reader.
            output_msg = new DataOutputStream(socket.getOutputStream());//is the flow of data that goes as the output.
            output_msg.writeUTF("Succesful Connection... \t"+"Write a message: ");

            //Reception of the message
            messageReceived = input.readLine();//reads message
            System.out.println("message :"+messageReceived);
            output_msg.writeUTF("Message Received: "+messageReceived);

            output_msg.writeUTF("\t");

            //serv_socket.close();//finishes the connection

        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }


    }
}
