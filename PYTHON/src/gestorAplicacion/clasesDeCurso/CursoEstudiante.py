from gestorAplicacion.clasesDeCurso import Curso

class CursoEstudiante(Curso.Curso):
    def __init__(self, nombre, id, cupos, creditos, numeroParciales, listaPorcentajes, facultad, listaNotas, semestre, estudiante, horario, profesor):
        super().__init__(nombre, creditos, numeroParciales, listaPorcentajes, facultad, id)
        self._listaNotas = listaNotas
        self._semestre = semestre
        self._estudiante = estudiante
        self._horario = horario
        self._profesor = profesor
        self._cupos = cupos
    

    def getListaNotas(self):
        return self._listaNotas
    

    def setListaNotas(self, listaNotas):
        self._listaNotas = listaNotas
    

    def añadirNota(self, nota):
        self._listaNotas.add(nota)
    

    def getSemestre(self): 
        return self._semestre
    

    def setSemestre(self, semestre):
        self._semestre = semestre
    

    def getEstudiante(self): 
        return self._estudiante
    

    def setEstudiante(self, estudiante):
        self._estudiante = estudiante
    

    def getHorario(self): 
        return self._horario
    

    def setHorario(self, horario):
        self._horario = horario
    

    def getProfesor(self): 
        return self._profesor
    

    def setProfesor(self, profesor): 
        self._profesor = profesor
    
    def getCupos(self):
        return self._cupos
    
    def calcularPromedio(self):
        listaPorcentajes = self.getListaPorcentajes()
        total = 0
        for i in range(len(self._listaNotas)):
            a = self._listaNotas[i][1]
            b = self._listaPorcentajes[i][1] / 100.0
            total += a * b
        
        total = round(total * 10.0) / 10.0
        return total