from tkinter.ttk import Treeview
from tkinter import Frame, Label, Menu, Button, Scrollbar, Listbox, CENTER
from gestorGrafico.Root import Root
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesDeCurso.Curso import Curso
from gestorGrafico.FieldFrame import FieldFrame
from Errores.TablaSinSeleccionException import TablaSinSeleccionException

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

        def handleVolver() :
            from gestorGrafico.UserWindow import UserWindow
            root.cleanRoot()
            UserWindow(root, estudiante)

        menuBar = Menu(root)
        root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=root.salir)

        titulo = "RECOMENDACIÓN DE ASIGNATURAS"
        descripcion = "Se te recomendarán materias para cursar el próximo semestre de acuerdo a tu historial académico y carrera."
        frameInteraccion = FieldFrame(root, titulo, descripcion, ['¿Incluir libre elección?'], valores=['radio'])
        frameInteraccion.crearBoton("Aceptar", handleAceptar).grid(row=0, column=0)
        frameInteraccion.crearBoton("Volver", handleVolver).grid(row=0, column=1)
        frameInteraccion.pack()

    def recomendar(self) :
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        # Obtener valor ingresado por el usuario
        incluyeLibreElección = True if self._entradas[0] == 1 else False

        self.cursosParaRecomendar = []

        for curso in Registro.getCursos() :
            vioCurso = self._estudiante.vioCurso(curso)
            if vioCurso : continue

            if incluyeLibreElección and curso.getEsLibreEleccion() :
                self.cursosParaRecomendar.append(curso)
                continue
            elif not incluyeLibreElección and curso.getEsLibreEleccion() : continue

            esDeLaCarrera = self._estudiante.getCarrera() in curso.getCarrerasRelacionadas()
            if not esDeLaCarrera : continue

            vioPrerrequisitos = curso.vioPrerrequisitos(self._estudiante)
            if vioPrerrequisitos : self.cursosParaRecomendar.append(curso)

        if len(self.cursosParaRecomendar) == 0 :
            def handleVolver() :
                from gestorGrafico.UserWindow import UserWindow
                self._root.cleanRoot()
                UserWindow(self._root, self._estudiante)

            frameResultado = Frame(self._root)
            Label(frameResultado, text="No hay cursos para recomendar.", font=("arial", 15)).pack()
            Button(frameResultado, text="Volver", command=handleVolver, font=("arial", 12)).pack()
            frameResultado.pack()
        else :
            self._root.cleanRoot()
            self.recomendar2()

    def recomendar2(self) :
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        frameTitulo = Frame(self._root)
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre.", font=("arial", 15)).pack()
        Label(frameTitulo, text="Seleccione un curso para ver los profesores disponibles.", font=("arial", 12)).pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack()

        # Treeview
        frameTabla = Frame(self._root)
        tabla = Treeview(frameTabla, column=("c1", "c2", "c3", "c4"), show='headings', height=len(self.cursosParaRecomendar))

        i = 0
        for e in ['ID', 'NOMBRE', 'CREDITOS', 'FACULTAD'] :
            tabla.column(f"#{i+1}", anchor=CENTER)
            tabla.heading(f"#{i+1}", text=e)
            i += 1

        for curso in self.cursosParaRecomendar :
            tabla.insert('', 'end', text="1", values=(curso.getId(), curso.getNombre(), curso.getCreditos(), curso.getFacultad()[0].value[1]))
        tabla.pack()
        frameTabla.pack()

        def handleAceptar() :
            curItem = tabla.focus()
            nombreCurso = tabla.item(curItem)['values'][1]
            self._entradas = [nombreCurso]
            self._root.cleanRoot()
            self.recomendar3()

        frameBotones = Frame(self._root)
        Button(frameBotones, text="Aceptar", command=handleAceptar, font=("arial", 12)).pack()      
        frameBotones.pack()

    def recomendar3(self) :
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        # Encontrar curso
        for curso in Registro.getCursos() :
            if curso.getNombre() == self._entradas[0] :
                cursoDeInteres = curso
                break
        
        nombresProfesoresDelCurso = []
        for profesor in cursoDeInteres.getProfesoresQueDictanElCurso() :
            nombresProfesoresDelCurso.append(profesor.getNombre())

        self.listaProfesores = []
        for profesor in Registro.getProfesores() :
            if profesor.getNombre() in nombresProfesoresDelCurso :
                self.listaProfesores.append(profesor)

        if len(self.listaProfesores) == 0 :
            def handleVolver() :
                self._root.cleanRoot()
                RecomendarAsignaturas(self._root, self._estudiante)

            frameResultado = Frame(self._root)
            Label(frameResultado, text="No hay profesores que dicten el curso.", font=("arial", 20)).pack()
            Button(frameResultado, text="Volver", command=handleVolver, font=("arial", 12)).pack()
            frameResultado.pack()
        else :
            self.recomendar4()

    def recomendar4(self) :
        # Get profesores no calificados
        profesoresNoCalificados = []
        for profesor in self.listaProfesores :
            if not profesor.fueCalificado() : profesoresNoCalificados.append(profesor)

        self.listaProfesores = [p for p in self.listaProfesores if p not in profesoresNoCalificados]
        self.listaProfesores = sorted(self.listaProfesores, key=lambda x : x.getCalificacion(), reverse=True)

        Label(self._root, text="PROFESORES QUE DICTAN EL CURSO DE INTERÉS", font=("arial", 20)).pack()

        # Treeview
        frameTabla = Frame(self._root)
        tabla = Treeview(frameTabla, column=("c1", "c2", "c3"), show='headings', height=len(self.listaProfesores))

        i = 0
        for e in ['NOMBRE', 'CALIFICACION', 'FACULTAD'] :
            tabla.column(f"#{i+1}", anchor=CENTER)
            tabla.heading(f"#{i+1}", text=e)
            i += 1

        for profesor in self.listaProfesores :
            tabla.insert('', 'end', text="1", values=(profesor.getNombre(), profesor.getCalificacion(), profesor.getFacultad().value[1]))
        tabla.pack()
        frameTabla.pack()

        def handleOtroCurso() :
            self._root.cleanRoot()
            RecomendarAsignaturas(self._root, self._estudiante)

        def handleMenuPrincipal() :
            from gestorGrafico.UserWindow import UserWindow
            self._root.cleanRoot()
            UserWindow(self._root, self._estudiante)

        frameBotones = Frame(self._root)
        Button(frameBotones, text="Ver Otro Curso", command=handleOtroCurso, font=("arial", 15)).grid(row=0, column=0)
        Button(frameBotones, text="Menú Principal", command=handleMenuPrincipal, font=("arial", 15)).grid(row=0, column=1)        
        frameBotones.pack()
