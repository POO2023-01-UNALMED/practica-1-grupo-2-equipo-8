from Errores.ErrorAplicacion import ErrorAplicacion

class ErroresUserWindow(ErrorAplicacion) :
    def __init__(self, complemento=""):
        super().__init__("Error en la interfaz de usuario " + "("+ complemento +")")