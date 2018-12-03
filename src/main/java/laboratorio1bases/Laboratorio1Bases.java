/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1bases;

import VO.Administrador;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Johan Sánchez
 */
public class Laboratorio1Bases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Administrador a = new Administrador(12,1992, 10, "J", 20, "johan222");
        
       Date fecha = new Date();
        System.out.println(fecha.toString());
        String  s =fecha.toString();
        System.out.println(s);
        char [ ]c=s.toCharArray();
        System.out.println(c.length);
        
        Date vence = new Date(0, 0, 0);
        Calendar hoy = Calendar.getInstance();
        System.out.println(hoy.getTime());
        //Calendar  vence = new GregorianCalendar(2020, 6, 4);
        Calendar sameDate = new GregorianCalendar(2010, Calendar.FEBRUARY, 22, 23, 11, 44);
System.out.println("Some Date : " + sameDate.getTime());
        
               Calendar Date = Calendar.getInstance();
        System.out.println(Date.getTime());
Date.set(Calendar.YEAR, 2080);
        System.out.println(Date.getTime());
        Date.set(1998, 6, 7);
        System.out.println(Date.getTime());
        System.out.println("El mes es: "+ Date.get(Calendar.MONTH));
        int year = Date.get(Calendar.YEAR);
        int month = Date.get(Calendar.MONTH);
        int day = Date.get(Calendar.DAY_OF_MONTH);
        System.out.println(year+" "+month+" "+day);
    }
}
