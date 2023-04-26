package uiMain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import baseDatos.Serializador;
import gestorAplicacion.Admin;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args){
        Registro registro = new Registro();
        Login.login();
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
            String opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5")) && !(opcion.equals("6")) && !(opcion.equals("7"))){
                System.out.println("Debe seleccionar un número entre el 1 y el 7");
                continue;
            }
            switch(opcion){
                case "1": UIRecomendarAsignaturas.recomendarAsignaturas(estudiante); continue; // Añadir llamada al método correspondiente
                case "2": ; break; // Añadir llamada al método correspondiente
                case "3": ; break; // Añadir llamada al método correspondiente
                case "4": ; break; // Añadir llamada al método correspondiente
                case "5": ; break; // Añadir llamada al método correspondiente
                case "6": ; break; // Añadir llamada al método correspondiente
                case "7": salir(); break;
            }
            break;
        }
        sc.close();
    }
    public static void sistema(Profesor profesor){
        System.out.println("Bienvenido "+profesor.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Calificar\n"
                    + "2. Ver aplicabilidad a estimulos\n"
                    + "3. Salir");
            String opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                continue;
            }
            switch(opcion){
                case "1": ; break; // Añadir llamada al método correspondiente
                case "2": ; break; // Añadir llamada al método correspondiente
                case "3": salir(); break;
            }
            break;
        }
        sc.close();
    }
    public static void sistema(Admin admin){
        System.out.println("Bienvenido "+admin.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Ver recomendación de asignaturas\n"
                    + "2. Crear curso\n"
                    + "3. Eliminar curso\n"
                    + "4. Ver cursos\n"
                    + "5. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 5");
                continue;
            }
            switch(opcion){
                case "1": ; break; // Añadir llamada al método correspondiente
                case "2": Admin.agregarCurso(); continue;
                case "3": Admin.eliminarCurso(); continue;
                case "4": Admin.verCursos(); continue;
                case "5": salir(); break;
            }
            break;
        }
    }
    
    public static void salir(){
        System.out.println("Hasta pronto");
        Serializador.serializador();
        System.exit(0);
    }
}
