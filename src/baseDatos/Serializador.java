package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import gestorAplicacion.Registro;

public class Serializador {
    private static File temp = new File("src\\baseDatos\\temp");
    
    public static void serializador(){
        FileOutputStream fos;
        ObjectOutputStream oos;
        File[] docs = temp.listFiles();
        PrintWriter pw;
        
        for(File f : docs){
            
            try{
                pw = new PrintWriter(f);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }      
        }
        for(File f : docs){
            
            if(f.getAbsolutePath().contains("profesores")){
                try{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Registro.getProfesores());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("estudiantes")){
                try{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Registro.getEstudiantes());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("admins")){
                try{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Registro.getAdmins());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("cursos")){
                try{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Registro.getCursos());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if(f.getAbsolutePath().contains("estudiantesMatriculados")){
                try{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Registro.getEstudiantesMatriculados());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
