package ac.cr.tec.ce1103.cedms.graphClass;

import ac.cr.tec.ce1103.cedms.dataStructures.List;

/**
 * dijkstra algorithm class
 */
public class Dijkstra {
    private Graph grafo;
    private List<GraphNode> camino_mas_corto;//resultado del algoritmo.
    private List<Link> links_procesados;//se va guardando las conexiones procesadas.
    private List<DijkstraNode> dijkstra_nodes_procesados;//actuales nodos procesados.
    private DijkstraNode actual;//nodo en cuestion para el algoritmo.
    private List<DijkstraNode> dijkstra_nodes;//total nodes of the graph as Dijsktra nodes.


    public Dijkstra(Graph grafo,DijkstraNode inicial){
        this.grafo=grafo;
        this.camino_mas_corto=new List<GraphNode>();
        this.links_procesados= new List<Link>();
        this.actual=inicial;
        this.dijkstra_nodes=new List<DijkstraNode>();
    }

    public List<DijkstraNode> getDijkstra_nodes_procesados() {
        return dijkstra_nodes_procesados;
    }

    public void setDijkstra_nodes_procesados(List<DijkstraNode> dijkstra_nodes_procesados) {
        this.dijkstra_nodes_procesados = dijkstra_nodes_procesados;
    }

    public List<DijkstraNode> getDijkstra_nodes() {
        return dijkstra_nodes;
    }

    public void setDijkstra_nodes(List<DijkstraNode> dijkstra_nodes) {
        this.dijkstra_nodes = dijkstra_nodes;
    }

    public void fillGraphNodeAsDijsktraNode(){//creates dijsktra nodes with the graph nodes.
        for(int i=0;i<grafo.getGraph_nodes().getLength();i++){
            dijkstra_nodes.append((new DijkstraNode(((GraphNode) grafo.getGraph_nodes().get(i)))));
        }
    }

    public DijkstraNode getActual() {
        return actual;
    }

    public void setActual(DijkstraNode actual) {
        this.actual = actual;
    }

    public List<Link> getLinks_procesados() {
        return links_procesados;
    }

    public void setLinks_procesados(List<Link> links_procesados) {
        this.links_procesados = links_procesados;
    }

    public List<GraphNode> getCamino_mas_corto() {
        return camino_mas_corto;
    }

    public void setCamino_mas_corto(List<GraphNode> camino_mas_corto) {
        this.camino_mas_corto = camino_mas_corto;
    }

    public Graph getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph grafo) {
        this.grafo = grafo;
    }

    public void Algorithm(DijkstraNode nodo){//begins algorithm from the node of the param.
        int cont=0;
        //fillGraphNodeAsDijsktraNode();//fills the nodes of the graph as the dijsktra nodes.
        if(existDijsktraNode(nodo)){//if nodo is in the graph.
            Link short_link=shorterConnection(nodo);//it is the adjacence link with the less weight.
            dijkstra_nodes_procesados.append(nodo);
            links_procesados.append(shorterConnection(nodo));//while.
            actual=new DijkstraNode(links_procesados.get(cont).getTerminal());//siguiente nodo en el recorrido.;
            actual.setAntecesor(nodo);//sets previous node.
            actual.setPrecio_recorrido(links_procesados.get(cont).getWeight());//sets all weight carried from the beginning to thhis node.
            cont+=1;
            Algorithm(actual);
        }
        else{
            System.out.println("node not found");
        }
    }

    public void init(){
        fillGraphNodeAsDijsktraNode();//fills the nodes of the graph as the dijsktra nodes.
       // while (dijkstra_nodes_procesados )
    }
    public List<Link> getNodeLinks(DijkstraNode node){//connections with adjacent nodes of the node in param.
        return node.getData().getLinks();
    }

    public Link shorterConnection(DijkstraNode node){//returns Link with less price between two nodes.Validates by the adjacence nodes.
        //checks th links of the param node and returns the weightless link of the node.
        List<Link> links_of_node=node.getData().getLinks();
        Link link=null;
        int less_weight;
        for(int i=0;i<links_of_node.getLength()+1;i++){
            if(links_of_node.get(i).getWeight()<links_of_node.get(i+1).getWeight()){
                less_weight=links_of_node.get(i).getWeight();
                link=links_of_node.get(i);
            }
            else{
                less_weight=links_of_node.get(i+1).getWeight();
                link=links_of_node.get(i);
            }
        }
        return link;
    }

    public boolean existDijsktraNode(DijkstraNode node){//checks in the list of dijsktra node if the param is in it.
        for(int i=0;i<grafo.getAllLinks().getLength();i++){
            if(dijkstra_nodes.get(i).equals(node)){
                return true;
            }
        }
        return  false;
    }
}
