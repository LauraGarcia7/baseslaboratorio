/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import DAO.AdministradorDAO;
import DAO.CajaDAO;
import DAO.VendedorDAO;
import VO.Administrador;
import VO.CajaRegistradora;
import VO.ComputadorPersonal;
import VO.Vendedor;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Johan Sánchez
 */
public class UsuariosPredeterminados {
    public static void main(String[] args) throws IOException {
        File fichero = new File("administradores.txt");
        AdministradorDAO adm = new AdministradorDAO(fichero);
        Administrador a1=new Administrador(101, 1998, 1023969517, "johansanchezsanchez2", 20, "jow@correo");
        adm.añadirReg(a1);
        
        File f2 = new File("vendedores.txt");
        VendedorDAO ven = new VendedorDAO(f2);
        Vendedor v1 = new Vendedor(201, 1111, 1023969501, "dayanasanchezsanchez", 30, "day@correo");
        Vendedor v2 = new Vendedor(202, 2222, 1023969502, "josepsanchezsanchez1", 32, "jos@correo");
        Vendedor v3 = new Vendedor(203, 3333, 1023969503, "nicosanchezsanchez33", 40, "nik@correo");
        Vendedor v4 = new Vendedor(204, 4444, 1023969504, "marshsanchezsanchez4", 31, "mah@correo");
        Vendedor v5 = new Vendedor(205, 5555, 1023969505, "sulaysanchezsanchez5", 30, "sul@correo");
        Vendedor v6 = new Vendedor(206, 6666, 1023969506, "lalasanchezsanchez66", 38, "vic@correo");
        ven.añadirReg(v1);
        ven.añadirReg(v2);
        ven.añadirReg(v3);
        ven.añadirReg(v4);
        ven.añadirReg(v5);
        ven.añadirReg(v6);
        
        File f3 = new File("cajas.txt");
        CajaDAO caja = new CajaDAO(f3);
        ComputadorPersonal cP  = new ComputadorPersonal(1, "dell");
        ComputadorPersonal cP2 = new ComputadorPersonal(2, "dell");
        ComputadorPersonal cP3 = new ComputadorPersonal(3, "dell");
        ComputadorPersonal cP4 = new ComputadorPersonal(4, "dell");
        ComputadorPersonal cP5 = new ComputadorPersonal(5, "dell");
        ComputadorPersonal cP6 = new ComputadorPersonal(6, "dell");
        CajaRegistradora c1 = new CajaRegistradora(11, 201, cP , 500000);
        CajaRegistradora c2 = new CajaRegistradora(22, 202, cP2, 500000);
        CajaRegistradora c3 = new CajaRegistradora(33, 203, cP3, 500000);
        CajaRegistradora c4 = new CajaRegistradora(44, 204, cP4, 500000);
        CajaRegistradora c5 = new CajaRegistradora(55, 205, cP5, 500000);
        CajaRegistradora c6 = new CajaRegistradora(66, 206, cP6, 500000);
        caja.añadirReg(c1);
        caja.añadirReg(c2);
        caja.añadirReg(c3);
        caja.añadirReg(c4);
        caja.añadirReg(c5);
        caja.añadirReg(c6);
    }
}
