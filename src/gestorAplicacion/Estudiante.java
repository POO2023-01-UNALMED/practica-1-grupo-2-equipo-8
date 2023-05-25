package gestorAplicacion; //Falta especificar en qué sub-paquéte estará

import java.util.ArrayList;
import uiMain.BusquedaCursos;
import uiMain.AsignarCita;


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
    private int cita;
    private Boolean inscribir = false;

    public Estudiante(String nombre, String correo, String nombreUsuario, String clave, String documento, Carreras carrera, Facultades facultad, int semestre) {
        super(nombre, correo, nombreUsuario, clave, documento);
        this.carrera = carrera;
        this.facultad = facultad;
        this.semestre = semestre;
        Registro.agregarEstudiante(this);
    }
    
    public Estudiante(String nombre, String correo, String nombreUsuario, String clave, String documento, Carreras carrera, Facultades facultad, int semestre, ArrayList<CursoEstudiante> cursosVistos, ArrayList<CursoEstudiante> listaCursos){
        this(nombre, correo, nombreUsuario, clave, documento, carrera, facultad, semestre);
        this.listaCursos = listaCursos;
        this.cursosVistos = cursosVistos;
        for(CursoEstudiante ce : listaCursos){
            Profesor profesor = ce.getProfesor();
            for(CursoProfesor cp : profesor.getListaCursos()){
                if(ce.getNombre().equals(cp.getNombre())){
                    cp.agregarEstudiante(this);
                    cp.setCupos((short)(cp.getCupos()-1));
                }
            }
        }
        Registro.agregarEstudiante(this);
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

    public void agregarCursoVisto(CursoEstudiante cursoVisto) {
        cursosVistos.add(cursoVisto);
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
    
    public int getCita() {
        return cita;
    }
    public void setCita(int cita) {
        this.cita = cita;
    }
    
    public Boolean getInscribir() {
        return inscribir;
    }

    public void setInscribir(Boolean inscribir) {
        this.inscribir = inscribir;
    }

    // METODOS
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
    
    public int calcularPAPA(){
        int sum = 0;
        int sumc = 0;
        for(CursoEstudiante c:cursosVistos){
            sum+=c.calcularPromedio()*c.getCreditos();
            sumc+=c.getCreditos();
        }

        return sumc == 0 ? 0: sum/sumc;
    }
    
    
    public void verMateriasDisponiblesParaInscripcion(){ //Falta complemento de la UI
        
    }

    public void consultarHorarioGeneral(){ //Falta complemento de la UI
        
    }
    
    public void consultarRecomendacionAsignaturas(){ //Falta complemento de la UI
        
    }
    
    @Override
    public void buscarCursos(){
        BusquedaCursos.buscarCursos(this);
    }
    
    public Horario crearHorario(){
        Horario horario = new Horario(this, new ArrayList<CursoEstudiante>());
        horariosCreados.add(horario);
        return horario;
    }
    
    public void inscribirCursos(ArrayList<CursoEstudiante> cursos){
        for(CursoEstudiante ce : listaCursos){
            if(ce.calcularPromedio()>=3){
                cursosVistos.add(ce);
            }
        }
        listaCursos.clear();
        for(CursoEstudiante ce : cursos){
            listaCursos.add(ce);
        }
        for(CursoEstudiante ce : cursos){
            Profesor profesor = ce.getProfesor();
            for(CursoProfesor cp : profesor.getListaCursos()){
                if(ce.getNombre().equals(cp.getNombre())){
                    cp.agregarEstudiante(this);
                    cp.setCupos((short)(cp.getCupos()-1));
                    AsignarCita.getEstudiantesConCita().get(0).setInscribir(false);
                    AsignarCita.getEstudiantesConCita().remove(0);
                    AsignarCita.getEstudiantesConCita().get(0).setInscribir(true);
                }
            }
        }
    }
    
    public void inscribirCursos(Horario horario){
        for(CursoEstudiante ce : listaCursos){
            if(ce.calcularPromedio()>=3){
                cursosVistos.add(ce);
            }
        }
        listaCursos.clear();
        for(CursoEstudiante ce : horario.getCursos()){
            listaCursos.add(ce);
        }
        for(CursoEstudiante ce : horario.getCursos()){
            Profesor profesor = ce.getProfesor();
            for(CursoProfesor cp : profesor.getListaCursos()){
                if(ce.getNombre().equals(cp.getNombre())){
                    cp.agregarEstudiante(this);
                    cp.setCupos((short)(cp.getCupos()-1));
                    AsignarCita.getEstudiantesConCita().get(0).setInscribir(false);
                    AsignarCita.getEstudiantesConCita().remove(0);
                    AsignarCita.getEstudiantesConCita().get(0).setInscribir(true);
                }
            }
        }
    }

    public boolean vioCurso(Curso curso) {
        // Si el estudiante es nuevo, no ha cursado ninguna materia
        if (this.getCursosVistos() == null || this.getCursosVistos().isEmpty()) {
            return false;
        }
        // La comparación se realiza entre los nombres, ya que son clases distintas,
        // por lo que se obtiene la lista de nombres,
        ArrayList<String> nombresCursosVistos = new ArrayList<String>();
        for (CursoEstudiante asignatura : this.getCursosVistos()) {
            nombresCursosVistos.add(asignatura.getNombre());
        }
        // y se revisa si el nombre del curso está en los cursos vistos.
        return nombresCursosVistos.contains(curso.getNombre());
    }
    
    public String toString() {
        return getNombre() + " (" + calcularPAPA() + ")";
    }
}