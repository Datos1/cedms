package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.BaseStation;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

import java.util.regex.Pattern;


public class BaseStationTerminal extends Terminal {
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion," +
            " 2: Imprimir Grafo 3: Ver Mensajes en espera 4: Ver Updates procesados 5:Ver Nodos Adyacentes " +
            "6: Cambiar peso Nodo 7: Desconectar ";
    static protected Pattern OPCIONES_PATTERN = Pattern.compile("[1-7]");
    BaseStation baseStation;

    public BaseStationTerminal(BaseStation pBase) {
        super(pBase);
        this.baseStation = pBase;// asignamos client
        init();
    }

    /**
     * menu que segun lo ingresado en consola actua segun las opciones
     */
    @Override
    protected void menuOpciones() {
        System.out.println(OPCIONES);
        if (terminal.hasNext(OPCIONES_PATTERN)) {
            switch (Integer.parseInt(terminal.nextLine())) {
                case 1:
                    askAndConnect();
                    break;
                case 2:
                    imprimirGrafo();
                    break;
                case 3:
                    verMensajes();
                    break;


            }
            System.out.println(SLASH);
        } else terminal.next();
    }

    private void imprimirGrafo() {

    }

    /**
     * imprime los mensajes que ya han sido procesados
     */
    private void verUpdatesProcesados() {
        List<String> updates = baseStation.getUpdates();
        for (int i = 0; i < updates.getLength(); i++) {
            System.out.println("UpdateId: ");
            System.out.println(updates.get(i));
        }
    }
    private void verMensajes() {

    }

}
