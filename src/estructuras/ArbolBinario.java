package estructuras;

public class ArbolBinario<K extends Comparable<K>, V> {

    private NodoArbol<K, V> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean estaVacia() {
        return raiz == null;
    }

    public void insertar(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave no puede ser nula.");
        }
        raiz = insertarRec(raiz, clave, valor);
    }

    private NodoArbol<K, V> insertarRec(NodoArbol<K, V> actual, K clave, V valor) {
        if (actual == null) {
            return new NodoArbol<>(clave, valor);
        }

        int comparacion = clave.compareTo(actual.getClave());
        if (comparacion < 0) {
            actual.setIzquierdo(insertarRec(actual.getIzquierdo(), clave, valor));
        } else {
            actual.setDerecho(insertarRec(actual.getDerecho(), clave, valor));
        }

        return actual;
    }

    public V buscar(K clave) {
        if (clave == null) {
            return null;
        }
        return buscarRec(raiz, clave);
    }

    private V buscarRec(NodoArbol<K, V> actual, K clave) {
        if (actual == null) {
            return null;
        }

        int comparacion = clave.compareTo(actual.getClave());
        if (comparacion == 0) {
            return actual.getValor();
        } else if (comparacion < 0) {
            return buscarRec(actual.getIzquierdo(), clave);
        } else {
            return buscarRec(actual.getDerecho(), clave);
        }
    }

    public ListaEnlazada<V> obtenerInOrden() {
        ListaEnlazada<V> resultado = new ListaEnlazada<>();
        inOrdenRec(raiz, resultado);
        return resultado;
    }

    private void inOrdenRec(NodoArbol<K, V> actual, ListaEnlazada<V> resultado) {
        if (actual == null) {
            return;
        }
        inOrdenRec(actual.getIzquierdo(), resultado);
        resultado.agregar(actual.getValor());
        inOrdenRec(actual.getDerecho(), resultado);
    }

    public void imprimirInOrden() {
        imprimirInOrdenRec(raiz);
    }

    private void imprimirInOrdenRec(NodoArbol<K, V> actual) {
        if (actual == null) {
            return;
        }
        imprimirInOrdenRec(actual.getIzquierdo());
        System.out.println("Clave: " + actual.getClave() + " -> " + actual.getValor());
        imprimirInOrdenRec(actual.getDerecho());
    }
}
