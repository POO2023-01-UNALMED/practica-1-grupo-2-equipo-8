import tkinter as tk

class FieldFrame(tk.Frame):
    def __init__(self, ventana, tituloProceso=None, descripcionProceso=None, titulosCriterios=None, criterios=None, titulosValores=None, valores = None) :
        super().__init__(ventana)

        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._titulosCriterios = titulosCriterios
        self._criterios = criterios
        self._titulosValores = titulosValores
        self._valores = valores

        proceso = tk.Label(self, text=self._tituloProceso)
        proceso.place(anchor=tk.CENTER)
        proceso.pack()
