from tkinter import Tk
from gestorGrafico.Root import Root
from gestorGrafico.Register import Register
from gestorGrafico.UserWindow import UserWindow
from gestorGrafico.Login import Login
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante

if __name__ == '__main__' :
    root = Root()
    A = Estudiante()
    #Login().iniciar()
    UserWindow(root, A)
    root.mainloop()
