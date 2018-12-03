/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.CajaRegistradora;
import VO.ComputadorPersonal;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Johan Sánchez
 */
public class CajaDAO {
    private RandomAccessFile file;
    private int nregs = 0;
    private int tamañoreg = 28;// 4(entero),4,4,8(Marca de cuatro caracteres),8
    private boolean regsEliminados = false;
    private ArrayList<CajaRegistradora> lista;
/**
 * Constructor de la clase que abre el archivo de las cajas registradoras
 * @param fichero
 * @throws IOException 
 */
    public CajaDAO(File fichero) throws IOException {
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
 * Metodo que escribe en el archivo de cajas a partir de un indice
 * @param i indice
 * @param caja objeto que se desea guardar
 * @return Si fue exitoso en el momento que se guardo un nueva caja
 * @throws IOException 
 */
    public boolean escribirReg(int i, CajaRegistradora caja) throws IOException {
        if (i >= 0 && i <= nregs) 
        {
            file.seek(i * tamañoreg);
            // public Vendedor(int idVendedor, int id, String nombre, int telefono, String correo) {
            file.writeInt(caja.getIdCaja());
            file.writeInt(caja.getIdVendedor());
            ComputadorPersonal cP = caja.getcP();
            file.writeInt(cP.getIdCompu());
            String marca= cP.getMarca();
            
            for (int j = 0; j < 4; j++) {
                
                file.writeChar(marca.charAt(j));
            }
            file.writeDouble(caja.getValor());
                        return true;
        }
        return false;
    }
/**
 * Metodo que apartir de un indice oermite devolver una caja en esa posición del archivo
 * @param i indice
 * @return El objeto caja
 * @throws IOException 
 */
    public CajaRegistradora leerReg(int i) throws IOException {
        if (i >= 0 && i < nregs) {
            file.seek(i * tamañoreg);
            int idCaja = file.readInt();
            int idVendedor = file.readInt();
            int idCompu = file.readInt();
            String marca = "";
            for (int j = 0; j < 4; j++) {
                marca = marca + file.readChar();
            }
ComputadorPersonal cP =  new  ComputadorPersonal(idCompu, marca);
double valor = file.readDouble();
            return new CajaRegistradora(idCaja,idVendedor, cP,valor);
        }
        System.out.println("Numero de registro fuera de limites");
        return null;
    }
/**
 * Este metodo permite añadir nuevas cajas al final del archivo
 * @param caja
 * @throws IOException 
 */
    public void añadirReg(CajaRegistradora caja) throws IOException {
        if (escribirReg(nregs, caja)) {
            nregs++;
        }
    }
/**
 * Este metodo como tal no borra una caja del achivo, para este programa las cajas con idCaja=0 no existen
 * es decir este metodo borra los cajas poniendo sus idCaja en 0
 * @param id identificador unico de las cajas
 * @return fue exitosa o no la eliminación
 * @throws IOException 
 */
    public boolean eliminarReg(int id) throws IOException {
        CajaRegistradora caja;
        for (int i = 0; i < nregs; i++) {
            caja = leerReg(i);
            if (caja.getIdCaja()== id) {
                caja.setIdCaja(0);
                escribirReg(i, caja);
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
 * El metodo buscar, permite saber el indice al que pertenece la caja en el archivo aleatorio a partir de su id
 * @param id es la variable que pertenece a la caja como bien del supermercado (En la clase se conoce como idCaja)
 * @return el indice que pertenece, en el archivo, el vendedorv ó -1 que indica que no esta.
 * @throws IOException 
 */
    public int buscarReg(int id) throws IOException {
        CajaRegistradora caja;
        int idCaja;
        if (id == 0) {//Fue eliminado
            return -1;
        }
       
        for (int i = 0; i < nregs; i++) {
            caja = leerReg(i);
            idCaja = caja.getIdCaja();
            if (id == idCaja) {
                
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
