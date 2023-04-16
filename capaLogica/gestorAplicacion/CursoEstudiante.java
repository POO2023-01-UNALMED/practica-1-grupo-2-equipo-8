package gestorAplicacion;

import java.util.ArrayList;

public class CursoEstudiante extends Curso {
    private ArrayList<int[]> listaNotas;
    private int semestre;
    private Estudiante estudiante;

    public ArrayList getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(int[] nota) {
        listaNotas.add(nota);
    }
    
    public int calcularPromedio(){
        if(semestre != estudiante.getSemestre() && listaNotas.isEmpty() != false){
            int sum = 0;
            for(int[] l: listaNotas){
                sum+=l[3];
            }
            return sum/listaNotas.size();
        }
        return -1; //Hay que corregir la lógica en la capa de UI
    }
    
    public void mostrarNotas(){ //Falta complemento de la UI
        
    }
    
    public void mostrarHorario(){ //Falta complemento de la UI
        
    }
}
