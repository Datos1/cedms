package ac.cr.tec.ce1103.cedms.dataStructures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue<T> extends List<T> {

    public static final int HEAD = 0;
    private Lock lock = new ReentrantLock();

    public void add(T data) {
        lock.lock();
        super.append(data);
        lock.unlock();
    }

    public T deQueue() {
        lock.lock();
        T data = get(HEAD);
        remove(HEAD);
        lock.unlock();
        return data;
    }

    public boolean isEmpty() {
        return (this.getLength() == 0);
    }

}
