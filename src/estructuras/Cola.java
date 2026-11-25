package estructuras;
public class Cola<T> {

    private NodoLista<T> frente;
    private NodoLista<T> ultimo;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.tamaño = 0;
import java.util.NoSuchElementException;

public class Cola<T> {

    private NodoLista<T> frente;
    private NodoLista<T> fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void encolar(T dato) {

    public int getTamanio() {
        return tamanio;
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
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía.");
        }

        T dato = frente.getDato();
        frente = frente.getSiguiente();
        tamanio--;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public T frente() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía.");
        }
        return frente.getDato();
    }

    public void mostrar() {
        NodoLista<T> actual = frente;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
