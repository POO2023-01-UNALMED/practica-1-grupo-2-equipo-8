package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    private static final long serialVersionUID = 5L;
    private String nombre;
    private int id;
    private short cupos;
    private short creditos;
    private ArrayList<String> horariosClase;
    private int numeroParciales;
    private int seguimiento;
    private ArrayList<int[]> listaPorcentajes;
    private ArrayList<Curso> preRequisitos;
    private ArrayList<Carreras> carrerasRelacionadas;
    private ArrayList<Profesor> profesoresQueDictanElCurso;
    private ArrayList<Facultades> facultad;


    public Curso(String nombre, int id, short cupos, short creditos, ArrayList<String> horariosClase,
        int numeroParciales, int seguimiento, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultad) {
      this.nombre = nombre;
      this.id = id;
      this.cupos = cupos;
      this.creditos = creditos;
      this.horariosClase = horariosClase;
      this.numeroParciales = numeroParciales;
      this.seguimiento = seguimiento;
      this.listaPorcentajes = listaPorcentajes;
      this.preRequisitos = preRequisitos;
      this.carrerasRelacionadas = carrerasRelacionadas;
      this.profesoresQueDictanElCurso = profesoresQueDictanElCurso;
      this.facultad = facultad;
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

    public int getNumeroParciales() {
      return numeroParciales;
    }

    public void setNumeroParciales(int numeroParciales) {
      this.numeroParciales = numeroParciales;
    }

    public int getSeguimiento() {
      return seguimiento;
    }

    public void setSeguimiento(int seguimiento) {
      this.seguimiento = seguimiento;
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

    public ArrayList<Facultades> getFacultad() {
      return facultad;
    }

    public void setFacultad(ArrayList<Facultades> facultad) {
      this.facultad = facultad;
    }
}
