package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class EstimuloEstudiante extends Estimulo {
  private int pbm;
  private int papa;

  public EstimuloEstudiante(
    String nombre,
    String descripcion,
    TipoUsuarios aQuienAplica, 
    List<Facultades> facultadesAplica,
    int cupos,
    int pbm,
    int papa
  ) {
    super(nombre, descripcion, aQuienAplica, facultadesAplica, cupos);
    this.pbm = pbm;
    this.papa = papa;
  }

  // metodos
  public ArrayList<String> obtenerCriterios() {
    ArrayList<String> criterios;

    criterio.add("Facultad: " + this.facultad);
    criterio.add("PAPA: " + this.papa);
    criterio.add("PBM: " + this.pbm);
    criterio.add("Cupos: " + this.cupos);
    criterio.add("Tipo usuario: Estudiante");

    return criterios;
  }

  public void obtenerAplicantes() {
    // buscar personas que cumplan los requisitos
  }

  public boolean verificarRequisitos(Estudiante estudiante) {
    ArrayList<String> razones = new ArrayList<>(); 
    boolean cumpleRequisitos = true; 

    if (this.cupos <= 0) {
      razones.add("Ya no quedan cupos para este estímulo");
      cumpleRequisitos = false; 
    }

    if (!this.facultadesAplica.contains(estudiante.getFacultad())) {
      razones.add("Este estímulo no aplica a tu facultad");
      cumpleRequisitos = false;
    }

    if (estudiante.calcularPAPA() < this.papa) {
      razones.add("Tu PAPA es inferior al requerido");
      cumpleRequisitos = false; 
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
  public int getPbm() {
    return pbm;
  }

  public int getPapa() {
    return papa;
  }

  // setters
  public void setPbm(int pbm) {
    this.pbm = pbm;
  }

  public void setPapa(int papa) {
    this.papa = papa;
  }
}
