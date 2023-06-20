from tkinter import Button, Entry, Frame, Label, Listbox, Menu, StringVar, Tk, Toplevel, messagebox, ttk, CENTER, StringVar
from tkinter.ttk import Treeview
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesExtra.TipoUsuarios import TipoUsuarios
from gestorGrafico.FieldFrame import FieldFrame
import gestorGrafico.UserWindow
from gestorAplicacion.clasesDeUsuario.Profesor import Profesor

class BusquedaEstimulos(Frame):
  def __init__(self, root, user):
    super().__init__(root)
    self._root = root
    self._user = user
    root.title("Estímulos")

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
    frameInteraccion = FieldFrame(root, titulo, descripcion, [])
    frameInteraccion.pack()

  def buscarEstimulos(self, estudiante=None, profesor=None):  
    if estudiante:
      estimulosEstudiante = BusquedaEstimulos.obtenerEstimulosEstudiante()
      estimulosALosQueAplica = [False] * len(estimulosEstudiante)
      totalAplicables = 0

      for i in range(len(estimulosEstudiante)):
        if estimulosEstudiante[i].verificarRequisitos(estudiante):
          estimulosALosQueAplica[i] = True
          totalAplicables += 1

      title = "Usted aplica a " + str(totalAplicables) + " estimulos: "
      elements = []
      j = 1

      for i in range(len(estimulosEstudiante)):
        if estimulosALosQueAplica[i]:
          elements.append(BusquedaEstimulos.formatearEstimulo(
            str(j) + ".",
            estimulosEstudiante[i],
            estimulosEstudiante[i].obtenerCriterios(),
          ))
          j += 1
      
      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

      title = "Otros estimulos para estudiantes"
      elements = []
      j = 1

      for i in range(len(estimulosEstudiante)):
        if not estimulosALosQueAplica[i]:
          elements.append(BusquedaEstimulos.formatearEstimulo(
            str(j) + ".",
            estimulosEstudiante[i],
            estimulosEstudiante[i].obtenerCriterios(),
          ))
          j += 1

      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

    elif profesor:
      estimulosProfesor = BusquedaEstimulos.obtenerEstimulosProfesor()
      estimulosALosQueAplica = [False] * len(estimulosProfesor)
      totalAplicables = 0

      for i in range(len(estimulosProfesor)):
        if estimulosProfesor[i].verificarRequisitos(profesor):
          estimulosALosQueAplica[i] = True
          totalAplicables += 1

      title = "Usted aplica a " + str(totalAplicables) + " estimulos: "
      elements = []
      j = 1

      for i in range(len(estimulosProfesor)):
        if estimulosALosQueAplica[i]:
          elements.append(BusquedaEstimulos.formatearEstimulo(
            str(j) + ".",
            estimulosProfesor[i],
            estimulosProfesor[i].obtenerCriterios(),
          ))
          j += 1

      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

      title = "Otros estimulos para profesores"
      elements = []
      j = 1

      for i in range(len(estimulosProfesor)):
        if not estimulosALosQueAplica[i]:
          elements.append(BusquedaEstimulos.formatearEstimulo(
            str(j) + ".",
            estimulosProfesor[i],
            estimulosProfesor[i].obtenerCriterios(),
          ))
          j += 1

      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

    else:
      estimulosEstudiante = BusquedaEstimulos.obtenerEstimulosEstudiante()
      estimulosProfesor = BusquedaEstimulos.obtenerEstimulosProfesor()

      estudiantesQueAplican = [[] for _ in range(len(estimulosEstudiante))]
      profesoresQueAplican = [[] for _ in range(len(estimulosProfesor))]

      for i in range(len(estimulosEstudiante)):
        estudiantesQueAplican[i] = estimulosEstudiante[i].obtenerAplicantes()

      for i in range(len(estimulosProfesor)):
        profesoresQueAplican[i] = estimulosProfesor[i].obtenerAplicantes()
  
      title = "Estimulos para estudiantes:"
      elements = []
      for i in range(len(estimulosEstudiante)):
        elements.append(BusquedaEstimulos.formatearEstimulo(
          str(i + 1) + ".",
          estimulosEstudiante[i],
          estimulosEstudiante[i].obtenerCriterios(),
          list(estudiantesQueAplican[i]),
        ))
      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements, ['Nombre', 'Descripción', 'Cupos', 'Aplicantes', 'Criterios'])

      title = "Estimulos para profesores:"
      elements = []
      for i in range(len(estimulosProfesor)):
        elements.append(BusquedaEstimulos.formatearEstimulo(
          str(i + 1) + ".",
          estimulosProfesor[i],
          estimulosProfesor[i].obtenerCriterios(),
          list(profesoresQueAplican[i]),
        ))
      BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements, ['Nombre', 'Descripción', 'Cupos', 'Aplicantes', 'Criterios'])

  def buscarEstimulosPorId(self):
    id = ""
    reEvalue = True
    while True:
      if reEvalue:
        self._root.cleanRoot()
        ans = StringVar()
        frameInteraccion = FieldFrame(self._root, "BUSQUEDA DE ESTÍMULOS", "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo.", [])
        frameInteraccion.pack()
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        frame1 = Frame(self._root, width=400, height=400)
        frame1.pack(expand=True)
        frame11 = Frame(frame1)
        frame11.pack(anchor="n")
        frame12 = Frame(frame1)
        frame12.pack(anchor="c", pady=10)
        frame13 = Frame(frame1)
        frame13.pack(anchor="s")
        res = Label(frame1) 
        res.pack(anchor="s", pady=10)
        label1 = Label(frame12, text="Digite el nombre de usuario a consultar:")
        label1.grid(row=0, column=0)
        entry1 = Entry(frame12)
        entry1.grid(row=1, column=1)
        boton1 = Button(frame13, text="Buscar")
        boton1.pack(side="left", padx=5)
        boton1.bind("<Button-1>",lambda x: ans.set(entry1.get()))
        self._root.wait_variable(ans)
        id = ans.get()

      if len(id) == 0 or not id.replace(" ", "").isalpha():
        self._root.cleanRoot()
        ans = StringVar()
        frameInteraccion = FieldFrame(self._root, "BUSQUEDA DE ESTÍMULOS", "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo.", [])
        frameInteraccion.pack()
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        frame1 = Frame(self._root, width=400, height=400)
        frame1.pack(expand=True)
        frame11 = Frame(frame1)
        frame11.pack(anchor="n")
        frame12 = Frame(frame1)
        frame12.pack(anchor="c", pady=10)
        frame13 = Frame(frame1)
        frame13.pack(anchor="s")
        res = Label(frame1) 
        res.pack(anchor="s", pady=10)
        label1 = Label(frame12, text="Digite un nombre válido (solo a-z A-Z):")
        label1.grid(row=0, column=0)
        entry1 = Entry(frame12)
        entry1.grid(row=1, column=1)
        boton1 = Button(frame13, text="Buscar")
        boton1.pack(side="left", padx=5)
        boton1.bind("<Button-1>",lambda e: ans.set(entry1.get()))
        self._root.wait_variable(ans)
        id = ans.get()
        reEvalue=False
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
        self._root.cleanRoot()
        frameInteraccion = FieldFrame(self._root, "BUSQUEDA DE ESTÍMULOS", "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo.", [])
        frameInteraccion.pack()
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)

        estimulos = BusquedaEstimulos.obtenerEstimulosEstudiante()
        estimulosALosQueAplica = [False] * len(estimulos)
        totalAplicables = 0

        for i in range(len(estimulos)):
          if estimulos[i].verificarRequisitos(estudiantePorId):
            estimulosALosQueAplica[i] = True
            totalAplicables += 1
        title = "Hay [" + str(totalAplicables) + "] estímulos a los que aplica el Estudiante " + estudiantePorId.getNombre()
        elements = []
        for i in range(len(estimulos)):
          if estimulosALosQueAplica[i]:
            elements.append(BusquedaEstimulos.formatearEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            ))

        BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

        title = "Otros estimulos para estudiantes: "
        elements = []
        for i in range(len(estimulos)):
          if not estimulosALosQueAplica[i]:
            elements.append(BusquedaEstimulos.formatearEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            ))

        BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)
      elif profesorPorId:        
        self._root.cleanRoot()
        frameInteraccion = FieldFrame(self._root, "BUSQUEDA DE ESTÍMULOS", "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo.", [])
        frameInteraccion.pack()
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)

        estimulos = BusquedaEstimulos.obtenerEstimulosProfesor()
        estimulosALosQueAplica = [False] * len(estimulos)
        totalAplicables = 0

        for i in range(len(estimulos)):
          if estimulos[i].verificarRequisitos(profesorPorId):
            estimulosALosQueAplica[i] = True
            totalAplicables += 1

        title = "Hay [" + str(totalAplicables) + "] estímulos a los que aplica el Profesor " + profesorPorId.getNombre()
        elements = []
        for i in range(len(estimulos)):
          if estimulosALosQueAplica[i]:
            elements.append(BusquedaEstimulos.formatearEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            ))

        BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)

        title = "Otros estimulos para profesores: "
        elements = []
        for i in range(len(estimulos)):
          if not estimulosALosQueAplica[i]:
            elements.append(BusquedaEstimulos.formatearEstimulo(
              str(i + 1) + ".",
              estimulos[i],
              estimulos[i].obtenerCriterios()
            ))

        BusquedaEstimulos.imprimirEstimuloTabla(self._root, title, elements)
      else:
        self._root.cleanRoot()
        ans = StringVar()
        frameInteraccion = FieldFrame(self._root, "BUSQUEDA DE ESTÍMULOS", "Se mostraran estimulos para los cuales aplicas de acuerdo a los criterios establecidos en cada estímulo.", [])
        frameInteraccion.pack()
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        frame1 = Frame(self._root, width=400, height=400)
        frame1.pack(expand=True)
        frame11 = Frame(frame1)
        frame11.pack(anchor="n")
        frame12 = Frame(frame1)
        frame12.pack(anchor="c", pady=10)
        frame13 = Frame(frame1)
        frame13.pack(anchor="s")
        res = Label(frame1) 
        res.pack(anchor="s", pady=10)
        label1 = Label(frame12, text=f"No hay un Estudiante o Profesor con nombre: {id}")
        label1.grid(row=0, column=0)
        entry1 = Entry(frame12)
        entry1.grid(row=1, column=1)
        boton1 = Button(frame13, text="Buscar")
        boton1.pack(side="left", padx=5)
        boton1.bind("<Button-1>",lambda e: ans.set(entry1.get()))
        self._root.wait_variable(ans)

      continuarPrograma = False

      ans = StringVar()
      frame1 = Frame(self._root, width=400, height=400)
      frame1.pack(expand=True)
      frame11 = Frame(frame1)
      frame11.pack(anchor="n")
      frame12 = Frame(frame1)
      frame12.pack(anchor="c", pady=10)
      frame13 = Frame(frame1)
      frame13.pack(anchor="s")
      res = Label(frame1) 
      res.pack(anchor="s", pady=10)
      label1 = Label(frame12, text="Desea consultar otro nombre?")
      label1.grid(row=0, column=0)
      txtv = StringVar(value="Opciones")
      val = ["SI", "NO"]
      combobox = ttk.Combobox(frame12, values=val, textvariable=txtv, state="readonly")
      combobox.grid(row=0, column=1)
      boton1 = Button(frame13, text="Aceptar")
      boton1.pack(side="left", padx=5)
      boton1.bind("<Button-1>",lambda e: ans.set(combobox.get()))
      self._root.wait_variable(ans)
      
      if ans.get() == ("SI"):
        continuarPrograma = True
        reEvalue = True

      if not continuarPrograma:
        reEvalue = False
        break

    self._root.cleanRoot()
    self._root.salir()

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
  def formatearEstimulo(sep, estimulo, criterios, aplicantes=None):
    print(aplicantes)
    if not aplicantes is None:
      return BusquedaEstimulos.formatearEstimuloCompleto(sep, "\n", estimulo, aplicantes, True, criterios)
    else:
      return BusquedaEstimulos.formatearEstimuloCompleto(sep, "\n", estimulo, [], False, criterios)

  @staticmethod
  def formatearEstimuloCompleto(sep, end="\n", estimulo=None, aplicantes=[], withAplicantes=False, criterios=[]):
    elements = [
      estimulo.getNombre(),
      estimulo.getDescripcion(),
      estimulo.getCupos()
    ]

    if len(aplicantes) > 0 and withAplicantes:
      elements.append(f"Aplican [{len(aplicantes)}]: ")
      for aplicante in aplicantes:
        elements[-1] += aplicante.getNombre() + ","
    elif withAplicantes:
      elements.append("Aplican [0]: No aplica ningún usuario")

    elements.append("")
    for criterio in criterios:
      elements[-1] += criterio + ","

    return elements

  @staticmethod
  def imprimirEstimuloTabla(
    root,
    text,
    elements,
    headings=['Nombre', 'Descripción', 'Cupos', 'Criterios']
  ):
    frameTitulo = Frame(root)
    Label(frameTitulo, text=text).pack()
    frameTitulo.anchor(CENTER)
    frameTitulo.pack()

    frameTabla = Frame(root)
    tabla = Treeview(frameTabla, column=(f"c{i}" for i in range(len(headings))), show='headings', height=len(elements))

    i = 0
    for e in headings:
      tabla.column(f"#{i+1}", anchor=CENTER)
      tabla.heading(f"#{i+1}", text=e)
      i += 1

    for e in elements:
      tabla.insert('', 'end', text="1", values=e)

    tabla.pack()
    frameTabla.pack()
