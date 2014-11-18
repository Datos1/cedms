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


    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return this.comparable;
    }
}
