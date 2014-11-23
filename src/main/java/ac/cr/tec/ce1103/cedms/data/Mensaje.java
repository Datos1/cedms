package ac.cr.tec.ce1103.cedms.data;

import ac.cr.tec.ce1103.cedms.dataStructures.List;

/**
 * Created by pablo on 18/11/14.
 */
public class Mensaje {
    private long source;
    private long target;
    private String updateId;
    private String titulo;
    private String msg;
    private int numero;


    private List<Long> ruta;

    /**
     *Constructor del Mensaje
     * @param source
     * @param target
     * @param updateId
     * @param titulo
     * @param msg
     * @param numero
     */
    public Mensaje(long source, long target, String updateId, String titulo, String msg, int numero) {
        this.source = source;
        this.target = target;
        this.updateId = updateId;
        this.titulo = titulo;
        this.msg = msg;
        this.numero = numero;
    }

    /**
     * mensaje con ruta
     * @param source
     * @param target
     * @param updateId
     * @param titulo
     * @param msg
     * @param numero
     * @param ruta
     */
    public Mensaje(long source, long target, String updateId, String titulo, String msg, int numero, List<Long> ruta) {
        this(source, target, updateId, titulo, msg, numero);
        this.ruta = ruta;
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

    public String getIdentifier() {
        return updateId+"-"+numero;
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

    public List<Long> getRuta() {
        return ruta;
    }
}
