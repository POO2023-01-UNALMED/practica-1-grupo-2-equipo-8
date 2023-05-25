package gestorAplicacion;

import java.util.ArrayList;

public abstract interface EstimuloEstudianteInterfaz {
  public abstract ArrayList<String> obtenerCriterios();
  public abstract ArrayList<Estudiante> obtenerAplicantes();
  public abstract boolean verificarRequisitos(Estudiante estudiante);
}
