package jerarquica.dinamica;
import lineales.dinamica.*;

public class ArbolBin {
	private NodoArbol raiz;

	public ArbolBin() {
		this.raiz = null;
	}

	public boolean insertar(Object elemento, Object elementoPadre, char lugar) {
		boolean exito = true;

		if (this.raiz == null) {
			// Si el árbol está vacío, el elemento insertado será la raíz
			this.raiz = new NodoArbol(elemento,null,null);
		} else {
			NodoArbol nodoPadre = obtenerNodo(this.raiz, elementoPadre);
			if (nodoPadre != null) {
				if (lugar == 'I' && nodoPadre.getIzq() == null) {
					nodoPadre.setIzq(new NodoArbol(elemento,null,null));
				} else if (lugar == 'D' && nodoPadre.getDer() == null) {
					nodoPadre.setDer(new NodoArbol(elemento,null,null));
				} else {
					exito = false; // Lugar inválido o nodo hijo ya existe
				}
			} else {
				exito = false; // No se encontró el nodo padre
			}
		}

		return exito;
	}



	private NodoArbol obtenerNodo(NodoArbol n ,Object buscado) {
		NodoArbol resultado=null;


		if(n!=null) {

			if(n.getElemen().equals(buscado)) {
				resultado=n;
			}else {

				resultado=obtenerNodo(n.getIzq(), buscado);

				if(resultado==null) {
					resultado=obtenerNodo(n.getDer(), buscado);


				}
			}



		}




		return resultado;
	}

	public boolean esVacio() {
		return this.raiz==null;

	}

	public int nivel(Object unElemento) {
		int unNivel=-1;
		if(!esVacio()) {

			unNivel=nivelAux(this.raiz,unElemento);
		}


		return unNivel;



	}

	private int nivelAux(NodoArbol n, Object unElemento) {
		int nivel=-1;
		int auxI ,auxD;

		if((n.getElemen().equals(unElemento))) {
			nivel=0;

		} else {

			if(n.getIzq()!=null){
				auxI=nivelAux(n.getIzq(), unElemento);
				if(auxI>-1) {
					nivel=auxI+1;

				}

			}
			if(n.getDer()!=null&& nivel==-1) {
				auxD=nivelAux(n.getDer(), unElemento);

				if(auxD>-1) {
					nivel=auxD+1;

				}

			}





		}

		return nivel;

	}




	public Object Padre(Object buscado) {

		Object padreE=null;
		if(!esVacio()) {
			if(!this.raiz.getElemen().equals(buscado)){
				padreE=padreAux(this.raiz,buscado);
			}
		}
		return padreE;




	}

	private Object padreAux(NodoArbol n,Object buscado) {

		Object padre=null;



		if(n.getIzq()!=null) {
			if(n.getIzq().getElemen().equals(buscado)){
				padre=n.getElemen();

			}else {
				padre=padreAux(n.getIzq(), buscado);

			}



		} if(n.getDer()!=null&&padre==null) {
			if(n.getDer().getElemen().equals(buscado)){
				padre=n.getElemen();

			}else {
				padre=padreAux(n.getDer(), buscado);

			}


		}


		return padre;

	}



	public int altura() {
		int altura=-1;
		if(!esVacio()) {
			altura=auxAltura(this.raiz);
		}
		return altura;

	}
	private int auxAltura(NodoArbol n){
		int altura=-1;
		int izq,der;

		if(n!=null) {
			izq=auxAltura(n.getIzq());
			der=auxAltura(n.getDer());
			altura=Math.max(izq, der)+1;


		} 

		return altura;
	}


	public void vaciar() {
		this.raiz=null;
	}


	public ArbolBin clone() {
		ArbolBin arbolC=new ArbolBin();
		if(!esVacio()) {

			NodoArbol raizC=new NodoArbol(raiz.getElemen(),null,null);

			arbolC.raiz=raizC;
			auxClone(this.raiz,raizC);


		}
		return arbolC;


	}
	private void auxClone(NodoArbol aux, NodoArbol copia) {
		NodoArbol temp;


		if(aux.getIzq()!=null) {
			temp=new NodoArbol(aux.getIzq().getElemen(),null,null);
			copia.setIzq(temp);
			auxClone(aux.getIzq(),copia.getIzq());



		}
		if(aux.getDer()!=null) {
			temp=new NodoArbol(aux.getDer().getElemen(),null,null);
			copia.setDer(temp);
			auxClone(aux.getDer(),copia.getDer());




		}
	}





	public String toString(){
		//retornamos un string 
		String retorno = "Arbol vacio";

		if(this.raiz != null){
			retorno = auxToString(this.raiz);
		}

		return retorno;
	}


	private String auxToString(NodoArbol raiz){
		String retorno = "";
		if(raiz != null){
			retorno = raiz.getElemen().toString()+":";

			//obtenemos los hijos de este sub arbol
			NodoArbol izquierdo = raiz.getIzq();
			NodoArbol derecho = raiz.getDer();
			//concatenamos lo que contengan esos hijos
			if( izquierdo != null){
				retorno = retorno + " HI:"+izquierdo.getElemen().toString();
			}else{
				retorno = retorno + " HI:-";
			}

			if( derecho != null){
				retorno = retorno + " HD:"+derecho.getElemen().toString();
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
	public Lista listaPreorder() {
		Lista list=new Lista();
		auxPreOrder(this.raiz,list);
		return list;


	}
	private void auxPreOrder(NodoArbol aux, Lista list) {

		if(aux!=null) {

			list.insertar(aux.getElemen(), list.longitud()+1);
			auxPreOrder(aux.getIzq(), list);
			auxPreOrder(aux.getDer(), list);

		}
	}

	public Lista listaInorder() {
		Lista list=new Lista();
		auxInOrder(raiz,list);
		return list;


	}
	private void auxInOrder(NodoArbol aux, Lista list) {

		if(aux!=null) {


			auxInOrder(aux.getIzq(), list);
			list.insertar(aux.getElemen(), list.longitud()+1);
			auxInOrder(aux.getDer(), list);

		}
	}

	public Lista listaPosOrder() {
		Lista list=new Lista();
		auxPosOrder(raiz,list);
		return list;


	}
	private void auxPosOrder(NodoArbol aux, Lista list) {

		if(aux!=null) {


			auxPosOrder(aux.getIzq(), list);
			auxPosOrder(aux.getDer(), list);
			list.insertar(aux.getElemen(), list.longitud()+1);

		}



	}

	public Lista listNivel() {
		Lista list=new Lista();
		Cola col=new Cola();


		if(!esVacio()) {
			col.poner(this.raiz);

			while(!col.esVacia()) {
				NodoArbol aux=(NodoArbol) col.obtenerFrente();
				list.insertar(aux.getElemen(), list.longitud()+1);
				col.sacar();
				if(aux.getIzq()!=null) {
					col.poner(aux.getIzq());



				}
				if(aux.getDer()!=null) {
					col.poner(aux.getDer());


				}

			}



		}


		return list;



	}  
	public boolean verificarPatron(Lista patron) {
		boolean verifico=false;

		if(!esVacio()) {
			int n=patron.longitud();
			verifico=auxPatron(this.raiz,patron,1,n);


		}
		return verifico;




	}


	private boolean auxPatron(NodoArbol n,Lista patron,int i,int longitud) {

		boolean verifica=false;
		if(n!=null) {



			if ((n.getElemen().equals(patron.recuperar(longitud)))&&(n.getIzq()==null&&n.getDer()==null)) {

				verifica=true;

			}

			else if(n.getElemen().equals(patron.recuperar(i))){
				verifica=auxPatron(n.getIzq(),patron,i+1,longitud);
				if(!verifica)
					verifica=auxPatron(n.getDer(),patron,i+1,longitud);


			}


		}

		return verifica;
	}





	public Lista frontera() {
		Lista list=new Lista();
		if(!esVacio()) {

			auxFrontera(list,this.raiz);

		}
		return list;

	}

	private void auxFrontera(Lista list,NodoArbol n) {

		if(n!=null) {

			if(n.getDer()==null&&n.getIzq()==null) {

				list.insertar(n.getElemen(), list.longitud()+1);

			}else {


				if(n.getIzq()!=null) {
					auxFrontera(list, n.getIzq());


				}
				if(n.getDer()!=null) {
					auxFrontera(list, n.getDer());


				}





			}

		}


	}


	public Lista ancestros(Object elemen) {
		Lista list=new Lista();
		if(!esVacio()&&!this.raiz.getElemen().equals(elemen)){

			auxAncest(this.raiz,list,elemen);
		}

		return list;



	}
	private boolean auxAncest(NodoArbol n,Lista list,Object ele) {
		boolean control =false;
		if(n!=null&&n.getElemen().equals(ele)) {
			control=true;
			list.insertar(n.getElemen(), 1);

		}else {

			if(n.getIzq()!=null) {

				control=auxAncest(n.getIzq(), list, ele);

			}

			if(!control&&n.getDer()!=null) {

				control=auxAncest(n.getDer(), list, ele);

			}
			if(control) {
				
				list.insertar(n.getElemen(), 1);
			}


		}



		return control;
	}
	public Lista des(Object elemen) {
		Lista list=new Lista();
		if(!esVacio()){
			NodoArbol n= obtenerNodo(this.raiz,elemen);
			auxPreOrder(n, list);
			
		}

		return list;



	}
	
	public boolean verificaRepetido(Object ele) {
		boolean p=false;
		int i=0;
		if(!esVacio())
		{
			i=auxR(this.raiz, ele);
			if(i>=2) {
				
				p=true;
			}
			
		}
		
		
		
		return p;
		
		
	}
	
	private int auxR(NodoArbol n,Object elemen) {
		int i=0;
		
		if(n!=null) {
			i=auxR(n.getIzq(), elemen)+i;
			
			if(n.getElemen().equals(elemen))
				i=i+1;
			

			if(n.getDer()!=null&&i<2) {
				
				i=auxR(n.getDer(), elemen)+i;
			}
			
		}
		
		
		return i;
		
		
		
	}
	
	
	


}


