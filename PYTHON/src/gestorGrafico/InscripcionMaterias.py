from tkinter import Button, Entry, Frame, Label, Listbox, Menu, Radiobutton, StringVar, Tk, Toplevel, messagebox, ttk
from Errores.EstudianteSinCitaException import EstudianteSinCitaException

from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesExtra.Horario import Horario
from gestorGrafico.BusquedaCursos import BusquedaCursos
from gestorGrafico.FieldFrame import FieldFrame
from gestorGrafico.Root import Root

class IncripcionMaterias(Frame):
    def __init__(self, root, estudiante=None) :
        super().__init__(root)
        self._estudiante = estudiante
        self._root = root
        self._entradas = []
    
    @classmethod
    def errorCita(cls, estudiante):
        if estudiante.getInscribir() == False:
            raise EstudianteSinCitaException(estudiante)
    
    def inscribirMaterias(self, estudiante):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        titulo = "Busqueda de Cursos"
        descrip = "Aquí podrás inscribir materias en caso de que ya tengas una cita asignada"
        frameInteraccion = FieldFrame(self._root, titulo, descrip)
        frameInteraccion.pack()
        valoresFrame = None
        for x in self._root.children.values():
            try:
                if x.winfo_name() == "valores":
                    valoresFrame = x
            except KeyError:
                continue
        
        try:
            IncripcionMaterias.errorCita(estudiante)
            labelti = Label(valoresFrame, text="Indique el tipo de inscripción: ")
            labelti.grid(row=0, column=0, padx=5)
            vals = ["Incribir materias manualmente", "Incribir materias a partir de un horario creado"]
            tinscripcion = ttk.Combobox(valoresFrame, values=vals, width=80)
            tinscripcion.grid(row=0, column=0, padx=5)
            def selected(e):
                if e.widget.get() == "Incribir materias manualmente":
                    self._root.cleanRoot()
                    self.inscribirManualmente(estudiante)
                if e.widget.get() == "Incribir materias a partir de un horario creado":
                    self._root.cleanRoot()
                    self.inscribirConHorario(estudiante)
            tinscripcion.bind("<<ComboboxSelected>>", selected)    
        except EstudianteSinCitaException as esc:
            txt = esc.mostrarMensaje()
            messagebox.showerror("Error", txt)
            self._root.cleanRoot()
            horario = estudiante.crearHorario() 
            BusquedaCursos(self._root, estudiante).buscarCursos(estudiante, horario)
        
    def inscribirManualmente(self, estudiante, listac = None, cursosres = None):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        if listac == None:
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            tit = Label(valoresFrame, text="Selección de asignaturas", font=("Arial", 18))
            tit.grid(row=0, column=0)
            
            listaCursos = []
            cursosPosibles = Registro.getCursos().copy()
            cursosAprobados = estudiante.getListaCursos().copy()
            
            cursosBorrar = []
            for ce in cursosAprobados:
                if(ce.calcularPromedio()<3):
                    cursosBorrar.append(ce)

            dif = []
            for x in cursosAprobados:
                comp = True
                for y in cursosBorrar:
                    if x == y:
                        comp == False
                if comp == True:
                    dif.append(x)
            cursosAprobados = dif
            cursosABorrar = []
            for c1 in cursosPosibles:
                for c2 in cursosAprobados:
                    if(c1.getNombre() == c2.getNombre()):
                        cursosABorrar.append(c1)
            dif1 = []
            for x in cursosPosibles:
                comp = True
                for y in cursosABorrar:
                    if x == y:
                        comp == False
                if comp == True:
                    dif1.append(x)
            cursosPosibles = dif1

            
            cursosres = cursosPosibles.copy()
            for curso in listaCursos:
                for c in cursosPosibles:
                    if(c.getNombre() == curso.getNombre()):
                        cursosres.remove(cursosres.index(c))
                    
                
            if(len(cursosres) == 0):
                from gestorGrafico.UserWindow import UserWindow
                txt = "No hay cursos disponibles"
                messagebox.showerror("Error", txt)
                self._root.cleanRoot()
                a = UserWindow(self._root, estudiante)
            else:
                selecasig = Label(valoresFrame, text="Seleccione un curso que quiera inscribir:", font=("Arial", 12))
                selecasig.grid(row=1, column=0)
                frameTabla = Frame(valoresFrame, name="tabla")

                l = 20 if len(cursosres) > 20 else len(cursosres)
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=l)
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                i = 0
                for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1

                for curso in cursosres:
                    lis = []
                    for x in curso.getCarrerasRelacionadas():
                        lis.append(x.value[1])
                    items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                    tabla.insert("", "end", values=items)
                tabla.column("c5", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=2, column=0)
                
                continuar = Button(framebotones, text="Continuar")
                continuar.grid(row=0, column=0)
                parar = Button(framebotones, text="No agregar más")
                parar.grid(row=0, column=1, padx=5)
                volver = Button(framebotones, text="Volver")
                volver.grid(row=0, column=2)
                
                def cont(e):
                    item = tabla.focus()
                    if item != "":
                        curso = tabla.item(item)["values"][1]
                        curs = None
                        for x in Registro.getCursos():
                            if x.getNombre() == curso:
                                curs = x
                                break
                        if curs != None:
                            self._root.cleanRoot()
                            self.mostrarDetalles(curs, estudiante, listaCursos, cursosres)
                    else:
                        messagebox.showinfo("Error", "No has seleccionado ningún curso")
                def para(e):
                    if(len(listaCursos) == 0):
                        messagebox.showinfo("Error", "No has inscrito ningún curso")
                    else:
                        horario = Horario(listaCursos)
                        if(horario.validarHorario(estudiante)):
                            from gestorGrafico.UserWindow import UserWindow
                            estudiante.inscribirCursos(listaCursos)
                            messagebox.showinfo("Proceso Exitoso", "La inscripción fue exitosa")
                            self._root.cleanRoot()
                            a = UserWindow(self._root, estudiante)
                        else:
                            self._root.cleanRoot()
                            messagebox.showinfo("Error", "Los cursos seleccionados presentan inconsistencias (hay horarios cruzados o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.")
                            self.inscribirMaterias(estudiante)
                def vol(e):
                    self._root.cleanRoot()
                    self.inscribirMaterias(estudiante)
                continuar.bind("<Button-1>", cont)
                parar.bind("<Button-1>", para)
                volver.bind("<Button-1>", vol)
        else:
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            tit = Label(valoresFrame, text="Selección de asignaturas", font=("Arial", 18))
            tit.grid(row=0, column=0)
            listaCursos = listac
            if(len(cursosres) == 0):
                from gestorGrafico.UserWindow import UserWindow
                txt = "No hay cursos disponibles"
                messagebox.showerror("Error", txt)
                self._root.cleanRoot()
                a = UserWindow(self._root, estudiante)
            else:
                selecasig = Label(valoresFrame, text="Seleccione un curso que quiera inscribir:", font=("Arial", 12))
                selecasig.grid(row=1, column=0)
                frameTabla = Frame(valoresFrame, name="tabla")

                l = 20 if len(cursosres) > 20 else len(cursosres)
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=l)
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                i = 0
                for e in ["ID", "NOMBRE", "CREDITOS", "FACULTAD", "CARRERAS RELACIONADAS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1

                for curso in cursosres:
                    lis = []
                    for x in curso.getCarrerasRelacionadas():
                        lis.append(x.value[1])
                    items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                    tabla.insert("", "end", values=items)
                tabla.column("c5", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=2, column=0)
                
                continuar = Button(framebotones, text="Continuar")
                continuar.grid(row=0, column=0)
                parar = Button(framebotones, text="No agregar más")
                parar.grid(row=0, column=1, padx=5)
                volver = Button(framebotones, text="Volver")
                volver.grid(row=0, column=2)
                
                def cont(e):
                    item = tabla.focus()
                    if item != "":
                        curso = tabla.item(item)["values"][1]
                        curs = None
                        for x in Registro.getCursos():
                            if x.getNombre() == curso:
                                curs = x
                                break
                        if curs != None:
                            comp = True
                            for x in listaCursos:
                                if x.getNombre() == curs.getNombre():
                                    comp = False
                                    messagebox.showinfo("Error", "Ya añadiste este curso")
                                    break
                            if comp == True:
                                self._root.cleanRoot()
                                self.mostrarDetalles(curs, estudiante, listaCursos, cursosres)    
                    else:
                        messagebox.showinfo("Error", "No has seleccionado ningún curso")
                def para(e):
                    if(len(listaCursos) == 0):
                        messagebox.showinfo("Error", "No has inscrito ningún curso")
                    else:
                        horario = Horario(estudiante, listaCursos)
                        if(horario.validarHorario(estudiante)):
                            from gestorGrafico.UserWindow import UserWindow
                            estudiante.inscribirCursos(listaCursos)
                            messagebox.showinfo("Proceso Exitoso", "La inscripción fue exitosa")
                            self._root.cleanRoot()
                            a = UserWindow(self._root, estudiante)
                        else:
                            self._root.cleanRoot()
                            messagebox.showinfo("Error", "Los cursos seleccionados presentan inconsistencias (hay horarios cruzados o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.")
                            self.inscribirMaterias(estudiante)
                def vol(e):
                    self._root.cleanRoot()
                    self.inscribirMaterias(estudiante)
                continuar.bind("<Button-1>", cont)
                parar.bind("<Button-1>", para)
                volver.bind("<Button-1>", vol)
                    
    def mostrarDetalles(self, curso, estudiante, listac, cursosres):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)

        listaCursos = curso.obtenerGrupos(estudiante)
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
        nota = Label(valoresFrame, text="Para agregar inscribir un curso, debes seleccionar el grupo que quieras y luego darle click a continuar", font=("Arial", 12), pady=5)
        nota.grid(row=3, column=0)
        if len(listaCursos) != 0:
            l = 20 if len(listaCursos) > 20 else len(listaCursos)
            frameTabla = Frame(valoresFrame, name="tabla")
            tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4"), show="headings", height=l)
            scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
            scrollbar.pack(side="right", fill="y")
            tabla.configure(yscrollcommand=scrollbar.set)
            
            i = 0
            for e in ["GRUPO", "PROFESOR", "CUPOS", "HORARIO"] :
                tabla.column(f"#{i+1}", anchor="center")
                tabla.heading(f"#{i+1}", text=e)
                i += 1
            i = 1
            for grupo in listaCursos:
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
                grupo = listaCursos[int(ng)-1]
                if grupo.getCupos()>0:
                    listac.append(grupo)
                    lista = listac
                    messagebox.showinfo("Proceso exitoso", "El curso se agregó correctamente")
                    self._root.cleanRoot()
                    self.inscribirManualmente(estudiante, lista, cursosres)
                else:
                    messagebox.showinfo("Proceso exitoso", "El curso seleccionado no tiene profesores asignados o no tiene cupos")
            else:
                messagebox.showinfo("Error", "Seleccione un grupo para poderlo agregar")
        def vol(e):
            self._root.cleanRoot()
            self.inscribirManualmente(estudiante)
        continuar.bind("<Button-1>", cont)
        volver.bind("<Button-1>", vol)
    
    def inscribirConHorario(self, estudiante):
        menuBar = Menu(self._root)
        self._root.config(menu=menuBar)
        archivo = Menu(menuBar, tearoff=False)
        menuBar.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Inicio", command=lambda : self._root.inicio(self._estudiante))
        archivo.add_command(label="Salir", command=self._root.salir)
        
        if len(estudiante.getHorariosCreados()) == 0:
            messagebox.showerror("Error", "No ha creado ningún horario, debes inscribir manualmente")
            self._root.cleanRoot()
            self.inscribirManualmente(estudiante)
        else:
            horarios = estudiante.getHorariosCreados()
            valoresFrame = Frame(self._root)
            valoresFrame.pack(anchor="n")
            framebotones = Frame(self._root)
            framebotones.pack(anchor="s")
            titulo = Label(valoresFrame, text="Horarios creados", font=("Arial", 18))
            titulo.grid(row=1, column=0)
            nota = Label(valoresFrame, text="Escoge el horario que quieras dandole click al boton Seleccionar del horario correspondiente", font=("Arial", 12), pady=5)
            nota.grid(row=2, column=0)
            y = 0
            z = 3
            listaBotones = []
            for x in horarios:
                label = Label(valoresFrame, text="Horario "+str(x.getId()))
                label.grid(row=z, column=0, sticky="e", padx=5)
                selector = Button(valoresFrame, text="Seleccionar", name=str(y))
                selector.grid(row=z, column=1, sticky="w", padx=5)
                listaBotones.append(selector)
                descrip = Label(valoresFrame, text="Cursos dentro del horario")
                descrip.grid(row=z+1, column=0, columnspan=2, pady=5)
                frameTabla = Frame(valoresFrame)
                cursos = x.getCursos()
                l = 20 if len(cursos) > 20 else len(cursos)
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show="headings", height=l)
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                
                i = 0
                for e in ["NOMBRE","CREDITOS","PROFESOR","HORARIO","CUPOS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                for x in cursos:
                    items = [x.getNombre(), str(x.getCreditos()), x.getProfesor(),x.getHorario(), str(x.getCupos())]
                    tabla.insert("", "end", values=items)
                tabla.column("c4", minwidth=0, width=300)
                tabla.pack()
                frameTabla.grid(row=z+2, column=0, columnspan=2)
                y+=1
                z+=3
            def cont(e):
                from gestorGrafico.UserWindow import UserWindow
                nh = e.widget.winfo_name()
                self._root.cleanRoot()
                horario = horarios[int(nh)]
                if(horario.validarHorario(estudiante) and len(horario.getCursos()) != 0):
                    from gestorGrafico.UserWindow import UserWindow
                    estudiante.inscribirCursos(horario.getCursos())
                    messagebox.showinfo("Proceso Exitoso", "La inscripción fue exitosa")
                    self._root.cleanRoot()
                    a = UserWindow(self._root, estudiante)
                else:
                    messagebox.showinfo("Error", "Los cursos seleccionados presentan inconsistencias (hay horarios cruzados, el horario contiene cursos que ya aprobaste o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.")
                    self._root.cleanRoot()
                    self.inscribirMaterias(estudiante)
            for x in listaBotones:
                x.bind("<Button-1>", cont)
            volver = Button(framebotones, text="Volver")
            volver.grid(row=0, column=0)
            
            def vol(e):
                self._root.cleanRoot()
                self.inscribirMaterias(estudiante)
            volver.bind("<Button-1>", vol)
