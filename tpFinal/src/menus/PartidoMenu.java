package menus;

import dominio.Ciudad;
import dominio.Equipo;

import java.util.Scanner;
import estructuras_de_datos.grafos.*;
import estructuras_de_datos.tdaE.*;
import estructuras_de_datos.conjuntistas.dinamica.*;
import dominio.ResultadoPartido;
import lectura_escritura.LecturaEscritura;

public class PartidoMenu {
	private static Scanner scanner = new Scanner(System.in);

	public static void mostrarMenu(MultiValueHashMap partidos, AVL equipos, Grafo mapa) {
		boolean salir = false;
		while (!salir) {
			System.out.println("Menu Partidos");
			System.out.println("1. Alta de partido");
			System.out.println("2. Volver al menu anterior");
			System.out.print("Seleccione una opcion: ");
			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				altaPartido(partidos, equipos, mapa);
				break;
			case 2:
				salir = true;
				break;
			default:
				System.out.println("Opcion no valida. Intente de nuevo.");
			}
		}
	}

	private static void altaPartido(MultiValueHashMap partidos, AVL equipos, Grafo mapa) {
		System.out.print("Ingrese el nombre del equipo 1: ");
		String equipo1 = scanner.nextLine();
		System.out.print("Ingrese el nombre del equipo 2: ");
		String equipo2 = scanner.nextLine();

		Equipo auxE1 =(Equipo) equipos.obtenerElemento(equipo1);
		Equipo  auxE2 =(Equipo) equipos.obtenerElemento(equipo2);

		if (auxE1 != null && auxE2 != null) {
			System.out.print("Ingrese la ciudad del evento: ");
			String ciudadEvento = scanner.nextLine().toUpperCase();
			Ciudad ciudadA = (Ciudad) mapa.obtenerElemento(ciudadEvento);

			if (ciudadA != null && ciudadA.getEsSede()) {
				System.out.print("Ingrese la ronda: ");
				String ronda = scanner.nextLine();
				System.out.print("Ingrese el nombre del estadio: ");
				String nombreEstadio = scanner.nextLine();
				int golesEquipo1 = 0;
				int golesEquipo2 = 0;


				try {
					System.out.print("Ingrese los goles del equipo 1: ");
					golesEquipo1 = scanner.nextInt();
					System.out.print("Ingrese los goles del equipo 2: ");
					golesEquipo2 = scanner.nextInt();
					scanner.nextLine(); 
					ResultadoPartido resultado = new ResultadoPartido(equipo1, equipo2, ronda, ciudadEvento, nombreEstadio, golesEquipo1, golesEquipo2);
					String clave=equipo1+"-"+equipo2;
					partidos.put(clave, resultado);

					System.out.println("Partido registrado exitosamente.");
					LecturaEscritura.escribirResultadoPartido(resultado);
				} catch (Exception e) {
					System.out.println("Error: Entrada inválida para los goles. Deben ser números enteros.");
					scanner.nextLine(); 

				}




			} else {
				System.out.println("Error: La ciudad " + ciudadEvento + " no es sede o no existe.");
			}
		} else {
			String aux1 = (auxE1 != null) ? "se encontro" : "no se encontro";
			String aux2 = (auxE2 != null) ? "se encontro" : "no se encontro";
			System.out.println("Error: Uno de los equipos no se encuentra en la copa.");
			System.out.println("El equipo 1 (" + equipo1 + "): " + aux1);
			System.out.println("El equipo 2 (" + equipo2 + "): " + aux2);
		}
	}
}
