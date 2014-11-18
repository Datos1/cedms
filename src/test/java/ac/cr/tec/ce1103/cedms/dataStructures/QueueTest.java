package ac.cr.tec.ce1103.cedms.dataStructures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {
    Queue<Integer> test = new Queue<Integer>();

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        System.out.println(test);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(test);
    }


    @Test
    public void testDeQueue() throws Exception {
        for (int i = 0; i < 3; i++)
            System.out.println(test.deQueue());
    }
}