package estructuras_de_datos.grafos;
import estructuras_de_datos.lineales.dinamica.*;

public class Grafo {
	private NodoVert inicio;

	public Grafo() {
		this.inicio=null;	}


	public boolean insertarVertice(Object elem) {


		boolean exito=false;
		NodoVert aux=ubicarVert(elem);
		if(aux==null) {

			this.inicio=new NodoVert(elem, this.inicio);
			exito=true;

		}

		return exito;
	}
	private NodoVert ubicarVert(Object buscado) {

		NodoVert aux=this.inicio;
		while(aux!=null&&!aux.getElemento().equals(buscado)) {

			aux=aux.getSigVertice();
		}

		return aux;

	}

	public boolean eliminarVert(Object elemento) {

		boolean exito=false;

		if(!esVacio()) {

			if(this.inicio.getElemento().equals(elemento)) {

				this.inicio=this.inicio.getSigVertice();
				exito=true;

			}else{
				NodoVert aux= encuentraPrevio(this.inicio, elemento);
				if(aux!=null) {

					aux.setSigVertice(aux.getSigVertice().getSigVertice());
					exito=true;
				}

			}

			if(exito) {
				NodoVert aux=this.inicio;
				while(aux!=null) {
					eliminaAdy(elemento,aux);
					aux=aux.getSigVertice();
				}

			}



		}
		return exito;
	}

	private void eliminaAdy(Object elem,NodoVert n) {

		NodoAd actualAdy=n.getPrimerAd();
		NodoAd previo=null;
		boolean borrado=false;
		while(actualAdy!=null&&!borrado) {
			if(actualAdy.getVertice().getElemento().equals(elem)) {
				if(previo==null) {
					n.setPrimerAd(actualAdy.getSigAd());
				}else {
					previo.setSigAd(actualAdy.getSigAd());

				}
				borrado=true;
			}

			previo=actualAdy;
			actualAdy=actualAdy.getSigAd();

		}


	}
	private NodoVert encuentraPrevio(NodoVert previo,Object elem) {
		boolean encontro=false;
		while(previo!=null&&!encontro) {

			if(previo.getSigVertice()!=null&&previo.getSigVertice().getElemento().equals(elem)) {

				encontro=true;
			}else {

				previo=previo.getSigVertice();
			}

		}

		return previo;

	}
	//Inserta un arco de un nodo origen a un destino
	public boolean insertarArco(Object origen,Object destino,Object etiqueta) {
		boolean exito=false;
		NodoVert nodoOrigen=ubicarVert(origen);
		NodoVert nodoDestino=ubicarVert(destino);

		if(nodoOrigen!=null&&nodoDestino!=null) {


			NodoAd nuevoArco = new NodoAd(nodoDestino, nodoOrigen.getPrimerAd(), etiqueta);
			nodoOrigen.setPrimerAd(nuevoArco);
			nuevoArco = new NodoAd(nodoOrigen, nodoDestino.getPrimerAd(), etiqueta);
			nodoDestino.setPrimerAd(nuevoArco);
			exito = true;


		}

		return exito;
	}


	public boolean eliminarArco(Object origen,Object destino ,Object etiqueta) {
		boolean exito=false;
		NodoVert nodoOrigen=ubicarVert(origen);
		NodoVert nodoDestino=ubicarVert(destino);

		if(nodoOrigen!=null&&nodoDestino!=null) {


			exito = auxEliminar(nodoOrigen, nodoDestino, etiqueta);
			if(exito) {
				
				
				auxEliminar(nodoDestino, nodoOrigen, etiqueta);
			}


		}




		return exito;
	}

	private boolean auxEliminar(NodoVert origen,NodoVert destino,Object etiqueta) {

		boolean exito=false;
		NodoAd auxD=origen.getPrimerAd();
		NodoAd previo=null;
		while(auxD!=null) {
			//Verifica que el elemento del vertice sea igual al del destino
			boolean verticeE=(auxD.getVertice().getElemento().equals(destino.getElemento()));
			//Verifica que la etiqueta sea la que se quiere eliminar
			boolean esEtiqueta=auxD.getEtiqueta().equals(etiqueta);
			if(verticeE&&esEtiqueta) {
				exito=true;
				if(previo==null) {
					origen.setPrimerAd(auxD.getSigAd());

				}else {
					previo.setSigAd(auxD.getSigAd());

				}


			}
			previo=auxD;
			auxD=auxD.getSigAd();




		}
		return exito;

	}
	public Lista listarEnProfundidad() {
		Lista visitados = new Lista();
		NodoVert aux = this.inicio;

		while (aux != null) {
			if (visitados.localizar(aux.getElemento()) < 0) {
				// Si el vértice no fue visitado aún, avanza en profundidad
				listarEnProfundidadAux(aux, visitados);
			}
			aux = aux.getSigVertice();
		}

		return visitados;
	}

	private void listarEnProfundidadAux(NodoVert n, Lista vis) {
		if (n != null) {
			// Marca al vértice n como visitado
			vis.insertar(n.getElemento(), vis.longitud() + 1);
			NodoAd ady = n.getPrimerAd();

			while (ady != null) {
				// Visita en profundidad los adyacentes de n aún no visitados
				if (vis.localizar(ady.getVertice().getElemento()) < 0) {
					listarEnProfundidadAux(ady.getVertice(), vis);
				}
				ady = ady.getSigAd();
			}
		}
	}


	public boolean esVacio() {

		return this.inicio==null;
	}
	public boolean existeArco(Object origen, Object destino) {
		NodoVert nodoOrigen = ubicarVert(origen);
		NodoVert nodoDestino = ubicarVert(destino);

		if (nodoOrigen != null && nodoDestino != null) {
			NodoAd arcoCandidato = nodoOrigen.getPrimerAd();

			// Buscar el arco desde nodoOrigen hacia nodoDestino
			while (arcoCandidato != null) {
				if (arcoCandidato.getVertice().getElemento().equals(destino)) {
					return true;
				}
				arcoCandidato = arcoCandidato.getSigAd();
			}
		}

		return false;
	}

	public Lista caminoMasCorto(Object origen, Object destino) {
		Lista lista = new Lista();

		if (!esVacio()) {
			// Obtiene los nodos
			NodoVert nodoOrigen = ubicarVert(origen);
			NodoVert nodoDestino = ubicarVert(destino);
			// Verifica que los dos nodos existan 
			if (nodoOrigen != null && nodoDestino != null) {
				Lista aux = new Lista();
				lista = auxCaminoMasCorto(nodoOrigen, destino, lista, aux);
			}
		}

		return lista;
	}

	private Lista auxCaminoMasCorto(NodoVert n, Object destino, Lista lista, Lista aux) {
		// Inserta el elemento actual en la lista auxiliar
		aux.insertar(n.getElemento(), aux.longitud() + 1);

		if (n.getElemento().equals(destino)) {
			// Si se alcanza el destino, compara y actualiza la lista mas corta
			if (lista.esVacia() || aux.longitud() < lista.longitud()) {
				lista = aux.clone();
			}
		} else {
			NodoAd arco = n.getPrimerAd();
			while (arco != null && (lista.esVacia() || aux.longitud() < lista.longitud())) {
				if (aux.localizar(arco.getVertice().getElemento()) < 0) {
					lista = auxCaminoMasCorto(arco.getVertice(), destino, lista, aux);
				}
				arco = arco.getSigAd();
			}
		}

		// Elimina el ultimo elemento insertado para retroceder en la recursion
		aux.eliminar(aux.longitud());
		return lista;
	}


	public Lista caminoMasCortoPorEtiqueta(Object origen, Object destino) {
	    Lista lista = new Lista();

	    if (!esVacio()) {
	        
	        NodoVert nodoOrigen = ubicarVert(origen);
	        NodoVert nodoDestino = ubicarVert(destino);
	        
	        if (nodoOrigen != null && nodoDestino != null) {
	            Lista aux = new Lista();
	            
	            double[] mejorSuma = new double[] { Double.MAX_VALUE };
	            lista = auxCaminoMasCortoPorEtiqueta(nodoOrigen, destino, aux, 0.0, mejorSuma, lista);
	        }
	    }

	    return lista;
	}

	private Lista auxCaminoMasCortoPorEtiqueta(NodoVert n, Object destino, Lista aux, double sumaActual, double[] mejorSuma, Lista mejorLista) {
	    // Inserta el elemento actual en la lista auxiliar
	    aux.insertar(n.getElemento(), aux.longitud() + 1);

	    if (n.getElemento().equals(destino)) {
	        // Si se alcanza el destino, compara y actualiza la lista con menor suma de etiquetas
	        if (sumaActual < mejorSuma[0]) {
	            mejorLista = aux.clone();
	            mejorSuma[0] = sumaActual;
	        }
	    } else {
	        NodoAd arco = n.getPrimerAd();
	        while (arco != null) {
	            double peso = (Double) arco.getEtiqueta();//Para el sistema copa America la etiqueta siempre sera double
	            if (aux.localizar(arco.getVertice().getElemento()) < 0 && sumaActual + peso < mejorSuma[0]) {
	                mejorLista = auxCaminoMasCortoPorEtiqueta(arco.getVertice(), destino, aux, sumaActual + peso, mejorSuma, mejorLista);
	            }
	            arco = arco.getSigAd();
	        }
	    }

	    // Elimina el último elemento insertado para retroceder en la recursión
	    aux.eliminar(aux.longitud());
	    return mejorLista;
	}



	public String toString() {
		String salida = "";

		if (this.inicio != null) {

			salida += "[ Vertices ] : [Etiqueta,Vertice] \n";

			NodoVert nodo_vertice = this.inicio;

			while (nodo_vertice != null) {
				salida += "[ " + nodo_vertice.getElemento() + " ] :";

				NodoAd nodo_adyacente = nodo_vertice.getPrimerAd();
				while (nodo_adyacente != null) {
					salida += "[ " + nodo_adyacente.getEtiqueta().toString() + " , "
							+ nodo_adyacente.getVertice().getElemento().toString() + " ] ";
					nodo_adyacente = nodo_adyacente.getSigAd();
				}
				salida += "\n";
				nodo_vertice = nodo_vertice.getSigVertice();
			}
		}
		return salida;
	}





}
