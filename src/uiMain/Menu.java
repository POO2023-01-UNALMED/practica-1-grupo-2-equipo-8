package uiMain;

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
        Registro registro = new Registro();
        int[] n1 = {1,33}; //La primera entrada corresponde al id del enum TipoNota 
        int[] n2 = {1,33}; //La primera entrada corresponde al id del enum TipoNota 
        int[] n3 = {1,34}; //La primera entrada corresponde al id del enum TipoNota 
        ArrayList<int[]> por = new ArrayList<int[]>();
        por.add(n1);
        por.add(n2);
        por.add(n3);
        ArrayList<Facultades> facultades1 = new ArrayList<Facultades>();
        facultades1.add(Facultades.MINAS);
        ArrayList<Facultades> facultades2 = new ArrayList<Facultades>();
        facultades2.add(Facultades.MINAS);
        facultades2.add(Facultades.CIENCIAS);
        ArrayList<Curso> pre1 = new ArrayList<Curso>();
        ArrayList<Carreras> carreras1 = new ArrayList<Carreras>();
        Curso c1 = new Curso("Programación Orientada a Objetos", (short)3, 3, por,pre1,carreras1,facultades2);
        ArrayList<Curso> pre2 = new ArrayList<Curso>();
        pre2.add(c1);
        Curso c2 = new Curso("Análisis de datos", (short)3, 3, por,pre1,carreras1,facultades2);
        //    public Curso(String nombre, short creditos, int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos, ArrayList<Carreras> carrerasRelacionadas, ArrayList<Facultades> facultad)
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
                    + "5. Ver estimulos a los que aplica\n"
                    + "6. Calificar a un docente\n"
                    + "7. Salir");
            String opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5")) && !(opcion.equals("6")) && !(opcion.equals("7"))){
                System.out.println("Debe seleccionar un número entre el 1 y el 7");
                continue;
            }
            switch(opcion){
                case "1": UIRecomendarAsignaturas.recomendarAsignaturas(estudiante, sc); continue;
                case "2": ; break; // Añadir llamada al método correspondiente
                case "3": ; break; // Añadir llamada al método correspondiente
                case "4": ; break; // Añadir llamada al método correspondiente
                case "5": BusquedaEstimulos.buscarEstimulos(estudiante);
                case "6": ; break; // Añadir llamada al método correspondiente
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
                    + "3. Ver estimulos a los que aplica\n"
                    + "4. Salir");
            String opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4"))){
                System.out.println("Debe seleccionar un número entre el 1 y el 4");
                continue;
            }
            switch(opcion){
                case "1": ; break; // Añadir llamada al método correspondiente
                case "2": ; break; // Añadir llamada al método correspondiente
                case "3": BusquedaEstimulos.buscarEstimulos(profesor);
                case "4": salir(); break;
            }
        }
    }
    public static void sistema(Admin admin){
        System.out.println("Bienvenido "+admin.getNombre());
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Indique lo que quiere realizar:\n"
                    + "1. Ver recomendación de asignaturas\n"
                    + "2. Ver estimulos [por id]\n"
                    + "3. Ver estimulos [todos]\n"
                    + "4. Salir");
            String opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4"))){
                    + "1. Crear curso\n"
                    + "2. Eliminar curso\n"
                    + "3. Ver cursos\n"
                    + "4. Salir";
            }
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 4");
                continue;
            }
            switch(opcion){
                case "1": ; break; // Añadir llamada al método correspondiente
                case "2": BusquedaEstimulos.buscarEstimulosPorId();
                case "3": BusquedaEstimulos.buscarEstimulos();
                case "1": Admin.agregarCurso(sc); continue;
                case "2": Admin.eliminarCurso(sc); continue;
                case "3": Admin.verCursos(sc); continue;
                case "4": salir(); break;
            }
        }
    }
    
    public static void salir(){
        System.out.println("Hasta pronto");
        Serializador.serializador();
        System.exit(0);
    }
}
