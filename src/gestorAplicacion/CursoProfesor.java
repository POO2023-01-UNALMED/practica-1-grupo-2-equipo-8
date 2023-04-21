package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private static final long serialVersionUID = 7L;
    private ArrayList<Estudiante> listaEstudiantes;

    public CursoProfesor(String nombre, int id, short cupos, short creditos, ArrayList<String> horariosClase,
        int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultad, ArrayList<Estudiante> listaEstudiantes) {
      super(nombre, id, cupos, creditos, horariosClase, numeroParciales, listaPorcentajes, preRequisitos,
          carrerasRelacionadas, profesoresQueDictanElCurso, facultad);
      this.listaEstudiantes = listaEstudiantes;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
      return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
      this.listaEstudiantes = listaEstudiantes;
    }
}
