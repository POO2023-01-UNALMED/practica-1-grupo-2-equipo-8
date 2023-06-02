package gestorAplicacion;

import java.util.ArrayList;

public class EstimuloEstudiante extends Estimulo implements EstimuloEstudianteInterfaz {
  private static final long serialVersionUID = 10L;
  private int pbm;
  private double papa;

  public EstimuloEstudiante(
      String nombre,
      String descripcion,
      TipoUsuarios aQuienAplica,
      ArrayList<Facultades> facultadesAplica,
      int cupos,
      int pbm,
      double papa
  ) {
    super(nombre, descripcion, aQuienAplica, facultadesAplica, cupos);
    this.pbm = pbm;
    this.papa = papa;
  }

  // metodos
  public ArrayList<String> obtenerCriterios() {
    ArrayList<String> criterios = new ArrayList<>();

    String facultades = "Facultad: [";
    for (Facultades facultad : getFacultadesAplica()) {
      facultades += facultad.getNombre() + ", ";
    }
    facultades += "]";

    criterios.add(facultades);
    criterios.add("PAPA: " + this.papa);
    criterios.add("PBM: " + this.pbm);
    criterios.add("Cupos: " + this.getCupos());
    criterios.add("Tipo usuario: Estudiante");

    return criterios;
  }

  public ArrayList<Estudiante> obtenerAplicantes() {
    ArrayList<Estudiante> estudiantes = new ArrayList<>();

    for (Estudiante estudiante : Registro.getEstudiantes()) {
      if (verificarRequisitos(estudiante)) {
        estudiantes.add(estudiante);
      }
    }

    return estudiantes;
  }

  public boolean verificarRequisitos(Estudiante estudiante) {
    boolean cumpleRequisitos = true;

    if (this.getCupos() <= 0) {
      cumpleRequisitos = false;
    }

    if (!this.getFacultadesAplica().contains(estudiante.getFacultad())) {
      cumpleRequisitos = false;
    }

    if (estudiante.calcularPAPA() < this.papa) {
      cumpleRequisitos = false;
    }

    return cumpleRequisitos;
  }

  // getters
  public int getPbm() {
    return pbm;
  }

  public double getPapa() {
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
