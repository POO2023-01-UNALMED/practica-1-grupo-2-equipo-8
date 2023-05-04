package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;


public class Estudiante extends Registro{
    private static final long serialVersionUID = 3L;
    private ArrayList<CursoEstudiante> listaCursos = new ArrayList<CursoEstudiante>();
    private ArrayList<CursoEstudiante> cursosVistos = new ArrayList<CursoEstudiante>();
    private ArrayList<Curso> listaCursosInscritos = new ArrayList<Curso>();
    private Carreras carrera;
    private Facultades facultad;
    private int semestre;
    private ArrayList<Horario> horariosCreados = new ArrayList<Horario>();
    private ArrayList<Estimulo> Estimulos = new ArrayList<Estimulo>();
    private String cita;
    private Boolean inscribir = false;

    public Estudiante(String nombre, String correo, String nombreUsuario, String clave, String documento, Carreras carrera, Facultades facultad, int semestre) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.carrera = carrera;
        this.facultad = facultad;
        this.semestre = semestre;
    }
    
    public Boolean getInscribir() {
        return inscribir;
    }

    public void setInscribir(Boolean inscribir) {
        this.inscribir = inscribir;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public ArrayList<CursoEstudiante> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(CursoEstudiante curso) {
        listaCursos.add(curso);
    }

    public ArrayList<CursoEstudiante> getCursosVistos() {
        return cursosVistos;
    }

    public void setCursosVistos(CursoEstudiante curso) {
        cursosVistos.add(curso);
    }

    public ArrayList<Curso> getListaCursosInscritos() {
        return listaCursosInscritos;
    }

    public void setListaCursosInscritos(Curso curso) {
        listaCursosInscritos.add(curso);
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    public ArrayList<Horario> getHorariosCreados() {
        return horariosCreados;
    }

    public void setHorariosCreados(ArrayList<Horario> horariosCreados) {
        this.horariosCreados = horariosCreados;
    }

    public void agregarHorario(Horario horario){
        horariosCreados.add(horario);
    }
    
    public int calcularPAPI(int sem){ //Para el cálculo del PAPPI se pide el semestre al que corresponde el PAPPI que quiere ver
        if(sem<semestre && sem>0){
            int sum = 0;
            int sumc = 0;
            for(CursoEstudiante c:cursosVistos){
                if(c.getSemestre() == sem){
                    sum+=c.calcularPromedio()*c.getCreditos();
                    sumc+=c.getCreditos();
                }
            }
            return sumc == 0 ? 0: sum/sumc;
        }
        return -1; // Hay que corregir la lógica en la capa de UI
    }
    
    // METODOS
    public int calcularPAPA(){
        int sum = 0;
        int sumc = 0;
        for(CursoEstudiante c:cursosVistos){
            sum+=c.calcularPromedio()*c.getCreditos();
            sumc+=c.getCreditos();
        }

        return sumc == 0 ? 0: sum/sumc;
    }
    
    public void inscribirMateria(CursoEstudiante curso){
        setListaCursos(curso);
    }
    
    public void verMateriasDisponiblesParaInscripcion(){ //Falta complemento de la UI
        
    }

    public void consultarHorarioGeneral(){ //Falta complemento de la UI
        
    }
    
    public void consultarRecomendacionAsignaturas(){ //Falta complemento de la UI
        
    }
    
    public void crearHorario(){
        Horario horario = new Horario(this, new ArrayList<CursoEstudiante>());
        horariosCreados.add(horario);
    }
    
}