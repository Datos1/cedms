package ac.cr.tec.ce1103.cedms;


import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.graphClass.Graph;
import ac.cr.tec.ce1103.cedms.graphClass.graphNode;

public class App {

    public static final String USAGE = "Usage: java App id_device  port device_type(client/hub/base_station)";
    public static final String CLIENT = "client";
    public static final String HUB = "hub";
    public static final String BASE_STATION = "base-station";

    public static void main(String[] args) {
        /*Graph graph=new Graph();
        BaseStation base1=new BaseStation(123456789,5012);
        BaseStation base2=new BaseStation(1234567,5012);
        Hub hub1=new Hub(123456,5012);
        Client client1= new Client(1239,5012);
        graphNode nodo1=new graphNode(base1);
        graphNode nodo2=new graphNode(hub1);
        graphNode nodo3=new graphNode(client1);
        graphNode nodo4=new graphNode(base2);
      //  nodo4.setId();
        graph.addNode(nodo1);
        System.out.println(nodo1.getId());
        graph.addNode(nodo2);
        System.out.println(nodo2.getId());
        graph.addNode(nodo3);
        System.out.println(nodo3.getId());
        graph.addNode(nodo4);
        System.out.println(nodo4.getId());

        graph.createLinks(nodo1,nodo2,1,12);
        graph.createLinks(nodo3,nodo2,1,45);*/

       // graph.getAllConnections();
        //graph.printAristas();

        Core core;
        if (args.length != 3)
            System.out.println(USAGE);
        try {
            long id = Long.parseLong(args[0]);
            int port = Integer.parseInt(args[1]);
            String type = args[2].toLowerCase();
            if (type.equals(CLIENT))
                core = new Client(id, port);
            else if (type.equals(HUB))
                core = new Hub(id, port);
            else if (type.equals(BASE_STATION))
                core = new BaseStation(id, port);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(USAGE);
        }
    }

}