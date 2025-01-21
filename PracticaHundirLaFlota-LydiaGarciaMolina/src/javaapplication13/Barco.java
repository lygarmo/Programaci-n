package javaapplication13;

import java.util.ArrayList;

public class Barco {
    protected ArrayList<Posicion> posiciones;
    protected ArrayList<Boolean> posicionesTocadas;
    
    /*
    -POSICION TOCADA
        te pasa posicion y la marcas como tocada
    - SABER SI BARCO HUNDIDO
        mira el array de posiciones y cuando todas esten tocadas dara un true de hundido
    */
    
    
    public boolean posicionTocada(int fila, int columna){
        //busca en que posicion de la lista está esta posicion
        int posicionLista=posiciones.indexOf(new Posicion(fila, columna));
        //poner true en la posicion que he obtenido
        if(posicionLista<0){
            //SI NO ESTA EN LA LISTA DEVOLVER FALSE
            return false;
        }
        posicionesTocadas.set(posicionLista, true);
        //cuando le pasas la posicin te devuelve si esta hundido
        return comprobarBarcoHundido();
    }
    
    public boolean comprobarBarcoHundido(){
        for(boolean tocado:posicionesTocadas){
            if(!tocado){
                return false;
            }
            
        }
        return true;
    }
    
     public ArrayList<Posicion> getPosiciones() {
        return posiciones;
    }

    public ArrayList<Boolean> getPosicionesTocadas() {
        return posicionesTocadas;
    }
}
