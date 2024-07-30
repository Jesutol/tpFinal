package menus;
import java.util.InputMismatchException;
import java.util.Scanner;
import dominio.Ciudad;
import lectura_escritura.LecturaEscritura;
import estructuras_de_datos.grafos.*;

public class CiudadMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Aquí se puede inicializar el grafo y llamar a mostrarMenu(mapa) si es necesario
    }

    public static void mostrarMenu(Grafo mapa) {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu Ciudades");
            System.out.println("1. Alta de ciudad");
            System.out.println("2. Baja de ciudad");
            System.out.println("3. Modificacion de ciudad");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    altaCiudad(mapa);
                    break;
                case 2:
                    bajaCiudad(mapa);
                    break;
                case 3:
                    modificarCiudad(mapa);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private static void altaCiudad(Grafo m) {
        System.out.print("Ingrese el nombre de la ciudad: ");
        String nombre = scanner.nextLine();

        boolean alojamientoDisponible = false;
        System.out.print("La ciudad tiene alojamiento disponible? (true/false): ");
        try {
            alojamientoDisponible = scanner.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no valida. Por favor, ingrese 'true' o 'false'.");
            return; // Salir del método en caso de entrada inválida
        }

        boolean esSede = false;
        System.out.print("Es sede? (true/false): ");
        try {
            esSede = scanner.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no valida. Por favor, ingrese 'true' o 'false'.");
            return; // Salir del método en caso de entrada inválida
        }

        Ciudad ciudad = new Ciudad(nombre, alojamientoDisponible, esSede);
        if (m.insertarVertice(ciudad)) {
            LecturaEscritura.escribirCiudad(ciudad);
            System.out.println("Ciudad " + nombre + " agregada exitosamente.");
        } else {
            System.out.println("Esta ciudad ya se encuentra en el mapa.");
        }
    }

    private static void bajaCiudad(Grafo m) {
        System.out.print("Ingrese el nombre de la ciudad a eliminar: ");
        String nombre = scanner.nextLine();
        if (m.eliminarVert(nombre)) {
            LecturaEscritura.eliminarCiudad(nombre);
            System.out.println("Ciudad " + nombre + " eliminada exitosamente.");
        } else {
            System.out.println("Ciudad " + nombre + " No fue encontrada en el mapa.");
        }
    }

    private static void modificarCiudad(Grafo m) {
        System.out.print("Ingrese el nombre de la ciudad a modificar: ");
        String nombre = scanner.nextLine();
        Ciudad ciudad = (Ciudad) m.obtenerElemento(nombre);

        if (ciudad != null) {
            System.out.println("Modificar datos de la ciudad " + nombre);
            System.out.println("1. Cambiar estado de alojamiento disponible");
            System.out.println("2. Cambiar estado de sede");
            System.out.println("3. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            boolean seguir =true;
            while(seguir) {
            	switch (opcion) {
                case 1:
                    boolean nuevoAlojamientoDisponible = false;
                    System.out.print("Nuevo estado de alojamiento disponible (true/false): ");
                    try {
                        nuevoAlojamientoDisponible = scanner.nextBoolean();
                        LecturaEscritura.eliminarCiudad(nombre);
                        LecturaEscritura.escribirCiudad(ciudad);
                        System.out.println("Ciudad " + nombre + " modificada exitosamente.");
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no valida. Por favor, ingrese 'true' o 'false'.");
                    
                    }
                    ciudad.setAlojamientoDisponible(nuevoAlojamientoDisponible);
                    System.out.println("Estado de alojamiento disponible actualizado.");
                    break;

                case 2:
                    boolean nuevoEsSede = false;
                    System.out.print("Nuevo estado de sede (true/false): ");
                    try {
                        nuevoEsSede = scanner.nextBoolean();
                        LecturaEscritura.eliminarCiudad(nombre);
                        LecturaEscritura.escribirCiudad(ciudad);
                        System.out.println("Ciudad " + nombre + " modificada exitosamente.");
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no valida. Por favor, ingrese 'true' o 'false'.");
                
                    }
                    ciudad.setEsSede(nuevoEsSede);
                    System.out.println("Estado de sede actualizado.");
                    break;

                case 3:
                  

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }

            }
            // Guardar los cambios en la persistencia
         
        } else {
            System.out.println("Ciudad " + nombre + " no fue encontrada.");
        }
    }
}
