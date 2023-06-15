from tkinter import Tk, Frame

class UserWindow :
    def __init__(self) -> None:
        window = Tk()
        window.title('Mi Gestor Académico')
        window.geometry("800x400")

        frame1 = Frame()

        window.mainloop()