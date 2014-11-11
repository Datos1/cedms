package ac.cr.tec.ce1103.cedms.dataStructures;

/**
 * List generics
 *
 * @author pablo
 */
public class List<T> {
    protected Node<T> _head = null;
    protected Node<T> _tail = null;
    protected int length = 0;

    /**
     * Creates a list of a specified size
     */
    public List() {

    }

    /**
     * Create List with arguments
     *
     * @param args
     */
    public List(T... args) {
        for (T arg : args) append(arg);
    }

    public Node getHead() {
        return _head;
    }

    public Node getTail() {
        return _tail;
    }

    /**
     * Adds data to the front
     *
     * @param pData
     */
    public void enQueue(T pData) {
        Node<T> node = new Node<T>(pData);
        if (_head == null)
            _tail = node;

        else
            node.setNext(_head);
        _head = node;
        this.length++;
    }

    /**
     * Adds data to the end
     *
     * @param pData
     */
    public void append(T pData) {
        Node<T> node = new Node<T>(pData);
        if (_head == null)
            _head = node;

        else
            _tail.setNext(node);
        _tail = node;
        this.length++;

    }

    /**
     * Prints all the list
     */
    public void print() {
        Node<T> ele = _head;
        while (ele != null) {
            System.out.println(ele.getData());
            ele = ele.getNext();

        }
    }

    public T get(int x) {
        if (this.length != 0 && x < this.length) {
            Node<T> ele = _head;
            for (int i = 0; i < x; i++)
                ele = ele.getNext();
            return ele.getData();
        }
        return null;

    }

    /**
     * Changes data at position
     *
     * @param i     position
     * @param pData new data
     * @return
     */
    public boolean set(int i, T pData) {
        if (i >= this.length)
            return false;
        Node<T> ele = _head;
        for (int x = 0; x < i; x++)
            ele = ele.getNext();
        ele.setData(pData);

        return true;
    }

    /**
     * @return length of list
     */
    public int getLength() {
        return this.length;

    }

    @Override
    public String toString() {
        return "List [" + _head + ", lenght="
                + length + "]";
    }

    /**
     * Removes ele at pos i
     *
     * @param i position
     */
    public void remove(int i) {
        if (i < length) {
            Node<T> ele = _head;
            if (i == 0)
                _head = ele.getNext();
            else {
                for (int x = 0; x < i - 1; x++)
                    ele = ele.getNext();
                if (i == length - 1) {
                    ele.setNext(null);
                    _tail = ele;
                } else
                    ele.setNext(ele.getNext().getNext());
            }

            length--;
        }

    }

    /**
     * Returns the node
     *
     * @param x position
     * @return the node at pos x
     */
    public Node<T> getNode(int x) {
        if (this.length != 0 && x < this.length) {
            Node<T> ele = _head;
            for (int i = 0; i < x; i++)
                ele = ele.getNext();
            return ele;
        }
        return null;

    }


}
