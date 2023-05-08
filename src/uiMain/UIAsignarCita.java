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


public class UIAsignarCita {
    static Scanner sc = new Scanner(System.in);

    private static ArrayList<Estudiante> estudiantesConCita = new ArrayList<Estudiante>();
    private static ArrayList<String> horariosDisponibles = new ArrayList<String>(Arrays.asList("6:00", "6:30", "7:00", "7:30", "8:00", 
    "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", 
    "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"));

    public static ArrayList<Estudiante> getEstudiantesConCita() {
        return estudiantesConCita;
    }

    public static void setEstudiantesConCita(ArrayList<Estudiante> estudiantesConCita) {
        UIAsignarCita.estudiantesConCita = estudiantesConCita;
    }
    
    
    public static void asignarCita(Estudiante estudiante){
        System.out.println("Escriba a continuación el Horario de la Cita en formato HH:MM"
            + "\n" + "Recuerde que las citas son cada 30 minutos, inician a las 6:00 y terminan a las 18:00");
        String cita = sc.next();
        String verificado = UIAsignarCita.comprobarCita(cita);
        if (verificado != null){
            if (estudiante.getCita() != null){
                UIAsignarCita.horariosDisponibles.add(estudiante.getCita());
                Collections.sort(UIAsignarCita.horariosDisponibles);           
            }
            estudiante.setCita(verificado);
            UIAsignarCita.horariosDisponibles.remove(verificado);
            UIAsignarCita.estudiantesConCita.add(estudiante);
        }
    }

    public static void asignarCita(List<Estudiante> estudiantes){
        List<Estudiante> PAPIs = UIAsignarCita.ordenarEstudiantesPorPAPI(estudiantes);
        for (int i = 0; i > PAPIs.size(); i++){
            String horario = UIAsignarCita.horariosDisponibles.get(i);
            Estudiante estudiante = PAPIs.get(i);
            estudiante.setCita(horario);
            UIAsignarCita.estudiantesConCita.add(estudiante);
        }
        
    }

    public static void siguienteEnInscribir(){
        if (!UIAsignarCita.estudiantesConCita.isEmpty()){
            Estudiante primero = UIAsignarCita.estudiantesConCita.get(0);
            primero.setInscribir(true);
        }
    }

    public static String comprobarCita(String cita) {
        ArrayList<String> lista = UIAsignarCita.horariosDisponibles;
        if (lista.contains(cita)) {
            System.out.println("El horario " + cita + " está disponible.");
            return cita;
        } else {
            System.out.println("El horario \"" + cita + "\" no es válido o no está disponible.");
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;
            while (opcion < 1 || opcion > 3) {
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Volver a intentar");
                System.out.println("2. Ver todos los horarios disponibles");
                System.out.println("3. Cancelar el proceso");
                try {
                    opcion = scanner.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("Introduce la nueva cita: ");
                            String nuevoString = scanner.next();
                            return comprobarCita(nuevoString);
                        case 2:
                            System.out.println("Las horarios disponibles son: ");
                            for (String s : lista) {
                                System.out.println(s);
                            }
                            System.out.println("Introduce la nueva cita: ");
                            String nuevo = scanner.next();
                            return comprobarCita(nuevo);
                        case 3:
                            System.out.println("Operación cancelada.");
                            scanner.close();
                            return null;
                        default:
                            System.out.println("Opción no válida, por favor ingrese una opción válida.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida, por favor ingrese una opción válida.");
                    scanner.nextLine();
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
}
