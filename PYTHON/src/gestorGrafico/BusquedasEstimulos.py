from tkinter import Button, Entry, Frame, Label, Listbox, Menu, StringVar, Tk, Toplevel, messagebox, ttk
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesExtra.TipoUsuarios import TipoUsuarios
from gestorGrafico.FieldFrame import FieldFrame

class BusquedaEstimulos(Frame):
  def __init__(self, root, estudiante=None):
    super().__init__(root)
    self._estudiante = estudiante
    self._root = root
    self._entradas = []

    def handleAceptar() :
      frameInteraccion.getEntradasUsuario()
      self._entradas = frameInteraccion._entradasUsuario
      root.cleanRoot()
      self.recomendar()

    menuBar = Menu(root)
    root.config(menu=menuBar)
    archivo = Menu(menuBar, tearoff=False)
    menuBar.add_cascade(label="Archivo", menu=archivo)
    archivo.add_command(label="Salir", command=root.salir)

    titulo = "BUSQUEDA DE ESTÍMULOS"
    descripcion = "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo."
    frameInteraccion = FieldFrame(root, titulo, descripcion, ['¿Incluir libre elección?'], valores=['radio'])
    frameInteraccion.crearBoton("Aceptar", handleAceptar).grid(row=0, column=0)
    frameInteraccion.crearBoton("Borrar").grid(row=0, column=1)
    frameInteraccion.pack()

    if estudiante:
      BusquedaEstimulos.buscarEstimulos(estudiante, None)


  @staticmethod
  def buscarEstimulos(estudiante=None, profesor=None):
    if estudiante:
      estimulosEstudiante = BusquedaEstimulos.obtenerEstimulosEstudiante()
      estimulosALosQueAplica = [False] * len(estimulosEstudiante)
      totalAplicables = 0

      for i in range(len(estimulosEstudiante)):
        if estimulosEstudiante[i].verificarRequisitos(estudiante):
          estimulosALosQueAplica[i] = True
          totalAplicables += 1

      print("Usted aplica a " + str(totalAplicables) + " estimulos")
      for i in range(len(estimulosEstudiante)):
        if estimulosALosQueAplica[i]:
          BusquedaEstimulos.imprimirEstimulo(
            str(j) + ".",
            estimulosEstudiante[i],
            estimulosEstudiante[i].obtenerCriterios(),
          )
          j += 1

      print("Otros estimulos para estudiantes")
      for i in range(len(estimulosEstudiante)):
        if not estimulosALosQueAplica[i]:
          BusquedaEstimulos.imprimirEstimulo(
            str(j) + ".",
            estimulosEstudiante[i],
            estimulosEstudiante[i].obtenerCriterios(),
          )
          j += 1
    elif profesor:
      estimulosProfesor = BusquedaEstimulos.obtenerEstimulosProfesor()
      estimulosALosQueAplica = [False] * len(estimulosProfesor)
      totalAplicables = 0

      for i in range(len(estimulosProfesor)):
        if estimulosProfesor[i].verificarRequisitos(profesor):
          estimulosALosQueAplica[i] = True
          totalAplicables += 1

      j = 1
      print("Usted aplica a " + str(totalAplicables) + " estimulos")
      for i in range(len(estimulosProfesor)):
        if estimulosALosQueAplica[i]:
          BusquedaEstimulos.imprimirEstimulo(
            str(j) + ".",
            estimulosProfesor[i],
            estimulosProfesor[i].obtenerCriterios(),
          )
          j += 1

      j = 1
      print("Otros estimulos para profesores")
      for i in range(len(estimulosProfesor)):
        if not estimulosALosQueAplica[i]:
          BusquedaEstimulos.imprimirEstimulo(
            str(j) + ".",
            estimulosProfesor[i],
            estimulosProfesor[i].obtenerCriterios(),
          )
          j += 1
    else:
      estimulosEstudiante = BusquedaEstimulos.obtenerEstimulosEstudiante()
      estimulosProfesor = BusquedaEstimulos.obtenerEstimulosProfesor()

      estudiantesQueAplican = [[] for _ in range(len(estimulosEstudiante))]
      profesoresQueAplican = [[] for _ in range(len(estimulosProfesor))]

      for i in range(len(estimulosEstudiante)):
        estudiantesQueAplican[i] = estimulosEstudiante[i].obtenerAplicantes()

      for i in range(len(estimulosProfesor)):
        profesoresQueAplican[i] = estimulosProfesor[i].obtenerAplicantes()

      print("\nEstimulos para estudiantes:")
      for i in range(len(estimulosEstudiante)):
        BusquedaEstimulos.imprimirEstimulo(
          str(i + 1) + ".",
          estimulosEstudiante[i],
          estimulosEstudiante[i].obtenerCriterios(),
          list(estudiantesQueAplican[i]),
        )

      print("\nEstimulos para profesores:")
      for i in range(len(estimulosProfesor)):
        BusquedaEstimulos.imprimirEstimulo(
          str(i + 1) + ".",
          estimulosProfesor[i],
          estimulosProfesor[i].obtenerCriterios(),
          list(profesoresQueAplican[i]),
        )


  @staticmethod
  def buscarEstimulosPorId():
    while True:
      print("Digite el nombre del usuario a consultar: ")
      id = input()

      if len(id) == 0 or not id.isalpha():
        print("Digite un nombre válido (solo a-z A-Z)")
        continue

      estudiantePorId = None
      profesorPorId = None

      for estudiante in Registro.getEstudiantes():
        if estudiante.getNombre() == id:
          estudiantePorId = estudiante

      for profesor in Registro.getProfesores():
        if profesor.getNombre() == id:
          profesorPorId = profesor

      if estudiantePorId:
        estimulos = BusquedaEstimulos.obtenerEstimulosEstudiante()
        estimulosALosQueAplica = [False] * len(estimulos)
        totalAplicables = 0

        for i in range(len(estimulos)):
          if estimulos[i].verificarRequisitos(estudiantePorId):
            estimulosALosQueAplica[i] = True
            totalAplicables += 1

        print("Hay [" + str(totalAplicables) + "] estímulos a los que aplica el Estudiante " + estudiantePorId.getNombre())

        for i in range(len(estimulos)):
          if estimulosALosQueAplica[i]:
            BusquedaEstimulos.imprimirEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            )

        print("Otros estimulos para estudiantes: ")
        for i in range(len(estimulos)):
          if not estimulosALosQueAplica[i]:
            BusquedaEstimulos.imprimirEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            )
      elif profesorPorId:
        estimulos = BusquedaEstimulos.obtenerEstimulosProfesor()
        estimulosALosQueAplica = [False] * len(estimulos)
        totalAplicables = 0

        for i in range(len(estimulos)):
          if estimulos[i].verificarRequisitos(profesorPorId):
            estimulosALosQueAplica[i] = True
            totalAplicables += 1

          print("Hay [" + str(totalAplicables) + "] estímulos a los que aplica el Profesor " + profesorPorId.getNombre())

        for i in range(len(estimulos)):
          if estimulosALosQueAplica[i]:
            BusquedaEstimulos.imprimirEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            )

        print("Otros estimulos para profesores: ")
        for i in range(len(estimulos)):
          if not estimulosALosQueAplica[i]:
            BusquedaEstimulos.imprimirEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            )
      else:
        print("No hay un Estudiante o Profesor con id: " + id)

      continuarPrograma = False

      while True:
        print("1. Consultar otro usuario por id\n" + "2. Salir\n")
        opcion = input()
        opciones = ["1", "2"]

        if opcion not in opciones:
          print("Debe seleccionar un número entre el 1 y el 2")
          continue

        if opcion == "1":
          continuarPrograma = True
        elif opcion == "2":
          continuarPrograma = False
        
        break

      if not continuarPrograma:
        break

  @staticmethod
  def obtenerEstimulosEstudiante():
    estimulos = []
    for e in Registro.getEstimulosEstudiantes():
      if e.getAQuienAplica() == TipoUsuarios.ESTUDIANTE:
        estimulos.append(e)
    return estimulos
  
  @staticmethod
  def obtenerEstimulosProfesor():
    estimulos = []
    for e in Registro.getEstimulosProfesores():
      if e.getAQuienAplica() == TipoUsuarios.PROFESOR:
        estimulos.append(e)
    return estimulos

  @staticmethod
  def imprimirEstimulo(sep, estimulo, criterios, aplicantes=None):
    if aplicantes:
      BusquedaEstimulos.imprimirEstimuloCompleto(sep, "\n", estimulo, aplicantes, True, criterios)
    else:
      BusquedaEstimulos.imprimirEstimuloCompleto(sep, "\n", estimulo, [], False, criterios)

  @staticmethod
  def imprimirEstimuloCompleto(sep, end="\n", estimulo=None, aplicantes=[], withAplicantes=False, criterios=[]):
    print(sep)
    print("\tNombre: " + estimulo.getNombre())
    print("\tDescripcion: " + estimulo.getDescripcion())
    print("\tCupos: " + estimulo.getCupos())

    if len(aplicantes) > 0 and withAplicantes:
      print("\tAplican [" + str(len(aplicantes)) + "]:")
      for aplicante in aplicantes:
        print("\t\t-" + aplicante.getNombre())
    elif withAplicantes:
      print("\tAplican [0]: No aplica ningún usuario")

    print("\tCriterios:")
    for criterio in criterios:
      print("\t\t" + criterio)

    print(end)
