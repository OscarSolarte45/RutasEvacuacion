
package app;

import estructuras.TablaHash;
import modelo.Persona;

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
}

}

