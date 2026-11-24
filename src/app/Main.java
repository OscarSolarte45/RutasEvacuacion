package app;
import estructuras.TablaHash;
import modelo.Edificio;
import modelo.NodoGrafo;
import modelo.Persona;
import modelo.Zona;
import algoritmos.BFS;
import servicios.EvacuacionService;
import servicios.GestionOcupantes;
import servicios.GestionRiesgos;
import simulacion.Simulador;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" SISTEMA DE EVACUACION DE EDIFICIOS");
        System.out.println("======================================");

        // 1. CREAR EDIFICIO Y NODOS

        Edificio edificio = new Edificio();

        NodoGrafo h101 = new NodoGrafo("H101", "Habitacion 101", "Habitacion");
        NodoGrafo h102 = new NodoGrafo("H102", "Habitacion 102", "Habitacion");
        NodoGrafo h103 = new NodoGrafo("H103", "Habitacion 103", "Habitacion");
        NodoGrafo p101 = new NodoGrafo("P101", "Pasillo primer piso", "Pasillo");
        NodoGrafo e101 = new NodoGrafo("E101", "Escalera principal", "Escalera");
        NodoGrafo p201 = new NodoGrafo("P201", "Pasillo segundo piso", "Pasillo");
        NodoGrafo h201 = new NodoGrafo("H201", "Habitacion 201", "Habitacion");
        NodoGrafo h202 = new NodoGrafo("H202", "Habitacion 202", "Habitacion");
        NodoGrafo p202 = new NodoGrafo("P202", "Pasillo lateral", "Pasillo");
        NodoGrafo e202 = new NodoGrafo("E202", "Escalera secundaria", "Escalera");
        NodoGrafo s201 = new NodoGrafo("S201", "Salida principal", "Salida");
        NodoGrafo s202 = new NodoGrafo("S202", "Salida alternativa", "Salida");

        edificio.agregarNodo(h101);
        edificio.agregarNodo(h102);
        edificio.agregarNodo(h103);
        edificio.agregarNodo(p101);
        edificio.agregarNodo(e101);
        edificio.agregarNodo(p201);
        edificio.agregarNodo(h201);
        edificio.agregarNodo(h202);
        edificio.agregarNodo(p202);
        edificio.agregarNodo(e202);
        edificio.agregarNodo(s201);
        edificio.agregarNodo(s202);

        // 2. CONECTAR EL EDIFICIO

        edificio.conectarNodos("H101", "P101", 2);
        edificio.conectarNodos("H102", "P101", 3);
        edificio.conectarNodos("H103", "P101", 4);
        edificio.conectarNodos("P101", "E101", 2);
        edificio.conectarNodos("E101", "P201", 4);
        edificio.conectarNodos("P201", "H201", 2);
        edificio.conectarNodos("H202", "P202", 2);
        edificio.conectarNodos("P202", "E202", 2);
        edificio.conectarNodos("E202", "P201", 3);
        edificio.conectarNodos("P201", "S201", 2);
        edificio.conectarNodos("P201", "S202", 6);

        // Simular una conexión bloqueada.
        edificio.bloquearArista("P201", "S201");

        // 3. REGISTRAR PERSONAS EN LA TABLA HASH

        TablaHash tablaPersonas = new TablaHash(20);

        Persona carlos = new Persona("G001", "Carlos", 1, "H101");
        Persona maria = new Persona("G002", "Maria", 1, "H102");
        Persona andres = new Persona("G003", "Andres", 2, "H201");

        tablaPersonas.insertar(carlos);
        tablaPersonas.insertar(maria);
        tablaPersonas.insertar(andres);

        // 4. GESTIONAR OCUPANTES CON LISTA ENLAZADA

        GestionOcupantes gestionOcupantes = new GestionOcupantes();

        gestionOcupantes.agregarOcupante(carlos);
        gestionOcupantes.agregarOcupante(maria);
        gestionOcupantes.agregarOcupante(andres);

        // 5. REGISTRAR ZONAS DE RIESGO

        GestionRiesgos gestionRiesgos = new GestionRiesgos();

        gestionRiesgos.agregarZona(new Zona("Z01", "Primer piso", 2));
        gestionRiesgos.agregarZona(new Zona("Z02", "Segundo piso", 3));

        // 6. MOSTRAR INFORMACION DEL EDIFICIO

        System.out.println("\n===== EDIFICIO REGISTRADO =====");
        edificio.mostrarGrafo();

        System.out.println("\n===== RECORRIDO BFS =====");

        BFS bfs = new BFS(edificio.getCantidadNodos());
        bfs.recorrer(h101);

        System.out.println("\n===== ZONAS DE RIESGO =====");
        gestionRiesgos.mostrarZonasOrdenadas();

        System.out.println("\n===== OCUPANTES REGISTRADOS =====");
        tablaPersonas.mostrarTodos();

        // 7. PREPARAR SIMULADOR

        EvacuacionService servicio = new EvacuacionService(
                edificio, tablaPersonas
        );

        Simulador simulador = new Simulador(
                edificio, servicio
        );

        simulador.agregarPersona(carlos);
        simulador.agregarPersona(maria);
        simulador.agregarPersona(andres);

        // 8. EJECUTAR EVACUACION

        System.out.println("\n===== INICIO DE LA EVACUACION =====");

        simulador.mostrarCola();

        for (int i = 0; i < 3; i++) {
            simulador.avanzarTurno();
        }

        // 9. MOSTRAR RESULTADOS

        System.out.println("\n===== RESULTADO FINAL =====");

        gestionOcupantes.mostrarOcupantes();

        System.out.println("\nPersonas pendientes:");
        simulador.mostrarCola();

        System.out.println("\n======================================");
        System.out.println(" SIMULACION FINALIZADA");
        System.out.println("======================================");
    }
}
