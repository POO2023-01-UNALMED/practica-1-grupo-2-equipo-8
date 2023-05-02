package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class EstimuloProfesor extends Estimulo {
  private ArrayList<Curso> materiasImpartidas;

  public EstimuloEstudiante(
    String nombre,
    String descripcion,
    TipoUsuarios aQuienAplica, 
    List<Facultades> facultadesAplica,
    int cupos,
    List<Curso> materiasImpartidas
  ) {
    super(nombre, descripcion, aQuienAplica, facultadesAplica, cupos);
    this.materiasImpartidas = materiasImpartidas;
  }

  // metodos
  public void obtenerCriterios() {
    ArrayList<String> criterios;

    String materias = "Materias: [";    
    for(Curso curso: materiasImpartidas) {
      materias += curso.getNombre() + ", ";
    }
    materias += "]"
    
    criterio.add("Facultad: " + this.facultad);
    criterio.add(materias);
    criterio.add("Cupos: " + this.cupos);
    criterio.add("Tipo usuario: Profesor");

    return criterios;
  }

  public void obtenerAplicantes() {
    // buscar personas que cumplan los requisitos
  }

  public boolean verificarRequisitos(Profesor profesor) {
    ArrayList<String> razones = new ArrayList<>(); 
    boolean cumpleRequisitos = true; 

    if (this.cupos <= 0) {
      razones.add("Ya no quedan cupos para este estímulo");
      cumpleRequisitos = false;
    }

    if (!this.facultadesAplica.contains(profesor.getFacultad())) {
      razones.add("Este estímulo no aplica a tu facultad");
      cumpleRequisitos = false;
    }

    // Validar que se hayn impartido las materias requisito

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
