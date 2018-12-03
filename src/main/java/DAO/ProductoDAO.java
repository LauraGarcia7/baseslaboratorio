/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Producto;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Johan Sánchez
 */
public class ProductoDAO {

    private RandomAccessFile file;
    private int nregs = 0;
    private int tamañoreg = 88;//revizar 4(entero),4,4,40 (20 char),4, 20 (10 char) --> Bytes por registro
    private boolean regsEliminados = false;
private ArrayList<Producto>lista;
    /**
     * Constructor de la clase que abre el archivo de los vendedores
     *
     * @param fichero
     * @throws IOException
     */
    public ProductoDAO(File fichero) throws IOException {
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
     * @param vendedor objeto que se desea guardar
     * @return Si fue exitoso en el momento que se guardo un nuevo vendedor
     * @throws IOException
     */
    public boolean escribirReg(int i, Producto producto) throws IOException {
        if (i >= 0 && i <= nregs) {
            file.seek(i * tamañoreg);
            file.writeInt(producto.getIdProducto());
            // public Vendedor(int idVendedor, int id, String nombre, int telefono, String correo) {
            String nombre = producto.getNombre();
            for (int j = 0; j < 10; j++) {
                file.writeChar(nombre.charAt(i));
            }

            file.writeInt(producto.getCantidad());
            file.writeDouble(producto.getPrecio());
            file.writeInt(producto.getCodigoBarra());

            Calendar entrada = producto.getEntrada();
            file.writeInt(entrada.get(Calendar.YEAR));
            file.writeInt(entrada.get(Calendar.MONTH));
            file.writeInt(entrada.get(Calendar.DAY_OF_MONTH));

            Calendar caducidad = producto.getEntrada();
            file.writeInt(caducidad.get(Calendar.YEAR));
            file.writeInt(caducidad.get(Calendar.MONTH));
            file.writeInt(caducidad.get(Calendar.DAY_OF_MONTH));

            String proveedor = producto.getProveedor();
            for (int j = 0; j < 10; j++) {//40byts 20 chars
                file.writeChar(proveedor.charAt(j));
            }
            file.writeInt(producto.getEstado());

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
    public Producto leerReg(int i) throws IOException {
        if (i >= 0 && i < nregs) {
            file.seek(i * tamañoreg);
            int idProducto = file.readInt();
            String nombre = "";
            for (int j = 0; j < 10; j++) {
                nombre = nombre + file.readChar();
            }
            int cantidad = file.readInt();
            double precio = file.readDouble();
            int codigoBarras = file.readInt();

            int year = file.readInt();
            int month = file.readInt();
            int day = file.readInt();
            Calendar entrada = Calendar.getInstance();
            entrada.set(year, month, day);;

            int year2 = file.readInt();
            int month2 = file.readInt();
            int day2 = file.readInt();
            Calendar caducidad = Calendar.getInstance();
            caducidad.set(year2, month2, day2);;

            String proveedor = "";
            for (int j = 0; j < 20; j++) {
                proveedor = proveedor + file.readChar();
            }

            int estado = file.readInt();

            return new Producto(idProducto,nombre, cantidad, precio, codigoBarras, entrada, caducidad, proveedor, estado);
        }
        System.out.println("Numero de registro fuera de limites");
        return null;
    }

    /**
     * Este metodo permite añadir nuevos vendedores al final del archivo
     *
     * @param producto
     * @throws IOException
     */
    public void añadirReg(Producto producto) throws IOException {
        if (escribirReg(nregs, producto)) {
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
        Producto producto;
        for (int i = 0; i < nregs; i++) {
            producto = leerReg(i);
            if (producto.getIdProducto() == id) {
                producto.setIdProducto(0);
                escribirReg(i, producto);
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
        Producto producto;
        int idProducto;
        if (id == 0) {//Fue eliminado
            return -1;
        }

        for (int i = 0; i < nregs; i++) {
            producto = leerReg(i);
            idProducto = producto.getIdProducto();
            if (id == idProducto) {

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
