package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.core.UpdateId;
import org.junit.Test;

public class XmlToolkitTest {

    @Test
    public void testReadMessage() throws Exception {
        System.out.println("");
    }

    @Test
    public void testNewConnection() throws Exception {
        System.out.println(XmlToolkit.newConnection(1, 1, 1, new UpdateId(12, 12)));
    }
}