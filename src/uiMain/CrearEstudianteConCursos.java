package uiMain;

import java.util.Scanner;
import java.util.ArrayList;

import gestorAplicacion.Curso;
import gestorAplicacion.CursoEstudiante;
import gestorAplicacion.CursoProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;

public class CrearEstudianteConCursos {
    public static void ModificarEstudiante(Scanner sc) {
        ArrayList<Estudiante> listaEstudiantes = Registro.getEstudiantes();
        ArrayList<Curso> listaCursos = Registro.getCursos();
        ArrayList<Profesor> listaProfesores = Registro.getProfesores();

        while(true) {
            System.out.println("Estudiantes:\n" +
            String.format("%s\t%-32s\t%s\t%s\t%s","#","Nombre","Carrera","Semestre","PAPA"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            int cont = 1;
            for (Estudiante estudiante : listaEstudiantes) {
                System.out.println(cont+"\t"+String.format("%-32s",estudiante.getNombre())+"\t"+estudiante.getCarrera()+"\t"+estudiante.getSemestre()+"\t"+estudiante.calcularPAPA());
                cont++;
            }
            System.out.printf("Elija un estudiante o 0 para terminar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!Helpers.checkOpcion(opcion, listaEstudiantes.size())) {
                System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", listaEstudiantes.size());
                continue;
            } else if (opcion == 0) break;

            Estudiante estudianteAModificar = listaEstudiantes.get(opcion - 1);

            while (true) {
                System.out.println("Cursos:\n" +
                String.format("%s\t%s\t%-32s\t%s\t%-17s\t%s","#","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                cont = 1;
                for (Curso curso : listaCursos) {
                    System.out.println(cont+"\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas());
                    cont++;
                }
                System.out.printf("Elija un curso o 0 para terminar: ");
                opcion = sc.nextInt();
                // Se verifica la opcion ingresada.
                if (!Helpers.checkOpcion(opcion, listaCursos.size())) {
                    System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", listaCursos.size());
                    continue;
                } else if (opcion == 0) break;

                Curso cursoATransformar = listaCursos.get(opcion - 1);

                System.out.println("Profesor:\n" +
                String.format("%s\t%-32s\t%-17s","#","Nombre","Facultad"));
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                cont = 1;
                for (Profesor profesor : listaProfesores) {
                    System.out.println(cont+"\t"+String.format("%-32s",profesor.getNombre())+"\t"+String.format("%-17s",profesor.getFacultad()));
                    cont++;
                }
                System.out.printf("Elija un profesor o 0 para terminar: ");
                opcion = sc.nextInt();
                // Se verifica la opcion ingresada.
                if (!Helpers.checkOpcion(opcion, listaProfesores.size())) {
                    System.out.printf("\t\tDebe seleccionar un número entre el 0 y el %d\n", listaProfesores.size());
                    continue;
                } else if (opcion == 0) break;

                Profesor profesorConElQueCurso = listaProfesores.get(opcion - 1);

                ArrayList<double[]> listaNotas = new ArrayList<double[]>();
                System.out.println("NOTAS");
                for (int i = 0; i < cursoATransformar.getNumeroParciales(); i++) {
                    System.out.printf("\tCalificacion parcial %d: ", i + 1);
                    double cal = sc.nextDouble();
                    double[] nota = {i+1, cal};
                    listaNotas.add(nota);
                }
                
                CursoEstudiante cursoVisto = new CursoEstudiante(
                    cursoATransformar.getNombre(), 
                    cursoATransformar.getId(), 
                    cursoATransformar.getCupos(), 
                    cursoATransformar.getCreditos(), 
                    cursoATransformar.getNumeroParciales(), 
                    cursoATransformar.getListaPorcentajes(), 
                    cursoATransformar.getFacultad(), 
                    listaNotas, 
                    0, 
                    estudianteAModificar, 
                    null, 
                    profesorConElQueCurso);

                estudianteAModificar.agregarCursoVisto(cursoVisto);
            }
        }
    }
}
