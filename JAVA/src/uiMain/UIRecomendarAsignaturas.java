package uiMain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collections;

import gestorAplicacion.Curso;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;

public class UIRecomendarAsignaturas{
    public static void recomendarAsignaturas(Estudiante estudiante, Scanner sc) {
        System.out.println("RECOMENDACION DE ASIGNATURAS");
        System.out.println("A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:");
        
        // Si no hay cursos en el sistema, terminar el proceso.
        if (Registro.getCursos() == null || Registro.getCursos().isEmpty()) {
            System.out.println("\tNo hay cursos existentes.");
            return;
        }

        // Lista con los cursos a recomendar
        ArrayList<Curso> cursosParaRecomendar = new ArrayList<Curso>();
        
        // Por cada curso
        for (Curso curso : Registro.getCursos()) {
            // Si las carreras del curso no están relacionadas con la carrera del estudiante,
            // o el estudiante ya vio el curso, no se toma en cuenta
            boolean esDeLaCarrera = curso.getCarrerasRelacionadas().contains(estudiante.getCarrera());
            boolean vioCurso = estudiante.vioCurso(curso);
            if (!esDeLaCarrera || vioCurso) continue;

            boolean vioPrerrequisitos = curso.vioPrerrequisitos(estudiante);
            if (vioPrerrequisitos) {
                cursosParaRecomendar.add(curso);
            }
        }
        // Si no hay cursos para recomendar, terminar el proceso.
        if (cursosParaRecomendar.isEmpty()) {
            System.out.println("\tNo hay cursos para recomendar.");
            return;
        }

        while (true) {
            // Se imprimen los cursos
            System.out.println("------------------------------------------------------------------------");
            System.out.println("\t\tLista de Cursos");
            System.out.println("------------------------------------------------------------------------");
            for (int i = 0; i < cursosParaRecomendar.size(); i++) {
                System.out.printf("\t%d\t%s\n", i + 1, cursosParaRecomendar.get(i));
            }
            System.out.printf("Elija un curso para ver profesores recomendados o 0 para terminar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, cursosParaRecomendar.size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", cursosParaRecomendar.size());
                continue;
            } else if (opcion == 0) break;

            // Se obtiene el curso de interés y la lista de profesores que lo dictan.
            Curso cursoDeInteres = cursosParaRecomendar.get(opcion - 1);

            ArrayList<String> nombresProfesoresDelCurso = new ArrayList<String>();
            for (Profesor profesor : cursoDeInteres.getProfesoresQueDictanElCurso()) {
                nombresProfesoresDelCurso.add(profesor.getNombre());
            }

            ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();
            for (Profesor profesor : Registro.getProfesores()) {
                if (nombresProfesoresDelCurso.contains(profesor.getNombre())) {
                    listaProfesores.add(profesor);
                }
            }
            // Si no hay profesores, volver a preguntar por otro curso.
            if (listaProfesores == null || listaProfesores.isEmpty()) {
                System.out.println("\tNo hay profesores que dicten el curso.");
                continue;
            }

            // Si un profesor tiene calificación de -1, significa que no ha sido calificado.
            ListIterator<Profesor> iter = listaProfesores.listIterator();
            while (iter.hasNext()) {
                if (!iter.next().fueCalificado()) {
                    iter.remove();
                }
            }
            if (listaProfesores.isEmpty()) {
                System.out.println("\tNo hay profesores que hallan sido calificados.");
                continue;
            }
            
            // Se ordena la lista de forma descendente según las calificaciones de los profesores.
            Collections.sort(listaProfesores, new Comparator<Profesor>() {
                @Override
                public int compare(Profesor p1, Profesor p2) {
                    return Double.compare(p2.getCalificacion(), p1.getCalificacion());
                }
            });

            // Se imprimen los profesores y su respectiva calificación.
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Profesores que dictan el curso:\n" +
                String.format("\t%s\t%-32s\t%s", "#","Nombre","Calificacion"));
            System.out.println("------------------------------------------------------------------------");
            for (int i = 0; i < listaProfesores.size(); i++) {
                System.out.println("\t"+(int) (i+1)+"\t"+String.format("%-32s",listaProfesores.get(i).getNombre())+"\t"+listaProfesores.get(i).getCalificacion());
            }
        }

        return;
    }
}
