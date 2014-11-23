package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.dataStructures.List;

import java.util.Hashtable;

/**
 * class for the graph
 */
public class Graph<T> {
    private int num_nodes;//number of the graph nodes
    private int num_aristas;//number of links of the graph
    private List graph_nodes;//graph nodes, contains base station,hub or client.
    private Hashtable hashtable_nodes = new Hashtable<Integer, graphNode>();//hashtable for nodes.
    private List<Link> aristas;//number of arches of the graph

    public Graph(){
        num_nodes=0;
        num_aristas=0;
        graph_nodes = new List<graphNode>();
        aristas = new List<Link>();
    }
    /**
     * @return list of arches of the graph
     */
    public List getAristas() {
        return aristas;
    }

    /**
     * sets arches
     *
     * @param aristas
     */
    public void setAristas(List aristas) {
        this.aristas = aristas;
    }

    /**
     * @return all links that have each node of the graph(could be repeated!)
     */
    public List<Link> getAllLinks() {//gets total links of the graphs.

        for (int i = 0; i < graph_nodes.getLength(); i++)
            for (int j = 0; j < ((graphNode) graph_nodes.get(i)).getLinks().getLength();j++)
                aristas.append(((Link) ((graphNode) graph_nodes.get(i)).getLinks().get(j)));//adds all existing links of each node to the list.

        return aristas;
    }

    /**
     * @param plink_list
     * @return list without the repeated links.
     */
    public List getArches(List<Link> plink_list) {//removes the repeated links
        for (int i = 0; i < plink_list.getLength(); i++) {
            for (int j = 0; j < plink_list.getLength(); j++) { //if it has the same initial and same terminal
                graphNode lnk1 = (graphNode) ((plink_list.get(i)).getInitial());
                graphNode lnk2 = (graphNode) ((plink_list.get(j)).getInitial());
                graphNode lnk3 = (graphNode) ((plink_list.get(j)).getTerminal());
                graphNode lnk4 = (graphNode) ((plink_list.get(i)).getTerminal());

                if (lnk1.equals(lnk2) && lnk4.equals(lnk3))
                    plink_list.remove(i);//removes the repeated link.
                else
                    continue;
            }
        }
        return plink_list;
    }

    /**
     * @return list of all the nodes of the graph.
     */
    public List getGraph_nodes() {
        return graph_nodes;
    }

    /**
     * sets nodes of the graph
     *
     * @param graph_nodes
     */
    public void setGraph_nodes(List graph_nodes) {
        this.graph_nodes = graph_nodes;
    }

    /**
     * @return number of arches of the graph
     */
    public int getNum_aristas() {
        return num_aristas;
    }

    /**
     * sets number of arches
     *
     * @param num_aristas
     */
    public void setNum_aristas(int num_aristas) {
        this.num_aristas = num_aristas;
    }

    /**
     * @return hashtable of nodes
     */
    public Hashtable<Integer, graphNode> getHashtable_nodes() {
        return hashtable_nodes;
    }

    /**
     * sets hashtable
     *
     * @param hashtable_nodes
     */
    public void setHashtable_nodes(Hashtable<Integer, graphNode> hashtable_nodes) {
        this.hashtable_nodes = hashtable_nodes;
    }

    /**
     * @return number of nodes.
     */
    public int getNum_nodes() {
        return graph_nodes.getLength();
    }

    /**
     * sets number of nodes.
     */
    public void setNum_nodes() {
        this.num_nodes = graph_nodes.getLength();
    }


    /**the nodes of the graph must be created.
     * creates the adjacency list to identify the connections.(adds the link after making the connection between nodes).
     * @param a
     * @param b
     * @param direction
     * @param weight
     */
    public void adjacencyListConnections(graphNode a, graphNode b, int direction,int weight) {//creates connection between two nodes forming the adjacency list.(lists inside lists)

            if(existNode(a) && existNode(b)){//if a and b exists in the list of graph nodes.
                if (direction == 0 || direction == 1) {// direction param: 1 if it is directed(directed from a to b).
                    if (direction == 0) {            //0 if it has links to both sides.(a to b, b to a)
                        //creates bidirectional link
                        //to first direction.
                        graphNode gr_node = (graphNode) graph_nodes.get(searchNodePosition(a));
                        List<graphNode> grphn = gr_node.getNode_graphs();
                        grphn.append(b);
                        gr_node.setNode_graphs(grphn);//adds the node to the adjacency list
                        ((graphNode) (gr_node)).addLink(b, weight);//makes the link between nodes
                        this.num_aristas+=1;

                        //to the other direction.
                        graphNode gr_node2 = (graphNode) graph_nodes.get(searchNodePosition(b));
                        List<graphNode> grphn2 = gr_node2.getNode_graphs();
                        grphn2.append(a);
                        gr_node2.setNode_graphs(grphn2);//adds the node to the adjacency list
                        ((graphNode) (gr_node2)).addLink(a, weight);//makes the link between nodes
                        this.num_aristas+=1;

                    }
                    else if (direction == 1) {//creates one direction link
                        //to first direction.
                        graphNode gr_node=(graphNode) graph_nodes.get(searchNodePosition(a));
                        gr_node.getNode_graphs().append(b);
                        gr_node.setNode_graphs(gr_node.getNode_graphs());
                        ((graphNode)(gr_node)).addLink(b,weight);
                        this.num_aristas+=1;

                    }
                }
            }
        else{
                System.out.println("graphnode a or graphnode are not in the list");
            }

    }

    /**
     * creates the links that are between the nodes of all the graph.
     */
    public void createLinks() {//the adjacency list must be created at this point, then the links are created based on the adjacency list.
        for (int j = 0; j < graph_nodes.getLength(); j++) {
            //List gr_node=(List)graph_nodes.get(j);
            int lenght = ((graphNode) (graph_nodes.get(j))).getNode_graphs().getLength();
            for (int i = 0; i < lenght; i++) {
                ((graphNode) graph_nodes.get(j)).addLink((graphNode) (((graphNode) graph_nodes.get(j)).getNode_graphs().get(i)), 12);//12= peso o precio que se agarra del xml.
                this.num_aristas+=1;
            }
        }

    }

    /**
     *
     * @param pgraphNode
     * @return true if node is in the list.
     */
    public boolean existNode(graphNode pgraphNode) {
        for (int i = 0; i < graph_nodes.getLength(); i++) {
            if (pgraphNode.equals(graph_nodes.get(i))) {//if param is equal to node in list.
                return true;
            }

        }
        return false;
    }
    /**
     * @param pgraphNode
     * @return returns the node to search, if it is not in the list return null.
     */
    public graphNode searchNode(graphNode pgraphNode) {//returns the specified node, if not is null.
        for (int i = 0; i < graph_nodes.getLength(); i++) {
            if (pgraphNode.equals(graph_nodes.get(i))) {//if param is equal to node in list.
                return (graphNode) graph_nodes.get(i);
            }

        }
        return null;
    }

    /**
     * @param pgraphNode
     * @return position of the element.
     */
    public int searchNodePosition(graphNode pgraphNode) {//return the actual position of the node in the parameter
        for (int i = 0; i < graph_nodes.getLength(); i++) {
            if (pgraphNode.equals(graph_nodes.get(i))) {//if param is equal to node in list.
                return i;
            }

        }
        return -1;//node is not in the list.
    }


    /**
     * adds the node to the total of graph nodes of the graph.
     *
     * @param nombre
     */
    public void addNode(graphNode nombre) {
        graph_nodes.append(nombre);//adds the param node to the total of nodes of the graph
       // hashtable_nodes.put(nombre.getId(), new graphNode(nombre));//puts hashtable ??
        num_nodes+=1;
    }


    /**
     * prints the links of a node.
     *
     */
    public void printNodeLinks(graphNode  node){//prints all links from a node ignoring if the a node doesnt have links and its list is null.
        for(int i=0;i<node.getLinks().getLength();i++) {
            if (node.getLinks().get(i) == null) {//if the node does not have links.
                continue;
            }
            else {
                System.out.println(((graphNode) node.getLinks().get(i)).getNombre_elemento());
            }
        }
    }

}
