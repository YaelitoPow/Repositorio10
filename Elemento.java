public abstract class Elemento {
    
    protected Posicion posicion;
    protected Escenario escenario;

    public Elemento(Escenario escenario, Posicion posicion){

        this.escenario = escenario;
        this.posicion = posicion;

    }

    public Escenario getEscenario(){
        return escenario;
    }

    public Posicion getPosicion(){
        return posicion;
    }
}
