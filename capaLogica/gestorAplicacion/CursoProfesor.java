package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private ArrayList<Estudiante> listaEstudiantes;

    public CursoProfesor(String nombre, short cupos, short creditos) {
      super(nombre, cupos, creditos);
      this.listaEstudiantes = new ArrayList();
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
      return listaEstudiantes;
    }
}
