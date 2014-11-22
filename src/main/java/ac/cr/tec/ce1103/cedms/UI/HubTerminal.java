package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.Hub;
import ac.cr.tec.ce1103.cedms.dataStructures.List;

/**
 * Created by pablo on 19/11/14.
 */
public class HubTerminal extends Terminal {
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion," +
            " 2: Ver updates procesados 3: Ver Nodos Adyacentes 4: Cambiar peso Nodo 5: Desconectar ";
    Hub hub;

    public HubTerminal(Hub pHub) {
        super(pHub);
        this.hub = pHub;// asignamos client
        init();
    }

    @Override
    protected void menuOpciones() {
        System.out.println(OPCIONES);
        if (terminal.hasNext(OPCIONES_PATTERN)) {
            switch (Integer.parseInt(terminal.next())) {
                case 1:
                    askAndConnect();
                    break;
                case 2:
                    verUpdatesProcesados();
                    break;
                case 3:
                    verNodosAdyacentes();
                    break;

            }
            System.out.println(SLASH);
        } else terminal.next();
    }

    private void verUpdatesProcesados() {
        List<String> updates = hub.getUpdates();
        for (int i = 0; i < updates.getLength(); i++) {
            System.out.println("UpdateId: ");
            System.out.println(updates.get(i));
        }
    }


}
