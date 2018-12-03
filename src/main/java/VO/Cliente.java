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
public class Cliente extends Persona{
    private int idCliente;

    public Cliente(int idCliente, int id, String nombre, int telefono, String correo) {
        super(id, nombre, telefono, correo);
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
