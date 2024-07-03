package estructuras_de_datos.conjuntistas.dinamica;


import estructuras_de_datos.lineales.dinamica.*;

public class ArbolBB {

	private NodoABB raiz;

	public ArbolBB() {

		this.raiz=null;
	}


	public boolean insertar(Comparable elem) {
		boolean exito=true;

		if(this.raiz==null) {

			this.raiz=new NodoABB(elem,null,null);

		}else {

			exito=auxInsertar(this.raiz,elem);
		}

		return exito;




	}

	private boolean auxInsertar(NodoABB n,Comparable elem) {

		boolean exito=true;

		if(elem.compareTo(n.getElem())==0) {

			exito=false;
		}else if(elem.compareTo(n.getElem())<0) {

			if(n.getHijoIzq()!=null) {
				exito=auxInsertar(n.getHijoIzq(), elem);

			}else {

				n.setHijoIzq(new NodoABB(elem,null,null));
			}
		}else {

			if(n.getHijoDer()!=null) {
				exito=auxInsertar(n.getHijoDer(), elem);

			}else {

				n.setHijoDer(new NodoABB(elem,null,null));
			}



		}
		return exito;
	}
	public boolean pertenece(Comparable elem) {
		boolean exito=false;

		if(!esVacio()) {

			exito=auxPertenece(this.raiz,elem);
		}

		return exito;




	}

	private boolean auxPertenece(NodoABB n,Comparable elem) {

		boolean exito=false;

		if(elem.compareTo(n.getElem())==0) {

			exito=true;
		}else if(elem.compareTo(n.getElem())<0) {

			if((n.getHijoIzq()!=null)&&!exito) {
				exito=auxPertenece(n.getHijoIzq(), elem);

			}
		}else {

			if((n.getHijoDer()!=null)&&!exito) {
				exito=auxPertenece(n.getHijoDer(), elem);

			}



		}
		return exito;
	}
	public boolean eliminar(Comparable elem) {
		boolean exito = false;
		if (!esVacio()) {
			exito = auxEliminar(this.raiz, elem);
		}
		return exito;
	}

	private boolean auxEliminar(NodoABB n, Comparable elem) {
		boolean exito = false;

		if (elem.compareTo(n.getElem()) == 0) {
			exito = aux2Eliminar(null, n, elem, ' ');
		} else if (elem.compareTo(n.getElem()) < 0) {
			if (n.getHijoIzq() != null) {
				exito = aux2Eliminar(n, n.getHijoIzq(), elem, 'I');
			}
		} else {
			if (n.getHijoDer() != null) {
				exito = aux2Eliminar(n, n.getHijoDer(), elem, 'D');
			}
		}

		return exito;
	}

	private boolean aux2Eliminar(NodoABB padre, NodoABB hijo, Comparable elem, char p) {
		boolean exito = false;

		if (elem.compareTo(hijo.getElem()) == 0) {
			exito = true;
			NodoABB remplazo = auxRemp(hijo);
			if (padre == null) {
				this.raiz = remplazo;
			} else {
				if (p == 'I') {
					padre.setHijoIzq(remplazo);
				} else if (p == 'D') {
					padre.setHijoDer(remplazo);
				}
			}
		} else if (elem.compareTo(hijo.getElem()) < 0) {
			if (hijo.getHijoIzq() != null) {
				exito = aux2Eliminar(hijo, hijo.getHijoIzq(), elem, 'I');
			}
		} else {
			if (hijo.getHijoDer() != null) {
				exito = aux2Eliminar(hijo, hijo.getHijoDer(), elem, 'D');
			}
		}

		return exito;
	}

	private NodoABB auxRemp(NodoABB n) {
		NodoABB nuevo = null;
		if (n.getHijoIzq() != null && n.getHijoDer() != null) {
			nuevo = caso3(n);
		} else if((n.getHijoIzq() != null || n.getHijoDer() != null)){
			nuevo = caso2(n);
		}
		return nuevo;
	}

	private NodoABB caso2(NodoABB n) {
		if (n.getHijoIzq() != null) {
			n= n.getHijoIzq();
		} else {
			n= n.getHijoDer();
		}
		return n;
	}

	private NodoABB caso3(NodoABB n) {
		NodoABB candidato = n.getHijoDer();
		NodoABB padreCandidato = n;

		// Encontrar el nodo candidato
		while (candidato.getHijoIzq() != null) {
			padreCandidato = candidato;
			candidato = candidato.getHijoIzq();
		}

		// Reemplazar el valor del nodo n por el valor del candidato
		n.setElem(candidato.getElem());

		// Eliminar el nodo candidato
		if (padreCandidato == n) {
			padreCandidato.setHijoDer(candidato.getHijoDer());
		} else {
			padreCandidato.setHijoIzq(candidato.getHijoDer());
		}

		return n;
	}
	//pepe

	public Lista lista() {
		Lista list=new Lista();
		if(!esVacio()) {
			auxLista(this.raiz,list);
		}

		return list;
	}
	private void  auxLista(NodoABB n,Lista list ){

		boolean exito=false;
		if(n!=null) {

			if(n.getHijoIzq()!=null) {
				auxLista(n.getHijoIzq(),list);
			}
			list.insertar(n.getElem(), list.longitud()+1);
			if(n.getHijoDer()!=null) {
				auxLista(n.getHijoDer(),list);

			}





		}




	}

	public Lista listarRango(Comparable min, Comparable max) {
		Lista list=new Lista();
		if(!esVacio()) {
			auxListaRango(this.raiz,list,min,max);
		}

		return list;
	}
	private void  auxListaRango(NodoABB n,Lista list ,Comparable min, Comparable max){

		boolean exito=false;
		if(n!=null) {

			if((n.getHijoIzq()!=null)&&((((Comparable) n.getElem()).compareTo(min) >=0))) {


				auxListaRango(n.getHijoIzq(),list,min,max);
			}

			if(((((Comparable) n.getElem()).compareTo(min) >=0)&&((Comparable) n.getElem()).compareTo(max) <=0)) {
				list.insertar(n.getElem(), list.longitud()+1);

			}


			if(n.getHijoDer()!=null&&((Comparable) n.getElem()).compareTo(max) <=0) {
				auxListaRango(n.getHijoDer(),list,min,max);

			}





		}




	}

	public Object minimo() {
		Object min=null;

		if(!esVacio()) {
			min=auxMinimo(this.raiz);

		}

		return min;

	}
	private Object auxMinimo(NodoABB n) {
		Object min=null;

		while(n.getHijoIzq()!=null) {
			n=n.getHijoIzq();
		}
		min=n.getElem();
		return min;

	}
	public Object maximo() {
		Object max=null;

		if(!esVacio()) {
			max=auxMax(this.raiz);

		}

		return max;

	}
	private Object auxMax(NodoABB n) {
		Object max=null;

		while(n.getHijoDer()!=null) {
			n=n.getHijoDer();
		}
		max=n.getElem();
		return max;

	}






	public boolean esVacio() {

		return this.raiz==null;
	}
	public void vaciar() {

		this.raiz=null;
	}
	public ArbolBB clone() {
		ArbolBB arbolC=new ArbolBB();
		if(!esVacio()) {

			NodoABB raizC=new NodoABB(raiz.getElem(),null,null);

			arbolC.raiz=raizC;
			auxClone(this.raiz,raizC);


		}
		return arbolC;


	}
	private void auxClone(NodoABB aux, NodoABB copia) {
		NodoABB temp;


		if(aux.getHijoIzq()!=null) {
			temp=new NodoABB(aux.getHijoIzq().getElem(),null,null);
			copia.setHijoIzq(temp);
			auxClone(aux.getHijoIzq(),copia.getHijoIzq());



		}
		if(aux.getHijoDer()!=null) {
			temp=new NodoABB(aux.getHijoDer().getElem(),null,null);
			copia.setHijoDer(temp);
			auxClone(aux.getHijoDer(),copia.getHijoDer());




		}
	}

	public ArbolBB clonaParteInvertida(Object elem) {
		ArbolBB clon=new ArbolBB();
		if(!esVacio()) {
			auxCloneParteInvertidad(raiz, clon, elem);
		}
		return clon;



	}


	private ArbolBB auxCloneParteInvertidad(NodoABB n,ArbolBB clon,Object elem) {

		NodoABB subArbol=buscaNodo(n, elem);
		if(subArbol!=null) {
			NodoABB raizC=new NodoABB(subArbol.getElem(),null,null);
			clon.raiz=raizC;
			auxCloneParteInvertida2(subArbol, raizC);

		}


		return clon;
	}
	private void auxCloneParteInvertida2(NodoABB aux, NodoABB copia) {
		NodoABB temp;


		if(aux.getHijoIzq()!=null) {
			temp=new NodoABB(aux.getHijoDer().getElem(),null,null);
			copia.setHijoIzq(temp);
			auxCloneParteInvertida2(aux.getHijoIzq(),copia.getHijoIzq());



		}
		if(aux.getHijoDer()!=null) {
			temp=new NodoABB(aux.getHijoIzq().getElem(),null,null);
			copia.setHijoDer(temp);
			auxCloneParteInvertida2(aux.getHijoDer(),copia.getHijoDer());




		}
	}

	private NodoABB buscaNodo(NodoABB n,Object elem) {

		NodoABB buscado=null;
		if(((Comparable) elem).compareTo(n.getElem())==0) {

			buscado=n;
		}else if(((Comparable) elem).compareTo(n.getElem())<0) {

			if((n.getHijoIzq()!=null)&&buscado==null) {
				buscado=buscaNodo(n.getHijoIzq(), elem);

			}
		}else {

			if((n.getHijoDer()!=null)&&buscado==null) {
				buscado=buscaNodo(n.getHijoDer(), elem);

			}



		}

		return buscado;
	}




	public String toString(){
		//retornamos un string 
		String retorno = "Arbol vacio";

		if(this.raiz != null){
			retorno = auxToString(this.raiz);
		}

		return retorno;
	}
	
	public boolean eliminarSA(Comparable elem) {
		boolean exito= false;
		
		if(!esVacio()) {
			exito=auxEliminarSa(this.raiz,null,elem);
			
			
		}
		
		return exito;
	}
	
	private boolean auxEliminarSa(NodoABB n,NodoABB padre,Comparable elem){
		
		boolean exito=false;

		if(elem.compareTo(n.getElem())==0) {
			
			if(padre!=null) {
				NodoABB temp=auxEliminarSA2(n);
				padre.setHijoIzq(temp);

				exito=true;
				
				
			}else {
				
				this.raiz=auxEliminarSA2(n);
				exito=true;
			}
			
			
		}else if(elem.compareTo(n.getElem())<0) {

			if((n.getHijoIzq()!=null)&&!exito) {
				exito=auxEliminarSa(n.getHijoIzq(), n, elem);

			}
		}else {

			if((n.getHijoDer()!=null)&&!exito) {
				exito=auxEliminarSa(n.getHijoDer(), n, elem);

			}



		}
		return exito;
		
	}
	
	private  NodoABB auxEliminarSA2(NodoABB n) {
		NodoABB temp=null;
		if(n.getHijoIzq()==null ) {
			n=n.getHijoDer();
			
			
		}else {
			temp=n;
			NodoABB hijo=n.getHijoIzq();
			while(hijo.getHijoIzq()!=null) {
				
				temp=hijo;
				hijo=hijo.getHijoIzq();
				
				
			}
			temp.setHijoIzq(hijo.getHijoDer());
		}
		
		
		return n;
	}


	private String auxToString(NodoABB raiz){
		String retorno = "";
		if(raiz != null){
			retorno = raiz.getElem().toString()+":";

			//obtenemos los hijos de este sub arbol
			NodoABB izquierdo = raiz.getHijoIzq();
			NodoABB derecho = raiz.getHijoDer();
			//concatenamos lo que contengan esos hijos
			if( izquierdo != null){
				retorno = retorno + " HI:"+izquierdo.getElem().toString();
			}else{
				retorno = retorno + " HI:-";
			}

			if( derecho != null){
				retorno = retorno + " HD:"+derecho.getElem().toString();
			}else{
				retorno = retorno + " HD:-";
			}
			//creamos el salto de linea para darle formato
			retorno = retorno + "\n";
			//y relizamos algun recorrido de arbol
			if(izquierdo != null){
				retorno = retorno + auxToString(izquierdo);
			}

			if(derecho != null){
				retorno = retorno + auxToString(derecho);
			}
		}
		//retornamos dicho string generado
		return retorno;
	}



}
