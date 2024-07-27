package menus;
import java.util.Scanner;
public class EquipoMenu {
	private static Scanner scanner = new Scanner(System.in);
	public static void mostrarMenu() {
	
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu Equipos");
            System.out.println("1. Alta de equipo");
            System.out.println("2. Baja de equipo");
            System.out.println("3. Modificación de equipo");
            System.out.println("4. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");
            System.out.println();
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
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

}
