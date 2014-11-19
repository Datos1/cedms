package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import javax.swing.*;

/**
 * Node class for the graphClass.
 */
public class Node<T> {//Node class

    private T element;//the element is of type base, client or hub.
    private List<Link> links;//all connections that have each elements
    private int actual_links;//number of links per node.
    private int id;//id of each node.
    private String nombre_elemento;//base,client or hub in string.


    public Node(T name)//name is hub,client or base station.
    {
        this.element = name;
        this.actual_links = -1;
        this.links= new List<Link>();
        this.id=1;

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


    public void addLink(int initial,int destiny, int weight){
        if (actual_links == -1){
            links.append(new Link(this.id,destiny,weight));
            actual_links++;
        }
        else
        {
            boolean pos= existLink(destiny);
            if (pos == false){
                links.append(new Link(this.id,destiny,weight));
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


    public boolean existLink(int link){//checks if there is a link from the parameter in the links of this node.
        for(int i=0;i<links.getLength();i++){
            if(links.get(i).getTerminal()==link)
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
