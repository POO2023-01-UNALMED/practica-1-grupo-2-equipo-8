package uiMain;

import baseDatos.Deserializador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import baseDatos.Serializador;
import gestorAplicacion.Admin;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Horario;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;

public class Menu {
    static Registro registro;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deserializador.deserializador();

        Login.login();
        sc.close();
    }

    public static void sistema(Estudiante estudiante) {
        registro = estudiante;
        System.out.println("Bienvenido " + estudiante.getNombre());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Ver recomendación de asignaturas\n"
                    + "2. Buscar asignatura\n"
                    + "3. Crear horario\n"
                    + "4. Inscribir materias\n"
                    + "5. Ver estimulos a los que aplica\n"
                    + "6. Calificar a un docente\n"
                    + "7. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 7");
                continue;
            }

            switch (opcion) {
                case "1":
                    UIRecomendarAsignaturas.recomendarAsignaturas(estudiante, sc);
                    continue;
                case "2":
                    registro.buscarCursos();
                    break;
                case "3":
                    Horario horario = estudiante.crearHorario();
                    BusquedaCursos.buscarCursos(estudiante, horario);
                    break;
                case "4":
                    IncripcionMaterias.inscribirMaterias(estudiante);
                    break;
                case "5":
                    BusquedaEstimulos.buscarEstimulos(estudiante);
                    break;
                case "6":
                    CalificacionProfesores.calificarProfesor(sc);
                    continue;
                case "7":
                    salir();
                    break;
            }
        }
    }

    public static void sistema(Profesor profesor) {
        registro = profesor;

        System.out.println("Bienvenido " + profesor.getNombre());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Calificar\n"
                    + "2. Ver estimulos a los que aplica\n"
                    + "3. Buscar asignatura\n"
                    + "4. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));

            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 4");
                continue;
            }

            switch (opcion) {
                case "1":
                    FuncsProfesor.calificar(profesor, sc);
                    break;
                case "2":
                    BusquedaEstimulos.buscarEstimulos(profesor);
                    break;
                case "3":
                    registro.buscarCursos();
                    break;
                case "4":
                    salir();
                    break;
            }
        }
    }

    public static void sistema(Admin admin) {
        registro = admin;

        System.out.println("Bienvenido " + admin.getNombre());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Crear curso\n"
                    + "2. Eliminar curso\n"
                    + "3. Buscar asignatura\n"
                    + "4. Asignar citas de inscripción\n"
                    + "5. Ver estudiantes\n"
                    + "6. Ver profesores\n"
                    + "7. Ver estimulos [por nombre]\n"
                    + "8. Ver estimulos [todos]\n"
                    + "9. Modificar estudiante con cursos\n"
                    + "10. Salir");
            String opcion = sc.nextLine();
            //sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(
                    Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9","10"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 10");
                continue;
            }
            switch (opcion) {
                case "1":
                    FuncsAdmin.agregarCurso(sc);
                    break;
                case "2":
                    FuncsAdmin.eliminarCurso(sc);
                    break;
                case "3":
                    registro.buscarCursos();
                    break;
                case "4":
                    AsignarCita.asignarCita(admin, Registro.getEstudiantes());
                    break;
                case "5":
                    FuncsAdmin.verEstudiantes(sc);
                    break;
                case "6":
                    FuncsAdmin.verProfesores();
                    break;
                case "7":
                    BusquedaEstimulos.buscarEstimulosPorId();
                    break;
                case "8":
                    BusquedaEstimulos.buscarEstimulos();
                    break;
                case "9":
                    CrearEstudianteConCursos.ModificarEstudiante(sc);
                    break;
                case "10":
                    salir();
                    break;
            }
        }
    }

    public static void salir(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("¿Desea cerrar salir del programa o solo cerrar sesión?\n"
                    + "1. Cerrar sesión\n"
                    + "2. Salir completamente del programa\n");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 2");
                continue;
            }
            switch(opcion){
                case "1":
                    Serializador.serializador();
                    Login.login();
                case "2":
                    System.out.println("Hasta pronto");
                    Serializador.serializador();
                    System.exit(0);
            }
        }
    }      
}
