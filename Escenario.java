import java.util.ArrayList;

public class Escenario {
    private String nombre;
    private Elemento[][] campoDeBatalla;

    public Escenario(String nombre) {
        this.nombre = nombre;
        this.campoDeBatalla = new Elemento[10][10];  //Tamaño fijo de 10x10
    }

    public String getNombre(){
        return nombre;
    }

    public void addElemento(Elemento e) {
        Posicion pos = e.getPosicion();
        campoDeBatalla[pos.getRenglon()][pos.getColumna()] = e;
    }

    public void destruirElementos(Posicion centro, int radio) {
        ArrayList<Elemento> objetosADestruir = new ArrayList<>();

        for (int i = Math.max(0, centro.getRenglon() - radio); 
                 i <= Math.min(9, centro.getRenglon() + radio); i++) {
            for (int j = Math.max(0, centro.getColumna() - radio); 
                     j <= Math.min(9, centro.getColumna() + radio); j++) {

                Elemento e = campoDeBatalla[i][j];
                if (e instanceof Destruible) { //Determinar si el objeto es de tipo 'Destruible'. 
                    objetosADestruir.add(e);
                }
            }
        }

        for (Elemento e : objetosADestruir) {
            System.out.println(((Destruible) e).destruir());
            campoDeBatalla[e.getPosicion().getRenglon()][e.getPosicion().getColumna()] = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (campoDeBatalla[i][j] == null) {
                    sb.append("0 ");
                } else if (campoDeBatalla[i][j] instanceof Terricola) {
                    sb.append("T ");
                } else if (campoDeBatalla[i][j] instanceof Extraterrestre) {
                    sb.append("E ");
                } else if (campoDeBatalla[i][j] instanceof Roca) {
                    sb.append("R ");
                } else if (campoDeBatalla[i][j] instanceof Bomba) {
                    sb.append("B ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

