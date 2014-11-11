package Structure;

/**
 * Created by Stiven on 11/10/2014.
 */
public class Node {
    private Object data;
    private Node nextNode;

    Node(Object object) {
        this.data = object;
        this.nextNode = null;
    }

    Node(Object object, Node Node) {
        this.data = object;
        this.nextNode = Node;
    }

    public void setdata(Object data) {
        this.data = data;
    }

    public void setnextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Object getdata() {
        return data;
    }

    public Node getnextNode() {
        return nextNode;
    }
}
