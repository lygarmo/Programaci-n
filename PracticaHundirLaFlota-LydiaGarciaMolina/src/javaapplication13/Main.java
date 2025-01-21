package javaapplication13;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static Scanner datos=new Scanner(System.in);

    public static void main(String[] args) {
      //UNIFICAR CODIG CUANDO ESTE HECHO

      /*
      creo las clases de cada tipo de barcos que heredan de la clase barco, la cual va a tener todos los metodos para que no este duplicado.
      Para cada tipo de barcos creo un ArryList el cual me guarda sus posiciones y otro ArrayList que guarda si esta posicion esta tocada con un true o un false.
      */
        
        char respuesta=' ';
        
        do{
            Juego juego = new Juego();
            juego.menu();
            juego.jugar();
            try{
                System.out.println("¿Quieres vovler a jugar? S/N");
                respuesta=datos.next().toUpperCase().charAt(0);

                if(respuesta!='N' && respuesta!='S'){
                    throw new RuntimeException();
                }                
            }catch(RuntimeException e){
                System.out.println("La respuesta que introduciste es incorrecta, introduce una valida");
            }
        }while(respuesta=='S');
    }
}
