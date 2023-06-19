class Curso:
  def __init__(self, nombre, creditos, numeroParciales, listaPorcentajes, facultades, id = 0, preRequisitos = None, carrerasRelacionadas = None, esLibreEleccion = False):
    from ..clasesDeUsuario.Registro import Registro

    self._nombre = nombre
    self._creditos = creditos
    self._numeroParciales = numeroParciales
    self._listaPorcentajes = listaPorcentajes
    self._facultades = facultades
    if id == 0:
      self._id = 100000 + len(Registro.getCursos())
    else:
      self._id = id
    self._cupos = 0
    self._horariosClase = set()
    if preRequisitos == None:
      self._preRequisitos = set()
    else:
      self._preRequisitos = preRequisitos
    if carrerasRelacionadas == None:
      self._carrerasRelacionadas = set()
    else:
      self._carrerasRelacionadas = carrerasRelacionadas
    self._profesoresQueDictanElCurso = set()
    self._esLibreEleccion = esLibreEleccion
    Registro.agregarCurso(self) # Se agrega el curso creado a la base de datos.

  # get y set
  def getHorariosClase(self): 
    return self._horariosClase
  

  def getId(self): 
    return self._id
  

  def getNombre(self): 
    return self._nombre
  

  def setNombre(self, nombre): 
    self._nombre = nombre
  

  def setId(self, id):
    self._id = id
  

  def getCupos(self): 
    return self._cupos
  

  def setCupos(self, cupos): 
    self._cupos = cupos
  

  def getCreditos(self): 
    return self._creditos
  

  def setCreditos(self, creditos):
    self._creditos = creditos
  

  def setHorariosClase(self, horariosClase):
      self._horariosClase = horariosClase
  
  
  def agregarHorario(self, horario):
      self._horariosClase.add(horario)
  
  

  def getNumeroParciales(self): 
    return self._numeroParciales
  

  def setNumeroParciales(self, numeroParciales):
    self._numeroParciales = numeroParciales
  

  def getListaPorcentajes(self): 
    return self._listaPorcentajes
  

  def setListaPorcentajes(self, listaPorcentajes):
    self._listaPorcentajes = listaPorcentajes
  

  def getPreRequisitos(self): 
    return self._preRequisitos
  

  def setPreRequisitos(self, preRequisitos):
    self._preRequisitos = preRequisitos
  

  def getCarrerasRelacionadas(self): 
    return self._carrerasRelacionadas
  

  def setCarrerasRelacionadas(self, carrerasRelacionadas):
    self._carrerasRelacionadas = carrerasRelacionadas
  

  def getProfesoresQueDictanElCurso(self): 
    return self._profesoresQueDictanElCurso
  

  def setProfesoresQueDictanElCurso(self, profesoresQueDictanElCurso):
    self._profesoresQueDictanElCurso = profesoresQueDictanElCurso
  
  
  def agregarProfesor(self, profesor):
    self._profesoresQueDictanElCurso.add(profesor)
  

  def getFacultad(self): 
    return self._facultades
  

  def setFacultad(self, facultades): 
    self._facultades = facultades
  
  def getEsLibreEleccion(self) :
    return self._esLibreEleccion
  
  def setEsLibreEleccion(self, esLibreEleccion) :
    self._esLibreEleccion = esLibreEleccion

  def __str__(self):
    return self.getNombre()+ " (" + self.getId() + ")"
  
  
  #Methods
  def obtenerGrupos(self, estudiante = None):
    from .CursoEstudiante import CursoEstudiante
    from ..clasesDeUsuario.Registro import Registro

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
    
