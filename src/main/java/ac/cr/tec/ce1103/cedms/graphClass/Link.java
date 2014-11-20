package ac.cr.tec.ce1103.cedms.graphClass;

/**
 * class that connects a graph node to another
 */
public class Link {

    private graphNode initial;//source(id) this id is the attribute from the class graphNode.
    private graphNode terminal;//destiny(id)this id is the attribute from the class graphNode.
    private int weight;//cost of link

    /**
     * constructor
     *
     * @param initial
     * @param terminal
     * @param weight
     */
    public Link(graphNode initial, graphNode terminal, int weight) {
        this.initial = initial;//source
        this.terminal = terminal;//target or destiny
        this.weight = weight;//price of the link
    }
    /*public Link(graphNode initial,graphNode terminal){//
        this.initial=initial;//source
        this.terminal=terminal;//target or destiny
        this.weight=-1; //doesnt have weight, weight by default. ??
    }*/

    /**
     * @return the initial or source graph node
     */
    public graphNode getInitial() {
        return initial;
    }

    /**
     * sets initial or source node
     *
     * @param initial
     */
    public void setInitial(graphNode initial) {
        this.initial = initial;//assigns initial as the param
    }

    /**
     * @return the terminal or destiny graph node
     */
    public graphNode getTerminal() {
        return terminal;
    }

    /**
     * sets the destiny node
     *
     * @param terminal
     */
    public void setTerminal(graphNode terminal) {
        this.terminal = terminal;//assigns terminal as the param
    }

    /**
     * @return the price or cost of the link
     */
    public int getWeight() {
        return weight;
    }

    /**
     * sets the price or cost of the link
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;//assigns the price or cost as the param
    }


}





