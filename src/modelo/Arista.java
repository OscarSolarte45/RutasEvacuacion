package modelo;

public class Arista {

    private NodoGrafo destino;
    private double peso;
    private boolean bloqueada;

    public Arista(NodoGrafo destino, double peso) {
        this.destino = destino;
        this.peso = peso;
        this.bloqueada = false;
    }
    public NodoGrafo getDestino() {
        return destino;
    }

    public void setDestino(NodoGrafo destino) {
        this.destino = destino;
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
}