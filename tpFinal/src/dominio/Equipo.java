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
	


	public Equipo(String nombrePais) {
		this.nombrePais = nombrePais;
		this.directorTecnico = "";
		this.grupo = ' ';
		this.puntosGanados =0;
		this.golesAFavor = 0;
		this.golesEnContra=0;
		

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
	
	public int calculaDiferenciaDeGoles() {
		
		return golesAFavor-golesEnContra;
	}
	
	
	public int compareTo(Object otroObjeto) {
	    Equipo unEquipo = (Equipo) otroObjeto;
	    return this.nombrePais.compareTo(unEquipo.getNombrePais());
	}




	@Override
	public String toString() {
		return nombrePais;
	}





}
