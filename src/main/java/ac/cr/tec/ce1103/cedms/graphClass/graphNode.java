package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import javax.swing.*;

/**
 * graphNode class for the graphClass.
 */
public class graphNode<T> {//graphNode class

    private T element;//the element is of type base, client or hub.
    private List<graphNode> node_graphs;//list of graph nodes(to make the Adjacency list)
    private List<Link> links;//all connections that has this node.
    private int actual_links;//number of links per node.
    private int id;//id of each node.
    private int num_node_graphs;
    private String nombre_elemento;//base,client or hub in string.
    //agregar una lista de nodos para hacer la lista de adyacencia.


    public graphNode(T name_type){//name is Object of type hub,client or base station.
        this.element = name_type;
        this.actual_links = 0;
        this.links= new List<Link>();
        this.id=1;


    }

    public int getNum_node_graphs() {
        return num_node_graphs;
    }

    public void setNum_node_graphs() {
        this.num_node_graphs=this.node_graphs.getLength();
    }

    public List<graphNode> getNode_graphs() {
        return node_graphs;
    }

    public void setNode_graphs(List<graphNode> node_graphs) {
        this.node_graphs = node_graphs;
    }

    public String getNombre_elemento() {
        return nombre_elemento;
    }

    public void setNombre_elemento(String nombre_elemento) {
        this.nombre_elemento = nombre_elemento;
    }


    public int getId() {
        return id;
    }

    public void setId() {//se suma uno al id cada vez que se crea un nuevo nodo,para que se tenga un unico id por nodo.
        this.id = this.id+1;
    }


    public int getActual_links() {
        return links.getLength();
    }


    public void setActual_links(int actual_links) {
        this.actual_links = actual_links;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public T getName() {
        return element;
    }

    public void setName(T name) {
        this.element = name;
    }


    public void addLink(graphNode destiny, int weight){
        if (actual_links == -1){
            links.append(new Link(this,destiny,weight));
            actual_links++;
        }
        else
        {
            boolean pos= existLink(destiny);
            if (pos == false){
                links.append(new Link(this,destiny,weight));
            }
        }
    }

   public boolean deleteLink(int posicion){
        if (posicion >= 0 && posicion <= links.getLength()){
            links.remove(posicion);
            actual_links--;
            return true;
        }
        else JOptionPane.showMessageDialog(null, "No hay enlace en la posicion " + posicion);

        return false;
    }


    public boolean existLink(graphNode link){//checks if there is a link from the parameter in the links of this node.
        for(int i=0;i<links.getLength();i++){
            if(links.get(i).getTerminal().equals(link))
                return true;
        }
        return false;
    }

    public String getElementName(){//gets the string name of the objects of type base station, hub or client.
        String name_element="";
        if(this.element instanceof BaseStation){
            this.nombre_elemento="Base Station";
            name_element="Base Station";
        }
        else if(this.element instanceof Client){
            this.nombre_elemento="Client";
            name_element="Client";
        }
        else if(this.element instanceof Hub){
            this.nombre_elemento="Hub";
            name_element="Hub";
        }

        return name_element;
    }


}
