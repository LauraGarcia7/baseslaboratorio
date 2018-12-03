/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Johan Sánchez
 */
public class ArbolBinrioFinal extends Arbol {

    @Override
    public int comparar(int obj1, int obj2) {
        int id1 = (int) obj1;
        int id2 = (int) obj2;
        int res = 0;

        if (id1 < id2) {
            res = -1;
        } else if (id1 == id2) {
            res = 0;
        } else {
            res = 1;
        }
        return res;
    }

    @Override
    public void procesar(int [] obj) {
        System.out.println(obj.toString());
    }

}
