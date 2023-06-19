from tkinter import Frame, Label, Menu, CENTER
from gestorGrafico.Root import Root
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorGrafico.FieldFrame import FieldFrame

class RecomendarAsignaturas(Frame) :
    def __init__(self, root:Root, estudiante:Estudiante) :
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

        titulo = "RECOMENDACIÓN DE ASIGNATURAS"
        descripcion = "Se te recomendarán materias para cursar el próximo semestre de acuerdo a tu historial académico y carrera."
        frameInteraccion = FieldFrame(root, titulo, descripcion, ['¿Incluir libre elección?'], valores=['radio'])
        frameInteraccion.crearBoton("Aceptar", handleAceptar).grid(row=0, column=0)
        frameInteraccion.crearBoton("Borrar").grid(row=0, column=1)
        frameInteraccion.pack()

    def recomendar(self) :
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)

        frameTitulo = Frame(self._root)
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:").pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack()

        print(self._entradas)

        # Obtener valor ingresado por el usuario
        #incluyeLibreElección = True if self._entradas[0] == 'S' else False

        """ frameTitulo = Frame(root)
        Label(frameTitulo, text="RECOMENDACION DE ASIGNATURAS").pack()
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:").pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack() """

        cursosParaRecomendar = []

        for curso in Registro.getCursos() :
            esDeLaCarrera = self._estudiante.getCarrera in curso.getCarrerasRelacionadas()
            vioCurso = self._estudiante.vioCurso(curso)
            if vioCurso or not esDeLaCarrera : continue

            if not incluyeLibreElección and curso._esLibreEleccion : continue

            vioPrerrequisitos = curso.vioPrerrequisitos(self._estudiante)
            if vioPrerrequisitos : cursosParaRecomendar.append(curso)

        
        