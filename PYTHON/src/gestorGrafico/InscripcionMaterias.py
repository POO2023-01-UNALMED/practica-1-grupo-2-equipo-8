from gestorGrafico.BusquedaCursos import BusquedaCursos
from gestorAplicacion.clasesDeUsuario.Registro import Registro
from gestorAplicacion.clasesExtra.Horario import Horario

class IncripcionMaterias:
    @staticmethod
    def inscribirMaterias(estudiante):
        comp = False
        if estudiante.getInscribir():
            while True:
                print("Indique lo que desea hacer:\n"
                      + "1. Incribir materias manualmente\n"
                      + "2. Incribir materias a partir de un horario creado\n"
                      + "3. Volver")
                opcion = input()
                if opcion not in ["1", "2", "3"]:
                    print("Debe seleccionar una opción entre el 1 y el 3")
                    continue
                if opcion == "1":
                    IncripcionMaterias.inscribirManualmente(estudiante)
                    break
                elif opcion == "2":
                    IncripcionMaterias.inscribirConHorario(estudiante)
                    break
                elif opcion == "3":
                    return

        else:
            print("Usted no puede inscribir cursos en este momento, pero te redirigiremos a crear un horario\n"
                  + "Este horario te servirá para inscribir (en el momento en el que puedas inscribir) automáticamente las materias que guardaste en dicho horario")
            horario = estudiante.crearHorario()
            BusquedaCursos.buscarCursos(estudiante, horario)

    @staticmethod
    def inscribirManualmente(estudiante):
        print("Selección de asignaturas")
        listaCursos = []
        cursosPosibles = list(Registro.getCursos().copy())
        cursosAprobados = list(estudiante.getListaCursos().copy())

        cursosBorrar = []
        for ce in cursosAprobados:
            if ce.calcularPromedio() < 3:
                cursosBorrar.append(ce)
        for curso in cursosBorrar:
            cursosAprobados.remove(curso)

        cursosABorrar = []
        for c1 in cursosPosibles:
            for c2 in cursosAprobados:
                if c1.getNombre() == c2.getNombre():
                    cursosABorrar.append(c1)
        for curso in cursosABorrar:
            cursosPosibles.remove(curso)

        cursosABorrar = []
        for c1 in cursosPosibles:
            for c2 in estudiante.getCursosVistos():
                if c1.getNombre() == c2.getNombre():
                    cursosABorrar.append(c1)
        for curso in cursosABorrar:
            cursosPosibles.remove(curso)

        while True:
            cursosres = cursosPosibles.copy()
            for curso in listaCursos:
                for c in cursosPosibles:
                    if c.getNombre() == curso.getNombre():
                        cursosres.remove(c)

            if not cursosres:
                print("No hay cursos disponibles")
                break

            print("Seleccione un curso que quiera inscribir: ")
            cont = 1
            print("Los cursos disponibles son:\n"
                  + "\t{:>2}\t{:32}\t{:8}\t{:17}\t{}".format("ID", "Nombre", "Creditos", "Facultad", "Programas relacionados"))
            print("----------------------------------------------------------------------------------------------------------------------")
            for curso in cursosres:
                print("\t{:>2}\t{:32}\t{:8}\t{:17}\t{}".format(curso.getId(), curso.getNombre(), curso.getCreditos(), curso.getFacultad(), curso.getCarrerasRelacionadas()))
                print("{}\tInscribir".format(cont))
                cont += 1
            print("{}\tNo agregar más cursos".format(cont))
            while True:
                opcion = input()
                if opcion == str(cont):
                    if not listaCursos:
                        print("No has inscrito ningún curso")
                        break
                    else:
                        comp = True
                        break

                for x in range(1, len(cursosres) + 1):
                    if opcion == str(x):
                        curso = cursosres[x - 1]
                        ce = IncripcionMaterias.mostrarDetalles(estudiante, curso)
                        listaCursos.append(ce)
                        break

                else:
                    print("Debe seleccionar un número entre el 1 y el {}".format(cont))
                    continue
                break

            if comp:
                break

        horario = Horario(listaCursos)
        if horario.validarHorario(estudiante):
            estudiante.inscribirCursos(listaCursos)
            print("La inscripción fue exitosa")
        else:
            print("Los cursos seleccionados presentan inconsistencias (hay horarios cruzados o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.")
            IncripcionMaterias.inscribirMaterias(estudiante)

    @staticmethod
    def inscribirConHorario(estudiante):
        if not estudiante.getHorariosCreados():
            print("No ha creado ningún horario, debes inscribir manualmente")
            IncripcionMaterias.inscribirManualmente(estudiante)
        else:
            while True:
                cont = 1
                for horario in estudiante.getHorariosCreados():
                    print("Horario {}:\n".format(horario.getId()))
                    print("Los cursos disponibles son:\n"
                          + "\t{:32}\t{:8}\t{:25}\t{:39}\t{}".format("Nombre", "Creditos", "Profesor", "Horario", "Cupos"))
                    print("----------------------------------------------------------------------------------------------------------------------------------------------")
                    for ce in horario.getCursos():
                        print("\t{:32}\t{:8}\t{:25}\t{:39}\t{}".format(ce.getNombre(), ce.getCreditos(), ce.getProfesor().getNombre(), ce.getHorario(), ce.getCupos()))
                    print("\n{}\tSeleccionar\n".format(cont))
                    cont += 1

                opcion = input()
                comp = True
                for x in range(1, cont):
                    if opcion == str(x):
                        horario = estudiante.getHorariosCreados()[x - 1]
                        if horario.validarHorario(estudiante):
                            estudiante.inscribirCursos(horario.getCursos())
                            print("La inscripción fue exitosa")
                        else:
                            print("Los cursos seleccionados presentan inconsistencias (hay horarios cruzados, el horario contiene cursos que ya aprobaste o no cumples con los requisitos de algún curso). Deberá hacer el proceso de nuevo.")
                            IncripcionMaterias.inscribirMaterias(estudiante)
                        comp = False
                        break

                if comp:
                    print("Debe seleccionar una opción entre el 1 y el {}".format(cont - 1))
                    continue
                break

    @staticmethod
    def mostrarDetalles(estudiante, curso):
        while True:
            listaCursos = curso.obtenerGrupos(estudiante)
            print("{}({})\n{}\n{}\n{}".format(curso.getNombre(), curso.getId(), curso.getCreditos(), curso.getFacultad(), curso.getCarrerasRelacionadas()))
            cont = 1
            for ce in listaCursos:
                print("Profesor: {}\nHorario: {}\nCupos: {}".format(ce.getProfesor(), ce.getHorario(), ce.getCupos()))
                print("{}\tInscribir".format(cont))
                cont += 1
            print("{}\tvolver".format(cont))
            opcion = input()
            if opcion == str(cont):
                IncripcionMaterias.inscribirManualmente(estudiante)

            comp = True
            for x in range(1, cont):
                if opcion == str(x):
                    if curso.obtenerGrupos() and listaCursos[x - 1].getCupos() > 0:
                        print("----------------------------------------------------------------------------------------------------------------------------------------------")
                        print("El curso se agregó correctamente")
                        print("----------------------------------------------------------------------------------------------------------------------------------------------")
                        return listaCursos[x - 1]
                    else:
                        print("El curso seleccionado no tiene profesores asignados o no tiene cupos")
                    comp = False
                    break

            if comp:
                print("Debe seleccionar una opción entre el 1 y el {}".format(cont))
