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
import estructuras.Cola;
import modelo.NodoGrafo;
import modelo.Arista;


public class BFS {
    private NodoGrafo[] visitados;
    private int cantidadVisitados;
    
    
    public BFS(int capacidad) {
        this.visitados = new NodoGrafo[capacidad];
        this.cantidadVisitados = 0;
    }

    private boolean fueVisitado(NodoGrafo nodo) {

    for (int i = 0; i < cantidadVisitados; i++) {
        if (visitados[i] == nodo) {
            return true;
        }
    }

    return false;
    }

    private void marcarVisitado(NodoGrafo nodo) {
     visitados[cantidadVisitados] = nodo;
     cantidadVisitados++;
    }
    public void recorrer(NodoGrafo inicio) {

    if (inicio == null) {
        System.out.println("El nodo inicial no existe.");
        return;
    }

    Cola<NodoGrafo> cola = new Cola<>();

    cantidadVisitados = 0;

    marcarVisitado(inicio);
    cola.encolar(inicio);

    while (!cola.estaVacia()) {

        NodoGrafo actual = cola.desencolar();

        System.out.println("Visitando: " + actual.getNombre());

        for (int i = 0; i < actual.getAristas().getTamaño(); i++) {

            Arista arista = actual.getAristas().obtener(i);

            NodoGrafo vecino = arista.getDestino();

            if (!arista.isBloqueada() && !fueVisitado(vecino)) {

                marcarVisitado(vecino);
                cola.encolar(vecino);
            }
        }
    }
    
}
}