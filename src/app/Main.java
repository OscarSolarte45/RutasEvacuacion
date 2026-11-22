
package app;

import estructuras.TablaHash;
import modelo.Persona;

public class Main {

    public static void main(String[] args) {

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
            System.out.println("Persona encontrada: " + encontrada.getNombre());
            System.out.println("Habitacion: " + encontrada.getHabitacion());
        } else {
            System.out.println("Persona no encontrada.");
        }
    }
}

