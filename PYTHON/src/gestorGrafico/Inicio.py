import tkinter as tk
from gestorGrafico.Root import Root
from PIL import Image, ImageTk
import os
import pathlib
from gestorGrafico.programador import Programador
from gestorGrafico.Register import Register
from gestorGrafico.Login import Login

path = os.path.join(pathlib.Path(__file__).parent.absolute())

class Inicio:
    def __init__(self, root:Root):
        # Creamos a los poderosos 4 programadores
        # Cada uno se agrega con sus fotos y su biografía
        # La biografía debe ser corta y decir dónde y cuándo naciste y qué te gusta
        # // Borrar este comentario antes de enviar el proyecto XD
        jesus = Programador("Jesús Miguel Porto López",
                            "Estudia Ingenieria de Sistemas.\nNació en Necoclí Antioquia el 24 de septiembre de 2003.\nSus gustos son el Fútbol y los videojuegos. Odia a java",
                            [path+"\\imagenes\\jesus1.png", path+"\\imagenes\\jesus2.png", 
                            path+"\\imagenes\\jesus3.png", path+"\\imagenes\\jesus4.png"])

        samuel = Programador("Samuel Restrepo Aguilar",
                            "Estudia Ciencias de la Computación.\nNació en Medellin Antioquia el 17 de Agosto de 2004\nSus gustos son la matemática y los algorítmos",
                            [path+"\\imagenes\\samuel1.png", path+"\\imagenes\\samuel2.png", 
                            path+"\\imagenes\\samuel3.png", path+"\\imagenes\\samuel4.png"])

        pablo = Programador("Juan Pablo Robledo Meza",
                            "Estudia Ciencias de la Computación.\nNació en Manizales el 1 de febrero de 2002\nLe gusta jugar fútbol, nadar y tocar el piano. Además odia Tkinter.",
                            [path+"\\imagenes\\pablo1.png", path+"\\imagenes\\pablo2.png", 
                            path+"\\imagenes\\pablo3.png", path+"\\imagenes\\pablo4.png"])

        jhon = Programador("Jhon Jairo Hernandez Castaneda",
                            "Estudia Ingenieria de Sistemas.\nNació en Leticia el 1 de Abril de 2003\nSus gustos son la natación y POO",
                            [path+"\\imagenes\\jhon1.png", path+"\\imagenes\\jhon2.png", 
                            path+"\\imagenes\\jhon3.png", path+"\\imagenes\\jhon4.png"])

        self.programadores = [jesus, samuel, pablo, jhon]
        self.imagenesMGA = [
                    Image.open(path+"\\imagenes\\MGA1.png").resize((600, 600)),
                    Image.open(path+"\\imagenes\\MGA2.png").resize((600, 600)),
                    Image.open(path+"\\imagenes\\MGA3.png").resize((600, 600)),
                    Image.open(path+"\\imagenes\\MGA4.png").resize((600, 600)),
                    Image.open(path+"\\imagenes\\MGA5.png").resize((600, 600))]

        self.contP = 0
        self.contI = 0
        self.p = self.programadores[self.contP]

        self.ventana = root
        self.ventana.title("Ventana de inicio")
        self.P1 = tk.Frame(self.ventana, bg= "lightgray")
        self.P1.pack(side="left", padx=10, pady=5, fill="both", expand=True)

        self.P3 = tk.Frame(self.P1)
        self.P3.pack(side="top", fill="both")
        self.saludo = tk.Label(self.P3, text="Bienvenido a\nMi Gestor Académico", font=("arial", 30))
        self.saludo.pack(padx=5, pady=5, anchor="center")

        self.P4 = tk.Frame(self.P1)
        self.P4.pack(side="bottom", fill="both")

        self.botones = tk.Frame(self.P4)
        self.botones.pack(side="bottom")


        self.boton1 = tk.Button(self.botones, text="Iniciar Sesión")
        self.boton1.pack(side="left", padx=7, pady=2)
        self.boton1.bind("<Button-1>", self.iniciar)

        self.boton2 = tk.Button(self.botones, text="Registrarse")
        self.boton2.pack(side="right", padx=7, pady=2)
        self.boton2.bind("<Button-1>", self.registrar)

        self.imagenes = tk.Label(self.P4)
        self.imagenes.bind("<Button-1>", self.cambiarImagen)
        imagen = ImageTk.PhotoImage(self.imagenesMGA[0])
        self.imagenes.config(image=imagen)
        self.imagenes.image = imagen 
        self.imagenes.pack(side="top")

        self.P2 = tk.Frame(self.ventana, bg= "lightgray")
        self.P2.pack(side="right", padx=10, pady=5, fill="both", expand=True)

        self.P5 = tk.Frame(self.P2)
        self.P5.pack(side="top", fill="x")

        self.bio = tk.Button(self.P5, text=f"{self.p.nombre}\nBiografia: {self.p.biografia}",
                             font=("arial", 15))
        self.bio.bind("<Button-1>", self.cambioProgramador)
        self.bio.pack(fill="both", anchor="center")
        self.bio.config(relief="flat")

        self.P6 = tk.Frame(self.P2)
        self.P6.pack(side="bottom", fill="both")

        imagen1 = ImageTk.PhotoImage(Image.open(self.p.fotos[0]).resize((300, 300)))
        self.imagen1 = tk.Label(self.P6, image=imagen1)
        self.imagen1.image = imagen1  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen1.grid(row=0, column=0)

        imagen2 = ImageTk.PhotoImage(Image.open(self.p.fotos[1]).resize((300, 300)))
        self.imagen2 = tk.Label(self.P6, image=imagen2)
        self.imagen2.image = imagen2  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen2.grid(row=0, column=1)

        imagen3 = ImageTk.PhotoImage(Image.open(self.p.fotos[2]).resize((300, 300)))
        self.imagen3 = tk.Label(self.P6, image=imagen3)
        self.imagen3.image = imagen3  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen3.grid(row=1, column=0)

        imagen4 = ImageTk.PhotoImage(Image.open(self.p.fotos[3]).resize((300, 300)))
        self.imagen4 = tk.Label(self.P6, image=imagen4)
        self.imagen4.image = imagen4  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen4.grid(row=1, column=1)

    def iniciar(self, event):
        self.ventana.cleanRoot()
        Login.iniciar(self.ventana)

    def registrar(self, event):
        self.ventana.cleanRoot()
        Register.register(self.ventana)

    def cambiarImagen(self, event):
        self.contI = (self.contI + 1) % 5
        newImage = ImageTk.PhotoImage(self.imagenesMGA[self.contI])
        self.imagenes.config(image=newImage)
        self.imagenes.image = newImage 
        self.imagenes.pack(side="top")

    def cambioProgramador(self, event):
        self.contP = (self.contP + 1) % 4
        self.p = self.programadores[self.contP]
        self.bio.config(text=f"{self.p.nombre}\nBiografia: {self.p.biografia}")

        self.imagen1.destroy()
        self.imagen2.destroy()
        self.imagen3.destroy()
        self.imagen4.destroy()
        imagen1 = ImageTk.PhotoImage(Image.open(self.p.fotos[0]).resize((300, 300)))
        self.imagen1 = tk.Label(self.P6, image=imagen1)
        self.imagen1.image = imagen1  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen1.grid(row=0, column=0)

        imagen2 = ImageTk.PhotoImage(Image.open(self.p.fotos[1]).resize((300, 300)))
        self.imagen2 = tk.Label(self.P6, image=imagen2)
        self.imagen2.image = imagen2  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen2.grid(row=0, column=1)

        imagen3 = ImageTk.PhotoImage(Image.open(self.p.fotos[2]).resize((300, 300)))
        self.imagen3 = tk.Label(self.P6, image=imagen3)
        self.imagen3.image = imagen3  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen3.grid(row=1, column=0)

        imagen4 = ImageTk.PhotoImage(Image.open(self.p.fotos[3]).resize((300, 300)))
        self.imagen4 = tk.Label(self.P6, image=imagen4)
        self.imagen4.image = imagen4  # Guardar una referencia para evitar que se elimine la imagen
        self.imagen4.grid(row=1, column=1)
