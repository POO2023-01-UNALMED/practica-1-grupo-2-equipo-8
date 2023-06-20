from Errores.ErroresUserWindow import ErroresUserWindow

class TablaSinSeleccionException(ErroresUserWindow) :
    def __init__(self):
        super().__init__(f"Ningún elemento de la tabla fue seleccionado.")

    def mostrarMensaje(self) :
        return f"Ningún elemento de la tabla fue seleccionado."