package menus;

import java.util.Scanner;

public class Sistema {
	private static Scanner scanner = new Scanner(System.in);


	public static void menu() {

		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Principal");
			System.out.println("1. Altas, bajas y modificaciones");
			System.out.println("2. Consultas sobre equipos");
			System.out.println("3. Consultas sobre partidos");
			System.out.println("4. Consultas sobre viajes");
			System.out.println("5. Generar lista de equipos por goles a favor");
			System.out.println("6. Mostrar sistema");
			System.out.println("7. Salir");
			System.out.print("Seleccione una opción: ");
			int opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar buffer

			switch (opcion) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				// Llamar al método correspondiente
				break;
			case 6:
				// Llamar al método correspondiente
				break;
			case 7:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}



		}
	}
}
