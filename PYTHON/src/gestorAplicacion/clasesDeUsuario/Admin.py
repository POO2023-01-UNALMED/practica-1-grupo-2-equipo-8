from Registro import Registro

class Admin(Registro) :
    def __init__(self, nombre, correo, nombreUsuario, clave, documentoIdentificacion) -> None:
        super().__init__(nombre, correo, nombreUsuario, clave, documentoIdentificacion)
        Registro.agregarAdmin(self)