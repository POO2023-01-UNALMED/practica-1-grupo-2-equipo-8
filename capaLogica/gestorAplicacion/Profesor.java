package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;

public class Profesor extends Registro{
    private static final long serialVersionUID = 2L;
    private ArrayList<CursoProfesor> listaCursos;
    private Facultad facultad;

    public Profesor(String nombre, String correo, String nombreUsuario, String clave, String documento, Facultad facultad) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.listaCursos = new ArrayList<CursoProfesor>();
        this.facultad = facultad;
    }

    public ArrayList<CursoProfesor> getCursosDictados() {
      return listaCursos;
    }

    public Factultad getFacultad() {
      return facultad;
    }

    public boolean agregarNota(Estudiante estudiante, Curso curso, int indiceNota, int indiceTipoNota, int nota) {
      if(!validarExistenciaCurso(curso)) return 
      // TODO: mire si el estuainte existe entre los estudiantes de ese curso 
      // TODO: mire si la nota es valdia, crear metodo estatico para validar de forma general

      ArrayList<CursoEstudiante> cursosEstudiante = estudiante.getListaCursos();
      CursoEstudiante cursoEstudianteAModificar;
      
      if(indiceTipoNota < 0 && indiceTipoNota >= cursoEstudianteAModificar.getListaNotas().size()) return false;
      if(indiceNota < 0 && indiceNota >= cursoEstudianteAModificar.getListaNotas().get(indiceTipoNota).size()) return false;
      
      ArrayList<int[]> notasCursoEstudiante = cursoEstudianteAModificar.getListaNotas().get(indiceTipoNota)[indiceNota];
      //notasCursoEstudiante.set(1, [nota]);
      return true;
    }

    public boolean editarNota(Estudiante estudiante, Curso curso, int nota) {
      // mire si el profesor dicta ese curso
      // mire si el estuainte existe entre los estudiantes de ese curso 
      // cambie la nota 
    }

    private boolean validarExistenciaCurso(Curso curso) {
      for(CursoProfesor cursoActual: listaCursos) {
        if(cursoActual.getId() == curso.getId()) return true;
      }
      return false;
    }
}