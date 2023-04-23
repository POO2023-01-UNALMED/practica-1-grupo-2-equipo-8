package uiMain;

import java.util.Scanner;
import baseDatos.Serializador;
import gestorAplicacion.Registro;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args){
        Registro registro = new Registro();
        Login.login();
        Serializador.serializador();
    }
}
