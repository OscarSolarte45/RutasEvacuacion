package servicios;

import estructuras.ListaEnlazada;
import modelo.Persona;

public class GestionOcupantes {

    private ListaEnlazada<Persona> ocupantes;

    public GestionOcupantes() {
        this.ocupantes = new ListaEnlazada<>();
    }

    public void agregarOcupante(Persona persona) {
        ocupantes.agregar(persona);
    }

    public void mostrarOcupantes() {
        ocupantes.mostrar();
    }

    public int getCantidadOcupantes() {
        return ocupantes.getTamaño();
    }
    public void mostrarOcupantesPorPiso(int piso) {

    System.out.println("\nOcupantes del piso " + piso + ":");

    boolean encontrado = false;

    for (int i = 0; i < ocupantes.getTamaño(); i++) {

        Persona persona = ocupantes.obtener(i);

        if (persona.getPiso() == piso && !persona.isEvacuado()) {

            System.out.println(persona);
            encontrado = true;
        }
    }

    if (!encontrado) {
        System.out.println("No hay ocupantes en este piso.");
    }
}
    public void mostrarPersonasEnTercerPiso() {
    mostrarOcupantesPorPiso(3);
}
    
}