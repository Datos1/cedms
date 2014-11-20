package ac.cr.tec.ce1103.cedms.serverClient;

import org.junit.Test;

public class Client_SocketTest {
    @Test
    public void testSend() throws Exception {
        Client_Socket client = new Client_Socket(null, "192.168.1.111", 5013);
        client.send("hello");

    }
}