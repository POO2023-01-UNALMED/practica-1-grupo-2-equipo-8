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
                fueCursada = estudiante.getCursosVistos().contains(curso);
            }
            if (!esDeLaCarrera || fueCursada) continue;
            
            // Se recomienda un curso si
            // el estudiante ya vio todos los prerrequisitos del curso
            boolean vioPrerrequisitos;
            if (estudiante.getCursosVistos() == null) {
                vioPrerrequisitos = false;
            } else {
                vioPrerrequisitos = estudiante.getCursosVistos().containsAll(curso.getPreRequisitos());
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
