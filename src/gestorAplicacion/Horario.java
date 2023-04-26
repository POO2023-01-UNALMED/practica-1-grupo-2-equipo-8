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
    private static int count;

    //Constructores
    public Horario(Estudiante estudiante, ArrayList<CursoEstudiante> cursos){
        this.estudiante = estudiante;
        this.cursos = cursos;
        Horario.count++;
        this.id = Horario.count;
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