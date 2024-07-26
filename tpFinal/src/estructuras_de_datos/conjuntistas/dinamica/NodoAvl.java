package estructuras_de_datos.conjuntistas.dinamica;

public class NodoAvl {
	private Object elemento;
	private int altura;
	private NodoAvl hijoIzq;
	private NodoAvl hijoDer;
	
	
	
	public NodoAvl(Object object,  NodoAvl hijoIzq, NodoAvl hijoDer) {
		this.elemento = object;
		this.altura =0;
		this.hijoIzq = hijoIzq;
		this.hijoDer = hijoDer;
	}



	public Object getElemento() {
		return elemento;
	}



	public void setElemento(Object object) {
		this.elemento = object;
	}



	public int getAltura() {
		return altura;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public NodoAvl getHijoIzq() {
		return hijoIzq;
	}



	public void setHijoIzq(NodoAvl hijoIzq) {
		this.hijoIzq = hijoIzq;
	}



	public NodoAvl getHijoDer() {
		return hijoDer;
	}
	public  void recalcularAltura() {
		
		int alturaI= (hijoIzq==null)? -1:hijoIzq.getAltura();
		int alturaD= (hijoDer==null)? -1:hijoDer.getAltura();
		altura= 1+Math.max(alturaI, alturaD);
		
	}



	public void setHijoDer(NodoAvl hijoDer) {
		this.hijoDer = hijoDer;
	}
	
	

}
