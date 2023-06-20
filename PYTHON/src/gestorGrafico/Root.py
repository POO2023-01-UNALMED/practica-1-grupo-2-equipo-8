from tkinter import Tk, Menu

class Root(Tk) :
    def __init__(self) -> None:
        super().__init__()
        self.state("zoomed")

    def mainloop(self, n: int = 0) -> None:
        return super().mainloop(n)
    
    def cleanRoot(self) :
        for w in self.winfo_children() :
            w.destroy()

    def salir(self) :
        from gestorGrafico.Inicio import Inicio
        from baseDatos.Serializador import Serializador
        Serializador.serializador()
        self.cleanRoot()
        Inicio(self)

    def inicio(self, user) :
        from gestorGrafico.UserWindow import UserWindow
        self.cleanRoot()
        UserWindow(self, user)
