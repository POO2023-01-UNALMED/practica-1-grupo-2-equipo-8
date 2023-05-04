package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private static final long serialVersionUID = 7L;
    private ArrayList<Estudiante> listaEstudiantes;
    private String horario;
    private int cupos = 5;

    public CursoProfesor(String nombre, int cupos, int creditos,
        int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultad, String horario) {
      super(nombre, cupos, creditos, numeroParciales, listaPorcentajes, preRequisitos,
          carrerasRelacionadas, profesoresQueDictanElCurso, facultad);
      this.horario = horario;
    }
    
    public void resetearCurso(){
        this.cupos = 5;
        this.listaEstudiantes = new ArrayList<Estudiante>();
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
      return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        this.listaEstudiantes.add(estudiante);
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    @Override
    public int getCupos() {
        return cupos;
    }
    
    
}
