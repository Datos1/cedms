package ac.cr.tec.ce1103.cedms.graphClass;

/**
 * Created by stiven on 23/11/14.
 */
public class DijkstraNode {

    private GraphNode data;
    private DijkstraNode Antecesor;
    private int precio_recorrido;
    private int num_operaciones;
    private boolean nodo_marcado;

    public DijkstraNode(GraphNode nodo){
        this.data=nodo;
        this.Antecesor=null;
        this.precio_recorrido=0;
        this.num_operaciones=0;
        this.nodo_marcado=true;
    }


    public boolean isNodo_marcado() {
        return nodo_marcado;
    }

    public void setNodo_marcado(boolean nodo_marcado) {
        this.nodo_marcado = nodo_marcado;
    }

    public GraphNode getData() {
        return data;
    }

    public void setData(GraphNode data) {
        this.data = data;
    }

    public DijkstraNode getAntecesor() {
        return Antecesor;
    }

    public void setAntecesor(DijkstraNode antecesor) {
        Antecesor = antecesor;
    }

    public int getPrecio_recorrido() {
        return precio_recorrido;
    }

    public void setPrecio_recorrido(int precio_recorrido) {
        this.precio_recorrido = precio_recorrido;
    }

    public int getNum_operaciones() {
        return num_operaciones;
    }

    public void setNum_operaciones(int num_operaciones) {
        this.num_operaciones = num_operaciones;
    }


}
