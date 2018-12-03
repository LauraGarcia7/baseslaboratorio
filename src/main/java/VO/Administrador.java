/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author Johan Sánchez
 */
public class Administrador extends Persona{
    
    private int idAdmin;
    private int contraseña;

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }

    public Administrador(int idAdmin,int contraseña, int id, String nombre, int telefono, String correo) {
        super(id, nombre, telefono, correo);
        this.idAdmin = idAdmin;
        this.contraseña = contraseña; 
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    
}
    

