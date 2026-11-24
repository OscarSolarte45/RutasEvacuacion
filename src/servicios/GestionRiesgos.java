package servicios;

import estructuras.ArbolBinario;
import modelo.Zona;

public class GestionRiesgos {

    private ArbolBinario arbolRiesgos;

    public GestionRiesgos() {
        this.arbolRiesgos = new ArbolBinario();
    }

    public void agregarZona(Zona zona) {
        arbolRiesgos.insertar(zona);
    }

    public Zona buscarZonaPorRiesgo(int nivelRiesgo) {
        return arbolRiesgos.buscar(nivelRiesgo);
    }

    public void mostrarZonasOrdenadas() {
        System.out.println("Zonas ordenadas por nivel de riesgo:");
        arbolRiesgos.mostrarInorden();
    }
}