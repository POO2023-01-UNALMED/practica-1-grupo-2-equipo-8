import tkinter as tk

class FieldFrame(tk.Frame):
    def __init__(self, root, tituloProceso=None, descripcionProceso=None, titulosCriterios=None, criterios=None, titulosValores=None, valores = None) :
        super().__init__(root)

        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._titulosCriterios = titulosCriterios
        self._criterios = criterios
        self._titulosValores = titulosValores
        self._valores = valores
        
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
        
