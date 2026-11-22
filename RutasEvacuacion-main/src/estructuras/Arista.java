package estructuras;

public class Arista {

    private String origen;
    private String destino;
    private double peso;
    private boolean bloqueada;

    public Arista(String origen, String destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.bloqueada = false;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    @Override
    public String toString() {
        return origen + " -> " + destino
                + " (peso: " + peso + ")"
                + (bloqueada ? " [BLOQUEADA]" : "");
    }
}