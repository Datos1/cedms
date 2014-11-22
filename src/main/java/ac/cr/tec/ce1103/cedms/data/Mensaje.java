package ac.cr.tec.ce1103.cedms.data;

/**
 * Created by pablo on 18/11/14.
 */
public class Mensaje {
    private long source = 0;
    private long target = 0;
    private String updateId;
    private String titulo;
    private String msg;
    private int numero;

    public Mensaje(long source, long target, String updateId, String titulo, String msg, int numero) {
        this.source = source;
        this.target = target;
        this.updateId = updateId;
        this.titulo = titulo;
        this.msg = msg;
        this.numero = numero;
    }

    public long getSource() {
        return source;
    }

    public long getTarget() {
        return target;
    }

    public String getUpdateId() {
        return updateId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMsg() {
        return msg;
    }

    public int getNumero() {
        return numero;
    }
}
