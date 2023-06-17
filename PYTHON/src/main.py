from gestorGrafico.UserWindow import UserWindow
from gestorGrafico.Login import Login
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante

if __name__ == '__main__' :
    A = Admin()
    #Login().iniciar()
    UserWindow(A)