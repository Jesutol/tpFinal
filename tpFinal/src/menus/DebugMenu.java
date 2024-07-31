package menus;

import estructuras_de_datos.grafos.Grafo;
import estructuras_de_datos.conjuntistas.dinamica.AVL;
import estructuras_de_datos.tdaE.MultiValueHashMap;
import dominio.Ciudad;
import dominio.Equipo;
import dominio.ResultadoPartido;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DebugMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu(Grafo mapa, AVL equipos, MultiValueHashMap partidos) {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu de Debugging");
            System.out.println("1. Mostrar Mapa (Grafo)");
            System.out.println("2. Mostrar Equipos (AVL)");
            System.out.println("3. Mostrar Partidos (Tabla)");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println();
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1:
                    mostrarMapa(mapa);
                    break;
                case 2:
                    mostrarEquipos(equipos);
                    break;
                case 3:
                    mostrarPartidos(partidos);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarMapa(Grafo mapa) {
        System.out.println(mapa.toString());
    }

    private static void mostrarEquipos(AVL equipos) {
        System.out.println(equipos.toString());
    }

    private static void mostrarPartidos(MultiValueHashMap partidos) {
        System.out.println(partidos.toString());
    }
}
