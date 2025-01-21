package javaapplication13;

public class Posicion {
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            //comprueba que estos dos objetos son de la misma instancia(comparando la memoria)
            return true;
        }
        //se verifica que obj sea una instancia de Posicion o de los que se heredan
        if (obj instanceof Posicion) {
            //casteo para convertir obj a obj de tipo posicion
            Posicion other = (Posicion) obj;
            //compara que obj y los atributos(this) sean iguales y los devuelve
            return this.fila == other.fila &&this.columna == other.columna;
        }
        //devuelvo false si el obj no es instancia o si los atributos (fila y columna) no son iguales
        return false;
    }
    
    

 
}
