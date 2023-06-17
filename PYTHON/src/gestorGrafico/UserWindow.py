from tkinter import Tk, Frame, Button, Menu, LEFT
from .FieldFrame import FieldFrame
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
#from ..gestorAplicacion.clasesDeUsuario.Profesor import Profesor
from .ProcesosEstudiante import RecomendarAsignaturas

class UserWindow :
    def __init__(self, root, user) -> None:

        def recomendarAsignaturas() :
            RecomendarAsignaturas(root)

        root.title('Mi Gestor Académico')
        root.state("zoomed")

        # 1) MENU SUPERIOR
        barra_menus = Menu()
        # Botón archivo
        archivo = Menu(barra_menus, tearoff=False)
        archivo.add_command(label="Aplicación")
        archivo.add_command(label="Salir", command=root.destroy)

        # Botón procesos y consultas
        procesosYConsultas = Menu(barra_menus, tearoff=False)
        if isinstance(user, Admin) :
            lista_procesos = [
                ('Crear curso', None),
                ('Eliminar curso', None),
                ('Buscar asignatura', None),
                ('Asignar citas de inscripción', None),
                ('Ver estudiantes', None),
                ('Ver profesores', None),
                ('Ver estimulos [por nombre]', None),
                ('Ver estimulos [todos]', None),
                ('Modificar estudiante con cursos', None)
            ]
        elif isinstance(user, Estudiante) :
            lista_procesos = [
                ('Ver recomendación de asignaturas', recomendarAsignaturas),
                ('Buscar asignatura', None),
                ('Crear horario', None),
                ('Inscribir materias', None),
                ('Ver estímulos a los que aplica', None),
                ('Calificar a un profesor', None)
            ]
        else :
            lista_procesos = [
                ('Calificar', None),
                ('Ver estímulos a los que aplica', None),
                ('Buscar asignatura', None)
            ]

        for proceso in lista_procesos :
            procesosYConsultas.add_command(label=proceso[0], command=proceso[1])
        
        # Botón ayuda
        ayuda = Menu(barra_menus, tearoff=False)
        ayuda.add_command(label="Acerca de")

        # Se agregan los botones
        barra_menus.add_cascade(menu=archivo, label="Archivo")
        barra_menus.add_cascade(menu=procesosYConsultas, label="Procesos y Consultas")
        barra_menus.add_cascade(menu=ayuda, label="Ayuda")
        root.config(menu=barra_menus)

        # 2) INTERACCIÓN USUARIO
        # Interaccion Usuario
        frameInteraccion = FieldFrame(root)
        #frameInteraccion = FieldFrame(root, 'A', 'B', ['A', 'B'], ['a', 'b'])
        frameInteraccion.pack()
