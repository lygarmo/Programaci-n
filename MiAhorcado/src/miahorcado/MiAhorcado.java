package miahorcado;

import java.util.Scanner;

public class MiAhorcado {
    
    public static Scanner datos=new Scanner (System.in);

    
    //PEDIMOS PALABRAS 
    public static String palabraAzar(){        
        int nPalabras=1;
        String [] palabras = new String[nPalabras];

        for(int i=0; i<nPalabras; i++){
            System.out.println("Introduce la palabra numero "+i);
            palabras[i]=datos.nextLine();
        }
        nPalabras = 0;//= (int)(Math.random() * 10);
                
        return palabras[nPalabras];
    }
    
    //ENCRIPTAMOS PALABRA
    public static String palabraEncriptada(String palabra) {
        String palabraOculta="";

        for (int i = 0; i < palabra.length(); i++) {
            palabraOculta += '*';
        }

        return palabraOculta;
    }
    
    //INTRODUCE LA LETRA
    public static char letraDicha(){
        System.out.println("Introduce una letra ");
        char letra=datos.next().charAt(0);
        
        return letra;
    }
    
    //COMPRUEBA SI LA LETRA ESTA O NO 
    public static String desencriptar(char letraDicha, String palabra, String palabraEncriptada){
        String palabraDesencriptada=palabraEncriptada;
        for(int i=0; i<palabra.length(); i++){
            char letra=palabra.charAt(i);
            if(letraDicha==letra){
                //convertir el string en un array de char para poder cambiar 
                //el asterisco en la posicion 
                char []array=palabraDesencriptada.toCharArray();
                array[i]=letraDicha;
                //vuelvo a convertir el array en string y actualizo la variable
                palabraDesencriptada=new String(array);
            }
        }
        return palabraDesencriptada;
    }
       
    
    //DIBUJO
    public static void ahorcado(int error){
        switch (error) {
            case 0 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       ");
                System.out.println("        ||       ");
                System.out.println("        ||       ");
                System.out.println("        ||       " );
                System.out.println("        ||       ");
            }
            case 1 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       O");
                System.out.println("        ||       |");
                System.out.println("        ||       |");
                System.out.println("        ||       |" );
                System.out.println("        ||       |" );
            }
            case 2 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       O");
                System.out.println("        ||       |");
                System.out.println("        ||    ___|");
                System.out.println("        ||       |" );
                System.out.println("        ||       |" );
            }
            case 3 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       O");
                System.out.println("        ||       |");
                System.out.println("        ||    ___|___");
                System.out.println("        ||       |" );
                System.out.println("        ||       |" );
            }
            case 4 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       O");
                System.out.println("        ||       |");
                System.out.println("        ||    ___|___");
                System.out.println("        ||       |" );
                System.out.println("        ||       |" );
                System.out.println("        ||     |---    ");
                System.out.println("       ____    |    ");
            }
            case 5 -> {
                System.out.println("        ||=======|");
                System.out.println("        ||       O");
                System.out.println("        ||       |");
                System.out.println("        ||    ___|___");
                System.out.println("        ||       |" );
                System.out.println("        ||       |" );
                System.out.println("        ||     |---|    ");
                System.out.println("       ____    |   |  ");
            }
        }
    }
    
    public static void pintarLetrasJugadas(char[]letrasCorrectas, char[]letrasIncorrectas, int letrasCorrectasCont, int letrasIncorrectasCont){
        System.out.println("Letras correctas: ");
        for(int i=0; i<letrasCorrectasCont; i++){
            System.out.print(letrasCorrectas[i]+" ");
        } 
        System.out.println("");
        System.out.println("Letras inorrectas: ");
        for(int i=0; i<letrasIncorrectasCont; i++){
            System.out.print(letrasIncorrectas[i]+" ");
        }
         System.out.println("");
    }
    
    public static boolean estaLetraContenida(char letra,char [] letrasArray){
        boolean encontrada=false;
        for(int i=0; i<letrasArray.length; i++){
            if(letra==letrasArray[i])
                encontrada=true;
        }
        return encontrada;
    }
    
    

    public static void main(String[] args) {
        
        
        System.out.println("BIENVENIDO AL JUEGO DEL AHORCADO ");
        System.out.println("Vamos a empezar a jugar, te explicamos un poco las normas: ");
        int error=0;
        
            System.out.println("Tienes que introducir 10 palabras de las cuales aletoriamente se elegira 1 la cual debereas descubrir");
            System.out.println("Para esto tienes 6 oportunidades, las cuales se iran reflejando en el muñeco del ahorcado");
            System.out.println("Suerte ;)");
            ahorcado(error);
            
        //Muestra las palabras
        int cantidadPalabras=10;
        String palabraJuego = palabraAzar();
        //
        String palabraOculta=palabraEncriptada(palabraJuego);
        System.out.println("La palabra oculta es: "+palabraOculta);
        
        char[]letrasCorrectas=new char[6];
        int letrasCorrectasCont=0;
        char[]letrasIncorrectas=new char[6];
        int letrasIncorrectasCont=0;
       
        do{

        char letra = letraDicha();
            if(!estaLetraContenida(letra,letrasCorrectas) && !estaLetraContenida(letra,letrasIncorrectas)){
                //hemos convertido la letra en String
                if(palabraJuego.contains(String.valueOf(letra))){
                    palabraOculta=desencriptar(letra, palabraJuego,palabraOculta);
                    System.out.println(palabraOculta);     
                    letrasCorrectas[letrasCorrectasCont]=letra;
                    letrasCorrectasCont++;
                }else{
                    error++;
                    letrasIncorrectas[letrasIncorrectasCont]=letra;
                    letrasIncorrectasCont++;
                }
            }else{
                System.out.println("Esta letra ya la has jugado, introduce una nueva :)");
            }
            ahorcado(error);
            pintarLetrasJugadas(letrasCorrectas, letrasIncorrectas,letrasCorrectasCont,letrasIncorrectasCont);
        }while(error<6 && !palabraOculta.equals(palabraJuego));   
        
        if(palabraOculta.equals(palabraJuego)){
            System.out.println("HAS GANADO!! :)");
        }else{
            System.out.println("Has perdido :(");
        }
        
        System.out.println("Quieres volver a jugar? Introduce si o no ");
        

        
      
    }
}

