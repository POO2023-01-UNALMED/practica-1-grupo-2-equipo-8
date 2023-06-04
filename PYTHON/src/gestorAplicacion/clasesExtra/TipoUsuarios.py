from enum import Enum

class TipoUsuarios(Enum) :
    ESTUDIANTE = (1, "Estudiante")
    PROFESOR = (2, "Profesor")
    ADMIN = (3, "Admin")