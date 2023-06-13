import pickle
from ..gestorAplicacion.clasesDeUsuario.Registro import Registro

class Serializador:
    @classmethod
    def serializador():
        file = open("PYTHON/src/baseDatos/temp/profesores.pkl", "wb")
        pcs = Registro.getProfesores()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/estudiantes.pkl", "wb")
        pcs = Registro.getEstudiantes()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/admins.pkl", "wb")
        pcs = Registro.getAdmins()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/estudiantesMatriculados.pkl", "wb")
        pcs = Registro.getEstudiantesMatriculados()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/estimulosProfesores.pkl", "wb")
        pcs = Registro.getEstimulosProfesores()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/estimulosEstudiantes.pkl", "wb")
        pcs = Registro.getEstimulosEstudiantes()
        pickle.dump(pcs, file)
        file.close()

        file = open("PYTHON/src/baseDatos/temp/cursos.pkl", "wb")
        pcs = Registro.getCursos()
        pickle.dump(pcs, file)
        file.close()