package estructuras_de_datos.conjuntistas.dinamica;

import estructuras_de_datos.lineales.dinamica.*;

public class AVL {
	private NodoAvl raiz;

	public AVL(){
		this.raiz=null;		
	}




	public boolean insertar(Comparable elemento) {
		boolean insercion = false;
		if (raiz == null) {
			raiz = new NodoAvl(elemento, null, null);
			insercion = true;
		} else {
			NodoAvl nuevaRaiz = insertarAux(this.raiz, elemento);
			if (nuevaRaiz != null) {
				this.raiz = nuevaRaiz;
				insercion = true;
			}
		}
		return insercion;
	}

	private NodoAvl insertarAux(NodoAvl nodo, Comparable elemento) {
		NodoAvl resultado = null;
		int comparacion = elemento.compareTo(nodo.getElemento());

		if (comparacion != 0) { // Busco en las sub-ramas para insertar
			if (comparacion < 0) {
				if (nodo.getHijoIzq() != null) {
					resultado = insertarAux(nodo.getHijoIzq(), elemento);
					if (nodo.getHijoIzq() != resultado) {
						nodo.setHijoIzq(resultado);
						resultado = nodo;
					}
				} else {
					nodo.setHijoIzq(new NodoAvl(elemento, null, null));
				}
			} else {
				if (nodo.getHijoDer() != null) {
					resultado = insertarAux(nodo.getHijoDer(), elemento);
					if (nodo.getHijoDer() != resultado) {
						nodo.setHijoDer(resultado);
						resultado = nodo;
					}
				} else {
					nodo.setHijoDer(new NodoAvl(elemento, null, null));
				}
			}
			nodo.recalcularAltura();
			resultado = balancear(nodo);
		} else {
			// Element already exists, do not insert
			resultado = nodo;
		}
		return resultado;
	}



	public boolean pertenece(Comparable elem) {
		boolean exito=false;

		if(!esVacio()) {

			exito=auxPertenece(this.raiz,elem);

		}

		return exito;




	}

	private boolean auxPertenece( NodoAvl n,Comparable elem) {

		boolean exito=false;

		if(elem.compareTo(n.getElemento())==0) {

			exito=true;
		}else if(elem.compareTo(n.getElemento())<0) {

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
		boolean borrado = false;
		if (this.raiz != null) {
			borrado = auxEliminar(elem, this.raiz, null);
			if (borrado) {
				this.raiz = balancear(this.raiz);  // Balancear la raíz al final
			}
		}
		return borrado;
	}

	private boolean auxEliminar(Comparable elemento, NodoAvl actual, NodoAvl padre) {
		boolean borrado = false;
		if (actual != null) {
			int temp = elemento.compareTo(actual.getElemento());
			if (temp < 0) {
				borrado = auxEliminar(elemento, actual.getHijoIzq(), actual);
				if (borrado) {
					actual.setHijoIzq(balancear(actual.getHijoIzq()));
				}
			} else if (temp > 0) {
				borrado = auxEliminar(elemento, actual.getHijoDer(), actual);
				if (borrado) {
					actual.setHijoDer(balancear(actual.getHijoDer()));
				}
			} else {
				if (actual.getHijoIzq() == null && actual.getHijoDer() == null) {
					caso1(elemento, padre, actual);
				} else if (actual.getHijoIzq() == null || actual.getHijoDer() == null) {
					caso2(elemento, actual, padre);
				} else {
					caso3(actual, actual.getHijoDer(), actual);
				}
				borrado = true;
			}
			if (borrado && padre != null) {
				actual.recalcularAltura();
				padre.recalcularAltura();
			}
		}
		return borrado;
	}

	private void caso1(Comparable elemento, NodoAvl padre, NodoAvl actual) {
		if (padre == null) {
			this.raiz = null;
		} else {
			int temp = elemento.compareTo(padre.getElemento());
			if (temp < 0) {
				padre.setHijoIzq(null);
			} else {
				padre.setHijoDer(null);
			}
		}
	}

	private void caso2(Comparable elem, NodoAvl hijo, NodoAvl padre) {
		NodoAvl reemplazo = (hijo.getHijoIzq() != null) ? hijo.getHijoIzq() : hijo.getHijoDer();
		if (padre == null) {
			this.raiz = reemplazo;
		} else {
			int temp = elem.compareTo(padre.getElemento());
			if (temp < 0) {
				padre.setHijoIzq(reemplazo);
			} else {
				padre.setHijoDer(reemplazo);
			}
		}
	}

	private void caso3(NodoAvl actual, NodoAvl hijo, NodoAvl padre) {
		NodoAvl candidato_A;
		if (hijo.getHijoIzq() == null) {
			candidato_A = hijo;
		} else {
			candidato_A = hijo.getHijoIzq();
			while (candidato_A.getHijoDer() != null) {
				candidato_A = candidato_A.getHijoDer();
			}
		}

		actual.setElemento(candidato_A.getElemento());
		NodoAvl hijoDerecho = candidato_A.getHijoDer();
		if (hijo.getHijoIzq() == candidato_A) {
			hijo.setHijoIzq(hijoDerecho);
		} else {
			padre.setHijoDer(hijoDerecho);
		}
	}
	//pepe

	public Lista lista() {
		Lista list=new Lista();
		if(!esVacio()) {
			auxLista(this.raiz,list);
		}

		return list;
	}
	private void  auxLista( NodoAvl n,Lista list ){

		boolean exito=false;
		if(n!=null) {

			if(n.getHijoIzq()!=null) {
				auxLista(n.getHijoIzq(),list);
			}
			list.insertar(n.getElemento(), list.longitud()+1);
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
	private void  auxListaRango( NodoAvl n,Lista list ,Comparable min, Comparable max){

		boolean exito=false;
		if(n!=null) {

			if((n.getHijoIzq()!=null)&&((((Comparable) n.getElemento()).compareTo(min) >=0))) {


				auxListaRango(n.getHijoIzq(),list,min,max);
			}

			if(((((Comparable) n.getElemento()).compareTo(min) >=0)&&((Comparable) n.getElemento()).compareTo(max) <=0)) {
				list.insertar(n.getElemento(), list.longitud()+1);

			}


			if(n.getHijoDer()!=null&&((Comparable) n.getElemento()).compareTo(max) <=0) {
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
	private Object auxMinimo( NodoAvl n) {
		Object min=null;

		while(n.getHijoIzq()!=null) {
			n=n.getHijoIzq();
		}
		min=n.getElemento();
		return min;

	}
	public Object maximo() {
		Object max=null;

		if(!esVacio()) {
			max=auxMax(this.raiz);

		}

		return max;

	}
	private Object auxMax( NodoAvl n) {
		Object max=null;

		while(n.getHijoDer()!=null) {
			n=n.getHijoDer();
		}
		max=n.getElemento();
		return max;

	}






	public boolean esVacio() {

		return this.raiz==null;
	}
	public void vaciar() {

		this.raiz=null;
	}
	public AVL clone() {
		AVL arbolC=new AVL();
		if(!esVacio()) {

			NodoAvl raizC=new  NodoAvl(raiz.getElemento(),null,null);

			arbolC.raiz=raizC;
			auxClone(this.raiz,raizC);


		}
		return arbolC;


	}
	private void auxClone( NodoAvl aux,  NodoAvl copia) {
		NodoAvl temp;


		if(aux.getHijoIzq()!=null) {
			temp=new  NodoAvl(aux.getHijoIzq().getElemento(),null,null);
			copia.setHijoIzq(temp);
			auxClone(aux.getHijoIzq(),copia.getHijoIzq());



		}
		if(aux.getHijoDer()!=null) {
			temp=new  NodoAvl(aux.getHijoDer().getElemento(),null,null);
			copia.setHijoDer(temp);
			auxClone(aux.getHijoDer(),copia.getHijoDer());




		}
	}

	public AVL clonaParteInvertida(Object elem) {
		AVL clon=new AVL();
		if(!esVacio()) {
			auxCloneParteInvertidad(raiz, clon, elem);
		}
		return clon;



	}


	private AVL auxCloneParteInvertidad( NodoAvl n,AVL clon,Object elem) {

		NodoAvl subArbol=buscaNodo(n, elem);
		if(subArbol!=null) {
			NodoAvl raizC=new  NodoAvl(subArbol.getElemento(),null,null);
			clon.raiz=raizC;
			auxCloneParteInvertida2(subArbol, raizC);

		}


		return clon;
	}
	private void auxCloneParteInvertida2( NodoAvl aux,  NodoAvl copia) {
		NodoAvl temp;


		if(aux.getHijoIzq()!=null) {
			temp=new  NodoAvl(aux.getHijoDer().getElemento(),null,null);
			copia.setHijoIzq(temp);
			auxCloneParteInvertida2(aux.getHijoIzq(),copia.getHijoIzq());



		}
		if(aux.getHijoDer()!=null) {
			temp=new  NodoAvl(aux.getHijoIzq().getElemento(),null,null);
			copia.setHijoDer(temp);
			auxCloneParteInvertida2(aux.getHijoDer(),copia.getHijoDer());




		}
	}

	private  NodoAvl buscaNodo( NodoAvl n,Object elem) {

		NodoAvl buscado=null;
		if(((Comparable) elem).compareTo(n.getElemento())==0) {

			buscado=n;
		}else if(((Comparable) elem).compareTo(n.getElemento())<0) {

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




	private String auxToString(NodoAvl nodo) {
		String aux = "";
		if (nodo != null) {
			aux = nodo.getElemento().toString() + " Al: " + nodo.getAltura() + " :";

			NodoAvl hijoIzquierdo = nodo.getHijoIzq();
			NodoAvl hijoDerecho = nodo.getHijoDer();

			if (hijoIzquierdo != null) {
				aux = aux + " HI:" + hijoIzquierdo.getElemento().toString();
			} else {
				aux = aux + " HI:-";
			}

			if (hijoDerecho != null) {
				aux = aux + " HD:" + hijoDerecho.getElemento().toString();
			} else {
				aux = aux + " HD:-";
			}

			aux = aux + "\n";

			if (hijoIzquierdo != null) {
				aux = aux + auxToString(hijoIzquierdo);
			}

			if (hijoDerecho != null) {
				aux = aux + auxToString(hijoDerecho);
			}
		}
		return aux;
	}


	private int balance (NodoAvl n) {

		int alturaIzq= (n.getHijoIzq()!=null)? n.getHijoIzq().getAltura():-1;
		int alturaDer= (n.getHijoDer()!=null)? n.getHijoDer().getAltura():-1;

		return alturaIzq-alturaDer;

	}



	private NodoAvl balancear(NodoAvl nodo) {
		if (nodo != null) {
			nodo.recalcularAltura();
			int balance = balance(nodo);

			if (balance < -1) { 
				if (balance(nodo.getHijoDer()) > 0) { // Rotación doble derecha-izquierda
					NodoAvl aux = rotarDerecha(nodo.getHijoDer());
					nodo.setHijoDer(aux);
				}
				nodo = rotarIzquierda(nodo);
			} else if (balance > 1) { 
				if (balance(nodo.getHijoIzq()) < 0) { // Rotación doble izquierda-derecha
					NodoAvl aux = rotarIzquierda(nodo.getHijoIzq());
					nodo.setHijoIzq(aux);
				}
				nodo = rotarDerecha(nodo);
			}

			nodo.recalcularAltura();
		}
		return nodo;
	}


	private NodoAvl rotarIzquierda(NodoAvl n) {

		NodoAvl hijo=n.getHijoDer();
		NodoAvl temp=hijo.getHijoIzq();
		hijo.setHijoIzq(n);
		n.setHijoDer(temp);
		n.recalcularAltura();
		hijo.recalcularAltura();


		return hijo;


	}

	private NodoAvl rotarDerecha(NodoAvl n) {

		NodoAvl hijo=n.getHijoIzq();
		NodoAvl temp=hijo.getHijoDer();
		hijo.setHijoDer(n);
		n.setHijoIzq(temp);
		n.recalcularAltura();
		hijo.recalcularAltura();


		return hijo;
	}
	public Lista listaInOrder() {
        Lista lista = new Lista();
        inOrderAux(this.raiz, lista);
        return lista;
    }

    private void inOrderAux(NodoAvl nodo, Lista lista) {
        if (nodo != null) {
            inOrderAux(nodo.getHijoIzq(), lista);
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            inOrderAux(nodo.getHijoDer(), lista);
        }
    }
	   public Object obtenerElemento(Object elemento) {
	        NodoAvl nodo = buscaNodo(this.raiz, elemento);
	        return (nodo != null) ?  nodo.getElemento() : null;
	    }




}







