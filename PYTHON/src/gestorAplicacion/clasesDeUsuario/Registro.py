class Registro :
    cantidadUsuariosExistentes = 0
    estudiantes = set()
    estudiantesMatriculados = set()
    cursos = set()
    admins = set()
    profesores = set()
    estimulosEstudiantes = set()
    estimulosProfesores = set()
    estudiantesConCita = set()

    def __init__(self, nombre, correo, nombreUsuario, clave, documentoIdentificacion) -> None:
        self._nombre = nombre
        self._correo = correo
        self._nombreUsuario = nombreUsuario
        self._clave = clave
        self._documentoIdentificacion = documentoIdentificacion

    def getNombre(self) :
        return self._nombre
    
    def setNombre(self, nombre) :
        self._nombre = nombre

    def getCorreo(self) :
        return self._correo
    
    def setCorreo(self, correo) :
        self._correo = correo

    def getNombreUsuario(self) :
        return self._nombreUsuario
    
    def setNombreUsuario(self, nombreUsuario) :
        self._nombreUsuario = nombreUsuario

    def getClave(self) :
        return self._clave
    
    def setClave(self, clave) :
        self._clave = clave

    def getDocumentoIdentificacion(self) :
        return self._documentoIdentificacion
    
    def setDocumentoIdentificacion(self, documentoIdentificacion) :
        self._documentoIdentificacion = documentoIdentificacion

    @classmethod
    def agregarEstudiante(cls, estudiante) :
        cls.estudiantes.add(estudiante)

    @classmethod
    def agregarEstudianteMatriculado(cls, estudianteMatriculado) :
        cls.estudiantesMatriculados.add(estudianteMatriculado)

    @classmethod
    def agregarCurso(cls, curso) :
        cls.cursos.add(curso)

    @classmethod
    def agregarAdmin(cls, admin) :
        cls.admins.add(admin)

    @classmethod
    def agregarProfesor(cls, profesor) :
        cls.profesores.add(profesor)

    @classmethod
    def agregarEstimuloEstudiante(cls, estimuloEstudiante) :
        cls.estimulosEstudiantes.add(estimuloEstudiante)

    @classmethod
    def agregarEstimuloProfesor(cls, estimuloProfesor) :
        cls.estimulosProfesores.add(estimuloProfesor)

    @classmethod
    def agregarEstudianteConCita(cls, estudianteConCita) :
        cls.estudiantesConCita.add(estudianteConCita)
