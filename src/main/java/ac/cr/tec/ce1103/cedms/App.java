package ac.cr.tec.ce1103.cedms;

/**
 * @version 1.01
 */

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.data.Commons;
import ac.cr.tec.ce1103.cedms.data.CoreType;

import java.net.ConnectException;


public class App implements Commons {

    private static Core core;


    public static void main(String[] args) {
        Core core = null;
        if (args.length >= 3) {
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

            if (args.length > 3)
                for (int i = 3; i < args.length; i++) {
                    if (core != null) {
                        try {
                            core.createConnectionInicial("localhost",Integer.parseInt(args[i]));
                        } catch (ConnectException e) {
                            e.getMessage();
                        }
                    }
                }

        }
        else {
            System.out.println(USAGE);
        }
    }


}




