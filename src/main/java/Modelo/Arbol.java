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
public abstract class Arbol {

    public Nodo raíz = null;

    private class Nodo {

        private Object datos;
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo() {
        }
    }

    public static final int CORRECTO = 000;
    public static final int NO_DATOS = 100;
    public static final int YA_EXISTE = 101;
    public static final int NO_EXISTE = 102;

    public Arbol() {
    }

    public abstract int comparar(int obj1, int obj2);

    public abstract void procesar(int[] i);

    public Object buscar(int obj) {
        Nodo actual = raíz;
        int nComp = 0;
        while (actual != null) {
            int[] comparar = (int [])actual.datos;
            if ((nComp = comparar(obj, comparar[0])) == 0) {
                return (actual.datos);
            } else if (nComp < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return null;
    }

    public int insertar(int[] obj) {
        System.out.println("error 0 AntesW2 "+obj[0]+" - "+obj[1]);
        Nodo último = null, actual = raíz;
        int nComp = 0;
        if (obj == null) {
            return NO_DATOS;
        }
 System.out.println("error 0 AntesW "+obj[0]+" - "+obj[1]);
        while (actual != null) {
            int []a=(int[]) actual.datos;
            if ((nComp = comparar(obj[0], a[0])) == 0) {
                break;
            } else {
                último = actual;
                if (nComp < 0) {
                    actual = actual.izquierdo;
                } else {
                    actual = actual.derecho;
                }
            }
        }
        System.out.println("error 0 Antes "+obj[0]+" - "+obj[1]);
        if (actual == null) {
            Nodo nuevoNodo = new Nodo();
            System.out.println("error 0 "+obj[0]+" - "+obj[1]);
            nuevoNodo.datos = obj;
            nuevoNodo.izquierdo = nuevoNodo.derecho = null;

            if (último == null) {
                raíz = nuevoNodo;
            } else if (nComp < 0) {
                último.izquierdo = nuevoNodo;
            } else {
                último.derecho = nuevoNodo;
            }
            return CORRECTO;
        } else {
            return YA_EXISTE;
        }
    }

    public Object borrar(int obj) 
    {

        Nodo ultimo = null, actual = raíz;
        Nodo marcado = null, sucesor = null;
        int nAnteriorComp = 0, nComp = 0;

        if (obj == 0) 
            return null;
        

        while (actual != null) 
        {
            nAnteriorComp = nComp;
            int a[]=(int[]) actual.datos;
            
            if ((nComp = comparar(obj, a[0])) == 0) 
                break;
             else 
            {
                ultimo = actual;
                if (nComp < 0) 
                    actual = actual.izquierdo;
                else 
                    actual = actual.derecho;
                
            }
        }
//
        if (actual != null) 
        {
            marcado = actual;
            if ((actual.izquierdo == null && actual.derecho == null)) 
                sucesor = null;
            else if (actual.izquierdo == null) 
                    sucesor = actual.derecho;
                 else if (actual.derecho == null) 
                        sucesor = actual.izquierdo;
                    else 
                 {
                        sucesor = actual = actual.derecho;
                        while (actual.izquierdo != null) 
                            actual = actual.izquierdo;
                        
                        marcado.datos = actual.datos;
                        if (actual.derecho == null) 
                            marcado.derecho = null;
                        
                        marcado = actual;
                        ultimo = sucesor;
                        sucesor = actual.derecho;
                    }
//
                    if (ultimo != null) 
                    {
                        if (nAnteriorComp < 0) 
                            ultimo.izquierdo = sucesor;
                        else 
                            ultimo.derecho = sucesor;
                        
                    } else 
                        raíz = sucesor;
                    
                    return marcado.datos;
                }
            
        else
            
        return null;
        
    }

    private void inorden(Nodo r, boolean nodoRaiz) 
    {
        System.out.println("EntroInicio");
        Nodo actual = null;
        if (nodoRaiz) {
            System.out.println("Entro1");
            actual = raíz;
        } else {
            System.out.println("Entro");
            actual = r;
        }
            if (actual != null)
            {

                inorden(actual.izquierdo, false);
                procesar((int[]) actual.datos);
                System.out.println("Entro");
                inorden(actual.derecho, false);
            }
        

    }

    public void inorden() {

        inorden(null, true);
    }
}
