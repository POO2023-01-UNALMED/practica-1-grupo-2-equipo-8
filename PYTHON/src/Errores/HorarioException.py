from .ErroresRegister import ErroresRegister
class HorarioException(ErroresRegister):
    def __init__(self, horario):
        super().__init__("El horario seleccionado ({}) no es valido. Recuerde que el formato debe ser como el del siguiente ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00".format(horario))
        self._horario = horario
    def mostrarMensaje(self):
        return ("El horario {} no es valido. Recuerde que el formato debe ser como el del siguiente ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00".format(self._horario))