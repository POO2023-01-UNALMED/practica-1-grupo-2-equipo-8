package gestorAplicacion;

public enum TipoNota {
  PARCIAL(1,"Parcial"),
  SEGUIMIENTO(2, "Seguimiento"),
  QUIZ(3, "Quiz");

  private String nombre;
  private int id;

  private TipoNota(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
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
}
