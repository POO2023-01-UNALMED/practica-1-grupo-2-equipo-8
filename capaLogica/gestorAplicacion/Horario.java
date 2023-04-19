package gestorAplicacion;

import java.util.ArrayList;

public class Horario {
    private int id;
    private Estudiante estudiante;
    private ArrayList<Curso> cursos = new ArrayList<>();
    private static int count;

    //Constructores
    public Horario(Estudiante estudiante, ArrayList<Curso> cursos){
        this.estudiante = estudiante;
        this.cursos = cursos;
        Horario.count++;
        this.id = Horario.count;
    }

    public Horario(int id, Estudiante estudiante, ArrayList<Curso> cursos){
        this.estudiante = estudiante;
        this.cursos = cursos;
        this.id = id;
    }

    //Metodos

    public void mostrar(){
        // Provicional mientras se define bien la clase Curso
        for (Curso curso : this.cursos) {
            System.out.println("-----------------------------------------");
            System.out.println(curso.getNombre() + ": " + curso.getHorariosClase());
        }
        System.out.println("-----------------------------------------");
    }
    

    public Boolean validarDisponibilidad(){
        for (Curso curso1 : this.cursos){
            for (String horario1 : curso1.getHorariosClase()){
                for (Curso curso2 : this.cursos){
                    for (String horario2 : curso1.getHorariosClase()){
                        if (horario1 == horario2){
                            return false;
                        }
                    }
                }
            }
            
        }
        return true;
    }

    //Sets y Gets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    }