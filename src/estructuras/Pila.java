
package estructuras;

/**
 * Implementa una pila enlazada.
 * Sigue el principio LIFO: el ultimo en entrar es el primero en salir.
 *
 * @param <T> Tipo de dato almacenado.
 */
public class Pila<T> {

    private NodoLista<T> cima;
    private int tamaño;

    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void apilar(T dato) {

        NodoLista<T> nuevo = new NodoLista<>(dato);

        nuevo.setSiguiente(cima);
        cima = nuevo;

        tamaño++;
    }

    public T desapilar() {

        if (estaVacia()) {
            return null;
        }

        T dato = cima.getDato();

        cima = cima.getSiguiente();

        tamaño--;

        return dato;
    }

    public T verCima() {

        if (estaVacia()) {
            return null;
        }

        return cima.getDato();
    }

    public void mostrar() {

        NodoLista<T> actual = cima;

        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
