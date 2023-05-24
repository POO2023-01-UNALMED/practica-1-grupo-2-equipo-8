package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class EstimuloEstudiante extends Estimulo implements Serializable {
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
    for(Facultades facultad: getFacultadesAplica()) {
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

	  for(Estudiante estudiante: Registro.getEstudiantes()) {
		  if(verificarRequisitos(estudiante)) {
			  estudiantes.add(estudiante);
		  }
	  }

	  return estudiantes;
  }

  public boolean verificarRequisitos(Estudiante estudiante) {
    ArrayList<String> razones = new ArrayList<>(); 
    boolean cumpleRequisitos = true; 

    if (this.getCupos() <= 0) {
      razones.add("Ya no quedan cupos para este estímulo");
      cumpleRequisitos = false; 
    }

    if (!this.getFacultadesAplica().contains(estudiante.getFacultad())) {
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
