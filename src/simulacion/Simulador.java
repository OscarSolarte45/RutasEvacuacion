package simulacion;

import algoritmos.Dijkstra;
import estructuras.Cola;
import modelo.Edificio;
import modelo.NodoGrafo;
import modelo.Persona;
import servicios.EvacuacionService;

public class Simulador {

    private Cola<Persona> colaEvacuacion;
    private int turno;
    private EvacuacionService servicio;
    private Edificio edificio;
    
    public Simulador(Edificio edificio, EvacuacionService servicio) {
    this.colaEvacuacion = new Cola<>();
    this.turno = 0;
    this.edificio = edificio;
    this.servicio = servicio;
}

    public void agregarPersona(Persona persona) {
        colaEvacuacion.encolar(persona);
    }

    public void mostrarCola() {
        System.out.println("Personas esperando:");
        colaEvacuacion.mostrar();
    }
   public void avanzarTurno() {

    turno++;

    System.out.println("\n===== TURNO " + turno + " =====");

    if (colaEvacuacion.estaVacia()) {
        System.out.println("No hay personas esperando.");
        return;
    }

    Persona persona = colaEvacuacion.desencolar();

    if (persona.isEvacuado()) {
        System.out.println(persona.getNombre() + " ya estaba evacuado.");
        return;
    }

    if (!tieneRuta(persona)) {
        System.out.println("No hay una ruta disponible para "
                + persona.getNombre());

        colaEvacuacion.encolar(persona);
        return;
    }

    servicio.evacuarDesde(persona.getId());
}

    public int getTurno() {
        return turno;
    }

    private boolean tieneRuta(Persona persona) {

    NodoGrafo origen = edificio.buscarNodo(persona.getHabitacion());

    if (origen == null) {
        return false;
    }

        Dijkstra dijkstra = new Dijkstra(
            edificio.getNodos(),
            edificio.getCantidadNodos()
    );

    dijkstra.calcularRutas(origen);

    for (int i = 0; i < edificio.getCantidadNodos(); i++) {

        NodoGrafo nodo = edificio.getNodos()[i];

        if (nodo.getTipo().equalsIgnoreCase("Salida")
                && dijkstra.obtenerDistancia(nodo) != Double.MAX_VALUE) {
            return true;
        }
    }

    return false;
}
}