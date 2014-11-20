package ac.cr.tec.ce1103.cedms.data;

/**
 * Created by stiven on 09/11/14.
 */
public interface Commons {


    public static final String USAGE = "Usage: java App id_device  port device_type(client/hub/base_station)";//string usage.
    public static final String CLIENT = "client";//string constant for client.
    public static final String HUB = "hub";//string constant for hub
    public static final String BASE_STATION = "base-station";//string constant for base station
    public static int INSTANCE_COUNTER=1;// instance counter for the unique graph node id.
    // each time a graph Node is created, this variable increments 1 value manually.
}
