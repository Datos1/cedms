package ac.cr.tec.ce1103.cedms.dataStructures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue<T> extends List<T> {

    public static final int HEAD = 0;

    public void add(T data) {
        super.append(data);
    }

    /**
     * saca un elelemento de la cola
     * @return
     */
    public T deQueue() {

        T data = get(HEAD);
        remove(HEAD);
        return data;
    }

    public boolean isEmpty() {
        return (this.getLength() == 0);
    }


    public void lock() {
        Lock lock = null;
        lock.lock();

    }
    public void unlock() {
        Lock lock = null;
        lock.unlock();
    }

}
