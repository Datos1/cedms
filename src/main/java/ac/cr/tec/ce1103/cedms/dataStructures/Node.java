package ac.cr.tec.ce1103.cedms.dataStructures;

public class Node<T> {

    private T data = null;
    private Node<T> next = null;//next item

    /**
     * Constructor sets data
     *
     * @param pData
     */
    public Node(T pData) {
        data = pData;
    }

    /**
     * @return current data
     */
    public T getData() {
        return data;
    }

    /**
     * Chnages the data
     *
     * @param pData new data
     */
    public void setData(T pData) {
        this.data = pData;
    }

    /**
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * adds next node
     *
     * @param pNext Node
     */
    public void setNext(Node<T> pNext) {
        next = pNext;
    }

    public String toString() {
        return data + ", " + next;
    }

}
