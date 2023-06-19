from tkinter import Tk
from Errores.HorarioException import HorarioException
from gestorAplicacion.clasesExtra.Carreras import Carreras
from gestorGrafico.Root import Root
from gestorGrafico.Register import Register
from gestorGrafico.UserWindow import UserWindow
from gestorGrafico.Login import Login
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from baseDatos.Deserializador import Deserializador
from baseDatos.Serializador import Serializador

if __name__ == '__main__' :
    Deserializador.deserializador()
    root = Root()
    #Login.iniciar(root)
    Register.register(root)
    root.mainloop()
    """ root = Root()
    A = Estudiante()
    #Login().iniciar()
    UserWindow(root, A) """
    Serializador.serializador()
