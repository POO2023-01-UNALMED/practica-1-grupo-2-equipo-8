package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import uiMain.BusquedaCursos;

public class Horario implements Serializable{
    private static final long serialVersionUID = 8L;
    private int id;
    private Estudiante estudiante;
    private ArrayList<CursoEstudiante> cursos = new ArrayList<>();

    //Constructores
    public Horario(Estudiante estudiante){
        if(estudiante.getHorariosCreados().isEmpty()){
            this.id = 1;
        }
        else{
            this.id = estudiante.getHorariosCreados().get(estudiante.getHorariosCreados().size()-1).getId()+1;
        }
    }
    
    public Horario(Estudiante estudiante, ArrayList<CursoEstudiante> cursos){
        this(estudiante);
        this.estudiante = estudiante;
        this.cursos = cursos;
    }
    
    public Horario(ArrayList<CursoEstudiante> cursos){
        this.cursos = cursos;
    }

    public void agregarCurso(CursoEstudiante curso) {
        cursos.add(curso);
        if(validarDisponibilidad()){
           BusquedaCursos.aceptar(); 
        }
    }
    

    public boolean validarDisponibilidad() {
        for(int x = 0; x<cursos.size(); x++){
            CursoEstudiante curso1 = cursos.get(x);
            String[] datos1 = curso1.getHorario().split(" ");
            String[] dias1 = {datos1[0],datos1[2]};
            String[][] horas1 = {datos1[1].split("-"),datos1[3].split("-")};
            for(int y = x+1; y<cursos.size(); y++){
                CursoEstudiante curso2 = cursos.get(y);
                String[] datos2 = curso2.getHorario().split(" ");
                String[] dias2 = {datos2[0],datos2[2]};
                String[][] horas2 = {datos2[1].split("-"),datos2[3].split("-")};
                if(curso1.getNombre().equals(curso2.getNombre())){
                    BusquedaCursos.reportarFallo(curso1.getNombre());
                    cursos.remove(cursos.size()-1); 
                    return false;
                }
                else{
                int cont1 = 1;
                int cont2 = 1;
                    for(String dia1 : dias1){

                        for(String dia2 : dias2){
                            if(dia1.equals(dia2)){
                                int hi1 = Integer.valueOf(horas1[cont1][0].substring(0, 2));
                                int hf1 = Integer.valueOf(horas1[cont1][1].substring(0, 2));
                                int hi2 = Integer.valueOf(horas2[cont2][0].substring(0, 2));
                                int hf2 = Integer.valueOf(horas2[cont2][1].substring(0, 2));
                                if((hi1 == hi2) || (hi1<=hi2 && hi2<hf1) || (hi1<hf2 && hf2<=hf1) || (hi2<=hi1 && hi1<hf2) || (hi2<hf1 && hf1<=hf2)){
                                    BusquedaCursos.reportarFallo(curso1, curso2);
                                    cursos.remove(cursos.size()-1); 
                                    return false;
                                }
                            }
                            cont2++;
                        }
                        cont1++;
                    }  
                }
            }
        }
        return true;
    }
    
    public boolean validarHorario(Estudiante estudiante) {
        for(int x = 0; x<cursos.size(); x++){
            CursoEstudiante curso1 = cursos.get(x);
            String[] datos1 = curso1.getHorario().split(" ");
            String[] dias1 = {datos1[0],datos1[2]};
            String[][] horas1 = {datos1[1].split("-"),datos1[3].split("-")};
            for(int y = x+1; y<cursos.size(); y++){
                CursoEstudiante curso2 = cursos.get(y);
                String[] datos2 = curso2.getHorario().split(" ");
                String[] dias2 = {datos2[0],datos2[2]};
                String[][] horas2 = {datos2[1].split("-"),datos2[3].split("-")};
                if(curso1.getNombre().equals(curso2.getNombre())){
                    return false;
                }
                int cont1 = 1;
                int cont2 = 1;
                    for(String dia1 : dias1){

                        for(String dia2 : dias2){
                            if(dia1.equals(dia2)){
                                int hi1 = Integer.valueOf(horas1[cont1][0].substring(0, 2));
                                int hf1 = Integer.valueOf(horas1[cont1][1].substring(0, 2));
                                int hi2 = Integer.valueOf(horas2[cont2][0].substring(0, 2));
                                int hf2 = Integer.valueOf(horas2[cont2][1].substring(0, 2));
                                if((hi1 == hi2) || (hi1<=hi2 && hi2<hf1) || (hi1<hf2 && hf2<=hf1) || (hi2<=hi1 && hi1<hf2) || (hi2<hf1 && hf1<=hf2)){
                                    return false;
                                }
                            }
                            cont2++;
                        }
                        cont1++;
                    }  
                
            }
        }
        for(CursoEstudiante ce1 : estudiante.getCursosVistos()){
            for(CursoEstudiante ce2 : getCursos()){
                if(ce1.getNombre().equals(ce2.getNombre())){
                    return false;
                }
            }
        }
        ArrayList<CursoEstudiante> cursosAprobados = (ArrayList<CursoEstudiante>) estudiante.getListaCursos().clone();
        ArrayList<CursoEstudiante> cursosABorrar = new ArrayList<CursoEstudiante>();
        for(CursoEstudiante ce : cursosAprobados){
            if(ce.calcularPromedio()<3){
                cursosABorrar.add(ce);
                //cursosAprobados.remove(ce);
            }
        }
        cursosAprobados.removeAll(cursosABorrar);
        for(Curso c1 : cursosAprobados){
            for(Curso c2 : getCursos()){
                if(c1.getNombre().equals(c2.getNombre())){
                    return false;
                }
            }
        }
        int cont;
        boolean comp;
        for(CursoEstudiante ce : cursos){
            cont = 0;
            for(Curso pre : ce.getPreRequisitos()){
                comp = false;
                for(CursoEstudiante cv : estudiante.getCursosVistos()){
                    if(cv.getNombre().equals(pre.getNombre())){
                        cont++;
                        comp = true;
                    }
                }
                if(comp == false){
                    for(CursoEstudiante capr : cursosAprobados){
                       if(capr.getNombre().equals(pre.getNombre())){
                            cont++;
                        } 
                    }
                }
            }
            if(cont != ce.getPreRequisitos().size()){
                return false;
            }
        }
        return true;
    }
    
    
    //Sets y Gets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<CursoEstudiante> getCursos() {
        return cursos;
    }
    public void setCursos(ArrayList<CursoEstudiante> cursos) {
        this.cursos = cursos;
    }
    }