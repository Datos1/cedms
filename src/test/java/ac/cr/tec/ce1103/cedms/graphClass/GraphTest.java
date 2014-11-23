package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.dataStructures.List;
import org.junit.Test;

public class GraphTest {

    @Test
    public void testGraph() throws Exception {

        Graph graph = new Graph();
        BaseStation base1 = new BaseStation(123456789, 5012);
        BaseStation base2 = new BaseStation(1234567, 5012);
        Hub hub1 = new Hub(123456, 5012);
        Client client1 = new Client(1239, 5012);
        GraphNode graph_node1 = new GraphNode(base1);
        GraphNode graph_node2 = new GraphNode(hub1);
        GraphNode graph_node3 = new GraphNode(client1);
       // graphNode graph_node4 = new graphNode(base2);
       // INSTANCE_COUNTER += 1;//contador para el id de cada instancia, ya sea hub, client o base station.
       // graph_node4.setId();
       // graph.addNode(graph_node1);

        graph.addNode(graph_node1);
        graph.addNode(graph_node2);

        graph.addNode(graph_node3);

        graph.adjacencyListConnections(graph_node1, graph_node2, 0,34);
        graph.adjacencyListConnections(graph_node3, graph_node1, 0,56);

       // String lik= ((graphNode) graph.getGraph_nodes().get(2)).getNombre_elemento();
        //int numlinks=(graph.getAllLinks()).getLength();
       // Link link=(Link)lik.getNode(0).getData();
        //graphNode gr=link.getTerminal();
        //List l= ((List) ((GraphNode) (graph.getGraph_nodes().get(0))).getLinks());
        //System.out.println("terminal: " + ((Link) l.get(0)).getTerminal().getNombre_elemento()+"\t");


        //List l2= ((List) ((GraphNode) (graph.getGraph_nodes().get(1))).getLinks());
        //System.out.println("terminal: " + ((Link) l2.get(0)).getTerminal().getNombre_elemento()+"\t");
        //List l3= ((List) ((GraphNode) (graph.getGraph_nodes().get(2))).getLinks());
        //System.out.println("terminal: " + ((Link) l3.get(0)).getTerminal().getNombre_elemento()+"\t");

       //System.out.println(graph.getAllLinks().getLength());
        /*System.out.println(((Link) graph.getAllLinks().get(0)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(1)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(2)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(3)).getWeight());
        System.out.println("precio cambiado: ");
        System.out.println(((Link) graph.getAllLinks().get(0)).getInitial().getNombre_elemento());
        System.out.println(((Link) graph.getAllLinks().get(0)).getTerminal().getNombre_elemento());
        System.out.println("\t");
        System.out.println(((Link) graph.getAllLinks().get(1)).getInitial().getNombre_elemento());
        System.out.println(((Link) graph.getAllLinks().get(1)).getTerminal().getNombre_elemento());
        System.out.println("\t");
        System.out.println(((Link) graph.getAllLinks().get(2)).getInitial().getNombre_elemento());
        System.out.println(((Link) graph.getAllLinks().get(2)).getTerminal().getNombre_elemento());
        System.out.println("\t");
        System.out.println(((Link) graph.getAllLinks().get(3)).getInitial().getNombre_elemento());
        System.out.println(((Link) graph.getAllLinks().get(3)).getTerminal().getNombre_elemento());
        System.out.println("\t");*/

        //List l= ((List) ((graphNode) (graph.getGraph_nodes().get(0))).getLinks());
       // System.out.println("terminal: " + ((Link) l.get(0)).getTerminal().getNombre_elemento()+"\t");


        //List l2= ((List) ((graphNode) (graph.getGraph_nodes().get(1))).getLinks());
        //System.out.println("terminal: " + ((Link) l2.get(0)).getTerminal().getNombre_elemento()+"\t");
       // List l3= ((List) ((graphNode) (graph.getGraph_nodes().get(2))).getLinks());
        //System.out.println("terminal: " + ((Link) l3.get(0)).getTerminal().getNombre_elemento()+"\t");

       /* graph.changePrice(((GraphNode) ((Link) graph.getAllLinks().get(0)).getInitial()), ((GraphNode) ((Link) graph.getAllLinks().get(0)).getTerminal()), 555);
        graph.changePrice(((GraphNode) ((Link) graph.getAllLinks().get(3)).getInitial()),((GraphNode) ((Link) graph.getAllLinks().get(3)).getTerminal()),555);
        System.out.println("\t");
        //System.out.println(((Link) graph.getAllLinks().get(1)).getInitial().getNombre_elemento());
        //System.out.println(((Link) graph.getAllLinks().get(1)).getTerminal().getNombre_elemento());
        System.out.println("\t");
        System.out.println(((Link) graph.getAllLinks().get(0)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(1)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(2)).getWeight());
        System.out.println(((Link) graph.getAllLinks().get(3)).getWeight());
        //((graphNode) (graph.getGraph_nodes().get(2))).print_NodeLinks();*/
        //System.out.println(graph.getGraph_nodes().getLength());
          //  i++;
        //}
       /* int i=0;
        while(i<3){
            try{
                if(((graphNode) (graph.getGraph_nodes().get(0))).hasLinks()){
                    System.out.println("ja");
                        List l= ((List) ((graphNode) (graph.getGraph_nodes().get(0))).getLinks());
                        System.out.println("terminal: " + ((Link) l.get(0)).getTerminal().getNombre_elemento()+"\t");
                    }
                if(((graphNode) (graph.getGraph_nodes().get(1))).hasLinks()) {
                    System.out.println("a");
                    List l2 = ((List) ((graphNode) (graph.getGraph_nodes().get(1))).getLinks());
                    System.out.println("terminal: " + ((Link) l2.get(0)).getTerminal().getNombre_elemento() + "\t");
                }
                if(((graphNode) (graph.getGraph_nodes().get(2))).hasLinks()) {
                    System.out.println("k");
                    List l3 = ((List) ((graphNode) (graph.getGraph_nodes().get(2))).getLinks());
                    System.out.println("terminal: " + ((Link) l3.get(0)).getTerminal().getNombre_elemento() + "\t");
                }
            }catch(Exception e){
                continue;
            }
        i++;
        }*/
       //for (int i = 0; i < l.getLength(); i++)

        //System.out.println("terminal: "+ (graphNode) graph.getGraph_nodes().get(1));
       // System.out.println("terminal: "+ (graphNode) graph.getGraph_nodes().get(2));

        /*for (int i = 0; i < graph.getAllLinks().getLength(); i++) {
            List lk = graph.getAllLinks();
            System.out.println("inicial de pos" + i + " : " + (String) (((Link) lk.get(i)).getInitial().getNombre_elemento()));
            System.out.println("terminal de pos" + i + " : " + (String) (((Link) lk.get(i)).getTerminal().getNombre_elemento()));
        }
        System.out.println("\t");
        graph.getArches(graph.getAllLinks());
        for (int i = 0; i < graph.getAllLinks().getLength(); i++) {
            System.out.println("inicial de pos" + i + " : " + graph.getAllLinks().get(i).getInitial().getNombre_elemento());
            System.out.println("terminal de pos" + i + " : " + graph.getAllLinks().get(i).getTerminal().getNombre_elemento());
        }
        /*for(int i=0;i<((graphNode)(graph.getGraph_nodes().get(0))).getActual_links();i++){
            System.out.println(((Link)(((graphNode)(graph.getGraph_nodes().get(0))).getLinks().get(i))).getWeight());
        }*/
        /*for(int i=0;i<graph.getNum_nodes();i++){
            for(int j=0;j<((graphNode)(graph.getGraph_nodes().get(i))).getNode_graphs().getLength();j++){
                System.out.println(((graphNode)(((graphNode)graph.getGraph_nodes().get(i)).getNode_graphs().get(j))).getNombre_elemento());
            }
        }*/
        /*for(int i=0;i<graph.getNum_nodes();i++){
            System.out.println(((graphNode)(graph.getGraph_nodes().get(i))).getNombre_elemento());
        }*/


        // graph.getAllConnections();
        //graph.printAristas();

    }


}