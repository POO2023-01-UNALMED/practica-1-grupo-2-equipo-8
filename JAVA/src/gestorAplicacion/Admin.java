package gestorAplicacion;

import uiMain.BusquedaCursos;

public class Admin extends Registro {
    private static final long serialVersionUID = 4L;
    public Admin(String nombre, String correo, String nombreUsuario, String clave, String documento){
        super(nombre, correo, nombreUsuario, clave, documento);
        Registro.agregarAdmin(this);
    }

    // METHODS   
    @Override
    public void buscarCursos() {
        BusquedaCursos.buscarCursos();
    }
}
