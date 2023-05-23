package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class EstimuloProfesor extends Estimulo {
  private ArrayList<Curso> materiasImpartidas;

  public EstimuloProfesor(
    String nombre,
    String descripcion,
    TipoUsuarios aQuienAplica, 
    ArrayList<Facultades> facultadesAplica,
    int cupos,
    ArrayList<Curso> materiasImpartidas
  ) {
    super(nombre, descripcion, aQuienAplica, facultadesAplica, cupos);
    this.materiasImpartidas = materiasImpartidas;
  }

  // metodos
  public ArrayList<String> obtenerCriterios() {
    ArrayList<String> criterios = new ArrayList<>();

    String materias = "Materias: [";    
    for(Curso curso: materiasImpartidas) {
    	materias += curso.getNombre() + ", ";
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
    ArrayList<String> razones = new ArrayList<>(); 
    boolean cumpleRequisitos = true; 

    if (this.getCupos() <= 0) {
      razones.add("Ya no quedan cupos para este estímulo");
      cumpleRequisitos = false;
    }

    if (!this.getFacultadesAplica().contains(profesor.getFacultad())) {
      razones.add("Este estímulo no aplica a tu facultad");
      cumpleRequisitos = false;
    }
    
    
    for(Curso curso: materiasImpartidas) {
    	if(!profesor.validarExistenciaCurso(curso)) {
    		razones.add("No ha impartido el curso: " + curso.getNombre());
	      cumpleRequisitos = false;
    	}
    }

    if (cumpleRequisitos) {
      System.out.println("Cumples con todos los requisitos para inscribirte");
      return true;
    }

    System.out.println("No puedes aplicar a este estímulo por las siguientes razones:");
    for (String razon : razones) {
      System.out.println(razon);
    }
    return false;
  }

  // getters
  public ArrayList<Curso> getMateriasImpartidas() {
    return materiasImpartidas;
  }
  
  // setters
  public void setMateriasImpartidas(ArrayList<Curso> materiasImpartidas) {
    this.materiasImpartidas = materiasImpartidas;
  }
}
