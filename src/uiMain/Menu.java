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
import gestorAplicacion.EstimuloEstudiante;
import gestorAplicacion.EstimuloProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Facultades;
import gestorAplicacion.Horario;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import gestorAplicacion.TipoUsuarios;
import uiMain.UIRecomendarAsignaturas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deserializador.deserializador();

        ArrayList<Facultades> A = new ArrayList<Facultades>();
        A.add(Facultades.MINAS);

        Registro.agregarEstimulosEstudiantes(new EstimuloEstudiante(
                "Estimulo 1",
                "Estimulo para irse de vacaciones y sacar 5 en todos los parciales de POO",
                TipoUsuarios.ESTUDIANTE,
                A,
                10,
                21,
                3));
        Registro.agregarEstimulosEstudiantes(new EstimuloEstudiante(
                "Estimulo 2",
                "Estimulo para irse de vacaciones y sacar 5 en todos los parciales de Integrales",
                TipoUsuarios.ESTUDIANTE,
                A,
                10,
                21,
                3.5));
        Registro.agregarEstimulosEstudiantes(new EstimuloEstudiante(
                "Estimulo 3",
                "Estimulo para irse de vacaciones y sacar 5 en todos los parciales de Algoritmos",
                TipoUsuarios.ESTUDIANTE,
                A,
                0,
                21,
                4));
        Registro.agregarEstimulosEstudiantes(new EstimuloEstudiante(
                "Estimulo 4",
                "Estimulo ultra poderoso, ganas todas las materias con 5 durante 2 semestres",
                TipoUsuarios.ESTUDIANTE,
                A,
                2,
                10,
                4.6));

        ArrayList<Integer> B1 = new ArrayList<Integer>();
        ArrayList<Integer> B2 = new ArrayList<Integer>();
        ArrayList<Integer> B3 = new ArrayList<Integer>();

        B1.add(100000);
        B1.add(100002);

        B2.add(3);

        B3.add(100005);
        B3.add(1000011);
        B3.add(100000);

        Registro.agregarEstimulosProfesores(new EstimuloProfesor(
                "Estimulo 1",
                "Nivel platino: 2x de salario x/2 de horas",
                TipoUsuarios.PROFESOR,
                A,
                10,
                B1));

        Registro.agregarEstimulosProfesores(new EstimuloProfesor(
                "Estimulo 2",
                "Nivel platino: 2x de salario x/2 de horas",
                TipoUsuarios.PROFESOR,
                A,
                10,
                B2));

        Registro.agregarEstimulosProfesores(new EstimuloProfesor(
                "Estimulo 3",
                "Nivel platino: 2x de salario x/2 de horas",
                TipoUsuarios.PROFESOR,
                A,
                10,
                B3));

        Registro.agregarEstimulosProfesores(new EstimuloProfesor(
                "Estimulo 4",
                "Nivel platino: 2x de salario x/2 de horas",
                TipoUsuarios.PROFESOR,
                A,
                10,
                B3));

        Registro.agregarEstimulosProfesores(new EstimuloProfesor(
                "Estimulo 5",
                "Nivel platino: 2x de salario x/2 de horas",
                TipoUsuarios.PROFESOR,
                A,
                10,
                B2));

        Login.login();
        sc.close();
    }

    public static void sistema(Estudiante estudiante) {
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
                    estudiante.buscarCursos();
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
                    continue; // Añadir llamada al método correspondiente
                case "7":
                    salir();
                    break;
            }
        }
    }

    public static void sistema(Profesor profesor) {
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
                    continue; // Añadir llamada al método correspondiente
                case "2":
                    BusquedaEstimulos.buscarEstimulos(profesor);
                case "3":
                    profesor.buscarCursos();
                    break;
                case "4":
                    salir();
                    break;
            }
        }
    }

    public static void sistema(Admin admin) {
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
                    + "7. Ver estimulos [por id]\n"
                    + "8. Ver estimulos [todos]\n"
                    + "9. Salir");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(
                    Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
            if (!opciones.contains(opcion)) {
                System.out.println("Debe seleccionar un número entre el 1 y el 9");
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
                    admin.buscarCursos();
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
