from tkinter import Tk, Frame, Button, LEFT

class UserWindow :
    def __init__(self) -> None:
        window = Tk()
        window.title('Mi Gestor Académico')
        window.state("zoomed")

        frameBotones = Frame(window)
        
        frameBotones.columnconfigure(0, weight=1)
        frameBotones.columnconfigure(1, weight=1)
        frameBotones.columnconfigure(2, weight=1)

        botonArchivo = Button(frameBotones, text="Archivo", font=("Arial", 18))
        botonArchivo.grid(row='1', column='0')

        botonProcesos = Button(frameBotones, text="Procesos y Consultas", font=("Arial", 18))
        botonProcesos.grid(row='1', column='1')

        botonAyuda = Button(frameBotones, text="Ayuda", font=("Arial", 18))
        botonAyuda.grid(row='1', column='2')

        frameBotones.pack(side=LEFT)

        window.mainloop()