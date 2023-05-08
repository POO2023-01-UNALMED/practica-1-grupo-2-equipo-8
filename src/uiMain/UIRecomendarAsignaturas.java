package uiMain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Collections;

import gestorAplicacion.Curso;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.CursoEstudiante;
import gestorAplicacion.Registro;

public class UIRecomendarAsignaturas {
    public static void recomendarAsignaturas(Estudiante estudiante, Scanner sc) {
        System.out.println("RECOMENDACION DE ASIGNATURAS");
        System.out.println("A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:");
        
        // Si no hay cursos en el sistema, terminar el proceso.
        if (Registro.getCursos().isEmpty()) {
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

            boolean fueCursada;
            // Si el estudiante es nuevo, no ha cursado ninguna materia
            if (estudiante.getCursosVistos() == null) {
                fueCursada = false;
            } else {
                // La comparación se realiza entre los nombres, ya que son clases distintas,
                // por lo que se obtiene la lista de nombres,
                ArrayList<String> nombresCursosVistos = new ArrayList<String>();
                for (CursoEstudiante asignatura : estudiante.getCursosVistos()) {
                    nombresCursosVistos.add(asignatura.getNombre());
                }
                // y se revisa si el nombre del curso está en los cursos vistos.
                fueCursada = nombresCursosVistos.contains(curso.getNombre());
            }
            if (!esDeLaCarrera || fueCursada) continue;
            
            // Se recomienda un curso si
            // el estudiante ya vio todos los prerrequisitos del curso
            boolean vioPrerrequisitos;
            // Si el estudiante es nuevo, no ha cursado niguna materia
            if (estudiante.getCursosVistos() == null) {
                vioPrerrequisitos = false;
            } else {
                // La comparación se realiza entre los nombres, ya que son clases distintas,
                // por lo que se obtiene la lista de nombres de los preRequisitos,
                ArrayList<String> nombresCursosPreRequisitos = new ArrayList<String>();
                for (Curso asignatura : curso.getPreRequisitos()) {
                    nombresCursosPreRequisitos.add(asignatura.getNombre());
                }
                // y la lista de nombres de los cursos vistos,
                ArrayList<String> nombresCursosVistos = new ArrayList<String>();
                for (CursoEstudiante asignatura : estudiante.getCursosVistos()) {
                    nombresCursosVistos.add(asignatura.getNombre());
                }
                // finalmente se verifica si el estudiante a cursado todos los preRequisitos.
                vioPrerrequisitos = nombresCursosVistos.containsAll(nombresCursosPreRequisitos);
            }
            if (vioPrerrequisitos) {
                cursosParaRecomendar.add(curso);
            // o si el curso no tiene prerrequisitos
            } else if (curso.getPreRequisitos().isEmpty()) {
                cursosParaRecomendar.add(curso);
            }
        }
        // Si no hay cursos para recomendar, terminar el proceso.
        if (cursosParaRecomendar.isEmpty()) {
            System.out.println("\tNo hay cursos para recomendar.");
            return;
        }

        // Se imprimen los cursos
        for (int i = 0; i < cursosParaRecomendar.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, cursosParaRecomendar.get(i));
        }

        while (true) {
            System.out.printf("Elija un curso para ver profesores recomendados o 0 para terminar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, cursosParaRecomendar.size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", cursosParaRecomendar.size());
                continue;
            } else if (opcion == 0) break;

            // Se obtiene el curso de interés y la lista de profesores que lo dictan.
            Curso curso = cursosParaRecomendar.get(opcion - 1);
            ArrayList<Profesor> listaProfesores = curso.getProfesoresQueDictanElCurso();
            // Si no hay profesores, volver a preguntar por otro curso.
            if (listaProfesores == null || listaProfesores.isEmpty()) {
                System.out.println("\tNo hay profesores que dicten el curso.");
                continue;
            }
            
            // Se ordena la lista de forma descendente según las calificaciones de los profesores.
            Collections.sort(listaProfesores, new Comparator<Profesor>() {
                @Override
                public int compare(Profesor p1, Profesor p2) {
                    return Double.compare(p2.getCalificacion(), p1.getCalificacion());
                }
            });

            // Si un profesor tiene calificación de -1, significa que no ha sido calificado.
            for (Profesor profesor : listaProfesores) {
                if (profesor.getCalificacion() == -1) {
                    listaProfesores.remove(profesor);
                }
            }
            if (listaProfesores.isEmpty()) {
                System.out.println("\tNo hay profesores que hallan sido calificados.");
                continue;
            }

            // Se imprimen los profesores y su respectiva calificación.
            for (int i = 0; i < listaProfesores.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, listaProfesores.get(i).toString());
            }
        }

        return;
    }
}
