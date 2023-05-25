package uiMain;

import gestorAplicacion.Carreras;
import gestorAplicacion.Curso;
import gestorAplicacion.CursoEstudiante;
import gestorAplicacion.CursoProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Facultades;
import gestorAplicacion.Horario;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import java.util.ArrayList;
import java.util.Scanner;

public class BusquedaCursos {
    public static void buscarCursos(){
        ArrayList<Curso> listaCursos = Registro.getCursos();
        boolean compp = true;
        Scanner sc = new Scanner(System.in);
        if(Registro.getCursos().isEmpty()){
            while(true){
                System.out.println("No hay cursos disponibles");
                System.out.println("1. volver");
                String opcion = sc.next();
                if(!(opcion.equals("1"))){
                    System.out.println("Solo puede seleccionar la opción \"volver\"");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                }
                break;
            }
        }
        else{
            while(true){
                System.out.println("Indique lo que quiere realizar:\n"
                        + "1. Ver todos los cursos\n"
                        + "2. Filtrar cursos por facultad\n"
                        + "3. Filtrar cursos por carreras relacionadas\n"
                        + "4. Filtrar cursos por horario\n"
                        + "5. Salir");
                String opcion = sc.next();
                sc.nextLine();
                if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5"))){
                    System.out.println("Debe seleccionar un número entre el 1 y el 5");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                    case "2": 
                        while(true){
                            System.out.println("Indique la facultad:\n"
                            + "1. Minas\n"
                            + "2. Ciencias\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.MINAS); break;
                                case "2": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.CIENCIAS); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "3": 
                        while(true){
                            System.out.println("Indique la carrera:\n"
                            + "1. Ingeniería de Sistemas\n"
                            + "2. Ciencias de la Computación\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.SISTEMAS); break;
                                case "2": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.COMPUTACION); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "4": 
                        System.out.println("Indique el horario (formato HH:MM) Ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00");
                        opcion = sc.nextLine();
                        listaCursos = Curso.filtrarPorHorario(listaCursos, opcion);
                        break;
                    case "5": compp = false; break;
                }
                break;
            }
            if(compp == true && !listaCursos.isEmpty()){
                while(true){
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    int cont = 1;
                    for(Curso curso : listaCursos){
                        System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas());
                        System.out.println(cont+". Ver detalles");
                        cont++;
                    }
                    System.out.println(cont+". Volver");
                    String opcion = sc.next();
                    if(opcion.equals(String.valueOf(cont))){
                        break;
                    }
                    boolean comp = true;
                    for(int x = 1; x<=listaCursos.size(); x++){
                        if(opcion.equals(String.valueOf(x))){
                            mostrarDetalles(listaCursos.get(x-1));
                            comp = false;
                            break;
                        }
                    }
                    if(comp == true){
                        System.out.println("Debe seleccionar una opción entre el 1 y el "+listaCursos.size()+1);
                    }
                }
            }
            else if(listaCursos.isEmpty()){
                System.out.println("No hay cursos con las especificaciones dadas");
            }
        }
    }
    
    
    
    public static void mostrarDetalles(Curso curso){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoProfesor> listaCursos= new ArrayList<>();
            ArrayList<Profesor> profesores = new ArrayList<>();
            for(Profesor profesor : Registro.getProfesores()){
                for(CursoProfesor cp : profesor.getListaCursos()){
                    if(cp.getNombre().equals(curso.getNombre())){
                        listaCursos.add(cp);
                        profesores.add(profesor);
                    }
                }
            }
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 0;
            for(CursoProfesor cp : listaCursos){
                System.out.println("Profesor: "+profesores.get(cont)+"\nHorario: "+cp.getHorario()+"\nCupos: "+cp.getCupos());
                cont++;
            }
            System.out.println("1. volver");
            String opcion = sc.next();
            if(opcion.equals("1")){
                break;
            }
        }
    }
    
    public static void buscarCursos(Estudiante estudiante){
        ArrayList<Curso> listaCursos = Registro.getCursos();
        boolean compp = true;
        Scanner sc = new Scanner(System.in);
        if(Registro.getCursos().isEmpty()){
            while(true){
                System.out.println("No hay cursos disponibles");
                System.out.println("1. volver");
                String opcion = sc.next();
                if(!(opcion.equals("1"))){
                    System.out.println("Solo puede seleccionar la opción \"volver\"");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                }
                break;
            }
        }
        else{
            while(true){
                System.out.println("Indique lo que quiere realizar:\n"
                        + "1. Ver todos los cursos\n"
                        + "2. Filtrar cursos por facultad\n"
                        + "3. Filtrar cursos por carreras relacionadas\n"
                        + "4. Filtrar cursos por horario\n"
                        + "5. Salir");
                String opcion = sc.next();
                sc.nextLine();
                if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5"))){
                    System.out.println("Debe seleccionar un número entre el 1 y el 5");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                    case "2": 
                        while(true){
                            System.out.println("Indique la facultad:\n"
                            + "1. Minas\n"
                            + "2. Ciencias\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.MINAS); break;
                                case "2": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.CIENCIAS); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "3": 
                        while(true){
                            System.out.println("Indique la carrera:\n"
                            + "1. Ingeniería de Sistemas\n"
                            + "2. Ciencias de la Computación\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.SISTEMAS); break;
                                case "2": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.COMPUTACION); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "4": 
                        System.out.println("Indique el horario (formato HH:MM) Ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00");
                        opcion = sc.nextLine();
                        listaCursos = Curso.filtrarPorHorario(listaCursos, opcion);
                        break;
                    case "5": compp = false; break;
                }
                break;
            }
            if(compp == true && !listaCursos.isEmpty()){
                while(true){
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    int cont = 1;
                    for(Curso curso : listaCursos){
                        System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas());
                        System.out.println(cont+". Ver detalles");
                        cont++;
                    }
                    System.out.println(cont+". Volver");
                    String opcion = sc.next();
                    if(opcion.equals(String.valueOf(cont))){
                        break;
                    }
                    boolean comp = true;
                    for(int x = 1; x<=listaCursos.size(); x++){
                        if(opcion.equals(String.valueOf(x))){
                            mostrarDetalles(estudiante, listaCursos.get(x-1));
                            comp = false;
                            break;
                        }
                    }
                    if(comp == true){
                        System.out.println("Debe seleccionar una opción entre el 1 y el "+listaCursos.size()+1);
                    }
                }
            }
            else if(listaCursos.isEmpty()){
                System.out.println("No hay cursos con las especificaciones dadas");
            }
        }
    }
    public static void mostrarDetalles(Estudiante estudiante, Curso curso){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoEstudiante> listaCursos = curso.obtenerGrupos(estudiante);
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 1;
            for(CursoEstudiante ce : listaCursos){
                System.out.println("Profesor: "+ce.getProfesor().getNombre()+"\nHorario: "+ce.getHorario()+"\nCupos: "+ce.getCupos());
                System.out.println(cont+". Añadir a horario existente");
                cont++;
            }
            System.out.println(cont+". volver");
            String opcion = sc.next();
            if(opcion.equals(String.valueOf(cont))){
                break;
            }
            boolean comp = true;
            for(int x = 1; x<=cont; x++){
                if(opcion.equals(String.valueOf(x))){
                    if(!curso.obtenerGrupos().isEmpty()){
                        agregarCurso(estudiante, listaCursos.get(x-1));
                    }
                    else{
                        System.out.println("El curso seleccionado no tiene profesores asignados");
                    }
                    comp = false;
                    break;
                }
            }
            if(comp == true){
                System.out.println("Debe seleccionar una opción entre el 1 y el "+cont);
            }
        }
    }
    
    public static void buscarCursos(Estudiante estudiante, Horario horario){
        ArrayList<Curso> listaCursos = Registro.getCursos();
        boolean compp = true;
        Scanner sc = new Scanner(System.in);
        if(Registro.getCursos().isEmpty()){
            while(true){
                System.out.println("No hay cursos disponibles");
                System.out.println("1. volver");
                String opcion = sc.next();
                if(!(opcion.equals("1"))){
                    System.out.println("Solo puede seleccionar la opción \"volver\"");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                }
                break;
            }
        }
        else{
            while(true){
                System.out.println("Indique lo que quiere realizar:\n"
                        + "1. Ver todos los cursos\n"
                        + "2. Filtrar cursos por facultad\n"
                        + "3. Filtrar cursos por carreras relacionadas\n"
                        + "4. Filtrar cursos por horario\n"
                        + "5. Salir");
                String opcion = sc.next();
                sc.nextLine();
                if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5"))){
                    System.out.println("Debe seleccionar un número entre el 1 y el 5");
                    continue;
                }
                switch(opcion){
                    case "1": break;
                    case "2": 
                        while(true){
                            System.out.println("Indique la facultad:\n"
                            + "1. Minas\n"
                            + "2. Ciencias\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.MINAS); break;
                                case "2": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.CIENCIAS); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "3": 
                        while(true){
                            System.out.println("Indique la carrera:\n"
                            + "1. Ingeniería de Sistemas\n"
                            + "2. Ciencias de la Computación\n"
                            + "3. Salir");
                            opcion = sc.next();
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                                continue;
                            }
                            switch(opcion){
                                case "1": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.SISTEMAS); break;
                                case "2": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.COMPUTACION); break;
                                case "3": break;
                            }
                            break;
                        }
                        break;
                    case "4": 
                        System.out.println("Indique el horario (formato HH:MM) Ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00");
                        opcion = sc.nextLine();
                        listaCursos = Curso.filtrarPorHorario(listaCursos, opcion);
                        break;
                    case "5": compp = false; break;
                }
                break;
            }
            if(compp == true && !listaCursos.isEmpty()){
                while(true){
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    int cont = 1;
                    for(Curso curso : listaCursos){
                        System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas());
                        System.out.println(cont+". Ver detalles");
                        cont++;
                    }
                    System.out.println(cont+". Volver");
                    String opcion = sc.next();
                    if(opcion.equals(String.valueOf(cont))){
                        break;
                    }
                    boolean comp = true;
                    for(int x = 1; x<=listaCursos.size(); x++){
                        if(opcion.equals(String.valueOf(x))){
                            mostrarDetalles(estudiante, listaCursos.get(x-1), horario);
                            comp = false;
                            break;
                        }
                    }
                    if(comp == true){
                        System.out.println("Debe seleccionar una opción entre el 1 y el "+listaCursos.size()+1);
                    }
                }
            }
            else if(listaCursos.isEmpty()){
                System.out.println("No hay cursos con las especificaciones dadas");
            }
        }
    }
    
    public static void mostrarDetalles(Estudiante estudiante, Curso curso, Horario horario){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoEstudiante> listaCursos = curso.obtenerGrupos(estudiante);
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 1;
            for(CursoEstudiante ce : listaCursos){
                System.out.println("Profesor: "+ce.getProfesor().getNombre()+"\nHorario: "+ce.getHorario()+"\nCupos: "+ce.getCupos());
                System.out.println(cont+". Añadir a horario");
                cont++;
            }
            System.out.println(cont+". volver");
            String opcion = sc.next();
            if(opcion.equals(String.valueOf(cont))){
                break;
            }
            boolean comp = true;
            for(int x = 1; x<cont; x++){
                if(opcion.equals(String.valueOf(x))){
                    if(!curso.obtenerGrupos().isEmpty()){
                        horario.agregarCurso(listaCursos.get(x-1));
                    }
                    else{
                        System.out.println("El curso seleccionado no tiene profesores asignados");
                    }
                    comp = false;
                    break;
                }
            }
            if(comp == true){
                System.out.println("Debe seleccionar una opción entre el 1 y el "+cont);
            }
        }
    }
    
    public static void agregarCurso(Estudiante estudiante, CursoEstudiante curso){
        Scanner sc = new Scanner(System.in);
        if(estudiante.getHorariosCreados().isEmpty()){
            System.out.println("No ha creado ningún horario");
        }
        else{
            while(true){
                System.out.println("Seleccione el horario al que le quiere agregar el curso seleccionado: ");
                int cont = 1;
                for(Horario horario : estudiante.getHorariosCreados()){
                    System.out.println("Horario "+horario.getId()+":\n");
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%-32s\t%-8s\t%-25s\t%-39s\t%-5s","Nombre","Creditos","Profesor","Horario","Cupos"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    for(CursoEstudiante ce : horario.getCursos()){
                        System.out.println("\t"+String.format("%-32s", ce.getNombre())+"("+ce.getId()+")\t"+String.format("%-8s", ce.getCreditos())+"\t"+String.format("%-25s", ce.getProfesor().getNombre())+"\t"+String.format("%-39s", ce.getHorario())+"\t"+String.format("%-5s", ce.getCupos()));
                    }
                    System.out.println("\n"+cont+". Seleccionar\n");
                    cont++;
                }
                String opcion = sc.next();
                boolean comp = true;
                for(int x = 1; x<cont; x++){
                    if(opcion.equals(String.valueOf(x))){
                        estudiante.getHorariosCreados().get(x-1).agregarCurso(curso);
                        comp = false;
                        break;
                    }
                }
                if(comp == true){
                    System.out.println("Debe seleccionar una opción entre el 1 y el "+(cont-1));
                    continue;
                }
                break;
            }
            
        }
    }
    
    public static void reportarFallo(String nombre){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ya tienes el curso "+nombre);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void reportarFallo(CursoEstudiante curso1, CursoEstudiante curso2){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Hay un problema entre el horario del curso "+curso1.getNombre()+" y el curso "+curso2.getNombre()+
                "\nHorario de "+curso1.getNombre()+":"+curso1.getHorario()+"\tHorario"+curso2.getNombre()+":"+curso1.getHorario());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public static void aceptar(){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("El curso se agregó correctamente");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
