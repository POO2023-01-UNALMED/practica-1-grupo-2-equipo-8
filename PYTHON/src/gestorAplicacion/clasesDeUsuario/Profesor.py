from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesDeCurso.CursoProfesor import CursoProfesor

class Profesor(Registro):
    def __init__(self, nombre, correo, nombreUsuario, clave, documento, listaCursos, facultad):
        super().__init__(nombre, correo, nombreUsuario, clave, documento)
        self._listaCursos = listaCursos
        self._facultad = facultad
        self._calificaciones = []
        self._calificacion = -1
        Registro.agregarProfesor(self)

    def getCalificacion(self):
        return self._calificacion

    def setCalificacion(self, calificacion):
        self._calificacion = calificacion

    def getListaCursos(self):
        return self._listaCursos

    def setListaCursos(self, listaCursos):
        self._listaCursos = listaCursos

    def agegarCurso(self, curso):
        self._listaCursos.append(curso)

    def getFacultad(self):
        return self._facultad

    def setFacultad(self, facultad):
        self._facultad = facultad

    def agregarNota(self, estudiante, curso, indiceNota, indiceTipoNota, nota):
        if not self.validarExistenciaCurso(curso):
            return False
        # TODO: Check if the student exists among the students of that course
        # TODO: Check if the grade is valid, create a static method for general validation
        return True

    def editarNota(self, estudiante, curso, nota):
        if not self.validarExistenciaCurso(curso):
            return False
        # TODO: Check if the student exists among the students of that course
        # TODO: Check if the grade is valid, create a static method for general validation
        return True

    """ def agregarCurso(self, listaCursos, listaHorarios):
        for curso, horario in zip(listaCursos, listaHorarios):
            cp = CursoProfesor(curso.nombre, curso.id, curso.creditos, curso.numeroParciales, curso.listaPorcentajes, curso.facultad, horario)
            self._listaCursos.append(cp)
            curso.agregarHorario(horario)
            curso.cupos += 5 """

    def validarExistenciaCurso(self, curso):
        for cursoActual in self._listaCursos:
            if cursoActual._id == curso._id:
                return True
        return False

    def calificar(self, valoracion):
        self._calificaciones.append(valoracion)
        self.setCalificacion(round(sum(self._calificaciones)/len(self._calificaciones), 1))

    def buscarCursos(self):
        # Implement the logic for searching courses here
        pass

    def fueCalificado(self) -> bool :
        return False if self.getCalificacion() == -1 else True

    def __str__(self):
        return f"{self.getNombre()}\t{self.getCalificacion()}"