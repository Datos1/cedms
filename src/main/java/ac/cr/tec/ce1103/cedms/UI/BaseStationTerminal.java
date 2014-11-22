package ac.cr.tec.ce1103.cedms.UI;

import ac.cr.tec.ce1103.cedms.core.BaseStation;


public class BaseStationTerminal extends Terminal {
    public static final String OPCIONES = "Seleccione una de las siguientes opciones: 1: Agregar Conexion," +
            " 2: Imprimir Grafo 3: Ver Mensajes en espera 4: Ver Updates procesados 5:Ver Nodos Adyacentes " +
            "6: Cambiar peso Nodo 7: Desconectar ";
    BaseStation baseStation;

    public BaseStationTerminal(BaseStation pBase) {
        super(pBase);
        this.baseStation = pBase;// asignamos client
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

    private void verMensajes() {

    }

}
