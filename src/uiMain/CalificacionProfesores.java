package uiMain;

import java.util.Scanner;

import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;

public class CalificacionProfesores {
    public static void calificarProfesor(Scanner sc) {
        System.out.println("CALIFICAR PROFESORES");

        while(true) {
            for (int i = 0; i < Registro.getProfesores().size(); i++) {
                System.out.printf("\t%d. %s\n", i+1, Registro.getProfesores().get(i));
            }
            // El usuario elige algún curso o 0 para terminar el proceso.
            System.out.print("\tElige un profesor o 0 para continuar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, Registro.getProfesores().size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", Registro.getProfesores().size());
                continue;
            } else if (opcion == 0) break;

            Profesor profesorACalificar = Registro.getProfesores().get(opcion - 1);

            System.out.print("\t¿Que calificación le da al docente?: ");
            double valoracion = sc.nextDouble();
            profesorACalificar.calificar(valoracion);
        }
    }
}
