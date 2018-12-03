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
public class ComputadorPersonal {
    private int idCompu;
    private String marca;

    public ComputadorPersonal(int idCompu, String marca) {
        this.idCompu = idCompu;
        this.marca = marca;
    }

    public int getIdCompu() {
        return idCompu;
    }

    public void setIdCompu(int idCompu) {
        this.idCompu = idCompu;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
