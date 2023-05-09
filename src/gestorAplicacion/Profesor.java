package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;
import uiMain.BusquedaCursos;
import uiMain.Helpers;

public class Profesor extends Registro{
    private static final long serialVersionUID = 2L;
    private ArrayList<CursoProfesor> listaCursos = new ArrayList<CursoProfesor>();
    private Facultades facultad;
    private ArrayList<Double> calificaciones = new ArrayList<Double>();
    private double calificacion;

    public Profesor(String nombre, String correo, String nombreUsuario, String clave, String documento, ArrayList<CursoProfesor> listaCursos, Facultades facultad) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.listaCursos = listaCursos;
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
    
    public void agregarCurso(ArrayList<Curso> listaCursos, ArrayList<String> listaHorarios){ //Método único para crear profesores en el menú
        for(int x = 0; x<listaCursos.size(); x++){
            Curso curso = listaCursos.get(x);
            String horario = listaHorarios.get(x);
            CursoProfesor cp = new CursoProfesor(curso.getNombre(), curso.getId(), (short)curso.getCreditos(), curso.getNumeroParciales(), curso.getListaPorcentajes(), curso.getFacultad(), horario);
            this.listaCursos.add(cp);
            curso.agregarHorario(horario);
            curso.setCupos((short)(curso.getCupos()+5));
        }
    }

    private boolean validarExistenciaCurso(Curso curso) {
      for(CursoProfesor cursoActual: listaCursos) {
        if(cursoActual.getId() == curso.getId()) return true;
      }
      return false;
    }

    public void calificar(double valoracion) {
      calificaciones.add(valoracion);
      calificacion = Helpers.promedioLista(calificaciones);
    }
    
    @Override
    public void buscarCursos(){
        BusquedaCursos.buscarCursos();
    }

    public String toString() {
      return this.getNombre() + " (" + this.getCalificacion() + ")";
    }
}