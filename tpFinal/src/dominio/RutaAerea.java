package dominio;

public class RutaAerea {
	private String ciudadOrigen;
	private String ciudadDestino;
	private double tiempoVuelo;
	
	public RutaAerea(String ciudadOrigen, String ciudadDestino, double tiempoVuelo) {
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.tiempoVuelo = tiempoVuelo;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public double getTiempoVuelo() {
		return tiempoVuelo;
	}

	public void setTiempoVuelo(double tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}

	@Override
	public String toString() {
		return "RutaAerea [ciudadOrigen=" + ciudadOrigen + ", ciudadDestino=" + ciudadDestino + ", tiempoVuelo="
				+ tiempoVuelo + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
