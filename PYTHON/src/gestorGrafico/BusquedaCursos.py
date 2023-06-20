from tkinter import Button, Entry, Frame, Label, Listbox, Menu, Radiobutton, StringVar, Tk, Toplevel, messagebox, ttk
from Errores.HorarioException import HorarioException
from gestorAplicacion.clasesDeCurso.Curso import Curso
from gestorAplicacion.clasesExtra.Carreras import Carreras
from gestorAplicacion.clasesExtra.Facultades import Facultades
from gestorGrafico.FieldFrame import FieldFrame

from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorGrafico.UserWindow import UserWindow

class BusquedaCursos(Frame):
    def __init__(self, root, estudiante=None) :
        super().__init__(root)
        self._estudiante = estudiante
        self._root = root
        self._entradas = []
    
    
        
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
       
    def buscarCursos(self, estudiante = None, horario = None):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        titulo = "Busqueda de Cursos"
        descripcion = "Aquí podrás buscar las asignaturas disponibles en el sistema y ver sus detalles"
        val = ["Ver todos los cursos","Filtrar cursos por facultad","Filtrar cursos por carreras relacionadas","Filtrar cursos por horario"]
        frameInteraccion = FieldFrame(self._root, titulo, descripcion, ["Indique lo que quiere realizar"], ["combobox"], [val])
        frameInteraccion.pack()
        valoresFrame = None
        for x in self._root.children.values():
            try:
                if x.winfo_name() == "valores":
                    valoresFrame = x
            except KeyError:
                continue
        for x in frameInteraccion.children.values():
            x.configure(state="disabled")
        def activar(e):
            e.widget.configure(state="disabled")
            continuar = Button(valoresFrame, text="Continuar")
            continuar.grid(row=2, column=0, columnspan=2)
            
            if e.widget.get() == "Filtrar cursos por facultad":
                facLabel = Label(valoresFrame, text="Facultad: ")
                facLabel.grid(row=1, column=0)
                valores = ["Minas","Ciencias"]
                facCombo = ttk.Combobox(valoresFrame, values=valores, state="readonly")
                facCombo.grid(row=1, column=1)
                def cont(e):
                    if facCombo.get() != "":
                        for x in valoresFrame.children.values():
                            try:
                                if x.winfo_name() == "tabla":
                                    x.destroy()
                                    break
                            except KeyError:
                                continue
                        if facCombo.get() == "Minas":
                            listaCursos = Curso.filtrarPorFacultad(Registro.getCursos(), Facultades.MINAS)
                        else:
                            listaCursos = Curso.filtrarPorFacultad(Registro.getCursos(), Facultades.CIENCIAS)
                        titl = Label(valoresFrame, text="Los cursos disponibles son los siguientes (seleccione uno para ver sus detalles y dele click a continuar): ")
                        titl.grid(row=3, column=0, columnspan=2)
                        
                        frameTabla = Frame(valoresFrame, name="tabla")
                        tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=len(listaCursos))
                        scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                        scrollbar.pack(side="right", fill="y")
                        tabla.configure(yscrollcommand=scrollbar.set)

                        i = 0
                        for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                            tabla.column(f"#{i+1}", anchor="center")
                            tabla.heading(f"#{i+1}", text=e)
                            i += 1

                        for curso in listaCursos:
                            lis = []
                            for x in curso.getCarrerasRelacionadas():
                                lis.append(x.value[1])
                            items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                            tabla.insert("", "end", values=items)
                        tabla.column("c5", minwidth=0, width=300)
                        tabla.pack()
                        frameTabla.grid(row=4, column=0, columnspan=2)
                    
                    def handleAceptar(e):
                        item = tabla.focus()
                        if item != "":
                            curso = tabla.item(item)["values"][1]
                            curs = None
                            for x in Registro.getCursos():
                                if x.getNombre() == curso:
                                    curs = x
                            if curs != None:
                                self._root.cleanRoot()
                                if estudiante == None and horario == None:
                                    self.mostrarDetalles(curs)
                                elif estudiante != None and horario == None:
                                    self.mostrarDetalles(curs, estudiante)
                                else:
                                    self.mostrarDetalles(curs, estudiante, horario)
                        else:
                            messagebox.showinfo("Error", "Seleccione un curso para ver sus detalles")
                    def handleBorrar(e):
                        self._root.cleanRoot()
                        self.buscarCursos()
                    Aceptar = frameInteraccion.crearBoton("Aceptar")
                    Aceptar.grid(row=0, column=0)
                    Aceptar.bind("<Button-1>", handleAceptar)
                    Borr = frameInteraccion.crearBoton("Borrar")
                    Borr.grid(row=0, column=1)
                    Borr.bind("<Button-1>", handleBorrar)    
                continuar.bind("<Button-1>",cont)
                
            elif e.widget.get() == "Filtrar cursos por carreras relacionadas":
                label = Label(valoresFrame, text="Carrera: ")
                label.grid(row=1, column=0)
                valores = ["Ciencias de la computación","Ingenieria de Sistemas"]
                combo = ttk.Combobox(valoresFrame, values=valores, state="readonly")
                combo.grid(row=1, column=1)
                def cont(e):
                    if combo.get() != "":
                        for x in valoresFrame.children.values():
                            try:
                                if x.winfo_name() == "tabla":
                                    x.destroy()
                                    break
                            except KeyError:
                                continue
                        if combo.get() == "Ciencias de la computación":
                            listaCursos = Curso.filtrarPorCarrera(Registro.getCursos(), Carreras.COMPUTACION)
                        else:
                            listaCursos = Curso.filtrarPorCarrera(Registro.getCursos(), Carreras.SISTEMAS)
                        titl = Label(valoresFrame, text="Los cursos disponibles son los siguientes (seleccione uno para ver sus detalles y dele click a continuar): ")
                        titl.grid(row=3, column=0, columnspan=2)
                        frameTabla = Frame(valoresFrame, name="tabla")
                        tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=len(listaCursos))
                        scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                        scrollbar.pack(side="right", fill="y")
                        tabla.configure(yscrollcommand=scrollbar.set)

                        i = 0
                        for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                            tabla.column(f"#{i+1}", anchor="center")
                            tabla.heading(f"#{i+1}", text=e)
                            i += 1

                        for curso in listaCursos:
                            lis = []
                            for x in curso.getCarrerasRelacionadas():
                                lis.append(x.value[1])
                            items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                            tabla.insert("", "end", values=items)
                        tabla.column("c5", minwidth=0, width=300)
                        tabla.pack()
                        frameTabla.grid(row=4, column=0, columnspan=2)
                    
                    def handleAceptar(e):
                        item = tabla.focus()
                        if item != "":
                            curso = tabla.item(item)["values"][1]
                            curs = None
                            for x in Registro.getCursos():
                                if x.getNombre() == curso:
                                    curs = x
                            if curs != None:
                                self._root.cleanRoot()
                                if estudiante == None and horario == None:
                                    self.mostrarDetalles(curs)
                                elif estudiante != None and horario == None:
                                    self.mostrarDetalles(curs, estudiante)
                                else:
                                    self.mostrarDetalles(curs, estudiante, horario)
                        else:
                            messagebox.showinfo("Error", "Seleccione un curso para ver sus detalles")
                    def handleBorrar(e):
                        self._root.cleanRoot()
                        self.buscarCursos()
                    Aceptar = frameInteraccion.crearBoton("Aceptar")
                    Aceptar.grid(row=0, column=0)
                    Aceptar.bind("<Button-1>", handleAceptar)
                    Borr = frameInteraccion.crearBoton("Borrar")
                    Borr.grid(row=0, column=1)
                    Borr.bind("<Button-1>", handleBorrar)    
                continuar.bind("<Button-1>",cont)
                
            elif e.widget.get() == "Filtrar cursos por horario":
                label = Label(valoresFrame, text="Horario: ")
                label.grid(row=1, column=0)
                entry = Entry(valoresFrame)
                entry.grid(row=1, column=1)
                continuar.grid(row=2, column=0, columnspan=1, padx=5, sticky="e")
                borrar = Button(valoresFrame, text="Borrar")
                borrar.grid(row=2, column=1, padx=5, sticky="w")
                def borrarf(e):
                    entry.delete(0, "end")
                borrar.bind("<Button-1>", borrarf)
                def cont(e):
                    if entry.get() != "":
                        try:
                            BusquedaCursos.errorHorario(entry.get())
                            for x in valoresFrame.children.values():
                                try:
                                    if x.winfo_name() == "tabla":
                                        x.destroy()
                                        break
                                except KeyError:
                                    continue
                            listaCursos = Curso.filtrarPorHorario(Registro.getCursos(), entry.get())
                            titl = Label(valoresFrame, text="Los cursos disponibles son los siguientes (seleccione uno para ver sus detalles y dele click a continuar): ")
                            titl.grid(row=3, column=0, columnspan=2)
                            frameTabla = Frame(valoresFrame, name="tabla")
                            tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=len(listaCursos))
                            scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                            scrollbar.pack(side="right", fill="y")
                            tabla.configure(yscrollcommand=scrollbar.set)

                            i = 0
                            for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                                tabla.column(f"#{i+1}", anchor="center")
                                tabla.heading(f"#{i+1}", text=e)
                                i += 1

                            for curso in listaCursos:
                                lis = []
                                for x in curso.getCarrerasRelacionadas():
                                    lis.append(x.value[1])
                                items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                                tabla.insert("", "end", values=items)
                            tabla.column("c5", minwidth=0, width=300)
                            tabla.pack()
                            frameTabla.grid(row=4, column=0, columnspan=2)
                        
                            def handleAceptar(e):
                                item = tabla.focus()
                                if item != "":
                                    curso = tabla.item(item)["values"][1]
                                    curs = None
                                    for x in Registro.getCursos():
                                        if x.getNombre() == curso:
                                            curs = x
                                    if curs != None:
                                        self._root.cleanRoot()
                                        if estudiante == None and horario == None:
                                            self.mostrarDetalles(curs)
                                        elif estudiante != None and horario == None:
                                            self.mostrarDetalles(curs, estudiante)
                                        else:
                                            self.mostrarDetalles(curs, estudiante, horario)
                                else:
                                    messagebox.showinfo("Error", "Seleccione un curso para ver sus detalles")
                            def handleBorrar(e):
                                self._root.cleanRoot()
                                self.buscarCursos()
                            Aceptar = frameInteraccion.crearBoton("Aceptar")
                            Aceptar.grid(row=0, column=0)
                            Aceptar.bind("<Button-1>", handleAceptar)
                            Borr = frameInteraccion.crearBoton("Borrar")
                            Borr.grid(row=0, column=1)
                            Borr.bind("<Button-1>", handleBorrar)
                        except HorarioException as he:
                            messagebox.showerror("Error", he.mostrarMensaje())
                continuar.bind("<Button-1>",cont)
                
            elif e.widget.get() == "Ver todos los cursos":
                continuar.destroy()
                listaCursos = Registro.getCursos()
                titl = Label(valoresFrame, text="Los cursos disponibles son los siguientes (seleccione uno para ver sus detalles y dele click a continuar): ")
                titl.grid(row=3, column=0, columnspan=2)
                

                frameTabla = Frame(valoresFrame, name="tabla")
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=len(listaCursos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                i = 0
                for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1

                for curso in listaCursos:
                    lis = []
                    for x in curso.getCarrerasRelacionadas():
                        lis.append(x.value[1])
                    items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                    tabla.insert("", "end", values=items)
                tabla.column("c5", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=4, column=0, columnspan=2)
                
            
                def handleAceptar(e):
                    item = tabla.focus()
                    if item != "":
                        curso = tabla.item(item)["values"][1]
                        curs = None
                        for x in Registro.getCursos():
                            if x.getNombre() == curso:
                                curs = x
                        if curs != None:
                            self._root.cleanRoot()
                            if estudiante == None and horario == None:
                                self.mostrarDetalles(curs)
                            elif estudiante != None and horario == None:
                                self.mostrarDetalles(curs, estudiante)
                            else:
                                self.mostrarDetalles(curs, estudiante, horario)
                    else:
                        messagebox.showinfo("Error", "Seleccione un curso para ver sus detalles")
                def handleBorrar(e):
                    self._root.cleanRoot()
                    self.buscarCursos()
                Aceptar = frameInteraccion.crearBoton("Aceptar")
                Aceptar.grid(row=0, column=0)
                Aceptar.bind("<Button-1>", handleAceptar)
                Borr = frameInteraccion.crearBoton("Borrar")
                Borr.grid(row=0, column=1)
                Borr.bind("<Button-1>", handleBorrar)   
        for x in valoresFrame.children.values():
            try:
                if x.winfo_name() == "1":
                    x.bind("<<ComboboxSelected>>", activar)
            except KeyError:
                continue
    
    def mostrarDetalles(self, curso, estudiante = None, horario = None):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        if estudiante == None and horario == None:
            grupos = []
            profesores = []
            
            for profesor in Registro.getProfesores():
                for cp in profesor.getListaCursos():
                    if(cp.getNombre() == curso.getNombre()):
                        grupos.append(cp)
                        profesores.append(profesor)
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            titulo = Label(valoresFrame, text="Detalles del curso "+ curso.getNombre(), font=("Arial", 18))
            titulo.grid(row=1, column=0)
            lis = []
            for x in curso.getCarrerasRelacionadas():
                lis.append(x.value[1])
            txt = curso.getNombre()+"("+str(curso.getId())+")\n"+"Creditos: "+str(curso.getCreditos())+"\n"+"Facultad: "+curso.getFacultad()[0].value[1]+"\n"+"Carreras relacionadas: "+", ".join(lis)
            descrip = Label(valoresFrame, text=txt, font=("Arial", 12))
            descrip.grid(row=2, column=0, pady=10)
            if len(grupos) != 0:
                frameTabla = Frame(valoresFrame, name="tabla")
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4"), show="headings", height=len(grupos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                
                i = 0
                for e in ["GRUPO", "PROFESOR", "CUPOS", "HORARIO"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                i = 1
                for grupo in grupos:
                    items = [str(i),profesores[i-1], str(grupo.getCupos()), grupo.getHorario()]
                    tabla.insert("", "end", values=items)
                    i+=1
                tabla.column("c4", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=3, column=0)
            else:
                aviso = Label(valoresFrame, text="El curso seleccionado no tiene profesores asignados", font=("Arial", 15), fg="red")
                aviso.grid(row=3, column=0)
            volver = Button(framebotones, text="Volver")
            volver.grid(row=0, column=0)
            def vol(e):
                self._root.cleanRoot()
                self.buscarCursos()
            volver.bind("<Button-1>", vol)
            
        elif estudiante != None and horario == None:
            grupos = curso.obtenerGrupos(estudiante)
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            titulo = Label(valoresFrame, text="Detalles del curso "+ curso.getNombre(), font=("Arial", 18))
            titulo.grid(row=1, column=0)
            lis = []
            for x in curso.getCarrerasRelacionadas():
                lis.append(x.value[1])
            txt = curso.getNombre()+"("+str(curso.getId())+")\n"+"Creditos: "+str(curso.getCreditos())+"\n"+"Facultad: "+curso.getFacultad()[0].value[1]+"\n"+"Carreras relacionadas: "+", ".join(lis)
            descrip = Label(valoresFrame, text=txt, font=("Arial", 12))
            descrip.grid(row=2, column=0, pady=10)
            nota = Label(valoresFrame, text="Para agregar un curso a un horario existente, debes seleccionar el grupo que quieras y luego darle click a continuar", font=("Arial", 12), pady=5)
            nota.grid(row=3, column=0)
            if len(grupos) != 0:
                frameTabla = Frame(valoresFrame, name="tabla")
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4"), show="headings", height=len(grupos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                
                i = 0
                for e in ["GRUPO", "PROFESOR", "CUPOS", "HORARIO"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                i = 1
                for grupo in grupos:
                    items = [str(i),grupo.getProfesor(), str(grupo.getCupos()), grupo.getHorario()]
                    tabla.insert("", "end", values=items)
                    i+=1
                tabla.column("c4", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=4, column=0)
            else:
                aviso = Label(valoresFrame, text="El curso seleccionado no tiene profesores asignados", font=("Arial", 15), fg="red")
                aviso.grid(row=4, column=0)
            volver = Button(framebotones, text="Volver")
            volver.grid(row=0, column=1)
            continuar = Button(framebotones, text="Continuar")
            continuar.grid(row=0, column=0)
            def cont(e):
                item = tabla.focus()
                if item != "":
                    ng = tabla.item(item)["values"][0]
                    grupo = grupos[int(ng)-1]
                    self._root.cleanRoot()
                    self.agregarCurso(estudiante, grupo)   
                else:
                    messagebox.showinfo("Error", "Seleccione un grupo para poderlo agregar")
            def vol(e):
                self._root.cleanRoot()
                self.buscarCursos()
            continuar.bind("<Button-1>", cont)
            volver.bind("<Button-1>", vol)
        else:
            grupos = curso.obtenerGrupos(estudiante)
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            titulo = Label(valoresFrame, text="Detalles del curso "+ curso.getNombre(), font=("Arial", 18))
            titulo.grid(row=1, column=0)
            lis = []
            for x in curso.getCarrerasRelacionadas():
                lis.append(x.value[1])
            txt = curso.getNombre()+"("+str(curso.getId())+")\n"+"Creditos: "+str(curso.getCreditos())+"\n"+"Facultad: "+curso.getFacultad()[0].value[1]+"\n"+"Carreras relacionadas: "+", ".join(lis)
            descrip = Label(valoresFrame, text=txt, font=("Arial", 12))
            descrip.grid(row=2, column=0, pady=10)
            nota = Label(valoresFrame, text="Para agregarle un curso al horario creado, debes seleccionar el grupo que quieras y luego darle click a continuar", font=("Arial", 12), pady=5)
            nota.grid(row=3, column=0)
            if len(grupos) != 0:
                frameTabla = Frame(valoresFrame, name="tabla")
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4"), show="headings", height=len(grupos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                
                i = 0
                for e in ["GRUPO", "PROFESOR", "CUPOS", "HORARIO"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                i = 1
                for grupo in grupos:
                    items = [str(i),grupo.getProfesor(), str(grupo.getCupos()), grupo.getHorario()]
                    tabla.insert("", "end", values=items)
                    i+=1
                tabla.column("c4", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=4, column=0)
            else:
                aviso = Label(valoresFrame, text="El curso seleccionado no tiene profesores asignados", font=("Arial", 15), fg="red")
                aviso.grid(row=4, column=0)
            volver = Button(framebotones, text="Volver")
            volver.grid(row=0, column=1)
            continuar = Button(framebotones, text="Continuar")
            continuar.grid(row=0, column=0)
            def cont(e):
                item = tabla.focus()
                if item != "":
                    ng = tabla.item(item)["values"][0]
                    grupo = grupos[int(ng)-1]
                    horario.agregarCurso(grupo)
                    UserWindow(self._root, estudiante)  
                else:
                    messagebox.showinfo("Error", "Seleccione un grupo para poderlo agregar")
            def vol(e):
                self._root.cleanRoot()
                self.buscarCursos()
            continuar.bind("<Button-1>", cont)
            volver.bind("<Button-1>", vol)
            
    def agregarCurso(self, estudiante, grupo):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Salir", command=self._root.salir)
        horarios = estudiante.getHorarios()
        valoresFrame = Frame(self._root)
        valoresFrame.pack(anchor="n")
        framebotones = Frame(self._root)
        framebotones.pack(anchor="s")
        titulo = Label(valoresFrame, text="Horarios creados", font=("Arial", 18))
        titulo.grid(row=1, column=0)
        nota = Label(valoresFrame, text="Escoge el horario que quieras dandole click al boton Seleccionar del horario correspondiente", font=("Arial", 12), pady=5)
        nota.grid(row=2, column=0)
        if len(horarios) != 0:
            y = 0
            listaBotones = []
            for x in horarios:
                label = Label(valoresFrame, text="Horario "+x.getId())
                label.grid(row=3+y, column=0, sticky="e", padx=5)
                selector = Button(valoresFrame, text="Seleccionar", name=str(y))
                selector.grid(row=3+y, column=1, sticky="w", padx=5)
                listaBotones.append(selector)
                descrip = Label(valoresFrame, text="Cursos dentro del horario")
                descrip.grid(row=4+y, column=0, columnspan=2, pady=5)
                frameTabla = Frame(valoresFrame, name="tabla")
                cursos = x.getCursos()
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=len(cursos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                
                i = 0
                for e in ["NOMBRE","CREDITOS","PROFESOR","HORARIO","CUPOS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                for grupo in cursos:
                    items = []
                    tabla.insert("", "end", values=items)
                tabla.column("c4", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=5+y, column=0, columnspan=2)
                y+=1
        else:
            aviso = Label(valoresFrame, text="No se han creado horarios", font=("Arial", 15), fg="red")
            aviso.grid(row=3, column=0)
        volver = Button(framebotones, text="Volver")
        volver.grid(row=0, column=0)
        def cont(e):
            self._root.cleanRoot()
            nh = x.winfo_name()
            horario = horarios[int(nh)]
            horario.agregarCurso(grupo)
            UserWindow(self._root, estudiante)
        def vol(e):
            self._root.cleanRoot()
            curso = None
            for x in Registro.getCursos:
                if grupo.getNombre == x.getNombre():
                    curso = x
                    break
            self.mostrarDetalles(curso, estudiante)
        for x in listaBotones:
            x.bind("<Button-1>", cont)
        volver.bind("<Button-1>", vol)
    
    @classmethod
    def reportarFallo(cls, *args):
        if len(args) == 1:
            messagebox.showinfo("Error", "Ya tienes el curso "+args[0])
        if len(args) == 2:
            messagebox.showinfo("Error", "Hay un problema entre el horario del curso "+args[0].getNombre()+" y el curso "+args[1].getNombre()+"\nHorario de "+args[0].getNombre()+":"+args[0].getHorario()+"\tHorario"+args[1].getNombre()+":"+args[1].getHorario())
    @classmethod
    def aceptar():
        messagebox.showinfo("Proceso excitoso", "El curso se agregó correctamente")

