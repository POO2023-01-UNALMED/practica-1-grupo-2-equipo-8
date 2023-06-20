package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Estimulo implements Serializable {
    private static final long serialVersionUID = 9L;
    private String nombre;
    private String descripcion;
    private TipoUsuarios aQuienAplica;
    private ArrayList<Facultades> facultadesAplica = new ArrayList<>();
    private int cupos;

    public Estimulo(
            String nombre,
            String descripcion,
            TipoUsuarios aQuienAplica,
            ArrayList<Facultades> facultadesAplica,
            int cupos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aQuienAplica = aQuienAplica;
        this.facultadesAplica = facultadesAplica;
        this.cupos = cupos;
    }

    // getters
    public TipoUsuarios getAQuienAplica() {
        return aQuienAplica;
    }

    public int getCupos() {
        return cupos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Facultades> getFacultadesAplica() {
        return facultadesAplica;
    }

    public String getNombre() {
        return nombre;
    }

    // setters
    public void setAQuienAplica(TipoUsuarios aQuienAplica) {
        this.aQuienAplica = aQuienAplica;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFacultadesAplica(ArrayList<Facultades> facultadesAplica) {
        this.facultadesAplica = facultadesAplica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
