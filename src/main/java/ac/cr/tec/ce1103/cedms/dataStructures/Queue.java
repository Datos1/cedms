package ac.cr.tec.ce1103.cedms.dataStructures;

/**
 * Created by pablo on 10/11/14.
 */
public class Queue<T> extends List<T> {

    public static final int HEAD = 0;

    public void add(T data) {
        super.append(data);
    }

    public T deQueue() {

        T data = get(HEAD);
        remove(HEAD);
        return data;
    }
}
