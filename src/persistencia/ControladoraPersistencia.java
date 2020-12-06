package persistencia;

import interficie.ControladoraInterficie;
import java.util.Vector;

public class ControladoraPersistencia {
    
    ControladoraInterficie ctrl_interficie;
    
    public ControladoraPersistencia(String root, ControladoraInterficie ci) {
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        
    }
    
    public Object[] getUsuari(String usuari) {
        // TODO
        return null;
    }
    
    public Vector<String> llista_ids_PartidesUsuari(String username){ // Un array millor? Depèn de lo que accepti l'objecte de l.interficie
        String[] totes_partides = Dades.llistaArxius("arxiu.json");
        Vector<String> partides_usuari = new Vector<>();
        // TODO
        return partides_usuari;
    }
    
    public void guardaPartida (String usuari, Integer timestamp, String taulerFormatEstandard) {
        // TODO
    }
    
}