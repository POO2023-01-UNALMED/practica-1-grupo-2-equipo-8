from tkinter import Frame

class FieldFrame(Frame) :
    def __init__(self, ventana, titulosCriterios, criterios, titulosValores, valores = None) :
        super.__init__(ventana)

        