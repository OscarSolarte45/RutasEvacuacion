package modelo;

public class Persona {

    private String id;
    private String nombre;
    private int piso;
    private String habitacion;
    private boolean evacuado;

    public Persona(String id, String nombre, int piso, String habitacion) {
        this.id = id;
        this.nombre = nombre;
        this.piso = piso;
        this.habitacion = habitacion;
        this.evacuado = false;
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

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public boolean isEvacuado() {
        return evacuado;
    }

    public void setEvacuado(boolean evacuado) {
        this.evacuado = evacuado;
    }

    public void evacuar() {
    this.evacuado = true;
    }
    @Override
    public String toString() {
    return "ID: " + id
            + " | Nombre: " + nombre
            + " | Piso: " + piso
            + " | Habitacion: " + habitacion
            + " | Evacuado: " + evacuado;
    }
        this.evacuado = true;
    }

    @Override
    public String toString() {
        return 
        
        "id: " + id + " | " + 
        "nombre: " + nombre + " | " +
        " (piso: " + piso + ", " 
        + habitacion + ")"
        + (evacuado ? " [evacuado]" : "");
    }

}
