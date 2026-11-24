package modelo;
public class Zona {
    private String id;
    private String nombre;
    private int nivelRiesgo;
    
    public Zona(String id, String nombre, int nivelRiesgo) {
    this.id = id;
    this.nombre = nombre;
    this.nivelRiesgo = nivelRiesgo;
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

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }
}
