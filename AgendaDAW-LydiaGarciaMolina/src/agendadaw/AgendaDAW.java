package agendadaw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AgendaDAW {
    
    public static void menu1(){
        System.out.println("   AGENDA  ");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Loguear usuario");
        System.out.println("3. SALIR");
    }
    
    public static void menu2(){
        System.out.println("\n  MENU DE CONTACTOS  ");
        System.out.println("1. Registrar contacto");
        System.out.println("2. Ver contactos");
        System.out.println("3. Buscar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. SALIR");
    }
    
    public static Scanner datos=new Scanner(System.in);
    

    
    public static void main(String[] args) {
        Agenda agenda=new Agenda();
        Usuarios u=new Usuarios("", "");
        int opcion=0;
        int contIniciosErroneos=0;
        int logueado=0;
        u.leerFichero();
        
        System.out.println("Bienvenido a la AGENDA DAW\n");
        do{
            try{
                menu1();
                System.out.println("Introduce tu opcion");
                opcion=datos.nextInt();

                switch (opcion) {
                    case 1:
                        u.crearUsuario();
                        u.guardarUsuario();
                        break;
                    case 2:
                        logueado=u.loguearUsuario();

                        if(logueado==1){
                            agenda.leerAgenda(u.getNombre());
                            do{
                                try{
                                    menu2(); 
                                    System.out.println("Introduce tu opcion");
                                    opcion=datos.nextInt();

                                    switch (opcion) {
                                        case 1:
                                            agenda.añadirContacto(u.getNombre());
                                            break;
                                        case 2:
                                            agenda.verContactos();
                                            break;
                                        case 3: 
                                            agenda.buscarContacto();
                                            break;
                                        case 4:
                                            agenda.borrarContacto(u.getNombre());
                                            break;
                                        case 5:
                                            System.out.println("Saliendo de la agenda");
                                            break;
                                        default:
                                            System.out.println("La opcion introducida no es valida, vuelve a introducir una nueva");;
                                    }
                                }catch(InputMismatchException e){
                                    System.out.println("Debes introducir un numero del 1 al 3\n");
                                    datos.next();
                                }
                            }while(opcion!=5);
                        }else if(logueado==3){
                            contIniciosErroneos++;
                            if(contIniciosErroneos==3){
                                System.out.println("Saliendo del programa");
                                opcion=3;
                            }
                        }
                        break;
                    case 3: 
                        System.out.println("\nSaliendo del programa");
                        break;
                    default:
                        System.out.println("La opcion introducida no es valida, vuelve a introducir una nueva");;
                }
            }catch(InputMismatchException e){
                System.out.println("Debes introducir un numero del 1 al 3\n");
                datos.next();
            }
        }while(opcion!=3);
    }

}
