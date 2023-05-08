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
        ArrayList<Integer> quiz = new ArrayList();
        ArrayList<Integer> parcial = new ArrayList();
        ArrayList<Integer> seguimiento = new ArrayList();
        for(int[] nota : listaNotas){
            if(nota[0]==1){
                parcial.add(nota[1]);
            }
            else if(nota[0]==2){
                seguimiento.add(nota[1]);
            }
            else{
                quiz.add(nota[1]);
            }
        }
        int pquiz = 0;
        ArrayList<Integer> pparcial = new ArrayList();
        int pseguimiento = 0;
        for(int[] porcentaje : getListaPorcentajes()){
            if(porcentaje[0]==1){
                pparcial.add(porcentaje[1]);
            }
            else if(porcentaje[0]==2){
                pseguimiento = porcentaje[1];
            }
            else{
                pquiz = porcentaje[1];
            }
        }
        int sum = 0;
        int seg = 0;
        int qui = 0;
        int par = 0;
        if(!seguimiento.isEmpty()){
            for(int x : seguimiento){
                sum+=x;
            }
            seg = sum*pseguimiento/(seguimiento.size());
        }
        sum = 0;
        if(!quiz.isEmpty()){
            for(int x : quiz){
                sum+=x;
            }
            qui = sum*pquiz/(quiz.size());
        }
        sum = 0;
        int cont = 0;
        if(!parcial.isEmpty()){
            for(int x : parcial){
                sum+=x*pparcial.get(cont);
                cont++;
            }
            par = sum;
        }
        int total = seg+qui+par;
        return total;
    }
}
