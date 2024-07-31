package dominio;

public class Equipo implements Comparable {
	private String nombrePais;
	private String directorTecnico;
	private char grupo;
	private int puntosGanados;
	private int golesAFavor;
	private int golesEnContra;


	public Equipo(String nombrePais, String directorTecnico, char grupo) {
		this.nombrePais = nombrePais;
		this.directorTecnico = directorTecnico;
		this.grupo = grupo;
		this.puntosGanados = 0;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
	}


	public String getNombrePais() {
		return nombrePais;
	}


	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}


	public String getDirectorTecnico() {
		return directorTecnico;
	}


	public void setDirectorTecnico(String directorTecnico) {
		this.directorTecnico = directorTecnico;
	}


	public char getGrupo() {
		return grupo;
	}


	public void setGrupo(char grupo) {
		this.grupo = grupo;
	}


	public int getPuntosGanados() {
		return puntosGanados;
	}


	public void setPuntosGanados(int puntosGanados) {
		this.puntosGanados = this.puntosGanados+puntosGanados;
	}


	public int getGolesAFavor() {
		return golesAFavor;
	}


	public void setGolesAFavor(int golesAFavor) {
		this.golesAFavor = this.golesAFavor+golesAFavor;
	}


	public int getGolesEnContra() {
		return golesEnContra;
	}


	public void setGolesEnContra(int golesEnContra) {
		this.golesEnContra = this.golesEnContra+golesEnContra;
	}	
	@Override

	public int compareTo(Object unEquipo) {
		if (unEquipo instanceof Equipo) {
			Equipo equipo = (Equipo) unEquipo;
			return this.nombrePais.compareTo(equipo.getNombrePais());
		} else {
			// Manejo de la situación en la que el objeto no es de tipo Equipo
			throw new IllegalArgumentException("El objeto proporcionado no es una instancia de la clase Equipo.");
		}
	}



	@Override
	public String toString() {
		return "Equipo:"+nombrePais;
	}





}
