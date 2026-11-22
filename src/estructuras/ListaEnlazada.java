
package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEnlazada<T> implements Iterable<T> {

    private NodoLista<T> cabeza;
    private NodoLista<T> cola;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void agregar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }

        tamanio++;
    }

    public boolean buscar(T dato) {
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamanio--;

            if (cabeza == null) {
                cola = null;
            }

            return true;
        }

        NodoLista<T> actual = cabeza;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                NodoLista<T> eliminado = actual.getSiguiente();

                actual.setSiguiente(eliminado.getSiguiente());

                if (eliminado == cola) {
                    cola = actual;
                }

                tamanio--;
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("indice " + indice + ", tamanio " + tamanio);  //Excepción que indica que se intentó acceder a una posición inexistente.
        }
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public void vaciar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (actual == null) {
                    throw new NoSuchElementException();
                }
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }

    public void mostrar() {
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
