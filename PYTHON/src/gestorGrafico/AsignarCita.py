from tkinter import LEFT, RIGHT, Y, Button, messagebox, Entry, Frame, Label, Scrollbar, Menu, StringVar, Text, Tk, Toplevel, messagebox, ttk, Listbox, MULTIPLE, END
from gestorGrafico import UserWindow
from gestorGrafico.Root import Root
from gestorAplicacion.clasesDeUsuario.Registro import Registro

class AsignarCita:
    estudiantesConCita = []
    citasDisponibles = ["06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
                        "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
                        "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
                        "18:00"]
    

    def __init__(self, root:Root, user):
        self._user = user
        self.root = root
        root.state("zoomed")
        root.title("Asignar Citas de Inscripción")
        
        menuBar = Menu(root)
        root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=root.salir)

        self.asignadosProvicionales = []

        self.titulo = Frame(root)
        self.titulo.pack(side="top")
        self.saludo = Label(self.titulo, text="Asignar Citas de Inscripción", font=("arial", 30))
        self.saludo.pack(padx=5, pady=5, anchor="center")
        self.descripcion = Label(self.titulo, text="Aquí podrás asignarles a los estudiantes sus respectivas citas de inscripción", font=("arial", 15))
        self.descripcion.pack(padx=5, pady=5, anchor="center")

        self.contenido = Frame(root)
        self.contenido.pack()
        self.frame_letf = Frame(self.contenido)
        self.frame_letf.pack(side="left", padx=10, pady=5, fill="both", expand=True)
        
        self.frame_right = Frame(self.contenido)
        self.frame_right.pack(side="right", padx=30, pady=5, fill="both", expand=True)

        self.changua = Frame(self.frame_letf)
        self.changua.pack(side="top",padx=5, pady=5, anchor="center")

        # Crear los botones de selección y deselección
        self.botones = Frame(self.frame_letf)
        self.botones.pack(side="top")
        self.boton_seleccionar = Button(self.botones, text="Seleccionar todos")
        self.boton_seleccionar.pack(side="left", padx=5, pady=5)
        self.boton_seleccionar.bind("<Button-1>", self.seleccionar_todos)
        self.boton_deseleccionar = Button(self.botones, text="Deseleccionar todos")
        self.boton_deseleccionar.pack(side="right", padx=5, pady=5)
        self.boton_deseleccionar.bind("<Button-1>", self.deseleccionar_todos)

        self.limpiador = Button(self.frame_letf, text="Limpiar la selección")
        self.limpiador.pack(padx=5, pady=5, anchor="center", side="bottom", fill="both")
        self.limpiador.bind("<Button-1>", self.limpiar)

        self.boton_confirmar = Button(self.frame_letf, text="Confirmar selección")
        self.boton_confirmar.pack(padx=5, pady=5, anchor="center", side="bottom", fill="both")
        self.boton_confirmar.bind("<Button-1>", self.confimar)

        self.lista = Listbox(self.frame_letf, selectmode=MULTIPLE)
        self.lista.pack(padx=5, pady=5, anchor="center", expand=True, fill="both")
        for estudiante in Registro.getEstudiantes():
            if estudiante not in AsignarCita.estudiantesConCita:
                self.lista.insert(END, estudiante)

        self.buttons1 = Frame(self.frame_right)
        self.buttons1.pack(side="top")

        self.reseteoHardcore = Button(self.buttons1, text="Resetear Cursos")
        self.reseteoHardcore.grid(row=0, column=0, padx=5, pady=5)
        self.reseteoHardcore.bind("<Button-1>", self.hardcore)

        self.reseteoSoft = Button(self.buttons1, text="Resetear citas")
        self.reseteoSoft.grid(row=0, column=1, padx=5, pady=5)
        self.reseteoSoft.bind("<Button-1>", self.soft)

        self.cajaSeleccionados = Text(self.frame_right, wrap="word")
        self.cajaSeleccionados.insert(END, "Aquí se mostrarán los estudiantes seleccionados en el orden de inscripción")
        self.cajaSeleccionados.configure(state="disable")

        scrollbar = Scrollbar(self.frame_right)
        scrollbar.config(command=self.cajaSeleccionados.yview)
        self.cajaSeleccionados.config(yscrollcommand=scrollbar.set)

        # Mostrar la barra de desplazamiento y el widget Text en la ventana
        scrollbar.pack(side="right", fill="y")
        self.cajaSeleccionados.pack()

        self.buttons2 = Frame(self.frame_right)
        self.buttons2.pack(side="bottom")

        self.boton_continuar = Button(self.buttons2, text="Asignar Citas")
        self.boton_continuar.grid(row=0, column=0, padx=5, pady=5)
        self.boton_continuar.bind("<Button-1>", self.continuar)

        self.boton_cancelar = Button(self.buttons2, text="Cancelar Proceso")
        self.boton_cancelar.grid(row=0, column=2, padx=5, pady=5)
        self.boton_cancelar.bind("<Button-1>", self.cancelar)

        

    # Función para seleccionar o deseleccionar todos los elementos
    def seleccionar_todos(self, event):
        self.lista.selection_set(0, END)

    def deseleccionar_todos(self, event):
        self.lista.selection_clear(0, END)

    def confimar(self, event):
        if len(self.lista.curselection())==0:
            messagebox.showerror("Error", "Debe seleccionar a al menos un estudiante")
        else:
            self.cajaSeleccionados.configure(state="normal")
            self.cajaSeleccionados.delete('1.0', END)
            seleccionados = []
            seleccion = self.lista.curselection()
            for indice in seleccion:
                estudiante = Registro.getEstudiantes()[indice]
                seleccionados.append(estudiante)
            ordenados = self.ordenarPorPAPA(seleccionados)
            for estudiante in ordenados:
                self.cajaSeleccionados.insert(END, estudiante.__str__() + "\n")
            self.asignadosProvicionales = ordenados
            self.deseleccionar_todos("e")
            self.cajaSeleccionados.configure(state="disable")
        
    def limpiar(self, event):
        self.cajaSeleccionados.configure(state="normal")
        self.cajaSeleccionados.delete('1.0', END)
        self.asignadosProvicionales = []
        self.cajaSeleccionados.insert(END, "Aquí se mostrarán los estudiantes seleccionados en el orden de inscripción")
        self.cajaSeleccionados.configure(state="disable")

    def continuar(self, event):
        if len(self.asignadosProvicionales) == 0:
            messagebox.showerror("Error", "No se han seleccionado estudiantes")
        else:
            respuesta = messagebox.askyesno("Confirmación", "¿Estás seguro que deseas continuar?")
            if respuesta:
                estudiantes = AsignarCita.estudiantesConCita = self.asignadosProvicionales
                for i in range(len(estudiantes)):
                    if i == 0:
                        estudiantes[i].setInscribir(True)
                    else:
                        estudiantes[i].setInscribir(False)
            self.root.cleanRoot()
            UserWindow.UserWindow(self.root, self._user)

    def cancelar(self, event):
        respuesta = messagebox.askyesno("Confirmación", "¿Estás seguro que deseas cancelar el proceso?")
        if respuesta:
            self.root.cleanRoot()
            UserWindow.UserWindow(self.root, self._user)

    def hardcore(self, event):
        respuesta = messagebox.askyesno("Confirmación", "¿Estás seguro que deseas resetear los cursos?\nEsto borrará a los estudiantes matriculados en cada uno de los cursos\nEsta decisión es irreversible")
        if respuesta:
            for profesor in Registro.getProfesores():
                for curso in profesor.getListaCursos():
                    curso.resetearCurso()

    def soft(self, event):
        respuesta = messagebox.askyesno("Confirmación", "¿Estás seguro que deseas resetear las citas de Inscripción?\nEsto hará que ningun estudiante tenga una\nEsta decisión es irreversible")
        if respuesta:
            self.lista.delete(0, "end")
            AsignarCita.estudiantesConCita = []
            for estudiante in Registro.getEstudiantes():
                estudiante.setInscribir(False)
                self.lista.insert(END, estudiante)
            

    def ordenarPorPAPA(self, estudiantes):
        estudiantesPorPAPA = sorted(estudiantes, key=lambda estudiante: estudiante.calcularPAPA(), reverse=True)
        return estudiantesPorPAPA



