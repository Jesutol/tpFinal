package estructuras_de_datos.jerarquica.dinamica;

public class NodoArbol {
	private Object elemen;
	private NodoArbol izq;
	private NodoArbol der;



	public NodoArbol(Object elemen, NodoArbol izq, NodoArbol der) {
		this.elemen = elemen;
		this.izq = izq;
		this.der = der;
	}



	public Object getElemen() {
		return elemen;
	}


	public void setElemen(Object elemen) {
		this.elemen = elemen;
	}


	public NodoArbol getIzq() {
		return izq;
	}


	public void setIzq(NodoArbol izq) {
		this.izq = izq;
	}


	public NodoArbol getDer() {
		return der;
	}


	public void setDer(NodoArbol der) {
		this.der = der;
	}





}