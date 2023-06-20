import pickle
from gestorAplicacion.clasesDeUsuario.Registro import Registro

class Deserializador:
    @classmethod
    def deserializador(cls):
        file = open("baseDatos/temp/profesores.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setProfesores(pcs)
        except EOFError :
            Registro.setProfesores([])
        file.close()

        file = open("baseDatos/temp/estudiantes.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setEstudiantes(pcs)
        except EOFError :
            Registro.setEstudiantes([])
        file.close()

        file = open("baseDatos/temp/admins.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setAdmins(pcs)
        except EOFError :
            Registro.setAdmins([])
        file.close()

        file = open("baseDatos/temp/estudiantesMatriculados.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setEstudiantesMatriculados(pcs)
        except EOFError :
            Registro.setEstudiantesMatriculados([])
        file.close()

        file = open("baseDatos/temp/estimulosProfesores.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setEstimulosProfesores(pcs)
        except EOFError :
            Registro.setEstimulosProfesores([])
        file.close()

        file = open("baseDatos/temp/estimulosEstudiantes.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setEstimulosEstudiantes(pcs)
        except EOFError :
            Registro.setEstimulosEstudiantes([])
        file.close()

        file = open("baseDatos/temp/cursos.pkl", "rb")
        try :
            pcs = pickle.load(file)
            Registro.setCursos(pcs)
        except EOFError :
            Registro.setCursos([])
        file.close()