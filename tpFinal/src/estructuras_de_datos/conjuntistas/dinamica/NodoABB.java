package estructuras_de_datos.conjuntistas.dinamica;

public class NodoABB {
	private	Object elem;
	private NodoABB hijoIzq;
	private NodoABB hijoDer;

	public NodoABB(Object elem, NodoABB hijoIzq, NodoABB hijoDer) {
		
		this.elem = elem;
		this.hijoIzq = hijoIzq;
		this.hijoDer = hijoDer;
	}

	public Object getElem() {
		return elem;
	}

	public void setElem(Object elem) {
		this.elem = elem;
	}

	public NodoABB getHijoIzq() {
		return hijoIzq;
	}

	public void setHijoIzq(NodoABB hijoIzq) {
		this.hijoIzq = hijoIzq;
	}

	public NodoABB getHijoDer() {
		return hijoDer;
	}

	public void setHijoDer(NodoABB hijoDer) {
		this.hijoDer = hijoDer;
	}

}
