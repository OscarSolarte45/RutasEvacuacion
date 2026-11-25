package servicios;

import algoritmos.Dijkstra;
import estructuras.ListaEnlazada;
import modelo.Arista;
import modelo.Edificio;
import modelo.NodoGrafo;
import modelo.Persona;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExportadorPlan {

    private static final String LINEA = "=".repeat(60);
    private static final String SUBLINEA = "-".repeat(60);

    private final Edificio edificio;
    private final ListaEnlazada<Persona> ocupantes;

    public ExportadorPlan(Edificio edificio, ListaEnlazada<Persona> ocupantes) {
        this.edificio = edificio;
        this.ocupantes = ocupantes;
    }

    // Construye el contenido completo del plan como texto.
    public String generarTexto() {
        StringBuilder sb = new StringBuilder();
        String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        sb.append(LINEA).append('\n');
        sb.append("PLAN DE EVACUACION DEL EDIFICIO\n");
        sb.append("Generado: ").append(fecha).append('\n');
        sb.append(LINEA).append("\n\n");

        agregarConexionesBloqueadas(sb);
        agregarRutasPorPersona(sb);

        return sb.toString();
    }

    private void agregarConexionesBloqueadas(StringBuilder sb) {
        sb.append("CONEXIONES BLOQUEADAS\n").append(SUBLINEA).append('\n');

        int bloqueadas = 0;

        for (int i = 0; i < edificio.getCantidadNodos(); i++) {
            NodoGrafo nodo = edificio.getNodos()[i];

            for (int j = 0; j < nodo.getAristas().getTamaño(); j++) {
                Arista arista = nodo.getAristas().obtener(j);

                // Las conexiones son bidireccionales: se lista cada una solo una vez.
                if (arista.isBloqueada()
                        && nodo.getId().compareTo(arista.getDestino().getId()) < 0) {
                    sb.append("  ").append(nodo.getNombre())
                            .append(" (").append(nodo.getId()).append(")")
                            .append(" <-> ")
                            .append(arista.getDestino().getNombre())
                            .append(" (").append(arista.getDestino().getId()).append(")\n");
                    bloqueadas++;
                }
            }
        }

        if (bloqueadas == 0) {
            sb.append("  (ninguna)\n");
        }
        sb.append('\n');
    }

    private void agregarRutasPorPersona(StringBuilder sb) {
        sb.append("RUTAS DE EVACUACION POR PERSONA\n").append(SUBLINEA).append('\n');

        int total = ocupantes.getTamaño();
        int conRuta = 0;
        int sinRuta = 0;
        int yaEvacuados = 0;

        for (int i = 0; i < total; i++) {
            Persona persona = ocupantes.obtener(i);

            sb.append('\n').append(i + 1).append(". ").append(persona.getNombre())
                    .append(" (").append(persona.getId()).append(")")
                    .append(" - Piso ").append(persona.getPiso())
                    .append(", ").append(persona.getHabitacion()).append('\n');

            if (persona.isEvacuado()) {
                yaEvacuados++;
                sb.append("   Estado: ya evacuado\n");
                continue;
            }

            NodoGrafo origen = edificio.buscarNodo(persona.getHabitacion());
            Dijkstra dijkstra = null;
            NodoGrafo salida = null;

            if (origen != null) {
                dijkstra = new Dijkstra(edificio.getNodos(), edificio.getCantidadNodos());
                dijkstra.calcularRutas(origen);
                salida = buscarSalidaMasCercana(dijkstra);
            }

            if (salida == null) {
                sinRuta++;
                sb.append("   ALERTA: no hay ruta disponible hacia una salida.\n");
                continue;
            }

            conRuta++;
            ListaEnlazada<NodoGrafo> ruta = dijkstra.obtenerRuta(salida);

            sb.append("   Salida asignada: ").append(salida.getNombre()).append('\n');
            sb.append("   Instrucciones:\n");
            for (int p = 0; p < ruta.getTamaño(); p++) {
                NodoGrafo paso = ruta.obtener(p);
                sb.append("     ").append(p + 1).append(". ").append(paso.getNombre()).append('\n');
            }
            sb.append("   Costo total: ").append(dijkstra.obtenerDistancia(salida)).append('\n');
        }

        sb.append('\n').append(SUBLINEA).append('\n');
        sb.append("Total de ocupantes: ").append(total).append('\n');
        sb.append("Con ruta asignada: ").append(conRuta).append('\n');
        sb.append("Sin ruta disponible: ").append(sinRuta).append('\n');
        sb.append("Ya evacuados: ").append(yaEvacuados).append('\n');
    }

    // Salida alcanzable de menor costo, o null si ninguna es alcanzable.
    private NodoGrafo buscarSalidaMasCercana(Dijkstra dijkstra) {
        NodoGrafo mejor = null;
        double menor = Double.MAX_VALUE;

        for (int i = 0; i < edificio.getCantidadNodos(); i++) {
            NodoGrafo nodo = edificio.getNodos()[i];

            if (nodo.getTipo().equalsIgnoreCase("Salida")) {
                double distancia = dijkstra.obtenerDistancia(nodo);

                if (distancia < menor) {
                    menor = distancia;
                    mejor = nodo;
                }
            }
        }
        return mejor;
    }

    // Guarda el plan en un archivo .txt (UTF-8) y devuelve su ruta absoluta.
    public Path exportarATxt(String rutaArchivo) throws IOException {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacia.");
        }
        if (!rutaArchivo.toLowerCase().endsWith(".txt")) {
            rutaArchivo += ".txt";
        }

        Path destino = Paths.get(rutaArchivo).toAbsolutePath();
        Path carpeta = destino.getParent();
        if (carpeta != null) {
            Files.createDirectories(carpeta);
        }

        Files.writeString(destino, generarTexto(), StandardCharsets.UTF_8);
        return destino;
    }
}