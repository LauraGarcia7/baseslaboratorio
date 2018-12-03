/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Administrador;
import VO.CajaRegistradora;
import VO.Factura;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *Esta clase permite administrar los registros de la clase Administrador
 * @author Johan Sánchez
 * @since 29/08/2018
 */
public class AdministradorDAO {
    private RandomAccessFile file;
    private int nregs = 0;
    private int tamañoreg = 76;// 4(entero),4,4,40 (20 char),4, 20 (10 char) --> Bytes por registro
    private boolean regsEliminados = false;
    private ArrayList<Administrador> lista;
/**
 * Constructor de la clase que abre el archivo de los vendedores
 * @param fichero
 * @throws IOException 
 */
    public AdministradorDAO(File fichero) throws IOException {
        if (fichero.exists() && !fichero.isFile()) {
            throw new IOException(fichero.getName() + "no es un fichero");
        }
        file = new RandomAccessFile(fichero, "rw");
       
        nregs = (int) Math.ceil((double) file.length() / (double) tamañoreg);
        lista =  new ArrayList<>();
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
 * Metodo que escribe en el archivo de administradores a partir de un indice
 * @param i indice
 * @param administrador objeto que se desea guardar
 * @return Si fue exitoso en el momento que se guardo un nuevo vendedor
 * @throws IOException 
 */
    public boolean escribirReg(int i, Administrador administrador) throws IOException {
        if (i >= 0 && i <= nregs) 
        {
            file.seek(i * tamañoreg);
            // public Vendedor(int idVendedor, int id, String nombre, int telefono, String correo) {
            file.writeInt(administrador.getIdAdmin());
            file.writeInt(administrador.getContraseña());
            file.writeInt(administrador.getId());
            for (int j = 0; j < 20; j++) {//40byts 20 chars
                file.writeChar(administrador.getNombre().charAt(j));
            }
            file.writeInt(administrador.getTelefono());
            for (int j = 0; j < 10; j++) {//10
                file.writeChar(administrador.getCorreo().charAt(j));
            }
                        return true;
        }
        return false;
    }
/**
 * Metodo que apartir de un indice oermite devolver un administrador en esa posición del archivo
 * @param i indice
 * @return El objeto vendedor
 * @throws IOException 
 */
    public Administrador leerReg(int i) throws IOException {
        if (i >= 0 && i < nregs) {
            file.seek(i * tamañoreg);
            int idAdmin = file.readInt();
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
            return new Administrador(idAdmin,contraseña, id, nombre, telefono, correo);
        }
        System.out.println("Numero de registro fuera de limites");
        return null;
    }
/**
 * Este metodo permite añadir nuevos administradores al final del archivo
 * @param administrador
 * @throws IOException 
 */
    public void añadirReg(Administrador administrador) throws IOException {
        if (escribirReg(nregs, administrador)) {
            nregs++;
        }
    }
/**
 * Este metodo como tal no borra el administrador del achivo, para este programa los administradores con idAdmin=0 no existen
 * es decir este metodo borra los administradores poniendo sus idAdminen 0
 * @param id identificador unico de los adminitradores
 * @return fue exitosa o no la eliminación
 * @throws IOException 
 */
    public boolean eliminarReg(int id) throws IOException {
        Administrador administrador;
        for (int i = 0; i < nregs; i++) {
            administrador = leerReg(i);
            if (administrador.getIdAdmin()== id) {
                administrador.setIdAdmin(0);
                escribirReg(i, administrador);
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
 * El metodo buscar, permite saber el indice al que pertenece el administrador en el archivo aleatorio a partir de su id
 * @param id es la variable que pertenece al administrador como trabajador del supermercado (En la clase se conoce como idVendedor)
 * @return el indice que pertenece, en el archivo, el vendedorv ó -1 que indica que no esta.
 * @throws IOException 
 */
    public int buscarReg(int id) throws IOException {
        Administrador administrador;
        int idAdmin;
        if (id == 0) {//Fue eliminado
            return -1;
        }
       
        for (int i = 0; i < nregs; i++) {
            administrador = leerReg(i);
            idAdmin = administrador.getIdAdmin();
            if (id == idAdmin) {
                
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
   
   /**
    * Este metodo facilit saber el n ombre de los productos vendidps y su total
    * @return Todo los productos vendidos
    * @throws IOException 
    */
   public ArrayList ventasPorProductos() throws IOException{
       File fichero = new File("facturas");
       FacturaDAO miF =  new FacturaDAO(fichero);
       ArrayList<Factura> facturas = miF.convertirArchivoALista();
       ArrayList<Factura.ProductoVendido> misProductos= new ArrayList<>();
       for (int i = 0; i < facturas.size(); i++) {
           Factura f = facturas.get(i);
           for (int j = 0; j < f.lista().size(); j++) {
               misProductos.add((Factura.ProductoVendido) f.lista().get(i));
           }
       }
       return misProductos;
   }
   /**
    * Este metodo permite saber cada vendedor que ganancias a dado o perdidas
    * @return todos los vendeedores con sus respectivas ventas
    * @throws IOException 
    */
   public ArrayList ventasPorVendedor() throws IOException{
       ArrayList<VentaVendedor> misVen = new ArrayList<>();
       File fichero = new File("cajas.txt");
       CajaDAO miDao = new CajaDAO(fichero);
       ArrayList<CajaRegistradora> misCajas = miDao.convertirArchivoALista();
       
       for (int i = 0; i < misCajas.size(); i++) {
           misVen.add(new VentaVendedor(misCajas.get(i).getIdVendedor(), (misCajas.get(i).getValor()-500000)));
       }
       return misVen;
   }
   /**
    * Esta clae la utilizamos para manipular los datos de las ventas de un endedor especifico
    */
   public class VentaVendedor{
       private int idVendedor;
       private double venta;

        public VentaVendedor(int idVendedor, double venta) {
            this.idVendedor = idVendedor;
            this.venta = venta;
        }

        public int getNombre() {
            return idVendedor;
        }

        public void setNombre(int idVendedor) {
            this.idVendedor = idVendedor;
        }

        public double getVenta() {
            return venta;
        }

        public void setVenta(double venta) {
            this.venta = venta;
        }
       
   }
}
