class Curso:
    def __init__(self, nombre, creditos, numeroParciales, listaPorcentajes, facultades, id=0, preRequisitos=[], carrerasRelacionadas=[], esLibreEleccion=False):
        from gestorAplicacion.clasesDeUsuario.Registro import Registro
        self._nombre = nombre
        self._creditos = creditos
        self._numeroParciales = numeroParciales
        self._listaPorcentajes = listaPorcentajes
        self._facultades = facultades
        self._id = 100000 + len(Registro.getCursos()) if id == 0 else id
        self._preRequisitos = preRequisitos
        self._carrerasRelacionadas = carrerasRelacionadas
        self._esLibreEleccion = esLibreEleccion
        Registro.agregarCurso(self)

    # GETS Y SETS
    def getNombre(self) -> str:
        return self._nombre

    def setNombre(self, nombre):
        self._nombre = nombre

    def getCreditos(self) -> int:
        return self._creditos

    def setCreditos(self, creditos):
        self._creditos = creditos

    def getNumeroParciales(self) -> int:
        return self._numeroParciales

    def setNumeroParciales(self, numeroParciales):
        self._numeroParciales = numeroParciales

    def getListaPorcentajes(self):
        return self._listaPorcentajes

    def setListaPorcentajes(self, listaPorcentajes):
        self._listaPorcentajes = listaPorcentajes

    def getFacultades(self):
        return self._facultades

    def setFacultades(self, facultades):
        self._facultades = facultades

    def getId(self) -> int:
        return self._id

    def setId(self, id):
        self._id = id

    def getPreRequisitos(self):
        return self._preRequisitos

    def setPreRequisitos(self, preRequisitos):
        self._preRequisitos = preRequisitos

    def getCarrerasRelacionadas(self) :
        return self._carrerasRelacionadas

    def setCarrerasRelacionadas(self, carrerasRelacionadas):
        self._carrerasRelacionadas = carrerasRelacionadas

    def getEsLibreEleccion(self) -> bool:
        return self._esLibreEleccion

    def setEsLibreEleccion(self, esLibreEleccion):
        self._esLibreEleccion = esLibreEleccion

    #Methods
    def obtenerGrupos(self, estudiante = None):
        from gestorAplicacion.clasesDeCurso.CursoEstudiante import CursoEstudiante
        from gestorAplicacion.clasesDeUsuario.Registro import Registro

        if(estudiante != None):
            listaCursos = []
            for profesor in Registro.getProfesores():
                for cp in profesor.getListaCursos():
                    if cp.getNombre() == self.getNombre():
                        listaCursos.append(CursoEstudiante(cp.getNombre(), cp.getId(), cp.getCupos(), cp.getCreditos(), cp.getNumeroParciales(), cp.getListaPorcentajes(), cp.getFacultad(), [], estudiante.getSemestre()+1, estudiante, cp.getHorario(), profesor))
            return listaCursos
        else:
            listaCursos = []
            for profesor in Registro.getProfesores():
                for cp in profesor.getListaCursos():
                    if cp.getNombre() == self.getNombre():
                        listaCursos.append(cp)
            return listaCursos
  
    def filtrarPorFacultad(self, cursos, facultad):
        cursosFiltrados = []
        for curso in cursos:
            if(facultad in curso._facultades):
                cursosFiltrados.append(curso)
        return cursosFiltrados
    
    def filtrarPorCarrera(self, cursos, carrera):
      cursosFiltrados = []
      for curso in cursos:
          if(carrera in curso._carrerasRelacionadas):
            cursosFiltrados.append(curso)
      return cursosFiltrados
  
  
    def filtrarPorHorario(self, cursos, horario):
        listaCursos = []
        for curso in cursos:
            cursosProfesores = curso.obtenerGrupos()
            for cp in cursosProfesores:
                if(cp.getHorario() == horario):
                    listaCursos.append(curso)
                    break
        return listaCursos
  

    def vioPrerrequisitos(self, estudiante):
        #Si el curso no tiene prerrequisitos, sí vio los prerrequisitos.
        if (len(self.getPreRequisitos()) == 0):
            return True

        #Si el estudiante es nuevo, no vio los prerrequisitos.
        if (len(estudiante.getCursosVistos()) == 0):
            return False

        #La comparación se realiza entre los nombres, ya que son clases distintas,
        #por lo que se obtiene la lista de nombres de los preRequisitos,
        nombresCursosPreRequisitos = []
        for asignatura in self.getPreRequisitos(): 
            nombresCursosPreRequisitos.append(asignatura.getNombre())

        #y la lista de nombres de los cursos vistos,
        nombresCursosVistos = []
        for asignatura in estudiante.getCursosVistos():
            nombresCursosVistos.append(asignatura.getNombre())

        #finalmente se verifica si el estudiante a cursado todos los preRequisitos.
        for pre in nombresCursosPreRequisitos:
            cont = 1
            for cv in nombresCursosVistos:
                if pre == cv:
                    break
                else:
                    cont += 1
            if cont > len(nombresCursosVistos):
                return False
        return True
