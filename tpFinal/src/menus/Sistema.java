package menus;

import java.util.Scanner;
import estructuras_de_datos.conjuntistas.dinamica.AVL;
import estructuras_de_datos.tdaE.MultiValueHashMap;
import estructuras_de_datos.grafos.*;
import estructuras_de_datos.lineales.dinamica.*;

public class Sistema {

	private static AVL equipos=new AVL();
	private static  MultiValueHashMap tabla=new  MultiValueHashMap();
	private static Grafo mapa=new Grafo();
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		menu();
	}


	public static void menu() {

		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Principal");
			System.out.println("1. Altas, bajas y modificaciones");
			System.out.println("2. Consultas sobre equipos");
			System.out.println("3. Consultar partidos entre equipos");
			System.out.println("4. Consultas sobre viajes");
			System.out.println("5. Generar lista de equipos por goles a favor");
			System.out.println("6. Mostrar sistema");
			System.out.println("7. Salir");
			System.out.print("Seleccione una opción: ");
			System.out.println();
			int opcion = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcion) {
			case 1:
				mostrarMenuA();
				break;
			case 2:
				mostrarMenuB();

				break;
			case 3:
				consultaPartido();
				break;
			case 4:
				mostrarMenuC();

				break;
			case 5:
				mostrarEquiposPorGoles();
				break;
			case 6:
				DebugMenu.mostrarMenu(mapa, equipos, tabla);
				break;
			case 7:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}



		}
	}

	private static void mostrarMenuA() {
		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Altas, Bajas y Modificaciones");
			System.out.println("1. Ciudades");
			System.out.println("2. Equipos");
			System.out.println("3. Partidos");
			System.out.println("4. Volver al menú principal");
			System.out.print("Seleccione una opción: ");
			System.out.println();
			int opcion = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcion) {
			case 1:
				CiudadMenu.mostrarMenu(mapa);

				break;
			case 2:
				EquipoMenu.mostrarMenu(equipos);

				break;
			case 3:
				PartidoMenu.mostrarMenu(tabla, equipos,mapa);
				break;
			case 4:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}

		}
	}
	private static void mostrarMenuB() {
		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Consultas sobre Equipos");
			System.out.println("1. Consultar por país estadisticas");
			System.out.println("2. Consultar por rango alfabético con min y max");
			System.out.println("3. Volver al menú principal");
			System.out.print("Seleccione una opción: ");
			System.out.println();
			int opcion = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcion) {
			case 1:
				consultarPorPaisE();
				break;
			case 2:
				mostrarEquiposA();

				break;
			case 3:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		}
	}
	private static void mostrarMenuC() {
		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Consultas sobre Viajes");
			System.out.println("1. Obtener camino de menor tiempo");
			System.out.println("2. Obtener camino con mínima cantidad de ciudades");
			System.out.println("3. Volver al menú principal");
			System.out.print("Seleccione una opción: ");
			System.out.println();
			int opcion = scanner.nextInt();
			  

			switch (opcion) {
			case 1:
				caminoMenorTiempo();
				break;
			case 2:
				caminoMinimaCiudades();
				break;
			case 3:
				salir = true;
				break;
			default:
				System.out.println("Opcion no valida. Intente de nuevo.");
			}
		}
	}
	public static void consultarPorPaisE() {}
	public static void mostrarEquiposA() {
		
		System.out.println("Ingrese el minimo (orden alfabetico)");
		String  min =scanner.nextLine();
		System.out.println("Ingrese el maximo (orden alfabetico)");
		String  max =scanner.nextLine();
		System.out.println();
		System.out.println(equipos.listarRango(min, max).toString());
		
	}
	public static void consultaPartido() {


	}


	public static void caminoMenorTiempo() {
		
		System.out.println("Ingresar ciudad origen");
		String  origen =scanner.nextLine();
		System.out.println("Ingresar ciudad destino");
		String  destino =scanner.nextLine();
		System.out.println();
		System.out.println(mapa.caminoMasCortoPorEtiqueta(origen, destino));


	}
	public static void caminoMinimaCiudades() {
		System.out.println("Ingresar ciudad origen");
		String  origen =scanner.nextLine();
		System.out.println("Ingresar ciudad destino");
		String  destino =scanner.nextLine();
		System.out.println();
		System.out.println(mapa.caminoMasCorto(origen, destino));


	}
	public static void mostrarEquiposPorGoles() {


	}
}