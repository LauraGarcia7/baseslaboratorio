/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import VO.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author Johan Sánchez
 */
public class Factura implements  Serializable{
    private int idFactura,efectivoResibido, cambio;
    private Calendar fechaFactura;
    private double impuesto;
    private Cliente miCl;
    private ArrayList<ProductoVendido> productosVendidos;
    
    public Factura(int idFactura,int efectivoResibido, int cambio, Calendar fechaFactura, double impuesto, Cliente miCl) {
        this.idFactura = idFactura;
        this.efectivoResibido = efectivoResibido;
        this.cambio = cambio;
        this.fechaFactura = fechaFactura;
        this.impuesto = impuesto;
        this.miCl = miCl;
        productosVendidos = new ArrayList<>();
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getEfectivoResibido() {
        return efectivoResibido;
    }

    public void setEfectivoResibido(int efectivoResibido) {
        this.efectivoResibido = efectivoResibido;
    }

    public int getCambio() {
        return cambio;
    }

    public void setCambio(int cambio) {
        this.cambio = cambio;
    }

    public Calendar getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Calendar fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public Cliente getMiCl() {
        return miCl;
    }

    public void setMiCl(Cliente miCl) {
        this.miCl = miCl;
    }
     public void agregarProductoVendido(String nombre, int cantidad, double precio){
         this.productosVendidos.add(new ProductoVendido(nombre, cantidad, precio));
     }
     public ArrayList lista(){
         return this.productosVendidos;
     }
    public class ProductoVendido{
    private String nombre;
    private int cantidad;
    private double precioTotal;

        public ProductoVendido(String nombre, int cantidad, double precioTotal) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precioTotal = precioTotal;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(double precioTotal) {
            this.precioTotal = precioTotal;
        }
    
}
    
}
