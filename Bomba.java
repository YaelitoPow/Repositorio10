public class Bomba extends Elemento implements Destruible {

    protected int radio;

    public Bomba(Escenario escenario, Posicion posicion, int radio){
        super(escenario, posicion);
        this.radio = radio;
    }

    public void explotar(){

        System.out.println("Explotando bomba!!");
        escenario.destruirElementos(posicion, radio);
    }

    @Override 
    public String destruir(){
        return "Bomba destruida";

    }
   
}
