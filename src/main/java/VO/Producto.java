/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Johan Sánchez
 */
public class Producto {
    private int idProducto;
    private String nombre;//10 caraacteres
    private int  cantidad;
    private double precio;
    private int codigoBarra;
    private Calendar entrada, cadusidad;
    private String proveedor;//10 caracteres
    
    private int estado;// 0->No hay, 1-> Hay, -1->Esta en bodega

    public Producto() {
    }

    public Producto(int idProducto,String nombre,  int cantidad, double precio, int codigoBarra, Calendar entrada, Calendar cadusidad, String proveedor,  int estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigoBarra = codigoBarra;
        this.entrada = entrada;
        this.cadusidad = cadusidad;
        this.proveedor = proveedor;
        
        this.estado = estado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(int codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Calendar getEntrada() {
        return entrada;
    }

    public void setEntrada(Calendar entrada) {
        this.entrada = entrada;
    }

    public Calendar getCadusidad() {
        return cadusidad;
    }

    public void setCadusidad(Calendar cadusidad) {
        this.cadusidad = cadusidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

   

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
