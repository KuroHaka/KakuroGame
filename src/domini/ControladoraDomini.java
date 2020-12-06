package domini;

import domini.ranking.Ranking;
import domini.repositori.Repositori;
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
    
    public void inicia(ArrayList<String> llista_id_usuaris, ArrayList<String> llista_id_enunciats/*TODO : falta ranking*/) {
        this.id_usuaris = llista_id_usuaris;
        this.id_enunciats_repo = llista_id_enunciats;
    }
    
    public boolean validarCredencials (String usuari, String password){
        Object[] obj = ctrl_persist.getUsuari(usuari);
        Usuari u = new Usuari();
        return true;
    }
    
    public boolean seleccionaUsuari (String id_usuari){
        if( ! id_usuaris.contains(id_usuari)) return false;
        if( id_usuari == this.id_usuari_actual) return true;
        id_usuari_actual = id_usuari;
        //Set Usuari (i inicia)
        
        return true;
    }
    
    // MÈTODES
    
    
    
}