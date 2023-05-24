package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import gestorAplicacion.Registro;
import gestorAplicacion.Profesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Admin;
import gestorAplicacion.Curso;
import gestorAplicacion.Estimulo;
import java.util.ArrayList;

public class Deserializador {
    private static File temp = new File("src\\baseDatos\\temp");
    public static void deserializador(){
        File[] docs = temp.listFiles();
        FileInputStream fis;
        ObjectInputStream ois;
        
        for(File f : docs){
            if(f.getAbsolutePath().contains("profesores")){
                try{
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    
                    Registro.setProfesores((ArrayList<Profesor>) ois.readObject());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("estudiantes")){
                try{
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    
                    Registro.setEstudiantes((ArrayList<Estudiante>) ois.readObject());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("admins")){
                try{
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    
                    Registro.setAdmins((ArrayList<Admin>) ois.readObject());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("cursos")){
                try{
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    
                    Registro.setCursos((ArrayList<Curso>) ois.readObject());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // else if(f.getAbsolutePath().contains("estimulos")){
            //     try{
            //         fis = new FileInputStream(f);
            //         ois = new ObjectInputStream(fis);
                    
            //         Registro.setEstimulos((ArrayList<Estimulo>) ois.readObject());
            //     }
            //     catch (FileNotFoundException e){
            //         e.printStackTrace();
            //     }
            //     catch (IOException e){
            //         e.printStackTrace();
            //     }
            //     catch (ClassNotFoundException e) {
            //         e.printStackTrace();
            //     }
            // }
        }
    }
}
