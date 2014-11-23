package ac.cr.tec.ce1103.cedms.graphClass;

/**
 * Created by stiven on 23/11/14.
 */
public class DijkstraNode {

    private GraphNode data;//data
    private DijkstraNode Antecesor;//antecesor
    private int precio_recorrido;//total weight of one way
    private int num_operaciones;//number of cycles
    private boolean nodo_marcado;//marked node

    /**
     *
     * @param nodo
     */
    public DijkstraNode(GraphNode nodo){
        this.data=nodo;
        this.Antecesor=null;
        this.precio_recorrido=0;
        this.num_operaciones=0;
        this.nodo_marcado=true;
    }

    /**
     *
     * @return is the node marked
     */
    public boolean isNodo_marcado() {
        return nodo_marcado;
    }

    /**
     *
     * @param nodo_marcado
     */
    public void setNodo_marcado(boolean nodo_marcado) {
        this.nodo_marcado = nodo_marcado;
    }

    /**
     *
     * @return data
     */
    public GraphNode getData() {
        return data;
    }

    /**
     * sets data
     * @param data
     */
    public void setData(GraphNode data) {
        this.data = data;
    }

    /**
     *
     * @return antecesor
     */
    public DijkstraNode getAntecesor() {
        return Antecesor;
    }

    /**
     * set antecesor
     * @param antecesor
     */
    public void setAntecesor(DijkstraNode antecesor) {
        Antecesor = antecesor;
    }

    /**
     *
     * @return weight
     */
    public int getPrecio_recorrido() {
        return precio_recorrido;
    }

    /**
     * sets weight
     * @param precio_recorrido
     */
    public void setPrecio_recorrido(int precio_recorrido) {
        this.precio_recorrido = precio_recorrido;
    }

    /**
     *
     * @return number of cycles
     */
    public int getNum_operaciones() {
        return num_operaciones;
    }

    /**
     * set number of cycles
     * @param num_operaciones
     */
    public void setNum_operaciones(int num_operaciones) {
        this.num_operaciones = num_operaciones;
    }


}
