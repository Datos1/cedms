package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.dataStructures.List;
import ac.cr.tec.ce1103.cedms.dataStructures.Node;

import java.util.Hashtable;

/**
 * class for the graph
 */
public class Graph {
    private int num_nodes;//number of the graph nodes
    private  int num_aristas;//number of links of the graph
    private List graph_nodes =new List<graphNode>();//graph nodes, contains base station,hub or client.
    private Hashtable hashtable_nodes =new Hashtable<Integer,graphNode>();//hashtable for nodes.
    private List aristas=new List<Link>();//number of arches of the graph

    /**
     *
     * @return list of arches of the graph
     */
    public List getAristas() {
        return aristas;
    }

    /**
     * sets arches
     * @param aristas
     */
    public void setAristas(List aristas) {
        this.aristas = aristas;
    }

    /**
     *
     * @return all links that have each node of the graph(could be repeated!)
     */
    public List<Link> getAllLinks(){
        List all_connections=new List<Link>();
        for(int i=0;i<graph_nodes.getLength();i++){
            all_connections.append(((graphNode)graph_nodes.get(i)).getLinks());//adds all existing links of each node to the list.
        }
        return all_connections;
    }

    /**
     *
     * @param plink_list
     * @return list without the repeated links.
     */
    public List getArches(List<Link> plink_list){//removes the repeated links
        for(int i=0;i<plink_list.getLength();i++){
            for(int j=0;j<plink_list.getLength();j++){ //if it has the same initial and same terminal
                graphNode lnk1=(graphNode)((plink_list.get(i)).getInitial());
                graphNode lnk2=(graphNode)((plink_list.get(j)).getInitial());
                graphNode lnk3=(graphNode)((plink_list.get(j)).getTerminal());
                graphNode lnk4=(graphNode)((plink_list.get(i)).getTerminal());

                if(lnk1.equals(lnk2) && lnk4.equals(lnk3))
                    plink_list.remove(i);//removes the repeated link.
                else
                    continue;
            }
        }return plink_list;
    }

    /**
     *
     * @return list of all the nodes of the graph.
     */
    public List getGraph_nodes() {
        return graph_nodes;
    }

    /**
     * sets nodes of the graph
     * @param graph_nodes
     */
    public void setGraph_nodes(List graph_nodes) {
        this.graph_nodes = graph_nodes;
    }

    /**
     *
     * @return number of arches of the graph
     */
    public int getNum_aristas() {
        return num_aristas;
    }

    /**
     * sets number of arches
     * @param num_aristas
     */
    public void setNum_aristas(int num_aristas) {
        this.num_aristas = num_aristas;
    }

    /**
     *
     * @return hashtable of nodes
     */
    public Hashtable<Integer,graphNode> getHashtable_nodes() {
        return hashtable_nodes;
    }

    /**
     * sets hashtable
     * @param hashtable_nodes
     */
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

    /**
     * creates the adjacency list to identify the connections
     * @param a
     * @param b
     * @param direction
     */
    public void adjacencyListConnections(graphNode a,graphNode b,int direction){//creates connection between two nodes forming the adjacency list.(lists inside lists)
        if (direction==0 || direction==1){// direction param: 1 if it is directed(directed from a to b).
            if(direction==0){            //0 if it has links to both sides.(a to b, b to a)
                //creates bidirectional link
                ((graphNode)graph_nodes.get(searchNodePosition(a))).getNode_graphs().append(b);//adds the node to the list
                ((graphNode)graph_nodes.get(searchNodePosition(b))).getNode_graphs().append(a);
            }
            else if(direction==1)//creates one direction link
                    ((graphNode)graph_nodes.get(searchNodePosition(a))).getNode_graphs().append(b);
        }
        else{
            System.out.println("error");
        }
    }

    /**
     * creates the links that are between the nodes of all the graph.
     *
     */
    public void createLinks(){//the adjacency list must be created at this point, then the links are created based on the adjacency list.
        for(int j=0;j<graph_nodes.getLength();j++){
            //List gr_node=(List)graph_nodes.get(j);
            int lenght=((graphNode)(graph_nodes.get(j))).getNode_graphs().getLength();
            for(int i=0;i<lenght;i++){
                ((graphNode) graph_nodes.get(j)).addLink((graphNode) (((graphNode) graph_nodes.get(j)).getNode_graphs().get(i)),12);//12= peso o precio que se agarra del xml.
            }
        }
    }

    /**
     *
     * @param pgraphNode
     * @return returns the node to search, if it is not in the list return null.
     */
    public graphNode searchNode(graphNode pgraphNode){//returns the specified node, if not is null.
        for(int i=0;i< graph_nodes.getLength();i++){
            if(pgraphNode.equals( graph_nodes.get(i))){//if param is equal to node in list.
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
    public int searchNodePosition(graphNode pgraphNode){//return the actual position of the node in the parameter
        for(int i=0;i< graph_nodes.getLength();i++){
            if(pgraphNode.equals( graph_nodes.get(i))){//if param is equal to node in list.
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
        graph_nodes.append(nombre);//adds the param node to the total of nodes of the graph
        hashtable_nodes.put(nombre.getId(), new graphNode(nombre));//puts hashtable ??
    }


  /*public void addLink(String nodoInicial,String nodoTerminal,int peso){
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
