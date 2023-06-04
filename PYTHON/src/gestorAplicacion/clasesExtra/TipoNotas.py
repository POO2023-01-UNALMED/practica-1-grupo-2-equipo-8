from enum import Enum

class TipoNotas(Enum) :
    PARCIAL = (1, "Parcial")
    SEGUIMIENTO = (2, "Seguimiento")
    QUIZ = (3, "Quiz")