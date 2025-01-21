package javaapplication13;

import java.util.ArrayList;

public class Fragata extends Barco{
    

    public Fragata(int fila, int columna, char orientacion) {
        posiciones = new ArrayList<>(2);
        posicionesTocadas = new ArrayList<>(2);
        if(orientacion=='H'){
            Posicion p=new Posicion(fila, columna);
            posiciones.add(p);
            posicionesTocadas.add(false);
            Posicion p2=new Posicion(fila, columna+1);
            posiciones.add(p2);
            posicionesTocadas.add(false);
        }else{
            Posicion p=new Posicion(fila, columna);
            posiciones.add(p);
            posicionesTocadas.add(false);    
            Posicion p2=new Posicion(fila+1, columna);
            posiciones.add(p2);
            posicionesTocadas.add(false);
    
        }
    }

    
    
    
   
}
