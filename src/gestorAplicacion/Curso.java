package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    private static final long serialVersionUID = 5L;
    private static int cursosExistentes = 0;
    private String nombre;
    private int id;
    private int cupos;
    private int creditos;
    static private ArrayList<String> horariosClase;
    private int numeroParciales;
    private ArrayList<int[]> listaPorcentajes;
    private ArrayList<Curso> preRequisitos;
    private ArrayList<Carreras> carrerasRelacionadas;
    private ArrayList<Profesor> profesoresQueDictanElCurso;
    private ArrayList<Facultades> facultades;


    public Curso(String nombre, int cupos, int creditos,
        int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultades) {
      this.nombre = nombre;
      this.id = 100000 + cursosExistentes;
      this.cupos = cupos;
      this.creditos = creditos;
      this.numeroParciales = numeroParciales;
      this.listaPorcentajes = listaPorcentajes;
      this.preRequisitos = preRequisitos;
      this.carrerasRelacionadas = carrerasRelacionadas;
      this.profesoresQueDictanElCurso = profesoresQueDictanElCurso;
      this.facultades = facultades;
      Curso.cursosExistentes++;
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

    public int getCupos() {
      return cupos;
    }

    public void setCupos(short cupos) {
      this.cupos = cupos;
    }

    public int getCreditos() {
      return creditos;
    }

    public void setCreditos(short creditos) {
      this.creditos = creditos;
    }
    
    

    public void setHorariosClase(ArrayList<String> horariosClase) {
        Curso.horariosClase = horariosClase;
    }
    
    public void agregarHorario(String horario){
        Curso.horariosClase.add(horario);
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

    public ArrayList<Facultades> getFacultades() {
      return facultades;
    }

    public void setFacultades(ArrayList<Facultades> facultad) {
      this.facultades = facultad;
    }
}
