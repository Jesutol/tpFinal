package menus;

import java.util.Scanner;
import logs.*;


import dominio.Ciudad;
import dominio.ClavePartido;
import dominio.Equipo;
import dominio.EquipoGoles;
import estructuras_de_datos.conjuntistas.dinamica.AVL;
import estructuras_de_datos.tdaE.MultiValueHashMap;
import lectura_escritura.LecturaEscritura;
import estructuras_de_datos.grafos.*;
import estructuras_de_datos.lineales.dinamica.*;

public class Sistema {

	private static AVL equipos=new AVL();
	private static  MultiValueHashMap tabla=new  MultiValueHashMap();
	private static Grafo mapa=new Grafo();
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		LecturaEscritura.crearLog();
		LecturaEscritura.cargaTodosLosDatos(mapa, equipos, tabla);
		
		
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
			System.out.println("1. Consultar por Equipo estadisticas");
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
		
			int opcion = scanner.nextInt();
					scanner.nextLine(); 
			  

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
	public static void consultarPorPaisE() {
		
		System.out.println("Ingresar nombre del pais");
		String  equipo =scanner.nextLine();
		Equipo aux=new Equipo(equipo.toUpperCase());
		 aux =(Equipo) equipos.obtenerElemento(aux);
		
		if(aux!=null) {
			
			System.out.println("Equipo: "+equipo+" GolesAfavor: "+aux.getGolesAFavor()+" GolesEnContra: "+aux.getGolesEnContra()
			+" Diferencia de goles: "+aux.calculaDiferenciaDeGoles());
			
			
		}else {
			System.out.println("El equipo:"+equipo+" no fue encontrado");
		}
		
		
		
		
	}
	
	public static void mostrarEquiposA() {
		
		System.out.println("Ingrese el minimo (orden alfabetico)");
		String  min =scanner.nextLine().trim();
		System.out.println("Ingrese el maximo (orden alfabetico)");
		String  max =scanner.nextLine().trim();
		Equipo auxE1=new Equipo(min.toUpperCase());
		Equipo auxE2=new Equipo(max.toUpperCase());
		// recorda que v es menor que venezuela por lo que si es el maximo no estara
		 System.out.println(equipos.listarRango(auxE1, auxE2).toString());
		
	
		
	}
	public static void consultaPartido() {

		System.out.print("Ingrese el nombre del equipo 1: ");
	    String equipo1 = scanner.nextLine().trim();

	    System.out.print("Ingrese el nombre del equipo 2: ");
	    String equipo2 = scanner.nextLine().trim();
	    
	    ClavePartido clave=new  ClavePartido(equipo1,equipo2);
	    
	    System.out.println(tabla.get(clave));

	}

	public static void caminoMenorTiempo() {
	   


	    // Leer el nombre del equipo 1
	    System.out.println("Ingresar ciudad origen:");
	    String ciudadO = scanner.nextLine().trim(); 

	    // Leer el nombre del equipo 2
	    System.out.println("Ingresar ciudad Destino:");
	    String ciudadD = scanner.nextLine().trim(); 

	    // Crear objetos Ciudad con los nombres ingresados
	    Ciudad auxO = new Ciudad(ciudadO.toUpperCase());
	    Ciudad auxD = new Ciudad(ciudadD.toUpperCase());

	    // Imprimir la información de las ciudades
	    System.out.println(mapa.caminoMasCortoPorEtiqueta(auxO, auxD));


	}

	public static void caminoMinimaCiudades() {
	    System.out.println("Ingresar ciudad origen:");
	    String ciudadO = scanner.nextLine().trim(); // Lee y limpia el buffer

	    // Leer el nombre del equipo 2
	    System.out.println("Ingresar ciudad Destino:");
	    String ciudadD = scanner.nextLine().trim(); // Lee y limpia el buffer

	    // Crear objetos Ciudad con los nombres ingresados
	    Ciudad auxO = new Ciudad(ciudadO.toUpperCase());
	    Ciudad auxD = new Ciudad(ciudadD.toUpperCase());

	    // Imprimir la información de las ciudades
	    System.out.println(mapa.caminoMasCorto(auxO, auxD));


	}
	public static void mostrarEquiposPorGoles() {
		
		Lista aux=equipos.lista();
		int i=1;
		int longitud=aux.longitud();
		
		if(!aux.esVacia()) {
			//arbol que organiza equipos por gol
			AVL ordenaE=new AVL();
			
			while(i<=longitud) {
				Equipo equipo=(Equipo) aux.recuperar(i);
				
				//Uso un constructor con esos datos , ya que son los unicos relevantes en este caso ya que sera algo temporal
				EquipoGoles equipoG=new EquipoGoles(equipo.getNombrePais(),equipo.getGolesAFavor());
				ordenaE.insertar(equipoG);
				
				i++;
				
			}
			
			System.out.println(ordenaE.lista());
			
		
			
			
			
		}else {
			
			System.out.println("No se encontraron equipos");
		}
		
		
		
	


	}
}