package estructuras_de_datos.lineales.dinamica;

public class Cola {
	private Nodo frente;
	private Nodo fin;


	public Cola() {

		this.frente=null;
		this.fin=null;



	}

	public boolean poner(Object unElemento) {

		

		if(esVacia()) {
			Nodo nodo=new Nodo(unElemento,null);

			this.fin=nodo;
			this.frente=nodo;



		}else {
			Nodo nodo=new Nodo(unElemento,null);
			this.fin.setEnlace(nodo);
			this.fin=nodo;




		}


		return true;




	}

	public boolean sacar() {
	    boolean exito = false;

	    if (frente != null) {
	        exito = true;
	        frente = frente.getEnlace();
	        if (frente == null) {
	            fin = null;
	        }
	    }

	    return exito;
	}


	public Object obtenerFrente() {




		return (frente!=null)? frente.getElemen():null;


	}

	public boolean esVacia()
	{
		return frente==null;




	}

	public void vaciar() {


		this.frente=null;
		this.fin=null;

	}

	public Cola clone() {
		Cola colaC=new Cola();
		if(!esVacia()) {
			Nodo ultimoC=new Nodo(frente.getElemen(),null);

			colaC.frente=ultimoC;
			Nodo aux=frente.getEnlace();

			while(aux!=null) {

				Nodo nuevoN=new Nodo(aux.getElemen(),null);
				ultimoC.setEnlace(nuevoN);
				ultimoC=nuevoN;
				aux=aux.getEnlace();


			}
			colaC.fin=ultimoC;


		}





		return colaC;




	}
	@Override
	public String toString() {
		String s = "[";

		Nodo aux = frente;

		if (aux != null) {
			while (aux != null) {
				s += aux.getElemen();
				aux = aux.getEnlace();
				if (aux != null)
					s += ",";
			}
		}

		s += "]";

		return s;
	}











}
