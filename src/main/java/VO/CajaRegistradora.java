/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;


import VO.ComputadorPersonal;
import VO.Factura;
import VO.Cliente;
import java.util.Calendar;


/**
 *
 * @author Johan Sánchez
 */
public class CajaRegistradora {

    private int idCaja,  idVendedor;
    private ComputadorPersonal cP;
    private double valor;
    
   // private ArrayList<Producto> productosFactura;

    public CajaRegistradora(int idCaja, int idVendedor, ComputadorPersonal cP,double valor) {
        this.idCaja = idCaja;
        this.idVendedor = idVendedor;
        this.cP = cP;
        this.valor=valor;
        // this.prroductosFactura = new ArrayList<>();
    }

    //inicializar factura
    public Factura crearFactura(int efectivoResibido, int cambio,double impuesto, Cliente miCL) {
       Factura miFactura = new Factura(idCaja, efectivoResibido, cambio, Calendar.getInstance(), impuesto, miCL);
return miFactura;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public ComputadorPersonal getcP() {
        return cP;
    }

    public void setcP(ComputadorPersonal cP) {
        this.cP = cP;
    }
    
}
