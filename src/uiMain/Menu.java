package uiMain;

import baseDatos.Deserializador;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import baseDatos.Serializador;
import gestorAplicacion.Admin;
import gestorAplicacion.Carreras;
import gestorAplicacion.Curso;
import gestorAplicacion.CursoEstudiante;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Facultades;
import gestorAplicacion.Horario;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Deserializador.deserializador();
        Login.login();
        sc.close();
    }

    public static void sistema(Estudiante estudiante){
        System.out.println("Bienvenido "+estudiante.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Ver recomendación de asignaturas\n"
                    + "2. Buscar asignatura\n"
                    + "3. Crear horario\n"
                    + "4. Inscribir materias\n"
                    + "5. Ver aplicabilidad a estimulos\n"
                    + "6. Calificar a un docente\n"
                    + "7. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 7");
                continue;
            }
            switch(opcion){
                case "1": UIRecomendarAsignaturas.recomendarAsignaturas(estudiante, sc); continue;
                case "2": estudiante.buscarCursos(); break;
                case "3": Horario horario = estudiante.crearHorario(); BusquedaCursos.buscarCursos(estudiante, horario); break;
                case "4": IncripcionMaterias.inscribirMaterias(estudiante); break;
                case "5": ; break; // Añadir llamada al método correspondiente
                case "6": CalificacionProfesores.calificarProfesor(sc); continue; // Añadir llamada al método correspondiente
                case "7": salir(); break;
            }
        }
    }

    public static void sistema(Profesor profesor){
        System.out.println("Bienvenido "+profesor.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Calificar\n"
                    + "2. Ver aplicabilidad a estimulos\n"
                    + "3. Buscar asignatura\n"
                    + "4. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 4");
                continue;
            }
            switch(opcion){
                case "1": ; break; // Añadir llamada al método correspondiente
                case "2": ; break; // Añadir llamada al método correspondiente
                case "3": profesor.buscarCursos(); break;
                case "4": salir(); break;
            }
        }
    }
    
    public static void sistema(Admin admin){
        System.out.println("Bienvenido "+admin.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Crear curso\n"
                    + "2. Eliminar curso\n"
                    + "3. Buscar asignatura\n"
                    + "4. Ver estudiantes\n"
                    + "5. Ver profesores\n"
                    + "6. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 6");
                continue;
            }
            switch(opcion){
                case "1": Admin.agregarCurso(sc); continue;
                case "2": Admin.eliminarCurso(sc); continue;
                case "3": admin.buscarCursos(); continue;
                case "4": Admin.verEstudiantes(); continue;
                case "5": Admin.verProfesores(); continue;
                case "6": salir(); break;
            }
        }
    }
    
    public static void salir(){
        System.out.println("Hasta pronto");
        Serializador.serializador();
        System.exit(0);
    }
}
