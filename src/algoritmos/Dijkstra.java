package algoritmos;
/*
 COMPARACION BFS Y DIJKSTRA

 BFS:
 Encuentra recorridos considerando la cantidad de conexiones.
 No tiene en cuenta el peso de las aristas.

 DIJKSTRA:
 Encuentra el camino de menor costo.
 Tiene en cuenta el peso de cada conexión.
*/
import estructuras.ListaEnlazada;
import modelo.NodoGrafo;
import estructuras.Pila;
import modelo.Arista;

public class Dijkstra {
    private NodoGrafo[] nodos;
    private double[] distancias;
    private boolean[] visitados;
    private NodoGrafo[] anteriores;
    private int cantidadNodos;

    public Dijkstra(NodoGrafo[] nodos, int cantidadNodos) {

    this.nodos = nodos;
    this.cantidadNodos = cantidadNodos;

    this.distancias = new double[cantidadNodos];
    this.visitados = new boolean[cantidadNodos];
    this.anteriores = new NodoGrafo[cantidadNodos];
}
    private int obtenerIndice(NodoGrafo nodo) {

    for (int i = 0; i < cantidadNodos; i++) {

        if (nodos[i] == nodo) {
            return i;
        }
    }

    return -1;
}
    private int obtenerMenorDistancia() {

    double menor = Double.MAX_VALUE;
    int indiceMenor = -1;

    for (int i = 0; i < cantidadNodos; i++) {

        if (!visitados[i] && distancias[i] < menor) {
            menor = distancias[i];
            indiceMenor = i;
        }
    }

    return indiceMenor;
}
    public void calcularRutas(NodoGrafo inicio) {

    int indiceInicio = obtenerIndice(inicio);

    if (indiceInicio == -1) {
        System.out.println("El nodo inicial no pertenece al edificio.");
        return;
    }

    for (int i = 0; i < cantidadNodos; i++) {
        distancias[i] = Double.MAX_VALUE;
        visitados[i] = false;
        anteriores[i] = null;
    }

    distancias[indiceInicio] = 0;

    for (int i = 0; i < cantidadNodos; i++) {

        int actual = obtenerMenorDistancia();

        if (actual == -1) {
            break;
        }

        visitados[actual] = true;

        NodoGrafo nodoActual = nodos[actual];

        for (int j = 0; j < nodoActual.getAristas().getTamaño(); j++) {

            Arista arista = nodoActual.getAristas().obtener(j);

            if (arista.isBloqueada()) {
                continue;
            }

            int indiceVecino = obtenerIndice(arista.getDestino());

            if (indiceVecino == -1 || visitados[indiceVecino]) {
                continue;
            }

            double nuevaDistancia = distancias[actual] + arista.getPeso();

            if (nuevaDistancia < distancias[indiceVecino]) {

                distancias[indiceVecino] = nuevaDistancia;
                anteriores[indiceVecino] = nodoActual;
            }
        }
    }
}
    public void mostrarDistancias() {

    for (int i = 0; i < cantidadNodos; i++) {

        System.out.print(nodos[i].getNombre() + ": ");

        if (distancias[i] == Double.MAX_VALUE) {
            System.out.println("Inalcanzable");
        } else {
            System.out.println(distancias[i]);
        }
    }
}
    public void mostrarRuta(NodoGrafo destino) {

    int indiceDestino = obtenerIndice(destino);

    if (indiceDestino == -1 || distancias[indiceDestino] == Double.MAX_VALUE) {
        System.out.println("No existe una ruta disponible.");
        return;
    }

    Pila<NodoGrafo> ruta = new Pila<>();

    NodoGrafo actual = destino;

    while (actual != null) {
        ruta.apilar(actual);

        int indice = obtenerIndice(actual);
        actual = anteriores[indice];
    }

    System.out.println("Ruta encontrada:");

    while (!ruta.estaVacia()) {
        NodoGrafo nodo = ruta.desapilar();

        System.out.println(" -> " + nodo.getNombre());
    }

    System.out.println("Costo total: " + distancias[indiceDestino]);
}
    // Devuelve la ruta (en orden, de origen a destino) o null si no hay camino.
    public ListaEnlazada<NodoGrafo> obtenerRuta(NodoGrafo destino) {

    int indiceDestino = obtenerIndice(destino);

    if (indiceDestino == -1 || distancias[indiceDestino] == Double.MAX_VALUE) {
        return null;
    }

    Pila<NodoGrafo> pila = new Pila<>();
    NodoGrafo actual = destino;

    while (actual != null) {
        pila.apilar(actual);
        actual = anteriores[obtenerIndice(actual)];
    }

    ListaEnlazada<NodoGrafo> ruta = new ListaEnlazada<>();

    while (!pila.estaVacia()) {
        ruta.agregar(pila.desapilar());
    }

    return ruta;
}
    public double obtenerDistancia(NodoGrafo destino) {

    int indice = obtenerIndice(destino);

    if (indice == -1) {
        return Double.MAX_VALUE;
    }

    return distancias[indice];
}
}