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
        System.out.println("Crear curso");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Cupos: ");
        int cupos = sc.nextInt();
        System.out.print("Creditos: ");
        int creditos = sc.nextInt();
        System.out.print("Cantidad Parciales: ");
        int numeroParciales = sc.nextInt();

        ArrayList<int[]> listaPorcentajes = new ArrayList<int[]>();
        for (int i = 0; i < numeroParciales; i++) {
            System.out.printf("Porcentaje parcial %d: ", i + 1);
            int porcentaje = sc.nextInt();
            int[] nota = {i+1, porcentaje};
            listaPorcentajes.add(nota);
        }

        ArrayList<Curso> preRequisitos = new ArrayList<Curso>();
        while (true) {
            for (int i = 0; i < Registro.getCursos().size(); i++) {
                System.out.printf("%d. %s\n", i + 1, Registro.getCursos().get(i).getNombre());
            }
            System.out.print("Elige un curso como prerrequisito o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > Registro.getCursos().size()) break;
            if (!preRequisitos.contains(Registro.getCursos().get(opcion - 1))) {
                preRequisitos.add(Registro.getCursos().get(opcion - 1));
            }
        }

        ArrayList<Carreras> carrerasRelacionadas = new ArrayList<Carreras>();
        while (true) {
            for (int i = 0; i < Carreras.values().length; i++) {
                System.out.printf("%d. %s\n", i + 1, Carreras.values()[i].getNombre());
            }
            System.out.print("Elige una carrera relacionada o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > Carreras.values().length) break;
            if (!carrerasRelacionadas.contains(Carreras.values()[opcion - 1])) {
                carrerasRelacionadas.add(Carreras.values()[opcion - 1]);
            }
        }

        ArrayList<Profesor> profesoresQueDictanElCurso = new ArrayList<Profesor>();
        while (true) {
            for (int i = 0; i < Registro.getProfesores().size(); i++) {
                System.out.printf("%d. %s\n", i + 1, Registro.getProfesores().get(i).getNombre());
            }
            System.out.print("Elige un profesor que dicta el curso o 0 para continuar: ");
            int opcion = sc.nextInt();
            if (opcion == 0 || opcion > Registro.getProfesores().size()) break;
            if (!profesoresQueDictanElCurso.contains(Registro.getProfesores().get(opcion - 1))) {
                profesoresQueDictanElCurso.add(Registro.getProfesores().get(opcion - 1));
            }
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
