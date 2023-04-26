package gestorAplicacion;

import java.util.ArrayList;

public class CursoEstudiante extends Curso {
    private static final long serialVersionUID = 6L;
    private ArrayList<int[]> listaNotas;
    private int semestre;
    private Estudiante estudiante;
    private String horario;
    private Profesor profesor;
    private short cupos;

    public CursoEstudiante(String nombre, int id, short cupos, short creditos,
            int numeroParciales, ArrayList<int[]> listaPorcentajes,
            ArrayList<Facultades> facultad, ArrayList<int[]> listaNotas, int semestre, Estudiante estudiante, String horario, Profesor profesor) {
        super(nombre, id, creditos, numeroParciales, listaPorcentajes, facultad);
        this.listaNotas = listaNotas;
        this.semestre = semestre;
        this.estudiante = estudiante;
        this.horario = horario;
        this.profesor = profesor;
        this.cupos = cupos;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    @Override
    public short getCupos(){
        return cupos;
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
