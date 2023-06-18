from .ErrorAplicacion import ErrorAplicacion
class HorarioException(ErrorAplicacion):
    def __init__(self, horario):
        super().__init__(format("El horario {} no es valido", horario))
    def mostrarMensaje(horario):
        return format("El horario {} no es valido. Recuerde que el formato es como el del siguiente ejemplo: Martes 12:00-14:00 Jueves 14:00-16:00", horario)