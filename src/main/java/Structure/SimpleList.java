package Structure;

/**
 * Created by Stiven on 11/10/2014.
 */
public class SimpleList {
    private Node firstNode;//initializes attribute for first Node.
    private Node lastNode;//initializes attribute for last Node.

    /**
     * constructor
     */
    public SimpleList() {
        firstNode = lastNode = null;//initializes in null as the list is empty.
    }

    /**
     *
     * @return true if list is empty.
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * inserts element to the front
     * @param element
     */
    public void insertAtFront(Object element) {
        if(isEmpty()) {
            firstNode = lastNode = new Node(element);//creates first element on the list.
        }
        else {
            Node Node = new Node(element, firstNode);//defines first element of list as the new node.
            firstNode = Node;
        }
    }

    /**
     * inserts element at last of the list
     * @param element
     */
    public void insertAtLast(Object element) {
        if (isEmpty()) {
            firstNode = lastNode = new Node(element);//creates first element on the list.
        }
        else {;//defines last element of list as the new node.
            Node Node = new Node(element);
            lastNode.setnextNode(Node);
            lastNode = lastNode.getnextNode();
        }
    }

    /**
     *
     * @return deleted element.
     * @throws Exception
     */
    public Object deleteFront() throws Exception {
        if (isEmpty()) {
            throw new Exception("No elements on list");//empty list.
        }
        else {
            Object element = firstNode.getdata();
            if (firstNode == lastNode) {//compares elements to know which element to delete
                firstNode = lastNode = null;
            }
            else {
                firstNode = firstNode.getnextNode();//browse the list.
            }
            return element;
        }
    }

    /**
     *
     * @return deleted element
     * @throws Exception
     */
    public Object deleteLast() throws Exception {
        if (isEmpty()) {
            throw new Exception("No elements on list");//empty list.
        }
        else {
            Object element = lastNode.getdata();
            if (firstNode == lastNode) {//compares elements to know which element to delete
                firstNode = lastNode = null;//erase element.
            }
            else {
                Node actual = firstNode, temporal;
                while (actual.getnextNode() != lastNode) {//browse the element to delete.
                    temporal = actual.getnextNode();
                    actual.setnextNode(temporal);
                    //actual.setnextNode(actual.getnextNode());
                }
                lastNode = actual;
                actual.setnextNode(null);//
            }
            return element;//returns the deleted element.
        }
    }

    /**
     * prints the actual list.
     */
    public void print() {
        if (isEmpty()) {//is empty
            System.out.println("List is empty");//prints empty list
        }
        else {
            Node actual = firstNode;
            while (actual != null) {// browses all elements of the list.
                System.out.println(actual.getdata());//prints the element.
                actual = actual.getnextNode();//sets browse variable as the next element
            }
        }
        System.out.println("");
    }
}
