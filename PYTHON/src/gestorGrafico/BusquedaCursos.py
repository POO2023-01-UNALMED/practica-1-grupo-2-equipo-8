from tkinter import Button, Entry, Frame, Label, Listbox, Menu, StringVar, Tk, Toplevel, messagebox, ttk
from Errores.HorarioException import HorarioException
from gestorAplicacion.clasesDeCurso.Curso import Curso
from gestorAplicacion.clasesExtra.Carreras import Carreras
from gestorAplicacion.clasesExtra.Facultades import Facultades
from gestorGrafico.FieldFrame import FieldFrame

from gestorAplicacion.clasesDeUsuario.Registro import Registro

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
                        tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show='headings', height=len(listaCursos))
                        scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                        scrollbar.pack(side="right", fill="y")
                        tabla.configure(yscrollcommand=scrollbar.set)

                        i = 0
                        for e in ['ID', 'NOMBRE', 'CREDITOS', 'FACULTAD', "CARRERAS RELACIONADAS"] :
                            tabla.column(f"#{i+1}", anchor="center")
                            tabla.heading(f"#{i+1}", text=e)
                            i += 1

                        for curso in listaCursos:
                            lis = []
                            for x in curso.getCarrerasRelacionadas():
                                lis.append(x.value[1])
                            items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                            tabla.insert('', 'end', values=items)
                        tabla.pack()
                        frameTabla.grid(row=4, column=0, columnspan=2)
                    
                    def handleAceptar(e):
                        item = tabla.focus()
                        if item != "":
                            curso = tabla.item(item)['values'][1]
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
                        tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show='headings', height=len(listaCursos))
                        scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                        scrollbar.pack(side="right", fill="y")
                        tabla.configure(yscrollcommand=scrollbar.set)

                        i = 0
                        for e in ['ID', 'NOMBRE', 'CREDITOS', 'FACULTAD', "CARRERAS RELACIONADAS"] :
                            tabla.column(f"#{i+1}", anchor="center")
                            tabla.heading(f"#{i+1}", text=e)
                            i += 1

                        for curso in listaCursos:
                            lis = []
                            for x in curso.getCarrerasRelacionadas():
                                lis.append(x.value[1])
                            items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                            tabla.insert('', 'end', values=items)
                        tabla.pack()
                        frameTabla.grid(row=4, column=0, columnspan=2)
                    
                    def handleAceptar(e):
                        item = tabla.focus()
                        if item != "":
                            curso = tabla.item(item)['values'][1]
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
                            tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show='headings', height=len(listaCursos))
                            scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                            scrollbar.pack(side="right", fill="y")
                            tabla.configure(yscrollcommand=scrollbar.set)

                            i = 0
                            for e in ['ID', 'NOMBRE', 'CREDITOS', 'FACULTAD', "CARRERAS RELACIONADAS"] :
                                tabla.column(f"#{i+1}", anchor="center")
                                tabla.heading(f"#{i+1}", text=e)
                                i += 1

                            for curso in listaCursos:
                                lis = []
                                for x in curso.getCarrerasRelacionadas():
                                    lis.append(x.value[1])
                                items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                                tabla.insert('', 'end', values=items)
                            tabla.pack()
                            frameTabla.grid(row=4, column=0, columnspan=2)
                        
                            def handleAceptar(e):
                                item = tabla.focus()
                                if item != "":
                                    curso = tabla.item(item)['values'][1]
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
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3", "c4", "c5"), show='headings', height=len(listaCursos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                i = 0
                for e in ['ID', 'NOMBRE', 'CREDITOS', 'FACULTAD', "CARRERAS RELACIONADAS"] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1

                for curso in listaCursos:
                    lis = []
                    for x in curso.getCarrerasRelacionadas():
                        lis.append(x.value[1])
                    items = [str(curso.getId()),curso.getNombre(),curso.getCreditos(),curso.getFacultad()[0].value[1],", ".join(lis)]
                    tabla.insert('', 'end', values=items)
                tabla.pack()
                frameTabla.grid(row=4, column=0, columnspan=2)
                
            
                def handleAceptar(e):
                    item = tabla.focus()
                    if item != "":
                        curso = tabla.item(item)['values'][1]
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
            grupos = curso.obtenerGrupos()
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
                tabla = ttk.Treeview(frameTabla, column=("c1", "c2", "c3"), show='headings', height=len(grupos))
                scrollbar = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
                scrollbar.pack(side="right", fill="y")
                tabla.configure(yscrollcommand=scrollbar.set)
                i = 0
                for e in ['GRUPO', 'PROFESOR', 'CUPOS'] :
                    tabla.column(f"#{i+1}", anchor="center")
                    tabla.heading(f"#{i+1}", text=e)
                    i += 1
                i = 0
                for grupo in grupos:
                    items = [str(i),grupo.getProfesor(),grupo.getHorario(), str(grupo.getCupos())]
                    tabla.insert('', 'end', values=items)
                    i+=1
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
    """ArrayList<CursoEstudiante> listaCursos = curso.obtenerGrupos(estudiante)
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas())
            int cont = 1
            for(CursoEstudiante ce : listaCursos)
                System.out.println("-------------------------------------------------------------------------------")
                System.out.println("Profesor: "+ce.getProfesor()+"\nHorario: "+ce.getHorario()+"\nCupos: "+ce.getCupos())
                System.out.println(cont+". Añadir a horario existente")
                System.out.println("-------------------------------------------------------------------------------")
                cont++
            
            System.out.println(cont+". volver")
            String opcion = sc.next()
            if(opcion.equals(String.valueOf(cont)))
                break
            
            boolean comp = true
            for(int x = 1 x<=cont x++)
                if(opcion.equals(String.valueOf(x)))
                    if(!curso.obtenerGrupos().isEmpty())
                        agregarCurso(estudiante, listaCursos.get(x-1))
                    
                    else
                        System.out.println("El curso seleccionado no tiene profesores asignados")
                    
                    comp = false
                    break
                
            
            if(comp == true)
                System.out.println("Debe seleccionar una opción entre el 1 y el "+cont)
            
        
    
    
    public static void buscarCursos(Estudiante estudiante, Horario horario)
        ArrayList<Curso> listaCursos = Registro.getCursos()
        boolean compp = true
        Scanner sc = new Scanner(System.in)
        if(Registro.getCursos().isEmpty())
            while(true)
                System.out.println("No hay cursos disponibles")
                System.out.println("1. volver")
                String opcion = sc.next()
                if(!(opcion.equals("1")))
                    System.out.println("Solo puede seleccionar la opción \"volver\"")
                    continue
                
                switch(opcion)
                    case "1": break
                
                break
            
        
        else
            while(true)
                System.out.println("Indique lo que quiere realizar:\n"
                        + "1. Ver todos los cursos\n"
                        + "2. Filtrar cursos por facultad\n"
                        + "3. Filtrar cursos por carreras relacionadas\n"
                        + "4. Filtrar cursos por horario\n"
                        + "5. Salir")
                String opcion = sc.next()
                sc.nextLine()
                if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")) && !(opcion.equals("4")) && !(opcion.equals("5")))
                    System.out.println("Debe seleccionar un número entre el 1 y el 5")
                    continue
                
                switch(opcion)
                    case "1": break
                    case "2": 
                        while(true)
                            System.out.println("Indique la facultad:\n"
                            + "1. Minas\n"
                            + "2. Ciencias\n"
                            + "3. Salir")
                            opcion = sc.next()
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")))
                                System.out.println("Debe seleccionar un número entre el 1 y el 3")
                                continue
                            
                            switch(opcion)
                                case "1": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.MINAS) break
                                case "2": listaCursos = Curso.filtrarPorFacultad(listaCursos, Facultades.CIENCIAS) break
                                case "3": break
                            
                            break
                        
                        break
                    case "3": 
                        while(true)
                            System.out.println("Indique la carrera:\n"
                            + "1. Ingeniería de Sistemas\n"
                            + "2. Ciencias de la Computación\n"
                            + "3. Salir")
                            opcion = sc.next()
                            if(!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")))
                                System.out.println("Debe seleccionar un número entre el 1 y el 3")
                                continue
                            
                            switch(opcion)
                                case "1": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.SISTEMAS) break
                                case "2": listaCursos = Curso.filtrarPorCarrera(listaCursos, Carreras.COMPUTACION) break
                                case "3": break
                            
                            break
                        
                        break
                    case "4": 
                        System.out.println("Indique el horario (formato HH:MM) Ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00")
                        opcion = sc.nextLine()
                        listaCursos = Curso.filtrarPorHorario(listaCursos, opcion)
                        break
                    case "5": compp = false break
                
                break
            
            if(compp == true && !listaCursos.isEmpty())
                while(true)
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%s\t%-32s\t%s\t%-17s\t%s","ID","Nombre","Creditos","Facultad","Programas relacionados"))
                    System.out.println("----------------------------------------------------------------------------------------------------------------------")
                    int cont = 1
                    for(Curso curso : listaCursos)
                        System.out.println("\t"+curso.getId()+"\t"+String.format("%-32s",curso.getNombre())+"\t"+String.format("%-8s",curso.getCreditos())+"\t"+String.format("%-16s",curso.getFacultad())+"\t"+curso.getCarrerasRelacionadas())
                        System.out.println(cont+". Ver detalles")
                        cont++
                    
                    System.out.println(cont+". Volver")
                    String opcion = sc.next()
                    if(opcion.equals(String.valueOf(cont)))
                        break
                    
                    boolean comp = true
                    for(int x = 1 x<=listaCursos.size() x++)
                        if(opcion.equals(String.valueOf(x)))
                            mostrarDetalles(estudiante, listaCursos.get(x-1), horario)
                            comp = false
                            break
                        
                    
                    if(comp == true)
                        System.out.println("Debe seleccionar una opción entre el 1 y el "+listaCursos.size()+1)
                    
                
            
            else if(listaCursos.isEmpty())
                System.out.println("No hay cursos con las especificaciones dadas")
            
        
    
    
    public static void mostrarDetalles(Estudiante estudiante, Curso curso, Horario horario)
        Scanner sc = new Scanner(System.in)
        
        while(true)
            ArrayList<CursoEstudiante> listaCursos = curso.obtenerGrupos(estudiante)
            System.out.println(curso.getNombre()+"("+curso.getId()+")\n"+curso.getCreditos()+"\n"+curso.getFacultad()+"\n"+curso.getCarrerasRelacionadas())
            int cont = 1
            for(CursoEstudiante ce : listaCursos)
                System.out.println("-------------------------------------------------------------------------------")
                System.out.println("Profesor: "+ce.getProfesor()+"\nHorario: "+ce.getHorario()+"\nCupos: "+ce.getCupos())
                System.out.println(cont+". Añadir a horario")
                cont++
                System.out.println("-------------------------------------------------------------------------------")
            
            System.out.println(cont+". volver")
            String opcion = sc.next()
            if(opcion.equals(String.valueOf(cont)))
                break
            
            boolean comp = true
            for(int x = 1 x<cont x++)
                if(opcion.equals(String.valueOf(x)))
                    if(!curso.obtenerGrupos().isEmpty())
                        horario.agregarCurso(listaCursos.get(x-1))
                    
                    else
                        System.out.println("El curso seleccionado no tiene profesores asignados")
                    
                    comp = false
                    break
                
            
            if(comp == true)
                System.out.println("Debe seleccionar una opción entre el 1 y el "+cont)
            
        
    
    
    public static void agregarCurso(Estudiante estudiante, CursoEstudiante curso)
        Scanner sc = new Scanner(System.in)
        if(estudiante.getHorariosCreados().isEmpty())
            System.out.println("No ha creado ningún horario")
        
        else
            while(true)
                System.out.println("Seleccione el horario al que le quiere agregar el curso seleccionado: ")
                int cont = 1
                for(Horario horario : estudiante.getHorariosCreados())
                    System.out.println("Horario "+horario.getId()+":\n")
                    System.out.println("Los cursos disponibles son:\n"
                    + String.format("\t%-32s\t%-8s\t%-25s\t%-39s\t%-5s","Nombre","Creditos","Profesor","Horario","Cupos"))
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
                    for(CursoEstudiante ce : horario.getCursos())
                        System.out.println("\t"+String.format("%-32s", ce.getNombre())+"("+ce.getId()+")\t"+String.format("%-8s", ce.getCreditos())+"\t"+String.format("%-25s", ce.getProfesor().getNombre())+"\t"+String.format("%-39s", ce.getHorario())+"\t"+String.format("%-5s", ce.getCupos()))
                    
                    System.out.println("\n"+cont+". Seleccionar\n")
                    cont++
                
                String opcion = sc.next()
                boolean comp = true
                for(int x = 1 x<cont x++)
                    if(opcion.equals(String.valueOf(x)))
                        estudiante.getHorariosCreados().get(x-1).agregarCurso(curso)
                        comp = false
                        break
                    
                
                if(comp == true)
                    System.out.println("Debe seleccionar una opción entre el 1 y el "+(cont-1))
                    continue
                
                break
            
            
            
        
    
    
    public static void reportarFallo(String nombre)
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
        System.out.println("Ya tienes el curso "+nombre)
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
    
    public static void reportarFallo(CursoEstudiante curso1, CursoEstudiante curso2)
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
        System.out.println("Hay un problema entre el horario del curso "+curso1.getNombre()+" y el curso "+curso2.getNombre()+
                "\nHorario de "+curso1.getNombre()+":"+curso1.getHorario()+"\tHorario"+curso2.getNombre()+":"+curso1.getHorario())
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
    
    
    public static void aceptar()
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
        System.out.println("El curso se agregó correctamente")
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------")
    """
