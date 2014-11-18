package ac.cr.tec.ce1103.cedms.data;

/**
 * Created by pablo on 17/11/14.
 */
public enum Message {
    GRAFO("grafo"),
    CONNECTION("connection"),
    MENSAJE("mensaje"),
    DESCONECTAR("desconectar");


    private final String comparable;

    Message(String pComparable) {
        this.comparable = pComparable;
    }

    public boolean equals(String input) {
        return this.comparable.equals(input);
    }


}
