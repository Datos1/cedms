package ac.cr.tec.ce1103.cedms;


import ac.cr.tec.ce1103.cedms.server.*;

public class App {

    public static void main(String[] args) {
        Server_Socket newserver=new Server_Socket();
        newserver.initConnection();
    }


}