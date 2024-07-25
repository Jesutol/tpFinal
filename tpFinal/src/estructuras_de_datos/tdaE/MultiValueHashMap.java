package estructuras_de_datos.tdaE;
import estructuras_de_datos.lineales.dinamica.*;
import java.util.HashMap;

public class MultiValueHashMap<K, V> {
    private HashMap<K, Lista> map;

    // Constructor
    public MultiValueHashMap() {
        this.map = new HashMap<>();
    }

    // Insertar un valor en la lista asociada a una clave
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new Lista());
        }
        map.get(key).insertar(value, map.get(key).longitud() + 1);
    }

    // Obtener la lista de valores asociados a una clave
    public Lista get(K key) {
        return map.getOrDefault(key, new Lista());
    }

    // Eliminar un valor específico de la lista asociada a una clave
    public boolean remove(K key, V value) {
        if (map.containsKey(key)) {
            Lista values = map.get(key);
            int pos = values.localizar(value);
            if (pos != -1) {
                values.eliminar(pos);
                if (values.esVacia()) {
                    map.remove(key);
                }
                return true;
            }
        }
        return false;
    }

    // Eliminar todos los valores asociados a una clave
    public Lista removeAll(K key) {
        return map.remove(key);
    }

    // Obtener el tamaño de la tabla hash
    public int size() {
        return map.size();
    }

    // Verificar si la tabla hash está vacía
    public boolean isEmpty() {
        return map.isEmpty();
    }

   
}

