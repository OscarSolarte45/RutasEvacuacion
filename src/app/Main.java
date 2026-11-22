
package app;

import estructuras.ArbolBinario;
import estructuras.Cola;
import estructuras.Grafo;
import estructuras.Pila;
import estructuras.TablaHash;
import modelo.Persona;

public class Main {

    public static void main(String[] args) {

        // Prueba tabla HAsh

        System.out.println("\nPRUEBA TABLA HASH\n");
        TablaHash tabla = new TablaHash(10);

        Persona persona1 = new Persona("P001", "Carlos", 2, "HAB_201");
        Persona persona2 = new Persona("P002", "Maria", 1, "HAB_101");
        Persona persona3 = new Persona("P003", "Juan", 3, "HAB_301");

        tabla.insertar(persona1);
        tabla.insertar(persona2);
        tabla.insertar(persona3);

        System.out.println("Personas registradas: " + tabla.getCantidad());

        Persona encontrada = tabla.buscar("P002");

        if (encontrada != null) {
            System.out.println("Persona encontrada: " + encontrada.toString());
        } else {
            System.out.println("Persona no encontrada.");
        }

        // Prueba de Cola

        System.out.println("\nPRUBEA COLA\n ");
        Cola<Persona> colaPuerta = new Cola<>();
        colaPuerta.encolar(new Persona("P001", "Carlos", 2, "HAB_201"));
        colaPuerta.encolar(new Persona("P002", "Maria", 1, "HAB_101"));

        System.out.println("Primero en pasar: " + colaPuerta.frente());
        System.out.println("Evacua turno 1: " + colaPuerta.desencolar());
        System.out.println("Quedan en cola: " + colaPuerta.getTamanio());
        System.out.println("Quedan en cola: " + colaPuerta.frente());

        // Prueba pila
        System.out.println("\n==========Prueba pila===============\n");
        Pila<String> pilaRuta = new Pila<>();
        pilaRuta.apilar("Salida A");
        pilaRuta.apilar("Pasillo 2");
        pilaRuta.apilar("Baje escalera B");
        pilaRuta.apilar("Alabio");
        pilaRuta.apilar("Alabao");

        System.out.println("Siguiente instrucción en cima: " + pilaRuta.cima());

        while (!pilaRuta.estaVacia()) {
            System.out.println("Paso: " + pilaRuta.desapilar());
        }

        // Prueba Arbol
        System.out.println("\n==========Prueba ARBOL===============\n");
        ArbolBinario<Integer, String> arbolRiesgo = new ArbolBinario<>();
        arbolRiesgo.insertar(5, "Zona Social");
        arbolRiesgo.insertar(2, "Recepción");
        arbolRiesgo.insertar(8, "Centro de Gas");
        arbolRiesgo.insertar(4, "Pasillo Piso 2");

        System.out.println("=== Recorrido InOrden (menor a mayor riesgo) ===");
        arbolRiesgo.imprimirInOrden();

        System.out.println("Buscar riesgo 8: " + arbolRiesgo.buscar(8));

        // Prueba Grafo
        System.out.println("\n==========Prueba GRAFO===============\n");

        Grafo grafo = new Grafo();
        grafo.agregarNodo("HAB_201");
        grafo.agregarNodo("PAS_2");
        grafo.agregarNodo("ESC_A");
        grafo.agregarNodo("EXIT_A");

        grafo.conectar("HAB_201", "PAS_2", 5);
        grafo.conectar("PAS_2", "ESC_A", 8);
        grafo.conectar("ESC_A", "EXIT_A", 10);

        grafo.bloquearArista("PAS_2", "ESC_A");
        grafo.mostrarGrafo();

    }
}
