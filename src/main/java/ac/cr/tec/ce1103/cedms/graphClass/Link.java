package ac.cr.tec.ce1103.cedms.graphClass;


public class Link {

    private graphNode initial;//source(id) this id is the attribute from the class graphNode.
    private graphNode terminal;//destiny(id)this id is the attribute from the class graphNode.
    private int weight;//cost of link

    public Link(graphNode initial,graphNode  terminal,int weight){
        this.initial=initial;
        this.terminal=terminal;
        this.weight=weight;
    }
    /*public Link(graphNode terminal){//
        this.initial=this;
        this.terminal=terminal;
        this.weight=-1; //doesnt have weight, weight by default.
    }*/


    public graphNode getInitial() {
        return initial;
    }

    public void setInitial(graphNode initial) {
        this.initial = initial;
    }

    public graphNode getTerminal() {
        return terminal;
    }

    public void setTerminal(graphNode terminal) {
        this.terminal = terminal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



}





