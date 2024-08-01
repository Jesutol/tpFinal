package dominio;

import java.util.Objects;

public class ResultadoPartido {
	public static void main(String[] args) {
		
		
		
	}
	 
		private ClavePartido clave;
	    private String ronda;
	    private String ciudadEvento;
	    private String nombreEstadio;
	    private int golesEquipo1;
	    private int golesEquipo2;
	    //test
		public ResultadoPartido(String equipo1, String equipo2, String ronda, String ciudadEvento, String nombreEstadio,
				int golesEquipo1, int golesEquipo2) {
			
			if((equipo1.compareTo(equipo2))<0){
				this.clave=new ClavePartido(equipo1, equipo2);
				this.golesEquipo1 = golesEquipo1;
				this.golesEquipo2 = golesEquipo2;
				
			}else {
				
				this.clave=new ClavePartido(equipo2, equipo1);
				this.golesEquipo1 = golesEquipo2;
				this.golesEquipo2 = golesEquipo1;
			}
			
			this.ronda = ronda;
			this.ciudadEvento = ciudadEvento;
			this.nombreEstadio = nombreEstadio;
			
		}
		
		
		

		public ClavePartido getClave() {
			return clave;
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
			return "ResultadoPartido:"+ " ronda=" + ronda
					+ ", ciudadEvento=" + ciudadEvento + ", nombreEstadio=" + nombreEstadio + ", golesEquipo1="
					+ golesEquipo1 + ", golesEquipo2=" + golesEquipo2 + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(ronda);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ResultadoPartido other = (ResultadoPartido) obj;
			return Objects.equals(ronda, other.ronda);
		}
		
	    
		
	    
	

	    


		
	    
	    
}
