import java.util.Scanner;

public class MisionPosibleMain {
    public static void main(String[] args) {
        Escenario e = new Escenario("Nostromo");

        //Cargar elementos desde el archivo de configuración inicial
        String nombreArchivo = "C:/Users/Usuario/Downloads/Repositorio10-main/ConfiguraciónInicial.txt";
        e.cargarElementos(nombreArchivo);

        //Mostrar el escenario inicial
        System.out.println("Estado inicial del escenario:");
        System.out.println(e);

        try (
        Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese la posición (renglon columna) de la bomba a detonar: ");
            int renglon = scanner.nextInt();
            int columna = scanner.nextInt();
            Elemento elemento = e.campoDeBatalla[renglon][columna];

            if (elemento instanceof Bomba) {
                ((Bomba) elemento).explotar();
            } else {
                System.out.println("No hay una bomba en esa posición.");
            }
        }

        //Mostrar el escenario después de la detonación
        System.out.println("Estado del escenario después de la detonación:");
        System.out.println(e);

        //Guardar el estado actual del escenario en el archivo
        e.guardarEstadoActual(nombreArchivo);
        System.out.println("El estado actual se ha guardado en " + nombreArchivo);
    }
}
