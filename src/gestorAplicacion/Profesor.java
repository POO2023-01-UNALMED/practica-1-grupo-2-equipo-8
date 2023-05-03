package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;

public class Profesor extends Registro{
    private static final long serialVersionUID = 2L;
    private ArrayList<CursoProfesor> listaCursos;
    private Facultades facultad;
    private double calificacion;

    public Profesor(String nombre, String correo, String nombreUsuario, String clave, String documento, ArrayList<CursoProfesor> listaCursos, Facultades facultad) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.listaCursos = new ArrayList<CursoProfesor>();
        this.facultad = facultad;
        this.calificacion = -1;
    }

    public double getCalificacion() {
      return calificacion;
    }

    public void setCalificacion(double calificacion) {
      this.calificacion = calificacion;
    }

    public ArrayList<CursoProfesor> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<CursoProfesor> listaCursos) {
        this.listaCursos = listaCursos;
    }
    
    public void agegarCurso(CursoProfesor curso){
        this.listaCursos.add(curso);
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
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

    public String toString() {
      return this.getNombre() + " (" + this.getCalificacion() + ")";
    }
}