package gestorAplicacion;

import java.util.ArrayList;
import java.util.Arrays;

public class Horario {
    private int id;
    private Estudiante estudiante;
    private ArrayList<Curso> cursos = new ArrayList<>();
    private static int count;

    //Constructores
    public Horario(Estudiante estudiante, ArrayList<Curso> cursos){
        this.estudiante = estudiante;
        this.cursos = cursos;
        Horario.count++;
        this.id = Horario.count;
    }

    public Horario(int id, Estudiante estudiante, ArrayList<Curso> cursos){
        this.estudiante = estudiante;
        this.cursos = cursos;
        this.id = id;
    }

    //Metodos

    public void mostrar(){
        // Provicional mientras se define bien la clase Curso
        this.cursos = cursos;
    }

    
    public void imprimirHorario() {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        String[] abreviado = {"L", "M", "X", "J", "V", "S"};
        ArrayList<String> horarios = this.cursos.getHorariosClase();
        String[][] clasesPorDia = new String[6][];
        for (int i = 0; i < 6; i++) {
            ArrayList<String> clases = new ArrayList<String>();
            for (String horario : horarios) {
                String[] datosHorario = horario.split(" ");
                String[] horarioClase = datosHorario[1].split("-");
                String diaClase = datosHorario[0];
                if (abreviado[i].equals(diaClase)) {
                    clases.add(datosHorario[0] + " " + datosHorario[1] + " " + datosHorario[2]);
                }
            }
            String[] clasesArray = new String[clases.size()];
            clases.toArray(clasesArray);
            Arrays.sort(clasesArray);
            clasesPorDia[i] = clasesArray;
        }
    
        for (int i = 0; i < 6; i++) {
            System.out.println("----------" + dias[i] + "----------");
            String[] clasesDia = clasesPorDia[i];
            for (String clase : clasesDia) {
                System.out.println(clase);
            }
        }
    }
    

    public Boolean validarDisponibilidad() {
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso1 = cursos.get(i);
            for (String horario1 : curso1.getHorariosClase()) {
                String[] datosHorario1 = horario1.split(" ");
                String dia1 = datosHorario1[0];
                String[] hora1 = datosHorario1[1].split("-");
                int horaInicio1 = Integer.parseInt(hora1[0]);
                int horaFin1 = Integer.parseInt(hora1[1]);
                for (int j = i+1; j < cursos.size(); j++) {
                    Curso curso2 = cursos.get(j);
                    for (String horario2 : curso2.getHorariosClase()) {
                        String[] datosHorario2 = horario2.split(" ");
                        String dia2 = datosHorario2[0];
                        String[] hora2 = datosHorario2[1].split("-");
                        int horaInicio2 = Integer.parseInt(hora2[0]);
                        int horaFin2 = Integer.parseInt(hora2[1]);
                        if (dia1.equals(dia2)) {
                            if (horaInicio1 == horaInicio2 || horaFin1 == horaFin2) {
                                return false;
                            } else if (horaInicio1 < horaInicio2 && horaInicio2 < horaFin1) {
                                return false;
                            } else if (horaInicio1 < horaFin2 && horaFin2 < horaFin1) {
                                return false;
                            } else if (horaInicio2 < horaInicio1 && horaFin1 < horaFin2) {
                                return false;
                            }
                        }
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

    public ArrayList<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    }