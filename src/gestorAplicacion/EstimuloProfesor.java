package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class EstimuloProfesor extends Estimulo implements Serializable {
  private static final long serialVersionUID = 11L;
  private ArrayList<Integer> materiasImpartidas;

  public EstimuloProfesor(
    String nombre,
    String descripcion,
    TipoUsuarios aQuienAplica, 
    ArrayList<Facultades> facultadesAplica,
    int cupos,
    ArrayList<Integer> materiasImpartidas
  ) {
    super(nombre, descripcion, aQuienAplica, facultadesAplica, cupos);
    this.materiasImpartidas = materiasImpartidas;
  }

  // metodos
  public ArrayList<String> obtenerCriterios() {
    ArrayList<String> criterios = new ArrayList<>();

    String materias = "Materias: [";    
    for(Integer cursoId: materiasImpartidas) {
      for(Curso curso: Registro.getCursos()) {
        if(curso.getId() == cursoId) {
          materias += curso.getNombre() + ", ";
        }
      }
    }
    materias += "]";
    
    String facultades = "Facultad: [";
    for(Facultades facultad: getFacultadesAplica()) {
    	facultades += facultad.getNombre() + ", ";
    }
    facultades += "]";

    criterios.add(facultades);
    criterios.add(materias);
    criterios.add("Cupos: " + this.getCupos());
    criterios.add("Tipo usuario: Profesor");

    return criterios;
  }

  public ArrayList<Profesor> obtenerAplicantes() {
	  ArrayList<Profesor> profesores = new ArrayList<>();

	  for(Profesor profesor: Registro.getProfesores()) {
		  if(verificarRequisitos(profesor)) {
			  profesores.add(profesor);
		  }
	  }

	  return profesores;
  }

  public boolean verificarRequisitos(Profesor profesor) {
    boolean cumpleRequisitos = true; 

    if (this.getCupos() <= 0) {
      cumpleRequisitos = false;
    }

    if (!this.getFacultadesAplica().contains(profesor.getFacultad())) {
      cumpleRequisitos = false;
    }
    
    for(Integer cursoId: materiasImpartidas) {
      for(Curso curso: Registro.getCursos()) {
        if(curso.getId() != cursoId) continue;
        if(!profesor.validarExistenciaCurso(curso)) {
          cumpleRequisitos = false;
        }
      }
    }

    return cumpleRequisitos;
  }

  // getters
  public ArrayList<Integer> getMateriasImpartidas() {
    return materiasImpartidas;
  }
  
  // setters
  public void setMateriasImpartidas(ArrayList<Integer> materiasImpartidas) {
    this.materiasImpartidas = materiasImpartidas;
  }
}
