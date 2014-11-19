package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.dataStructures.List;

import java.util.Hashtable;


public class Graph {
    private int num_nodes;
    private  int num_aristas;
    private List graph_nodes =new List<Node>();//contains base station,hub or client.
    private Hashtable nodes=new Hashtable<Integer,Node>();
    private List aristas=new List<Link>();


    public List getAristas() {
        return aristas;
    }

    public void setAristas(List aristas) {
        this.aristas = aristas;
    }

    public List getGraph_nodes() {
        return graph_nodes;
    }

    public void setGraph_nodes(List graph_nodes) {
        this.graph_nodes = graph_nodes;
    }

    public int getNum_aristas() {
        return num_aristas;
    }

    public void setNum_aristas(int num_aristas) {
        this.num_aristas = num_aristas;
    }

    public Hashtable<String,Node> getNodes() {
        return nodes;
    }

    public void setNodes(Hashtable<String,Node> nodes) {
        this.nodes = nodes;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes() {
        this.num_nodes = graph_nodes.getLength();
    }

    public void createLinks(Node a,Node b,int dirigido,int weight){
        if (dirigido==0 || dirigido==1){// dirigido param: 1 if it is directed(directed from a to b).
            if(dirigido==0){            //0 if it has links to both sides.
                a.addLink(a.getId(),b.getId(),weight);
                b.addLink(b.getId(),a.getId(),weight);
            }
            else if(dirigido==1)
                    a.addLink(a.getId(),b.getId(),weight);
        }
        else{
            System.out.println("error");
        }
    }

    public boolean searchNode(Node node){
        for(int i=0;i<nodes.size();i++){//duda
            if(node.equals(nodes.get(i))){
                return true;
            }

        }
        return false;
    }

    public void addNode(Node nombre){
        graph_nodes.append(nombre);
        nodes.put(nombre.getId(),new Node(nombre));
    }

   /* public void addLink(String nodoInicial,String nodoTerminal,int peso){
        Link nuevo=new Link(nodoInicial,nodoTerminal,peso);
        int i=searchIndex(nuevo.getWeight());

        if(i==-1)
            aristas.append(nuevo);
        else
            aristas.insertInPosition(i,nuevo);//*

        //nodes.get(nodoInicial).addLink(nodoTerminal, peso);//duda *
        //nodes.get(nodoTerminal).addLink(nodoInicial, peso);
    }*/

   /* public int searchIndex(int peso){
        for(int i=0;i<aristas.getLength();i++){
            if(peso<(int)aristas.get(i))
                return i;
        }
        return -1;
    }*/

}
