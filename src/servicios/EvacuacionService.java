package servicios;

import modelo.Edificio;
import modelo.Persona;
import modelo.NodoGrafo;
import estructuras.TablaHash;
import algoritmos.Dijkstra;

public class EvacuacionService {

    private Edificio edificio;
    private TablaHash tablaPersonas;
    private Dijkstra ultimoDijkstra;
    
    public EvacuacionService(Edificio edificio, TablaHash tablaPersonas) {
        this.edificio = edificio;
        this.tablaPersonas = tablaPersonas;
    }
    private boolean esSalida(NodoGrafo nodo) {
    return nodo.getTipo().equalsIgnoreCase("Salida");
    }
    private NodoGrafo buscarSalidaMasCercana(NodoGrafo origen) {

    ultimoDijkstra = new Dijkstra(
        edificio.getNodos(),
        edificio.getCantidadNodos()
);

    ultimoDijkstra.calcularRutas(origen);

    NodoGrafo mejorSalida = null;
    double menorDistancia = Double.MAX_VALUE;

    for (int i = 0; i < edificio.getCantidadNodos(); i++) {

        NodoGrafo nodo = edificio.getNodos()[i];

        if (esSalida(nodo)) {

            double distancia = ultimoDijkstra.obtenerDistancia(nodo);

            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                mejorSalida = nodo;
            }
        }
    }

    return mejorSalida;
}
    public void evacuarDesde(String idPersona) {

    Persona persona = tablaPersonas.buscar(idPersona);

    if (persona == null) {
        System.out.println("La persona no esta registrada.");
        return;
    }

    if (persona.isEvacuado()) {
        System.out.println("Esta persona ya fue evacuada.");
        return;
    }

    NodoGrafo origen = edificio.buscarNodo(persona.getHabitacion());

    if (origen == null) {
        System.out.println("No se encontro la ubicacion de la persona.");
        return;
    }

    NodoGrafo salida = buscarSalidaMasCercana(origen);

    if (salida == null) {
        System.out.println("No hay una salida disponible.");
        return;
    }

    System.out.println("Persona: " + persona.getNombre());
    System.out.println("Ubicacion actual: " + origen.getNombre());
    System.out.println("Salida seleccionada: " + salida.getNombre());
    System.out.println("\nInstrucciones de evacuacion:");
    ultimoDijkstra.mostrarRuta(salida);
    persona.evacuar();
    System.out.println("\n" + persona.getNombre() + " ha sido evacuado correctamente.");
}
    
    
}