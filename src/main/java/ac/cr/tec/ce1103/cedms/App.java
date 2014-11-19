package ac.cr.tec.ce1103.cedms;


import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.core.Client;
import ac.cr.tec.ce1103.cedms.core.Core;
import ac.cr.tec.ce1103.cedms.core.Hub;

public class App {

    public static final String USAGE = "Usage: java App id_device  port device_type(client/hub/base_station)";
    public static final String CLIENT = "client";
    public static final String HUB = "hub";
    public static final String BASE_STATION = "base-station";

    public static void main(String[] args) {
        Core core;
        if (args.length != 3)
            System.out.println(USAGE);
        try {
            long id = Long.parseLong(args[0]);
            int port = Integer.parseInt(args[1]);
            String type = args[2].toLowerCase();
            if (type.equals(CLIENT))
                core = new Client(id, port);
            else if (type.equals(HUB))
                core = new Hub(id, port);
            else if (type.equals(BASE_STATION))
                core = new BaseStation(id, port);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(USAGE);
        }
    }

}