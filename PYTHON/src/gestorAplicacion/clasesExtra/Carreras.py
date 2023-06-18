from enum import Enum
from .Facultades import Facultades

class Carreras(Enum) :
    SISTEMAS = (1, "Ingenieria de Sistemas", Facultades.MINAS.value[1])
    COMPUTACION = (2, "Ciencias de la Computacion", Facultades.CIENCIAS.value[1])
