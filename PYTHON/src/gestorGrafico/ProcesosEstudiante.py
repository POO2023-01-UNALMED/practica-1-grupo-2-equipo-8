from tkinter import Frame, Label, CENTER

from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Registro import Registro

class RecomendarAsignaturas(Frame) :
    def __init__(self, root, estudiante:Estudiante) :
        super().__init__(root)

        frameTitulo = Frame(root)
        Label(frameTitulo, text="RECOMENDACION DE ASIGNATURAS").pack()
        Label(frameTitulo, text="A continuación se muestran las asignaturas recomendadas para cursar el próximo semestre:").pack()
        frameTitulo.anchor(CENTER)
        frameTitulo.pack()

        cursosParaRecomendar = []

        for curso in Registro.getCursos() :
            esDeLaCarrera = estudiante.getCarrera in curso.getCarrerasRelacionadas()
            vioCurso = estudiante.vioCurso(curso)
            if vioCurso or not esDeLaCarrera : continue

            vioPrerrequisitos = curso.vioPrerrequisitos(estudiante)
            if vioPrerrequisitos : cursosParaRecomendar.append(curso)

        
        