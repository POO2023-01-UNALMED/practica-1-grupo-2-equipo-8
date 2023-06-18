from .ErrorAplicacion import ErrorAplicacion
class ErroresRegister(ErrorAplicacion):
    def __init__(self, complemento):
        super().__init__("Error en el registro " + "("+ complemento +")")