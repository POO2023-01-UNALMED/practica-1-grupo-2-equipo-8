from clasesDeUsuario.Registro import Registro

class Curso :
    def __init__(self, nombre, creditos, cupos, facultad, notasYPorcentajes = [],  
                 horariosClase = [], carrerasRelacionadas = set(), preRequisitos = set(), 
                 profesoresQueDictanElCurso = set()) -> None:
        self._nombre = nombre
        self._id = 100000 + len(Registro.cursos)
        self._creditos = creditos
        self._cupos = cupos
        self._facultad = facultad
        self._notasYPorcentajes = notasYPorcentajes
        self._horariosClase = horariosClase
        self._carrerasRelacionadas = carrerasRelacionadas
        self._preRequisitos = preRequisitos
        self._profesoresQueDictanElCurso = profesoresQueDictanElCurso
        Registro.agregarCurso(self)

    def getNombre(self) :
        return self._nombre
    
    def setNombre(self, nombre) :
        self._nombre = nombre

    def getId(self) :
        return self._id
    
    def setId(self, id) :
        self._id = id

    def getCreditos(self) :
        return self._creditos
    
    def setCreditos(self, creditos) :
        self._creditos = creditos

    def getCupos(self) :
        return self._cupos
    
    def setCupos(self, cupos) :
        self._cupos = cupos

    def getFacultad(self) :
        return self._facultad
    
    def setFacultad(self, facultad) :
        self._facultad = facultad

    def getNotasYPorcentajes(self) :
        return self._notasYPorcentajes
    
    def setNotasYPorcentajes(self, notasYPorcentajes) :
        self._notasYPorcentajes = notasYPorcentajes

    def getCarrerasRelacionadas(self) :
        return self._carrerasRelacionadas
    
    def setCarrerasRelacionadas(self, carrerasRelacionadas) :
        self._carrerasRelacionadas = carrerasRelacionadas

    def getHorariosClase(self) :
        return self._horariosClase
    
    def setHorariosClase(self, horariosClase) :
        self._horariosClase = horariosClase

    def getPreRequisitos(self) :
        return self._preRequisitos
    
    def setPreRequisitos(self, preRequisitos) :
        self._preRequisitos = preRequisitos

    def getProfesoresQueDictanElCurso(self) :
        return self._profesoresQueDictanElCurso
    
    def setProfesoresQueDictanElCurso(self, profesoresQueDictanElCurso) :
        self._profesoresQueDictanElCurso = profesoresQueDictanElCurso

    def agregarHorarioClase(self, horario) :
        self._horariosClase.append(horario)

    def agregarCarreraRelacionada(self, carrera) :
        self._carrerasRelacionadas.add(carrera)

    def agregarPreRequisito(self, preRequisito) :
        self._preRequisitos.add(preRequisito)

    def agregarprofesorQueDictaElCurso(self, profesorQueDictaElCurso) :
        self._profesoresQueDictanElCurso.add(profesorQueDictaElCurso)
