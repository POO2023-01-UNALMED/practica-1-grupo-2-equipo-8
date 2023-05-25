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

    public static boolean esNumerico(String texto) {
        return texto.matches("\\d+");
    }

    public static boolean esAlfa(String str) {
        str = str.trim();
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }

        return true;
    }

    public static double promedioLista(ArrayList<Double> lista) {
        double sum = 0;
        for (double n : lista) {
            sum += n;
        }
        return sum / lista.size();
    }
}
