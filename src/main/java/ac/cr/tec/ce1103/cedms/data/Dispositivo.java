package ac.cr.tec.ce1103.cedms.data;

/**
 * Created by pablo on 21/11/14.
 */
public class Dispositivo {
    private long id;
    private CoreType tipo;
    private int puerto;

    public Dispositivo(long id, CoreType tipo, int puerto) {
        this.id = id;
        this.tipo = tipo;
        this.puerto = puerto;
    }

    public Dispositivo(String id, String tipo, String puerto) {
        this.id = Long.parseLong(id);
        this.tipo = CoreType.parseCoreType(tipo);
        this.puerto = Integer.parseInt(puerto);
    }

    public int getPuerto() {
        return puerto;
    }

    public long getId() {
        return id;
    }

    public CoreType getTipo() {
        return tipo;
    }
}
