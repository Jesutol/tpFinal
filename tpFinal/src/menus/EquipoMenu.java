package menus;
import java.util.InputMismatchException;
import java.util.Scanner;
import dominio.Equipo;
import lectura_escritura.LecturaEscritura;
import estructuras_de_datos.conjuntistas.dinamica.*;

public class EquipoMenu {
    private static Scanner scanner = new Scanner(System.in);

    

    public static void mostrarMenu(AVL equipos) {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu Equipos");
            System.out.println("1. Alta de equipo");
            System.out.println("2. Baja de equipo");
            System.out.println("3. Modificacion de director tecnico");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    altaEquipo(equipos);
                    break;
                case 2:
                    bajaEquipo(equipos);
                    break;
                case 3:
                    modificarDirectorTecnico(equipos);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private static void altaEquipo(AVL equipos) {
        System.out.print("Ingrese el nombre del pais del equipo: ");
        String nombrePais = scanner.nextLine();
        nombrePais=nombrePais.toUpperCase();
        System.out.print("Ingrese el nombre del director tecnico: ");
        String directorTecnico = scanner.nextLine();
        directorTecnico=directorTecnico.toUpperCase();
        System.out.print("Ingrese el grupo (un solo caracter): ");
        char grupo = scanner.nextLine().toUpperCase().charAt(0);
        Equipo equipo = new Equipo(nombrePais, directorTecnico, grupo);
        if(equipos.insertar(equipo)) {
        	
        	 LecturaEscritura.escribirEquipo(equipo);
             System.out.println("Equipo " + nombrePais + " agregado exitosamente.");
        }else {
        	  System.out.println("Equipo " + nombrePais + " Error ya se encuentra cargado.");
        	
        	
        }
       
    }

    private static void bajaEquipo(AVL equipos) {
        System.out.print("Ingrese el nombre del pais del equipo: ");
        String nombrePais = scanner.nextLine().trim();
        nombrePais=nombrePais.toUpperCase();
        Equipo aux=new Equipo(nombrePais);
        if(equipos.eliminar(aux)) {
        	 LecturaEscritura.eliminarEquipo(nombrePais);
             System.out.println("Equipo " + nombrePais + " eliminado exitosamente.");
        	
        }else {
        	System.out.println("Equipo " + nombrePais + " No encontrado");
        }
       
    }

    private static void modificarDirectorTecnico(AVL equipos) {
        System.out.print("Ingrese el nombre del pais del equipo: ");
        String nombrePais = scanner.nextLine().trim();
        nombrePais=nombrePais.toUpperCase();
        // Buscar el equipo
        Equipo equipo= new Equipo(nombrePais);
         equipo = (Equipo) equipos.obtenerElemento(equipo);
        if (equipo != null) {
            System.out.print("Ingrese el nuevo nombre del director tecnico: ");
            String nuevoDirectorTecnico = scanner.nextLine();
            equipo.setDirectorTecnico(nuevoDirectorTecnico);
            LecturaEscritura.eliminarEquipo(nombrePais);
            LecturaEscritura.escribirEquipo(equipo);
            
            System.out.println("Director tecnico del equipo " + nombrePais + " modificado exitosamente.");
        } else {
            System.out.println("Equipo con el nombre " + nombrePais + " no encontrado.");
        }
    }
}
