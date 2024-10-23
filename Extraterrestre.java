public class Extraterrestre extends Personaje {
    public Extraterrestre(String nombre, Escenario escenario, Posicion posicion) {
        super(nombre, escenario, posicion);
    }

    @Override
    public String destruir() {
        return nombre + " destruido";
    }
}
