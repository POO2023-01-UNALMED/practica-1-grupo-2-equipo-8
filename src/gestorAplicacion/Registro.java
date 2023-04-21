package gestorAplicacion;

import baseDatos.Deserializador;
import java.util.ArrayList;
import java.io.Serializable;

public class Registro implements Serializable{
    private static final long serialVersionUID = 1L;
    public static short cantidadUsuariosExistentes;
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String clave;
    private String documentoIdentificacion;
    private static ArrayList<Estudiante> estudiantes;
    private static ArrayList<Estudiante> estudiantesMatriculados;
    private static ArrayList<Curso> cursos;
    private static ArrayList<Admin> admins;
    private static ArrayList<Profesor> profesores;
    private static ArrayList<Estimulo> estimulos;

    public Registro(){
        Deserializador.deserializador();
    }
    
    public Registro(String nombre, String correo, String nombreUsuario, String clave, String documentoIdentificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.documentoIdentificacion = documentoIdentificacion;
    }

    public static short getCantidadUsuariosExistentes() {
        return cantidadUsuariosExistentes;
    }

    public static void setCantidadUsuariosExistentes(short cantidadUsuariosExistentes) {
        Registro.cantidadUsuariosExistentes = cantidadUsuariosExistentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    
    public static ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public static void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        Registro.estudiantes = estudiantes;
    }
    
    public static void agregarEstudiante(Estudiante estudiante){
        Registro.estudiantes.add(estudiante);
    }

    
    public static ArrayList<Estudiante> getEstudiantesMatriculados() {
        return estudiantesMatriculados;
    }

    public static void setEstudiantesMatriculados(ArrayList<Estudiante> estudiantesMatriculados) {
        Registro.estudiantesMatriculados = estudiantesMatriculados;
    }
    
    public static void agregarEstudianteMatriculado(Estudiante estudiante){
        Registro.estudiantesMatriculados.add(estudiante);
    }
    
    
    public static ArrayList<Curso> getCursos() {
        return cursos;
    }

    public static void setCursos(ArrayList<Curso> cursos) {
        Registro.cursos = cursos;
    }
    
    public static void agregarCurso(Curso curso){
        Registro.cursos.add(curso);
    }
    
    
    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Registro.admins = admins;
    }
    
    public static void agregarAdmin(Admin admin){
        Registro.admins.add(admin);
    }

    
    public static ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public static void setProfesores(ArrayList<Profesor> profesores) {
        Registro.profesores = profesores;
    }
    
    public static void agregarProfesor(Profesor profesor){
        Registro.profesores.add(profesor);
    }

    public static ArrayList<Estimulo> getEstimulos() {
        return estimulos;
    }

    public static void setEstimulos(ArrayList<Estimulo> estimulos) {
        Registro.estimulos = estimulos;
    }
    
    public static void agregarEstimulos(Estimulo estimulos){
        Registro.estimulos.add(estimulos);
    }
    
    public void buscarCursos() {
        
    }
}
