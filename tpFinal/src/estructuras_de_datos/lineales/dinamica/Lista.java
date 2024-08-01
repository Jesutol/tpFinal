
package estructuras_de_datos.lineales.dinamica;

public class Lista {

	private Nodo cabecera;


	public Lista(){
		this.cabecera=null;			
	}





	public boolean insertar(Object unElemento, int pos) {
		boolean exito=true;

		if(pos<1||pos>longitud()+1) {
			exito=false;
		}else {

			if(pos==1) {

				this.cabecera=new Nodo(unElemento,this.cabecera);
			}else {
				Nodo aux=this.cabecera;
				int i=1;
				while(i<pos-1) {
					aux=aux.getEnlace();
					i++;

				}

				Nodo nuevo=new Nodo(unElemento,aux.getEnlace());
				aux.setEnlace(nuevo);



			}



		}

		return exito;

	}

	public boolean eliminar(int pos) {

		boolean exito;


		if((pos<1||pos>longitud())) {
			exito=false;
		}else {

			if(pos==1) {

				this.cabecera=this.cabecera.getEnlace();
				exito=true;
			}else {
				Nodo aux=this.cabecera;
				int i=1;
				while(i<pos-1) {
					aux=aux.getEnlace();
					i++;

				}


				aux.setEnlace(aux.getEnlace().getEnlace());
				exito=true;



			}



		}

		return exito;

	}
	public  int localizar(Object unElemento) {

		int i=1;
		int pos=-1;
		Nodo aux=this.cabecera;

		if(!esVacia()) {

			while((i<=longitud())&&pos==-1) {


				if(aux.getElemen()==unElemento) {
					pos=i;

				}else {

					aux=aux.getEnlace();
					i++;




				}

			}
		}
		return pos;



	}






	public Object recuperar(int pos) {

		int i=1;

		Object elemento;
		elemento=null;
		Nodo aux= this.cabecera;

		if(!esVacia()) {

			if(pos<1||pos>longitud()) {
				elemento=null;
			}else {

				while(i<pos){


					aux=aux.getEnlace();
					i++;


				}
				if(aux!=null)
					elemento=aux.getElemen();


			}
		}

		return elemento;
	}












	public void vaciar() {

		this.cabecera=null;



	}







	public int longitud() {

		int n=0;
		Nodo aux=this.cabecera;

		if(!esVacia()){

			while(aux!=null) {
				aux=aux.getEnlace();
				n++;

			}



		}




		return n;


	}




	public boolean esVacia() {

		return this.cabecera==null;

	}



	public Lista clone() {
		Lista clon=new Lista();
		if(!esVacia()) {

			Nodo ultimoC=new  Nodo(cabecera.getElemen(),null);
			clon.cabecera=ultimoC;
			Nodo aux=this.cabecera;
			aux=aux.getEnlace();
			while(aux!=null) {   

				Nodo nuevo=new Nodo(aux.getElemen(),null);
				ultimoC.setEnlace(nuevo);
				ultimoC=nuevo;
				aux=aux.getEnlace();




			}



		}






		return clon;
	}


	@Override
	public String toString() {
		String s="";
		
		if(esVacia()) {
			 s = "[lista vacia]";
			
		}else {
			
			 s = "[";

			Nodo aux = this.cabecera;


			if (aux != null) {
				while (aux != null) {
					s += aux.getElemen().toString();
					aux = aux.getEnlace();
					if (aux != null)
						s += ",";
				}
			}

			s += "]";
		}
	

		return s;
	}

	public  Lista localizarMulti(int num) {

		int i=1;
		Lista mul=new Lista();
		Nodo aux=this.cabecera;
		Nodo aux2=null;

	

			while(aux!=null) {


				if(i%num==0) {

					if(mul.cabecera==null) {
						mul.cabecera=new Nodo(aux.getElemen(),null);
						aux2=mul.cabecera;


					}else{

						Nodo nuevo=new Nodo(aux.getElemen(),null);
						aux2.setEnlace(nuevo);
						aux2=nuevo;
					}
					i++;
					aux=aux.getEnlace();



				}else {

					aux=aux.getEnlace();
					i++;




				}

			
		}
		return mul;



	}
	public void eliminaA(Object unE) {
		Nodo aux = this.cabecera;
		Nodo previo = null;

		while (aux != null) {
			if (aux.getElemen().equals(unE)) {
				if (previo == null) { 
					this.cabecera = aux.getEnlace();
				} else {
					previo.setEnlace(aux.getEnlace());
				}
			} else {
				previo = aux; 
			}
			aux = aux.getEnlace(); 
		}
	}
	
	public boolean verificaBalanceo() {
		boolean exito=true;
		Pila auxP=new Pila();
		Nodo aux=this.cabecera;
		while(aux!=null&&exito) {
			
			
			char ele=(char)aux.getElemen();
			//Se fija si encuentra un abridor y lo apila
			if(ele=='{'||ele=='['||ele=='(') {
				auxP.apilar(ele);
				
				
			}else if(ele=='}'||ele==']'||ele==')') {

				/*Lo hacemos porque si es igual a 
    				vacío nos puede dar error y 
				significa que el abridor no exite
				*/
				if(!auxP.esVacia()) {
				
				char a=(char)auxP.obtenerTope();	
				/* Compara que los elementos
    				tenga abridor y cerrador
				*/
				if((corresponde(a,ele))) {		
					auxP.desapilar();
				}else {
					exito=false;
					
				}
				
					
					
				
				}else {
					/* Si entro aqui y la pila es vacia
				significa que no hay abridor
				*/
					
					
					exito=false;
				}
					
			
				
				
			}
			
			
			
			aux=aux.getEnlace();
				
				
			
		}
		

				
		if(!auxP.esVacia()) {
			exito=false;
			
		}
		
		
		
		
		return exito;
		
		
	}
	private boolean corresponde(char a, char b) {
	    return (a == '{' && b == '}') ||
	           (a == '[' && b == ']') ||
	           (a == '(' && b == ')');
	}
	
	
	public boolean cambiaPos(int pos) {
		
		int i=1;
		boolean exito=true;
		if(pos<1||pos>longitud()) {
			exito=false;
		}else {
			
			if(pos==1) {
				
				Nodo temp=new Nodo(this.cabecera.getElemen(),null);
				this.cabecera=this.cabecera.getEnlace();
				Nodo aux=this.cabecera;
				
				
				int longitud=longitud();
				
				while(i<longitud-1) {
					
					aux=aux.getEnlace();
					i++;
					
					
				}
				Nodo nuevo=new Nodo(temp.getElemen(),aux.getEnlace());
				aux.setEnlace(nuevo);
				
			}else {
				int longitud=longitud();
				Nodo aux=this.cabecera;
				while(i<pos-1) {
					
					aux=aux.getEnlace();
					i++;
					
				}
				
				Nodo temp=new Nodo(aux.getEnlace().getElemen(),null);
				aux.setEnlace(aux.getEnlace().getEnlace());
				i=pos;
				
				
				while(i<longitud-1) {
					
					
					i++;
					aux=aux.getEnlace();
				}
				
				Nodo nuevo=new Nodo(temp.getElemen(),aux.getEnlace());
				aux.setEnlace(nuevo);
				
				
				
				
				
				
				
				
			}
		
		}
			
			
		
		return exito;
			
	}
	
	
	
	public Lista invierte() {
		Lista lista=new Lista();
		
		if(!esVacia()) {
			auxInvierte(this.cabecera,lista);
		}
		
		
		Nodo aux=this.cabecera;
		
		
		
		return lista;
		
	}
	private Lista auxInvierte(Nodo n, Lista list) {
	    while (n != null) {
	        // Crear un nuevo nodo con el elemento del nodo actual y enlazarlo con la cabecera actual
	        Nodo nuevoNodo = new Nodo(n.getElemen(), list.cabecera);
	        // Actualizar la cabecera de la lista para que apunte al nuevo nodo
	        list.cabecera = nuevoNodo;
	        
	        // Mover al siguiente nodo en la lista original
	        n = n.getEnlace();
	    }
	    
	    return list;
	}
	
	public void anterior(Object valor1,Object valor2) {
		
		if(this.cabecera!=null) {
			Nodo aux=this.cabecera;
			Nodo previo=null;
			
			while(aux!=null) {
				
				if(valor1.equals(aux.getElemen())) {
					Nodo nuevo=new Nodo(valor2,null);
					if(previo==null) {
						nuevo.setEnlace(this.cabecera);
						this.cabecera=nuevo;
						
					}else {
						nuevo.setEnlace(aux);
						previo.setEnlace(nuevo);
						
					}
					
					
				}
				
				previo=aux;
				aux=aux.getEnlace();
				
				
			}
			
			
		}
		
		
	}
	
		
		
		

	}
	
	
	



