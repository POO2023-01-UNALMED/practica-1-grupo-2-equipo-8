import pickle

class Estimulo:
    def __init__(self, nombre, descripcion, aQuienAplica, facultadesAplica, cupos):
        self.nombre = nombre
        self.descripcion = descripcion
        self.aQuienAplica = aQuienAplica
        self.facultadesAplica = facultadesAplica
        self.cupos = cupos

    def getAQuienAplica(self):
        return self.aQuienAplica

    def getCupos(self):
        return self.cupos

    def getDescripcion(self):
        return self.descripcion

    def getFacultadesAplica(self):
        return self.facultadesAplica

    def getNombre(self):
        return self.nombre

    def setAQuienAplica(self, aQuienAplica):
        self.aQuienAplica = aQuienAplica

    def setCupos(self, cupos):
        self.cupos = cupos

    def setDescripcion(self, descripcion):
        self.descripcion = descripcion

    def setFacultadesAplica(self, facultadesAplica):
        self.facultadesAplica = facultadesAplica

    def setNombre(self, nombre):
        self.nombre = nombre

# Serialización
# def serializar(objeto, archivo):
#     with open(archivo, 'wb') as file:
#         pickle.dump(objeto, file)

# Deserialización
# def deserializar(archivo):
#     with open(archivo, 'rb') as file:
#         objeto = pickle.load(file)
#     return objeto
