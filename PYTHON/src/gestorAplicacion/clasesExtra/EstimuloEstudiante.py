
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesExtra.Estimulo import Estimulo
from gestorAplicacion.clasesExtra.EstimuloEstudianteInterfaz import EstimuloEstudianteInterfaz

class EstimuloEstudiante(Estimulo, EstimuloEstudianteInterfaz):
    def __init__(
        self,
        nombre,
        descripcion,
        aQuienAplica,
        facultadesAplica,
        cupos,
        pbm,
        papa
    ):
        super().__init__(nombre, descripcion, aQuienAplica, facultadesAplica, cupos)
        self.pbm = pbm
        self.papa = papa

    def obtenerCriterios(self):
        criterios = []

        facultades = "Facultad: ["
        for facultad in self.getFacultadesAplica():
            facultades += facultad.name + ", "
        facultades += "]"

        criterios.append(facultades)
        criterios.append("PAPA: " + str(self.papa))
        criterios.append("PBM: " + str(self.pbm))
        criterios.append("Cupos: " + str(self.getCupos()))
        criterios.append("Tipo usuario: Estudiante")

        return criterios

    def obtenerAplicantes(self):
        estudiantes = []

        for estudiante in Registro.getEstudiantes():
            if self.verificarRequisitos(estudiante):
                estudiantes.append(estudiante)

        return estudiantes

    def verificarRequisitos(self, estudiante):
        cumpleRequisitos = True

        if self.getCupos() <= 0:
            cumpleRequisitos = False

        if estudiante.getFacultad() not in self.getFacultadesAplica():
            cumpleRequisitos = False

        if estudiante.calcularPAPA() < self.papa:
            cumpleRequisitos = False

        return cumpleRequisitos

    def getPbm(self):
        return self.pbm

    def getPapa(self):
        return self.papa

    def setPbm(self, pbm):
        self.pbm = pbm

    def setPapa(self, papa):
        self.papa = papa
