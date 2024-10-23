public class MisionPosibleMain {
    public static void main(String[] args) {
        
        Escenario e = new Escenario("Nostromo");
        System.out.println("Iniciando el juego en el escenario: " + e.getNombre());
        e.addElemento(new Terricola("Ripley", e, new Posicion(3, 2)));
        e.addElemento(new Extraterrestre("Alien", e, new Posicion(3, 5)));
        e.addElemento(new Roca(e, new Posicion(4, 3)));
        Bomba b = new Bomba(e, new Posicion(4, 4), 1);
        e.addElemento(b);

        System.out.println(e);
        b.explotar();
    }
}

