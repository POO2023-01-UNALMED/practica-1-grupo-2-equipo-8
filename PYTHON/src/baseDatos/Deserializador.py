import pickle
from ..gestorAplicacion.clasesDeUsuario.Registro import Registro

class Deserializador:
    @classmethod
    def deserializador():
        file = open("PYTHON/src/baseDatos/temp/profesores.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setProfesores(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/estudiantes.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setEstudiantes(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/admins.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setAdmins(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/estudiantesMatriculados.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setEstudiantesMatriculados(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/estimulosProfesores.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setEstimulosProfesores(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/estimulosEstudiantes.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setEstimulosEstudiantes(pcs)

        file.close()

        file = open("PYTHON/src/baseDatos/temp/cursos.pkl", "wb")
        pcs = pickle.load(file)
        Registro.setCursos(pcs)

        file.close()