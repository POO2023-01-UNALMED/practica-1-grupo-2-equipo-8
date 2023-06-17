import tkinter as tk

class FieldFrame(tk.Frame):
    def __init__(self, root, tituloProceso=None, descripcionProceso=None, criterios=None, valores = None) :
        super().__init__(root)

        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._criterios = criterios
        self._valores = valores
        
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
        frameValores.anchor(tk.CENTER)
        frameValores.pack()
