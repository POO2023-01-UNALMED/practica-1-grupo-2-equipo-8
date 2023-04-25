package gestorAplicacion;

import java.util.ArrayList;
import java.util.Scanner;

import uiMain.Menu;

public class Admin extends Registro {
    private static final long serialVersionUID = 4L;
    public Admin(String nombre, String correo, String nombreUsuario, String clave, String documento){
        super(nombre, correo, nombreUsuario, clave, documento);
    }

    // METHODS
    public static void agregarCurso() {
        Scanner sc = new Scanner(System.in);

        // Obtenemos datos como nombre, cupos, creditos y cantidad parciales.
        System.out.println("CREAR CURSO");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Cupos: ");
        int cupos = sc.nextInt();
        System.out.print("Creditos: ");
        int creditos = sc.nextInt();
        System.out.print("Cantidad Parciales: ");
        int numeroParciales = sc.nextInt();

        // Se asigna un porcentaje a cada parcial según la cantidad indicada.
        ArrayList<int[]> listaPorcentajes = new ArrayList<int[]>();
        for (int i = 0; i < numeroParciales; i++) {
            System.out.printf("Porcentaje parcial %d: ", i + 1);
            int porcentaje = sc.nextInt();
            int[] nota = {i+1, porcentaje};
            listaPorcentajes.add(nota);
        }

        // PREREQUISITOS
        // Se definen los cursos que serán prerrequisitos.
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
                System.out.println("No hay cursos disponibles como prerrequisitos.");
                break;
            }

            // Se imprimen los posibles prerrequisitos.
            for (int i = 0; i < notInPreRequisitos.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, notInPreRequisitos.get(i).getNombre());
            }
            // El usuario elige algún curso como prerrequisito o 0 para continuar con el siguiente apartado.
            System.out.print("Elige un curso como prerrequisito o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > notInPreRequisitos.size()) break;

            // Se agrega el curso seleccionado como prerrequisito.
            preRequisitos.add(notInPreRequisitos.get(opcion - 1));
        }

        // CARRERAS
        // Se definen las carreras que están relacionadas con el curso.
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
                System.out.println("No hay carreras disponibles.");
                break;
            }

            // Se imprimen las posibles carreras.
            for (int i = 0; i < notIncarrerasRelacionadas.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, notIncarrerasRelacionadas.get(i).getNombre());
            }
            // El usuario elige alguna carrera o 0 para continuar con el siguiente apartado.
            System.out.print("Elige una carrera o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > notIncarrerasRelacionadas.size()) break;

            // Se agrega la carrera seleccionada como relacionada.
            carrerasRelacionadas.add(notIncarrerasRelacionadas.get(opcion - 1));
        }

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

        ArrayList<Facultades> facultades = new ArrayList<Facultades>();
        while (true) {
            for (int i = 0; i < Facultades.values().length; i++) {
                System.out.printf("%d. %s\n", i + 1, Facultades.values()[i].getNombre());
            }
            System.out.print("Elige una facultad relacionada o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > Facultades.values().length) break;
            if (!facultades.contains(Facultades.values()[opcion - 1])) {
                facultades.add(Facultades.values()[opcion - 1]);
            }
        }

        System.out.printf("Curso %s agregado con exito.\n", nombre);
        Curso nuevoCurso = new Curso(nombre, cupos, creditos, numeroParciales, listaPorcentajes, preRequisitos, carrerasRelacionadas, profesoresQueDictanElCurso, facultades);
        Registro.agregarCurso(nuevoCurso);
        Menu.salir();
    }
}
