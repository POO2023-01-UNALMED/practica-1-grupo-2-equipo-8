package uiMain;

import gestorAplicacion.Curso;
import gestorAplicacion.CursoEstudiante;
import gestorAplicacion.CursoProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Horario;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import java.util.ArrayList;
import java.util.Scanner;

public class BusquedaCursos {
    public static void buscarCursos(Estudiante estudiante){
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
                System.out.println("Los cursos disponibles son:\n"
                + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                int cont = 1;
                for(Curso curso : Registro.getCursos()){
                    System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+curso.getFacultad()+"\t"+curso.getCarrerasRelacionadas());
                    System.out.println(cont+". Ver detalles");
                    cont++;
                }
                System.out.println(cont+". Volver");
                String opcion = sc.next();
                if(opcion.equals(String.valueOf(cont))){
                    break;
                }
                boolean comp = true;
                for(int x = 1; x<=Registro.getCursos().size(); x++){
                    if(opcion.equals(String.valueOf(x))){
                        mostrarDetalles(estudiante, Registro.getCursos().get(x-1));
                        comp = false;
                        break;
                    }
                }
                if(comp == true){
                    System.out.println("Debe seleccionar una opción entre el 1 y el "+Registro.getCursos().size()+1);
                }
            }
        }
    }
    public static void mostrarDetalles(Estudiante estudiante, Curso curso){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoEstudiante> listaCursos = new ArrayList<CursoEstudiante>();
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 1;
            for(Profesor profesor : Registro.getProfesores()){
                for(CursoProfesor cp : profesor.getListaCursos()){
                    if(cp.getNombre().equals(curso.getNombre())){
                        listaCursos.add(new CursoEstudiante(cp.getNombre(), cp.getId(), cp.getCupos(), cp.getCreditos(),
                        cp.getNumeroParciales(), cp.getListaPorcentajes(),
                        cp.getFacultad(), new ArrayList(), estudiante.getSemestre()+1, estudiante, cp.getHorario(), profesor));
                        System.out.println("Profesor: "+profesor.getNombre()+"\nHorario: "+cp.getHorario()+"\nCupos: "+cp.getCupos());
                        System.out.println(cont+". Añadir a horario existente");
                        cont++;
                    }
                }
            }
            System.out.println(cont+". volver");
            String opcion = sc.next();
            if(opcion.equals(String.valueOf(cont))){
                break;
            }
            boolean comp = true;
            for(int x = 1; x<=cont; x++){
                if(opcion.equals(String.valueOf(x))){
                    if(!curso.getProfesoresQueDictanElCurso().isEmpty()){
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
                System.out.println("Los cursos disponibles son:\n"
                + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                int cont = 1;
                for(Curso curso : Registro.getCursos()){
                    System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+curso.getFacultad()+"\t"+curso.getCarrerasRelacionadas());
                    System.out.println(cont+". Ver detalles");
                    cont++;
                }
                System.out.println(cont+". Volver");
                String opcion = sc.next();
                if(opcion.equals(String.valueOf(cont))){
                    break;
                }
                boolean comp = true;
                for(int x = 1; x<=Registro.getCursos().size(); x++){
                    if(opcion.equals(String.valueOf(x))){
                        mostrarDetalles(estudiante, Registro.getCursos().get(x-1), horario);
                        comp = false;
                        break;
                    }
                }
                if(comp == true){
                    System.out.println("Debe seleccionar una opción entre el 1 y el "+Registro.getCursos().size()+1);
                }
            }
        }
    }
    
    public static void mostrarDetalles(Estudiante estudiante, Curso curso, Horario horario){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoEstudiante> listaCursos = new ArrayList<CursoEstudiante>();
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 1;
            for(Profesor profesor : Registro.getProfesores()){
                for(CursoProfesor cp : profesor.getListaCursos()){
                    if(cp.getNombre().equals(curso.getNombre())){
                        listaCursos.add(new CursoEstudiante(cp.getNombre(), cp.getId(), cp.getCupos(), cp.getCreditos(),
                        cp.getNumeroParciales(), cp.getListaPorcentajes(),
                        cp.getFacultad(), new ArrayList(), estudiante.getSemestre()+1, estudiante, cp.getHorario(), profesor));
                        System.out.println("Profesor: "+profesor.getNombre()+"\nHorario: "+cp.getHorario()+"\nCupos: "+cp.getCupos());
                        System.out.println(cont+". Añadir a horario");
                        cont++;
                    }
                }
            }
            System.out.println(cont+". volver");
            String opcion = sc.next();
            if(opcion.equals(String.valueOf(cont))){
                break;
            }
            boolean comp = true;
            for(int x = 1; x<cont; x++){
                if(opcion.equals(String.valueOf(x))){
                    if(!curso.getProfesoresQueDictanElCurso().isEmpty()){
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
        System.out.println("Ya tienes el curso "+nombre);
    }
    public static void reportarFallo(CursoEstudiante curso1, CursoEstudiante curso2){
        System.out.println("Hay un problema entre el horario del curso "+curso1.getNombre()+" y el curso "+curso2.getNombre()+
                "\nHorario de "+curso1.getNombre()+":"+curso1.getHorario()+"\tHorario"+curso2.getNombre()+":"+curso1.getHorario());
    }
    
    public static void aceptar(){
        System.out.println("El curso se agregó correctamente");
    }
}
