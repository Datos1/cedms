package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import javax.swing.*;

import static ac.cr.tec.ce1103.cedms.App.INSTANCE_COUNTER;

/**
 * graphNode class for the graphClass.
 */
public class GraphNode<T> {//graphNode class

    private T element;//the element is of type base, client or hub.
    private List<GraphNode<T>> node_graphs;//list of graph nodes(to make the Adjacency list)
    private List<Link<T>> links;//all connections that has this node.
    private int actual_links;//number of links per node.
    private int id;//id of each node.
    private int num_node_graphs;//number of graphs of this graphNode.
    private String nombre_elemento;//base,client or hub in string.
    //agregar una lista de nodos para hacer la lista de adyacencia.

    /**
     * constructor
     *
     * @param name_type
     */
    public GraphNode(T name_type) {//name is Object of type hub,client or base station.
        this.element = name_type;        //initializing the attribute variables.
        this.actual_links = 0;
        this.links = new List<Link<T>>();
        this.node_graphs = new List<GraphNode<T>>();
        this.id = 1;
    }

    /**
     * @return number of node graphs.
     */
    public int getNum_node_graphs() {
        return num_node_graphs;
    }

    /**
     * sets node graphs
     */
    public void setNum_node_graphs() {
        this.num_node_graphs = this.node_graphs.getLength();//set number of graphs this graph class has. lenght of list.
    }

    /**
     * @return the list of graphs
     */
    public List<GraphNode<T>> getNode_graphs() {
        return node_graphs;
    }

    /**
     * sets list of nodes.
     *
     * @param node_graphs
     */
    public void setNode_graphs(List<GraphNode<T>> node_graphs) {
        this.node_graphs = node_graphs;
    }

    /**
     * @return String name for the type of object
     */
    public String getNombre_elemento() {
        this.getElementName();//calls method to identify string name of the object
        return this.nombre_elemento;
    }

    /**
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the unique id for each instance of this class.
     */
    public void setId() {//se suma uno al INSTANCE_COUNTER cada vez que se crea un nuevo nodo,para que se tenga un unico id por nodo.
        this.id = INSTANCE_COUNTER;

    }

    /**
     * @return int actual links.
     */
    public int getNumActual_links() {
        return links.getLength();
    }

    /**
     * @param actual_links
     */
    public void setNumActual_links(int actual_links) {
        this.actual_links = actual_links;
    }

    /**
     * @return the list of links
     */
    public List<Link<T>> getLinks() {
        return links;
    }

    /**
     * sets the links
     *
     * @param links
     */
    public void setLinks(List<Link<T>> links) {
        this.links = links;
    }

    /**
     * @return gets the type of object
     */
    public T getElement() {
        return element;
    }

    /**
     * sets the type of element
     *
     * @param name
     */
    public void setElement(T name) {
        this.element = name;
    }

    /**
     * adds a link for this graph node
     *
     * @param destiny
     * @param weight
     */
    public void addLink(GraphNode destiny, int weight) { //adds the link with the param destiny and its weight or price.
        if (actual_links == -1) {//if there are not links.
            links.append(new Link(this, destiny, weight));//creates new link and adds it to the list of links of this node.
            actual_links++;
        } else {
            boolean pos = existLink(destiny);//if param exists in the list.
            if (pos == false) {
                links.append(new Link(this, destiny, weight));//creates and adds link to list.
                actual_links++;
            }
        }

    }

    /**
     * adds link
     * @param destiny
     */
    /*public void addLink(graphNode destiny){ //adds the link with the param destiny
        if (actual_links == -1){//if there are not links.
            links.append(new Link(this,destiny));
            actual_links++;
        }
        else{
            boolean pos= existLink(destiny);//if param exists in the list.
            if (pos == false){
                links.append(new Link(this,destiny));//creates and adds link to list.
            }
        }
    }*/

    /**
     * @param posicion
     * @return true if the link was deleted.
     */
    public boolean deleteLink(int posicion) {//deletes the link on the position of the parameter
        if (posicion >= 0 && posicion <= links.getLength()) {//if position is in a valid range.
            links.remove(posicion);//deletes from links list the element of that position
            actual_links--;//reduces variable
            return true;
        } else JOptionPane.showMessageDialog(null, "No link in position: " + posicion);//message no link found

        return false;
    }

    /**
     * @param link
     * @return true if the link exists
     */
    public boolean existLink(GraphNode link) {//checks if there is a link from the parameter in the links of this node.
        for (int i = 0; i < links.getLength(); i++) {
            if (links.get(i).getTerminal().equals(link))//compares with the terminal of the link
                return true;
        }
        return false;
    }

    /**
     * @return string name of the element, hub, base station or client.
     */
    private void getElementName() {//gets the string name of the objects of type base station, hub or client.
        if (this.element instanceof BaseStation) {//if it is instance of Base Station
            this.nombre_elemento = "Base Station";

        } else if (this.element instanceof Client) {//if it is instance of Client
            this.nombre_elemento = "Client";

        } else if (this.element instanceof Hub) {//if it is instance of Hub
            this.nombre_elemento = "Hub";

        }


    }

    /**
     *
     * @return true if list of links of the node are different from null.
     */
    public boolean hasLinks(){
        if(links==null)
            return false;
        else
            return true;
    }

}