package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private static final long serialVersionUID = 7L;
    private ArrayList<Estudiante> listaEstudiantes;

    public CursoProfesor(int id, String nombre, short cupos, short creditos) {
      super(id, nombre, cupos, creditos);
      this.listaEstudiantes = new ArrayList();
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
      return listaEstudiantes;
    }
}
