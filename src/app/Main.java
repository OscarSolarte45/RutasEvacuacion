
package app;

import estructuras.ArbolBinario;
import modelo.Zona;
import estructuras.TablaHash;
import modelo.Persona;
import estructuras.Cola;
import estructuras.Pila;
import modelo.Edificio;
import modelo.NodoGrafo;
import algoritmos.BFS;
import algoritmos.Dijkstra;
import servicios.EvacuacionService;
import servicios.GestionOcupantes;
import servicios.GestionRiesgos;
import simulacion.Simulador;


public class Main {

    public static void main(String[] args) {

    TablaHash tabla = new TablaHash(10);

    Persona p1 = new Persona("A", "Carlos", 2, "HAB_201");
    Persona p2 = new Persona("K", "Maria", 1, "HAB_101");
    Persona p3 = new Persona("U", "Juan", 3, "HAB_301");

    tabla.insertar(p1);
    tabla.insertar(p2);
    tabla.insertar(p3);

    System.out.println("Personas registradas: " + tabla.getCantidad());
    System.out.println("\nListado de personas:");
    tabla.mostrarTodos();

    System.out.println("\nEliminando a Maria...");
    tabla.eliminar("K");

    System.out.println("Buscando a Juan...");
    Persona encontrada = tabla.buscar("U");

    if (encontrada != null) {
        System.out.println("Encontrado: " + encontrada.getNombre());
    } else {
        System.out.println("No encontrado.");
    }

    System.out.println("\nBuscando a Maria...");
    System.out.println(tabla.buscar("K"));

    System.out.println("\nInsertando una nueva persona con ID K...");
    Persona p4 = new Persona("K", "Luisa", 1, "HAB_102");
    tabla.insertar(p4);

    System.out.println("Nueva persona: " + tabla.buscar("K").getNombre());
    System.out.println("Personas registradas: " + tabla.getCantidad());
    System.out.println("\nListado de personas:");
    tabla.mostrarTodos();
    System.out.println("\n--- PRUEBA DE COLA ---");

    Cola<Persona> cola = new Cola<>();

    Persona l1 = new Persona("A", "Carlos", 2, "HAB_201");
    Persona l2 = new Persona("K", "Maria", 1, "HAB_101");
    Persona l3 = new Persona("U", "Juan", 3, "HAB_301");

    cola.encolar(l1);
    cola.encolar(l2);
    cola.encolar(l3);

    System.out.println("Personas esperando: " + cola.getTamaño());

    System.out.println("Primero en la fila: "
        + cola.verFrente().getNombre());

    System.out.println("Sale de la fila: "
        + cola.desencolar().getNombre());

    System.out.println("Sale de la fila: "
        + cola.desencolar().getNombre());

    System.out.println("Ahora el primero es: "
        + cola.verFrente().getNombre());

    System.out.println("Personas restantes: " + cola.getTamaño());
    System.out.println("\nPersonas que siguen en la cola:");
    cola.mostrar();
    
    
    System.out.println("\n--- PRUEBA DE PILA ---");
    Pila<String> ruta = new Pila<>();

    ruta.apilar("Salir de HAB_201");
    ruta.apilar("Avanzar al pasillo");
    ruta.apilar("Bajar por las escaleras");
    ruta.apilar("Llegar a la salida");

    System.out.println("Instrucciones almacenadas: " + ruta.getTamaño());

    System.out.println("\nRuta almacenada:");
    ruta.mostrar();

    System.out.println("\nRetirando instrucciones:");

    while (!ruta.estaVacia()) {
    System.out.println(ruta.desapilar());
    }

    System.out.println("Pila vacia: " + ruta.estaVacia());
    System.out.println("\n--- PRUEBA DE ARBOL BINARIO ---");

    ArbolBinario arbol = new ArbolBinario();

    Zona z1 = new Zona("Z01", "Laboratorio", 4);
    Zona z2 = new Zona("Z02", "Pasillo norte", 2);
    Zona z3 = new Zona("Z03", "Almacen", 6);

    arbol.insertar(z1);
    arbol.insertar(z2);
    arbol.insertar(z3);

    System.out.println("Zonas ordenadas por riesgo:");
    arbol.mostrarInorden();

    System.out.println("\nBuscando riesgo 6:");

    Zona zonaEncontrada = arbol.buscar(6);

    if (zonaEncontrada != null) {
    System.out.println("Zona encontrada: " + zonaEncontrada.getNombre());
    } else {
    System.out.println("Zona no encontrada.");
    }
    Zona z4 = new Zona("Z04", "Escalera este", 4);

    arbol.insertar(z4);

    System.out.println("\nArbol con riesgos repetidos:");
    arbol.mostrarInorden();
    Edificio edificioPrueba = new Edificio();

    NodoGrafo habitacion = new NodoGrafo("H01", "Habitacion 1", "Habitacion");
    NodoGrafo pasillo = new NodoGrafo("P01", "Pasillo 1", "Pasillo");
    NodoGrafo escalera = new NodoGrafo("E01", "Escalera 1", "Escalera");

    edificioPrueba.agregarNodo(habitacion);
    edificioPrueba.agregarNodo(pasillo);
    edificioPrueba.agregarNodo(escalera);

    edificioPrueba.conectarNodos("H01", "P01", 5);
    edificioPrueba.conectarNodos("P01", "E01", 3);

    edificioPrueba.bloquearArista("H01", "P01");

    edificioPrueba.mostrarGrafo();
    
    System.out.println("\n--- PRUEBA BFS ---");

    BFS bfs = new BFS(12);

    bfs.recorrer(edificioPrueba.buscarNodo("H01"));
    System.out.println("\n--- BFS DESDE EL PASILLO ---");

    BFS bfs2 = new BFS(12);

    bfs2.recorrer(edificioPrueba.buscarNodo("P01"));
    
    
    System.out.println("\n--- PRUEBA DIJKSTRA ---");

    Dijkstra dijkstra = new Dijkstra(
        edificioPrueba.getNodos(),
        edificioPrueba.getCantidadNodos()
);

    dijkstra.calcularRutas(edificioPrueba.buscarNodo("P01"));

    dijkstra.mostrarDistancias();

    System.out.println("\n--- RUTA HACIA LA ESCALERA ---");

    dijkstra.mostrarRuta(edificioPrueba.buscarNodo("E01"));
    System.out.println("\n===== PRUEBA DE EVACUACION =====");

    Edificio edificioServicio = new Edificio();

    NodoGrafo habitacionServicio = new NodoGrafo("H10", "Habitacion 10", "Habitacion");
    NodoGrafo pasilloServicio = new NodoGrafo("P10", "Pasillo 10", "Pasillo");
    NodoGrafo escaleraServicio = new NodoGrafo("E10", "Escalera 10", "Escalera");
    NodoGrafo salidaServicio = new NodoGrafo("S10", "Salida principal", "Salida");

    edificioServicio.agregarNodo(habitacionServicio);
    edificioServicio.agregarNodo(pasilloServicio);
    edificioServicio.agregarNodo(escaleraServicio);
    edificioServicio.agregarNodo(salidaServicio);

    edificioServicio.conectarNodos("H10", "P10", 4);
    edificioServicio.conectarNodos("P10", "E10", 3);
    edificioServicio.conectarNodos("E10", "S10", 2);

    TablaHash tablaServicio = new TablaHash(10);

    Persona personaServicio = new Persona("P100", "Carlos", 1, "H10");
    tablaServicio.insertar(personaServicio);

        EvacuacionService servicio = new EvacuacionService(edificioServicio, tablaServicio);

    servicio.evacuarDesde("P100");

    System.out.println("\nEstado final de la persona:");
    System.out.println(personaServicio);
    System.out.println("\n===== PRUEBA DE RUTA BLOQUEADA =====");

    edificioServicio.bloquearArista("P10", "E10");

    Persona segundaPersona = new Persona("P101", "Maria", 1, "H10");
    tablaServicio.insertar(segundaPersona);

    servicio.evacuarDesde("P101");
    
    edificioServicio.conectarNodos("H10", "E10", 10);

    Persona terceraPersona = new Persona("P102", "Lucia", 1, "H10");
    tablaServicio.insertar(terceraPersona);

    System.out.println("\n===== PRUEBA DE RUTA ALTERNATIVA =====");

    servicio.evacuarDesde("P102");
    
    System.out.println("\n===== PRUEBA DEL SIMULADOR INTEGRADO =====");

    Persona personaA = new Persona("P200", "Andres", 1, "H10");
    Persona personaB = new Persona("P201", "Sofia", 2, "H10");
    Persona personaC = new Persona("P202", "Daniel", 3, "H10");

    // Registrar las personas en la tabla hash.
    tablaServicio.insertar(personaA);
    tablaServicio.insertar(personaB);
    tablaServicio.insertar(personaC);

    // Crear el simulador usando el mismo edificio y servicio.
    Simulador simulador = new Simulador(edificioServicio, servicio);

    // Agregar personas a la cola.
    simulador.agregarPersona(personaA);
    simulador.agregarPersona(personaB);
    simulador.agregarPersona(personaC);

    // Ejecutar los turnos.
    simulador.mostrarCola();

    simulador.avanzarTurno();
    simulador.avanzarTurno();
    simulador.avanzarTurno();

    simulador.mostrarCola();
    System.out.println("\n===== PRUEBA DE PERSONA SIN SALIDA =====");

    edificioServicio.bloquearArista("H10", "E10");

    Persona personaAtrapada = new Persona(
            "P300", "Mateo", 2, "H10"
    );

    tablaServicio.insertar(personaAtrapada);

    Simulador simuladorBloqueado = new Simulador(
            edificioServicio, servicio
    );

    simuladorBloqueado.agregarPersona(personaAtrapada);

    simuladorBloqueado.avanzarTurno();

    System.out.println("\nEstado de Mateo:");
    System.out.println(personaAtrapada);

    simuladorBloqueado.mostrarCola();

     System.out.println("\n===== GESTION DE OCUPANTES =====");

            GestionOcupantes gestion = new GestionOcupantes();

    Persona ocupante1 = new Persona("P400", "Carlos", 1, "H01");
    Persona ocupante2 = new Persona("P401", "Maria", 2, "H02");
    Persona ocupante3 = new Persona("P402", "Juan", 3, "H03");
    Persona ocupante4 = new Persona("P403", "Laura", 3, "H04");

    gestion.agregarOcupante(ocupante1);
    gestion.agregarOcupante(ocupante2);
    gestion.agregarOcupante(ocupante3);
    gestion.agregarOcupante(ocupante4);

    gestion.mostrarOcupantesPorPiso(2);
    gestion.mostrarPersonasEnTercerPiso();
    
    System.out.println("\n===== GESTION DE RIESGOS =====");

        GestionRiesgos riesgos = new GestionRiesgos();

    Zona zona1 = new Zona("Z01", "Laboratorio", 5);
    Zona zona2 = new Zona("Z02", "Biblioteca", 2);
    Zona zona3 = new Zona("Z03", "Cafeteria", 4);
    Zona zona4 = new Zona("Z04", "Almacen", 1);

    riesgos.agregarZona(zona1);
    riesgos.agregarZona(zona2);
    riesgos.agregarZona(zona3);
    riesgos.agregarZona(zona4);

    riesgos.mostrarZonasOrdenadas();

    Zona zonaEncontradaRiesgos = riesgos.buscarZonaPorRiesgo(5);

    if (zonaEncontradaRiesgos != null) {
        System.out.println("\nZona encontrada:");
        System.out.println(zonaEncontradaRiesgos.getNombre());
    }
    System.out.println("\n===== COMPARACION BFS Y DIJKSTRA =====");

    Edificio edificioComparacion = new Edificio();

    NodoGrafo inicioComp = new NodoGrafo("A", "Inicio", "Habitacion");
    NodoGrafo intermedio1Comp = new NodoGrafo("B", "Pasillo 1", "Pasillo");
    NodoGrafo intermedio2Comp = new NodoGrafo("C", "Pasillo 2", "Pasillo");
    NodoGrafo salidaComp = new NodoGrafo("D", "Salida", "Salida");

    edificioComparacion.agregarNodo(inicioComp);
    edificioComparacion.agregarNodo(intermedio1Comp);
    edificioComparacion.agregarNodo(intermedio2Comp);
    edificioComparacion.agregarNodo(salidaComp);

    // Camino directo: una conexion, pero costo 20.
    edificioComparacion.conectarNodos("A", "D", 20);

    // Camino alternativo: tres conexiones, costo total 6.
    edificioComparacion.conectarNodos("A", "B", 2);
    edificioComparacion.conectarNodos("B", "C", 2);
    edificioComparacion.conectarNodos("C", "D", 2);

    System.out.println("\nRecorrido BFS:");

    BFS bfsComparacion = new BFS(12);
    bfsComparacion.recorrer(inicioComp);

    System.out.println("\nRuta seleccionada por Dijkstra:");

    Dijkstra dijkstraComparacion = new Dijkstra(
            edificioComparacion.getNodos(),
            edificioComparacion.getCantidadNodos()
    );

    dijkstraComparacion.calcularRutas(inicioComp);
    dijkstraComparacion.mostrarRuta(salidaComp);
    
    System.out.println("\n===== INTEGRACION DE OCUPANTES Y EVACUACION =====");

    // Crear edificio.
    Edificio edificioLista = new Edificio();

    NodoGrafo habitacionLista = new NodoGrafo(
            "HL01", "Habitacion del tercer piso", "Habitacion"
    );

    NodoGrafo salidaLista = new NodoGrafo(
            "SL01", "Salida de emergencia", "Salida"
    );

    edificioLista.agregarNodo(habitacionLista);
    edificioLista.agregarNodo(salidaLista);

    edificioLista.conectarNodos("HL01", "SL01", 5);

    // Registrar persona en la tabla hash.
    TablaHash tablaLista = new TablaHash(10);

    Persona personaLista = new Persona(
            "P500", "Roberto", 3, "HL01"
    );

    tablaLista.insertar(personaLista);

    // Registrar la misma persona en la lista enlazada.
    GestionOcupantes gestionLista = new GestionOcupantes();

    gestionLista.agregarOcupante(personaLista);

    // Crear servicio de evacuacion.
    EvacuacionService servicioLista = new EvacuacionService(
            edificioLista, tablaLista
    );

    // Consultar antes de evacuar.
    System.out.println("\nAntes de evacuar:");
    gestionLista.mostrarPersonasEnTercerPiso();

    // Evacuar.
    servicioLista.evacuarDesde("P500");

    // Consultar despues de evacuar.
    System.out.println("\nDespues de evacuar:");
    gestionLista.mostrarPersonasEnTercerPiso();
}
}

