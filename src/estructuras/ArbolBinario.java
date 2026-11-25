package estructuras;

import modelo.Zona;

public class ArbolBinario {

    private class Nodo {

        private Zona zona;
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo(Zona zona) {
            this.zona = zona;
            this.izquierdo = null;
            this.derecho = null;
        }
        
    }

    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }
    public void insertar(Zona zona) {
    raiz = insertarRecursivo(raiz, zona);   
    }
    private Nodo insertarRecursivo(Nodo actual, Zona zona) {

    if (actual == null) {
        return new Nodo(zona);
    }

    if (zona.getNivelRiesgo() < actual.zona.getNivelRiesgo()) {

        actual.izquierdo = insertarRecursivo(actual.izquierdo, zona);

    } else {

        actual.derecho = insertarRecursivo(actual.derecho, zona);
    }

    return actual;
}
    
    
    public Zona buscar(int nivelRiesgo) {
    Nodo resultado = buscarRecursivo(raiz, nivelRiesgo);

    if (resultado == null) {
        return null;
    }

    return resultado.zona;
    }
    private Nodo buscarRecursivo(Nodo actual, int nivelRiesgo) {

    if (actual == null) {
        return null;
    }

    if (actual.zona.getNivelRiesgo() == nivelRiesgo) {
        return actual;
    }

    if (nivelRiesgo < actual.zona.getNivelRiesgo()) {
        return buscarRecursivo(actual.izquierdo, nivelRiesgo);
    }

    return buscarRecursivo(actual.derecho, nivelRiesgo);
    }
    
    
    public void mostrarInorden() {
    mostrarInordenRecursivo(raiz);
    }
    private void mostrarInordenRecursivo(Nodo actual) {

    if (actual == null) {
        return;
    }

    mostrarInordenRecursivo(actual.izquierdo);

    System.out.println(
        "ID: " + actual.zona.getId()
        + " | Zona: " + actual.zona.getNombre()
        + " | Riesgo: " + actual.zona.getNivelRiesgo()
    );

    mostrarInordenRecursivo(actual.derecho);
    }
    
}
