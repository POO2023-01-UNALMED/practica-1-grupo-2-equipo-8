package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    private static final long serialVersionUID = 5L;
    private String nombre;
    private int id;
    private short cupos;
    private short creditos;
    private ArrayList<String> horariosClase = new ArrayList<String>();
    private int numeroParciales;
    private ArrayList<int[]> listaPorcentajes = new ArrayList<int[]>();
    private ArrayList<Curso> preRequisitos = new ArrayList<Curso>();
    private ArrayList<Carreras> carrerasRelacionadas = new ArrayList<Carreras>();
    private ArrayList<Profesor> profesoresQueDictanElCurso = new ArrayList<Profesor>();
    private ArrayList<Facultades> facultad = new ArrayList<Facultades>();
    private static int cont = 0;


    public Curso(String nombre, short creditos, int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos, ArrayList<Carreras> carrerasRelacionadas, ArrayList<Facultades> facultad) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.numeroParciales = numeroParciales;
        this.listaPorcentajes = listaPorcentajes;
        this.preRequisitos = preRequisitos;
        this.carrerasRelacionadas = carrerasRelacionadas;
        this.facultad = facultad;
        Curso.cont++;
        this.id = Curso.cont;
    }
    
    public Curso(String nombre, int id, short creditos, int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Facultades> facultad){
        this.nombre = nombre;
        this.creditos = creditos;
        this.numeroParciales = numeroParciales;
        this.listaPorcentajes = listaPorcentajes;
        this.facultad = facultad;
        this.id = id;
    }
    
    

    // get y set
    public ArrayList<String> getHorariosClase() {
      return horariosClase;
    }

    public int getId() {
      return id;
    }

    public String getNombre() {
      return nombre;
    }

    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

    public void setId(int id) {
      this.id = id;
    }

    public short getCupos() {
      return cupos;
    }

    public void setCupos(short cupos) {
      this.cupos = cupos;
    }

    public short getCreditos() {
      return creditos;
    }

    public void setCreditos(short creditos) {
      this.creditos = creditos;
    }
    
    

    public void setHorariosClase(ArrayList<String> horariosClase) {
        this.horariosClase = horariosClase;
    }
    
    public void agregarHorario(String horario){
        this.horariosClase.add(horario);
    }
    

    public int getNumeroParciales() {
      return numeroParciales;
    }

    public void setNumeroParciales(int numeroParciales) {
      this.numeroParciales = numeroParciales;
    }

    public ArrayList<int[]> getListaPorcentajes() {
      return listaPorcentajes;
    }

    public void setListaPorcentajes(ArrayList<int[]> listaPorcentajes) {
      this.listaPorcentajes = listaPorcentajes;
    }

    public ArrayList<Curso> getPreRequisitos() {
      return preRequisitos;
    }

    public void setPreRequisitos(ArrayList<Curso> preRequisitos) {
      this.preRequisitos = preRequisitos;
    }

    public ArrayList<Carreras> getCarrerasRelacionadas() {
      return carrerasRelacionadas;
    }

    public void setCarrerasRelacionadas(ArrayList<Carreras> carrerasRelacionadas) {
      this.carrerasRelacionadas = carrerasRelacionadas;
    }

    public ArrayList<Profesor> getProfesoresQueDictanElCurso() {
      return profesoresQueDictanElCurso;
    }

    public void setProfesoresQueDictanElCurso(ArrayList<Profesor> profesoresQueDictanElCurso) {
      this.profesoresQueDictanElCurso = profesoresQueDictanElCurso;
    }
    
    public void agregarProfesor(Profesor profesor){
        this.profesoresQueDictanElCurso.add(profesor);
    }

    public ArrayList<Facultades> getFacultad() {
      return facultad;
    }

    public void setFacultad(ArrayList<Facultades> facultad) {
      this.facultad = facultad;
    }
}
