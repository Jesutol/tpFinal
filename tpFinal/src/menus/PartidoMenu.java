package menus;

import java.util.Scanner;
import estructuras_de_datos.tdaE.*;
import estructuras_de_datos.conjuntistas.dinamica.*;
import dominio.ResultadoPartido;
import lectura_escritura.LecturaEscritura;

public class PartidoMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu(MultiValueHashMap partidos,AVL equipos) {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu Partidos");
            System.out.println("1. Alta de partido");
            System.out.println("2. Volver al menú anterior");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    altaPartido( partidos,equipos);
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void altaPartido(MultiValueHashMap partidos,AVL equipos) {
        System.out.print("Ingrese el nombre del equipo 1: ");
        String equipo1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del equipo 2: ");
        String equipo2 = scanner.nextLine();
        System.out.print("Ingrese la ronda: ");
        String ronda = scanner.nextLine();
        System.out.print("Ingrese la ciudad del evento: ");
        String ciudadEvento = scanner.nextLine();
        System.out.print("Ingrese el nombre del estadio: ");
        String nombreEstadio = scanner.nextLine();
        System.out.print("Ingrese los goles del equipo 1: ");
        int golesEquipo1 = scanner.nextInt();
        System.out.print("Ingrese los goles del equipo 2: ");
        int golesEquipo2 = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        ResultadoPartido resultado = new ResultadoPartido(equipo1, equipo2, ronda, ciudadEvento, nombreEstadio, golesEquipo1, golesEquipo2);
        LecturaEscritura.escribirResultadoPartido(resultado);
        System.out.println("Partido registrado exitosamente.");
    }
}
