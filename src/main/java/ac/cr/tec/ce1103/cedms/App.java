package ac.cr.tec.ce1103.cedms;


<<<<<<< HEAD
import ac.cr.tec.ce1103.cedms.core.*;


public class App {

    public static final String USAGE = "Usage: java App.jar id_device port device_type(client/hub/base)";

    private static Core core;
=======
import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.data.Commons;
>>>>>>> remotes/origin/logic

public class App implements Commons {

    public static void main(String[] args) {
        Core core;
        if (args.length != 3)
            System.out.println(USAGE);
        try {
            long id = Long.parseLong(args[0]);
            int port = Integer.parseInt(args[1]);
            String type = args[2].toLowerCase();
            if (type.equals(CoreType.CLIENTE.toString()))
                core = new Client(id, port);
            else if (type.equals(CoreType.HUB.toString()))
                core = new Hub(id, port);
            else if (type.equals(CoreType.BASESTATION.toString()))
                core = new BaseStation(id, port);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(USAGE);
        }
    }

<<<<<<< HEAD
}
=======

}
>>>>>>> remotes/origin/logic
