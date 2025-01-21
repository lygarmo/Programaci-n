package javaapplication13;

import java.util.Scanner;

public class TableroIA extends Tablero{

    public TableroIA() {
        super();
        super.esHumano=false;
        inicializaTableroIA();       
    }
    
    /*
    Lo he utilizado par aprobar y poder ver donde estan las posiciones de la IA
    public void mostrarTablero(){
        System.out.println("         TABLERO IA    ");
        System.out.println("   A  B  C  D  E  F  G  H  I  J  ");
           
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print(j);
                for (int x = 0; x < this.tablero.length; x++) {
                    System.out.print("  ");
                    System.out.print(this.tablero[j][x]);
                }
            System.out.println(" ");
            }
    }*/

    private void inicializaTableroIA() {
        this.tablero = inicializarTablero();
        
        insertarPortavion();
        
        insertarBuques();
        insertarFragatas();
        insertarVeleros();
        
    }
    
}
