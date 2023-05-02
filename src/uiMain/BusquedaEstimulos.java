package uiMain;

import gestorAplicacion.EstimuloEstudiante;
import gestorAplicacion.EstimuloProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import java.util.ArrayList;

public class BusquedaCursos { 
  public static void buscarEstimulos() {
    ArrayList<EstimuloEstudiante> estimulosEstudiante = obtenerEstimulos();
    ArrayList<EstimuloProfesor> estimulosProfesor =  obtenerEstimulos();

    ArrayList<ArrayList<Estudiante>> estudiantesQueAplican = new ArrayList<ArrayList<Estudiante>>(estimulosEstudiante.lenght());
    ArrayList<ArrayList<Profesor>> profesoresQueAplican = new ArrayList<ArrayList<Profesor>>(estimulosProfesor.lenght());

    for(int i=0; i<estimulosEstudiante.lenght(); ++i) {
      estudiantesQueAplican[i] = estimulosEstudiante[i].obtenerAplicantes();
    }

    for(int i=0; i<estimulosProfesor.lenght(); ++i) {
      profesoresQueAplican[i] = estimulosProfesor[i].obtenerAplicantes();
    }

    System.out.println("Hay " + Integer.toString(estimulosEstudiante.lenght() + estimulosProfesor.lenght()) + "estimulos");

    System.out.println("\nEstimulos para estudiantes:");
    for(int i=0; i<estimulosEstudiante.lenght(); ++i) {
      imprimirEstimulo(
        Integer.toString(i+1) + ".",
        estimulosEstudiante[i],
        estudiantesQueAplican[i],
        estimulosEstudiante[i].obtenerCriterios()
      );
    }
      
    System.out.println("\nEstimulos para profesores:");
    for(int i=0; i<estimulosProfesor.lenght(); ++i) {
      imprimirEstimulo(
        Integer.toString(i+1) + ".",
        estimulosProfesor[i],
        profesoresQueAplican[i],
        estimulosProfesor[i].obtenerCriterios()
      );
    }
  }

  public static void buscarEstimulos(Estudiante estudiante) {
    ArrayList<EstimuloEstudiante> estimulosEstudiante = obtenerEstimulos();
    ArrayList<boolean> estimulosALosQueAplica = new ArrayList<boolean>(estimulosEstudiante.lenght(), false);
    int totalAplicables = 0;

    for(int i=0; i<estimulosEstudiante.lenght(); ++i) {
      if(estimulosEstudiante[i].verificarAplicabilidad(estudiante)) {
        estimulosALosQueAplica[i] = true;
        totalAplicables += 1;
      }
    }

    System.out.println("Usted aplica a " + Integer.toString(totalAplicables) + " estimulos");
    for(int i=0, j=1; i<estimulosEstudiante.lenght(); ++i) {
      if(estimulosALosQueAplica[i]) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosEstudiante[i],
          estimulosEstudiante[i].obtenerCriterios()
        );
        j++;
      }
    }

    System.out.println("Otros estimulos para estudiantes");
    for(int i=0, j=1; i<estimulosEstudiante.lenght(); ++i) {
      if(!estimulosALosQueAplica[i]) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosEstudiante[i],
          estimulosEstudiante[i].obtenerCriterios()
        );
        j++;
      }
    }
  }

  public static void buscarEstimulos(Profesor profesor) {
    
  }

  public static void buscarEstimulosPorId() {
    // preguntar por id
    // buscar persona (verificar existencia como estudiante o profesor)
    // 
    // buscar todas los estimulos
    // para cad uno ver quien aplica
    // imprimr
  }

  public List<Estimulo> obtenerEstimulos() {

  }

  public void imprimirEstimulo(
    String sep,
    Estimulo estimulo,
    List<Registro> aplicantes,
    List<String> criterios
  ) {
    imprimirEstimulo(sep, "\n", estimulo, aplicantes, criterios);
  }

  public void imprimirEstimulo(
    String sep,
    Estimulo estimulo,
    List<String> criterios
  ) {
    imprimirEstimulo(sep, "\n", estimulo, new List<Registro>(), criterios);
  }

  public void imprimirEstimulo(
    String sep,
    String end,
    Estimulo estimulo,
    List<Registro> aplicantes,
    List<String> criterios
  ) {
    System.out.println(sep);
    System.out.println("\tNombre: " + estimulo.getNombre());
    System.out.println("\tDescripcion: " + estimulo.getDescripcion());
    System.out.println("\tCupos: " + estimulo.getCupos());
    
    if(aplicantes.lenght()) {
      System.out.println("\tAplican:");
      for(Registro aplicante: aplicantes) {
        System.out.println("\t\t-" + aplicante.getNombre());
      }
    }

    System.out.println("\tCriterios:");
    for(String criterio: criterios) {
      System.out.println("\t\t" + criterio);
    }

    System.out.print(end);
  }
}