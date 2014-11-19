package ac.cr.tec.ce1103.cedms.graphClass;


public class Link {

    private int initial;//source(id) this id is the attribute from the class Node.
    private int terminal;//destiny(id)this id is the attribute from the class Node.
    private int weight;//cost of link

    public Link(int initial,int  terminal,int weight){
        this.initial=initial;
        this.terminal=terminal;
        this.weight=weight;
    }
   /* public Link(int terminal){//
        this.initial=initial;
        this.terminal=terminal;
        this.weight=-1; //doesnt have weight, weight by default.
    }*/


    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



}





