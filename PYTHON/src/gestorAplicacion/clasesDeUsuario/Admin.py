from .Registro import Registro

class Admin(Registro) :
    def __init__(self, nombre=None, correo=None, nombreUsuario=None, clave=None, documentoIdentificacion=None) -> None:
        super().__init__(nombre, correo, nombreUsuario, clave, documentoIdentificacion)
        Registro.agregarAdmin(self)

    # METODOS
    def buscarCursos() :
        pass