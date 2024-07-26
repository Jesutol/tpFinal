package estructuras_de_datos.grafos;

public class NodoVert {
	private Object elemento;
	private NodoVert sigVertice;
	private NodoAd primerAd;
	
	
	public NodoVert(Object elemento, NodoVert sigVertice) {
		this.elemento = elemento;
		this.sigVertice = sigVertice;
		this.primerAd = null;
	}
	
	
	
	public Object getElemento() {
		return elemento;
	}


	public void setElemento(Object elemento) {
		this.elemento = elemento;
	}


	public NodoVert getSigVertice() {
		return sigVertice;
	}


	public void setSigVertice(NodoVert sigVertice) {
		this.sigVertice = sigVertice;
	}


	public NodoAd getPrimerAd() {
		return primerAd;
	}


	public void setPrimerAd(NodoAd primerAd) {
		this.primerAd = primerAd;
	}




}
