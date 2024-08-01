package lectura_escritura;
import estructuras_de_datos.conjuntistas.dinamica.*;
import estructuras_de_datos.grafos.*;
import estructuras_de_datos.tdaE.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.StringTokenizer;

import dominio.Ciudad;
import dominio.Equipo;
import dominio.ResultadoPartido;
import dominio.RutaAerea;

public class LecturaEscritura {

	public static final String ciudades_path = "src/datos/Ciudades.txt";
	public static final String equipos_path = "src/datos/Equipos.txt";
	public static final String resultados_path = "src/datos/Partidos.txt";
	public static final String rutas_path = "src/datos/Rutas.txt";


	public static void cargaTodosLosDatos(Grafo mapa,AVL equipos,MultiValueHashMap partidos){

		leerCiudad(mapa);
		leerEquipos(equipos);
		leerResultadoPartido(partidos, equipos);
		leerRutaAerea(mapa);





	}

	public static void leerCiudad(Grafo mapa) {
		try (BufferedReader br = new BufferedReader(new FileReader(ciudades_path))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				// Ignorar el prefijo 'C: '
				if (linea.startsWith("C: ")) {
					linea = linea.substring(2).trim(); // Eliminar 'C: ' del inicio
				}

				StringTokenizer st = new StringTokenizer(linea, ";");
				if (st.countTokens() == 3) {
					String nombre = st.nextToken().trim();
					boolean alojamientoD = Boolean.parseBoolean(st.nextToken().trim());
					boolean esSede = Boolean.parseBoolean(st.nextToken().trim());

					Ciudad ciudad = new Ciudad(nombre, alojamientoD, esSede);
					mapa.insertarVertice(ciudad);
					System.out.println(ciudad.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void leerEquipos(AVL equipos) {
		try (BufferedReader br = new BufferedReader(new FileReader(equipos_path))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("E: ")) {
					String contenido = linea.substring(3); // 
					StringTokenizer st = new StringTokenizer(contenido, ";");
					if (st.countTokens() == 3) {
						String nombrePais = st.nextToken().trim();
						String directorTecnico = st.nextToken().trim();
						char grupo = st.nextToken().trim().charAt(0);

						Equipo equipo = new Equipo(nombrePais, directorTecnico, grupo);
						equipos.insertar(equipo);
						System.out.println(equipo.toString());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void leerResultadoPartido(MultiValueHashMap partidos,AVL equipos) {
		try (BufferedReader br = new BufferedReader(new FileReader(resultados_path))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("P: ")) {
					String contenido = linea.substring(3); // Ignorar "P: "
					StringTokenizer st = new StringTokenizer(contenido, ";");
					if (st.countTokens() == 7) {
						String equipo1 = st.nextToken().trim();
						String equipo2 = st.nextToken().trim();
						String ronda = st.nextToken().trim();
						String ciudadEvento = st.nextToken().trim();
						String nombreEstadio = st.nextToken().trim();
						int golesEquipo1 = Integer.parseInt(st.nextToken().trim());
						int golesEquipo2 = Integer.parseInt(st.nextToken().trim());


						ResultadoPartido resultado = new ResultadoPartido(equipo1, equipo2, ronda, ciudadEvento, nombreEstadio, golesEquipo1, golesEquipo2);
						partidos.put(resultado.getClave(), resultado);
				

						Equipo auxE1=new Equipo(equipo1);
						Equipo auxE2=new Equipo(equipo2);
						 auxE1 = (Equipo) equipos.obtenerElemento(auxE1);
						 auxE2 = (Equipo) equipos.obtenerElemento(auxE2);
						 
						 if(auxE1!=null&&auxE2!=null) {
							// Asignar goles
								auxE1.setGolesAFavor(golesEquipo1);
								auxE1.setGolesEnContra(golesEquipo2);
								auxE2.setGolesAFavor(golesEquipo2);
								auxE2.setGolesEnContra(golesEquipo1);

								// Asignar puntos
								if (golesEquipo1 > golesEquipo2) {
									auxE1.setPuntosGanados(3);
								} else if (golesEquipo1 < golesEquipo2) {
									auxE2.setPuntosGanados(3);
								} else {
									auxE1.setPuntosGanados(1);
									auxE2.setPuntosGanados(1);
								}

							 
						 }


					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void leerRutaAerea(Grafo mapa) {
		try (BufferedReader br = new BufferedReader(new FileReader(rutas_path))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("R: ")) {
					String contenido = linea.substring(3); // Ignorar "R: "
					StringTokenizer st = new StringTokenizer(contenido, ";");
					if (st.countTokens() == 3) {
						String ciudadOrigen = st.nextToken().trim();
						String ciudadDestino = st.nextToken().trim();
						double tiempoVuelo = Double.parseDouble(st.nextToken().trim());

						Ciudad auxO=new Ciudad(ciudadOrigen);
						Ciudad auxD=new Ciudad(ciudadDestino);
						RutaAerea rutaAerea = new RutaAerea(ciudadOrigen, ciudadDestino, tiempoVuelo);
						System.out.println(rutaAerea.toString());
						System.out.println(mapa.insertarArco(auxO, auxD, tiempoVuelo));



					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirCiudad(Ciudad ciudad) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ciudades_path, true))) {
			bw.write("C: " + ciudad.getNombre() + ";" + ciudad.getAlojamientoDisponible() + ";" + ciudad.getEsSede());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirEquipo(Equipo equipo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(equipos_path, true))) {
			bw.write("E: " + equipo.getNombrePais() + ";" + equipo.getDirectorTecnico() + ";" + equipo.getGrupo());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirResultadoPartido(ResultadoPartido resultado) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultados_path, true))) {
			bw.write("P: " + resultado.getClave().getEquipo1() + ";" + resultado.getClave().getEquipo2() + ";" + resultado.getRonda() + ";" +
					resultado.getCiudadEvento() + ";" + resultado.getNombreEstadio() + ";" + resultado.getGolesEquipo1() + ";" +
					resultado.getGolesEquipo2());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirRutaAerea(RutaAerea rutaAerea) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutas_path, true))) {
			bw.write("R: " + rutaAerea.getCiudadOrigen() + ";" + rutaAerea.getCiudadDestino() + ";" + rutaAerea.getTiempoVuelo());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Métodos para eliminar
	public static void eliminarCiudad(String nombreCiudad) {
		eliminarEntrada(ciudades_path, "C:", nombreCiudad);
	}

	public static void eliminarEquipo(String nombrePais) {
		eliminarEntrada(equipos_path, "E:", nombrePais);
	}

	public static void eliminarResultadoPartido(String equipo1, String equipo2, String ronda) {
		eliminarEntrada(resultados_path, "P:", equipo1 + ";" + equipo2 + ";" + ronda);
	}

	public static void eliminarRutaAerea(String ciudadOrigen, String ciudadDestino) {
		eliminarEntrada(rutas_path, "R:", ciudadOrigen + ";" + ciudadDestino);
	}

	private static void eliminarEntrada(String filePath, String prefix, String key) {
		File inputFile = new File(filePath);
		File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.startsWith(prefix) || !linea.contains(key)) {
					bw.write(linea);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Borrar el archivo original
		if (!inputFile.delete()) {
			System.out.println("No se pudo borrar el archivo original");
			return;
		}

		// Renombrar el archivo temporal
		if (!tempFile.renameTo(inputFile)) {
			System.out.println("No se pudo renombrar el archivo temporal");
		}
	}
}
