package Structure;

/**
 * Created by Stiven on 11/10/2014.
 */
public class Queue {
    /**
     * Queue Attribute
     */
    private SimpleList listQueue;

    /**
     * constructor for the Queue
     */
    public Queue() {
        listQueue = new SimpleList();//creates new Queue list.
    }

    /**
     * inserts an element to the Queue
     * @param object
     */
    public void enqueue(Object object) {
        listQueue.insertAtLast(object);//calls insertion at last operation from de Queue list.
    }

    /**
     * deletes an element from the Queue
     * @return
     * @throws Exception
     */
    public Object dequeue() throws Exception {
        return listQueue.deleteFront();//deletes element at front of list.
    }

    /**
     *
     * @return true if Queue is empty or false if not
     */
    public boolean isEmpty() {
        return listQueue.isEmpty();//calls Queue list operation.
    }

    /**
     * prints the Queue.
     */
    public void print() {
        listQueue.print();//calls printing.
    }
}
