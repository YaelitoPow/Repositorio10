public class Posicion{

    protected int renglon;
    protected int columna;

    public Posicion(int renglon, int columna){

        this.renglon = renglon;
        this.columna = columna;

    }
    
    //Getters

    public int getRenglon(){
        return renglon;
    }

    public int getColumna(){
        return columna;
    }

}