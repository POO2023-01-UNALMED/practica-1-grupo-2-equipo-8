
from clasesDeUsuario.Registro import Registro
from clasesExtra.Estimulo import Estimulo
from clasesExtra.EstimuloProfesorInterfaz import EstimuloProfesorInterfaz

class EstimuloProfesor(Estimulo, EstimuloProfesorInterfaz):
    def __init__(
        self,
        nombre,
        descripcion,
        aQuienAplica,
        facultadesAplica,
        cupos,
        materiasImpartidas
    ):
        super().__init__(nombre, descripcion, aQuienAplica, facultadesAplica, cupos)
        self.materiasImpartidas = materiasImpartidas

    def obtenerCriterios(self):
        criterios = []

        materias = "Materias: ["
        for cursoId in self.materiasImpartidas:
            for curso in Registro.getCursos():
                if curso.getId() == cursoId:
                    materias += curso.getNombre() + ", "
        materias += "]"

        facultades = "Facultad: ["
        for facultad in self.getFacultadesAplica():
            facultades += facultad.getNombre() + ", "
        facultades += "]"

        criterios.append(facultades)
        criterios.append(materias)
        criterios.append("Cupos: " + str(self.getCupos()))
        criterios.append("Tipo usuario: Profesor")

        return criterios

    def obtenerAplicantes(self):
        profesores = []

        for profesor in Registro.getProfesores():
            if self.verificarRequisitos(profesor):
                profesores.append(profesor)

        return profesores

    def verificarRequisitos(self, profesor):
        cumpleRequisitos = True

        if self.getCupos() <= 0:
            cumpleRequisitos = False

        if profesor.getFacultad() not in self.getFacultadesAplica():
            cumpleRequisitos = False

        for cursoId in self.materiasImpartidas:
            for curso in Registro.getCursos():
                if curso.getId() != cursoId:
                    continue
                if not profesor.validarExistenciaCurso(curso):
                    cumpleRequisitos = False

        return cumpleRequisitos

    def getMateriasImpartidas(self):
        return self.materiasImpartidas

    def setMateriasImpartidas(self, materiasImpartidas):
        self.materiasImpartidas = materiasImpartidas
