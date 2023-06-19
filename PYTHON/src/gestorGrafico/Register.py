from tkinter import Button, Entry, Frame, Label, Menu, StringVar, Tk, Toplevel, messagebox, ttk
from Errores.HorarioException import HorarioException
from Errores.CampoErroneoException import CampoErroneoException
from gestorGrafico.UserWindow import UserWindow
from gestorGrafico.Root import Root
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesExtra.Carreras import Carreras
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro

class Register:
    @classmethod
    def errorHorario(cls, horario):
        lis = horario.split()
        dias = ["lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo", "miercoles", "sabado"]
        if len(lis) != 4 or not(lis[0].lower() in dias) or not(lis[0].lower() in dias):
            
            raise HorarioException(horario)
        if "-" in lis[1]:
            horas1 = lis[1].split("-")
            if ":" in horas1[0] and ":" in horas1[1] and len(horas1) == 2:
                h11 = horas1[0].split(":")
                if len(h11) == 2:
                    if len(h11[0]) == 2 and len(h11[1]) == 2:
                        try:
                            int(h11[0])
                            int(h11[1])
                            if not(int(h11[0])<=24 and int(h11[0])>=0 and int(h11[1])<60 and int(h11[1])>=0):
                                raise ValueError
                        except ValueError:
                            
                            raise HorarioException(horario)
                    else:
                        
                        raise HorarioException(horario)
                else:
                    
                    raise HorarioException(horario)
                h12 = horas1[1].split(":")
                if len(h12) == 2:
                    if len(h12[0]) == 2 and len(h12[1]) == 2:
                        try:
                            int(h12[0])
                            int(h12[1])
                            if not(int(h12[0])<=24 and int(h12[0])>=0 and int(h12[1])<60 and int(h12[1])>=0):
                                raise ValueError
                        except ValueError:
                            
                            raise HorarioException(horario)
                    else:
                        
                        raise HorarioException(horario)
                else:
                    
                    raise HorarioException(horario)
            else:
               
               raise HorarioException(horario)
        else:
            
            raise HorarioException(horario)
        if abs(int(h11[1])-int(h12[1]))>0:
            if abs(int(h11[0])-int(h12[0]))>3:
                raise HorarioException(horario)
        else:
            if abs(int(h11[0])-int(h12[0]))>4:
                raise HorarioException(horario)
        if "-" in lis[3]:
            horas2 = lis[3].split("-")
            if ":" in horas2[0] and ":" in horas2[1] and len(horas2) == 2:
                h21 = horas2[0].split(":")
                if len(h21) == 2:
                    if len(h21[0]) == 2 and len(h21[1]) == 2:
                        try:
                            int(h21[0])
                            int(h21[1])
                            if not(int(h21[0])<=24 and int(h21[0])>=0 and int(h21[1])<60 and int(h21[1])>=0):
                                raise ValueError
                        except ValueError:
                            
                            raise HorarioException(horario)
                    else:
                        
                        raise HorarioException(horario)
                else:
                    
                    raise HorarioException(horario)
                h22 = horas2[1].split(":")
                if len(h22) == 2:
                    if len(h22[0]) == 2 and len(h22[1]) == 2:
                        try:
                            int(h22[0])
                            int(h22[1])
                            if not(int(h22[0])<=24 and int(h22[0])>=0 and int(h22[1])<60 and int(h22[1])>=0):
                                raise ValueError
                        except ValueError:
                            
                            raise HorarioException(horario)
                    else:
                        
                        raise HorarioException(horario)
                else:
                    
                    raise HorarioException(horario)
            else:
               
               raise HorarioException(horario)
        else:
            
            raise HorarioException(horario)
        if abs(int(h21[1])-int(h22[1]))>0:
            if abs(int(h21[0])-int(h22[0]))>3:
                raise HorarioException(horario)
        else:
            if abs(int(h21[0])-int(h22[0]))>4:
                raise HorarioException(horario)
    @classmethod
    def errorEntradas(cls, lis, entradaDocumento=None):
        con = 1
        for x in lis:
            if x == entradaDocumento:
                try:
                    int(x.get())
                except ValueError:
                    raise CampoErroneoException(con)
            else:
                if x.get() == "":
                    raise CampoErroneoException(con)
            con+=1
    @classmethod
    def register(cls, root:Root):
        
        root.state("zoomed")
        root.title("Registro")
        
        menuBar = Menu(root)
        root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=root.salir)
        frame1 = Frame(root, width=400, height=400)
        frame1.pack(expand=True)
        frame11 = Frame(frame1)
        frame11.pack(anchor="n")
        frame12 = Frame(frame1)
        frame12.pack(anchor="c", pady=10)
        frame13 = Frame(frame1)
        frame13.pack(anchor="s")
        frame14 = Frame(frame1)
        frame14.pack(anchor="s",pady=10)
        res = Label(frame1) 
        res.pack(anchor="s", pady=10)
        titulo = Label(frame11, text="Registro", font=("Arial", 20))
        titulo.pack()
        label1 = Label(frame12, text="Tipo de usuario:")
        label1.grid(row=0, column=0)
        val = ["Estudiante", "Profesor", "Admin"]
        combobox = ttk.Combobox(frame12, values=val, state="readonly")
        combobox.set("Usuarios")
        combobox.grid(row=0, column=1)
        label2 = Label(frame12, text="Nombre:")
        label2.grid(row=1, column=0)
        entry1 = Entry(frame12)
        entry1.grid(row=1, column=1)
        label3 = Label(frame12, text="Nombre de usuario:")
        label3.grid(row=2, column=0)
        entry2 = Entry(frame12)
        entry2.grid(row=2, column=1)
        boton1 = Button(frame13, text="Continuar")
        boton1.pack(side="left", padx=5)
        boton2 = Button(frame13, text="Borrar")
        boton2.pack(side="right", padx=5)
        
        
        def handle(e):
            boton = e.widget.cget("text")
            if boton != "Continuar":
                entry1.delete(0, "end")
                entry2.delete(0, "end")
                combobox.set("Usuarios")
            else:
                try:
                    lis = []
                    for x in frame12.children.values():
                        if type(x) is Entry:
                            lis.append(x)
                    Register.errorEntradas(lis)
                    tu = combobox.get()
                    nombre = entry1.get()
                    nombreUsuario = entry2.get()
                    comp = True
                    if(tu == "Estudiante"):
                        for us in Registro.getEstudiantes():
                            if(us.getNombreUsuario() == nombreUsuario or us.getNombre() == nombre):
                                res.configure(text="El Nombre o el nombre de usuario ya existen")
                                combobox.set("Usuarios")
                                entry1.delete(0,"end")
                                entry2.delete(0,"end")
                                comp = False
                                break
                    
                    elif(tu == "Profesor"):
                        for us in Registro.getProfesores():
                            if(us.getNombreUsuario() == nombreUsuario or us.getNombre() == nombre):
                                res.configure(text="El Nombre o el nombre de usuario ya existen")
                                combobox.set("Usuarios")
                                entry1.delete(0,"end")
                                entry2.delete(0,"end")
                                comp = False
                                break
                            
                    elif(tu == "Admin"):
                        for us in Registro.getAdmins():
                            if(us.getNombreUsuario() == nombreUsuario or us.getNombre() == nombre):
                                res.configure(text="El Nombre o el nombre de usuario ya existen")
                                combobox.set("Usuarios")
                                entry1.delete(0,"end")
                                entry2.delete(0,"end")
                                comp = False
                                break
                    else:
                        res.configure(text="Debe seleccionar un tipo de usuario")
                        combobox.set("Usuarios")
                        entry1.delete(0,"end")
                        entry2.delete(0,"end")
                        comp = False         
                    if comp == True:
                        res.configure(text="")
                        for x in frame12.children.values():
                            x.configure(state="disabled")
                        lis = []
                        for x in frame13.children.values():
                            lis.append(x)
                        for x in lis:
                            x.destroy()
                        lis = []
                        for x in frame14.children.values():
                            lis.append(x)
                        for x in lis:
                            x.destroy()
                        
                        
                        label3 = Label(frame13, text="Clave")
                        label3.grid(row=0, column=0)
                        entry3 = Entry(frame13, show="*")
                        entry3.grid(row=0, column=1)
                        label4 = Label(frame13, text="Correo:")
                        label4.grid(row=1, column=0)
                        entry4 = Entry(frame13)
                        entry4.grid(row=1, column=1)
                        label5 = Label(frame13, text="Documento:")
                        label5.grid(row=2, column=0)
                        entry5 = Entry(frame13)
                        entry5.grid(row=2, column=1)
                        if(tu == "Estudiante"):
                            label6 = Label(frame13, text="Carrera:")
                            label6.grid(row=3, column=0)
                            val1 = ["Ingenieria de Sistemas", "Ciencias de la Computacion"]
                            combobox1 = ttk.Combobox(frame13, values=val1, state="readonly")
                            combobox1.set("Carreras")
                            combobox1.grid(row=3, column=1)
                            label7 = Label(frame13, text="Semestre:")
                            label7.grid(row=4, column=0)
                            val2 = ["1","2","3","4","5","6","7","8","9","10"]
                            combobox2 = ttk.Combobox(frame13, values=val2, state="readonly")
                            combobox2.set("Semestre")
                            combobox2.grid(row=4, column=1) 
                            boton1 = Button(frame14, text="Continuar")
                            boton1.pack(side="left", padx=5)
                            boton2 = Button(frame14, text="Borrar")
                            boton2.pack(side="right", padx=5)
                            def continuarf(e):
                                try:
                                    lis = []
                                    for x in frame12.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    for x in frame13.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    Register.errorEntradas(lis, entry5)
                                    if combobox1.get() == "Carrera":
                                        res.configure(text="Selecciona una carrera")
                                    elif combobox2.get() == "Semestre":
                                        res.configure(text="Selecciona un semestre")
                                    else:
                                        clave = entry3.get()
                                        correo = entry4.get()
                                        documento = entry5.get()
                                        carrera = combobox1.get()
                                        semestre = int(combobox2.get())
                                        for carr in Carreras:
                                            if carr.value[1] == carrera:
                                                facultad = carr.value[2]
                                                break
                                        estudiante = Estudiante(nombre, correo, nombreUsuario, clave, documento, carrera, facultad, semestre)
                                        root.cleanRoot()
                                        UserWindow(root, estudiante)
                                except CampoErroneoException as ce:
                                    messagebox.showerror("Error", ce.mostrarMensaje())
                                    res.configure(text="")
                                    lis = []
                                    for x in frame13.children.values():
                                        lis.append(x)
                                    for x in lis:
                                        if type(x) is Entry:
                                            x.delete(0, "end")
                                    combobox1.set("Carrera")
                                    combobox2.set("Semestre")
                            def borrarf(e):
                                res.configure(text="")
                                lis = []
                                for x in frame13.children.values():
                                    lis.append(x)
                                for x in lis:
                                    if type(x) is Entry:
                                        x.delete(0, "end")
                                combobox1.set("Carrera")
                                combobox2.set("Semestre")
                            boton2.bind("<Button-1>",borrarf)
                            boton1.bind("<Button-1>",continuarf)
                        elif(tu == "Profesor"):
                            cursos = []
                            
                            label3.grid(row=0, column=0, columnspan=2, sticky="e")
                            entry3.grid(row=0, column=2, columnspan=2, sticky="w")
                            label4.grid(row=1, column=0, columnspan=2, sticky="e")
                            entry4.grid(row=1, column=2, columnspan=2, sticky="w")
                            label5.grid(row=2, column=0, columnspan=2, sticky="e")
                            entry5.grid(row=2, column=2, columnspan=2, sticky="w")
                            label = Label(frame13, text="Seleccione los cursos que dicta a continuación")
                            label.grid(row=3, column=0, columnspan=4, pady=10)
                            ej = Label(frame13, text="Ejemplo de la entrada para el horario: Martes 12:00-14:00 Jueves 14:00-16:00")
                            ej.grid(row=4, column=0, columnspan=4, pady=10)
                            label6 = Label(frame13, text="Seleccione un curso:")
                            label6.grid(row=5, column=0)
                            val1 = ["Curso God"]
                            for x in Registro.getCursos():
                                val1.append(x.getNombre())
                            combobox1 = ttk.Combobox(frame13, values=val1, state="readonly")
                            combobox1.set("Cursos")
                            combobox1.grid(row=5, column=1, padx=10)
                            label7 = Label(frame13, text="Horario en el que dicta el curso:")
                            label7.grid(row=5, column=2, padx=10)
                            entry6 = Entry(frame13, state="disabled")
                            entry6.grid(row=5, column=3)
                            continuar = Button(frame14, text="Continuar")
                            continuar.pack(side="left", padx=5)
                            anadir = Button(frame14, text="Añadir")
                            anadir.pack(side="left", after=continuar, padx=5)
                            borrar = Button(frame14, text="Borrar")
                            borrar.pack(side="left", after=anadir, padx=5)
                            def selected(e):
                                opcion = e.widget.get()
                                if opcion != "Cursos":
                                    entry6.configure(state="normal") 
                            
                            def anadirf(e):
                                opcion = combobox1.get()
                                if opcion != "Cursos":
                                    curso = None
                                    for c in Registro.getCursos():
                                        if c.getNombre() == opcion:
                                            curso = c
                                            break
                                    horario = entry6.get()
                                    try:
                                        Register.errorHorario(horario)
                                        #cp = CursoProfesor(curso.getNombre(), curso.getId(), curso.getCreditos(), curso.getNumeroParciales(), curso.getListaPorcentajes(), curso.getFacultad(), horario)
                                        cursos.append(cp)
                                        val1.pop(val1.index(opcion))
                                    except HorarioException as he:
                                        messagebox.showerror("Error", he.mostrarMensaje())
                                    combobox1.set("Cursos")
                                    entry6.delete(0, "end")
                                    entry6.configure(state="disabled")
                                    anadir.after(2000, anadir.config(state="normal"))
                            def borrarf(e):
                                res.configure(text="")
                                lis = []
                                for x in frame13.children.values():
                                    lis.append(x)
                                for x in lis:
                                    if type(x) is Entry:
                                        x.delete(0, "end")
                                    combobox1.set("Cursos")
                            def continuarf(e):
                                try:
                                    lis = []
                                    for x in frame12.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    for x in frame13.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    Register.errorEntradas(lis, entry5)
                                    for x in frame13.children.values():
                                        x.configure(state="disabled")
                                    lis = []
                                    for x in frame14.children.values():
                                        lis.append(x)
                                    for x in lis:
                                        x.destroy()
                                    
                                    label8 = Label(frame13, text="Facultad:")
                                    label8.grid(row=6, column=0, columnspan=2, sticky="e")
                                    val2 = ["Minas", "Ciencias"]
                                    combobox2 = ttk.Combobox(frame13, values=val2, state="readonly")
                                    combobox2.set("Facultades")
                                    combobox2.grid(row=6, column=2, padx=10, columnspan=2, sticky="w")
                                    boton3 = Button(frame14, text="Continuar")
                                    boton3.pack(side="left", padx=5)
                                    boton4 = Button(frame14, text="Borrar")
                                    boton4.pack(side="right", padx=5)
                                    def cont(e):
                                        try:
                                            lis = []
                                            for x in frame12.children.values():
                                                if type(x) is Entry:
                                                    lis.append(x)
                                            for x in frame13.children.values():
                                                if type(x) is Entry:
                                                    lis.append(x)
                                            Register.errorEntradas(lis)
                                            if combobox2.get() == "Facultades":
                                                res.configure(text="Selecciona una facultad")
                                            else:
                                                clave = entry3.get()
                                                correo = entry4.get()
                                                documento = entry5.get()
                                                facultad = combobox2.get()
                                                profesor = Profesor(nombre, correo, nombreUsuario, clave, documento, cursos, facultad)
                                                for cp in cursos:
                                                    for curso in Registro.getCursos():
                                                        if(cp.getNombre() == curso.getNombre()):
                                                            curso.agregarProfesor(profesor)
                                                root.cleanRoot()
                                                UserWindow(root, profesor)
                                        except CampoErroneoException as ce:
                                            messagebox.showerror("Error", ce.mostrarMensaje())
                                            combobox2.set("Facultades")
                                    def borr(e):
                                        combobox2.set("Facultades")
                                    boton4.bind("<Button-1>",borr)
                                    boton3.bind("<Button-1>",cont)
                                except CampoErroneoException as ce:
                                    messagebox.showerror("Error", ce.mostrarMensaje())
                                    res.configure(text="")
                                    lis = []
                                    for x in frame13.children.values():
                                        lis.append(x)
                                    for x in lis:
                                        if type(x) is Entry:
                                            x.delete(0, "end")
                                        combobox1.set("Cursos")
                            continuar.bind("<Button-1>",continuarf)
                            anadir.bind("<Button-1>",anadirf)
                            borrar.bind("<Button-1>",borrarf)
                            combobox1.bind("<<ComboboxSelected>>", selected)
                        elif(tu == "Admin"):
                            boton1 = Button(frame14, text="Continuar")
                            boton1.pack(side="left", padx=5)
                            boton2 = Button(frame14, text="Borrar")
                            boton2.pack(side="right", padx=5)
                            def continuarf(e):
                                try:
                                    lis = []
                                    for x in frame12.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    for x in frame13.children.values():
                                        if type(x) is Entry:
                                            lis.append(x)
                                    Register.errorEntradas(lis, entry5)
                                    clave = entry3.get()
                                    correo = entry4.get()
                                    documento = entry5.get()
                                    admin = Admin(nombre, correo, nombreUsuario, clave, documento)
                                    root.cleanRoot()
                                    UserWindow(root, admin)
                                except CampoErroneoException as ce:
                                    messagebox.showerror("Error", ce.mostrarMensaje())
                                    for x in frame13.children.values():
                                        if type(x) is Entry:
                                            x.delete(0, "end")
                            def borrarf(e):
                                for x in frame13.children.values():
                                    if type(x) is Entry:
                                        x.delete(0, "end")
                            boton2.bind("<Button-1>",borrarf)
                            boton1.bind("<Button-1>",continuarf)
                except CampoErroneoException as ce:
                    messagebox.showerror("Error", ce.mostrarMensaje())
                    entry1.delete(0, "end")
                    entry2.delete(0, "end")
                    combobox.set("Usuarios")
        boton2.bind("<Button-1>",handle)
        boton1.bind("<Button-1>",handle)