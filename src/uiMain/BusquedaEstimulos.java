package uiMain;

import gestorAplicacion.EstimuloEstudiante;
import gestorAplicacion.EstimuloProfesor;
import gestorAplicacion.Estudiante;
import gestorAplicacion.Profesor;
import gestorAplicacion.Estimulo;
import gestorAplicacion.Registro;
import uiMain.Helpers;
import gestorAplicacion.TipoUsuarios;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class BusquedaEstimulos {
  public static void buscarEstimulos() {
    ArrayList<EstimuloEstudiante> estimulosEstudiante = obtenerEstimulosEstudiante();
    ArrayList<EstimuloProfesor> estimulosProfesor =  obtenerEstimulosProfesor();

    ArrayList<ArrayList<Estudiante>> estudiantesQueAplican = new ArrayList<ArrayList<Estudiante>>(estimulosEstudiante.size());
    ArrayList<ArrayList<Profesor>> profesoresQueAplican = new ArrayList<ArrayList<Profesor>>(estimulosProfesor.size());

    for(int i=0; i<estimulosEstudiante.size(); ++i) {
      estudiantesQueAplican.set(i, estimulosEstudiante.get(i).obtenerAplicantes());
    }

    for(int i=0; i<estimulosProfesor.size(); ++i) {
      profesoresQueAplican.set(i, estimulosProfesor.get(i).obtenerAplicantes());
    }

    System.out.println("Hay " + Integer.toString(estimulosEstudiante.size() + estimulosProfesor.size()) + "estimulos");

    System.out.println("\nEstimulos para estudiantes:");
    for(int i=0; i<estimulosEstudiante.size(); ++i) {
      imprimirEstimulo(
        Integer.toString(i+1) + ".",
        (Estimulo) estimulosEstudiante.get(i),
        new ArrayList<Registro>(estudiantesQueAplican.get(i)),
        estimulosEstudiante.get(i).obtenerCriterios()
      );
    }
      
    System.out.println("\nEstimulos para profesores:");
    for(int i=0; i<estimulosProfesor.size(); ++i) {
      imprimirEstimulo(
        Integer.toString(i+1) + ".",
        (Estimulo) estimulosProfesor.get(i),
        new ArrayList<Registro>(profesoresQueAplican.get(i)),
        estimulosProfesor.get(i).obtenerCriterios()
      );
    }
  }

  public static void buscarEstimulos(Estudiante estudiante) {
    ArrayList<EstimuloEstudiante> estimulosEstudiante = obtenerEstimulosEstudiante();
    ArrayList<Boolean> estimulosALosQueAplica = new ArrayList<>(Collections.nCopies(estimulosEstudiante.size(), false));
    int totalAplicables = 0;

    for(int i=0; i<estimulosEstudiante.size(); ++i) {
      if(estimulosEstudiante.get(i).verificarRequisitos(estudiante)) {
        estimulosALosQueAplica.set(i, true);
        totalAplicables += 1;
      }
    }

    System.out.println("Usted aplica a " + Integer.toString(totalAplicables) + " estimulos");
    for(int i=0, j=1; i<estimulosEstudiante.size(); ++i) {
      if(estimulosALosQueAplica.get(i)) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosEstudiante.get(i),
          estimulosEstudiante.get(i).obtenerCriterios()
        );
        j++;
      }
    }

    System.out.println("Otros estimulos para estudiantes");
    for(int i=0, j=1; i<estimulosEstudiante.size(); ++i) {
      if(!estimulosALosQueAplica.get(i)) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosEstudiante.get(i),
          estimulosEstudiante.get(i).obtenerCriterios()
        );
        j++;
      }
    }
  }

  public static void buscarEstimulos(Profesor profesor) {
    ArrayList<EstimuloProfesor> estimulosProfesor = obtenerEstimulosProfesor();
    ArrayList<Boolean> estimulosALosQueAplica = new ArrayList<>(Collections.nCopies(estimulosProfesor.size(), false));
    int totalAplicables = 0;

    for(int i=0; i<estimulosProfesor.size(); ++i) {
      if(estimulosProfesor.get(i).verificarRequisitos(profesor)) {
        estimulosALosQueAplica.set(i,  false);
        totalAplicables += 1;
      }
    }

    System.out.println("Usted aplica a " + Integer.toString(totalAplicables) + " estimulos");
    for(int i=0, j=1; i<estimulosProfesor.size(); ++i) {
      if(estimulosALosQueAplica.get(i)) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosProfesor.get(i),
          estimulosProfesor.get(i).obtenerCriterios()
        );
        j++;
      }
    }

    System.out.println("Otros estimulos para profesores");
    for(int i=0, j=1; i<estimulosProfesor.size(); ++i) {
      if(!estimulosALosQueAplica.get(i)) {
        imprimirEstimulo(
          Integer.toString(j) + ".",
          estimulosProfesor.get(i),
          estimulosProfesor.get(i).obtenerCriterios()
        );
        j++;
      }
    }    
  }

  public static boolean buscarEstimulosPorId() {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Digite el id del usuario a consultar");
    String id = sc.nextLine();
    
    if(id.length() == 0 || !Helpers.esNumerico(id)) {
      System.out.println("Digite un id válido (solo números)");
      return false;
    }

    Estudiante estudiantePorId = null;
    Profesor profesorPorId = null;

    for(Estudiante estudiante: Registro.getEstudiantes()) {
      if(estudiante.getDocumentoIdentificacion() == id) {
        estudiantePorId = estudiante;
      }
    }
    
    for(Profesor profesor: Registro.getProfesores()) {
      if(profesor.getDocumentoIdentificacion() == id) {
        profesorPorId = profesor;
      }
    }
    
    if(estudiantePorId != null) {
      ArrayList<EstimuloEstudiante> estimulos = obtenerEstimulosEstudiante();
      
      for(int i=0, j=1; i<estimulos.size(); ++i){
    	  if(estimulos.get(i).verificarRequisitos(estudiantePorId)) {
    		  imprimirEstimulo(
    			  Integer.toString(j) + ".",
				  estimulos.get(i),
				  estimulos.get(i).obtenerCriterios()
				  );
	        j++;
          }
      }
    } else if(profesorPorId != null) {
    	 ArrayList<EstimuloProfesor> estimulos = obtenerEstimulosProfesor();
         
         for(int i=0, j=1; i<estimulos.size(); ++i) {
       	  if(estimulos.get(i).verificarRequisitos(profesorPorId)) {
       		  imprimirEstimulo(
       			  Integer.toString(j) + ".",
   				  estimulos.get(i),
   				  estimulos.get(i).obtenerCriterios()
   				  );
   	        j++;
             }
         }
    }

    return false;
  }

  public static ArrayList<EstimuloEstudiante> obtenerEstimulosEstudiante() {
	  ArrayList<EstimuloEstudiante> estimulos = new ArrayList<>();

	  for(Estimulo e: Registro.getEstimulos()) {
		  if(e.getAQuienAplica() == TipoUsuarios.ESTUDIANTE) {
			  estimulos.add((EstimuloEstudiante) e);
		  }
	  }

	  return estimulos;
  }
  
  public static ArrayList<EstimuloProfesor> obtenerEstimulosProfesor() {
	  ArrayList<EstimuloProfesor> estimulos = new ArrayList<>();

	  for(Estimulo e: Registro.getEstimulos()) {
		  if(e.getAQuienAplica() == TipoUsuarios.PROFESOR) {
			  estimulos.add((EstimuloProfesor) e);
		  }
	  }

	  return estimulos;
  }


  public static void imprimirEstimulo(
    String sep,
    Estimulo estimulo,
    ArrayList<Registro> aplicantes,
    ArrayList<String> criterios
  ) {
    imprimirEstimulo(sep, "\n", estimulo, aplicantes, criterios);
  }

  public static void imprimirEstimulo(
    String sep,
    Estimulo estimulo,
    ArrayList<String> criterios
  ) {
    imprimirEstimulo(sep, "\n", estimulo, new ArrayList<Registro>(), criterios);
  }

  public static void imprimirEstimulo(
    String sep,
    String end,
    Estimulo estimulo,
    ArrayList<Registro> aplicantes,
    ArrayList<String> criterios
  ) {
    System.out.println(sep);
    System.out.println("\tNombre: " + estimulo.getNombre());
    System.out.println("\tDescripcion: " + estimulo.getDescripcion());
    System.out.println("\tCupos: " + estimulo.getCupos());
    
    if(aplicantes.size() > 0) {
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