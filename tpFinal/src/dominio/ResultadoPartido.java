package dominio;

public class ResultadoPartido {
	   private String equipo1;
	    private String equipo2;
	    private String ronda;
	    private String ciudadEvento;
	    private String nombreEstadio;
	    private int golesEquipo1;
	    private int golesEquipo2;
	    //test
		public ResultadoPartido(String equipo1, String equipo2, String ronda, String ciudadEvento, String nombreEstadio,
				int golesEquipo1, int golesEquipo2) {
			this.equipo1 = equipo1;
			this.equipo2 = equipo2;
			this.ronda = ronda;
			this.ciudadEvento = ciudadEvento;
			this.nombreEstadio = nombreEstadio;
			this.golesEquipo1 = golesEquipo1;
			this.golesEquipo2 = golesEquipo2;
		}
		
		public String getEquipo1() {
			return equipo1;
		}
		public void setEquipo1(String equipo1) {
			this.equipo1 = equipo1;
		}
		public String getEquipo2() {
			return equipo2;
		}
		public void setEquipo2(String equipo2) {
			this.equipo2 = equipo2;
		}
		public String getRonda() {
			return ronda;
		}
		public void setRonda(String ronda) {
			this.ronda = ronda;
		}
		public String getCiudadEvento() {
			return ciudadEvento;
		}
		public void setCiudadEvento(String ciudadEvento) {
			this.ciudadEvento = ciudadEvento;
		}
		public String getNombreEstadio() {
			return nombreEstadio;
		}
		public void setNombreEstadio(String nombreEstadio) {
			this.nombreEstadio = nombreEstadio;
		}
		public int getGolesEquipo1() {
			return golesEquipo1;
		}
		public void setGolesEquipo1(int golesEquipo1) {
			this.golesEquipo1 = golesEquipo1;
		}
		public int getGolesEquipo2() {
			return golesEquipo2;
		}
		public void setGolesEquipo2(int golesEquipo2) {
			this.golesEquipo2 = golesEquipo2;
		}

		@Override
		public String toString() {
			return "ResultadoPartido [equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", ronda=" + ronda
					+ ", ciudadEvento=" + ciudadEvento + ", nombreEstadio=" + nombreEstadio + ", golesEquipo1="
					+ golesEquipo1 + ", golesEquipo2=" + golesEquipo2 + "]";
		}
	    
		
	    
	

	    


		
	    
	    
}
