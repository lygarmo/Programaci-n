package javaapplication13;

import java.util.Scanner;

public class Juego {
    private TableroIA tableroIA;
    private TableroJugador tableroJugador;

    public Juego() {
        tableroIA=new TableroIA();
        tableroJugador=new TableroJugador();
    }
    
    public static Scanner datos=new Scanner(System.in);

    public void menu(){
        System.out.println("Bienvenido al juego de Hundir la flota  :)");
        System.out.println("Tu oponente es la IA ");
        System.out.println("Normas del juego: \n"
                + "     - Los barcos no pueden estar en las posiciones N, S, O y E de otro barcos, pero no te preocupes nuestro programa se encargará de avisarte\n"
                + "     - Si introduces un barco en una posicion en la cual ya se haya insertado un barco se volverá a pedir que introduzcas el barco de nuevo\n"
                + "     - Tu tablero es el de la izquierda (verás como la IA dispara en tu tablero) y el tablero de la derecha es donde tus disparos caen en el tablero de la IA\n"
                +"      - X: tocado     O: agua"
                + "\nAhora es hora de jugar !!\n");
    }
    
    private boolean juegoHumano(){
        int fila=0;
        int columna=0;
        boolean acierto=false;
        boolean coordenadaUtilizada=false;
        do{
            System.out.println("Introduce el disparo");
            fila=tableroJugador.introducirFila();
            columna=tableroJugador.introducirColumna();
            try{
                acierto= tableroIA.disparo(fila, columna);
                coordenadaUtilizada=false;
            }catch(RuntimeException e){
                System.out.println("\nYa has disparado en esta posicion, introduce otra distinta\n");
                coordenadaUtilizada=true;
            }
        }while(coordenadaUtilizada);
        
        tableroJugador.insertarDisparo(fila, columna, acierto);
        tableroJugador.mostrarTableros();
        return acierto;
    }
    
    private boolean juegoIA(){
        int fila=0;
        int columna=0;
        boolean acierto=false;
        boolean coordenadaUtilizada=false;
        do{
            fila=tableroIA.introducirFila();
            columna=tableroIA.introducirColumna();
            try{
                acierto=tableroJugador.disparo(fila, columna);
                coordenadaUtilizada=false;
            }catch(RuntimeException e){
                coordenadaUtilizada=true;
            }
        }while(coordenadaUtilizada);
        
        tableroJugador.mostrarTableros();
        return acierto;
    }
    
    public void jugar(){
        int contTurnos=1;
        int contHumano=0;
        int contIA=0;
        boolean acierto=false;
        
        do{
            if(contTurnos%2!=0){
                System.out.println("\nTurno humano:");
                acierto=juegoHumano();
                contHumano=tableroIA.getContHundidos();
                System.out.println("Barcos hundidos por el humano: "+contHumano);
            }else{
                System.out.println("\nTurno IA:");
                acierto=juegoIA();
                contIA=tableroJugador.getContHundidos();
                System.out.println("Barcos hundidos por la AI: "+contIA);
            }
            if(!acierto){
                contTurnos++;
            }
        }while(contHumano<10 && contIA<10);
        
        if(contHumano==10){
            System.out.println("\nHAS GANADO!!");
        }else{
            System.out.println("\nPerdiste contra la IA :(");
        }
    }

    
    
}
