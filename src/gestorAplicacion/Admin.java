package gestorAplicacion;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Registro {
    private static final long serialVersionUID = 4L;
    public Admin(String nombre, String correo, String nombreUsuario, String clave, String documento){
        super(nombre, correo, nombreUsuario, clave, documento);
    }

    // METHODS
    public static void agregarCurso(Scanner sc) {
        // Obtenemos datos como nombre, cupos, creditos y cantidad parciales.
        System.out.println("CREAR CURSO");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Creditos: ");
        int creditos = sc.nextInt();
        System.out.print("Cantidad Parciales: ");
        int numeroParciales = sc.nextInt();

        // Se asigna un porcentaje a cada parcial según la cantidad indicada.
        System.out.println("Lista de porcentajes");
        ArrayList<int[]> listaPorcentajes = new ArrayList<int[]>();
        for (int i = 0; i < numeroParciales; i++) {
            System.out.printf("\tPorcentaje parcial %d: ", i + 1);
            int porcentaje = sc.nextInt();
            int[] nota = {i+1, porcentaje};
            listaPorcentajes.add(nota);
        }

        // PREREQUISITOS
        // Se definen los cursos que serán prerrequisitos.
        System.out.println("Prerrequisitos");
        ArrayList<Curso> preRequisitos = new ArrayList<Curso>();
        while (true) {
            // Se construye la lista de cursos que no son prerrequisitos,
            // esto para evitar repeticiones.
            ArrayList<Curso> notInPreRequisitos = new ArrayList<Curso>();
            for (Curso curso : Registro.getCursos()) {
                if (!preRequisitos.contains(curso)) {
                    notInPreRequisitos.add(curso);
                }
            }
            // Si no hay cursos disponibles, se continua al siguiente apartado.
            if (notInPreRequisitos.isEmpty()) {
                System.out.println("\tNo hay cursos disponibles como prerrequisitos.");
                break;
            }

            // Se imprimen los posibles prerrequisitos.
            for (int i = 0; i < notInPreRequisitos.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, notInPreRequisitos.get(i).getNombre());
            }

            // Se crea la lista de opciones.
            ArrayList<Integer> opciones = new ArrayList<Integer>();
            for (int i = 0; i < notInPreRequisitos.size(); i++) {
                opciones.add(i);
            }
            // El usuario elige alguna facultad o 0 para continuar con el siguiente apartado.
            System.out.print("\tElige una facultad o 0 para continuar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!opciones.contains(opcion)) {
                System.out.printf("Debe seleccionar un número entre el 0 y el %d\n", opciones.size());
                continue;
            } else if (opcion == 0) break;

            // Se agrega el curso seleccionado como prerrequisito.
            preRequisitos.add(notInPreRequisitos.get(opcion - 1));
        }

        // CARRERAS
        // Se definen las carreras que están relacionadas con el curso.
        System.out.println("Carreras relacionadas");
        ArrayList<Carreras> carrerasRelacionadas = new ArrayList<Carreras>();
        while (true) {
            // Se construye la lista de carreras que no están relacionadas,
            // esto para evitar repeticiones.
            ArrayList<Carreras> notIncarrerasRelacionadas = new ArrayList<Carreras>();
            for (Carreras carrera : Carreras.values()) {
                if (!carrerasRelacionadas.contains(carrera)) {
                    notIncarrerasRelacionadas.add(carrera);
                }
            }
            // Si no hay carreras disponibles, se continua al siguiente apartado.
            if (notIncarrerasRelacionadas.isEmpty()) {
                System.out.println("\tNo hay carreras disponibles.");
                break;
            }

            // Se imprimen las posibles carreras.
            for (int i = 0; i < notIncarrerasRelacionadas.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, notIncarrerasRelacionadas.get(i).getNombre());
            }

            // Se crea la lista de opciones.
            ArrayList<Integer> opciones = new ArrayList<Integer>();
            for (int i = 0; i < notIncarrerasRelacionadas.size(); i++) {
                opciones.add(i);
            }
            // El usuario elige alguna facultad o 0 para continuar con el siguiente apartado.
            System.out.print("\tElige una facultad o 0 para continuar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!opciones.contains(opcion)) {
                System.out.printf("Debe seleccionar un número entre el 0 y el %d\n", opciones.size());
                continue;
            } else if (opcion == 0) break;

            // Se agrega la carrera seleccionada como relacionada.
            carrerasRelacionadas.add(notIncarrerasRelacionadas.get(opcion - 1));
        }
        
        /*
        ArrayList<Profesor> profesoresQueDictanElCurso = new ArrayList<Profesor>();
        // PROFESORES
        // Se definen los profesores que dictan el curso.
        ArrayList<Profesor> profesoresQueDictanElCurso = new ArrayList<Profesor>();
        while (true) {
            // Se construye la lista de profesores que no dictan el curso,
            // esto para evitar repeticiones.
            ArrayList<Profesor> notInProfesoresQueDictanElCurso = new ArrayList<Profesor>();
            for (Profesor profesor : Registro.getProfesores()) {
                if (!profesoresQueDictanElCurso.contains(profesor)) {
                    notInProfesoresQueDictanElCurso.add(profesor);
                }
            }
            // Si no hay profesores disponibles, se continua al siguiente apartado.
            if (notInProfesoresQueDictanElCurso.isEmpty()) {
                System.out.println("No hay profesores disponibles.");
                break;
            }

            // Se imprimen los profesores disponibles.
            for (int i = 0; i < notInProfesoresQueDictanElCurso.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, notInProfesoresQueDictanElCurso.get(i).getNombre());
            }
            // El usuario elige algún profesor o 0 para continuar con el siguiente apartado.
            System.out.print("Elige un profesor o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > notInProfesoresQueDictanElCurso.size()) break;

            // Se agrega el profesor seleccionado.
            profesoresQueDictanElCurso.add(notInProfesoresQueDictanElCurso.get(opcion - 1));
        }
        */

        // FACULTADES
        // Se definen las facultades del curso.
        System.out.println("Facultades relacionadas");
        ArrayList<Facultades> facultades = new ArrayList<Facultades>();
        while (true) {
            // Se construye la lista de facultades disponibles,
            // esto para evitar repeticiones.
            ArrayList<Facultades> notInFacultades = new ArrayList<Facultades>();
            for (Facultades facultad : Facultades.values()) {
                if (!facultades.contains(facultad)) {
                    notInFacultades.add(facultad);
                }
            }
            // Si no hay facultades disponibles, se continua al siguiente apartado.
            if (notInFacultades.isEmpty()) {
                System.out.println("\tNo hay facultades disponibles.");
                break;
            }

            // Se imprimen las facultades disponibles.
            for (int i = 0; i < notInFacultades.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, notInFacultades.get(i).getNombre());
            }

            // Se crea la lista de opciones.
            ArrayList<Integer> opciones = new ArrayList<Integer>();
            for (int i = 0; i < notInFacultades.size(); i++) {
                opciones.add(i);
            }
            // El usuario elige alguna facultad o 0 para continuar con el siguiente apartado.
            System.out.print("\tElige una facultad o 0 para continuar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!opciones.contains(opcion)) {
                System.out.printf("Debe seleccionar un número entre el 0 y el %d\n", opciones.size());
                continue;
            } else if (opcion == 0) break;

            // Se agrega la facultad seleccionada.
            facultades.add(notInFacultades.get(opcion - 1));
        }

        Curso nuevoCurso = new Curso(nombre, (short)creditos, numeroParciales, listaPorcentajes, preRequisitos, carrerasRelacionadas, facultades);
        System.out.printf("Curso %s (%d) agregado con exito.\n", nuevoCurso.getNombre(), nuevoCurso.getId());
        Registro.agregarCurso(nuevoCurso);
        return;
    }

    public static void eliminarCurso(Scanner sc) {
        // Cursos para eliminar.
        System.out.println("ELIMINAR CURSOS");
        while (true) {
            // Si no hay cursos que eliminar, terminar el proceso.
            if (Registro.getCursos().isEmpty()) {
                System.out.println("\tNo hay cursos.");
                break;
            }

            // Se imprimen los cursos existentes.
            for (int i = 0; i < Registro.getCursos().size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, Registro.getCursos().get(i));
            }

            // Se crea la lista de opciones.
            ArrayList<Integer> opciones = new ArrayList<Integer>();
            for (int i = 0; i < Registro.getCursos().size(); i++) {
                opciones.add(i);
            }
            // El usuario elige algún curso o 0 para terminar el proceso.
            System.out.print("\tElige un curso o 0 para continuar: ");
            int opcion = sc.nextInt();
            // Se verifica la opcion ingresada.
            if (!opciones.contains(opcion)) {
                System.out.printf("Debe seleccionar un número entre el 0 y el %d\n", opciones.size());
                continue;
            } else if (opcion == 0) break;
            
            // Se elimina el curso del sistema.
            ArrayList<Curso> newList = Registro.getCursos();
            newList.remove(opcion - 1);
            Registro.setCursos(newList);
        }
        return;
    }

    public static void verCursos(Scanner sc) {
        System.out.println("LISTA DE CURSOS");

        // Si no hay cursos para ver, terminar el proceso.
        if (Registro.getCursos().isEmpty()) {
            System.out.println("\tNo hay cursos.");
            return;
        }
        // Se imprimen los cursos.
        for (int i = 0; i < Registro.getCursos().size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, Registro.getCursos().get(i));
        }
        System.out.print("\tPresiona una tecla para continuar: ");
        sc.nextLine();
        return;
    }
}
