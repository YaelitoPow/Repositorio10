public class Terricola extends Personaje {
    public Terricola(String nombre, Escenario escenario, Posicion posicion) {
        super(nombre, escenario, posicion);
    }

    @Override
    public String destruir() {
        return "Terricola " + nombre + " destruido";
    }
}


