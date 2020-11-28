package persistencia;

import domini.hashing.Hash;
import interficie.ControladoraInterficie;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    String dir_root;
    String dir_enunciats;
    String dir_partides;
    String dir_usuaris;
    String dir_shadow;
    
    ControladoraInterficie ctrl_interficie;
    
    public ControladoraPersistencia(String root, ControladoraInterficie ci) {
        this.dir_root = root;
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        dir_enunciats = dir_root + "/enunciats";
        dir_partides = dir_root + "/partides";
        dir_usuaris = dir_root + "/usuaris";
        dir_shadow = dir_usuaris + "/shadow.txt";
    }
    
    public Vector<String> llistaPartidesUsuari(String username){ // Un array millor? Depèn de lo que accepti l'objecte de l.interficie
        String[] totes_partides = Dades.llistaArxius(dir_partides);
        Vector<String> partides_usuari = new Vector<>();
        if (totes_partides.length != 0)
        for (String f : totes_partides) {
            //System.out.println("arxiu: " + f);
            if (f.startsWith(username+"@")) partides_usuari.add(f);
        }
        return partides_usuari;
    }
    
    public boolean validaCredencials (String u, String p) {
        String f = null;
        try {
            f = Dades.carregaArxiu(dir_shadow);
        } catch (NoSuchFileException ex) {
            return false;
        }
        String[] shadow = f.split("\n");
        for (String line : shadow){
            String[] params = line.split(":");
            if(u.equals(params[0])){
                String hash = Hash.calculaHash(p);
                System.out.println("(Ctr.Persist) Hash pwd:" + hash + "  Shadow:" + params[1]);
                return hash.equals(params[1]);
            }
        }
        return false;
    }
    
    public boolean registrarUsuari (String u, String p){
        String hash = Hash.calculaHash(p);
        String line = "\n" + u + ":" + hash;
        String f = null;
        try {
            f = Dades.carregaArxiu(dir_shadow);
        } catch (NoSuchFileException ex) {
            System.out.println("(Ctr.Persist) No s'ha trobat l'arxiu " + dir_shadow);
            return false;
        }
        // Comprovar que no existeixi
        String[] shadow = f.split("\n");
        for (String l : shadow){
            String[] params = l.split(":");
            if(u.equals(params[0])){
                System.out.println("(Ctr.Persist) L'usuari " + params[0] + " ja existeix");
                return false;
            }
        }
        
        String new_f = f + line;
        Dades.guardarArxiu(dir_shadow, new_f);
        return true;
    }
    
    public void guardaPartida (String usuari, Integer timestamp, String taulerFormatEstandard) {
        //Hash.calculaHash(format_Estandard());
        // TODO
    }
    
}
