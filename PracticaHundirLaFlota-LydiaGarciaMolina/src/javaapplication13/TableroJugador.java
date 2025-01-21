package javaapplication13;

import java.util.Scanner;

public class TableroJugador extends Tablero{
    public static Scanner datos=new Scanner(System.in);
    private char[][] disparos;
    
    public TableroJugador() {
        super();
        super.esHumano=true;
        inicializaTableroJugador();
    }
    
    public void insertarDisparo(int fila, int columna, boolean acierto){
        if(acierto){
            this.disparos[fila][columna]='X';
        }else{
            this.disparos[fila][columna]='O';
        }
    }
    
    public void mostrarTableros(){
        System.out.println("         TABLERO JUGADOR                      TABLERO DISPAROS");
        System.out.println("   A  B  C  D  E  F  G  H  I  J        A  B  C  D  E  F  G  H  I  J");
        for (int i = 0; i < this.disparos.length; i++) {
            System.out.print(i);
            for (int j = 0; j < 2; j++) {
                for (int x = 0; x < this.disparos.length; x++) {

                    if (j == 0) {
                        System.out.print("  ");
                        System.out.print(this.tablero[i][x]);
                    } else {
                        System.out.print("  ");
                        System.out.print(this.disparos[i][x]);
                    }
                }
                if(j==0){
                    System.out.print("     "+i);
                }
            }
            System.out.println(" ");
        }
    }
   
    private void inicializaTableroJugador() {
        this.tablero = inicializarTablero();
        this.disparos =new char[10][10];
        for(int i=0; i<this.disparos.length; i++){
            for(int x=0; x<this.disparos.length; x++){
                this.disparos[i][x]='*';
            }
        }
        mostrarTablero();
        
        System.out.println("PORTAVION X1");
        insertarPortavion();
        
        System.out.println("BUQUE X2");
        insertarBuques();

        System.out.println("FRAGATA X3");
        insertarFragatas();

        System.out.println("VELERO X4");
        insertarVeleros();
        mostrarTableros();
        
               
    }
    
}
