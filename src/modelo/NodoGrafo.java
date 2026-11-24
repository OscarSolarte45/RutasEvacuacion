package modelo;

import estructuras.ListaEnlazada;


public class NodoGrafo {

    private String id;
    private String nombre;
    private String tipo;
    private ListaEnlazada<Arista> aristas;

    public NodoGrafo(String id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.aristas = new ListaEnlazada<>();
    }
    public String getId() {
    return id;
}

    public void setId(String id) {
      this.id = id;
    }

    public String getNombre() {
     return nombre;
    }

    public void setNombre(String nombre) {
     this.nombre = nombre;
    }

    public String getTipo() {
      return tipo;
    }

    public void setTipo(String tipo) {
      this.tipo = tipo;
    }

    public ListaEnlazada<Arista> getAristas() {
     return aristas;
    }
}