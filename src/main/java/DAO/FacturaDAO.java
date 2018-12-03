/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Cliente;
import VO.Factura;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Johan Sánchez
 */
public class FacturaDAO {

    private RandomAccessFile file;
    private int nregs = 0;
    private int tamañoreg = 104;// 4,4,4,12(Date 4,4,4[año,mes,dia]),8(double),60 (Cliente 4,4,40 nombre,4, 20 direccion)
    private boolean regsEliminados = false;
    private ArrayList<Factura> lista;

    /**
     * Constructor de la clase que abre el archivo de los vendedores
     *
     * @param fichero
     * @throws IOException
     */
    public FacturaDAO(File fichero) throws IOException {
        if (fichero.exists() && !fichero.isFile()) {
            throw new IOException(fichero.getName() + "no es un fichero");
        }
        file = new RandomAccessFile(fichero, "rw");

        nregs = (int) Math.ceil((double) file.length() / (double) tamañoreg);
        lista =  new ArrayList<>();
    }

    /**
     * Cierra el flujo de datos del archivo
     *
     * @throws IOException
     */
    public void cerrarFichero() throws IOException {
        file.close();
    }

    /**
     * Permite manipular cuantos registros se hanhecho en el archivo
     *
     * @return número de registros
     */
    public int numeroDeRegs() {
        return nregs;
    }

    /**
     * Metodo que escribe en el archivo de vendedores a partir de un indice
     *
     * @param i indice
     * @param factura objeto que se desea guardar
     * @return Si fue exitoso en el momento que se guardo un nuevo vendedor
     * @throws IOException
     */
    public boolean escribirReg(int i, Factura factura) throws IOException {
        if (i >= 0 && i <= nregs) {
            file.seek(i * tamañoreg);
            // public Vendedor(int idVendedor, int id, String nombre, int telefono, String correo) {

            file.writeInt(factura.getIdFactura());
            file.writeInt(factura.getEfectivoResibido());
            file.writeInt(factura.getCambio());

            Calendar fecha = factura.getFechaFactura();
            file.writeInt(fecha.get(Calendar.YEAR));
            file.writeInt(fecha.get(Calendar.MONTH));
            file.writeInt(fecha.get(Calendar.DAY_OF_MONTH));

            file.writeDouble(factura.getImpuesto());

            Cliente c = factura.getMiCl();
            file.writeInt(c.getIdCliente());
            file.writeInt(c.getId());
            String nombre = c.getNombre();
            for (int j = 0; j < 20; j++) {//20 caracteres para el nombre
                file.writeChar(nombre.charAt(i));
            }

            file.writeInt(c.getTelefono());
            String direccion = c.getCorreo();
            for (int j = 0; j < 10; j++) {
                file.writeChar(direccion.charAt(i));
            }

            return true;
        }
        return false;
    }

    /**
     * Metodo que apartir de un indice oermite devolver un vendedor en esa
     * posición del archivo
     *
     * @param i indice
     * @return El objeto vendedor
     * @throws IOException
     */
    public Factura leerReg(int i) throws IOException {
        if (i >= 0 && i < nregs) {
            file.seek(i * tamañoreg);
            int idFactura = file.readInt();
            int efectivo = file.readInt();
            int cambio = file.readInt();

            int year = file.readInt();
            int month = file.readInt();
            int day = file.readInt();
            Calendar fechaFactura = Calendar.getInstance();
            fechaFactura.set(year, month, day);;

            double impuesto = file.readDouble();

            int idCliente = file.readInt();
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
            Cliente miCl =  new Cliente(idCliente, id, nombre, telefono, correo);
            return new Factura(idFactura, efectivo, id, fechaFactura, telefono, miCl);
        }
        System.out.println("Numero de registro fuera de limites");
        return null;
    }

    /**
     * Este metodo permite añadir nuevos vendedores al final del archivo
     *
     * @param factura
     * @throws IOException
     */
    public void añadirReg(Factura factura) throws IOException {
        if (escribirReg(nregs, factura)) {
            nregs++;
        }
    }

    /**
     * Este metodo como tal no borra el vendedor del achivo, para este programa
     * los vendedores con idVendedor=0 no existen es decir este metodo borra los
     * vendedores poniendo sus idVendedor en 0
     *
     * @param id identificador unico de los vendedores
     * @return fue exitosa o no la eliminación
     * @throws IOException
     */
    public boolean eliminarReg(int id) throws IOException {
        Factura factura;
        for (int i = 0; i < nregs; i++) {
            factura = leerReg(i);
            if (factura.getIdFactura() == id) {
                factura.setIdFactura(id);
                escribirReg(i, factura);
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
     * El metodo buscar, permite saber el indice al que pertenece el vendedor en
     * el archivo aleatorio a partir de su id
     *
     * @param id es la variable que pertenece al vendedor como trabajador del
     * supermercado (En la clase se conoce como idVendedor)
     * @return el indice que pertenece, en el archivo, el vendedorv ó -1 que
     * indica que no esta.
     * @throws IOException
     */
    public int buscarReg(int id) throws IOException {
        Factura factura;
        int idFactura;
        if (id == 0) {//Fue eliminado
            return -1;
        }

        for (int i = 0; i < nregs; i++) {
            factura = leerReg(i);
            idFactura = factura.getIdFactura();
            if (id == idFactura) {

                return i;
            }

        }
        return -1;

    }

    /**
     * Imprime en pantalla el tamaño del archivo
     *
     * @throws IOException
     */
    public void tamañoArchivo() throws IOException {
        System.out.println("Tamaño: " + file.length());
    }

    /**
     * permite tomar como variable el valor de el tamaño del archivo
     *
     * @return el tamañ del archivo
     */
    public int size() {
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
