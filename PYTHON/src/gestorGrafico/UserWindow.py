from tkinter import Tk, Frame, Button, Menu, LEFT
from .FieldFrame import FieldFrame
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
#from ..gestorAplicacion.clasesDeUsuario.Profesor import Profesor

class UserWindow :
    def __init__(self, user) -> None:
        window = Tk()
        window.title('Mi Gestor Académico')
        window.state("zoomed")

        # 1) MENU SUPERIOR
        barra_menus = Menu()
        # Botón archivo
        archivo = Menu(barra_menus, tearoff=False)
        archivo.add_command(label="Aplicación")
        archivo.add_command(label="Salir", command=window.destroy)

        # Botón procesos y consultas
        procesosYConsultas = Menu(barra_menus, tearoff=False)
        if isinstance(user, Admin) :
            lista_procesos = [
                'Crear curso',
                'Eliminar curso',
                'Buscar asignatura',
                'Asignar citas de inscripción',
                'Ver estudiantes',
                'Ver profesores',
                'Ver estimulos [por nombre]',
                'Ver estimulos [todos]',
                'Modificar estudiante con cursos'
            ]
        elif isinstance(user, Estudiante) :
            lista_procesos = [
                'Ver recomendación de asignaturas',
                'Buscar asignatura',
                'Crear horario',
                'Inscribir materias',
                'Ver estímulos a los que aplica',
                'Calificar a un profesor'
            ]
        else :
            lista_procesos = [
                'Calificar',
                'Ver estímulos a los que aplica',
                'Buscar asignatura'
            ]

        for proceso in lista_procesos :
            procesosYConsultas.add_command(label=proceso)
        
        # Botón ayuda
        ayuda = Menu(barra_menus, tearoff=False)
        ayuda.add_command(label="Acerca de")

        # Se agregan los botones
        barra_menus.add_cascade(menu=archivo, label="Archivo")
        barra_menus.add_cascade(menu=procesosYConsultas, label="Procesos y Consultas")
        barra_menus.add_cascade(menu=ayuda, label="Ayuda")
        window.config(menu=barra_menus)

        # 2) INTERACCIÓN USUARIO
        # Interaccion Usuario
        frameInteraccion = FieldFrame(window, 'A', 'B')
        frameInteraccion.pack()

        window.mainloop()

    @classmethod
    def handleArchivo() :
        pass

    def handleProcesos() :
        pass