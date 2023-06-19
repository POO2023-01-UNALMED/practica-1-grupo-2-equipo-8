from tkinter import Frame, Label, Menu, Button, CENTER
from gestorGrafico.Root import Root
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesDeCurso.Curso import Curso
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

        # Obtener valor ingresado por el usuario
        incluyeLibreElección = True if self._entradas[0] == 1 else False

        """ frameTitulo = Frame(root)
        Label(frameTitulo, text="RECOMENDACION DE ASIGNATURAS").pack()
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:").pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack() """

        self.cursosParaRecomendar = []

        for curso in Registro.getCursos() :
            if incluyeLibreElección and curso.getEsLibreEleccion() :
                self.cursosParaRecomendar.append(curso)
                continue

            esDeLaCarrera = self._estudiante.getCarrera in curso.getCarrerasRelacionadas()
            vioCurso = self._estudiante.vioCurso(curso)
            if vioCurso or not esDeLaCarrera : continue

            vioPrerrequisitos = curso.vioPrerrequisitos(self._estudiante)
            if vioPrerrequisitos : self.cursosParaRecomendar.append(curso)

        if len(self.cursosParaRecomendar) == 0 :
            def handleVolver() :
                from gestorGrafico.UserWindow import UserWindow
                self._root.cleanRoot()
                UserWindow(self._root, self._estudiante)

            frameResultado = Frame(self._root)
            Label(frameResultado, text="No hay cursos para recomendar.").pack()
            Button(frameResultado, text="Volver", command=handleVolver).pack()
            frameResultado.pack()
        else :
            self.recomendar2()

    def recomendar2(self) :
        frameTitulo = Frame(self._root)
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:").pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack()
        
        frameTabla = Frame(self._root)
        rows = []
        i = 0
        for curso in self.cursosParaRecomendar :
            Label(frameTabla, text=curso.getNombre()).grid(row=i, column=0)
            Label(frameTabla, text=curso.getId()).grid(row=i, column=1)
            i += 1
        frameTabla.pack()
