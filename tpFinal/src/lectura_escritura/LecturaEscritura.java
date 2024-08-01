package lectura_escritura;

import estructuras_de_datos.conjuntistas.dinamica.*;
import estructuras_de_datos.grafos.*;
import estructuras_de_datos.tdaE.*;
import logs.Log;

import java.io.*;
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
    
    private static Log log;

    public static void crearLog() {
        log = new Log();
    }

    public static void cargaTodosLosDatos(Grafo mapa, AVL equipos, MultiValueHashMap partidos) {
        leerCiudad(mapa);
        leerEquipos(equipos);
        leerResultadoPartido(partidos, equipos);
        leerRutaAerea(mapa);
    }

    public static void leerCiudad(Grafo mapa) {
        try (BufferedReader br = new BufferedReader(new FileReader(ciudades_path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("C: ")) {
                    linea = linea.substring(3).trim(); // Eliminar 'C: ' del inicio
                }

                StringTokenizer st = new StringTokenizer(linea, ";");
                if (st.countTokens() == 3) {
                    String nombre = st.nextToken().trim();
                    boolean alojamientoD = Boolean.parseBoolean(st.nextToken().trim());
                    boolean esSede = Boolean.parseBoolean(st.nextToken().trim());

                    Ciudad ciudad = new Ciudad(nombre, alojamientoD, esSede);
                    mapa.insertarVertice(ciudad);
                    log.info("Ciudad cargada: " + ciudad.toString());
                }
            }
        } catch (IOException e) {
            log.error("Error al leer ciudades", e);
        }
    }

    public static void leerEquipos(AVL equipos) {
        try (BufferedReader br = new BufferedReader(new FileReader(equipos_path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("E: ")) {
                    String contenido = linea.substring(3);
                    StringTokenizer st = new StringTokenizer(contenido, ";");
                    if (st.countTokens() == 3) {
                        String nombrePais = st.nextToken().trim();
                        String directorTecnico = st.nextToken().trim();
                        char grupo = st.nextToken().trim().charAt(0);

                        Equipo equipo = new Equipo(nombrePais, directorTecnico, grupo);
                        equipos.insertar(equipo);
                        log.info("Equipo cargado: " + equipo.toString());
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error al leer equipos", e);
        }
    }

    public static void leerResultadoPartido(MultiValueHashMap partidos, AVL equipos) {
        try (BufferedReader br = new BufferedReader(new FileReader(resultados_path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("P: ")) {
                    String contenido = linea.substring(3);
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

                        log.info("Resultado partido cargado: " + resultado.toString());

                        Equipo auxE1 = new Equipo(equipo1);
                        Equipo auxE2 = new Equipo(equipo2);
                        auxE1 = (Equipo) equipos.obtenerElemento(auxE1);
                        auxE2 = (Equipo) equipos.obtenerElemento(auxE2);

                        if (auxE1 != null && auxE2 != null) {
                            auxE1.setGolesAFavor(golesEquipo1);
                            auxE1.setGolesEnContra(golesEquipo2);
                            auxE2.setGolesAFavor(golesEquipo2);
                            auxE2.setGolesEnContra(golesEquipo1);

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
            log.error("Error al leer resultados de partidos", e);
        }
    }

    public static void leerRutaAerea(Grafo mapa) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutas_path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("R: ")) {
                    String contenido = linea.substring(3);
                    StringTokenizer st = new StringTokenizer(contenido, ";");
                    if (st.countTokens() == 3) {
                        String ciudadOrigen = st.nextToken().trim();
                        String ciudadDestino = st.nextToken().trim();
                        double tiempoVuelo = Double.parseDouble(st.nextToken().trim());

                        Ciudad auxO = new Ciudad(ciudadOrigen);
                        Ciudad auxD = new Ciudad(ciudadDestino);
                        RutaAerea rutaAerea = new RutaAerea(ciudadOrigen, ciudadDestino, tiempoVuelo);
                        log.info("Ruta aérea cargada: " + rutaAerea.toString());

                        mapa.insertarArco(auxO, auxD, tiempoVuelo);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error al leer rutas aéreas", e);
        }
    }

    public static void escribirCiudad(Ciudad ciudad) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ciudades_path, true))) {
            bw.write("C: " + ciudad.getNombre() + ";" + ciudad.getAlojamientoDisponible() + ";" + ciudad.getEsSede());
            bw.newLine();
            log.info("Ciudad escrita: " + ciudad.toString());
        } catch (IOException e) {
            log.error("Error al escribir ciudad", e);
        }
    }

    public static void escribirEquipo(Equipo equipo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(equipos_path, true))) {
            bw.write("E: " + equipo.getNombrePais() + ";" + equipo.getDirectorTecnico() + ";" + equipo.getGrupo());
            bw.newLine();
            log.info("Equipo escrito: " + equipo.toString());
        } catch (IOException e) {
            log.error("Error al escribir equipo", e);
        }
    }

    public static void escribirResultadoPartido(ResultadoPartido resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultados_path, true))) {
            bw.write("P: " + resultado.getClave().getEquipo1() + ";" + resultado.getClave().getEquipo2() + ";" + resultado.getRonda() + ";" +
                    resultado.getCiudadEvento() + ";" + resultado.getNombreEstadio() + ";" + resultado.getGolesEquipo1() + ";" +
                    resultado.getGolesEquipo2());
            bw.newLine();
            log.info("Resultado de partido escrito: " + resultado.toString());
        } catch (IOException e) {
            log.error("Error al escribir resultado de partido", e);
        }
    }

    public static void escribirRutaAerea(RutaAerea rutaAerea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutas_path, true))) {
            bw.write("R: " + rutaAerea.getCiudadOrigen() + ";" + rutaAerea.getCiudadDestino() + ";" + rutaAerea.getTiempoVuelo());
            bw.newLine();
            log.info("Ruta aérea escrita: " + rutaAerea.toString());
        } catch (IOException e) {
            log.error("Error al escribir ruta aérea", e);
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

    private static void eliminarEntrada(String rutaArchivo, String prefijo, String clave) {
        File archivoOriginal = new File(rutaArchivo);
        File archivoTemporal = new File(archivoOriginal.getAbsolutePath() + ".tmp");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.startsWith(prefijo) || !linea.contains(clave)) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }

            log.info("Entrada eliminada: " + clave);
        } catch (IOException e) {
            log.error("Error al eliminar entrada", e);
        }

        // Borrar el archivo original
        if (!archivoOriginal.delete()) {
            log.error("No se pudo borrar el archivo original", null);
            return;
        }

        // Renombrar el archivo temporal
        if (!archivoTemporal.renameTo(archivoOriginal)) {
            log.error("No se pudo renombrar el archivo temporal", null);
        }
    }
}
