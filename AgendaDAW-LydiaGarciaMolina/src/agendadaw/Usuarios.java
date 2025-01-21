package agendadaw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuarios {
    private String nombre;
    private String clave;
    private ArrayList <Usuarios> usuarios=new ArrayList<>();
    
    private int cont=0;

    public Scanner datos=new Scanner(System.in);
    
    public Usuarios(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }
    
    
    public void leerFichero(){
        String nomb="";
        String contra="";
        int cont=0;
        
        try{
            FileReader miLector = new FileReader("usuarios.txt");
            BufferedReader miBuffer=new BufferedReader(miLector);
            String linea="";
            
            while(linea!=null){
                linea=miBuffer.readLine();
                if(linea!=null){
                    char caracter;
                    for(int i=0; i<linea.length(); i++){
                        caracter=linea.charAt(i);
                        //cada vez que en el archivo aparezca un espacio significa que cambia de usuario a contraseña
                        if(caracter==' '){
                           cont++;
                        }
                        
                        //si el caracter es un distinto de un espacio y el contador es par se guarda en  la varibale nombre
                        //si el contador es impar se guarda en contraseña. y si es un espacio no se guarda porque sino se 
                        //contaria el espacio como un carcater de la contraseña.
                        if(caracter != ' '){
                            if(cont%2 == 0){
                                nomb=nomb+caracter;
                            }else{
                                contra=contra+caracter;
                            }
                        }
                        
                        //si el contador llega a dos siginifica que ya ha leido el nombre y la contraseña de 1 usuario
                        //reiniciamos el contador a 0 ya que vamos a leer un usuario nuevo para volver a hacer los mismos pasos
                        if(cont == 2){
                            Usuarios u=new Usuarios(nomb, contra);
                            this.usuarios.add(u);
                            cont = 0;
                            nomb = "";
                            contra = "";
                        }
                        
                    }
                   
                }
            }
            miBuffer.close(); 
            miLector.close();
        }catch(IOException e){
            // System.out.println("Archivo no encontrado");
        }
    }
    
    
    //creo el usuario
    public void crearUsuario(){
        //nombre
        boolean correcto=false;
        do{
            System.out.println("Introduce el nombre de tu usuario");
            this.nombre=datos.nextLine().toLowerCase();

            if(this.nombre.contains(" ")){
                correcto=false;
                System.out.println("No puede contener ningun espacio, introduce un nombre valido de nuevo");
            }else{
                correcto=true;
                for(Usuarios actual:this.usuarios){
                    if(actual.getNombre().equals(this.nombre)){
                        System.out.println("Este usuario ya existe");
                        correcto=false;
                        break;
                    }
                }
                 
            }  
        }while(!correcto);
        
        
        //clave
        if(correcto){
            correcto=false;
            do{
                System.out.println("Introduce la clave, recuerda que puedes introducir mayusculas, minusculas y numeros");
                this.clave=datos.nextLine();

                if(this.clave.contains(" ")){
                    correcto=false;
                    System.out.println("No puede contener ningun espacio, introduce una clave nueva");
                }else{
                    System.out.println("USUARIO REGISTRADO");
                    correcto=true;
                }

            }while(!correcto);

            Usuarios u=new Usuarios(this.nombre,this.clave);
            usuarios.add(u);
            
            //creo la agenda del usuario
            File file = new File("agenda-" + this.nombre + ".dat");
            try {
                file.createNewFile();
            } catch (IOException ex) {
               System.out.println("Error al crear el fichero de agenda");
            }
        }
        
    }
    
    
    /*muestro el usuario y la clave, lo he utilizado para probar
    public void mostrar(){
        for(Usuarios usuario : usuarios) {
            System.out.println(usuario.getNombre()+"  "+usuario.getClave());
        }
    }*/
    

    //logueo al usuario
    public int loguearUsuario(){
        int cont=0;
        String nombreNuevo;
        String claveNueva;
        int indice=0;
        
        
        //Lo que hago es primer ocomparar el usuario, si el usuario esta significa que hay una contraseña que tenemos que validar
        //sino hay usuario no tengo que validar nada y directamente lo descarto
        
        System.out.println("Introduce tu nombre");
        nombreNuevo=datos.nextLine().toLowerCase();
        System.out.println("Introduce tu clave");
        claveNueva=datos.nextLine();     
        
        for(Usuarios actual:usuarios){
            if(actual.getNombre().equals(nombreNuevo)){
                
                if(actual.getClave().equals(claveNueva)){
                    return 1;
                }else{
                    //contraseña mal introducida
                    this.cont++;
                    if(this.cont==3){
                        System.out.println("Usuario eliminado");
                        eliminarUsuario(indice);
                        borrarAgenda(nombreNuevo);
                        return 2;
                    }else{
                        System.out.println("Intentos restantes: "+(3-this.cont));
                    }
                    
                }
            }
            indice++;
        }
        return 3;
    }
    
      
    
    public void guardarUsuario() {
        File archivo = new File("usuarios.txt");
        if(archivo.exists()){
            //si el archivo existe se elimina
            archivo.delete();
        }
        //se vuelve a crear, y se escribe los datos que hay en el ArrayList
        //asi al borrar el usuario del ArrayList, se vuelve a gener un .txt nuevo en el 
        //cual se han volcado los datos del ArrayList
        archivo = new File("usuarios.txt");
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo, true))) {
            // Escribir el nombre de usuario y la clave en el archivo
            for(Usuarios usuario : usuarios) {
                escribir.write(usuario.getNombre() + " " + usuario.getClave() + " ");
            }     
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }


    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    
    public void eliminarUsuario(int indice){
        this.usuarios.remove(indice);
        //hago una actualizacion de usuarios
        guardarUsuario();
    }
   
    public void borrarAgenda(String nombreArchivoAgenda) {
        File archivoAgenda = new File("agenda-"+nombreArchivoAgenda+".dat");
        if (archivoAgenda.exists()) {
            archivoAgenda.delete();
        }
    }

    
}

    

