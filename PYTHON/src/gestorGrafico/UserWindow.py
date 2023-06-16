from tkinter import Tk, Frame, Button, Menu, LEFT
from .FieldFrame import FieldFrame

class UserWindow :
    def __init__(self) -> None:
        window = Tk()
        window.title('Mi Gestor Académico')
        window.state("zoomed")

        barra_menus = Menu()
        menu_archivo = Menu(barra_menus, tearoff=False)
        menu_archivo.add_command(
            label="Nuevo",
            accelerator="Ctrl+N",
        )
        window.bind_all("<Control-n>")
        menu_archivo.add_separator()
        menu_archivo.add_command(label="Salir", command=window.destroy)
        menu_opciones = Menu(barra_menus, tearoff=False)
        barra_menus.add_cascade(menu=menu_archivo, label="Archivo")
        barra_menus.add_cascade(menu=menu_opciones, label="Opciones")
        window.config(menu=barra_menus)
        window.mainloop()

        # Menu Superior
        frameBotones = Frame(window)
        frameBotones.grid(row=0, column=0, sticky='ew')

        frameBotones.columnconfigure(0, weight=1)
        frameBotones.columnconfigure(1, weight=1)
        frameBotones.columnconfigure(2, weight=1)

        bar = Menu(window)

        botonArchivo = Menu(bar)
        botonArchivo.add_cascade(label='Aplicacion')
        botonArchivo.add_cascade(label='Salir')

        botonProcesos = Button(frameBotones, text="Procesos y Consultas", font=("Arial", 18))
        botonProcesos.grid(row=1, column=1)

        botonAyuda = Button(frameBotones, text="Ayuda", font=("Arial", 18))
        botonAyuda.grid(row=1, column=2)

        # Interaccion Usuario
        frameInteraccion = FieldFrame(window, 'A')
        frameInteraccion.grid(row=1, column=0, sticky='nsew')

        
        # Configure grid weights to allow frames to expand
        window.grid_rowconfigure(0, weight=1)
        window.grid_rowconfigure(1, weight=1)
        window.grid_columnconfigure(0, weight=1)

        window.mainloop()

    @classmethod
    def handleArchivo() :
        pass

    def handleProcesos() :
        pass