package gestorAplicacion;


public enum TipoUsuarios {
  ESTUDIANTE(1, "Estudiante"),
  PROFESOR(2, "Profesor"),
  ADMIN(3, "Admin");

  private String tipoUsuario;
  private int id;

  private TipoUsuarios(int id, String tipoUsuario) {
    this.id = id;
    this.tipoUsuario = tipoUsuario;
  }

  public String tipoUsuario() {
    return tipoUsuario;
  }

  public int getId() {
    return id;
  }
}
