package uiMain;

import java.util.ArrayList;

public class Helpers {
    public static boolean checkOpcion(int opcion, int cantidadOpciones) {
        ArrayList<Integer> opciones = new ArrayList<Integer>();
        for (int i = 0; i <= cantidadOpciones; i++) {
            opciones.add(i);
        }
        return opciones.contains(opcion);
    }
}
