package domini;

import domini.hashing.Hash;
import domini.ranking.Ranking;
import domini.repositori.Repositori;
import domini.usuari.Configuracio;
import domini.usuari.Usuari;
import interficie.ControladoraInterficie;
import java.util.ArrayList;
import persistencia.ControladoraPersistencia;

public class ControladoraDomini {
    
    // ATRIBUTS
    
    // Usuaris
    private ArrayList<String> id_usuaris;
    private String id_usuari_actual;
    private Usuari usuari_actual;
    // Repositori
    private ArrayList<String> id_enunciats_repo;
    private Repositori repositori;
    
    // Ranking
    //private ArrayList<String> id_entrades_ranking; // ?????
    Ranking ranking;
    
    // CONTROLADORES
    
    public ControladoraInterficie ctrl_interficie;
    public ControladoraPersistencia ctrl_persist;
    
    // CONSTRUCTORES    
    
    public ControladoraDomini (ControladoraInterficie ci, ControladoraPersistencia persist) {
        this.ctrl_interficie = ci;
        this.ctrl_persist = persist;
    }
    
    // INICIALITZACIÓ
    
    public void inicia() {
        this.id_usuaris = ctrl_persist.llista_usuaris();
        this.id_enunciats_repo = ctrl_persist.llista_id_enunciats();
    }
    
    public boolean validarCredencials (String id_usuari, String password){
        String hash = Hash.calculaHash(password);
        String real = ctrl_persist.getHashPassword(id_usuari);
        return real.equals(hash);
    }
    
    public boolean seleccionaUsuari (String id_usuari){
        if( ! id_usuaris.contains(id_usuari)) return false;
        if( id_usuari.equals(this.id_usuari_actual)) return true;
        
        // TODO
        String hashPwd = ctrl_persist.getHashPassword(id_usuari);
        ArrayList<String> partides = ctrl_persist.llista_id_partides(id_usuari);
        
        Object[] ret = ctrl_persist.getConfiguracio(id_usuari);
        Configuracio c = new Configuracio((int)ret[0], (int)ret[1], (int)ret[2], (int)ret[3]);
        
        Usuari u = new Usuari(id_usuari, hashPwd, c, partides);
        this.id_usuari_actual = id_usuari;
        return true;
    }
    
    // MÈTODES
    
    
    
}