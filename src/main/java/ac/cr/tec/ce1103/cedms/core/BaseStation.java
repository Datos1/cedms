package ac.cr.tec.ce1103.cedms.core;

/**
 * Created by pablo on 10/11/14.
 */
public class BaseStation extends Core {


    public BaseStation(int pId, int pPort) {
        super(pId, pPort);
    }

    @Override
    public void recibirConnection(int source, int target, int id, int adyacente, int precio, int updateId) {

    }

    /**
     * It receives a message and
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
