from abc import ABC, abstractclassmethod
class Registro(ABC):
    _cantidadUsuariosExistentes = 0
    _estudiantes = set()
    _estudiantesMatriculados = set()
    _cursos = []
    _admins = set()
    _profesores = set()
    _estimulosEstudiantes = set()
    _estimulosProfesores = set()
    _estudiantesConCita = set()

    def __init__(self, nombre, correo, nombreUsuario, clave, documentoIdentificacion):
        self._nombre = nombre
        self._correo = correo
        self._nombreUsuario = nombreUsuario
        self._clave = clave
        self._documentoIdentificacion = documentoIdentificacion
    
    @classmethod
    def getEstudiantesConCita(cls):
        return Registro._estudiantesConCita
    
    @classmethod
    def setEstudiantesConCita(cls, estudiantesConCita):
        Registro._estudiantesConCita = estudiantesConCita

    @classmethod
    def getCantidadUsuariosExistentes(cls):
        return Registro._cantidadUsuariosExistentes

    @classmethod
    def setCantidadUsuariosExistentes(cls, cantidadUsuariosExistentes):
        Registro._cantidadUsuariosExistentes = cantidadUsuariosExistentes
    
    @classmethod
    def getAdmins(cls): 
        return Registro._admins
    
    @classmethod
    def setAdmins(cls, admins):
        Registro._admins = admins
    
    @classmethod
    def agregarAdmin(cls, admin):
        Registro._admins.add(admin)
    
    @classmethod
    def getProfesores(cls):
        return Registro._profesores
    
    @classmethod
    def setProfesores(cls, profesores):
        Registro._profesores = profesores
    
    @classmethod
    def agregarProfesor(cls, profesor):
        Registro._profesores.add(profesor)
    
    @classmethod
    def getEstimulosEstudiantes(cls):
        return Registro._estimulosEstudiantes
    
    @classmethod
    def setEstimulosEstudiantes(cls, estimulos):
        Registro._estimulosEstudiantes = estimulos
    
    @classmethod
    def agregarEstimulosEstudiantes(cls, estimulos):
        Registro._estimulosEstudiantes.add(estimulos)
    
    @classmethod
    def getEstimulosProfesores(cls):
        return Registro._estimulosProfesores
    
    @classmethod
    def setEstimulosProfesores(cls, estimulos):
        Registro._estimulosProfesores = estimulos
    
    @classmethod
    def agregarEstimulosProfesores(cls, estimulos):
        Registro._estimulosProfesores.add(estimulos)
    
    @classmethod
    def getEstudiantes(cls):
        return Registro._estudiantes
    
    @classmethod
    def setEstudiantes(cls, estudiantes):
        Registro._estudiantes = estudiantes

    @classmethod
    def agregarEstudiante(cls, estudiante):
        Registro._estudiantes.add(estudiante)
    
    @classmethod
    def getEstudiantesMatriculados(cls):
        return Registro._estudiantesMatriculados
    
    @classmethod
    def setEstudiantesMatriculados(cls, estudiantesMatriculados):
        Registro._estudiantesMatriculados = estudiantesMatriculados
    
    @classmethod
    def agregarEstudianteMatriculado(cls, estudiante):
        Registro._estudiantesMatriculados.add(estudiante)
    
    @classmethod
    def getCursos(cls): 
        return Registro._cursos
    
    @classmethod
    def setCursos(cls, cursos):
        Registro._cursos = cursos
    
    @classmethod
    def agregarCurso(cls, curso):
        comp = True
        for c in Registro._cursos:
            if c.getNombre() == curso.getNombre():
                comp = False
                break
        if comp == True:
            Registro._cursos.add(curso)
    
    @classmethod
    def eliminarCurso(cls, curso):
        Registro._cursos.remove(curso)
    
    @classmethod
    def eliminarCurso(cls, i): #En desuso
        Registro._cursos.remove(i)
    
    def getNombre(self):
        return self._nombre
    
    def setNombre(self, nombre):
        self._nombre = nombre
    

    def getCorreo(self):
        return self._correo
    
    def setCorreo(self, correo):
        self._correo = correo
    
    def getNombreUsuario(self):
        return self._nombreUsuario
    
    def setNombreUsuario(self, nombreUsuario): 
        self._nombreUsuario = nombreUsuario
    
    def getClave(self):
        return self._clave
    
    def setClave(self, clave): 
        self._clave = clave
    
    def getDocumentoIdentificacion(self): 
        return self._documentoIdentificacion
    
    def setDocumentoIdentificacion(self, documentoIdentificacion): 
        self._documentoIdentificacion = documentoIdentificacion
    
    @abstractclassmethod
    def buscarCursos(self):
        pass
