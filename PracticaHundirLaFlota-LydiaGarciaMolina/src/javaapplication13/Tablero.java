package javaapplication13;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication13.TableroJugador.datos;

public class Tablero {
    private int contHundidos;
    
    protected char [][]tablero;
    protected char [][]tableroDisparos;
    
    protected boolean esHumano=false;
    
    protected ArrayList<Velero> veleros;
    protected ArrayList<Fragata> fragatas;
    protected ArrayList<Buque> buques;
    protected Portavion portavion;

    public int getContHundidos() {
        return contHundidos;
    }

   
    
    //el portavion se inicializa al dar la posicion
    protected char[][] inicializarTablero() {
        this.tablero = new char [10][10];
        //cuantos barcos hay de cada tipo
        veleros = new ArrayList<>(4);
        fragatas = new ArrayList<>(3);
        buques = new ArrayList<>(2);
        
        for(int i=0; i<this.tablero.length; i++){
            for(int x=0; x<this.tablero.length; x++){
                tablero[i][x]='*';
            }
        }
        return this.tablero;
    } 
    
    protected void insertarVeleros() {
        int filaVelero;
        int c;
        int cont = 1;
        do {
            boolean comprobarVelero = false;
            do {
                try {
                    if (esHumano) {
                        System.out.println("Introduce la posicion para los Veleros");
                    }
                    filaVelero = introducirFila();
                    c = introducirColumna();

                    if (this.tablero[filaVelero][c] == '*') {
                        if (comprobarDisponibilidadAlrededoresVelero(filaVelero, c)) {
                            meterVelero(filaVelero, c);
                            cont++;
                            comprobarVelero = true;
                            Velero velero=new Velero(filaVelero, c);
                            veleros.add(velero);
                        } else {
                            throw new RuntimeException();
                        }
                    }else{
                        if(esHumano){
                            System.out.println("\nPosicion ocupada");
                        }
                    }
                } catch (RuntimeException e) {
                    comprobarVelero = false;
                    if (esHumano) {
                        System.out.println("\nNo hay disponiblidad en los alrededores");
                    }
                }
                if(esHumano){
                    mostrarTablero();
                }
            } while (comprobarVelero == false);
        } while (cont <= 4);
    }
    
   
    
    protected void insertarFragatas() {
        int cont = 1;

        do {
            boolean comprobarFragata = false;
            do {
                try {
                    if(esHumano){
                        System.out.println("\nFRAGATA Nº" + cont);
                    }
                    char respuesta=introducirVerticalHorizontal();
                    int filaFragata = introducirFila();
                    int c = introducirColumna();
                    
                    
                    if (respuesta == 'V') {
                        if(filaFragata>8){
                            throw new Exception();
                        }
                        if (this.tablero[filaFragata][c] == '*') {
                            if (comprobarDisponibilidadAlrededoresFragataVertical(filaFragata, c) == true) {
                                meterFragataVertical(filaFragata, c);
                                cont++;
                                comprobarFragata = true;
                            } else {
                                if(esHumano){
                                    System.out.println("\nNo hay disponiblidad en los alrededores"); 
                                }
                                throw new RuntimeException();
                            }
                        } else {
                            if(esHumano){
                                System.out.println("\nPosicion ocupada");
                            }
                            throw new RuntimeException();
                        }
                    } else if (respuesta == 'H') {
                        if(c>7){
                            throw new Exception();
                        }
                        if (this.tablero[filaFragata][c] == '*') {
                            if (comprobarDisponibilidadAlrededoresFragataHorizontal(filaFragata, c) == true) {
                                meterFragataHorizontal(filaFragata, c);
                                comprobarFragata = true;
                                cont++;
                            }
                        } else {
                            if(esHumano){
                                System.out.println("\nPosicion ocupada");
                            }
                            throw new RuntimeException();
                        }
                    } else {
                        if(esHumano){
                            System.out.println("\nLa respuesta es incorrecta ");
                        }
                    }
                }catch (RuntimeException e) {
                    comprobarFragata = false;
                }catch (Exception e) {
                    if(esHumano){
                        System.out.println("El barco excede los limites del tablero");
                    }
                }
                if(esHumano){
                    mostrarTablero();
                }
            } while (comprobarFragata == false);
        } while (cont <= 3);
    }

    protected void insertarBuques(){
        int cont=1;
        do{
            boolean comprobarBuque=false;
            
            char respuesta=' ';
            
            do{
                try{
                    if(esHumano){
                        System.out.println("\nBUQUE "+cont);
                    }
                    respuesta=introducirVerticalHorizontal();
                    int filaBuque = introducirFila();
                    int c = introducirColumna();

                    if(respuesta=='V'){
                        if(filaBuque>7){
                            throw new Exception();
                        }
                        if(this.tablero[filaBuque][c]=='*'){
                            if(comprobarDisponibilidadAlrededoresBuqueVertical(filaBuque, c)){
                                meterBuqueVertical( filaBuque, c);
                                cont++;
                                comprobarBuque=true;
                            }else{
                                if(esHumano){
                                    System.out.println("\nNo hay disponiblidad en los alrededores"); 
                                }
                                throw new RuntimeException();
                            }
                        }else{
                            if(esHumano){
                                System.out.println("\nPosicion ocupada");
                            }
                            throw new RuntimeException();
                        }
                    }else if(respuesta=='H'){
                        if(c>7){
                            throw new Exception();
                        }
                        if(this.tablero[filaBuque][c]=='*'){
                            if(comprobarDisponibilidadAlrededoresBuqueHorizontal(filaBuque, c)){
                                meterBuqueHorizontal( filaBuque,c);
                                cont++;
                                comprobarBuque=true;
                            }else{
                               if(esHumano){
                                    System.out.println("\nNo hay disponiblidad en los alrededores"); 
                                }
                                throw new RuntimeException();
                            }
                        }else{
                            if(esHumano){
                                System.out.println("\nPosicion ocupada");
                            }
                            throw new RuntimeException();
                        }
                    }else{
                        if(esHumano){
                            System.out.println("La respuesta es incorrecta ");              
                        }
                    }
                }catch(RuntimeException e){
                    comprobarBuque=false;
                } catch (Exception e) {
                    if(esHumano){
                        System.out.println("El barco excede los limites del tablero");
                    }
                }
                if(esHumano){
                    mostrarTablero();
                }
            }while(comprobarBuque==false);
        }while(cont<=2);
    }
    
    protected void insertarPortavion(){
        char respuesta=introducirVerticalHorizontal();
        boolean comprobarPortavion=false;
        
        do{
            try{
                if(esHumano){
                    System.out.println("Portavion ");
                }
                int filaPortavion = introducirFila();
                int c = introducirColumna();

                if(respuesta=='V'){
                    if(this.tablero[filaPortavion][c]=='*'){
                        if(comprobarDisponibilidadAlrededoresPortavionVertical(filaPortavion, c)){
                            meterPortavionVertical( filaPortavion, c);
                            comprobarPortavion=true;
                        }else{
                            if(esHumano){
                                System.out.println("\nNo hay disponiblidad en los alrededores");    
                            }
                            throw new RuntimeException();
                        }
                    }else{
                        if(esHumano){
                            System.out.println("Posicion ocupada");
                        }
                        throw new RuntimeException();
                    }
                }else if(respuesta=='H'){
                    if(this.tablero[filaPortavion][c]=='*'){
                        if(comprobarDisponibilidadAlrededoresPortavionHorizontal(filaPortavion, c)){
                            meterPortavionHorizontal( filaPortavion, c);
                            comprobarPortavion=true;
                        }else{
                            if(esHumano){
                                System.out.println("\nNo hay disponiblidad en los alrededores"); 
                            }
                            throw new RuntimeException();
                        }
                    }else{
                        comprobarPortavion=false;
                        if(esHumano){
                            System.out.println("\nPosicion ocupada");
                        }
                        throw new RuntimeException();
                    }
                }else{
                    if(esHumano){
                        System.out.println("\nLa respuesta es incorrecta ");
                    }
                }
            }catch(RuntimeException e){
                comprobarPortavion=false;    
                if(esHumano){
                    System.out.println("El barco excede los limites del tablero");
                }
            }
            if(esHumano){
                mostrarTablero();
            }
        }while(comprobarPortavion==false);
    }
    
     public void mostrarTablero(){
        System.out.println("   A  B  C  D  E  F  G  H  I  J  ");
           
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print(j);
                for (int x = 0; x < this.tablero.length; x++) {
                    System.out.print("  ");
                    System.out.print(this.tablero[j][x]);
                }
            System.out.println(" ");
            }
    }
    
    
    protected int introducirColumna() {
        if(esHumano==true){
            while(true){
                try{
                    System.out.println("Columna");
                    char columna = datos.next().charAt(0);
                    columna = Character.toUpperCase(columna);
                    if(comprobarLetra(columna)){
                        return asignarNumeroALetra(columna);
                    }else{
                        throw new RuntimeException();
                    }
                }catch(RuntimeException e){
                    System.out.println("Introduce de nuevo una columna");
                }
            }
        }else{
            int numeroAleatorio = (int) (Math.random()*10);
            return numeroAleatorio;
        }
        
    }
     
    private char introducirVerticalHorizontal() {
        char respuesta = 0;
        if(esHumano==true){
            while(true){
                try{
                    System.out.println("Como quieres introducir el barco vertical : V  u  horizontal : H");
                    respuesta=datos.next().charAt(0);
                    respuesta = Character.toUpperCase(respuesta);
                    if(respuesta=='V' || respuesta=='H'){
                        return respuesta;
                    }else{
                        throw new RuntimeException();
                    }
                }catch(RuntimeException e){
                    System.out.println("Letra no valida");
                }
            }
        }else{
            boolean aleatorio = new Random().nextBoolean();
            if(aleatorio){
                respuesta='V';
            }else {
                respuesta='H';
            }
            return respuesta;
        }
        
    }

    
    protected int introducirFila() {
        if(esHumano==true){
            do{
                try{
                    System.out.println("Fila");
                    int fila = datos.nextInt();
                    if(fila>=0 && fila<=9){
                        return fila;
                    }else{
                        System.out.println("Inserta una fila valida");
                    }
                }catch(InputMismatchException e){
                    System.out.println("Por favor, introduce un número válido");
                    datos.nextLine();
                }
            }while(true);
        } else{
            int numeroAleatorio = (int) (Math.random()*9);
            return numeroAleatorio;
        }
    }
    
    protected boolean comprobarLetra(char columna) {
        if( columna=='A'|| columna=='B' || columna=='C'
                || columna=='D' ||columna == 'E' || columna=='F'
                || columna=='G' || columna=='H' || columna== 'I' || columna=='J'){
            return true;
        }else{
            return false;
        }
    }
    
    protected int asignarNumeroALetra(char columna) {
        int c=0;
        c = switch (columna) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            case 'E' -> 4;
            case 'F' -> 5;
            case 'G' -> 6;
            case 'H' -> 7;
            case 'I' -> 8;
            default -> 9;
        };
        return c;
    }   
    
    private void meterVelero(int fila, int c) {
        char velero='V';
        this.tablero[fila][c]=velero;
        Velero v=new Velero(fila,c);
        veleros.add(v);
    }
    
    private void meterFragataVertical(int fila, int c) {
        char fragata='F';
        this.tablero[fila][c]=fragata;
        this.tablero[fila+1][c] = fragata;
        Fragata f=new Fragata(fila,c,'V');
        fragatas.add(f);
    }
    
    private void meterFragataHorizontal(int fila, int c) {
        char fragata='F';
        this.tablero[fila][c]=fragata;
        this.tablero[fila][c+1] = fragata;
        Fragata f=new Fragata(fila,c,'H');
        fragatas.add(f);
    }
    
    private void meterBuqueVertical(int fila, int c) {
        char buque='B';
        this.tablero[fila][c]=buque;
        this.tablero[fila+1][c] = buque;
        this.tablero[fila+2][c] = buque;
        Buque b=new Buque(fila,c,'V');
        buques.add(b);
    }
    
    private void meterBuqueHorizontal(int fila, int c) {
        char buque='B';
        this.tablero[fila][c]=buque;
        this.tablero[fila][c+1] = buque;
        this.tablero[fila][c+2] = buque;
        Buque b=new Buque(fila,c,'H');
        buques.add(b);
    }
    
    private void meterPortavionVertical(int fila, int c) {
        char portavion='P';
        this.tablero[fila][c]=portavion;
        this.tablero[fila+1][c] = portavion;
        this.tablero[fila+2][c] = portavion;
        this.tablero[fila+3][c] = portavion;
        this.portavion=new Portavion(fila, c, 'V');
        
    }
    
    private void meterPortavionHorizontal(int fila, int c) {
        char portavion='P';
        this.tablero[fila][c]=portavion;
        this.tablero[fila][c+1] = portavion;
        this.tablero[fila][c+2] = portavion;
        this.tablero[fila][c+3] = portavion;
        this.portavion=new Portavion(fila, c, 'H');

    }
    

    private boolean comprobarDisponibilidadAlrededoresVelero(int fila, int c) {       
        if(fila==0 && c==0 && 
                this.tablero[fila+1][c]=='*'/*Sur*/&&
                this.tablero[fila][c+1]=='*'/*Este*/  ){
            return true;
        }else if(fila==0 && c==9 &&
                this.tablero[fila+1][c]=='*'/*Sur*/ &&
                this.tablero[fila][c-1]=='*'/*Oeste*/){
            return true;
        }else if(fila==9 && c==0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/ &&
                this.tablero[fila][c+1]=='*'/*Este*/){
            return true;
        }else if(fila==9 && c==9 &&
                this.tablero[fila-1][c]=='*'/*Norte*/ &&
                this.tablero[fila][c-1]=='*'/*Oeste*/){
            return true;    
        }       
        else if(fila==0 && c!=0 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/&&
                this.tablero[fila][c+1]=='*'/*Este*/ &&
                this.tablero[fila+1][c]=='*'/*Sur*/){
            return true;
        }else if(fila==9 && c!=0 && 
                this.tablero[fila-1][c]=='*'/*Norte*/&&
                this.tablero[fila][c-1]=='*'/*Oeste*/&&
                this.tablero[fila][c+1]=='*'/*Este*/){
            return true;
        }else if(c==0 && fila!=0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/&&
                this.tablero[fila+1][c]=='*'/*Sur*/ &&
                this.tablero[fila][c+1]=='*'/*Este*/){
            return true;
        }else if(c==9 && fila!=0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/&&
                this.tablero[fila+1][c]=='*'/*Sur*/ &&
                this.tablero[fila][c-1]=='*'/*Oeste*/){
            return true;
        }else if(fila!=0 && c!=0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/ &&
                this.tablero[fila][c+1]=='*'/*Este*/ &&
                this.tablero[fila+1][c]=='*'/*Sur*/ &&
                this.tablero[fila][c-1]=='*'/*Oeste*/){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarDisponibilidadAlrededoresFragataHorizontal(int fila, int c) {
        if(fila==0 && c==0 &&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*' &&
                this.tablero[fila][c+2]=='*'/*Este*/){
            return true;
        }else if(fila==0 && c==8 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/ &&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(fila==9 && c==0 && 
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*' &&
                this.tablero[fila][c+2]=='*'/*Este*/){
            return true;
        }else if(fila==9 && c==8 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/ &&
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*'){
            return true;
        }else if(fila==0 && c!=0 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/&&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*' &&
                this.tablero[fila][c+2]=='*'/*Este*/){
            return true;
        }else if(fila==9 && c!=0 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/ &&
                this.tablero[fila][c+2]=='*'/*Este*/ &&
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*'){
            return true;
        }else if(c==0 && fila!=0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*' &&
                this.tablero[fila][c+2]=='*'/*Este*/ &&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(c==8 && fila!=0 &&
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*' &&
                this.tablero[fila][c-1]=='*'/*Oeste*/&&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                this.tablero[fila][c-1]=='*'/*Oeste*/&&
                this.tablero[fila][c+2]=='*'/*Este*/ &&
                this.tablero[fila-1][c]=='*'/*Norte*/&& this.tablero[fila-1][c+1]=='*' &&
                this.tablero[fila+1][c]=='*'/*Sur*/ && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarDisponibilidadAlrededoresFragataVertical(int fila, int c) {
        if(fila==0 && c==0 &&
                /*Este*/this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' &&
                /*Sur*/this.tablero[fila+2][c]=='*'){
            return true;
        }else if(c==0 && fila==8 &&
                /*Norte*/ this.tablero[fila-1][c]=='*' &&
                /*Este*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(fila==0 && c==9 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' &&
                /*Sur*/this.tablero[fila+2][c]=='*'){
            return true;
        }else if(fila==8 && c==9 &&
                /*Norte*/ this.tablero[fila-1][c]=='*' &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*'){
            return true;
        }else if(fila==0 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' &&
                /*Sur*/this.tablero[fila+2][c]=='*' &&
                /*Este*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(fila==8 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' &&
                /*Norte*/ this.tablero[fila-1][c]=='*' && 
                /*Este*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(c==0 && fila!=0 &&
                /*Norte*/ this.tablero[fila-1][c]=='*' && 
                /*Sur*/this.tablero[fila+2][c]=='*' &&
                /*Este*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*'){
            return true;
        }else if(c==9 && fila!=0 &&
                /*Norte*/ this.tablero[fila-1][c]=='*' &&
                /*Sur*/this.tablero[fila+2][c]=='*' &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' &&
                /*Este*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' &&
                /*Norte*/ this.tablero[fila-1][c]=='*'&& 
                /*Sur*/this.tablero[fila+2][c]=='*'){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarDisponibilidadAlrededoresBuqueHorizontal(int fila, int c) {
        if(fila==0 && c==0 &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*'){
            return true;
        }else if(fila==0 && c==7 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*'){
            return true;
        }else if(fila==9 && c==0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*'){
            return true;
        }else if(fila==9 && c==7 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
               /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*'){
            return true;
        }else if( fila==0 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*' ){
            return true;
        }else if(fila==9 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*'){
            return true;
        }else if(c==0 && fila!=0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*' &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*' ){
            return true;
        }else if(c==7 && fila!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*' &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                /*Oeste*/this.tablero[fila][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+3]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' && this.tablero[fila-1][c+2]=='*' &&
                /*Sur*/this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+1][c+2]=='*'){
            return true;
        }else{
            return false;
        }
    }
    private boolean comprobarDisponibilidadAlrededoresBuqueVertical(int fila, int c) {
        if(fila==0 && c==0 &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*'){
            return true;
        }else if(fila==7 && c==0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*'){
            return true;
        }else if(fila==0 && c==9 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*'){
            return true;
        }else if(fila==7 && c==9 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*'){
            return true;
        }else if(fila==0 && c!=0 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*'){
            return true;
        }else if(fila==7 && c!=0 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*'){
            return true;
        }else if(c==0 && fila!=0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*'){
            return true;
        }else if(c==9 && fila!=0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*SUR*/ this.tablero[fila+3][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*'){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarDisponibilidadAlrededoresPortavionVertical(int fila, int c) {
         if(fila==0 && c==0 &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*' &&
                /*SUR*/ this.tablero[fila+4][c]=='*' ){
            return true;
        }else if(fila==0 && c==9 &&
               /*SUR*/ this.tablero[fila+4][c]=='*' &&
               /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*'){
            return true;
        }else if(fila==6 && c==0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*'){
            return true;
        }else if(fila!=0 && c==0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*SUR*/this.tablero[fila+4][c]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*'){
            return true;
        }else if(fila!=0 && c==9 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*SUR*/ this.tablero[fila+4][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*'){
            return true;
        }else if(fila==0 && c!=0 &&
                /*SUR*/ this.tablero[fila+4][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*'){
            return true;
        }else if(fila==6 && c!=0 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' ){
            return true;
        }else if(fila==6 && c==9 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' &&
                /*SUR*/ this.tablero[fila+4][c]=='*' &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' && this.tablero[fila+1][c-1]=='*' && this.tablero[fila+2][c-1]=='*' && this.tablero[fila+3][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+1]=='*' && this.tablero[fila+1][c+1]=='*' && this.tablero[fila+2][c+1]=='*' && this.tablero[fila+3][c+1]=='*'){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarDisponibilidadAlrededoresPortavionHorizontal(int fila, int c) {
        if(fila==0 && c==0 &&
                /*SUR*/ this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*' &&
                /*ESTE*/ this.tablero[fila][c+4]=='*'){
            return true;
        }else if(fila==9 && c==0 &&
                /*ESTE*/ this.tablero[fila][c+5]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*'){
            return true;
        }else if(fila==0 && c==6 &&
                /*SUR*/ this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*' && 
                /*OESTE*/ this.tablero[fila][c-1]=='*' ){
            return true;
        }else if(fila==9 && c==6 &&
                /*OESTE*/this.tablero[fila][c-1]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*'){
            return true;
        }else if(fila==0 && c!=0 &&
                /*SUR*/ this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*' && 
                /*OESTE*/ this.tablero[fila][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+4]=='*' ){
            return true;
        }else if(fila==9 && c!=0 &&
                /*OESTE*/ this.tablero[fila][c-1]=='*' &&
                /*ESTE*/ this.tablero[fila][c+4]=='*' &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*'){
            return true;
        }else if(fila!=0 && c==6 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*' &&
                /*SUR*/ this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*' && 
                /*OESTE*/ this.tablero[fila][c-1]=='*'){
            return true;
        }else if(fila!=0 && c==0 &&
                /*NORTE*/ this.tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*' &&
                /*SUR*/ this.tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*' &&
                /*ESTE*/ this.tablero[fila][c+4]=='*'){
            return true;
        }else if(fila!=0 && c!=0 &&
                /*NORTE*/ tablero[fila-1][c]=='*' && this.tablero[fila-1][c+1]=='*' &&  this.tablero[fila-1][c+2]=='*' && this.tablero[fila-1][c+3]=='*' &&
                /*SUR*/ tablero[fila+1][c]=='*' && this.tablero[fila+1][c+1]=='*' &&  this.tablero[fila+1][c+2]=='*' && this.tablero[fila+1][c+3]=='*'&& 
                /*OESTE*/ tablero[fila][c-1]=='*' &&
                /*ESTE*/ tablero[fila][c+4]=='*' ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean disparo(int fila, int columna) {
        boolean acierto = false;
        char simbolo = this.tablero[fila][columna];
        boolean posicionNoOcupada = false;
        do {

            if (simbolo == 'O' || simbolo == 'X') {
                posicionNoOcupada = false;
                throw new RuntimeException();
            } else if (simbolo == 'V') {
                System.out.println("Tocado");
                this.tablero[fila][columna] = 'X';
                acierto = true;
                posicionNoOcupada = true;
                for (Velero velero : veleros) {
                    boolean hundido = velero.posicionTocada(fila, columna);
                    if (hundido) {
                        System.out.println("Hundido");
                        contHundidos++;
                        //hago un break para salir del bucle, porque ya he encontrado la posicion del disparo
                        break;
                    }
                }
            } else if (simbolo == 'F') {
                System.out.println("Tocado");
                this.tablero[fila][columna] = 'X';
                acierto = true;
                posicionNoOcupada = true;
                for (Fragata fragata : fragatas) {
                    boolean hundido = fragata.posicionTocada(fila, columna);
                    if (hundido) {
                        System.out.println("Hundido");
                        contHundidos++;
                        break;
                    }
                }
            } else if (simbolo == 'B') {
                System.out.println("Tocado");
                this.tablero[fila][columna] = 'X';
                acierto = true;
                posicionNoOcupada = true;
                for (Buque buque : buques) {
                    boolean hundido = buque.posicionTocada(fila, columna);
                    if (hundido) {
                        System.out.println("Hundido");
                        contHundidos++;
                        break;
                    }
                }
            } else if (simbolo == 'P') {
                System.out.println("Tocado");
                this.tablero[fila][columna] = 'X';
                acierto = true;
                posicionNoOcupada = true;
                boolean hundido = portavion.posicionTocada(fila, columna);
                if (hundido) {
                    System.out.println("Hundido");
                    contHundidos++;
                }
            } else {
                System.out.println("Agua");
                this.tablero[fila][columna] = 'O';
                posicionNoOcupada = true;
            }

        } while (!posicionNoOcupada);
        return acierto;
    }

    
   
}
    
    

