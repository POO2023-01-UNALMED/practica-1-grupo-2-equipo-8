import tkinter as tk
from tkinter import ttk

class FieldFrame(tk.Frame):
    def __init__(self, root, tituloProceso=None, descripcionProceso=None, criterios=None, valores = None, valoresCombo = None) :
        super().__init__(root)

        # Si se abre la ventana por primera vez
        if tituloProceso == None :
            frame = tk.Frame(root)
            tk.Label(root, text='ELIJA UN PROCESO').pack()
            frame.anchor(tk.CENTER)
            frame.pack()
            return

        self._root = root
        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._criterios = criterios
        self._valores = valores
        self._entrys = []
        self._entradasUsuario = []
        if valoresCombo == None:
            self._valoresCombo = []
        else:
            self._valoresCombo = valoresCombo
        
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
        valoresCombo = []
        for x in self._valoresCombo:
            valoresCombo.append(x)
        
        frameValores = tk.Frame(root, name="valores")
        if criterios != None:
            cont = 1
            for i in range(len(criterios)) :
                criterio = tk.Label(frameValores, text=self._criterios[i])
                criterio.grid(row=i, column=0)

                if valores[i] == "combobox":
                    valor = ttk.Combobox(frameValores, values=valoresCombo[0], width=30, name=str(cont), state="readonly")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                    valoresCombo.pop(0)
                    cont+=1
                elif valores[i] == "comboboxdeshabilitado":
                    valor = ttk.Combobox(frameValores, values=valoresCombo[0], width=30, name=str(cont), state="disabled")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                    valoresCombo.pop(0)
                    cont+=1
                elif valores[i] == "deshabilitado":
                    valor = tk.Entry(frameValores, state="disabled")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                elif valores[i] == 'radio' :
                    frameRadios = tk.Frame(frameValores)
                    radio = tk.IntVar()
                    tk.Radiobutton(frameRadios, text='Sí', variable=radio, value=1).grid(row=0, column=0)
                    tk.Radiobutton(frameRadios, text='No', variable=radio, value=0).grid(row=0, column=1)
                    self._entrys.append(radio)
                    frameRadios.grid(row=i, column=1)
                else :
                    valor = tk.Entry(frameValores)
                    valor.grid(row=i, column=1)
                    valor.insert(0, self._valores[i])
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
            self._entradas = self._entradasUsuario
            self._root.cleanRoot()
            self.recomendar()

    def getEntradasUsuario(self) :
        for entrada in self._entrys :
            self._entradasUsuario.append(entrada.get())

    def crearBoton(self, texto, comando=None) :
        return tk.Button(self, text=texto, command=comando)
