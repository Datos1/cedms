package ac.cr.tec.ce1103.cedms.core;

/**
 * Created by pablo on 10/11/14.
 */
public class Hub extends Core {
    public Hub(int pId, int pPort) {
        super(pId, pPort);
    }

    @Override
    public void recibirConnection(int source, int target, int id, int adyacente, int precio, int updateId) {

    }

    /**
     * It diffuses a message through all the system
     *
     * @param msg
     */
    @Override
    public void difusion(String msg) {

    }

    @Override
    protected void initScanner() {

    }

    @Override
    protected void createConnection() {

    }
}
