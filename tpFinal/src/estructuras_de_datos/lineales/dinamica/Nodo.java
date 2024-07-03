package estructuras_de_datos.lineales.dinamica;

public class Nodo {
	private Object elemen;
	private Nodo enlace;



	//Constructor
	public Nodo (Object elem,Nodo enlace){

		this.elemen=elem;
		this.enlace=enlace;



	}



	public Object getElemen() {
		return elemen;
	}



	public void setElemen(Object elemen) {
		this.elemen = elemen;
	}



	public Nodo getEnlace() {
		return enlace;
	}



	public void setEnlace(Nodo enlace) {
		this.enlace = enlace;
	}



}
