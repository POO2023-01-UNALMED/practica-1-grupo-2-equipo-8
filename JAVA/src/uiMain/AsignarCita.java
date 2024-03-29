package uiMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import gestorAplicacion.Estudiante;
import gestorAplicacion.Registro;
import gestorAplicacion.Admin;
import gestorAplicacion.CursoProfesor;

public class AsignarCita {
    // Añadir un Scanner
    static Scanner sc = new Scanner(System.in);

    // Atributos
    private static ArrayList<Estudiante> estudiantesConCita = new ArrayList<Estudiante>();
    private static ArrayList<Integer> horariosDisponibles = new ArrayList<>(Arrays.asList(
        600, 630, 700, 730, 800, 830, 900, 930, 1000, 1030, 1100, 1130, 1200,
        1230, 1300, 1330, 1400, 1430, 1500, 1530, 1600, 1630, 1700, 1730, 1800));

    // Gets y Sets
    public static ArrayList<Estudiante> getEstudiantesConCita() {
        return estudiantesConCita;}

    public static ArrayList<Integer> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public static void setHorariosDisponibles(ArrayList<Integer> horariosDisponibles) {
        AsignarCita.horariosDisponibles = horariosDisponibles;
    }

    // Funcionalidad
    public static void asignarCita(Registro usuario, ArrayList<Estudiante> estudiantes){
        // Verificar que el usuario sea un Administrador
        if (usuario instanceof Admin){
            // Resetear los cursos
            // Guardar Copias de Seguridad
            ArrayList<Estudiante> copiaEstudiantesConCita = AsignarCita.estudiantesConCita;
            ArrayList<Integer> copiaHorariosDisponibles = AsignarCita.horariosDisponibles;
            ArrayList<Integer> copiaHorarioEstudiantes = new ArrayList<Integer>();
            for (int i = 0; i < estudiantes.size(); i++){
                copiaHorarioEstudiantes.add(estudiantes.get(i).getCita());
            }
            ArrayList<CursoProfesor> cursosProfesor = CursoProfesor.getCursosCreados();
            ArrayList<ArrayList<Estudiante>> copiaListaEstudiantes = new ArrayList<>();
            for (int i = 0; i < cursosProfesor.size(); i++){
                copiaListaEstudiantes.add(cursosProfesor.get(i).getListaEstudiantes());
            }
            if (AsignarCita.resetearCursos()){
                // Seleccionar estudiantes
                ArrayList<Estudiante> estudiantesSeleccionados = AsignarCita.seleccionarEstudiantes(estudiantes);
                if (estudiantesSeleccionados != null){
                    // Eliminar citas de inscripción antiguas
                    for (Estudiante estudiante : estudiantesSeleccionados){
                        if (estudiante.getCita() != 0) {
                            AsignarCita.horariosDisponibles.add(estudiante.getCita());
                        }
                        estudiante.setCita(0);
                    }
                    Collections.sort(AsignarCita.horariosDisponibles);

                    // Ordenar por PAPA
                    ArrayList<Estudiante> PAPAs = AsignarCita.ordenarEstudiantesPorPAPA(estudiantesSeleccionados);
                    
                    // Borrar citas
                    for (Estudiante estudiante : estudiantes) {
                        if (horariosDisponibles.contains(estudiante.getCita())) {
                            horariosDisponibles.remove(estudiante.getCita());
                        }
                    }

                    // Asignarles las citas disponibles a cada estudiante
                    ArrayList<Integer> citasTomadas = new ArrayList<Integer>();
                    for (int i = 0; i < PAPAs.size(); i++) {
                        int horario = AsignarCita.horariosDisponibles.get(i);
                        Estudiante estudiante = PAPAs.get(i);
                        estudiante.setCita(horario);
                        AsignarCita.estudiantesConCita.add(estudiante);
                        citasTomadas.add(horario);
                    }
                    horariosDisponibles.removeAll(citasTomadas);

                    boolean continuar = true;
                    while (continuar){
                        // Asegurarse de la opción
                        System.out.println("---------------------------------------------------------------------------------------------------\n"
                                            + "¿ Desea guardar los cambios?\n"
                                            + "1. Sí\n"
                                            + "2. Sí, Mostrar citas asignadas\n"
                                            + "3. Cancelar la Asignación de Citas de Inscripción"
                                            + "\n----------------------------------------------------------------------------\n");
                        String opcion = sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3"));
                        if (!opciones.contains(opcion)) {
                            System.out.println("---------------------------------------------------------------------------------------------------\n"
                                                + "Debe seleccionar un número entre el 1 y el 3"
                                                + "\n----------------------------------------------------------------------------\n");
                            continue;
                        }
                        switch(opcion){
                            case "1":
                                continuar = false;
                                Registro.setEstudiantesConCita(AsignarCita.estudiantesConCita);
                                break;
                            
                            case "2":
                                System.out.println("----------------------------------------------------------------------------\n"
                                + "Orden de inscripción");
                                int cont = 1;
                                for (Estudiante estudiante : AsignarCita.estudiantesConCita){
                                    System.out.println(cont + ": " + estudiante);
                                    cont++;
                                }
                                System.out.println("----------------------------------------------------------------------------");
                                for (Estudiante estudiante : Registro.getEstudiantes()) {
                                    estudiante.setInscribir(false);
                                }
                                AsignarCita.estudiantesConCita.get(0).setInscribir(true);
                                continuar = false;
                                Registro.setEstudiantesConCita(AsignarCita.estudiantesConCita);
                                break;
                            
                            case "3":
                                // Resetear todo
                                AsignarCita.estudiantesConCita = copiaEstudiantesConCita;
                                AsignarCita.horariosDisponibles = copiaHorariosDisponibles;
                                for (int i = 0; i < estudiantes.size(); i++){
                                    Estudiante estudiante = estudiantes.get(i);
                                    int cita = copiaHorarioEstudiantes.get(i);
                                    estudiante.setCita(cita);
                                }
                                for (int i = 0; i < cursosProfesor.size(); i++){
                                    CursoProfesor curso = cursosProfesor.get(i);
                                    ArrayList<Estudiante> listaEstudiantes = copiaListaEstudiantes.get(i);
                                    curso.setListaEstudiantes(listaEstudiantes);
                                }
                                continuar = false;
                                break;
                        }
                    }
                }
                // Si el usuario cancelo el proceso en la etapa de seleccionar estudiantes
                else{
                    for (int i = 0; i < cursosProfesor.size(); i++){
                        CursoProfesor curso = cursosProfesor.get(i);
                        ArrayList<Estudiante> listaEstudiantes = copiaListaEstudiantes.get(i);
                        curso.setListaEstudiantes(listaEstudiantes);
                    }
                    System.out.println("----------------------------------------------------------------------------\n"
                                        + "Proceso Cancelado"
                                        + "\n----------------------------------------------------------------------------\n");
                }

            }
            // Si el usuario cancela el proceso en la etapa de reseteo
            else{
                System.out.println("----------------------------------------------------------------------------\n"
                                    + "Proceso Cancelado"
                                    + "\n----------------------------------------------------------------------------\n");
            }
        }
        // Si el usuario no era un admin
        else{
            System.out.println("----------------------------------------------------------------------------\n"
                                + "Debe tener el rol de Administrador para llevar a cabo este proceso"
                                + "\n----------------------------------------------------------------------------\n");
        }
    }

    // Metodos Auxiliares
    public static ArrayList<Estudiante> ordenarEstudiantesPorPAPA(ArrayList<Estudiante> estudiantes) {
        // Verificar que la lista de estudiantes no esté vacía
        if (estudiantes.isEmpty()) {
            return estudiantes;
        }

        // Se ordena la lista de forma descendente según los PAPAs de los estudiantes.
        Collections.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante e1, Estudiante e2) {
                return Double.compare(e2.calcularPAPA(), e1.calcularPAPA());
            }
        });
    
       
        
        // Retornar la lista de estudiantes ordenada por PAPI de mayor a menor
        return estudiantes;
    }

    public static ArrayList<Estudiante> seleccionarEstudiantes(ArrayList<Estudiante> estudiantes){
        // Mostrar todos los estudiantes
        System.out.println("----------------------------------------------------------------------------\n"
                            + "Seleccione a los estudiantes a los que le quiera asignar una cita\n"
                            + "Para esto, solo escriba los números asociados separados por una coma\n"
                            + "Para seleccionarlos a todos escriba 0");
        for (int i = 0; i < estudiantes.size(); i++){
            System.out.println((i + 1) + ": " + estudiantes.get(i));
            ;
        }
        System.out.println("----------------------------------------------------------------------------");
        String seleccion = sc.nextLine();

        String[] numerosSeleccionados = seleccion.split(",");
        ArrayList<Estudiante> estudiantesSeleccionados = new ArrayList<>();

        for (String numero : numerosSeleccionados) {
            int indice = Integer.parseInt(numero.trim()) - 1;

            if (indice == -1) {
                // Se ha seleccionado la opción de seleccionar todos los estudiantes
                estudiantesSeleccionados.addAll(estudiantes);
            }

            if (indice >= 0 && indice < estudiantes.size()) {
                // Se ha seleccionado un estudiante específico
                estudiantesSeleccionados.add(estudiantes.get(indice));
            }
        }
        // Mostrar Estudiantes Seleccionados
        System.out.println("----------------------------------------------------------------------------\n"
                            + "Haz elegido a los siguientes estudiantes: ");
        for (Estudiante estudiante : estudiantesSeleccionados){
            System.out.println("-" + estudiante.getNombre());
        }
        System.out.println("----------------------------------------------------------------------------");
        while (true){
            // Asegurarse de la opción
            System.out.println("----------------------------------------------------------------------------\n"
                                + "Confirmar\n"
                                + "1. Continuar\n"
                                + "2. No, volver a seleccionar\n"
                                + "3. Cancelar la Asignación de Citas de Inscripción"
                                
                                + "\n----------------------------------------------------------------------------\n");
            String opcion = sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3"));
            if (!opciones.contains(opcion)) {
                // La opción ingresada no es válida
                System.out.println("----------------------------------------------------------------------------\n"
                                    + "Debe seleccionar un número entre el 1 y el 3"
                                    + "\n----------------------------------------------------------------------------\n");
                continue;
            }
            switch(opcion){
                case "1":
                    // Se confirma la selección de estudiantes
                    return estudiantesSeleccionados;
                
                case "2":
                    // Se desea volver a seleccionar estudiantes
                    return AsignarCita.seleccionarEstudiantes(estudiantes);
                
                case "3":
                    // Se cancela la asignación de citas
                    return null;
            }
        }
    }


    public static boolean resetearCursos(){
        while(true){
            // Asegurarse de la opción
            System.out.println("----------------------------------------------------------------------------\n"
                                + "¿Antes de continuar, desea resetear las listas de estudiantes de los cursos?\n"
                                + "Recuerda que si ya llevaste a cabo este proceso una vez no debería ser necesario llevarlo a cabo de nuevo\n"
                                + "1. Sí, resetear y continuar\n"
                                + "2. No, continuar sin resetear\n"
                                + "3. Cancelar la Asignación de Citas de Inscripción"
                                + "\n----------------------------------------------------------------------------");
            String opcion = sc.nextLine();
            //sc.nextLine();
            ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2", "3"));
            if (!opciones.contains(opcion)) {
                System.out.println("----------------------------------------------------------------------------\n"
                                    + "Debe seleccionar un número entre el 1 y el 3"
                                    + "\n----------------------------------------------------------------------------\n");
                continue;
            }
            switch(opcion){
                case "1":
                // Confirmar elección
                while (true){
                    System.out.println("----------------------------------------------------------------------------\n"
                                        + "¿Estás seguro?\n"
                                        + "1. Sí, resetear y continuar\n"
                                        + "2. No, continuar sin resetear\n"
                                        + "3. Cancelar la Asignación de Citas de Inscripción"
                                        + "\n----------------------------------------------------------------------------\n");
                    String confirmacion = sc.nextLine();
                    ArrayList<String> confirmaciones = new ArrayList<String>(Arrays.asList("1", "2", "3"));
                    if (!confirmaciones.contains(confirmacion)) {
                        System.out.println("----------------------------------------------------------------------------\n"
                                            + "Debe seleccionar un número entre el 1 y el 3"
                                            + "\n----------------------------------------------------------------------------\n");
                        continue;
                    }
                    switch(confirmacion){
                        case "1":
                            // Resetear Cursos
                            for (CursoProfesor curso : CursoProfesor.getCursosCreados()){
                                curso.resetearCurso();
                                return true;
                            }
                        
                        case "2":
                            return true;

                        case "3":
                            return false;
                    }
                }

                case "2":
                    return true;

                case "3":
                    return false;
            }
        }
    }
}
