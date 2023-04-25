package uiMain;

import java.util.ArrayList;

import gestorAplicacion.*;

import gestorAplicacion.Estudiante;
import gestorAplicacion.Registro;

public class UIRecomendarAsignaturas {
    public static void RecomendarAsignaturas(Estudiante estudiante) {
        System.out.println("A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:");
        
        // Lista con los cursos a recomendar
        ArrayList<Curso> cursosParaRecomendar = new ArrayList<Curso>();
        
        // Por cada curso
        for (Curso curso : Registro.getCursos()) {
            // Si las carreras del curso no están relacionadas con la carrera del estudiante,
            // o el estudiante ya vio el curso,
            // no se toma en cuenta
            boolean isCarrera = curso.getCarrerasRelacionadas().contains(estudiante.getCarrera());
            boolean isCursada = estudiante.getCursosVistos().contains(curso);
            if (!isCarrera || isCursada) continue;
            
            // Se recomienda un curso si
            // el estudiante ya vio todos los prerrequisitos del curso
            boolean isPrerrequisitos = estudiante.getCursosVistos().containsAll(curso.getPreRequisitos());
            if (isPrerrequisitos) {
                cursosParaRecomendar.add(curso);
            // o si el curso no tiene prerrequisitos
            } else if (curso.getPreRequisitos().isEmpty()) {
                cursosParaRecomendar.add(curso);
            }
        }

        // Se imprimen los cursos
        for (int i = 0; i < cursosParaRecomendar.size(); i++) {
            System.out.printf("%i. %s", i + 1, cursosParaRecomendar.get(i));
        }
    }
}
