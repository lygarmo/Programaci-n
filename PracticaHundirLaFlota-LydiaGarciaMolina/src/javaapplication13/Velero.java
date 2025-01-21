package javaapplication13;

import java.util.ArrayList;

public class Velero extends Barco{

    public Velero(int fila, int columna) {
        posiciones = new ArrayList<>(1);
        posicionesTocadas = new ArrayList<>(1);
        Posicion p=new Posicion(fila, columna);
        posiciones.add(p);
        posicionesTocadas.add(false);
    }
    


    
    
    
    
}
