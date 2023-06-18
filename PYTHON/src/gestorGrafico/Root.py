from tkinter import Tk

class Root(Tk) :
    def __init__(self) -> None:
        super().__init__()

    def mainloop(self, n: int = 0) -> None:
        return super().mainloop(n)
    
    def cleanRoot(self) :
        for w in self.winfo_children() :
            w.destroy()
