package estructuras;
public class Cola<T> {

    private NodoLista<T> frente;
    private NodoLista<T> ultimo;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void encolar(T dato) {

        NodoLista<T> nuevo = new NodoLista<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        tamaño++;
    }

    public T desencolar() {

        if (estaVacia()) {
            return null;
        }

        T dato = frente.getDato();

        frente = frente.getSiguiente();

        if (frente == null) {
            ultimo = null;
        }

        tamaño--;

        return dato;
    }

    public T verFrente() {

        if (estaVacia()) {
            return null;
        }

        return frente.getDato();
    }
    public void mostrar() {

    NodoLista<T> actual = frente;

    if (estaVacia()) {
        System.out.println("La cola esta vacia.");
        return;
    }

    while (actual != null) {
        System.out.println(actual.getDato());
        actual = actual.getSiguiente();
    }
}
}
