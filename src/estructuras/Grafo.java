package estructuras;

public class Grafo {

    

    private static class VerticeAdyacencia {
        private final String id;
        private final ListaEnlazada<Arista> aristas;

        public VerticeAdyacencia(String id) {
            this.id = id;
            this.aristas = new ListaEnlazada<>();
        }
    }

    private final ListaEnlazada<VerticeAdyacencia> vertices;

    public Grafo() {
        this.vertices = new ListaEnlazada<>();
    }

    private VerticeAdyacencia buscarVertice(String id) {
        for (VerticeAdyacencia v : vertices) {
            if (v.id.equals(id)) {
                return v;
            }
        }
        return null;
    }

    public boolean existeNodo(String id) {
        return buscarVertice(id) != null;
    }

    public void agregarNodo(String id) {
        if (id == null || id.isBlank() || existeNodo(id)) {
            return;
        }
        vertices.agregar(new VerticeAdyacencia(id));
    }

    // Agrega conexión bidireccional (útil para pasillos, puertas y escaleras)
    public void conectar(String origen, String destino, double peso) {
        if (!existeNodo(origen) || !existeNodo(destino)) {
            throw new IllegalArgumentException("Ambos nodos deben existir antes de conectar.");
        }
        agregarAristaDirigida(origen, destino, peso);
        agregarAristaDirigida(destino, origen, peso);
    }

    public void agregarAristaDirigida(String origen, String destino, double peso) {
        VerticeAdyacencia vOrigen = buscarVertice(origen);
        if (vOrigen == null) {
            throw new IllegalArgumentException("No existe el nodo origen: " + origen);
        }

        for (Arista a : vOrigen.aristas) {
            if (a.getDestino().equals(destino)) {
                a.setPeso(peso);
                return;
            }
        }

        vOrigen.aristas.agregar(new Arista(origen, destino, peso));
    }

    public ListaEnlazada<Arista> obtenerVecinos(String id) {
        VerticeAdyacencia v = buscarVertice(id);
        if (v == null) {
            return new ListaEnlazada<>();
        }
        return v.aristas;
    }

    // Bloquea en ambos sentidos para simular humo o incendio en el tramo
    public boolean bloquearArista(String origen, String destino) {
        boolean b1 = cambiarEstadoBloqueo(origen, destino, true);
        boolean b2 = cambiarEstadoBloqueo(destino, origen, true);
        return b1 || b2;
    }

    public boolean desbloquearArista(String origen, String destino) {
        boolean d1 = cambiarEstadoBloqueo(origen, destino, false);
        boolean d2 = cambiarEstadoBloqueo(destino, origen, false);
        return d1 || d2;
    }

    private boolean cambiarEstadoBloqueo(String origen, String destino, boolean estado) {
        VerticeAdyacencia vOrigen = buscarVertice(origen);
        if (vOrigen == null) {
            return false;
        }
        for (Arista a : vOrigen.aristas) {
            if (a.getDestino().equals(destino)) {
                a.setBloqueada(estado);
                return true;
            }
        }
        return false;
    }

    public void mostrarGrafo() {
        for (VerticeAdyacencia v : vertices) {
            System.out.println("Nodo [" + v.id + "]:");
            if (v.aristas.estaVacia()) {
                System.out.println("   (sin conexiones)");
            } else {
                for (Arista a : v.aristas) {
                    System.out.println("   -> " + a);
                }
            }
        }
    }
}
