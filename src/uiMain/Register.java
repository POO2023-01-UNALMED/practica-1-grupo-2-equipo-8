package uiMain;

import gestorAplicacion.Admin;
import gestorAplicacion.Carreras;
import gestorAplicacion.Curso;
import gestorAplicacion.CursoProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Facultades;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import gestorAplicacion.TipoUsuarios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Register {
    public static void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Antes de crear una cuenta, debe indicar su tipo de ususario");
        TipoUsuarios tu = null;
        while(true){
            System.out.println("Tipo de usuario:\n"
                    + "1. Estudiante\n"
                    + "2. Profesor\n"
                    + "3. Admin");
            String opcion = sc.next();
            if(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                continue;
            }
            switch(opcion){
                case "1": tu = TipoUsuarios.ESTUDIANTE;break;
                case "2": tu = TipoUsuarios.PROFESOR; break;
                case "3": tu = TipoUsuarios.ADMIN; break;
            }
            break;
        }
        System.out.println("Nombre: ");
        String nombre = sc.next();
        System.out.println("Correo: ");
        String correo = sc.next();
        System.out.println("Nombre de usuario: ");
        String nombreUsuario = sc.next();
        System.out.println("clave: ");
        String clave = sc.next();
        System.out.println("documento: ");
        String documento;
        while(true){
            boolean comp = false;
            documento = sc.next();
            String[] lis = {"1","2","3","4","5","6","7","8","9","0"};
            for(int x = 0;x<documento.length();x++){
               if(!(Arrays.asList(lis).contains(String.valueOf(documento.charAt(x))))){
                    System.out.println("El documento indicado es incorrecto");
                    comp = true;
                    break;
                } 
            }
            if(comp == true){
                continue;
            }
            break;
        }
        
        if(tu.tipoUsuario().equals("Estudiante")){
            Carreras carrera = null;
            while(true){
                System.out.println("Carrera:\n"
                    + "1. Ingenieria de Sistemas\n"
                    + "2. Ciencias de la Computacion");
                String opcion = sc.next();
                if(!opcion.equals("1") && !opcion.equals("2")){
                    System.out.println("Debe seleccionar un número entre el 1 y el 2");
                    continue;
                }
                switch(opcion){
                    case "1": carrera = Carreras.SISTEMAS; break;
                    case "2": carrera = Carreras.COMPUTACION; break;
                }
                break;
            }
            Facultades facultad = carrera.getFacultad();
            int semestre = 0;
            while(true){
                System.out.println("Semestre:\n"
                    + "1\n"
                    + "2\n"
                    + "3\n"
                    + "4\n"
                    + "5");
                String opcion = sc.next();
                if(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4") && !opcion.equals("5")){
                    System.out.println("Debe seleccionar un número entre el 1 y el 5");
                    continue;
                }
                switch(opcion){
                    case "1": semestre = 1; break;
                    case "2": semestre = 2; break;
                    case "3": semestre = 3; break;
                    case "4": semestre = 4; break;
                    case "5": semestre = 5; break;
                }
                break;
            }
            Registro.agregarEstudiante(new Estudiante(nombre, correo, nombreUsuario, clave, documento, carrera, facultad, semestre));
        }
        
        if(tu.tipoUsuario().equals("Profesor")){
            ArrayList<CursoProfesor> listaCursos = new ArrayList();
            System.out.println("Cursos que dicta\n"); // Agregar cursos a un profesor puede ser una funcionalidad aparte
            
            while(true){
                ArrayList<Curso> cursosres = (ArrayList<Curso>) Registro.getCursos().clone();
                for(Curso curso : listaCursos){
                    for(Curso c : cursosres){
                        if(c.getNombre().equals(curso.getNombre())){
                            cursosres.remove(cursosres.indexOf(c));
                        }
                    }
                }
                if(cursosres.isEmpty() == true){
                    System.out.println("No hay cursos disponibles");
                    break;
                }   
                System.out.println("Seleccione un curso que dicte: ");

                int cont = 1;
                for(Curso curso : cursosres){
                    System.out.println(cont+". "+curso.getNombre());
                    cont++;
                }
                System.out.println(cont+". No agregar mas cursos");
                while(true){
                    String opcion = sc.next();
                    if(opcion.equals(String.valueOf(cont))){
                        break;
                    }
                    CursoProfesor cp;
                    int cont1 = 0;
                    for(int x=1; x<=cursosres.size();x++){
                        if(opcion.equals(String.valueOf(x))){
                            System.out.println("En qué horario dicta el curso (formato 01:00 - 24:00) Ejemplo: 12:00-14:00");
                            String horario = sc.next();
                            Curso curso = Registro.getCursos().get(x-1);
                            cp = new CursoProfesor(curso.getNombre(), curso.getId(), curso.getCupos(), curso.getCreditos(), curso.getNumeroParciales(), curso.getListaPorcentajes(), curso.getPreRequisitos(),
                            curso.getCarrerasRelacionadas(), curso.getProfesoresQueDictanElCurso(), curso.getFacultad(), horario);
                            listaCursos.add(cp);
                            curso.agregarHorario(horario);
                            break;
                        }
                        cont1 = x;
                    }
                    if(cont1>Registro.getCursos().size()){
                        System.out.println("Debe seleccionar un número entre el 1 y el "+cont);
                        continue;
                    }
                    break;
                }
            }
           
            Facultades facultad = null;
            while(true){
                System.out.println("Facultad:\n"
                    + "1. Ciencias\n"
                    + "2. Minas");
                String opcion = sc.next();
                if(!opcion.equals("1") && !opcion.equals("2")){
                    System.out.println("Debe seleccionar un número entre el 1 y el 2");
                    continue;
                }
                switch(opcion){
                    case "1": facultad = Facultades.CIENCIAS; break;
                    case "2": facultad = Facultades.MINAS; break;
                }
                break;
            }
            Registro.agregarProfesor(new Profesor(nombre, correo, nombreUsuario, clave, documento, listaCursos, facultad));
        }
        
        if(tu.tipoUsuario().equals("Admin")){
            Registro.agregarAdmin(new Admin(nombre, correo, nombreUsuario, clave, documento));
        }
    }
}
