from tkinter import Tk, Frame, Button, Menu, messagebox, LEFT
from gestorAplicacion.clasesExtra.Horario import Horario
from gestorGrafico.BusquedaCursos import BusquedaCursos

from gestorGrafico.Root import Root
from gestorGrafico.FieldFrame import FieldFrame
from gestorAplicacion.clasesDeUsuario.Admin import Admin
from gestorAplicacion.clasesDeUsuario.Profesor import Profesor
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
#from ..gestorAplicacion.clasesDeUsuario.Profesor import Profesor

from gestorGrafico.RecomendarAsignaturas import RecomendarAsignaturas
from gestorGrafico.AsignarCita import AsignarCita
from gestorGrafico.InscripcionMaterias import IncripcionMaterias
from gestorGrafico.BusquedasEstimulos import BusquedaEstimulos
from baseDatos.Serializador import Serializador

class UserWindow :
    def __init__(self, root:Root, user) -> None:
        self._user = user

        def handleAplicacion() :
            messagebox.showinfo(title="Mi Gestor Académico",
                                message="Es una aplicación que permite tanto a estudiantes como profesores llevar su historia académica.")

        def handleAcercaDe() :
            messagebox.showinfo(title="Autores",
                                message="Samuel Restrepo Aguilar\nJesús Porto López\nJuan Pablo Robledo Meza\nJhon Jairo Hernández Castañeda")

        def recomendarAsignaturas() :
            root.cleanRoot()
            RecomendarAsignaturas(root, self._user)
            
        def buscarCursos():
            root.cleanRoot()
            self._user.buscarCursos(root)

        def asignarCita():
            root.cleanRoot()
            AsignarCita(root, self._user)
        
        def crearHorario():
            root.cleanRoot()
            horario = self._user.crearHorario()
            BusquedaCursos(root, self._user).buscarCursos(self._user, horario)

        def inscripcionMaterias() :
            root.cleanRoot()
            IncripcionMaterias(root, self._user).inscribirMaterias(self._user)

        def bucarEstimulos():
            root.cleanRoot()

            if isinstance(self._user, Profesor):
                BusquedaEstimulos(root, self._user).buscarEstimulos(None, self._user)
            elif isinstance(self._user, Estudiante):
                BusquedaEstimulos(root, self._user).buscarEstimulos(self._user, None)
            else:
                BusquedaEstimulos(root, self._user).buscarEstimulos(None, None)

        def bucarEstimulosPorId():
            root.cleanRoot()
            BusquedaEstimulos(root, self._user).buscarEstimulosPorId()


        root.title('Mi Gestor Académico')

        # 1) MENU SUPERIOR
        barra_menus = Menu()
        # Botón archivo
        archivo = Menu(barra_menus, tearoff=False)
        archivo.add_command(label="Aplicación", command=handleAplicacion)
        archivo.add_command(label="Salir", command=root.salir)

        # Botón procesos y consultas
        procesosYConsultas = Menu(barra_menus, tearoff=False)
        if isinstance(self._user, Admin) :
            lista_procesos = [
                ('Asignar citas de inscripción', asignarCita),
                ('Ver estimulos [por nombre]', bucarEstimulosPorId),
                ('Ver estimulos [todos]', bucarEstimulos),
            ]
        elif isinstance(self._user, Estudiante) :
            lista_procesos = [
                ('Ver recomendación de asignaturas', recomendarAsignaturas),
                ('Buscar asignatura', buscarCursos),
                ('Crear horario', crearHorario),
                ('Inscribir materias', inscripcionMaterias),
                ('Ver estímulos a los que aplica', bucarEstimulos),
            ]
        else :
            lista_procesos = [
                ('Ver estímulos a los que aplica', bucarEstimulos),
            ]

        for proceso in lista_procesos :
            procesosYConsultas.add_command(label=proceso[0], command=proceso[1])
        
        # Botón ayuda
        ayuda = Menu(barra_menus, tearoff=False)
        ayuda.add_command(label="Acerca de", command=handleAcercaDe)

        # Se agregan los botones
        barra_menus.add_cascade(menu=archivo, label="Archivo")
        barra_menus.add_cascade(menu=procesosYConsultas, label="Procesos y Consultas")
        barra_menus.add_cascade(menu=ayuda, label="Ayuda")
        root.config(menu=barra_menus)
        # 2) INTERACCIÓN USUARIO
        # Interaccion Usuario
        frameInteraccion = FieldFrame(root, self._user)
        frameInteraccion.pack()
