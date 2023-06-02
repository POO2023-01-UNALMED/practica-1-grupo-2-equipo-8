package gestorAplicacion;

public enum Carreras {
  SISTEMAS(1, "Ingenieria de Sistemas", Facultades.MINAS),
  COMPUTACION(2, "Ciencias de la Computacion", Facultades.CIENCIAS);

  private String nombre;
  private int id;
  private Facultades facultad;

  private Carreras(int id, String nombre, Facultades facultad) {
    this.id = id;
    this.nombre = nombre;
    this.facultad = facultad;
  }
  
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Facultades getFacultad() {
    return facultad;
  }

  public void setFacultad(Facultades facultad) {
    this.facultad = facultad;
  }
}
