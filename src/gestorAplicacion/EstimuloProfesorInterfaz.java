package gestorAplicacion;

import java.util.ArrayList;

public abstract interface EstimuloProfesorInterfaz {
  public abstract ArrayList<String> obtenerCriterios();
  public abstract ArrayList<Profesor> obtenerAplicantes();
  public abstract boolean verificarRequisitos(Profesor profesor);
}
