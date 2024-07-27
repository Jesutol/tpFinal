package menus;

import java.util.Scanner;

public class Sistema {
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

				break;
			case 4:
				 mostrarMenuC();

				break;
			case 5:
				
				break;
			case 6:
			
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
				CiudadMenu.mostrarMenu();
				
				break;
			case 2:
				EquipoMenu.mostrarMenu();
				
				break;
			case 3:
				
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
            System.out.println("1. Consultar por país");
            System.out.println("2. Consultar por rango alfabético");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            System.out.println();
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                   
                    break;
                case 2:
                   
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
	            scanner.nextLine();  

	            switch (opcion) {
	                case 1:
	                    
	                    break;
	                case 2:
	                    
	                    break;
	                case 3:
	                    salir = true;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        }
	    }
}