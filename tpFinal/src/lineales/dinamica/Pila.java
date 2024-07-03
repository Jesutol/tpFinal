package lineales.dinamica;

public class Pila {
	private Nodo tope;

	public Pila() {
		this.tope=null;





	}


	public boolean apilar(Object nuevoEle) {
		
		Nodo nuevo=new Nodo(nuevoEle,this.tope);
		this.tope=nuevo;

	





		return true;

	}


	public boolean desapilar() {
		boolean exito;

		if(tope==null) {
			exito=false;



		}else {
			exito=true;
			tope=tope.getEnlace();

		}





		return exito;

	}
	public boolean esVacia() {




		return (tope==null);

	}


	public void vaciar() {
		tope = null;
	}
	
	public Object obtenerTope(){
		
		
			
												
		return (tope!=null)? tope.getElemen():null;
	}
	
	


	public Pila clone() {
		Pila pilaC=new Pila();

		if(tope!=null) {

			Nodo nodoActual=this.tope;

			Nodo ultimoC=new Nodo(nodoActual.getElemen(),null);

			pilaC.tope=ultimoC;
			while(nodoActual.getEnlace()!=null) {
				nodoActual=nodoActual.getEnlace();
				Nodo nuevoNodo=new Nodo(nodoActual.getElemen(),null);
				ultimoC.setEnlace(nuevoNodo);
				ultimoC=nuevoNodo;


			}




		}





		return pilaC;

	}
	
	public String toString() {
		
		String s="]";
		
		Nodo aux=tope;
		
		if(aux!=null) {
			
			while(aux!=null) {
				
				s+=aux.getElemen();
				aux=aux.getEnlace();
				if(aux!=null)
					s=","+s;
					
				
					
					
					
				}
			
			
		}
		
	
		s="["+s;
		
		return s;
		
	}



}
