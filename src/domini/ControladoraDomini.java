package domini;

import domini.hashing.Hash;
import domini.ranking.Ranking;
import domini.repositori.Repositori;
import domini.usuari.Configuracio;
import domini.usuari.Usuari;
import interficie.ControladoraInterficie;
import java.util.ArrayList;
import java.util.Vector;
import persistencia.ControladoraPersistencia;

public class ControladoraDomini {
    
    // ATRIBUTS
    
    // Usuaris
    private ArrayList<String> usuaris;
    private String nom_usuari_actual;
    private Usuari usuari_actual;
    // Repositori
    private Vector<String> id_enunciats_repo;
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
    
    public void inicia () {
        this.usuaris = ctrl_persist.llista_usuaris();
        this.id_enunciats_repo = ctrl_persist.llista_id_enunciats();
    }
    
    public boolean seleccionaUsuari (String nom_usuari){
        if( ! usuaris.contains(nom_usuari)) return false;
        if( nom_usuari.equals(this.nom_usuari_actual)) return true;
        
        // TODO
        String hashPwd = ctrl_persist.getHashPassword(nom_usuari);
        Vector<String> partides = ctrl_persist.llista_id_partides(nom_usuari);
        
        Object[] ret = ctrl_persist.getConfiguracio(nom_usuari);
        Configuracio c = new Configuracio((int)ret[0], (int)ret[1], (int)ret[2], (int)ret[3]);
        
        Usuari u = new Usuari(nom_usuari, hashPwd, c, partides);
        this.nom_usuari_actual = nom_usuari;
        this.usuari_actual = u;
        return true;
    }
    
    // MÈTODES per la capa de Domini
    
    public Vector<String> llistaPartidesUsuari() {
        return usuari_actual.getLlistaIdPartides();
    }
    
    public Vector<String> llista_id_enunciats() {
        //return ctrl_persist.llista_id_enunciats();
        return new Vector<String>();
    }
    
    // API amb Persistència
    
    public boolean registrarUsuari (String nom_u, String password) {
        if (usuaris.contains(nom_u)){
            System.out.println("(CtrlDomini) Usuari ja existeix");
            return false;
        }
        String hash = Hash.calculaHash(password);
        boolean correcte = ctrl_persist.addUser(nom_u, hash);
        if (correcte){
            usuaris.add(nom_u);
        }
        return correcte;
    }
        
    public boolean validarCredencials (String id_usuari, String password){
        String hash = Hash.calculaHash(password);
        String real = ctrl_persist.getHashPassword(id_usuari);
        return real.equals(hash);
    }
    
    public Object[] getInfoPartida (String id_partida) {
        return ctrl_persist.getInfoPartida(id_partida);
    }
    
    public boolean borrarPartida (String id_partida, String usuari) {
        return ctrl_persist.borrarPartida(id_partida, usuari);
    }
    
    public Object[] getConfigPreferida (String usuari) {
        return ctrl_persist.getConfiguracio(usuari);
    }
    
    // TODO
    public boolean setConfigPreferida (String usuari, Object[] conf) {
        //return ctrl_persist.setConfiguracio(usuari, conf);
        return true;
    }
    
}