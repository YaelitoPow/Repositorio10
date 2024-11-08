import java.io.*;
import java.util.*;

public class Escenario {
    private String nombre;
    Elemento[][] campoDeBatalla;

    public Escenario(String nombre) {
        this.nombre = nombre;
        this.campoDeBatalla = new Elemento[10][10]; //Tamaño fijo de 10x10
    }

    public String getNombre() {
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
                if (e instanceof Destruible) {
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

    //Método para escribir en un archivo de texto
    public static void escribirConfiguracion(String nombreArchivo, List<String> configuracion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : configuracion) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Configuración escrita en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    //Método para leer desde un archivo de texto
    public static List<String> leerConfiguracion(String nombreArchivo) {
        List<String> configuracion = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                configuracion.add(linea);
            }
            System.out.println("Configuración leída del archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return configuracion;
    }

    //Método para cargar elementos desde el archivo al escenario
    public void cargarElementos(String nombreArchivo) {
        List<String> configuracion = leerConfiguracion(nombreArchivo);
        for (String linea : configuracion) {
            String[] partes = linea.split(" ");
            String tipo = partes[0];
            int renglon = Integer.parseInt(partes[1]);
            int columna = Integer.parseInt(partes[2]);
            Posicion posicion = new Posicion(renglon, columna);

            switch (tipo) {
                case "Roca":
                    addElemento(new Roca(this, posicion));
                    break;
                case "Extraterrestre":
                    addElemento(new Extraterrestre("Extraterrestre", this, posicion));
                    break;
                case "Bomba":
                    int radio = Integer.parseInt(partes[3]);
                    addElemento(new Bomba(this, posicion, radio));
                    break;
                case "Terricola":
                    addElemento(new Terricola("Terricola", this, posicion));
                    break;
            }
        }
    }

    //Método para guardar el estado actual del escenario
    public void guardarEstadoActual(String nombreArchivo) {
        List<String> configuracion = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Elemento e = campoDeBatalla[i][j];
                if (e != null) {
                    if (e instanceof Roca) {
                        configuracion.add("Roca " + i + " " + j);
                    } else if (e instanceof Extraterrestre) {
                        configuracion.add("Extraterrestre " + i + " " + j);
                    } else if (e instanceof Bomba) {
                        Bomba bomba = (Bomba) e;
                        configuracion.add("Bomba " + i + " " + j + " " + bomba.radio);
                    } else if (e instanceof Terricola) {
                        configuracion.add("Terricola " + i + " " + j);
                    }
                }
            }
        }
        escribirConfiguracion(nombreArchivo, configuracion);
    }
}
