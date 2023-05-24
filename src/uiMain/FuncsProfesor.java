package uiMain;

import java.util.Scanner;

import gestorAplicacion.CursoProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;

public class FuncsProfesor {
    public static void calificar(Profesor profesor, Scanner sc) {
        System.out.println("CALIFICAR");

        if (profesor.getListaCursos() == null || profesor.getListaCursos().isEmpty()) {
            System.out.println("No dicta ningún curso.");
            return;
        }

        while (true) {
            // Se imprimen los cursos que dicta el profesor.
            System.out.println("Los cursos que dicta:\n" +
            String.format("%s\t%s\t%-32s\t%s\t%-17s\t%s","#","ID","Nombre","Creditos","Facultad","Programas relacionados"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            int cont = 1;
            for (CursoProfesor curso : profesor.getListaCursos()) {
                System.out.println(cont+"\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas());
                cont++;
            }
            System.out.printf("Elija un curso para calificar o 0 para terminar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, profesor.getListaCursos().size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", profesor.getListaCursos().size());
                continue;
            } else if (opcion == 0) break;

            CursoProfesor cursoACalificar = profesor.getListaCursos().get(opcion - 1);

            if (cursoACalificar.getListaEstudiantes() == null || cursoACalificar.getListaEstudiantes().isEmpty()) {
                System.out.println("No hay estudiantes en el curso.");
                continue;
            }

            // Se imprimen los estudiantes inscritos al curso.
            System.out.println("Estudiantes del curso:\n" +
            String.format("%s\t%-32s\t%-17s","#","Nombre","Carrera"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            cont = 1;
            for (Estudiante estudiante : cursoACalificar.getListaEstudiantes()) {
                System.out.println(cont+"\t"+String.format("%-32s",estudiante.getNombre())+"\t"+String.format("%-8s",estudiante.getCarrera()));
                cont++;
            }
            System.out.printf("Elija un estudiante para calificar o 0 para terminar: ");
            opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, cursoACalificar.getListaEstudiantes().size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", cursoACalificar.getListaEstudiantes().size());
                continue;
            } else if (opcion == 0) break;
        }

        return;
    }
}
