package estructuras_de_datos.grafos;

public class NodoAd {
	private NodoVert vertice;
	private NodoAd sigAd;
	private Object Etiqueta;
	public NodoAd(NodoVert vertice, NodoAd sigAd, Object etiqueta) {
		this.vertice = vertice;
		this.sigAd = sigAd;
		Etiqueta = etiqueta;
	}
	public NodoVert getVertice() {
		return vertice;
	}
	public void setVertice(NodoVert vertice) {
		this.vertice = vertice;
	}
	public NodoAd getSigAd() {
		return sigAd;
	}
	public void setSigAd(NodoAd sigAd) {
		this.sigAd = sigAd;
	}
	public Object getEtiqueta() {
		return Etiqueta;
	}
	public void setEtiqueta(Object etiqueta) {
		Etiqueta = etiqueta;
	}

	
	
}
