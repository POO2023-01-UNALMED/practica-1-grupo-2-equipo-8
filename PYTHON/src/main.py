from tkinter import Tk
from Errores.HorarioException import HorarioException
from gestorAplicacion.clasesExtra.Carreras import Carreras
from gestorGrafico.Register import Register
from gestorGrafico.UserWindow import UserWindow
from gestorGrafico.Login import Login
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante

if __name__ == '__main__' :
    Register.register()
    """root = Tk()
    A = Estudiante()
    #Login().iniciar()
    UserWindow(root, A)
    root.mainloop()"""
