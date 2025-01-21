package agendadaw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda implements Serializable {
    
    private ArrayList <Contactos> contactos=new ArrayList <>();
       
    public void añadirContacto(String nombreUsuario){
        boolean existe=false;
        Scanner datos=new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto");
        String nombre=datos.nextLine().toLowerCase();
        System.out.println("Introduce los apellidos");
        String apellidos=datos.nextLine().toLowerCase();
        
        for(Contactos actual:this.contactos){
            if(actual.getNombre().equalsIgnoreCase(nombre) && actual.getApellidos().equalsIgnoreCase(apellidos)){
                System.out.println("Este contacto ya existe");
                existe=true;
            }
        }
        
        int edad=0;
        int numeroTelefono=0;
        if(existe==false){
             while (true) {
                try {
                    System.out.println("Introduce la edad");
                    edad = Integer.parseInt(datos.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número válido para la edad.");
                }
            }
            
            while (true) {
                try {
                    System.out.println("Introduce el número de teléfono");
                    numeroTelefono = Integer.parseInt(datos.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número válido para el teléfono.");
                }
            }
            
            System.out.println("Introduce la dirección de correo electrónico");
            String email = datos.nextLine().trim().toLowerCase();
            
            Contactos c = new Contactos(nombre, apellidos, edad, numeroTelefono, email);
            contactos.add(c);

        //guardo la agenda
        guardarAgenda(nombreUsuario);
        }
    }
    
    
    public void buscarContacto(){
        Scanner datos=new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto");
        String nombreContacto=datos.nextLine().toLowerCase();
        System.out.println("Introduce los apellidos del contacto");
        String apellidosContacto=datos.nextLine().toLowerCase();
        
        for(Contactos actual:contactos){
            if(actual.getNombre().equalsIgnoreCase(nombreContacto) && actual.getApellidos().equalsIgnoreCase(apellidosContacto)){
                System.out.println(actual);
            }
        }
    }
    
    
    public void borrarContacto(String nombreUsuario){
        Scanner datos=new Scanner(System.in);        
        System.out.println("Introduce el nombre del contacto");
        String nombreContacto=datos.nextLine().toLowerCase();
        System.out.println("Introduce los apellidos del contacto");
        String apellidosContacto=datos.nextLine().toLowerCase();
        Contactos contactoABorrar = null;
        
        for(Contactos actual:contactos){
            if(actual.getNombre().equalsIgnoreCase(nombreContacto) && actual.getApellidos().equalsIgnoreCase(apellidosContacto)){
                contactoABorrar = actual;
                break;
            }
        }
        
        if(contactoABorrar != null){
            contactos.remove(contactoABorrar);
        }else{
            System.out.println("Usuario a borrar no encontrado");
        }
        guardarAgenda(nombreUsuario);
    }

     public void verContactos(){
        if(contactos.size()<0){
            System.out.println("No existen contactos");
        }
        for(Contactos actual:contactos){
            System.out.println(actual.toString());
        }
    }
     
     
    public void guardarAgenda(String nombreUsuario){
        File archivo = new File("agenda-"+nombreUsuario+".dat");
        try {
            //abrir flujo, creo un objeto tipo objectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  
    }
    
    
    public void leerAgenda(String nombreUsuario) {
        File archivo = new File("agenda-"+nombreUsuario+".dat");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            Agenda agendaLeida=(Agenda)ois.readObject();
            //lleno la agenda con los datos del fichero
            this.contactos=agendaLeida.getContactos();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha creado la agenda");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }catch(ClassNotFoundException e) {
            System.out.println("No se puedo encontrar el archivo");
        } 
    }

    public ArrayList<Contactos> getContactos() {
        return contactos;
    }

    

}
