package gestorAplicacion;

import java.util.ArrayList;
import java.util.Map;

public class Curso {
    private String nombre;
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
}
