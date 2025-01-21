package javaapplication13;

import java.util.ArrayList;

public class Buque extends Barco{
   
    public Buque(int fila, int columna, char orientacion) {
        posiciones = new ArrayList<>(3);
        posicionesTocadas = new ArrayList<>(3);
        if(orientacion=='H'){
            Posicion p=new Posicion(fila, columna);
            posiciones.add(p);
            posicionesTocadas.add(false);
            Posicion p2=new Posicion(fila, columna+1);
            posiciones.add(p2);
            posicionesTocadas.add(false);
            Posicion p3=new Posicion(fila, columna+2);
            posiciones.add(p3);
            posicionesTocadas.add(false);
        }else{
            Posicion p=new Posicion(fila, columna);
            posiciones.add(p);
            posicionesTocadas.add(false);    
            Posicion p2=new Posicion(fila+1, columna);
            posiciones.add(p2);
            posicionesTocadas.add(false);
            Posicion p3=new Posicion(fila+2, columna);
            posiciones.add(p3);
            posicionesTocadas.add(false);
    
        }
    }
    
    
}
