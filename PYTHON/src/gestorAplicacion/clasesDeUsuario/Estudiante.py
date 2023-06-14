from clasesDeUsuario import Registro

class Estudiante(Registro):
    def __init__(self, nombre, correo, nombreUsuario, clave, documento, carrera, facultad, semestre, cursosVistos = None, listaCursos = None):
        super().__init__(self, nombre, correo, nombreUsuario, clave, documento)
        self._carrera = carrera
        self._facultad = facultad
        self._semestre = semestre
        self._horariosCreados = set()
        self._estimulos = set()
        if listaCursos == None:
            self._listaCursos = set()
        else:
            self._listaCursos = listaCursos
        if cursosVistos == None:
            self._cursosVistos = set()
        else:
            self._cursosVistos = cursosVistos
        self._listaCursosInscritos = set()
        for ce in listaCursos:
            profesor = ce.getProfesor()
            for cp in profesor.getListaCursos():
                if ce.getNombre() == cp.getNombre():
                    cp.agregarEstudiante(self)
                    cp.setCupos(cp.getCupos()-1)
        self._cita = 0
        self._inscribir = False
        Registro.agregarEstudiante(self)
        
        
    def getListaCursos(self): 
        return self._listaCursos
    

    def setListaCursos(self, curso):
        self._listaCursos.add(curso)
    

    def getCursosVistos(self):
        return self._cursosVistos
    

    def agregarCursoVisto(self, cursoVisto):
        self._cursosVistos.add(cursoVisto)
    

    def setCursosVistos(self, curso):
        self._cursosVistos.add(curso)
    

    def getListaCursosInscritos(self): 
        return self._listaCursosInscritos
    

    def setListaCursosInscritos(self, curso):
        self._listaCursosInscritos.add(curso)
    

    def getCarrera(self): 
        return self._carrera
    

    def setCarrera(self, carrera):
        self._carrera = carrera
    

    def getFacultad(self): 
        return self._facultad
    

    def setFacultad(self, facultad):
        self._facultad = facultad
    

    def getSemestre(self):
        return self._semestre
    

    def setSemestre(self, semestre):
        self._semestre = semestre
    
    
    def getHorariosCreados(self): 
        return self._horariosCreados
    

    def setHorariosCreados(self, horariosCreados): 
        self._horariosCreados = horariosCreados
    

    def agregarHorario(self, horario):
        self._horariosCreados.add(horario)
    
    
    def getCita(self): 
        return self._cita
    
    def setCita(self, cita):
        self._cita = cita
    
    
    def getInscribir(self): 
        return self._inscribir
    

    def setInscribir(self, inscribir):
        self._inscribir = inscribir
    

    #METODOS
    def calcularPAPA(self):
        sum = 0
        sumc = 0
        for c in self._cursosVistos:
            sum+=c.calcularPromedio()*c.getCreditos()
            sumc+=c.getCreditos()
        try:
            return round(sum/sumc * 10.0)/10.0
        except ZeroDivisionError:
            return 0
    
    def buscarCursos(self):
        BusquedaCursos.buscarCursos(self)
    
    
    def crearHorario(self):
        horario = Horario(self, [])
        self._horariosCreados.add(horario)
        return horario
    
    
    def inscribirCursos(self, cursos = None, horario = None):
        if cursos != None:
            for ce in self._listaCursos:
                if(ce.calcularPromedio()>=3):
                    self._cursosVistos.add(ce)
            self._listaCursos.clear()
            for ce in cursos:
                self._listaCursos.add(ce)
            for ce in cursos:
                profesor = ce.getProfesor()
                for cp in profesor.getListaCursos():
                    if ce.getNombre() == cp.getNombre():
                        cp.agregarEstudiante(self)
                        cp.setCupos(cp.getCupos()-1)
                        AsignarCita.getEstudiantesConCita().get(0).setInscribir(False)
                        AsignarCita.getEstudiantesConCita().remove(0)
                        AsignarCita.getEstudiantesConCita().get(0).setInscribir(True)
        elif horario != None:
            for ce in self._listaCursos:
                if(ce.calcularPromedio()>=3):
                    self._cursosVistos.add(ce)
            self._listaCursos.clear()
            for ce in horario.getCursos():
                self._listaCursos.add(ce)
            
            for ce in horario.getCursos():
                profesor = ce.getProfesor()
                for cp in profesor.getListaCursos():
                    if ce.getNombre() == cp.getNombre():
                        cp.agregarEstudiante(self)
                        cp.setCupos(cp.getCupos()-1)
                        AsignarCita.getEstudiantesConCita().get(0).setInscribir(False)
                        AsignarCita.getEstudiantesConCita().remove(0)
                        AsignarCita.getEstudiantesConCita().get(0).setInscribir(True)
    
    def vioCurso(self, curso):
        #Si el estudiante es nuevo, no ha cursado ninguna materia
        if len(self.getCursosVistos()) == 0:
            return False
        
        #La comparación se realiza entre los nombres, ya que son clases distintas,
        #por lo que se obtiene la lista de nombres,
        nombresCursosVistos = []
        for asignatura in self.getCursosVistos(): 
            nombresCursosVistos.add(asignatura.getNombre())
        
        #y se revisa si el nombre del curso está en los cursos vistos.
        return curso.getNombre() in nombresCursosVistos
    
    
    def __str__(self):
        return self.getNombre() + " (" + self.calcularPAPA() + ")"
    
