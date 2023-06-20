import tkinter as tk
from tkinter import ttk
from gestorAplicacion.clasesDeUsuario.Admin import Admin

from gestorAplicacion.clasesDeUsuario.Estudiante import Estudiante
from gestorAplicacion.clasesDeUsuario.Profesor import Profesor

class FieldFrame(tk.Frame):
    def __init__(self, root, tituloProceso=None, descripcionProceso=None, criterios=None, valores = None, valoresCombo = None, user = None) :
        super().__init__(root)

        # Si se abre la ventana por primera vez
        if tituloProceso == None :
            if isinstance(user, Admin):
                # Crear ventana
                ventana = root

                # Configurar título de la ventana
                ventana.title("Mi Gestor Académico")

                # Crear label para el título
                titulo_label = tk.Label(ventana, text="Mi Gestor Académico", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear frame para la parte izquierda
                frame_izquierda = tk.Frame(ventana)
                frame_izquierda.pack(fill="both", side=tk.LEFT, padx=10)

                # Crear label para el título
                titulo_label = tk.Label(frame_izquierda, text="Resumen", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear texto para la descripción
                descripcion_text = tk.Text(frame_izquierda, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                descripcion_text.insert(tk.END, "Nuestra aplicación es una plataforma integral diseñada para " \
                                    "administrar y ejecutar diversos procesos y servicios académicos. " \
                                    "Esta herramienta facilitará a los estudiantes realizar una " \
                                    "variedad de tareas para ayudarlos en su organización dentro de " \
                                    "la institución universitaria. \n\n Para utilizar las funcionalidades " \
                                    "de nuestra aplicación, simplemente dirígete a la esquina " \
                                    "superior izquierda y selecciona la acción que deseas realizar. " \
                                    "Las opciones disponibles se mostrarán de acuerdo al tipo de " \
                                    "usuario con el que iniciaste sesión. Además, en el lado derecho" \
                                    ", encontrarás las funcionalidades específicas " \
                                    "para cada tipo de usuario.")
                descripcion_text.configure(state="disable")
                descripcion_text.pack(fill="both", pady=10)

                # Crear frame para la parte derecha
                frame_derecha = tk.Frame(ventana)
                frame_derecha.pack(fill="both", side=tk.RIGHT, padx=10)

                alto = 8
                ancho = 70

                # Crear label y texto para la sección "Admin"
                admin_label = tk.Label(frame_derecha, text="Funcionalidades del Administrador", font=("Times New Roman", 30, "bold"))
                admin_label.pack(fill="both", pady=10)

                admin_texto = tk.Text(frame_derecha, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                admin_texto.insert(tk.END,
                        "ASIGNAR CITAS DE INSCRIPCIÓN:\nPermite al Administrador seleccionar a varios o a todos los estudiantes "\
                        "y asignarles una cita para poder inscribir. La función ordenará a los estudiantes seleccionados por PAPA "\
                        "para asignar las citas, además, cuenta con dos opciones, una para resetear los cursos y prepararlos para un "\
                        "nuevo semestre y otra para resetear las citas viejas. "\
                        "\n\n"\
                        "BUSCAR ESTIMULOS:\nPermite ver todos los estímulos disponibles tanto para estudiantes como profesores, "\
                        "se puede decidir si verlos todos o buscar uno en específico.")
                admin_texto.configure(state="disable")
                admin_texto.pack(fill="both")

            elif isinstance(user, Estudiante):
                # Crear ventana
                ventana = root

                # Configurar título de la ventana
                ventana.title("Mi Gestor Académico")

                # Crear label para el título
                titulo_label = tk.Label(ventana, text="Mi Gestor Académico", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear frame para la parte izquierda
                frame_izquierda = tk.Frame(ventana)
                frame_izquierda.pack(fill="both", side=tk.LEFT, padx=10)

                # Crear label para el título
                titulo_label = tk.Label(frame_izquierda, text="Resumen", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear texto para la descripción
                descripcion_text = tk.Text(frame_izquierda, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                descripcion_text.insert(tk.END, "Nuestra aplicación es una plataforma integral diseñada para " \
                                    "administrar y ejecutar diversos procesos y servicios académicos. " \
                                    "Esta herramienta facilitará a los estudiantes realizar una " \
                                    "variedad de tareas para ayudarlos en su organización dentro de " \
                                    "la institución universitaria. \n\n Para utilizar las funcionalidades " \
                                    "de nuestra aplicación, simplemente dirígete a la esquina " \
                                    "superior izquierda y selecciona la acción que deseas realizar. " \
                                    "Las opciones disponibles se mostrarán de acuerdo al tipo de " \
                                    "usuario con el que iniciaste sesión. Además, en el lado derecho" \
                                    ", encontrarás las funcionalidades específicas " \
                                    "para cada tipo de usuario.")
                descripcion_text.configure(state="disable")
                descripcion_text.pack(fill="both", pady=10)

                # Crear frame para la parte derecha
                frame_derecha = tk.Frame(ventana)
                frame_derecha.pack(fill="both", side=tk.RIGHT, padx=10)

                alto = 8
                ancho = 70

                # Crear label y texto para la sección "Admin"
                admin_label = tk.Label(frame_derecha, text="Funcionalidades del Estudiante", font=("Times New Roman", 30, "bold"))
                admin_label.pack(fill="both", pady=10)

                admin_texto = tk.Text(frame_derecha, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                admin_texto.insert(tk.END,
                        "Ver Recomendación de Asignaturas:\nSe le recomiendan materias al estudiante para el próximo semestre teniendo en cuenta su carrera y su historial académico. "\
                        "Primero, se le pregunta al estudiante si desea añadir libre elecciones o no, luego se muestra una lista de cursos recomendados donde se puede seleccionar uno para ver los detalles de este. "\
                        "\n\n"\
                        "Buscar Asignatura:\nPermite buscar y ver información de un curso en específico. Solo debes seleccionar un filtro ya sea Facultad, Carrera, Horario o Ninguno si deseas verlos todos. Luego simplemente debes seleccionar el curso que estás buscando y se desplegarán detalles sobre él. "\
                        "\n\n"\
                        "Crear Horario:\nPermite al estudiante crear varios horarios para posteriormente usarlos a la hora de inscribir materias. Solo debes buscar la materia de interés y añadirla al horario que estés creando. "\
                        "\n\n"\
                        "Inscribir Materias:\nPermite al estudiante inscribir las materias para el próximo semestre. Esto lo puedes hacer manualmente, donde deberás buscar una a una las asignaturas, o puedes seleccionar uno de los horarios que hiciste anteriormente y el proceso se hará de manera más eficiente. "\
                        "\n\n"\
                        "Ver estímulos a los que aplica:\nPermite al estudiante ver todos los estímulos a los cuales podría optar, ya que cumple con los requisitos exigidos.")
                admin_texto.configure(state="disable")
                admin_texto.pack(fill="both")

            elif isinstance(user, Profesor):
                # Crear ventana
                ventana = root

                # Configurar título de la ventana
                ventana.title("Mi Gestor Académico")

                # Crear label para el título
                titulo_label = tk.Label(ventana, text="Mi Gestor Académico", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear frame para la parte izquierda
                frame_izquierda = tk.Frame(ventana)
                frame_izquierda.pack(fill="both", side=tk.LEFT, padx=10)

                # Crear label para el título
                titulo_label = tk.Label(frame_izquierda, text="Resumen", font=("Times New Roman", 30, "bold"))
                titulo_label.pack(fill="both", pady=10)

                # Crear texto para la descripción
                descripcion_text = tk.Text(frame_izquierda, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                descripcion_text.insert(tk.END, "Nuestra aplicación es una plataforma integral diseñada para " \
                                    "administrar y ejecutar diversos procesos y servicios académicos. " \
                                    "Esta herramienta facilitará a los estudiantes realizar una " \
                                    "variedad de tareas para ayudarlos en su organización dentro de " \
                                    "la institución universitaria. \n\n Para utilizar las funcionalidades " \
                                    "de nuestra aplicación, simplemente dirígete a la esquina " \
                                    "superior izquierda y selecciona la acción que deseas realizar. " \
                                    "Las opciones disponibles se mostrarán de acuerdo al tipo de " \
                                    "usuario con el que iniciaste sesión. Además, en el lado derecho" \
                                    ", encontrarás las funcionalidades específicas " \
                                    "para cada tipo de usuario.")
                descripcion_text.configure(state="disable")
                descripcion_text.pack(fill="both", pady=10)

                # Crear frame para la parte derecha
                frame_derecha = tk.Frame(ventana)
                frame_derecha.pack(fill="both", side=tk.RIGHT, padx=10)

                alto = 8
                ancho = 70

                # Crear label y texto para la sección "Admin"
                admin_label = tk.Label(frame_derecha, text="Funcionalidades del Profesor", font=("Times New Roman", 30, "bold"))
                admin_label.pack(fill="both", pady=10)

                admin_texto = tk.Text(frame_derecha, font=("Times New Roman", 17), width=55, height=20, wrap="word")
                admin_texto.insert(tk.END,
                        "Ver Estímulos a los que Aplica:\nPermite al profesor ver todos los estímulos a los cuales podría optar, ya que cumple con los requisitos exigidos.")
                admin_texto.configure(state="disable")
                admin_texto.pack(fill="both")

        self._root = root
        self._tituloProceso = tituloProceso
        self._descripcionProceso = descripcionProceso
        self._criterios = criterios
        self._valores = valores
        self._entrys = []
        self._entradasUsuario = []
        if valoresCombo == None:
            self._valoresCombo = []
        else:
            self._valoresCombo = valoresCombo
        
        # Titulo proceso
        frameTituloProceso = tk.Frame(root)
        tk.Label(frameTituloProceso, text=self._tituloProceso, font=("arial", 30)).pack()
        frameTituloProceso.anchor(tk.CENTER)
        frameTituloProceso.pack()

        # Descripcion proceso
        frameDescripcionProceso = tk.Frame(root)
        tk.Label(frameDescripcionProceso, text=self._descripcionProceso, font=("arial", 15)).pack()
        frameDescripcionProceso.anchor(tk.CENTER)
        frameDescripcionProceso.pack()

        # Valores
        valoresCombo = []
        for x in self._valoresCombo:
            valoresCombo.append(x)
        
        frameValores = tk.Frame(root, name="valores")
        if criterios != None:
            cont = 1
            for i in range(len(criterios)) :
                criterio = tk.Label(frameValores, text=self._criterios[i])
                criterio.grid(row=i, column=0)

                if valores[i] == "combobox":
                    valor = ttk.Combobox(frameValores, values=valoresCombo[0], width=30, name=str(cont), state="readonly")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                    valoresCombo.pop(0)
                    cont+=1
                elif valores[i] == "comboboxdeshabilitado":
                    valor = ttk.Combobox(frameValores, values=valoresCombo[0], width=30, name=str(cont), state="disabled")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                    valoresCombo.pop(0)
                    cont+=1
                elif valores[i] == "deshabilitado":
                    valor = tk.Entry(frameValores, state="disabled")
                    valor.grid(row=i, column=1)
                    self._entrys.append(valor)
                elif valores[i] == 'radio' :
                    frameRadios = tk.Frame(frameValores)
                    radio = tk.IntVar()
                    tk.Radiobutton(frameRadios, text='Sí', variable=radio, value=1).grid(row=0, column=0)
                    tk.Radiobutton(frameRadios, text='No', variable=radio, value=0).grid(row=0, column=1)
                    self._entrys.append(radio)
                    frameRadios.grid(row=i, column=1)
                else :
                    valor = tk.Entry(frameValores)
                    valor.grid(row=i, column=1)
                    valor.insert(0, self._valores[i])
                    self._entrys.append(valor)
        frameValores.anchor(tk.CENTER)
        frameValores.pack()

    def handleAceptar(self) :
            self.getEntradasUsuario()
            self._entradas = self._entradasUsuario
            self._root.cleanRoot()
            self.recomendar()

    def getEntradasUsuario(self) :
        for entrada in self._entrys :
            self._entradasUsuario.append(entrada.get())

    def crearBoton(self, texto, comando=None) :
        return tk.Button(self, text=texto, command=comando, font=("arial", 15))
