package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;

public class Profesor extends Registro{
    private static final long serialVersionUID = 2L;
    private ArrayList<CursoProfesor> listaCursos;
    private Facultades facultad;

    public Profesor(String nombre, String correo, String nombreUsuario, String clave, String documento, Facultades facultad) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.listaCursos = new ArrayList<CursoProfesor>();
        this.facultad = facultad;
    }

    public ArrayList<CursoProfesor> getCursosDictados() {
      return listaCursos;
    }

    public Facultades getFacultad() {
      return facultad;
    }

    public boolean agregarNota(Estudiante estudiante, Curso curso, int indiceNota, int indiceTipoNota, int nota) {
      if(!validarExistenciaCurso(curso)) return false;
      // TODO: mire si el estuainte existe entre los estudiantes de ese curso 
      // TODO: mire si la nota es valdia, crear metodo estatico para validar de forma general
      return true;
    }

    public boolean editarNota(Estudiante estudiante, Curso curso, int nota) {
      if(!validarExistenciaCurso(curso)) return false;
      // TODO: mire si el estuainte existe entre los estudiantes de ese curso 
      // TODO: mire si la nota es valdia, crear metodo estatico para validar de forma general
      return true;
    }

    private boolean validarExistenciaCurso(Curso curso) {
      for(CursoProfesor cursoActual: listaCursos) {
        if(cursoActual.getId() == curso.getId()) return true;
      }
      return false;
    }
}