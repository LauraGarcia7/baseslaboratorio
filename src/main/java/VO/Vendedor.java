/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import VO.Persona;

/**
 *
 * @author Johan Sánchez
 */
public class Vendedor extends Persona{
    private int idVendedor,contraseña;

    @Override
    public String toString() {
        return "Vendedor{" + "idVendedor=" + idVendedor + ", contrase\u00f1a=" + contraseña + '}';
    }

    public Vendedor(int idVendedor,int contraseña, int id, String nombre, int telefono, String correo) {
        super(id, nombre, telefono, correo);
        this.idVendedor = idVendedor;
        this.contraseña= contraseña;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }
    
}
