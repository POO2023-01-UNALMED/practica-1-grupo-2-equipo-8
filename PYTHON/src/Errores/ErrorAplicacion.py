class ErrorAplicacion(Exception):
    def __init__(self, complemento = ""):
        super().__init__("Manejo de errores de la Aplicacion: " + complemento)