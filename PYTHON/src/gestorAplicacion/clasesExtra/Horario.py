class Horario:
    def __init__(self, estudiante, cursos=None):
        self.id = 1 if not estudiante.getHorariosCreados() else estudiante.getHorariosCreados()[-1].getId() + 1
        self.estudiante = estudiante
        self.cursos = cursos if cursos else []

    def agregarCurso(self, curso):
        self.cursos.append(curso)
        if self.validarDisponibilidad():
            BusquedaCursos.aceptar()

    def validarDisponibilidad(self):
        for x in range(len(self.cursos)):
            curso1 = self.cursos[x]
            datos1 = curso1.getHorario().split(" ")
            dias1 = [datos1[0], datos1[2]]
            horas1 = [datos1[1].split("-"), datos1[3].split("-")]
            for y in range(x + 1, len(self.cursos)):
                curso2 = self.cursos[y]
                datos2 = curso2.getHorario().split(" ")
                dias2 = [datos2[0], datos2[2]]
                horas2 = [datos2[1].split("-"), datos2[3].split("-")]
                if curso1.getNombre() == curso2.getNombre():
                    BusquedaCursos.reportarFallo(curso1.getNombre())
                    self.cursos.pop()
                    return False
                else:
                    cont1 = 1
                    cont2 = 1
                    for dia1 in dias1:
                        for dia2 in dias2:
                            if dia1 == dia2:
                                hi1 = int(horas1[cont1][0][:2])
                                hf1 = int(horas1[cont1][1][:2])
                                hi2 = int(horas2[cont2][0][:2])
                                hf2 = int(horas2[cont2][1][:2])
                                if (hi1 == hi2) or (hi1 <= hi2 < hf1) or (hi1 < hf2 <= hf1) or (hi2 <= hi1 < hf2) or (
                                        hi2 < hf1 <= hf2):
                                    BusquedaCursos.reportarFallo(curso1, curso2)
                                    self.cursos.pop()
                                    return False
                            cont2 += 1
                        cont1 += 1
        return True

    def validarHorario(self, estudiante):
        for x in range(len(self.cursos)):
            curso1 = self.cursos[x]
            datos1 = curso1.getHorario().split(" ")
            dias1 = [datos1[0], datos1[2]]
            horas1 = [datos1[1].split("-"), datos1[3].split("-")]
            for y in range(x + 1, len(self.cursos)):
                curso2 = self.cursos[y]
                datos2 = curso2.getHorario().split(" ")
                dias2 = [datos2[0], datos2[2]]
                horas2 = [datos2[1].split("-"), datos2[3].split("-")]
                if curso1.getNombre() == curso2.getNombre():
                    return False
                cont1 = 1
                cont2 = 1
                for dia1 in dias1:
                    for dia2 in dias2:
                        if dia1 == dia2:
                            hi1 = int(horas1[cont1][0][:2])
                            hf1 = int(horas1[cont1][1][:2])
                            hi2 = int(horas2[cont2][0][:2])
                            hf2 = int(horas2[cont2][1][:2])
                            if (hi1 == hi2) or (hi1 <= hi2 < hf1) or (hi1 < hf2 <= hf1) or (hi2 <= hi1 < hf2) or (
                                    hi2 < hf1 <= hf2):
                                return False
                        cont2 += 1
                    cont1 += 1

        for ce1 in estudiante.getCursosVistos():
            for ce2 in self.getCursos():
                if ce1.getNombre() == ce2.getNombre():
                    return False

        cursosAprobados = estudiante.getListaCursos().copy()
        cursosABorrar = []
        for ce in cursosAprobados:
            if ce.calcularPromedio() < 3:
                cursosABorrar.append(ce)
        cursosAprobados = [curso for curso in cursosAprobados if curso not in cursosABorrar]

        for c1 in cursosAprobados:
            for c2 in self.getCursos():
                if c1.getNombre() == c2.getNombre():
                    return False

        cont = 0
        for ce in self.cursos:
            for pre in ce.getPreRequisitos():
                comp = False
                for cv in estudiante.getCursosVistos():
                    if cv.getNombre() == pre.getNombre():
                        cont += 1
                        comp = True
                if not comp:
                    for capr in cursosAprobados:
                        if capr.getNombre() == pre.getNombre():
                            cont += 1
            if cont != len(ce.getPreRequisitos()):
                return False

        return True

    def getId(self):
        return self.id

    def setId(self, id):
        self.id = id

    def getEstudiante(self):
        return self.estudiante

    def setEstudiante(self, estudiante):
        self.estudiante = estudiante

    def getCursos(self):
        return self.cursos

    def setCursos(self, cursos):
        self.cursos = cursos
