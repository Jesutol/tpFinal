package dominio;

public class RutaAerea {
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private int tiempoVuelo;
	
	
	public RutaAerea(Ciudad ciudadOrigen, Ciudad ciudadDestino, int tiempoVuelo) {
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.tiempoVuelo = tiempoVuelo;
	}


	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}


	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}


	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}


	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}


	public int getTiempoVuelo() {
		return tiempoVuelo;
	}


	public void setTiempoVuelo(int tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}
	
	

	
	
}
