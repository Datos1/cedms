package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Node;

import java.util.Hashtable;


public class Graph {
    private int num_nodes;
    private  int num_aristas;
    private List graph_nodes =new List<graphNode>();//contains base station,hub or client.
    private Hashtable hashtable_nodes =new Hashtable<Integer,graphNode>();
    private List aristas=new List<Link>();


    public List getAristas() {
        return aristas;
    }

    public void setAristas(List aristas) {
        this.aristas = aristas;
    }

    public List getAllConnections(){
        for(int i=0;i<graph_nodes.getLength();i++){
            graphNode nod=(graphNode)graph_nodes.get(i);
            aristas.append(nod.getLinks());
        }
        return  aristas;
    }
    public void printAristas(){
        for(int i=0;i<aristas.getLength();i++)
            //graphNode nod=(graphNode)aristas.get(i);
            System.out.println(aristas.get(i));
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

    public Hashtable<Integer,graphNode> getHashtable_nodes() {
        return hashtable_nodes;
    }

    public void setHashtable_nodes(Hashtable<Integer,graphNode> hashtable_nodes) {
        this.hashtable_nodes = hashtable_nodes;
    }

    /**
     *
     * @return number of nodes.
     */
    public int getNum_nodes() {
        return graph_nodes.getLength();
    }

    /**
     * sets number of nodes.
     */
  /*  public void setNum_nodes() {
        this.num_nodes = graph_nodes.getLength();
    }*/

    /*public void createLinks(graphNode a,graphNode b,int direction,int weight){
        if (direction==0 || direction==1){// dirigido param: 1 if it is directed(directed from a to b).
            if(direction==0){            //0 if it has links to both sides.
                a.addLink(b,weight);//link from a to b
                b.addLink(a,weight);//link from b to a.
            }
            else if(direction==1)
                    a.addLink(b,weight);//link from a to b.
        }
        else{
            System.out.println("error");
        }
    }*/

    public void createConnection(graphNode a,graphNode b,int direction,int weight){//creates connection between two nodes forming the adjacency list.
        if (direction==0 || direction==1){// direction param: 1 if it is directed(directed from a to b).
            if(direction==0){            //0 if it has links to both sides.(a to b, b to a)
                ((graphNode)graph_nodes.get(searchNodePosition(a))).getNode_graphs().append(b);
                ((graphNode)graph_nodes.get(searchNodePosition(b))).getNode_graphs().append(a);
            }
            else if(direction==1)
                    ((graphNode)graph_nodes.get(searchNodePosition(a))).getNode_graphs().append(b);
        }
        else{
            System.out.println("error");
        }
    }

    /**
     * creates the links that are between the nodes of all the graph.
     * @param weight
     */
    public void createLinks(int weight){//the adjacency list must be created at this point, then the links are created.
        for(int j=0;j<graph_nodes.getLength();j++){
            //List gr_node=(List)graph_nodes.get(j);
            int lenght=((graphNode)(graph_nodes.get(j))).getNode_graphs().getLength();
            for(int i=0;i<lenght;i++){
                ((graphNode)((graphNode) graph_nodes.get(j))).addLink((graphNode) (((graphNode) graph_nodes.get(j)).getNode_graphs().get(i)), weight);
            }
        }
    }

    /**
     *
     * @param pgraphNode
     * @return returns the node to search, if it is not in the list return null.
     */
    public graphNode searchNode(graphNode pgraphNode){
        for(int i=0;i< graph_nodes.getLength();i++){
            if(pgraphNode.equals( graph_nodes.get(i))){
                return (graphNode)graph_nodes.get(i);
            }

        }
        return null;
    }

    /**
     *
     * @param pgraphNode
     * @return position of the element.
     */
    public int searchNodePosition(graphNode pgraphNode){
        for(int i=0;i< graph_nodes.getLength();i++){
            if(pgraphNode.equals( graph_nodes.get(i))){
                return i;
            }

        }
       return -1;//node is not in the list.
    }


    /**
     * adds the node to the total of graph nodes of the graph.
     * @param nombre
     */
    public void addNode(graphNode nombre){
        graph_nodes.append(nombre);
        hashtable_nodes.put(nombre.getId(), new graphNode(nombre));
    }


   /* public void addLink(String nodoInicial,String nodoTerminal,int peso){
        Link nuevo=new Link(nodoInicial,nodoTerminal,peso);
        int i=searchIndex(nuevo.getWeight());

        if(i==-1)
            aristas.append(nuevo);
        else
            aristas.insertInPosition(i,nuevo);//*

        //hashtable_nodes.get(nodoInicial).addLink(nodoTerminal, peso);//duda *
        //hashtable_nodes.get(nodoTerminal).addLink(nodoInicial, peso);
    }*/

   /* public int searchIndex(int peso){
        for(int i=0;i<aristas.getLength();i++){
            if(peso<(int)aristas.get(i))
                return i;
        }
        return -1;
    }*/

}
