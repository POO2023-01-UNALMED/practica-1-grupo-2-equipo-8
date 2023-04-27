package uiMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import gestorAplicacion.Estudiante;
import gestorAplicacion.Registro;
import gestorAplicacion.TipoUsuarios;


public class UIAsignarCita {
    static Scanner sc = new Scanner(System.in);

    private static ArrayList<String> horariosDisponibles = new ArrayList<String>(Arrays.asList("6:00", "6:30", "7:00", "7:30", "8:00", 
    "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", 
    "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"));
    private static ArrayList<Estudiante> estudiantesConCita = new ArrayList<>(Collections.nCopies(horariosDisponibles.size(), null));
    
    public static void asignarCita(Estudiante estudiante){
        System.out.println("Escriba a continuación el Horario de la Cita en formato HH:MM"
        + "\n" + "Recuerde que las citas son cada 30 minutos, inician a las 6:00 y terminan a las 18:00");
        String cita = sc.next();
        String verificado = UIAsignarCita.comprobarCita(cita);
        UIAsignarCita.estudiantesConCita.set(UIAsignarCita.horariosDisponibles.indexOf(verificado),estudiante);
    }

    public static void asignarCita(List<Estudiante> estudiantes){
        List<Estudiante> PAPIs = UIAsignarCita.ordenarEstudiantesPorPAPI(estudiantes);
        for (i = 0, i > .size(); ){

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
            while (true) {
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Volver a intentar");
                System.out.println("2. Ver todos los horarios disponibles");
                System.out.println("3. Cancelar el proceso");
                try {
                    int opcion = scanner.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("Introduce la nueva cita: ");
                            String nuevoString = scanner.next();
                            comprobarCita(nuevoString);
                            break;
                        case 2:
                            System.out.println("Las horarios disponibles son: ");
                            for (String s : lista) {
                                System.out.println(s);
                            }
                            System.out.println("Introduce la nueva cita: ");
                            String nuevo = scanner.next();
                            comprobarCita(nuevo);
                            break;
                        case 3:
                            System.out.println("Operación cancelada.");
                            break;
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
        // scanner.close();
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


