package gestorAplicacion;

import java.util.ArrayList;

public class CursoEstudiante extends Curso {
    private static final long serialVersionUID = 6L;
    private ArrayList<int[]> listaNotas;
    private int semestre;
    private Estudiante estudiante;
    
    public CursoEstudiante(int id, String nombre, short cupos, short creditos, int semestre, Estudiante estudiante){
        super(id, nombre, cupos, creditos);
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public ArrayList<int[]> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(ArrayList<int[]> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public void añadirNota(int[] nota) {
        listaNotas.add(nota);
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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
