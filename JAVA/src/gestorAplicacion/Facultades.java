package gestorAplicacion;


public enum Facultades {
  MINAS(1, "Minas"), CIENCIAS(2, "Ciencias");
  
  private String nombre;
  private int id;

  private Facultades(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public int getId() {
    return id;
  }
}
