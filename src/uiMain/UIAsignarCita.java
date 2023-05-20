package uiMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import gestorAplicacion.Estudiante;
import gestorAplicacion.Horario;
import gestorAplicacion.Registro;
import gestorAplicacion.TipoUsuarios;
import gestorAplicacion.Admin;
import gestorAplicacion.CursoProfesor;


public class UIAsignarCita {
    static Scanner sc = new Scanner(System.in);

    private static ArrayList<Estudiante> estudiantesConCita = new ArrayList<Estudiante>();
    private static ArrayList<String> horariosDisponibles = new ArrayList<>(Arrays.asList(
    "AM 6:00", "AM 6:30", "AM 7:00", "AM 7:30", "AM 8:00", "AM 8:30",
    "AM 9:00", "AM 9:30", "AM 10:00", "AM 10:30", "AM 11:00", "AM 11:30",
    "PM 12:00", "PM 12:30", "PM 1:00", "PM 1:30", "PM 2:00", "PM 2:30",
    "PM 3:00", "PM 3:30", "PM 4:00", "PM 4:30", "PM 5:00", "PM 5:30", "PM 6:00"));

    // Set y Get
    public static ArrayList<Estudiante> getEstudiantesConCita() {
        return estudiantesConCita;
    }

    public static void setEstudiantesConCita(ArrayList<Estudiante> estudiantesConCita) {
        UIAsignarCita.estudiantesConCita = estudiantesConCita;
    }
    
    // Metodos
    public static void asignarCita(Registro admin,Estudiante estudiante){
        // Verificar que la persona que hizo la solicitud es un administrador
        if (admin instanceof Admin){
            // Resetear cursos
            if (UIAsignarCita.resetearCursos()){
                // Guardar los datos anteriores
                ArrayList<String> copiaHorarios = UIAsignarCita.horariosDisponibles;
                ArrayList<Estudiante> copiaEstudiantes = UIAsignarCita.estudiantesConCita;
                String copiaCita = estudiante.getCita();

                // Insertar Cita del estudiante
                System.out.println("Escriba a continuación el Horario de la Cita en formato HH:MM"
                    + "\n" + "Recuerde que las citas son cada 30 minutos, inician a las 6:00 y terminan a las 18:00");
                String cita = sc.next();

                // Comprobar que la cita ingresada sea válida
                String verificado = UIAsignarCita.comprobarCita(cita);
                if (verificado != null){

                    // Comprobar que el estudiante no tenia una cita anterior
                    if (estudiante.getCita() != null){
                        UIAsignarCita.horariosDisponibles.add(estudiante.getCita());
                        UIAsignarCita.ordenarHorarios(UIAsignarCita.horariosDisponibles);           
                    }

                    // Asignarle la Cita al estudiante
                    estudiante.setCita(verificado);
                    UIAsignarCita.horariosDisponibles.remove(verificado);
                    UIAsignarCita.estudiantesConCita.add(estudiante);

                    // Preguntar si se quiere guardar los cambios
                    System.out.println("¿Confirmar los cambios?\n" 
                    + "1. Sí, confirmar\n"
                    + "2. No, cambiar la cita\n"
                    + "3. No, cancelar todo\n");

                    int opcion = sc.nextInt();
                    switch(opcion){
                    case 1:
                    // Continuar
                    break;

                    case 2:
                        // Resetear todo
                        UIAsignarCita.estudiantesConCita = copiaEstudiantes;
                        UIAsignarCita.horariosDisponibles = copiaHorarios;
                        estudiante.setCita(copiaCita);

                        // Volver a iniciar el método
                        UIAsignarCita.asignarCita(admin, estudiante);
                    case 3:
                        // Resetar todo
                        UIAsignarCita.estudiantesConCita = copiaEstudiantes;
                        UIAsignarCita.horariosDisponibles = copiaHorarios;
                        break;
                    }
                }
            }
            // Si se canceló el proceso desde el reseteo de asignaturas
            else{
                System.out.println("Proceso cancelado");
            }
        }
        // Si no es admin
        else{
            System.out.println("Usted debe ser un administrador para poder llevar a cabo este proceso");
        }


    }

    public static void asignarCita(Registro admin, ArrayList<Estudiante> estudiantes){
        // Verificar que la solicitud la hizo un admin
        if (admin instanceof Admin){
            // Resetear cursos
            if (UIAsignarCita.resetearCursos()){
                // Guardar copias de seguridad
                ArrayList<Estudiante> copiaEstudiantesConCita = UIAsignarCita.estudiantesConCita;
                ArrayList<String> copiaHorarios = UIAsignarCita.horariosDisponibles;
                ArrayList<String> copiaHorarioEstudiante = new ArrayList<String>();
                for (int i = 0; i < estudiantes.size(); i++){
                    copiaHorarioEstudiante.add(estudiantes.get(i).getCita());
                }

                // Eliminar las citas que tenian los estudiantes anteriormente

                // Ordenar a los estudiantes por PAPI
                List<Estudiante> PAPIs = UIAsignarCita.ordenarEstudiantesPorPAPI(estudiantes);

                // Asignarles las citas disponibles a cada estudiante
                for (int i = 0; i < PAPIs.size(); i++) {
                    String horario = UIAsignarCita.horariosDisponibles.get(i);
                    Estudiante estudiante = PAPIs.get(i);
                    estudiante.setCita(horario);
                    UIAsignarCita.estudiantesConCita.add(estudiante);
                }
                
                // Verificar si se desea continuar
                System.out.println("¿Deseas continuar?");
                System.out.println("1. Continuar y guardar");
                System.out.println("2. Mostrar la cita asignada a cada estudiante y continuar");
                System.out.println("3. Cancelar proceso");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        // Ordenar a los estudiantes por PAPI
                        break;

                    case 2:
                        for (Estudiante estudiante : UIAsignarCita.estudiantesConCita){
                            System.out.println(estudiante.getNombre() + ": " + estudiante.getCita());
                        }
                        break;
                    case 3:
                        // Resetear todo
                        UIAsignarCita.estudiantesConCita = copiaEstudiantesConCita;
                        UIAsignarCita.horariosDisponibles = copiaHorarios;
                        for (int i = 0; i < estudiantes.size(); i++){
                            Estudiante estudiante = estudiantes.get(i);
                            String cita = copiaHorarioEstudiante.get(i);
                            estudiante.setCita(cita);
                        }  

                    default:
                        System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                }
            }
            // Si se canceló el proceso durante el reseteo de cursos
            else{
                System.out.println("Proceso cancelado");
            }
        }
        // Si no es administrador
        else{
            System.out.println("Usted debe ser un administrador para poder llevar a cabo este proceso");
        }
    }

    public static boolean resetearCursos(){
        // Asegurarse de la opción
        int opcion = 0;
        System.out.println("¿Deseas Resetear las listas de estudiantes antes de continuar?\n"
                            + "Recuerda que si ya llevaste a cabo este proceso una vez no debería ser necesario llevarlo a cabo de nuevo");
        System.out.println("1. Sí, resetear y continuar");
        System.out.println("2. No, continuar sin resetear");
        System.out.println("3. Cancelar el proceso");

        opcion = sc.nextInt();
        switch(opcion) {
            case 1:
                int opcion2 = 0;
                System.out.println("¿Estás totalmente seguro?\n"
                                    + "Está opción es irreversible");
                System.out.println("1. Estoy seguro, resetear y continuar");
                System.out.println("2. No, continuar sin resetear");
                System.out.println("3. Cancelar el proceso");

                opcion2 = sc.nextInt();
                switch(opcion2){
                    case 1:
                        // Resetear Cursos
                        for (CursoProfesor curso : CursoProfesor.getCursosCreados()){
                            curso.resetearCurso();
                            return true;
                        
                        }    
                    case 2:
                        // No resetear
                        return true;
                    case 3:
                        // Cancelar
                        return false;
                }
                
            case 2:
                // No resetear
                return true;
            case 3:
                // Cancelar
                return false;
            default:
                System.out.println("Seleccione una opción valida");
        }
        return false;
    }

    public static void siguienteEnInscribir(){

        // Verificar que la lista de inscripción no esté vacía
        if (!UIAsignarCita.estudiantesConCita.isEmpty()){

            // Permitirle inscribir solo al primer estudiante
            Estudiante primero = UIAsignarCita.estudiantesConCita.get(0);
            primero.setInscribir(true);
        }
        else{
            System.out.println("No hay más estudiantes en la lista");
        }
    }

    public static String comprobarCita(String cita) {

        // Crear una lista con lo horarios disponibles
        ArrayList<String> lista = UIAsignarCita.horariosDisponibles;

        // Verificar que la cita está disponible
        if (lista.contains(cita)) {
            System.out.println("El horario " + cita + " está disponible.");
            return cita;
        } else {
            System.out.println("El horario \"" + cita + "\" no es válido o no está disponible.");

            int opcion = 0;
            // Preguntar que se desea hacer ahora
            while (opcion < 1 || opcion > 3) {
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Volver a intentar");
                System.out.println("2. Ver todos los horarios disponibles");
                System.out.println("3. Cancelar el proceso");
                try {
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            // Introducir nueva cita
                            System.out.println("Introduce la nueva cita: ");
                            String nuevoString = sc.next();
                            return comprobarCita(nuevoString);
                        case 2:

                            // Mostrar los horarios disponibles
                            System.out.println("Las horarios disponibles son: ");
                            for (String s : lista) {
                                System.out.println(s);
                            }
                            System.out.println("Introduce la nueva cita: ");
                            String nuevo = sc.next();
                            return comprobarCita(nuevo);
                        case 3:

                            // Cancelar
                            System.out.println("Operación cancelada.");
                            sc.close();
                            return null;
                        default:
                            System.out.println("Opción no válida, por favor ingrese una opción válida.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida, por favor ingrese una opción válida.");
                    sc.nextLine();
                }
            }
        }
        return null; // Esta línea nunca se alcanza, pero es necesaria para que el código compile.
    }
    
    
    static List<Estudiante> ordenarEstudiantesPorPAPI(List<Estudiante> estudiantes) {
        // Verificar que la lista de estudiantes no esté vacía
        if (estudiantes.isEmpty()) {
            return estudiantes;
        }
    
        // Obtener el tamaño de la lista de estudiantes
        int n = estudiantes.size();
    
        // Recorrer la lista de estudiantes para ordenarla por PAPI
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del estudiante con el PAPI máximo
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Obtener los estudiantes a comparar
                Estudiante estudianteJ = estudiantes.get(j);
                Estudiante estudianteMax = estudiantes.get(maxIndex);

                // Calcular el valor de PAPI para cada estudiante
                int papiJ = estudianteJ.calcularPAPI(estudianteJ.getSemestre());
                int papiMax = estudianteMax.calcularPAPI(estudianteMax.getSemestre());

                // Si el valor de PAPI del estudiante J es mayor, actualizar el índice del máximo
                if (papiJ > papiMax) {
                    maxIndex = j;
                }
            }
            // Si el índice del máximo es diferente del actual, intercambiar los estudiantes
            if (maxIndex != i) {
                // Intercambiar los estudiantes en la lista
                Estudiante tempEstudiante = estudiantes.get(i);
                estudiantes.set(i, estudiantes.get(maxIndex));
                estudiantes.set(maxIndex, tempEstudiante);
            }
        }
        
        // Retornar la lista de estudiantes ordenada por PAPI de mayor a menor
        return estudiantes;
    }

    // Método para ordenar una lista de horarios
    private static void ordenarHorarios(List<String> horarios) {
        for (int i = 0; i < horarios.size() - 1; i++) {
            for (int j = i + 1; j < horarios.size(); j++) {
                String horario1 = horarios.get(i);
                String horario2 = horarios.get(j);
                if (compararHorarios(horario1, horario2) > 0) {
                    // Intercambiar los horarios
                    horarios.set(i, horario2);
                    horarios.set(j, horario1);
                }
            }
        }
    }

    // Método para comparar horarios
    private static int compararHorarios(String horario1, String horario2) {
        int hora1 = Integer.parseInt(horario1.split(":")[0]);
        int minuto1 = Integer.parseInt(horario1.split(":")[1]);
        int hora2 = Integer.parseInt(horario2.split(":")[0]);
        int minuto2 = Integer.parseInt(horario2.split(":")[1]);

        if (hora1 < hora2) {
            return -1;
        } else if (hora1 > hora2) {
            return 1;
        } else {
            if (minuto1 < minuto2) {
                return -1;
            } else if (minuto1 > minuto2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
