package gestorAplicacion;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class Estimulo {
    private String nombre;
    private String descripcion;
    private tipoUsuario aQuienAplica;
    private ArrayList<facultades> facultadesAplica = new ArrayList<>();
    private int cupos;
    private int PBM;
    private int PAPA;
    private ArrayList<Curso> materiasImpartidas;
    private ArrayList<tipoUsuario> inscritos = new ArrayList<>();

    //Constructores
    public Estimulo(String nombre, String descripcion, tipoUsuario aQuienAplica, 
    ArrayList<facultades> facultadesAplica, int cupos, int PBM, int PAPA, 
    ArrayList<Curso> materiasImpartidas, ArrayList<tipoUsuario> inscritos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aQuienAplica = aQuienAplica;
        this.facultadesAplica = facultadesAplica;
        this.cupos = cupos;
        this.PBM = PBM;
        this.PAPA = PAPA;
        this.materiasImpartidas = materiasImpartidas;
        this.inscritos = inscritos;
    }

    //Metodos
    public boolean verificarRequisitos(Estudiante estudiante) {
        ArrayList<String> razones = new ArrayList<>(); 
        boolean cumpleRequisitos = true; 
    
        if (this.cupos <= 0) {
            razones.add("Ya no quedan cupos para este estímulo");
            cumpleRequisitos = false; 
        }
    
        if (!this.facultadesAplica.contains(estudiante.getFacultad())) {
            razones.add("Este estímulo no aplica a tu facultad");
            cumpleRequisitos = false; 
        }
    
        if (estudiante.calcularPAPA() < this.PAPA) {
            razones.add("Tu PAPA es demasiado bajo");
            cumpleRequisitos = false; 
    
        if (cumpleRequisitos) {
            System.out.println("Cumples con todos los requisitos para inscribirte");
            return true;
        } else {
            System.out.println("No puedes aplicar a este estímulo por las siguientes razones:");
            for (String razon : razones) {
                System.out.println(razon);
            }
            return false;
        }
    }
    

    //Gets y Sets

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public tipoUsuario getaQuienAplica() {
        return aQuienAplica;
    }
    public void setaQuienAplica(tipoUsuario aQuienAplica) {
        this.aQuienAplica = aQuienAplica;
    }

    public ArrayList<facultades> getFacultadesAplica() {
        return facultadesAplica;
    }
    public void setFacultadesAplica(ArrayList<facultades> facultadesAplica) {
        this.facultadesAplica = facultadesAplica;
    }
    
    public int getCupos() {
        return cupos;
    }
    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
    
    public int getPBM() {
        return PBM;
    }
    public void setPBM(int PBM) {
        this.PBM = PBM;
    }
    
    public int getPAPA() {
        return PAPA;
    }
    public void setPAPA(int PAPA) {
        this.PAPA = PAPA;
    }
    
    public ArrayList<Curso> getMateriasImpartidas() {
        return materiasImpartidas;
    }
    public void setMateriasImpartidas(ArrayList<Curso> materiasImpartidas) {
        this.materiasImpartidas = materiasImpartidas;
    }
    
    public ArrayList<tipoUsuario> getInscritos() {
        return inscritos;
    }
    public void setInscritos(ArrayList<tipoUsuario> inscritos) {
        this.inscritos = inscritos;
    }
}