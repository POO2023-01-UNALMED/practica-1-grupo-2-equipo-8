from ..gestorAplicacion.clasesDeUsuario.Registro import Registro
from tkinter import Button, Entry, Frame, Label, StringVar, Tk, ttk



class Login:
    @classmethod
    def iniciar(cls):
        root = Tk()
        root.state("zoomed")
        root.title("Registro")
        
        frame1 = Frame(root, width=400, height=400)
        frame1.pack(expand=True)
        frame11 = Frame(frame1)
        frame11.pack(anchor="n")
        frame12 = Frame(frame1)
        frame12.pack(anchor="c", pady=10)
        frame13 = Frame(frame1)
        frame13.pack(anchor="s")
        res = Label(frame1) 
        res.pack(anchor="s")
        titulo = Label(frame11, text="Login", font=("Arial", 20))
        titulo.pack()
        label1 = Label(frame12, text="Tipo de usuario:")
        label1.grid(row=0, column=0)
        txtv = StringVar(value="Usuarios")
        val = ["Estudiante", "Profesor", "Admin"]
        combobox = ttk.Combobox(frame12, values=val, textvariable=txtv, state="readonly")
        combobox.grid(row=0, column=1)
        label2 = Label(frame12, text="Nombre:")
        label2.grid(row=1, column=0)
        entry1 = Entry(frame12)
        entry1.grid(row=1, column=1)
        label3 = Label(frame12, text="Clave:")
        label3.grid(row=2, column=0)
        entry2 = Entry(frame12, show="*")
        entry2.grid(row=2, column=1)
        boton1 = Button(frame13, text="Login")
        boton1.pack(side="left", padx=5)
        boton2 = Button(frame13, text="Borrar")
        boton2.pack(side="right", padx=5)

        def handle(e):
            boton = e.widget.cget("text")
            if boton == "Login":
                tu = combobox.get()
                nom = entry1.get()
                clav = entry2.get()
                comp = False
                if(tu == ("Estudiante")):
                    for us in Registro.getEstudiantes():
                        if(us.getNombreUsuario()==nom and us.getClave()==clav):
                            comp = True
                            #Menu.sistema(us) #Falta ver a qué se va a redireccionar
                            break
                    if comp == True:
                        res.setvar("El Nombre o la clave no coincide")
                        handle(Login)
                
            
                elif(tu.equals("Profesor")):
                    for us in Registro.getProfesores():
                        if(us.getNombreUsuario()==nom and us.getClave()==clav):
                            comp = True
                            #Menu.sistema(us) #Falta ver a qué se va a redireccionar
                            break
                    if comp == True:
                        res.setvar("El Nombre o la clave no coincide")
                        handle(Login)
                    
                
                elif(tu.equals("Admin")):
                    for us in Registro.getAdmins():
                        if(us.getNombreUsuario()==nom and us.getClave()==clav):
                            comp = True
                            #Menu.sistema(us) #Falta ver a qué se va a redireccionar
                            break
                    if comp == True:
                        res.setvar("El Nombre o la clave no coincide")
                        handle(Login)
            else:
                combobox.set("Usuarios")
                entry1.delete(0,"end")
                entry2.delete(0,"end")
        
        boton1.bind("<Button-1>",handle)
        boton2.bind("<Button-1>",handle)
        root.mainloop()
        
if __name__ == "__main__":
    Login.iniciar()       
            
        
        
    
