
package estructuras;
public class ListaEnlazada<T> {

    private NodoLista<T> cabeza;
    private NodoLista<T> cola;
    private int tamaño;

    public ListaEnlazada() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamaño() {
        return tamaño;
    }
    public T obtener(int indice) {

    if (indice < 0 || indice >= tamaño) {
        return null;
    }

    NodoLista<T> actual = cabeza;

    for (int i = 0; i < indice; i++) {
        actual = actual.getSiguiente();
    }

    return actual.getDato();
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

        tamaño++;
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
            tamaño--;

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

                tamaño--;
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public void mostrar() {
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
