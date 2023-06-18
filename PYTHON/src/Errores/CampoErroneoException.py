from .ErroresRegister import ErroresRegister
class CampoErroneoException(ErroresRegister):
    def __init__(self, entrada):
        super().__init__("La entrada {} está vacía o no contiene el tipo de valor que se solicita".format(entrada))
        self._entrada = entrada
    def mostrarMensaje(self):
        return ("La entrada {} está vacía o no contiene el tipo de valor que se solicita".format(self._entrada))