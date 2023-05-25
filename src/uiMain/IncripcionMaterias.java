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

public class IncripcionMaterias {
    public static void inscribirMaterias(Estudiante estudiante){
        Scanner sc = new Scanner(System.in);
        boolean comp = false;
        if(estudiante.getInscribir()){
            while(true){
                System.out.println("Indique lo que desea hacer:\n"
                    + "1. Incribir materias manuealmente\n"
                    + "2. Incribir materias a partir de un horario creado\n"
                    + "3. Volver");
                String opcion = sc.next();
                if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))){
                    System.out.println("Debe seleccionar una opción entre el 1 y el 3");
                    continue;
                }
                switch(opcion){
                    case "1": inscribirManualmente(estudiante); break;
                    case "2": inscribirConHorario(estudiante);break;
                    case "3": return;
                }
                break;
            }
        }
        else{
            System.out.println("Usted no puede inscribir cursos en este momento, pero te redirigiremos a crear un horario\n"
                    + "Este horario te servirá para inscribir (en el momento en el que puedas inscribir) automáticamente las materias que guardaste en dicho horario");
            Horario horario = estudiante.crearHorario(); 
            BusquedaCursos.buscarCursos(estudiante, horario);
        }
    }
    
    public static void inscribirManualmente(Estudiante estudiante){
        Scanner sc = new Scanner(System.in);
        System.out.println("Selección de asignaturas");
        ArrayList<CursoEstudiante> listaCursos = new ArrayList();
        ArrayList<Curso> cursosPosibles = (ArrayList<Curso>) Registro.getCursos().clone();
        ArrayList<CursoEstudiante> cursosAprobados = (ArrayList<CursoEstudiante>) estudiante.getListaCursos().clone();
        
        ArrayList<CursoEstudiante> cursosBorrar = new ArrayList<CursoEstudiante>();
        for(CursoEstudiante ce : cursosAprobados){
            if(ce.calcularPromedio()<3){
                cursosBorrar.add(ce);
                //cursosAprobados.remove(ce);
            }
        }
        cursosAprobados.removeAll(cursosBorrar);

        ArrayList<Curso> cursosABorrar = new ArrayList<Curso>();
        for(Curso c1 : cursosPosibles){
            for(Curso c2 : cursosAprobados){
                if(c1.getNombre().equals(c2.getNombre())){
                    cursosABorrar.add(c1);
                    //cursosPosibles.remove(c1);
                }
            }
        }
        cursosPosibles.removeAll(cursosABorrar);

        cursosABorrar = new ArrayList<Curso>();
        for(Curso c1 : cursosPosibles){
            for(Curso c2 : estudiante.getCursosVistos()){
                if(c1.getNombre().equals(c2.getNombre())){
                    cursosABorrar.add(c1);
                    //cursosPosibles.remove(c1);
                }
            }
        }
        cursosPosibles.removeAll(cursosABorrar);
        
        while(true){
            boolean comp = false;
            ArrayList<Curso> cursosres = (ArrayList<Curso>) cursosPosibles.clone();
            for(Curso curso : listaCursos){
                for(Curso c : cursosPosibles){
                    if(c.getNombre().equals(curso.getNombre())){
                        cursosres.remove(cursosres.indexOf(c));
                    }
                }
            }
            if(cursosres.isEmpty() == true){
                System.out.println("No hay cursos disponibles");
                break;
            }   
            System.out.println("Seleccione un curso que quiera inscribir: ");

            int cont = 1;
            System.out.println("Los cursos disponibles son:\n"
            + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            for(Curso curso : cursosres){
                System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+curso.getFacultad()+"\t"+curso.getCarrerasRelacionadas());
                System.out.println(cont + ". Inscribir");
                cont++;
            }
            System.out.println(cont+". No agregar más cursos");
            while(true){
                boolean comp1 = true;
                String opcion = sc.next();
                if(opcion.equals(String.valueOf(cont))){
                    if(listaCursos.isEmpty()){
                        System.out.println("No has inscrito ningún curso");
                        break;
                    }
                    else{
                        comp = true;
                        break;
                    }
                }
                for(int x=1; x<=cursosres.size();x++){
                    if(opcion.equals(String.valueOf(x))){
                        Curso curso = cursosres.get(x-1);
                        CursoEstudiante ce = mostrarDetalles(estudiante, curso);
                        listaCursos.add(ce);
                        comp1 = false;
                        break;
                    }
                }
                if(comp1 == true){
                    System.out.println("Debe seleccionar un número entre el 1 y el "+cont);
                    continue;
                }
                break;
            }
            if(comp == true){
                break;
            }
        }
        Horario horario = new Horario(listaCursos);
        if(horario.validarHorario(estudiante)){
            estudiante.inscribirCursos(listaCursos);
            System.out.println("La inscripción fue exitosa");
        }
        else{
            System.out.println("Los cursos seleccionados presentan inconsistencias (hay horarios cruzados o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.");
            inscribirMaterias(estudiante);
        }
    }
    
    public static void inscribirConHorario(Estudiante estudiante){
        Scanner sc = new Scanner(System.in);
        if(estudiante.getHorariosCreados().isEmpty()){
            System.out.println("No ha creado ningún horario, debes inscribir manualmente");
            inscribirManualmente(estudiante);
        }
        else{
            while(true){
                System.out.println("Seleccione el horario que desea inscribir: ");
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
                        Horario horario = estudiante.getHorariosCreados().get(x-1);
                        if(horario.validarHorario(estudiante)){
                            estudiante.inscribirCursos(horario.getCursos());
                            System.out.println("La inscripción fue exitosa");
                        }
                        else{
                            System.out.println("Los cursos seleccionados presentan inconsistencias (hay horarios cruzados, el horario contiene cursos que ya aprobaste o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.");
                            inscribirMaterias(estudiante);
                        }
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
    
    public static CursoEstudiante mostrarDetalles(Estudiante estudiante, Curso curso){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            ArrayList<CursoEstudiante> listaCursos = curso.obtenerGrupos(estudiante);
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas());
            int cont = 1;
            for(CursoEstudiante ce : listaCursos){
                System.out.println("Profesor: "+ce.getProfesor()+"\nHorario: "+ce.getHorario()+"\nCupos: "+ce.getCupos());
                System.out.println(cont+". Inscribir");
                cont++;
            }
            System.out.println(cont+". volver");
            String opcion = sc.next();
            if(opcion.equals(String.valueOf(cont))){
                inscribirManualmente(estudiante);
            }
            boolean comp = true;
            for(int x = 1; x<=cont; x++){
                if(opcion.equals(String.valueOf(x))){
                    if(!(curso.obtenerGrupos().isEmpty()) && listaCursos.get(x-1).getCupos()>0){
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("El curso se agregó correctamente");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                        return listaCursos.get(x-1);
                    }
                    else{
                        System.out.println("El curso seleccionado no tiene profesores asignados o no tiene cupos");
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
}
