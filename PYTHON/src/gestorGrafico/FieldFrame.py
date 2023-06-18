import tkinter as tk

class FieldFrame(tk.Frame):
    def __init__(self, root, tituloProceso=None, descripcionProceso=None, criterios=None, valores = None) :
        super().__init__(root)

        self._root = root
        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._criterios = criterios
        self._valores = valores
        self._entrys = []
        self._entradasUsuario = []

        # Si se habré la ventana por primera vez
        if tituloProceso == None :
            frame = tk.Frame(root)
            tk.Label(root, text='ELIJA UN PROCESO').pack()
            frame.anchor(tk.CENTER)
            frame.pack()
            return
        
        # Titulo proceso
        frameTituloProceso = tk.Frame(root)
        tk.Label(frameTituloProceso, text=self._tituloProceso).pack()
        frameTituloProceso.anchor(tk.CENTER)
        frameTituloProceso.pack()

        # Descripcion proceso
        frameDescripcionProceso = tk.Frame(root)
        tk.Label(frameDescripcionProceso, text=self._descripcionProceso).pack()
        frameDescripcionProceso.anchor(tk.CENTER)
        frameDescripcionProceso.pack()

        # Valores
        frameValores = tk.Frame(root)
        for i in range(len(criterios)) :
            criterio = tk.Label(frameValores, text=criterios[i])
            criterio.grid(row=i, column=0)

            valor = tk.Entry(frameValores)
            valor.grid(row=i, column=1)
            valor.insert(0, valores[i])
            self._entrys.append(valor)
        frameValores.anchor(tk.CENTER)
        frameValores.pack()

        # Botones
        """ frameBotones = tk.Frame(self._root)
        aceptar = tk.Button(frameBotones, text='Aceptar', command=self.handleAceptar)
        aceptar.grid(row=0, column=0)

        borrar = tk.Button(frameBotones, text='Borrar')
        borrar.grid(row=0, column=1)
        frameBotones.pack() """

    def handleAceptar(self) :
        self.getEntradasUsuario()
        self._root.cleanRoot()

    def getEntradasUsuario(self) :
        for entrada in self._entrys :
            self._entradasUsuario.append(entrada.get())

    def crearBoton(self, texto, comando=None) :
        return tk.Button(self, text=texto, command=comando)
