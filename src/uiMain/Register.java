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
            sc.nextLine();
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
        String nombre;
        String nombreUsuario;
        while(true){
            boolean comp = true;
            System.out.println("Nombre: ");
            nombre = sc.nextLine();
            System.out.println("Nombre de usuario: ".replaceAll("\n", ""));
            nombreUsuario = sc.nextLine();
            if(tu.tipoUsuario().equals("Estudiante")){
                for(Estudiante us : Registro.getEstudiantes()){
                    if(us.getNombreUsuario().equals(nombreUsuario) || us.getNombre().equals(nombre)){
                        System.out.println("El Nombre o el nombre de usuario ya existen");
                        comp = false;
                        break;
                    }
                }
            }
            else if(tu.equals("Profesor")){
                for(Profesor us : Registro.getProfesores()){
                    if(us.getNombreUsuario().equals(nombreUsuario) || us.getNombre().equals(nombre)){
                        System.out.println("El Nombre o el nombre de usuario ya existen");
                        comp = false;
                        break;
                    }
                }
            }
            else if(tu.equals("Admin")){
                for(Admin us : Registro.getAdmins()){
                    if(us.getNombreUsuario().equals(nombreUsuario) || us.getNombre().equals(nombre)){
                        System.out.println("El Nombre o el nombre de usuario ya existen");
                        comp = false;
                        break;
                    }
                }
            }
            if(comp == true){
                break;
            }
        }
        System.out.println("clave: ");
        String clave = sc.next();
        System.out.println("Correo: ");
        String correo = sc.next();
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
            Estudiante estudiante = new Estudiante(nombre, correo, nombreUsuario, clave, documento, carrera, facultad, semestre);
            Menu.sistema(estudiante);
        }
        
        else if(tu.tipoUsuario().equals("Profesor")){
            ArrayList<CursoProfesor> listaCursos = new ArrayList();
            System.out.println("Cursos que dicta\n"); // Agregar cursos a un profesor puede ser una funcionalidad aparte
            
            while(true){
                boolean comp = false;
                ArrayList<Curso> cursosres = (ArrayList<Curso>) Registro.getCursos().clone();
                for(CursoProfesor curso : listaCursos){
                    for(Curso c : Registro.getCursos()){
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
                System.out.println(cont+". No agregar más cursos");
                while(true){
                    boolean comp1 = true;
                    String opcion = sc.next();
                    sc.nextLine();
                    if(opcion.equals(String.valueOf(cont))){
                        comp = true;
                        break;
                        
                    }
                    CursoProfesor cp;
                    for(int x=1; x<=cursosres.size();x++){
                        if(opcion.equals(String.valueOf(x))){
                            System.out.println("En qué horario dicta el curso (formato 01:00 - 24:00) Ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00");
                            String horario = sc.nextLine();
                            Curso curso = cursosres.get(x-1);
                            cp = new CursoProfesor(curso.getNombre(), curso.getId(), curso.getCreditos(), curso.getNumeroParciales(), curso.getListaPorcentajes(), curso.getFacultad(), horario);
                            listaCursos.add(cp);
                            curso.agregarHorario(horario);
                            curso.setCupos((short)(curso.getCupos()+5));
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
            Profesor profesor = new Profesor(nombre, correo, nombreUsuario, clave, documento, listaCursos, facultad);
            for(CursoProfesor cp : listaCursos){
                for(Curso curso : Registro.getCursos()){
                    if(cp.getNombre().equals(curso.getNombre())){
                        curso.agregarProfesor(profesor);
                    }
                }
            }
            Menu.sistema(profesor);
        }
        
        else if(tu.tipoUsuario().equals("Admin")){
            Admin admin = new Admin(nombre, correo, nombreUsuario, clave, documento);
            Menu.sistema(admin);
        }
    }
}
