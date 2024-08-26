package estructuras_de_datos.conjuntistas.dinamica;

public class Test {
	
	public static void main(String[] args) {
		menu();
	}

	
	public static void menu() {
		
		AVL a=new AVL();
		
		a.insertar("ARGENTINA");
		a.insertar("VENEZUELA");
	
		System.out.println(a.listarRango('A','V' ));
		
	}
}
