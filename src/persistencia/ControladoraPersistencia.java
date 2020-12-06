package persistencia;

import interficie.ControladoraInterficie;
import java.util.ArrayList;

public class ControladoraPersistencia {
    
    ControladoraInterficie ctrl_interficie;
    
    public ControladoraPersistencia(String root, ControladoraInterficie ci) {
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        
    }
       
    public ArrayList<String> llista_id_usuaris() {
        // TODO
        return null;
    }
    
    public ArrayList<String> llista_id_enunciats() {
        // TODO
        return null;
    }
    
    public ArrayList<String> llista_id_partides(String id_usuari) {
        // TODO
        return null;
    }
    
    public String getHashPassword(String id_usuari) {
        // TODO
        return null;
    }
    
    public Object[] getUsuari(String id_usuari) {
        // TODO
        return null;
    }
    
    public Object[] getConfiguracio (String id_usuari) {
        // TODO
        int numeroBlanquesEstablertes = 10;
        int numeroBlanques = 20;
        int dimX = 8;
        int dimY = 8;
        
        Object[] ret = new Object[] {numeroBlanquesEstablertes, numeroBlanques, dimX, dimY};
        return ret;
    }
    
    public Object[] carregaPartida (String id_partida) {
        // TODO
        return null;
    }
    
    public boolean guardaPartida (String usuari, Integer timestamp, String taulerFormatEstandard) {
        // TODO
        return true;
    }
    
}