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
import java.util.Arrays;
import java.util.Collections;

public class BusquedaEstimulos {
  public static void buscarEstimulos() {
    ArrayList<EstimuloEstudiante> estimulosEstudiante = obtenerEstimulosEstudiante();
    ArrayList<EstimuloProfesor> estimulosProfesor =  obtenerEstimulosProfesor();

    ArrayList<ArrayList<Estudiante>> estudiantesQueAplican = new ArrayList<ArrayList<Estudiante>>(estimulosEstudiante.size());
    ArrayList<ArrayList<Profesor>> profesoresQueAplican = new ArrayList<ArrayList<Profesor>>(estimulosProfesor.size());

    for(int i=0; i<estimulosEstudiante.size(); ++i) {
      estudiantesQueAplican.add(estimulosEstudiante.get(i).obtenerAplicantes());
    }

    for(int i=0; i<estimulosProfesor.size(); ++i) {
      profesoresQueAplican.add(estimulosProfesor.get(i).obtenerAplicantes());
    }

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
        estimulosALosQueAplica.set(i,  true);
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

  public static void buscarEstimulosPorId() {
    Scanner sc = new Scanner(System.in);
    
    while (true) {
      System.out.println("Digite el nombre del usuario a consultar: ");
      String id = sc.nextLine();
      
      if(id.length() == 0 || !Helpers.esAlfa(id)) {
        System.out.println("Digite un nombre válido (solo a-z A-Z)");
        continue;
      }

      Estudiante estudiantePorId = null;
      Profesor profesorPorId = null;

      for(Estudiante estudiante: Registro.getEstudiantes()) {
        if(estudiante.getNombre().equals(id)) {
          estudiantePorId = estudiante;
        }
      }
      
      for(Profesor profesor: Registro.getProfesores()) {
        if(profesor.getNombre().equals(id)) {
          profesorPorId = profesor;
        }
      }
      
      if(estudiantePorId != null) {
        ArrayList<EstimuloEstudiante> estimulos = obtenerEstimulosEstudiante();
        ArrayList<Boolean> estimulosALosQueAplica = new ArrayList<>(Collections.nCopies(estimulos.size(), false));
        int totalAplicables = 0;
    
        for(int i=0; i<estimulos.size(); ++i) {
          if(estimulos.get(i).verificarRequisitos(estudiantePorId)) {
            estimulosALosQueAplica.set(i,  true);
            totalAplicables += 1;
          }
        }
        System.out.println("Hay [" + totalAplicables + "] estímulos a los que aplica el Estudiante " + estudiantePorId.getNombre());
   
        for(int i=0, j=1; i<estimulos.size(); ++i){
          if(estimulosALosQueAplica.get(i)) {
            imprimirEstimulo(
              Integer.toString(j) + ".",
              estimulos.get(i),
              estimulos.get(i).obtenerCriterios()
            );
            j++;
          }
        }

        System.out.println("Otros estimulos para estudiantes: ");
        for(int i=0, j=1; i<estimulos.size(); ++i) {
          if(!estimulosALosQueAplica.get(i)) {
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
        ArrayList<Boolean> estimulosALosQueAplica = new ArrayList<>(Collections.nCopies(estimulos.size(), false));
        int totalAplicables = 0;
    
        for(int i=0; i<estimulos.size(); ++i) {
          if(estimulos.get(i).verificarRequisitos(profesorPorId)) {
            estimulosALosQueAplica.set(i,  true);
            totalAplicables += 1;
          }
        }
        System.out.println("Hay [" + totalAplicables + "] estímulos a los que aplica el Profesor " + profesorPorId.getNombre());
   
        for(int i=0, j=1; i<estimulos.size(); ++i){
          if(estimulosALosQueAplica.get(i)) {
            imprimirEstimulo(
              Integer.toString(j) + ".",
              estimulos.get(i),
              estimulos.get(i).obtenerCriterios()
            );
            j++;
          }
        }

        System.out.println("Otros estimulos para profesores: ");
        for(int i=0, j=1; i<estimulos.size(); ++i) {
          if(!estimulosALosQueAplica.get(i)) {
            imprimirEstimulo(
              Integer.toString(j) + ".",
              estimulos.get(i),
              estimulos.get(i).obtenerCriterios()
            );
            j++;
          }
        }
      } else {
        System.out.println("No hay un Estudiante o Profesor con id: " + id);
      }

      Boolean continuarPrograma = false;

      while (true) { 
        System.out.println("1. Consultar otro usuario por id\n" + "2. Salir\n");
        String opcion = sc.nextLine();
        ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("1", "2"));

        if (!opciones.contains(opcion)) {
          System.out.println("Debe seleccionar un número entre el 1 y el 2");
          continue;
        }
        
        switch (opcion) {
          case "1":
            continuarPrograma = true;          
            break;
          case "2":
            continuarPrograma = false;
            break;
          default:
            break;
        }

        break;
      }

      if(!continuarPrograma) {
        break;
      }
    }
  }

  public static ArrayList<EstimuloEstudiante> obtenerEstimulosEstudiante() {
	  ArrayList<EstimuloEstudiante> estimulos = new ArrayList<>();

	  for(Estimulo e: Registro.getEstimulosEstudiantes()) {
		  if(e.getAQuienAplica() == TipoUsuarios.ESTUDIANTE) {
			  estimulos.add((EstimuloEstudiante) e);
		  }
	  }

	  return estimulos;
  }
  
  public static ArrayList<EstimuloProfesor> obtenerEstimulosProfesor() {
	  ArrayList<EstimuloProfesor> estimulos = new ArrayList<>();

	  for(Estimulo e: Registro.getEstimulosProfesores()) {
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
    imprimirEstimulo(sep, "\n", estimulo, aplicantes, true, criterios);
  }

  public static void imprimirEstimulo(
    String sep,
    Estimulo estimulo,
    ArrayList<String> criterios
  ) {
    imprimirEstimulo(sep, "\n", estimulo, new ArrayList<Registro>(), false, criterios);
  }

  public static void imprimirEstimulo(
    String sep,
    String end,
    Estimulo estimulo,
    ArrayList<Registro> aplicantes,
    Boolean withAplicantes,
    ArrayList<String> criterios
  ) {
    System.out.println(sep);
    System.out.println("\tNombre: " + estimulo.getNombre());
    System.out.println("\tDescripcion: " + estimulo.getDescripcion());
    System.out.println("\tCupos: " + estimulo.getCupos());
    
    if(aplicantes.size() > 0 && withAplicantes) {
      System.out.println("\tAplican [" + aplicantes.size() + "]:");
      for(Registro aplicante: aplicantes) {
        System.out.println("\t\t-" + aplicante.getNombre());
      }
    } else if(withAplicantes) {
      System.out.println("\tAplican [0]: No aplica ningun usuario");
    }

    System.out.println("\tCriterios:");
    for(String criterio: criterios) {
      System.out.println("\t\t" + criterio);
    }

    System.out.print(end);
  }
  
  
}