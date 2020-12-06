package persistencia;

import interficie.ControladoraInterficie;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    ControladoraInterficie ctrl_interficie;
    String root = "dades/";
    
    public ControladoraPersistencia(ControladoraInterficie ci) {
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        
    }
       
    public ArrayList<String> llista_usuaris() {
        ArrayList<String> llista = new ArrayList<>();
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            llista.add(fila.split(":")[0]);
        }
        
        return llista;
    }
    
    public ArrayList<String> llista_id_enunciats() {
        // TODO
        return null;
    }
    
    public ArrayList<String> llista_id_partides(String usuari) {
        // TODO
        return null;
    }
    
    public String getHashPassword(String usuari) {
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            if (fila.split(":")[0].equals(usuari)) {
                return fila.split(":")[1];
            }
        }
        return "no_existeix_usuari";
    }
    
    public Object[] getUsuari(String usuari) {
        // TODO
        return null;
    }
    
    public Object[] getConfiguracio (String usuari) {
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
    
    public boolean addUser(String usuari, String hash) {
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String line = "\n" + usuari + ":" + hash + ":";
        
        String new_shadow = shadow + line;
        Dades.guardarArxiu(root + "shadow.txt", new_shadow);
        
        return true;
    }
    
}