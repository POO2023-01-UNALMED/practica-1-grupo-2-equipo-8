from gestorAplicacion.clasesDeCurso.Curso import Curso

class CursoProfesor(Curso):
    cursosCreados = []

    def __init__(self, nombre, id, creditos, numeroParciales, listaPorcentajes, facultad, horario):
        super().__init__(nombre, id, creditos, numeroParciales, listaPorcentajes, facultad)
        self._listaEstudiantes = []
        self._horario = horario
        self._cupos = 10
        CursoProfesor.cursosCreados.append(self)

    def resetearCurso(self):
        self._cupos = 10
        self._listaEstudiantes = []

    @staticmethod
    def getCursosCreados():
        return CursoProfesor.cursosCreados

    @staticmethod
    def setCursosCreados(cursos):
        CursoProfesor.cursosCreados = cursos

    def getListaEstudiantes(self):
        return self._listaEstudiantes

    def setListaEstudiantes(self, listaEstudiantes):
        self._listaEstudiantes = listaEstudiantes

    def agregarEstudiante(self, estudiante):
        self._listaEstudiantes.append(estudiante)

    def getHorario(self):
        return self._horario

    def setHorario(self, horario):
        self._horario = horario

    def getCupos(self):
        return self._cupos