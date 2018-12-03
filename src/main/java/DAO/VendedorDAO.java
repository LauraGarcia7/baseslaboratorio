/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Vendedor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Clase que me permite administrar los archivos de los vendedores
 * @author Johan Sánchez
 * @since 29/08/2018
 */

public class VendedorDAO {

    private RandomAccessFile file;
    private int nregs = 0;
    private int tamañoreg = 76;// 4(entero),4,4,40 (20 char),4, 20 (10 char) --> Bytes por registro
    private boolean regsEliminados = false;
    private ArrayList<Vendedor> lista;
/**
 * Constructor de la clase que abre el archivo de los vendedores
 * @param fichero
 * @throws IOException 
 */
    public VendedorDAO(File fichero) throws IOException {
        if (fichero.exists() && !fichero.isFile()) {
            throw new IOException(fichero.getName() + "no es un fichero");
        }
        file = new RandomAccessFile(fichero, "rw");
       
        nregs = (int) Math.ceil((double) file.length() / (double) tamañoreg);
        lista  = new ArrayList<>();
    }
/**
 * Cierra el flujo de datos del archivo
 * @throws IOException 
 */
    public void cerrarFichero() throws IOException {
        file.close();
    }
/**
 * Permite manipular cuantos registros se hanhecho en el archivo
 * @return número de registros
 */
    public int numeroDeRegs() {
        return nregs;
    }
/**
 * Metodo que escribe en el archivo de vendedores a partir de un indice
 * @param i indice
 * @param vendedor objeto que se desea guardar
 * @return Si fue exitoso en el momento que se guardo un nuevo vendedor
 * @throws IOException 
 */
    public boolean escribirReg(int i, Vendedor vendedor) throws IOException {
        if (i >= 0 && i <= nregs) 
        {
            file.seek(i * tamañoreg);
            // public Vendedor(int idVendedor, int id, String nombre, int telefono, String correo) {
            file.writeInt(vendedor.getIdVendedor());
            file.writeInt(vendedor.getContraseña());
            file.writeInt(vendedor.getId());
            for (int j = 0; j < 20; j++) {//40byts 20 chars
                file.writeChar(vendedor.getNombre().charAt(j));
            }
            file.writeInt(vendedor.getTelefono());
            for (int j = 0; j < 10; j++) {//10
                file.writeChar(vendedor.getCorreo().charAt(j));
            }
                        return true;
        }
        return false;
    }
/**
 * Metodo que apartir de un indice oermite devolver un vendedor en esa posición del archivo
 * @param i indice
 * @return El objeto vendedor
 * @throws IOException 
 */
    public Vendedor leerReg(int i) throws IOException {
        if (i >= 0 && i < nregs) {
            file.seek(i * tamañoreg);
            int idVendedor = file.readInt();
            int contraseña = file.readInt();
            int id = file.readInt();
            String nombre = "";
            for (int j = 0; j < 20; j++) {
                nombre = nombre + file.readChar();
            }

            int telefono = file.readInt();
            String correo = "";
            for (int j = 0; j < 10; j++) {
                correo = correo + file.readChar();
            }
            return new Vendedor(idVendedor,contraseña, id, nombre, telefono, correo);
        }
        System.out.println("Numero de registro fuera de limites");
        return null;
    }
/**
 * Este metodo permite añadir nuevos vendedores al final del archivo
 * @param vendedor
 * @throws IOException 
 */
    public void añadirReg(Vendedor vendedor) throws IOException {
        if (escribirReg(nregs, vendedor)) {
            nregs++;
        }
    }
/**
 * Este metodo como tal no borra el vendedor del achivo, para este programa los vendedores con idVendedor=0 no existen
 * es decir este metodo borra los vendedores poniendo sus idVendedor en 0
 * @param id identificador unico de los vendedores
 * @return fue exitosa o no la eliminación
 * @throws IOException 
 */
    public boolean eliminarReg(int id) throws IOException {
        Vendedor vendedor;
        for (int i = 0; i < nregs; i++) {
            vendedor = leerReg(i);
            if (vendedor.getIdVendedor() == id) {
                vendedor.setIdVendedor(0);
                escribirReg(i, vendedor);
                regsEliminados = true;
                return true;
            }
        }
        return false;
    }

    public boolean tieneRegseliminados() {
        return regsEliminados;
    }
/**
 * El metodo buscar, permite saber el indice al que pertenece el vendedor en el archivo aleatorio a partir de su id
 * @param id es la variable que pertenece al vendedor como trabajador del supermercado (En la clase se conoce como idVendedor)
 * @return el indice que pertenece, en el archivo, el vendedorv ó -1 que indica que no esta.
 * @throws IOException 
 */
    public int buscarReg(int id) throws IOException {
        Vendedor vendedor;
        int idVendedor;
        if (id == 0) {//Fue eliminado
            return -1;
        }
       
        for (int i = 0; i < nregs; i++) {
            vendedor = leerReg(i);
            idVendedor = vendedor.getIdVendedor();
            if (id == idVendedor) {
                
                return i;
            }

        }
        return -1;

    }
   
/**
 * Imprime en pantalla el tamaño del archivo
 * @throws IOException 
 */
    public void tamañoArchivo() throws IOException{
        System.out.println("Tamaño: "+file.length());
    }
    /**
     * permite tomar como variable el valor de el tamaño del archivo
     * @return el tamañ del archivo
     */
    public int size(){
        return this.tamañoreg;
    }
    /**
     * Este metodo permite agregar los datos del archivo aleatorio en una lista, con el fin de convertirloen Obervablelist; para
     * añadir los datos n una tabla javafx
     * @return la lista convertida
     * @throws IOException  
     */
   public ArrayList convertirArchivoALista() throws IOException{
       for (int i = 0; i < nregs; i++) {
           this.lista.add(leerReg(i));
       }
       return this.lista;
   }
  
   
}
