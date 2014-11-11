package ac.cr.tec.ce1103.cedms.core;

/**
 * Created by pablo on 11/11/14.
 * Update Id for messages
 */
public class UpdateId {
    int idDevice;
    int idMessage;

    public UpdateId(int idDevice, int idMessage) {
        this.idDevice = idDevice;
        this.idMessage = idDevice;
    }

    public boolean isUpdateId(int idDevice, int idMessage) {
        return (this.idDevice == idDevice && this.idMessage == idDevice);
    }
}
