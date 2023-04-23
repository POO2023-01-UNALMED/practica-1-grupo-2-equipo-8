package uiMain;

import gestorAplicacion.Admin;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.Registro;
import gestorAplicacion.TipoUsuarios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Login {
    static Scanner sc = new Scanner(System.in);
    static void login(){
        System.out.println("Bienvenido, para continuar debe identificarse");
        String opcion;
        while(true){
            System.out.println("Indique lo que desea hacer:\n"
                    + "1. Iniciar sesión (ya tiene un usuario)\n"
                    + "2. Registrase");
            opcion = sc.next();
            if(!(opcion.equals("1")) && !(opcion.equals("2"))){
                System.out.println("Debe seleccionar un número entre el 1 y el 2");
                continue;
            }
            switch(opcion){
                case "1": iniciar(); break;
                case "2": Register.register(); break;
            }
            break;
        }
    }
    static void iniciar(){
        String tu = null;
        String opcion;
        while(true){
            System.out.println("Tipo de usuario:\n"
                    + "1. Estudiante\n"
                    + "2. Profesor\n"
                    + "3. Admin");
            opcion = sc.next();
            if(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
                System.out.println("Debe seleccionar un número entre el 1 y el 3");
                continue;
            }
            switch(opcion){
                case "1": tu = "Estudiante"; break;
                case "2": tu = "Profesor"; break;
                case "3": tu = "Admin"; break;
            }
            break;
        }
        while(true){
            boolean comp = false;
            System.out.println("Nombre de usuario: ");
            String nom = sc.next();
            System.out.println("Clave: ");
            String clav = sc.next();
            if(tu.equals("Estudiante")){
                for(Estudiante us : Registro.getEstudiantes()){
                    if(us.getNombreUsuario().equals(nom) && us.getClave().equals(clav)){
                        comp = true;
                        Menu.sistema(us);
                        break;
                    }
                }
                if(comp == true){
                    break;
                }
                else{
                    System.out.println("El Nombre o la clave no coincide");
                }
            }
            else if(tu.equals("Profesor")){
                for(Profesor us : Registro.getProfesores()){
                    if(us.getNombreUsuario().equals(nom) && us.getClave().equals(clav)){
                        comp = true;
                        Menu.sistema(us);
                        break;
                    }
                }
                if(comp == true){
                    break;
                }
                else{
                    System.out.println("El Nombre o la clave no coincide");
                }
            }
            else if(tu.equals("Admin")){
                for(Admin us : Registro.getAdmins()){
                    if(us.getNombreUsuario().equals(nom) && us.getClave().equals(clav)){
                        comp = true;
                        Menu.sistema(us);
                        break;
                    }
                }
                if(comp == true){
                    break;
                }
                else{
                    System.out.println("El Nombre o la clave no coincide");
                }
            }
        }
        
    }
}
