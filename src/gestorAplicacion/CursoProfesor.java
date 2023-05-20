package gestorAplicacion;

import java.util.ArrayList;

public class CursoProfesor extends Curso {
    private static final long serialVersionUID = 7L;
    private ArrayList<Estudiante> listaEstudiantes;
    private String horario;
    private short cupos = 10;
    private static ArrayList<CursoProfesor> cursosCreados = new ArrayList<CursoProfesor>();

    /*
    public CursoProfesor(String nombre, short cupos, short creditos,
        int numeroParciales, ArrayList<int[]> listaPorcentajes, ArrayList<Curso> preRequisitos,
        ArrayList<Carreras> carrerasRelacionadas, ArrayList<Profesor> profesoresQueDictanElCurso,
        ArrayList<Facultades> facultad, String horario) {
      super(nombre, cupos, creditos, numeroParciales, listaPorcentajes, preRequisitos,
          carrerasRelacionadas, profesoresQueDictanElCurso, facultad);
      this.horario = horario;
    }
    */

    // Sobrecarga para agregarCurso() en clase Profesor
    public CursoProfesor(String nombre, int id, short creditos,
                        int numeroParciales, ArrayList<int[]> listaPorcentajes,
                        ArrayList<Facultades> facultad, String horario) {
      super(nombre, id, creditos, numeroParciales, listaPorcentajes, facultad);
      this.horario = horario;
      CursoProfesor.cursosCreados.add(this);
    }
    
    public void resetearCurso(){
        this.cupos = 10;
        this.listaEstudiantes = new ArrayList<Estudiante>();
    }

    public static ArrayList<CursoProfesor> getCursosCreados() {
        return cursosCreados;
    }

    public static void setCursosCreados(ArrayList<CursoProfesor> cursos){
        CursoProfesor.cursosCreados = cursos;
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
    public short getCupos() {
        return cupos;
    }
    
    
}
