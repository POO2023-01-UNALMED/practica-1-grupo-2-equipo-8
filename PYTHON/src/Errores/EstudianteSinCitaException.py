from Errores.ErroresUserWindow import ErroresUserWindow
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante

class EstudianteSinCitaException(ErroresUserWindow) :
    def __init__(self, estudiante:Estudiante):
        super().__init__(f"El estudiante {estudiante.getNombre()} no tiene cita asignada en este momento. No puede proceder con la inscripción hasta que sea asignada.")
        self._entrada = estudiante

    def mostrarMensaje(self) :
        return f"El estudiante {self._entrada.getNombre()} no tiene cita asignada en este momento. No puede proceder con la inscripción hasta que sea asignada."