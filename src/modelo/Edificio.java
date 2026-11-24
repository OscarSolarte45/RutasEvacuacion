package modelo;

public class Edificio {

    private NodoGrafo[] nodos;
    private int cantidadNodos;

    public Edificio() {
        this.nodos = new NodoGrafo[12];
        this.cantidadNodos = 0;
    }
   public NodoGrafo[] getNodos() {
     return nodos;
}

    public int getCantidadNodos() {
        return cantidadNodos;
}
    public void agregarNodo(NodoGrafo nodo) {

    if (cantidadNodos >= nodos.length) {
        System.out.println("El edificio ya alcanzo su capacidad maxima.");
        return;
    }

    if (buscarNodo(nodo.getId()) != null) {
        System.out.println("Ya existe un nodo con ese ID.");
        return;
    }

    nodos[cantidadNodos] = nodo;
    cantidadNodos++;

    System.out.println("Nodo agregado correctamente.");
    }
    
    public NodoGrafo buscarNodo(String id) {

    for (int i = 0; i < cantidadNodos; i++) {

        if (nodos[i].getId().equals(id)) {
            return nodos[i];
        }
    }

    return null;
    }
    
    public void conectarNodos(String idOrigen, String idDestino, double peso) {

    NodoGrafo origen = buscarNodo(idOrigen);
    NodoGrafo destino = buscarNodo(idDestino);

    if (origen == null || destino == null) {
        System.out.println("No se encontro alguno de los nodos.");
        return;
    }

    if (peso <= 0) {
        System.out.println("El peso debe ser mayor que cero.");
        return;
    }

    origen.getAristas().agregar(new Arista(destino, peso));
    destino.getAristas().agregar(new Arista(origen, peso));

    System.out.println("Nodos conectados correctamente.");
    }
    public void bloquearArista(String idOrigen, String idDestino) {

    NodoGrafo origen = buscarNodo(idOrigen);
    NodoGrafo destino = buscarNodo(idDestino);

    if (origen == null || destino == null) {
        System.out.println("No se encontro alguno de los nodos.");
        return;
    }

    boolean bloqueada = false;

    for (int i = 0; i < origen.getAristas().getTamaño(); i++) {

        Arista arista = origen.getAristas().obtener(i);

        if (arista.getDestino() == destino) {
            arista.setBloqueada(true);
            bloqueada = true;
        }
    }

    for (int i = 0; i < destino.getAristas().getTamaño(); i++) {

        Arista arista = destino.getAristas().obtener(i);

        if (arista.getDestino() == origen) {
            arista.setBloqueada(true);
        }
    }

    if (bloqueada) {
        System.out.println("Conexion bloqueada correctamente.");
    } else {
        System.out.println("No existe una conexion entre esos nodos.");
    }
    }
    public void desbloquearArista(String idOrigen, String idDestino) {

    NodoGrafo origen = buscarNodo(idOrigen);
    NodoGrafo destino = buscarNodo(idDestino);

    if (origen == null || destino == null) {
        System.out.println("No se encontro alguno de los nodos.");
        return;
    }

    boolean encontrada = false;

    for (int i = 0; i < origen.getAristas().getTamaño(); i++) {

        Arista arista = origen.getAristas().obtener(i);

        if (arista.getDestino() == destino) {
            arista.setBloqueada(false);
            encontrada = true;
        }
    }

    for (int i = 0; i < destino.getAristas().getTamaño(); i++) {

        Arista arista = destino.getAristas().obtener(i);

        if (arista.getDestino() == origen) {
            arista.setBloqueada(false);
        }
    }

    if (encontrada) {
        System.out.println("Conexion desbloqueada correctamente.");
    } else {
        System.out.println("No existe una conexion entre esos nodos.");
    }
    }
    public void mostrarGrafo() {

    for (int i = 0; i < cantidadNodos; i++) {

        NodoGrafo actual = nodos[i];

        System.out.println("\nLugar: " + actual.getNombre()
                + " (" + actual.getId() + ")");

        for (int j = 0; j < actual.getAristas().getTamaño(); j++) {

            Arista arista = actual.getAristas().obtener(j);

            System.out.println("  -> "
                    + arista.getDestino().getNombre()
                    + " | Peso: " + arista.getPeso()
                    + " | Bloqueada: " + arista.isBloqueada());
        }
    }
    }
    
}