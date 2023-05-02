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
  public void verEstimulos() {

  }

  public void verEstimulos(String id) {
      
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
