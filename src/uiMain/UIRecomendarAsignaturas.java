package uiMain;

import java.util.ArrayList;

import gestorAplicacion.*;

import gestorAplicacion.Estudiante;
import gestorAplicacion.Registro;

public class UIRecomendarAsignaturas {
    public static void recomendarAsignaturas(Estudiante estudiante) {
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
                ArrayList<String> nombresCursosEstudiante = new ArrayList<String>();
                for (CursoEstudiante asignatura : estudiante.getCursosVistos()) {
                    nombresCursosEstudiante.add(asignatura.getNombre());
                }
                // y se revisa si el nombre del curso está en los cursos vistos.
                fueCursada = nombresCursosEstudiante.contains(curso.getNombre());
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
                ArrayList<String> nombresCursosEstudiante = new ArrayList<String>();
                for (CursoEstudiante asignatura : estudiante.getCursosVistos()) {
                    nombresCursosEstudiante.add(asignatura.getNombre());
                }
                // finalmente se verifica si el estudiante a cursado todos los preRequisitos.
                vioPrerrequisitos = nombresCursosEstudiante.containsAll(nombresCursosPreRequisitos);
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

        return;
    }
}
