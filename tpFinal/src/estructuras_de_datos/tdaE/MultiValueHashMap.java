package estructuras_de_datos.tdaE;

import estructuras_de_datos.lineales.dinamica.Lista;
import java.util.HashMap;
import java.util.Map.Entry;

public class MultiValueHashMap<K, V> {
    private HashMap<K, Lista> map;

    // Constructor
    public MultiValueHashMap() {
        this.map = new HashMap<>();
    }

    // Insertar un valor en la lista asociada a una clave
    public boolean put(K key, V value) {
    	boolean exito=false;
        if (!map.containsKey(key)) {
            map.put(key, new Lista());
        }

        Lista lista = map.get(key);
        if (lista.localizar(value) == -1) { // Verifica si el valor ya existe en la lista EN  caso de existir no se insertara
            lista.insertar(value, 1);
            exito=true;
        }
        return exito;
    }

    // Obtener la lista de valores asociados a una clave
    public Lista get(K key) {
        return map.getOrDefault(key, new Lista());
    }

    // Eliminar un valor especifico de la lista asociada a una clave
    public boolean remove(K key, V value) {
    	boolean exito=false;
        if (map.containsKey(key)) {
            Lista values = map.get(key);
            int pos = values.localizar(value);
            if (pos != -1) {
                values.eliminar(pos);
                if (values.esVacia()) {
                    map.remove(key);
                }
                exito=true;
            }
        }
        return exito;
    }

    // Eliminar todos los valores asociados a una clave
    public Lista removeAll(K key) {
        return map.remove(key);
    }

    // Obtener el tamaño de la tabla hash
    public int size() {
        return map.size();
    }

    // Verificar si la tabla hash esta vacia
    public boolean isEmpty() {
        return map.isEmpty();
    }

    // Metodo toString para mostrar todo el contenido del MultiValueHashMap
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contenido de MultiValueHashMap:\n");

        for (Entry<K, Lista> entry : map.entrySet()) {
            sb.append("Clave: ").append(entry.getKey()).append(" -> Valores: ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
