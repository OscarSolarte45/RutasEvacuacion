package estructuras;
import modelo.Persona;

public class TablaHash {

    private Persona[] tabla; // arreglo donde almacenarán las personas.
    private int capacidad; //cantidad máxima de posiciones disponibles inicialmente.
    private int cantidad; //número de personas registradas actualmente.
    private boolean[] eliminados;

  
   public TablaHash(int capacidad) {
    this.capacidad = capacidad;
    this.tabla = new Persona[capacidad];
    this.eliminados = new boolean[capacidad];
    this.cantidad = 0;
}
    
private int calcularIndice(String id) {
    int codigo = id.hashCode(); //convierte el identificador en un número entero.
return Math.abs(codigo % capacidad); //% capacidad obtiene el residuo de dividir ese número entre la capacidad. y Math.abs convierte el resultado en positivo si es negativo.
}
    


public boolean insertar(Persona persona) {

    int indice = calcularIndice(persona.getId());
    int inicio = indice;
    int primeraEliminada = -1;

    while (tabla[indice] != null || eliminados[indice]) {

        if (tabla[indice] != null &&
            tabla[indice].getId().equals(persona.getId())) {
            return false;
        }

        if (tabla[indice] == null && primeraEliminada == -1) {
            primeraEliminada = indice;
        }

        indice = (indice + 1) % capacidad;

        if (indice == inicio) {
            break;
        }
    }

    if (primeraEliminada != -1) {
        indice = primeraEliminada;
    } else if (tabla[indice] != null) {
        return false;
    }

    tabla[indice] = persona;
    eliminados[indice] = false;
    cantidad++;

    return true;
}




public Persona buscar(String id) {

    int indice = calcularIndice(id);
    int inicio = indice;

    while (tabla[indice] != null || eliminados[indice]) {

        if (tabla[indice] != null &&
            tabla[indice].getId().equals(id)) {

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
public void mostrarTodos() {

    for (int i = 0; i < capacidad; i++) {

        if (tabla[i] != null) {

            Persona persona = tabla[i];

            System.out.println(
                "ID: " + persona.getId()
                + " | Nombre: " + persona.getNombre()
                + " | Piso: " + persona.getPiso()
                + " | Habitacion: " + persona.getHabitacion()
                + " | Evacuado: " + persona.isEvacuado()
            );
        }
    }
}


public boolean eliminar(String id) {

    int indice = calcularIndice(id);
    int inicio = indice;

    while (tabla[indice] != null || eliminados[indice]) {

        if (tabla[indice] != null &&
            tabla[indice].getId().equals(id)) {

            tabla[indice] = null;
            eliminados[indice] = true;
            cantidad--;

            return true;
        }

        indice = (indice + 1) % capacidad;

        if (indice == inicio) {
            break;
        }
    }

    return false;
}


}
