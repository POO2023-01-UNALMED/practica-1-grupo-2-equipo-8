package gestorAplicacion;

import java.util.ArrayList;
import java.util.Map;

public class Curso{
    private String nombre;
    private int id;
    private short cupos;
    private short creditos;
    private ArrayList<String> horariosClase;
    private int numeroParciales;
    private int seguimiento;
    private Map<tipoCalificacion, short> listaPorcentajes;
    private ArrayList<Curso> preRequisitos;
    private ArrayList<carreras> carrerasRelacionadas;
    private ArrayList<Profesor> profesoresQueDictanElCurso;
    private ArrayList<facultades> facultad;


    public Curso(int id, String nombre, short cupos, short creditos) {
      this.id = id;
      this.nombre = nombre;
      this.cupos = cupos;
      this.creditos = creditos;
    }

    public int getId() {
      return id;
    }
}
