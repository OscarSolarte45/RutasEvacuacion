package estructuras;

import modelo.Persona;

public class TablaHash {

    private Persona[] tabla; // arreglo donde almacenarán las personas.
    private int capacidad; // cantidad máxima de posiciones disponibles inicialmente.
    private int cantidad; // número de personas registradas actualmente.

    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Persona[capacidad];
        this.cantidad = 0;
    }

    private int calcularIndice(String id) {
        int codigo = id.hashCode(); // convierte el identificador en un número entero.
        return Math.abs(codigo % capacidad); // % capacidad obtiene el residuo de dividir ese número entre la capacidad.
                                             // y Math.abs convierte el resultado en positivo si es negativo.
    }

    public boolean insertar(Persona persona) {

        int indice = calcularIndice(persona.getId());

        int inicio = indice;

        while (tabla[indice] != null) {

            if (tabla[indice].getId().equals(persona.getId())) {
                return false;
            }

            indice = (indice + 1) % capacidad;

            if (indice == inicio) {
                return false;
            }
        }

        tabla[indice] = persona;
        cantidad++;

        return true;
    }

    public Persona buscar(String id) {

        int indice = calcularIndice(id);

        int inicio = indice;

        while (tabla[indice] != null) {

            if (tabla[indice].getId().equals(id)) {
                return tabla[indice];
            }

            indice = (indice + 1) % capacidad;

            if (indice == inicio) {
                break;
            }
        }

        return null;
    }

    public int getCantidad() {
        return cantidad;
    }
}
