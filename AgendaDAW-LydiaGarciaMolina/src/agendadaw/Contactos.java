package agendadaw;

import java.io.Serializable;

public class Contactos implements Serializable{
    private String nombre;
    private String apellidos;
    private int edad;
    private int numeroTelefono;
    private String email;

    public Contactos(String nombre, String apellidos, int edad, int numeroTelefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getEmail() {
        return email;
    }
   
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    @Override
   public String toString(){
       return "Nombre: "+this.nombre+", Apellidos: "+this.apellidos+", Edad: "+this.edad+", "
               + "Numero de telefono: "+this.numeroTelefono+", Email: "+this.email;
   }
    

    
}


