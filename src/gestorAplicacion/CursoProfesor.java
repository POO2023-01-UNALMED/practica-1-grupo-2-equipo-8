package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private static final long serialVersionUID = 7L;
    private static ArrayList<Estudiante> listaEstudiantes;
    private String horario;

    public CursoProfesor(String nombre, int id, short cupos, short creditos,
        int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultad, String horario) {
      super(nombre, id, cupos, creditos, numeroParciales, listaPorcentajes, preRequisitos,
          carrerasRelacionadas, profesoresQueDictanElCurso, facultad);
      this.horario = horario;
    }
    
    

    public ArrayList<Estudiante> getListaEstudiantes() {
      return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        CursoProfesor.listaEstudiantes = listaEstudiantes;
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        CursoProfesor.listaEstudiantes.add(estudiante);
    }
}
