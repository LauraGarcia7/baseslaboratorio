/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1bases;

import DAO.VendedorDAO;
import Modelo.ArbolBinrioFinal;
import VO.Vendedor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johan Sánchez
 */
public class Prueba2 {
    public static void main(String[] args) {
        try {
            ArbolBinrioFinal miA = new ArbolBinrioFinal();
            System.out.println("h");
            /*miA.insertar(1023);
            miA.insertar(10);
            miA.insertar(302);
            miA.inorden();*/
            
            Vendedor miV = new Vendedor(123, 1998, 1023969517, "JosepSanchezSanchez2", 14, "jose@gmail");
            Vendedor miV1 = new Vendedor(213, 2002, 1023969518, "DayanaSanchezSanchez", 15, "daya@gmail");
            Vendedor miV2 = new Vendedor(312, 2003, 1023969519, "JohanSanchezSanchez1", 14, "jowi@gmail");
            
            File archVen = new File("vendedores.txt");
            VendedorDAO miVD= new VendedorDAO(archVen);
          /*  if(!archVen.exists()){
            miVD.escribirReg(0, miV1);
            miVD.escribirReg(1, miV2);
            miVD.escribirReg(2, miV);
            System.out.println("Tamaño "+archVen.length());}else{
                Vendedor leido = miVD.leerReg(0);
                System.out.println("Nombre: "+leido.getNombre());
                System.out.println(leido.toString());
                
            }*/
         /* miVD.escribirReg(0, miV1);
            miVD.escribirReg(1, miV2);*/
        /* miVD.añadirReg(miV1);
         miVD.añadirReg(miV2);*/
        //miVD.añadirReg(miV);
            miVD.tamañoArchivo();
            System.out.println("Numero registros "+miVD.numeroDeRegs());
          Vendedor leido = miVD.leerReg(2);
                System.out.println("Nombre: "+leido.getNombre());
                System.out.println(leido.toString());
            System.out.println("Buscar el 123 "+miVD.buscarReg(123));     
                
                miVD.cerrarFichero();
        } catch (IOException ex) {
            Logger.getLogger(Prueba2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
